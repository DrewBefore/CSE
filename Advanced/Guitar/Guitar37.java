//This class creates a Guitar with KEYBOARD.length() strings
public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  
   private GuitarString[] Guitar;
   private int time;
   
   // Post: Constructs a guitar with KEYBOARD.length() strings
   //       Assigns each strings corresponding frequency
   public Guitar37() {  
      Guitar = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++){
         Guitar[i] = new GuitarString(440 * Math.pow(2,(i - 24) / 12.0));
      }           
   }
   
   // Pre: takes in a pitch to play, if the guitar cannot play the note it is ignored
   // Post: Calls pluck on the index relative to the given pitch  
   public void playNote(int pitch){
      if (pitch + 12 >= 0 && pitch + 12 <= KEYBOARD.length()){
         Guitar[pitch + 12].pluck();     
      }
   }
   
   // Post: Accepts a key as a prameter and checks to see if the key exists in this guitar
   //       Returns false if key is not found
   public boolean hasString(char key){
      for (int i = 0; i < KEYBOARD.length(); i ++){
         return KEYBOARD.indexOf(key) != -1;     
      }
      return false;
   }
   
   // Pre: Passes in a key as a parameter If guitar does not have key throws IllegalArgumentException
   // Post: calls pluck on the index of the key
   public void pluck(char key){
      if (hasString(key) == false){
         throw new IllegalArgumentException();
      }
      Guitar[KEYBOARD.indexOf(key)].pluck();
   }
   
   // Post: returns the sum of all current GuitarString samples
   public double sample(){
      double sum = 0;
      for (int i = 0; i < KEYBOARD.length(); i++){
         sum += Guitar[i].sample();
      }
      return sum;
   }
   
   // Post: Calls tic() for each GuitarString, then increases time by 1
   public void tic(){
      for (int i = 0; i < KEYBOARD.length(); i++){
         Guitar[i].tic();
      }
      time += 1;
   }
   
   // Post: Returns current time (the number of times tic has been called)
   public int time(){
      return time;
   }
}