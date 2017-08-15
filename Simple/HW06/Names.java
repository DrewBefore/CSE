import java.io.*;
import java.util.*;
import java.awt.*;

public class Names{
   public static final String FILENAME = "names.txt"; 
   public static final int STARTYEAR = 1880; 
   public static final int YEARS = 131; 
   public static final int WIDTH =  5;
   
   public static void main (String[] args) throws FileNotFoundException{
      Scanner input = new Scanner (new File(FILENAME));
      String line = findLine(input, search());
      yearPop(line);
      graph(line);
   }
   
   // prompts for and returns the users search word (name gender)
   public static String search(){
      System.out.print("Type a name and gender: ");
      Scanner console = new Scanner (System.in);
      String searchWord = console.nextLine();
      return searchWord;   
   }
   
   // uses search word to find line corresponding with that name
   public static String findLine(Scanner input, String search){
      String gender = "";  
      while (input.hasNextLine()){
         String line = input.nextLine();
         Scanner lineScan = new Scanner(line);
         String name = lineScan.next();
         gender = lineScan.next();
         if (search.equalsIgnoreCase(name + " " + gender)){
            return line;       
         }    
      }
      String name = search.toUpperCase().substring(0, search.length()-2);
      System.out.println("\"" + name + "\"" + " (" + gender + ")" + " not found.");
      return ("");       
   }
   
   // prints out the rank and popularity for each decade
   public static void yearPop(String line){
      Scanner lineScan = new Scanner(line);
      if (line.equals("")){
         return;
      } else {
         System.out.println();
         String name = lineScan.next();
         String gender = lineScan.next();
         System.out.print("Popularity ranking of name ");
         System.out.println("\"" + name + "\"" + " (" + gender + ")");     
      }
      int year = STARTYEAR;
      if (year % 10 != 0){
         for (int i =1; i <=  2 * (10 - (year % 10)); i++){
            lineScan.nextInt();
         }
         year = year + 10 - (year % 10);
      }
      while (year < YEARS + STARTYEAR && year <= 2010){
         System.out.println(year + ":" + " " + lineScan.nextInt() + " " + "(" + lineScan.nextInt() + ")");  
         for (int i = 1; i <= 18; i++){
            if (lineScan.hasNextInt()){
            lineScan.nextInt();
            }
         }
         year = year + 10;
      }
   }
   
   // if searchname is found draws the grid and background of the graph
   public static void graph(String line){
      if (line.equals("")){
         return;
      }
      DrawingPanel panel = new DrawingPanel((YEARS - 1) * WIDTH, 560);
      Graphics g = panel.getGraphics();
      g.setColor(Color.YELLOW);
      g.fillRect(0, 0, (YEARS -1) * WIDTH, 30); // top yellow triangle
      g.fillRect(0, 530, (YEARS -1) * WIDTH, 560); // bottom yellow triangle
      g.setColor(Color.BLACK);
      Scanner lineScan = new Scanner(line);
      String name = lineScan.next();
      String gender = lineScan.next();
      g.drawString("Ranking of name " + "\"" + name + "\"" + 
         " (" + gender + "):", 0, 16);
      int year = STARTYEAR - 1;
         // for loop draws the verticle lines
         for (int i = 0; i < YEARS + STARTYEAR; i++){
            year++;
            if (year % 10 == 0){
               g.setColor(Color.LIGHT_GRAY);
               g.drawLine(i * WIDTH, 30, i * WIDTH, 530);
               g.setColor(Color.BLACK);
               g.drawString("" + year,(i * WIDTH), 546);
            }
         }
      g.setColor(Color.LIGHT_GRAY);
      for (int i = 0; i <= 10; i++){ // draws horizontal lines
         g.drawLine(0, 30 + i * 50, (YEARS - 1) * WIDTH, i * 50 + 30);
      }
      plotGraph(panel, g, line, lineScan);
               
   }
   
   // plot the graph using information from line,
   // and translating it to x/y coordinates on drawingpanel
   public static void plotGraph(DrawingPanel panel, Graphics g, String line, Scanner lineScan){
      g.setColor(Color.RED);
      int highYear = STARTYEAR;// year when name reached highest popularity
      int year = STARTYEAR; // current year
      int rank = lineScan.nextInt(); // current rank
      int lowestRank = 5000;// lowest rank that the name achieved
      if (rank < lowestRank && rank != 0) {
         lowestRank = rank;
      }
      int amount = lineScan.nextInt();// amount of people named that year
      int x1 = 0;
      int y1 = (rank / 2) + 30;
      if (rank == 0 || rank >= 1000){
         y1 = 530;
      } 
      int x2 = 0;
      int y2 = 0;
      int highYearX = x1; // where to draw info regarding most popular year
      int highYearY = y1;
      int highAmount = amount;
      for (int i = 0; i < YEARS - 1; i++){
         rank = lineScan.nextInt(); 
         amount = lineScan.nextInt();
         x2 = (i + 1) * WIDTH;
         y2 = rank / 2 + 30;
         year++;
               
         if (rank <= lowestRank && rank != 0){
            lowestRank = rank;
            highYear = year;
            highYearX = x2;
            highAmount = amount;
            if (y2 > 530) {
               highYearY = 530;
            } else { 
               highYearY = y2;
            }
         }
         if (rank == 0){
            y2 = 530;
         } else if (rank >= 1000){
            y2 = 530; 
         }
         g.drawLine(x1, y1, x2, y2);
         x1 = x2;
         y1 = y2;
      }
         g.setColor(Color.BLACK);
         g.drawString(highYear + ":" + " #" + lowestRank + 
            " (" + highAmount + ")", highYearX, highYearY);   
   }
}