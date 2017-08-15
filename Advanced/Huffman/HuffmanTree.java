import java.util.*;
import java.io.*;

// This class uses Huffman coding to create new codes for ascii values
// Helps compress files from .txt to .short
public class HuffmanTree {
   private HuffmanNode overallRoot;
   
   // Post: sets overallRoot to the new tree made from the given int[] 
   public HuffmanTree(int[] count){
      Queue<HuffmanNode> charQ = new PriorityQueue<HuffmanNode>();
      for (int i = 0; i < count.length; i++){
         if (count[i] > 0){
            charQ.add(new HuffmanNode(count[i], i));
         }
      } 
      HuffmanNode eofNode = new HuffmanNode(1, count.length);
      charQ.add(eofNode);
      while(charQ.size() > 1){
         HuffmanNode leftNode = charQ.remove();
         HuffmanNode rightNode = charQ.remove();
         int totalFrequency = leftNode.frequency + rightNode.frequency;
         charQ.add(new HuffmanNode(totalFrequency, leftNode, rightNode));
      }  
      overallRoot = charQ.remove();
   }
   
   // Pre: Assumes the given tree is in standard format
   // Post: This constructor reconstructs the tree from a file
   public HuffmanTree(Scanner input){
      overallRoot = new HuffmanNode(-1, -1);
      while (input.hasNextLine()){
         int charValue = Integer.parseInt(input.nextLine());
         String code = input.nextLine();
         HuffmanNode prevRoot = overallRoot;
         for (int i = 0; i < code.length(); i++){
            if (code.charAt(i) == '0'){
               if (prevRoot.left == null){
                  prevRoot.left = setRoot(prevRoot.left, charValue, code, i);
                  prevRoot = prevRoot.left;
               } else {
                  prevRoot = prevRoot.left;
               }
            } else { // code.charAt(i) == '1'
               if (prevRoot.right == null){
                  prevRoot.right = setRoot(prevRoot.right, charValue, code, i);
                  prevRoot = prevRoot.right;
               } else {
                  prevRoot = prevRoot.right;
               }
            }
         }
      prevRoot = overallRoot;
      }
   }
   
   // This method decides if you are creating a blank node, compared to
   // a leaf node with a charValue
   private HuffmanNode setRoot(HuffmanNode leftOrRight, int charValue, String code, int i){
      if (i != code.length() - 1){
         leftOrRight = new HuffmanNode(-1, null, null); // A new node only for tree
      } else {                                          // structure
         leftOrRight = new HuffmanNode(-1, charValue); // Tree structure is already made
      }                                                // -1 frequency is irrelevant
      return leftOrRight;
   }
   
   // Writes the current tree to the given output stream in standard format
   public void write(PrintStream output){
      write(overallRoot, output, "");
   }
   
   // Post: accepts a HuffmanNode, PrintStream, and string to print
   //       the standard format and code to be used in output
   private void write(HuffmanNode root, PrintStream output, String code){
      if (root.left == null && root.right == null){
         output.println(root.charValue);
         output.println(code);
      } else { // root.left || root.right != null
         write(root.left, output, code + "0");
         write(root.right, output, code + "1");
      }
   }   
   
   // Pre: Assumes the input stream is legal encoding of chars
   // Post: Writes the corresponding characters to output from input stream
   public void decode(BitInputStream input, PrintStream output, int eof){
      boolean endOfFile = false;
      while (!endOfFile){   
         HuffmanNode tempRoot = overallRoot;
         while (tempRoot.left != null && tempRoot.right != null){ 
            if (input.readBit() == 0){
               tempRoot = tempRoot.left;
            } else { 
               tempRoot = tempRoot.right;
            }
         }
         if (tempRoot.charValue != eof){
            output.write(tempRoot.charValue);
         } else {
            endOfFile = true;
         }
      }
   }
}