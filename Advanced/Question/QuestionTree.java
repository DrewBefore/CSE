import java.io.*;
import java.util.*;

// This class stores the binary tree QuestionTree. 
public class QuestionTree{
   private QuestionNode overallRoot;
   private Scanner console;

   // Post: Constructs a question tree with one leaf node representing the object "computer"
   public QuestionTree(){
      overallRoot = new QuestionNode("computer"); 
      console = new Scanner(System.in);
   }
   
   // Pre: assumes the file is legal and in standard format
   // Post: Replaces the current tree by reading another tree from a file
   //       Accepts a scanner as a parameter to make the new tree
   //       Reads entire lines of input at a time
   public void read(Scanner input){
      overallRoot = read(overallRoot, input);  
   }
  
   // Post: Returns a new QuestionNode root from the given line of input
   private QuestionNode read(QuestionNode root, Scanner input){
      String line1 = input.nextLine();
      String line2 = input.nextLine();
      root = new QuestionNode(line2);
      if (line1.equals("Q:")) { 
         root.left = read(root.left, input);
         root.right = read(root.right, input);
      } 
      return root;   
   }
   
   // Post: Stores the current tree to an output file
   public void write(PrintStream output){
      write(overallRoot, output);
   }
   
   // Post: accepts a root, and PrintStream and prints the tree out to an output file 
   private void write(QuestionNode root, PrintStream output){
      if (root != null){
         if (root.left == null && root.right == null){
            output.println("A:");
         } else {
            output.println("Q:");
         }
         output.println(root.data);
         write(root.left, output);
         write(root.right, output);
      }
   }
   
   // Post: Uses the current tree to ask the user yes or no questions
   //       On incorrect guess expands the tree to include new answer and question
   public void askQuestions(){
      askQuestions(overallRoot);
   }
   
   // Post: Continues to ask questions until it wins the game or finds an incorrect answer
   //       Accepts and returns a QuestionNode root
   private void askQuestions(QuestionNode root){
      if (root.left == null && root.right == null){ // answer
         if (yesTo("Would your object happen to be " + root.data + "?")){
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            String newAnswer = console.nextLine();
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String newQuestion = console.nextLine();
            System.out.print("And what is the answer for your object? (y/n)? ");
            String yesNo = console.nextLine().toLowerCase();
            String prevData = root.data;
            root.data = newQuestion;
            if (yesNo.equals("y")){
               root.left = new QuestionNode(newAnswer);
               root.right = new QuestionNode(prevData);
            } else {
               root.left = new QuestionNode(prevData);
               root.right = new QuestionNode(newAnswer);
            }
         }
      } else { // its a question
         if (yesTo(root.data)){
            askQuestions(root.left);
         } else {
            askQuestions(root.right);
         }
      }
   }
   
   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}  