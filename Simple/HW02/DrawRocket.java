// This program will print out a scaleable rocket
public class DrawRocket {
   public static final int SIZE = 3;
   
   public static void main(String[] args){
      drawCone();
      drawRocketLine();
      drawBodyOne();
      drawBodyTwo();
      drawRocketLine();
      drawBodyTwo();
      drawBodyOne();
      drawRocketLine();
      drawCone();
   }
   
   // This method will draw the cone shape found on the top and bottom
   // of the rocket ship
   public static void drawCone(){
      for (int i = 1; i <= SIZE * 2 - 1; i++){
         for (int j = 1; j <= SIZE * 2 - i; j++){
            System.out.print(" ");
         } 
         for (int j = 1; j <= i; j++){
            System.out.print("/");
         }
         System.out.print("**");
         for (int j = 1; j <=i; j++){
            System.out.print("\\");
         }
         System.out.println();
      }
   }
   
   // This method will draw the line that appears in multiple
   // places on the rocket ship (+=*=* etc.)
   public static void drawRocketLine(){
      System.out.print("+");
      for (int i = 1; i <= SIZE * 2; i++){
         System.out.print("=*");
      }
      System.out.println("+");
   }
   
   // This method will print out the part of the rocket ship body
   // with triangles that point up
   public static void drawBodyOne(){
      for (int i = 1; i <= SIZE; i++){
         System.out.print("|");
         for (int j = 1; j <= SIZE - i; j++){
            System.out.print(".");
         }
         for (int j = 1; j <= i; j++){
            System.out.print("/\\");
         }
         for (int j = 1; j <= SIZE * 2 - i * 2; j++){
            System.out.print(".");
         }
         for (int j = 1; j <= i; j++){
            System.out.print("/\\");
         }
         for (int j = 1; j <= SIZE - i; j++){
            System.out.print(".");
         }
         System.out.println("|");
      }
   }
   
   // This method will print out the part of the rocket ship body
   // with triangles that point down
   public static void drawBodyTwo(){
      for (int i = 1; i <= SIZE; i++){
         System.out.print("|");
         for (int j = 1; j <= i - 1; j++){
            System.out.print(".");
         }
         for (int j = 1; j <= SIZE + 1 - i; j++){
            System.out.print("\\/");
         }
         for (int j = 1; j <= i * 2 - 2; j++){
            System.out.print(".");
         }
         for (int j = 1; j <= SIZE  + 1 - i; j++){
            System.out.print("\\/");
         }
         for (int j = 1; j <= i - 1; j++){
            System.out.print(".");
         }
         System.out.println("|");
      }
   }
}

/*
     /**\
    //**\\
   ///**\\\
  ////**\\\\
 /////**\\\\\
+=*=*=*=*=*=*+
|../\..../\..|
|./\/\../\/\.|
|/\/\/\/\/\/\|
|\/\/\/\/\/\/|
|.\/\/..\/\/.|
|..\/....\/..|
+=*=*=*=*=*=*+
|\/\/\/\/\/\/|
|.\/\/..\/\/.|
|..\/....\/..|
|../\..../\..|
|./\/\../\/\.|
|/\/\/\/\/\/\|
+=*=*=*=*=*=*+
     /**\
    //**\\
   ///**\\\
  ////**\\\\
 /////**\\\\\
*/