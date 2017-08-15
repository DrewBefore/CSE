// This program will print out a cumulative song
public class Song {
   public static void main(String[] args){
      verse1();
      verse2();
      verse3();
      verse4();
      verse5();
      verse6();
      verse7();
   }

   // This method prints out first the verse
   public static void verse1(){
      System.out.println("There was an old woman who swallowed a fly.");
      perhaps();
   }
   
   // This method prints out the second verse
   public static void verse2(){
      System.out.println("There was an old woman who swallowed a spider,");
      System.out.println("That wriggled and iggled and jiggled inside her.");
      spider();
      perhaps();
   }
   
   // This method prints out the third verse
   public static void verse3(){
      System.out.println("There was an old woman who swallowed a bird,");
      System.out.println("How absurd to swallow a bird.");
      bird();
      perhaps();
   }
   
   // This method prints out the fourth verse
   public static void verse4(){
      System.out.println("There was an old woman who swallowed a cat,");
      System.out.println("Imagine that to swallow a cat.");
      cat();
      perhaps();
   }
   
   // This method prints out the fifth verse
   public static void verse5(){
      System.out.println("There was an old woman who swallowed a dog,");
      System.out.println("What a hog to swallow a dog.");
      dog();
      perhaps();
   }
   // This method prints out my custom sixth verse
   public static void verse6(){
      System.out.println("There was an old woman who swallowed a bunny,");
      System.out.println("How funny to swallow a bunny.");
      System.out.println("She swallowed the bunny to catch the dog,");
      dog();
      perhaps();
   }
   
   // This method prints out the seventh verse
   public static void verse7(){
      System.out.println("There was an old woman who swallowed a horse,");
      System.out.println("She died of course.");
   }
   
   // This method prints out the repeated line about the spider
   public static void spider(){
      System.out.println("She swallowed the spider to catch the fly,");
   }
   
   // This method prints out the birds line, and all previous repeated lines
   public static void bird(){
      System.out.println("She swallowed the bird to catch the spider,");
      spider();
   }

   // This method prints out the cats line, and all previous repeated lines
   public static void cat(){
      System.out.println("She swallowed the cat to catch the bird,");
      bird();
   }  
   
   // This method prints out the dogs line and all previous repeated lines
   public static void dog(){
      System.out.println("She swallowed the dog to catch the cat,");
      cat();
   }
   
   // This method prints out the the final two lines of each verse, and
   // adds a space between verses
   public static void perhaps(){
      System.out.println("I don't know why she swallowed that fly,");
      System.out.println("Perhaps she'll die.");
      System.out.println();
   }
}