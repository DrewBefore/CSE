import java.io.*;
import java.util.*;

public class Hello {
   public static void main (String[] args) throws FileNotFoundException {
      Scanner s = new Scanner(new File("text.txt"));
      processData(s);
      
   }
   public static void processData(Scanner s){
      int n = 0;
      int sum = 0;
      int curr = 0;
      while(s.hasNextInt()){
         curr++;
         n = s.nextInt();
         sum += n;
         System.out.println("Sum of " + curr + " = " + sum);

      }
      System.out.println("Average = " + (double) sum / curr);
   }
}
