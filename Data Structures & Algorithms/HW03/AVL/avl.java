class avl{
   
   Node root;
   
   // Keeps track of an avl Node with added field for height
   private class Node{
      String keyword;
      Record record;
      int size;
      Node l;
      Node r;
      int height;
      
      // Constructs one Node initializing left and right to null
      private Node(String k){
         this(k, null, null);
      }
      
      // Constructs one Node with keyword, and given left and right nodes
      private Node(String k, Node left, Node right){
         this.keyword = k; l = left; r = right; height = 0;
      }
      
      // Adds the record to the linked list of records
      private void update(Record r){
         if (this.size == 0){
            this.record = r;
         } else {
            r.next = this.record;
            this.record = r;
         }
      }
   }
   
   public avl(){
   }
   
   // Returns the height of a node
   private int height(Node root){
      return root == null ? -1 : root.height;
   }
   
   // Calls private method to insert nodes and records into AVL tree
   // from a file
   public void insert(String keyword, FileData fd){
      Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
      root = insert(keyword, fd, recordToAdd, root);
   }
   
   // Inserts new node at the keyword. Balances tree before returning the root
   private Node insert(String keyword, FileData fd, Record recordToAdd, Node root){
      if (root == null){
         Node newNode = new Node(keyword, null, null);
         newNode.update(recordToAdd);
         return newNode;
      }
      int compareResult = keyword.compareTo(root.keyword);
      if (compareResult < 0){
         root.l = insert(keyword, fd, recordToAdd, root.l);
      } else if (compareResult > 0){
         root.r = insert(keyword, fd, recordToAdd, root.r);
      } else {
         root.record = recordToAdd;
      }
      return balance(root);
   }
   
   // Maximum amount of imbalance found in the tree
   private static final int ALLOWED_IMBALANCE = 1;
   
   // Rearranges tree in order to maintain AVL order of ALLOWED_IMBALANCE
   // returns new balanced root
   private Node balance(Node root){
      if (root == null){
         return root;
      }
      if (height(root.l) - height(root.r) > ALLOWED_IMBALANCE){
         if(height(root.l.l) >= height(root.l.r)){
            root = rotateWithLeftChild(root);
         } else {
            root = doubleWithLeftChild(root);
         }
      } else if (height(root.r) - height(root.l) > ALLOWED_IMBALANCE) {
         if (height(root.r.r) >= height(root.r.l)){
            root = rotateWithRightChild(root);
         } else {
            root = doubleWithRightChild(root);
         }
      }
      root.height = Math.max(height(root.l), height(root.r)) + 1;
      return root;
   }
   
   // rotates node with left child
   private Node rotateWithLeftChild(Node k2){
      Node k1 = k2.l;
      k2.l = k1.r;
      k1.r = k2;
      k2.height = Math.max(height(k2.l), height(k2.r)) + 1;
      k1.height = Math.max(height(k1.l), k2.height) + 1;
      return k1;
   }
   
   // does a double rotation rotating with right, then with left
   private Node doubleWithLeftChild(Node k3){
      k3.l = rotateWithRightChild(k3.l);
      return rotateWithLeftChild(k3);
   }
   
   // rotates node with right child
   private Node rotateWithRightChild(Node k2){
      Node k1 = k2.r;
      k2.r = k1.l;
      k1.l = k2;
      k2.height = Math.max(height(k2.r), height(k2.l)) + 1;
      k1.height = Math.max(height(k1.r), k2.height) + 1;
      return k1;
   }
   
   // double rotation rotating with left, then with right
   private Node doubleWithRightChild(Node k3){
      k3.r = rotateWithLeftChild(k3.r);
      return rotateWithRightChild(k3);
   }
   
   // calls private contains to find if keyword is in tree
   public boolean contains(String keyword){
      return contains(keyword, root);
   }
   
   // returns true if keyword is found in tree
   private boolean contains(String keyword, Node root){
      if (root != null){
         if (root.keyword == keyword){
            return true;
         } else {
            contains(keyword, root.l);
            contains(keyword, root.r);
         }
      }
      return false;
   }
   
   // Calls private method to return record associated with keyword
   public Record get_records(String keyword){
      return get_records(keyword, root);
   }
   
   // returns the record associated with the given keyword
   private Record get_records(String keyword, Node root){
      if (root != null){
         int compareResult = keyword.compareTo(root.keyword);
         if (compareResult < 0){
            return get_records(keyword, root.l);
         } else if (compareResult > 0){
            return get_records(keyword, root.r);
         } else {
            return root.record;
         }
      }
      return null;
   }
   
   // calls private method to delete node with given keyword
   public void delete(String keyword){
      delete(keyword, root);
   }
   
   // deletes the node with given keyword, and returns new root
   // after deletion
   private Node delete(String keyword, Node root){
      if (root == null){
         return root;
      }
      int compareResult = keyword.compareTo(root.keyword);
      if (compareResult < 0){
         root.l = delete(keyword, root.l);
      } else if (compareResult > 0){
         root.r = delete(keyword, root.r);
      } else if (root.l != null && root.r != null){
         root.keyword = findMin(root.r).keyword;
         root.record = findMin(root.r).record;
         root.r = delete(root.keyword, root.r);
      } else {
         root = (root.l != null) ? root.l : root.r;
      }
      return root;
   }
   
   // returns the smallest node found after root
   private Node findMin(Node root){
      if (root == null){
         return null;
      } else if (root.l == null){
         return root;
      }
      return findMin(root.l);
   }
   
   // prints current AVL tree
   public void print(){
      print(root);
   }
   // prints current AVL tree
   private void print(Node t){
      if (t!=null){
         print(t.l);
         System.out.println(t.keyword);
         Record r = t.record;
         while(r != null){
            System.out.printf("\t%s\n",r.title);
            r = r.next;
         }
         print(t.r);
      }
   }
   
   
}
