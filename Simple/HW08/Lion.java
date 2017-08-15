// This class represents the Lion Critter. Lions Move between walls, are multicolored
// and are represented by an L 
import java.awt.*;
import java.util.*;

public class Lion extends Critter {
   private int step;
   private Random r;
   private int color;
   
   // Constructs a Lion Critter
   public Lion(){
      r = new Random();
      this.step = 0;
   }
   
   // Accepts a CritterInfo object to access the Lions info
   // Returns the Action of the Lion. Lions will move back and forth
   // between two walls
   public Action getMove(CritterInfo info){
      step++;
      if (info.getFront() == Neighbor.OTHER){
         return Action.INFECT;
      } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL){
         return Action.LEFT;
      } else if (info.getFront() == Neighbor.SAME){
         return Action.RIGHT;
      } else {
         return Action.HOP;
      }
   }
   
   // Returns the color of the lion
   public Color getColor(){
      if (step % 3 == 0){
         color = r.nextInt(3);
      }
      if (color == 0){
         return Color.RED;
      } else if (color == 1 ){
         return Color.BLUE;
      } else {
         return Color.GREEN;
      }
   }
   
   // Returns the toString representation of the Lion
   public String toString(){
      return "L";
   }
}