import java.util.*;

// This program manipulates and keeps track of a grammar in BNF form
// Randomly generates random elements of the grammar by
// randomly selecting one set of rules from each non terminal
public class GrammarSolver{
   SortedMap<String, String> grammar;

   // Pre: Throws IllegalArgumentException() if list of strings that is passed is empty
   //      Or if there are two or more entries in the grammar for the same nonterminal
   // Post: Stores the list in a map so the nonterminals are keys and each key is
   //       associated with its corresponding String of rules 
   public GrammarSolver(List<String> grammar){
      if (grammar.isEmpty()){
         throw new IllegalArgumentException();
      }
      this.grammar = new TreeMap<String, String>();
      for(String s: grammar){
         String[] parts = s.split("::=");
         if (grammarContains(parts[0])){
            throw new IllegalArgumentException();
         }
         this.grammar.put(parts[0], parts[1]);
      }
   }
   
   // Returns true if given symbol is nonterminal of grammar, false otherwise
   public boolean grammarContains(String symbol){
      return grammar.containsKey(symbol);     
   }
   
   // Pre: grammar must contain the symbol, and the given times must be >= 0
   // Post: Uses the grammar to randomly generate the given number of occurrences of the symbol
   //       and returns the result as an Array of Strings. 
   public String[] generate(String symbol, int times){
      if (!grammarContains(symbol) || times < 0){
         throw new IllegalArgumentException();
      }
      String[] generation = new String[times];
      for (int i = 0; i < times; i++){
         generation[i] = oneString(symbol).trim();  
      }
      return generation;
   }
   
   // Accepts a String symbol as a parameter and uses it to get the corresponding rules
   // With equal probability chooses 1 random rule, recurses if random rule is nonterminal
   // Returns the String that is generated
   private String oneString(String symbol){
      Random r = new Random();
      String currentString = "";
      String[] parts = grammar.get(symbol).split("[|]");
      int random = r.nextInt(parts.length);
      String randomlyChosen = parts[random].trim();
      String[] keys = randomlyChosen.split("[ \t]+");
      for (String s : keys){
         if (grammarContains(s)){
            currentString += oneString(s);
         } else {
            currentString  += s;
            currentString += " ";
         }   
      }  
      return currentString;
   }
   
   // Returns a String of the nonterminal symbos from the grammar
   // String is in a sorted, comma seperated list, enclosed by brackets  
   public String getSymbols(){
      return "" + grammar.keySet().toString();
   }
}