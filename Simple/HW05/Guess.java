// This class plays the Guessing Game. Will pick a number between 1 and MAX_NUM
// Continues to play more games while user wants to. Will report statistics of games
// after user is done playing.

import java.util.*;

public class Guess{
   public static final int MAX_NUM = 100;
   
   public static void main (String args[]){
      Scanner console = new Scanner(System.in);
      intro();
      boolean playAgain = true;
      int games = 0;
      int totalGuesses = 0;
      int bestGame = MAX_NUM; // MAX_NUM to represent worst game possible
      while (playAgain){
         int guesses = playGame(console);
         totalGuesses += guesses;
         if (guesses < bestGame){
            bestGame = guesses;
         }
         games++;
         playAgain = playAgain(console);
         System.out.println();
      }
      results(games, totalGuesses, bestGame);
   }
   
   // Prints out the intro explaining how to play the game                                         
   public static void intro(){
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and");
      System.out.println("100 and will allow you to guess until");
      System.out.println("you get it.  For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.println("than your guess.");
      System.out.println();   
   }
   
   // Accepts a scanner as a parameter and returns a boolean
   // representing true for wanting to play again and false
   // for not wanting to play again.
   public static boolean playAgain(Scanner console){
      System.out.print("Do you want to play again? ");
      String response = console.next();
      response = response.toUpperCase();
      return response.startsWith("Y");
   }
   
   // Plays one game of the guessing game. Accepts a Scanner as parameter
   // and returns the number of guesses made for the one game.
   public static int playGame(Scanner console){
      Random r = new Random();
      System.out.println("I'm thinking of a number between 1 and " + MAX_NUM + "...");
      //int answer = r.nextInt(MAX_NUM + 1);
      int answer = 42;
      int guesses = 0;
      int guess = 0;
      while (guess != answer){
         System.out.print("Your guess? ");
         guess = console.nextInt();
         guesses++;
         if (guess < answer){
            System.out.println("It's higher.");
         } else if (guess > answer){
            System.out.println("It's lower.");
         } else {
            System.out.print("You got it right in " + guesses);
            if (guesses == 1){ // Got it correct in one guess
               System.out.println(" guess");
            } else {
               System.out.println(" guesses");
            }
         }
      }
      return guesses;
   }
   
   // Accepts an int for games played, for guesses made, and for the best
   // game played. Analyzes, formats, and prints out results.
   public static void results(int games, int guesses, int bestGame){
      System.out.println("Overall results:");
      System.out.println("    total games   = " + games);
      System.out.println("    total guesses = " + guesses);
      System.out.printf("    guesses/game  = %.1f%n", (double) guesses / games);
      System.out.println("    best game     = " + bestGame);
   }
}