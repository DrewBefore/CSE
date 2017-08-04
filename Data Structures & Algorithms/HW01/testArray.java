public class testArray {

 public static void main(String[] args) {
  Utility.init(); // initializes file readers
  String[] questions = Utility.readQuestions(); //reads question.txt file into questions array
  String[] answers = Utility.readAnswers(); // reads answers.txt file into answers array
  
  int numOracles = answers.length; //finds the number of oracles
  
  //TODO Assign questions to oracles using Utility.random and print the question paired with the oracle response

  // 1. Initialize one ArrayQueue per oracle (arrays will work best).
      ArrayQueue oracle = new ArrayQueue(3);
      oracle.enqueue("1");
      oracle.enqueue("2");
      oracle.enqueue("3");
    
     
      oracle.dequeue();
      oracle.dequeue();
      oracle.enqueue("2 new");
      oracle.enqueue("3 new");
      oracle.enqueue("4 new");
   
   }
}