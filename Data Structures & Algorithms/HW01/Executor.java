// This class prints out a question and answer pair for each question that an oracle has
public class Executor {
   
	public static void main(String[] args) {
		Utility.init(); // initializes file readers
		String[] questions = Utility.readQuestions(); //reads question.txt file into questions array
		String[] answers = Utility.readAnswers(); // reads answers.txt file into answers array
		int numOracles = answers.length; //finds the number of oracles
     
		// 1. Initialize one ArrayQueue per oracle (arrays will work best).
         ArrayQueue[] oracles = new ArrayQueue[numOracles];
         for(int i = 0; i <numOracles; i++){
            oracles[i] = new ArrayQueue(questions.length);
         }
      
		// 2. Put the questions into the queues, assigning each one to the queue of the oracle whose number is returned by the random number generator.
      for(String s : questions){
         int oracle = Utility.random(numOracles);
		   oracles[oracle].enqueue(s);
      }
      
      // 3. Loop through the oracles, having each one remove a question from its queue (if empty do nothing) and answer it with its unique answer (oracle[k] uses answers[k]). Do this repeatedly till all queues become empty.
      int questionsLeft = questions.length;
      while(questionsLeft > 0){ // while there are questions left, loop through each oracle
         int currOracle = 0;
         for(ArrayQueue q : oracles){ // loop through each oracle
            if(!q.isEmpty()){
               System.out.print(q.dequeue() + ": "); // Print the question
               System.out.println(answers[currOracle]); // Print the answer
               questionsLeft--;
            }
         currOracle++; // To keep track of which oracle is being asked the question
         }
      }
   }
}


