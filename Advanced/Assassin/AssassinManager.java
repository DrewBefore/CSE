// This class keeps track of who Assassins are stalking,
// the graveyard, and a history of who killed who
import java.util.*;

public class AssassinManager{
   private AssassinNode killfront;
   private AssassinNode graveyard;

   // Pre: Throws an Illegal Argument Exception if the list is empty
   // Post: Adds names from the list in the same order
   //       Assumes that names are nonempty strings and not duplicates
   public AssassinManager(List<String> names){
      if (names.size() == 0){
         throw new IllegalArgumentException();
      }
      killfront = new AssassinNode(names.get(0));
      AssassinNode current = killfront;
      for (int i = 1; i < names.size(); i++){
         String name = names.get(i);
         current.next = new AssassinNode(name);
         current = current.next;
      }
   }
   
   // Prints each assassins name and who they are stalking
   public void printKillRing(){
      AssassinNode current = killfront;
      while (current.next != null) {
         System.out.println("    " + current.name + " is stalking " + current.next.name);
         current = current.next;    
      }
      System.out.println("    " + current.name + " is stalking " + killfront.name);
   }
   
   // Prints the names, and the killer of the assassins in the graveyard
   // Prints the most recently killed first
   // Prints no output if graveyard is empty
   public void printGraveyard(){
      AssassinNode killer = killfront;
      AssassinNode victim = graveyard;
      while (victim != null){      
         System.out.println("    " + victim.name + " was killed by " + victim.killer);
         victim = victim.next;
      }
   }
   
   // Calls contains method and returns true if name in parameter exists in killring
   // Ignores case in comparison
   public boolean killRingContains(String name){
      return contains(name, killfront);      
   }
   
   // Calls contains method and returns true if the name in paremeter exists in graveyard
   // Ignores case in comparison
   public boolean graveyardContains(String name){
      return contains(name, graveyard);
   }
   
   // Method is used to check for a name in an Assassin List
   // Accepts a name and an AssassinNode as parameters (Killring or Graveyard)
   public boolean contains(String name, AssassinNode node){
      AssassinNode current = node;
      while (current != null){
         if (name.equalsIgnoreCase(current.name)){
            return true;
         }
         current = current.next;
      }
      return false;
   }   
   
   // Returns true if the killring has just 1 assassin in it
   public boolean gameOver(){
      return killfront.next == null;
   }
   
   // Pre: If game is over returns null
   // Post: Returns the name of the winner
   public String winner(){
      if (!gameOver()){
         return null;
      } else {
         return killfront.name;
      }
      
   }
   
   // Pre: If killring doesn't contain the passed in name throws IllegalArgumentException()
   //      Or if the game is over throws IllegalStateException()
   // Post: Records the killing of an assassin, and switches who alive assassins are stalking
   //       Ignores case in comparing names from parameter to killring name
   //       Assigns to the victim who their killer is
   public void kill(String name){
      if (!killRingContains(name)){
         throw new IllegalArgumentException();
      }
      if (gameOver()){
         throw new IllegalStateException();
      }
      AssassinNode current = killfront;
      AssassinNode prev = killfront;
      while (!current.name.equalsIgnoreCase(name)){
         prev = current;
         current = current.next;
      }
      if (current == killfront){ // if victim is at the front of the killring
         killfront = current.next;
         AssassinNode killer = killfront; 
         current.next = null;
         while (killer.next != null){
            killer = killer.next;
         }
         current.killer = killer.name;
      } else { 
         prev.next = current.next;
         current.killer = prev.name;
      }                                    
      current.next = graveyard;
      graveyard = current;
   }
}      