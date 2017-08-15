// This program will print out a dumbell
/*
    [][]
  [][][][]
[][][][][][]
     ||
     ||
     ||
     ||
[][][][][][]
  [][][][]
    [][]
*/
public class AsciiArt {
   public static void main(String[] args){
      drawTopHalf();
      drawBar();
      drawBottomHalf();
   }
   
   // Draws the top half of the dumbell
   public static void drawTopHalf(){
      for (int i = 1; i <= 3; i++){
         for (int j = 0; j < 6 - i * 2 ; j++){
            System.out.print(" ");
         }
         for (int j = 0; j < i * 2; j++){
            System.out.print("[]");
         }
         System.out.println();
      }   
   }
   
   // Draws the bar connecting top and bottom halves
   public static void drawBar(){
      for (int i = 0; i < 4; i++){
         for (int j = 0; j < 5; j++){
            System.out.print(" ");
         }
         System.out.println("||");
      }
   }
   
   // Draws the bottom half of the dumbell
   public static void drawBottomHalf(){
      for (int i = 1; i <= 3; i++){
         for (int j = 0; j < i * 2 - 2; j++){
            System.out.print(" ");
         }
         for (int j = 0; j < 8 - i * 2; j++){
            System.out.print("[]");
         }
         System.out.println();
      } 
   }
}
