import java.awt.*;

// This class draws a football field
public class Doodle{

   public static void main (String[] args){
      DrawingPanel panel = new DrawingPanel(300, 300);
      panel.setBackground (Color.GRAY);
      Graphics g = panel.getGraphics();
      drawField(g);
   }
   
   // This method Takes graphics as a parameter
   // to draw the field
   public static void drawField(Graphics g){
      g.setColor(Color.WHITE);
      g.fillRect(10, 0, 200, 120);
      g.setColor(Color.GREEN);
      g.fillRect(10, 10, 200, 100);
      g.setColor(Color.WHITE);
      for (int i = 0; i < 20; i++){
         g.drawLine(10 * i + 10, 10, 10 * i + 10, 110);
      }
      g.setColor(Color.MAGENTA);
      g.fillRect(210, 10, 10, 100);
      g.fillRect(0, 10, 10, 100);
      g.setColor(Color.WHITE);
   }
}