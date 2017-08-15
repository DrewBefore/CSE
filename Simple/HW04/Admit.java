import java.util.*;

public class Admit {
   public static void main(String args[]){
      Scanner console = new Scanner(System.in);
      intro();
      double applicantOneOverall = examScore(1, console) + gpaScore(console);
      double applicantTwoOverall = examScore(2, console) + gpaScore(console);
      conclusion(applicantOneOverall, applicantTwoOverall);
   }
   
   // This method prints out the intro to the program
   // adds a blank line at the end of the intro
   public static void intro(){
      System.out.println("This program compares two applicants to");
      System.out.println("determine which one seems like the stronger");
      System.out.println("applicant.  For each candidate I will need");
      System.out.println("either SAT or ACT scores plus a weighted GPA.");
      System.out.println();
   }
   
   // This method takes in which applicant you want, and a scanner object
   // returns the applicants exam score
   public static double examScore(int applicant, Scanner console){
      System.out.println("Information for applicant #" + applicant + ":");
      System.out.print("    do you have 1) SAT scores or 2) ACT scores? ");
      int exam = console.nextInt();
      double examScore = 0;
      if (exam == 1){
         examScore = satScore(console);
      } else {
         examScore = actScore(console);
      }
      System.out.print("    exam score = ");
      System.out.format("%.1f%n", examScore);       // -->  "3.141593"      
      return examScore;
   }
   
   // This method takes in a Scanner object, and returns an applicants
   // SAT score
   public static double satScore(Scanner console){
      System.out.print("    SAT math? ");
      int math = console.nextInt();
      System.out.print("    SAT critical reading? ");
      int reading = console.nextInt();
      System.out.print("    SAT writing? ");
      int writing = console.nextInt();
      return (2.0 * math + reading + writing) / 32;   
   }
   
   // This method takes in a Scanner object, and returns an applicants
   // ACT score
   public static double actScore(Scanner console){
      System.out.print("    ACT English? ");
      int english = console.nextInt();
      System.out.print("    ACT math? ");
      int math = console.nextInt();
      System.out.print("    ACT reading? ");
      int reading = console.nextInt();
      System.out.print("    ACT science? ");
      int science = console.nextInt();
      return (english + 2 * math + reading + science) / 1.8;      
   }
   
   // This method takes in a Scanner object, and returns an applicants
   // final GPA score
   public static double gpaScore(Scanner console){
      System.out.print("    overall GPA? ");
      double gpa = console.nextDouble();
      System.out.print("    max GPA? ");
      double maxGpa = console.nextDouble();
      System.out.print("    Transcript Multiplier? ");
      double multiplier = console.nextDouble();
      double finalGpaScore = (gpa / maxGpa) * 100 * multiplier;
      System.out.print("    GPA score = ");
      System.out.printf("%.1f%n", finalGpaScore);
      System.out.println(); 
      return finalGpaScore;
   }
   
   // This method takes in two applicants as parameters and prints out
   // their overall scores. Then prints out which applicant seems better
   // or if they seem the same
   public static void conclusion(double applicantOne, double applicantTwo){
      System.out.print("First applicant overall score  = ");
      System.out.printf("%.1f%n", applicantOne);
      System.out.print("Second applicant overall score = ");
      System.out.printf("%.1f%n", applicantTwo);
      if (applicantOne > applicantTwo){
         System.out.println("The first applicant seems to be better");
      } else if (applicantOne == applicantTwo){
         System.out.println("The two applicants seem to be equal");
      } else {
         System.out.println("The second applicant seems to be better");
      }
   }

}