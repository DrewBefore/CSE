import java.util.*;
import java.io.*;

public class Test{
   public static void main(String[] args) throws FileNotFoundException {
   
     Scanner console = new Scanner(System.in);
     System.out.println("Welcome to the CSE random sentence generator.");
     System.out.println();

     // open grammar file
     System.out.print("What is the name of the grammar file? ");
     String fileName = "t.txt";
     Scanner input = new Scanner(new File(fileName));

     // read the grammar file and construct the grammar solver
     List<String> grammar = new ArrayList<String>();
     while (input.hasNextLine()) {
         String next = input.nextLine().trim();
         if (next.length() > 0)
             grammar.add(next);
     } 
     GrammarSolver solver = 
            new GrammarSolver(Collections.unmodifiableList(grammar));

      solver.generate("<t>", 5);
   }
}