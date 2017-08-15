import java.awt.*;

// This class will draw the Cafe Wall illusion and pieces of the
// Illusion onto a drawing panel
public class CafeWall {
   public static final int MORTAR = 2;
   
   public static void main (String args[]){
      DrawingPanel panel = new DrawingPanel (650, 400);
      panel.setBackground (Color.GRAY);
      Graphics g = panel.getGraphics();
      drawLine(0, 0, 4, 20, g);
      drawLine(50, 70, 5, 30, g);
      drawGrid(10, 150, 4, 25, 0, g);
      drawGrid(250, 200, 3, 25, 10, g);
      drawGrid(400, 20, 2, 35, 35, g);
      drawGrid(425, 180, 5, 20, 10, g);
   }
   
   // This method will draw one line of the illusion. accepts ints for x, and y coordinates
   // for how many pairs you want, and the size of the box. Also accepts Graphics object.
   public static void drawLine(int x, int y, int pairs, int boxSize, Graphics g){
      int currentBox = 0; // x coordinate of box you are on, alternates every other
      for (int i = 0; i < pairs; i++){
         currentBox = i * boxSize * 2; 
         g.setColor(Color.BLACK);
         g.fillRect(x + currentBox, y, boxSize, boxSize); // Draw black boxes 
         g.setColor(Color.BLUE);
         g.drawLine(x + currentBox, y, x + currentBox + boxSize, y + boxSize); // Draw the blue
         g.drawLine(x + currentBox + boxSize, y, x + currentBox, y + boxSize); // Crosses
         g.setColor(Color.WHITE);
         g.fillRect(x + currentBox + boxSize, y, boxSize, boxSize); // Draw white boxes
      }
   }
   
   // This method will draw a grid of the lines. It accepts an x, y coordinate,  how many pairs
   // of boxes per line, the box size, how much to offset every other line, and the Graphics
   // object.
   public static void drawGrid(int x, int y, int pairs, int boxSize, int offSet, Graphics g){
      int currentLine = 0; // Current y coordinate of the line of the grid that you are on
      for (int i  = 0; i < pairs; i++){
         currentLine = y + (i * (boxSize * 2 + 2 * MORTAR));
         drawLine(x, currentLine, pairs, boxSize, g);  
         drawLine(x + offSet, currentLine + boxSize + MORTAR, pairs, boxSize, g); 
      }
   }
}