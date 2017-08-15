// This class represents the Bear Critter. Bears circle around the map, can be white
// and are represented by a forward or backward slash character
import java.awt.*;

public class Bear extends Critter {
   private boolean polar;
   private int step;
   
   // Takes a boolean to determine whether or not the bear will be a polar bear.
   // Then constructs the bear (or polar Bear).
   public Bear(boolean polar){
      this.polar = polar;
      this.step = 0;
   }
   
   // Accepts a CritterInfo object to access the Bears info
   // Returns the Action of the Bear. Bears will move in a counter clockwise
   // fashion around the map
   public Action getMove(CritterInfo info) {
      step++;
      if (info.getFront() == Neighbor.OTHER){
         return Action.INFECT;
      } else if (info.getFront() == Neighbor.EMPTY){
         return Action.HOP;
      } else {
         return Action.LEFT;
      }
   }

    // Returns the color of the Bear
   public Color getColor() {
      if (polar){
        return Color.WHITE;
      } else {
        return Color.BLACK;
      }
   }

   // Returns the String representation of the Bear.
   public String toString() {
      if (step % 2 == 0){
         return "/";
      } else {
         return "\\";
      }
   }
}