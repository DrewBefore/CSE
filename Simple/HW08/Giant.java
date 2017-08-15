import java.awt.*;

public class Giant extends Critter {
   private int step;
   
   // Constructs one Giant Critter
   public Giant(){
      this.step = 0;
   }
   
   // Accepts a CritterInfo object to access the Giants info
   // Returns the Action of the Giant. Giants will move in a clockwise
   // fashion around the map
   public Action getMove(CritterInfo info){
      step++;
      if (info.getFront() == Neighbor.OTHER){
         return Action.INFECT;
      } else if(info.getFront() == Neighbor.EMPTY){
         return Action.HOP;
      } else {
         return Action.RIGHT;
      }
   }
   
   // Returns the color of the Giant
   public Color getColor(){
      return Color.GRAY;
   }
   
   // Returns the toString representation of the Giant
   public String toString(){
      int name = step % 24;
      if (name < 6){ 
         return "fee";
      } else if (name > 5 && name < 12){ 
         return "fie";
      } else if(name > 11 && name < 18){ 
         return "foe";
      } else {
         return "fum";
      }
      
   }
}