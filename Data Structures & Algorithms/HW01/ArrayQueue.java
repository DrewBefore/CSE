// This class keeps track of the status of a circular arrayQueue
public class ArrayQueue {
	private String[] queueArray;
	private int size;
	private int front;
	private int back;
	
   // Constructs an ArrayQueue with no paremeters to initial state
	public ArrayQueue(){
		queueArray = new String[100];
		size = 0;
		front = 0;
		back = -1;
	}
	
   // Constructs an ArrayQueue to specified int in the parameters
	public ArrayQueue(int startSize){
		queueArray = new String[startSize];
		size = 0;
		front = 0;
		back = -1;
	}
   
	/**
	 * @function returns the number of elements in the queue
	 * @return size
	 */
	public int getSize(){
		return size;
	}
   
	/**
	 * @function adds a string to the end of the queue
	 * @param toEnqueue: the input to be inserted
	 */
	public void enqueue(String toEnqueue){
      if(!isFull()){ 
         int next = (back + 1) % queueArray.length;
         queueArray[next] = toEnqueue;
         back = next;
         size++;
	   } else { // Queue is full, print out a message to let the user know
         System.out.println("queueArray is full");
      }
   }
	
	/**
	 * @function removes the string from the front of the queue
	 * @return the string from the front of the queue
	 */
	public String dequeue(){
		String toDequeue = "";
         if(!isEmpty()){
    		   toDequeue = queueArray[front];
            queueArray[front] = null;
            front = (front + 1) % queueArray.length;
            size--;
         }
      return toDequeue;
	}
	
	/**
	 * 
	 * @return returns true if the queue is empty, false otherwise
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * 
	 * @return returns true if the queue is full, false otherwise
	 */
	public boolean isFull(){
		return ((back + 1) % queueArray.length == front) && (size != 0);
	}
	
}
