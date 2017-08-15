import java.util.*;

// This class uses a dictionary to find all combinations of words that 
// have the same letters as a given phrase
public class AnagramSolver {
   Map<String, LetterInventory> map;
   List<String> dictionary;
   
   // Pre: The dictionary must be a nonempty collection of nonempty sequences of letters
   //      and must contain no duplicates
   // Post: Constructs an anagram solver that uses the list as its dictionary
   public AnagramSolver(List<String> list){
      dictionary = list;
      map = new TreeMap<String, LetterInventory>();
      for (String s : list){
         map.put(s, new LetterInventory(s));
      }
   }
   
   // Pre: throws IllegalArgumentException if max is less than 0
   // Post: Sets up recursive private method by converting string to LetterInventory
   public void print(String s, int max){
      if (max < 0){
         throw new IllegalArgumentException();
      }
      LetterInventory scrambleInventory = new LetterInventory(s);
      //prune(scrambleInventory);
      Queue<String> q = new LinkedList<String>();
      print(prune(scrambleInventory), scrambleInventory, q, max);
   }
   
   // Prints to System.out all combinations of words from the dictionary that are anagrams of scramble
   // Anagrams must contain at most max words or unlimited words if max is 0
   private void print(List<String> dict, LetterInventory scramble, Queue<String> q, int max){
      LetterInventory temp = scramble;
      if (scramble.isEmpty() && max == 0){
         System.out.println(q);
      } else if (scramble.isEmpty() && q.size() <= max){
         System.out.println(q);
      } else {
         for (String word : dict){
            if(scramble.subtract(map.get(word)) != null){
               q.add(word);
               scramble = scramble.subtract(map.get(word));
               print(dict, scramble, q, max);
               scramble = temp;     
               q.remove(word);
            } 
         }
      }
   }
   
   // Accepts a LetterInventory and Returns a new shortened dictionary that 
   // contains words that fit into the string being scrambled 
   private List<String> prune(LetterInventory scrambleInventory){   
      List<String> dictionary2 = new ArrayList<String>();
      for(String word : map.keySet()){
         if(scrambleInventory.subtract(map.get(word)) != null){
            dictionary2.add(word);   
         }
      } 
      return dictionary2;
   }
}