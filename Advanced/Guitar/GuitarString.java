// Andrew Before 1229508
// October 4, 2014
// CSE 143 GuitarString

// This class creates a single GuitarString
import java.util.*;

public class GuitarString {
   private Queue<Double> buffer;
   public static final double DECAY_FACTOR = .996;
   
   // Pre: Creates a ring buffer of the desired capacity n
   //     If the frequency is less than or equal to 0 or the ring buffer size becomes less than 2
   //     throws an IllegalArgumentException
   // Post: Constructs a guitar string of the given frequency
   public GuitarString(double frequency){
      buffer = new LinkedList<Double>();
      int n = (int) Math.round((double) StdAudio.SAMPLE_RATE / frequency); // check
      if (frequency <= 0 || n < 2) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < n; i ++){
         buffer.add(0.0);
      }
   }
   
   // Pre: If int.length < 2, throws an IllegalArgumentException. 
   // Post: Constructs a guitar string and constructs the ring buffer with values in the array.
   public GuitarString(double[] init) {
      if (init.length < 2){
         throw new IllegalArgumentException();
      }
      buffer = new LinkedList<Double>();
      for (double d : init) {
         buffer.add(d);
      }
   }
   
   // Post: This method replaces the elements in the ring buffer with random values [-0.5, .5)
   public void pluck(){
      Random rand = new Random();
      for (int i = 0; i < buffer.size(); i++){
         buffer.remove();
         double r = rand.nextDouble() -.5;
         buffer.add(r);
      }
   }
   
   // Post: Applies the Karplus-Strong update once
   public void tic(){
      buffer.add(DECAY_FACTOR * (.5 * (buffer.remove() + buffer.peek())));
   }
   
   // Post: returns the value at the front of the ring buffer
   public double sample() {
      return buffer.peek();
   }
}