// This class Keeps track of a Husky Critter. The Husky will form a 
// Pack with other huskies at the top right corner. Huskies are magenta,
// and switch between a D, B, and a 4. (Husky Strategy is at Bottom)
import java.awt.*;
import java.util.*;

public class DrewBeforeHusky extends Critter {
   private int step; // Current step
   private boolean atTop; // Is it in the top of the map
   private boolean atRight; // Is it at the right of the map
   private boolean inBox; // Is it in the box formation (in the pack)
   private Random r; // Random to determine if it should hop South
   private boolean needsToHopSouth; // Does the husky need to hop South
   
   // Constructs one Husky object, initializing the random object and 
   // initializing needsToHopSouth
   public DrewBeforeHusky(){
      r = new Random(); 
      this.needsToHopSouth = false;
   }
   
   // Accents a CritterInfo object to access the huskies info
   // Returns the Action of the Husky. Husky will move to the top right
   // Corner and defend itself with fellow huskies.
   public Action getMove(CritterInfo info) {
      step++;
      Direction d = info.getDirection(); // d = the Direction the husky is facing      
      if (info.getFront() == Neighbor.OTHER){
         return Action.INFECT;
      }
      // Shuffle the Huskies in the Front
      if (info.getFront() == Neighbor.EMPTY && step % 100 == 0){
         return Action.HOP;
      }
      // Set atTop to true if husky is at the top, or in the pack
      if (d == Direction.NORTH){
         if (info.getFront() == Neighbor.WALL || info.getFront() == Neighbor.SAME){
            atTop = true;
         }
      }
      // Get up to the pack!
      if (atRight && d == Direction.WEST && info.getRight() == Neighbor.EMPTY){
         atTop = false;
         atRight = false;
         return Action.HOP;
      }
      // turn and enable needsToHopSouth to get to the pack!
      if (atTop && d == Direction.WEST && info.getBack() == Neighbor.EMPTY){
         atRight = false;
         needsToHopSouth = true;
         return Action.LEFT;
      }
      // At the far right of the map
      if (atTop && d == Direction.EAST){
         if (info.getFront() == Neighbor.WALL || info.getFront() == Neighbor.SAME){
            atRight = true;
         }
      }
      if (d == Direction.EAST && info.getFront() == Neighbor.WALL){
         inBox = true;
      } 
      // Make Box
      if (step <= 100){
         if (atTop && atRight && d == Direction.EAST){
            if (info.getFront() == Neighbor.SAME && (info.getLeft() == Neighbor.WALL || info.getLeft() == Neighbor.SAME || info.getLeft() == Neighbor.EMPTY)){
               int shouldMove = r.nextInt(5);
               if (shouldMove == 0 || shouldMove == 1) {
                  needsToHopSouth = true;
                  atRight = false;
               }
            }
         }
      }
      // At top Right corner (in the pack)
      if (atRight && atTop){
         if (d == Direction.EAST){
            return Action.RIGHT;
         } else if (d == Direction.SOUTH){
            return Action.RIGHT;
         } else {
            return Action.LEFT;
         }
      } else {
         if (!atTop){
            if (d == Direction.NORTH && info.getFront() == Neighbor.EMPTY){
               return Action.HOP;
            } else if (d == Direction.EAST){
               return Action.LEFT;
            } else { // d == WEST or SOUTH or North and can't hop
               return Action.RIGHT;
            }
         } else { // atTop == true but atRight == false
            if (!atRight && d == Direction.NORTH) {
               return Action.RIGHT;
            } else if (!atRight && d == Direction.EAST && needsToHopSouth) {
               needsToHopSouth = true;
               return Action.RIGHT;
            } else if (d == Direction.SOUTH && needsToHopSouth){
               needsToHopSouth = false;
               return Action.HOP;
            } else if (!atRight && d == Direction.SOUTH && !needsToHopSouth){
               needsToHopSouth = false;
               return Action.LEFT;
            } else {
               return Action.HOP;
            }
         } 
      }
   }
   
 // Returns the color of a husky: MAGENTA
   public Color getColor() {
      return Color.MAGENTA;
   }

 // Returns the String representation of a husky
   public String toString() {
      if (step % 3 == 0){
         return "D";
      } else  if (step % 3 == 1) {
         return "B";
      } else {
         return "4";
      }
   }
}

/* __________________________________Husky Strategy___________________________________
   
   The pack is stronger than the Wolf. My Strategy is to stay together in a pack.
   Huskies will gather and form a box, or a pack in the top Right corner of the map.
   Huskies will watch eachothers back and will only have two directions they need to be
   careful of. If in the pack Huskies will rotate to look either South or West since
   their back, and above them, is either to another husky or a wall.
   
   After about 100 Steps Huskies begin to wear out from infecting. Every 100 steps the
   Huskies in the front of the pack will move and allow the fresh huskies to do some
   infecting.
   
   Huskies will display as a Magenta D, B, or 4 to represent my name (Drew Before)
*/