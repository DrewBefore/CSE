import java.util.*;

public class test{

   public static void main (String[] args){
      List<String> names = new ArrayList<String>();
      names.add("a");
      names.add("b");
      names.add("c");
      names.add("d");
      AssassinManager manager = new AssassinManager(names);
      manager.printKillRing();
      if (manager.killRingContains("c")) {
         System.out.println("yes");
      
      }
      manager.kill("d");
      System.out.println(manager.graveyard.name);
      manager.kill("c");
      System.out.println(manager.graveyard.name);
      if (manager.gameOver()){
         System.out.println(manager.winner() + " won");
      }
      if (manager.graveyardContains("c")){
         System.out.println("c is in the graveyard");
      }
      manager.printGraveyard();
   }
}