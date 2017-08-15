import java.util.*;

// This class manages a game of hangman
// Keeps track of the current words, guesses, remaining guesses, and current pattern of guesses
public class HangmanManager{
   private Set<String> dictionary;  
   private int guessesLeft;
   private String pattern;
   private SortedSet<Character> guessed;

   // Pre: Throws an IllegalArgumentException if length is less than 1 or if max is less than 0
   // Post: Accepts the dictionary, length of the word, and the max number of wrong guesses allowed
   //       sets this.dictionary to contain all words from parameter ignoring duplicates
   public HangmanManager(Collection<String> dictionary, int length, int max){
      if (length < 1 || max < 0){
         throw new IllegalArgumentException();
      }
      guessesLeft = max;
      this.dictionary = new TreeSet<String>();
      guessed = new TreeSet<Character>();  
      for (String word : dictionary){
         if (word.length() == length){
            this.dictionary.add(word); 
         }
      }
      pattern = "";
      for (int i = 0; i < length; i++){
         pattern += "-";
      } 
   }
   
   // Returns the current set of words being considered 
   public Set<String> words(){
      return dictionary;
   }
   
   // Returns how many guesses the player has left
   public int guessesLeft(){
      return guessesLeft;
   }
   
   // Returns the current SortedSet of letters that have been guessed (alphebetical order)
   public SortedSet<Character> guesses(){
            for (int i = 0; i < pattern.length(); i++){
         if (pattern.charAt(i) != '-'){
            guessed.add(pattern.charAt(i));
         }
      }
      return guessed;  
   }
   
   // Pre: Throws IllegalStateException if the set of words is empty
   // Post: Reformats the pattern to contain space between chars
   //       Returns the new spacedPattern
   public String pattern(){
      if (dictionary.isEmpty()){
         throw new IllegalStateException();
      }
      String spacedPattern = "" + pattern.charAt(0);
      for (int i = 1; i < pattern.length(); i++){
         spacedPattern += " " + pattern.charAt(i);
      }
      return spacedPattern;
      
   }
   
   // Pre: Throws an IllegalStateException if guesses left is < 1 or if dictionary is empty
   //      Throws an IllegalArgumentException if dictionary isnt empty and guesses() contains guess
   // Post: Records the guess made by taking guess in as a parameter
   //       Chooses the largest set of words to go forward
   //       Returns the amount of occurrences of guess
   public int record(char guess){
      if (guessesLeft() < 1 || dictionary.isEmpty()){
         throw new IllegalStateException();
      }
      if (!dictionary.isEmpty() && guesses().contains(guess)){
         throw new IllegalArgumentException();
      }
      // using guess decide which set of words to use going forward
      guessed.add(guess);
      Map<String, TreeSet<String>> words = new TreeMap<String, TreeSet<String>>();
      String guesses = stringToKey(guess, words);
      pattern = findBiggestKey(words);
      // return the number of occurrences of the guessed letter in the new pattern
      int occurrences = 0;
      for (int i= 0; i < pattern.length(); i++){
         if (pattern.charAt(i) == guess){
            occurrences++;
         }
      }
      if (occurrences == 0){
         guessesLeft -= 1;
      }
      removeFromDictionary(guess); // Remove words that contained guess from dictionary
      return occurrences;
   }
   
   // Accepts a Map<String, TreesSet<String>> as a parameter
   // Compares each set in the map, and returns the largest set
   private String findBiggestKey(Map<String, TreeSet<String>> words){
      TreeSet<String> biggest = null;
      String biggestKey = "";
      for (String s : words.keySet()){
         if (biggest == null){
            biggest = words.get(s);
            biggestKey = s;
         }
         if (words.get(s).size() > biggest.size()){
            biggest = words.get(s);
            biggestKey = s;
         }
      }
      return biggestKey; 
   }
   
   // Accepts the guess and a Map<String, TreeSet<String>> as parameters
   // returns a string that represents correct and incorrect guesses
   private String stringToKey(char guess, Map<String, TreeSet<String>> words){
      String guesses = "";
      for (String word : dictionary){                 
         for (int i = 0; i < word.length(); i++){ 
            if (word.charAt(i) == pattern.charAt(i)){             
               guesses += pattern.charAt(i);
            } else if (word.charAt(i) == guess) {
               guesses += guess;   
            } else {
               guesses += "-";
            }
         }
         if (!words.containsKey(guesses)){
            TreeSet<String> set  = new TreeSet<String>();
            set.add(word);
            words.put(guesses, set);
         } else {
            words.get(guesses).add(word);
         }
         guesses = "";         
      }
      return guesses;
   }
   
   // Accepts the guess as a parameter and removes all words from dictionary which
   // do not equal the current pattern
   private void removeFromDictionary(char guess){
      Iterator<String> iterator = dictionary.iterator();
      while (iterator.hasNext()){
         String word = iterator.next();
         String wordCompare = "";
         for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == pattern.charAt(i)){
               wordCompare += pattern.charAt(i);
            } else if (word.charAt(i) == guess) {
               wordCompare += guess;   
            } else {
               wordCompare += "-";
            }
         }
         if (!wordCompare.equals(pattern)){
            iterator.remove();
         }
      }
   }
}

