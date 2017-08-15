// This class keeps track of the state of a single node
public class QuestionNode{
   public String data;
   public QuestionNode left;
   public QuestionNode right;
   
   // Post: constructs a new QuestionNode initializing all fields to null
   public QuestionNode(){
      this(null, null, null);
   }       
   
   // Post: Constructs a new QuestionNode with a given String as the data
   public QuestionNode(String data) {
      this(data, null, null);
   }
              
   // Post: Constructs a new QuestionNode with a given String, and assigns the left and right
   //       fields to the given QuestionNodes
   public QuestionNode(String data, QuestionNode left, QuestionNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
   } 
}
