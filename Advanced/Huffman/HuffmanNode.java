// This class keeps track of the nodes in a HuffmanTree
// Each node contains a frequency, and the int value of a character
public class HuffmanNode implements Comparable<HuffmanNode> {
   public int frequency;		 
   public int charValue;
   public HuffmanNode left;
   public HuffmanNode right;
   
   // post: Constructs a HuffmanNode with only a given frequency and charValue		
   public HuffmanNode(int frequency, int charValue){
      this(frequency, charValue, null, null);
   }	
   
   // post: Constructs a HuffmanNode with a given frequency, left node, and right node
   public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right){
      this(frequency, -1, left, right);
   }  
   
   // post: constructs a HuffmanNode with the given frequency, charvalue,
   //       and left and right nodes
   public HuffmanNode(int frequency, int charValue, HuffmanNode left, HuffmanNode right) {
      this.frequency = frequency;
      this.charValue = charValue;
      this.left = left;
      this.right = right;
   }
   
   // post: comppares the two frequencies of HuffmanNodes and returns the result
   public int compareTo(HuffmanNode other){
      return this.frequency - other.frequency;
     
   }
}