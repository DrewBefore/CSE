// This class creates an bst tree with keywords and records
class bst{
   
   Node root;

   // Keeps track of a bst Node    
   private class Node{
      String keyword;
      Record record;
      int size;
      Node l;
      Node r;

      // Constructs one Node with given keyword      
      private Node(String k){
         this.keyword = k;
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
   
   public bst(){
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
         Node newNode = new Node(keyword);
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
      return root;
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
