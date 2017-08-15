// This class Takes answers to the personality test
// and uses those answers to determine the persons personality type
import java.util.*;
import java.io.*;

public class Personality{
   public static final int DIMENSION = 4;
   
   public static void main (String[] args) throws FileNotFoundException{
      intro();
      Scanner console = new Scanner(System.in);
      Scanner input = getInput(console);
      PrintStream output = getOutput(console);
      
      while (input.hasNextLine()){
         String name = input.nextLine();
         output.print(name + ": ");
         String answers = input.nextLine();
         int[] aCount = new int[DIMENSION];
         int[] bCount = new int[DIMENSION];
         countAnswers(aCount, bCount, answers);
         int[] percentB = percentB(aCount, bCount);
         output.print(Arrays.toString(percentB));
         personalityType(percentB, output);
      }
   }
   
   // Print out the Intro to the Personality program
   public static void intro(){
      System.out.println("This program processes a file of answers to the");
      System.out.println("Keirsey Temperament Sorter.  It converts the");
      System.out.println("various A and B answers for each person into");
      System.out.println("a sequence of B-percentages and then into a");
      System.out.println("four-letter personality type.");
      System.out.println();
   }   
   
   // Accapets a Scanner and returns the desired input file Scanner
   public static Scanner getInput(Scanner console) throws FileNotFoundException{
      System.out.print("input file name? ");
      String inputName = console.next();
      Scanner input = new Scanner(new File(inputName));      
      return input;
   }
   
   // Accepts a Scanner and returns the desired File output
   public static PrintStream getOutput(Scanner console) throws FileNotFoundException{
      System.out.print("output file name? ");
      String outputName = console.next();
      PrintStream output = new PrintStream(new File(outputName));     
      return output;
   }
   
   // Accepts two arrays, and a string of answers. Counts number of a's and b's
   // in the array. (ignores case)
   public static void countAnswers(int[] aCount, int[] bCount, String answers){
      for (int i = 0; i < answers.length(); i++){
         char answer = answers.charAt(i);
         if (answer == 'a' || answer == 'A'){
            aCount[findIndex(i)]++;
         } else if (answer == 'b' || answer == 'B'){
            bCount[findIndex(i)]++;
         }
      }
   }
   
   // Accepts an int and returns which arrayIndex that int should
   // go into
   public static int findIndex(int questionNumber){
      int questionType = questionNumber % 7;
      if (questionType == 0){
         return 0;
      } else if (questionType == 1 || questionType == 2){
         return 1;
      } else if (questionType == 3 || questionType == 4){
         return 2;
      } else {
         return 3;
      }
   }
   
   // Accepts two arrays and returns a new array containing percentage of b answers
   public static int[] percentB(int[] aCount, int[] bCount){
      int[] bPercentage = new int[DIMENSION];
      for (int i = 0; i < bPercentage.length; i++){
         double percentage = 100 * ((double) bCount[i] / (aCount[i] + bCount[i]));
         int roundedPercentage = (int) Math.round(percentage); 
         bPercentage[i] = roundedPercentage;
      }
      return bPercentage;
   }
   
   // Accepts an array of b percentages and an output PrintStream. Prints the
   // Correct personality type for the person to output.
   public static void personalityType(int[] percentB, PrintStream output){
      output.print(" = ");
      String type = "";
      char[] types = {'E', 'I', 'S', 'N', 'T', 'F', 'J', 'P'};
      for (int i = 0; i < percentB.length; i++){
         if (percentB[i] < 50){
            type += types[i * 2];
         } else if (percentB[i] > 50) {
            type += types[i * 2 + 1];
         } else {
            type += 'X';
         }        
      }
      output.println(type);
   }   
}