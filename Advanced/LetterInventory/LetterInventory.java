// This class creates an array of the alphabet and stores occurences of each letter
public class LetterInventory {
   private static final int ALPHABET = 26;
   private int[] elementData;
   private int size;
   
   // creates an inventory of the alphabetic letters in the given string
   // is not case sensitive, ignores non-alphabetic characters
   public LetterInventory(String data){
      elementData = new int[ALPHABET];
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++){
         if (data.charAt(i) >= 'a' && data.charAt(i) <= 'z'){
            elementData[data.charAt(i) - 'a'] ++;
            size++;
         }
      }   
   }
   
   // pre: if letter is non-alphabetic throws IllegalArgumentException
   // post: returns a count of specific letter in the inventory
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if (letter > 'z'|| letter < 'a'){
         throw new IllegalArgumentException("letter " + letter);
      }
      return elementData[letter - 'a'];
   }
   
   // pre: if letter is non-alphabetic or value is negative, throws IllegalArgumentException
   // post: sets the count for given letter to given value and updates size
   public void set(char letter, int value) {
      letter = Character.toLowerCase(letter);
      if (letter > 'z'|| letter < 'a' || value < 0){
         throw new IllegalArgumentException("letter: " + letter + ", value" + value); //question
      }
      size += value - elementData[letter - 'a'];
      elementData[letter - 'a'] = value;   
   }
   
   // returns the sum of all counts in this inventory
   public int size() {
      return size;
   }
   
   // returns true if inventory is 0
   public boolean isEmpty() {
      return size == 0;
   }
   
   // returns a sorted String of the inventory with letters in lowercase 
   public String toString() {
      String result = "["; 
      for (int i = 0; i < ALPHABET; i++){
         if (elementData[i] != 0) {
            for (int j = 0; j < elementData[i]; j++){
               result += (char) ('a' + i);
            }
         }
      }   
      result += "]";
      return result;     
   }
 
   // constructs and returns a new LetterInventory with the result of adding this LetterInventory to Other
   // does not change original LetterInventories
   public LetterInventory add(LetterInventory other) {
      LetterInventory sum = new LetterInventory("");
      for (char i = 'a'; i < 'z'; i++){
         sum.set(i, elementData[i - 'a'] + other.elementData[i - 'a']);
      }
      return sum;
   }
   
   // pre: returns null if this's index - others index < 0
   // post: constructs and returns a new LetterInventory that stores the 
   //       result of subracting other LetterInventory from this LetterInventory
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory subtract = new LetterInventory("");
      for (char i = 'a'; i < 'z'; i++){
         if (this.get(i) - other.get(i) < 0) {        
            return null;
         }
         subtract.set(i, this.get(i) - other.get(i));
      }
      return subtract;   
   }
}










