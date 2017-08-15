public class test{
   public static void main(String args[]){
   String s = undouble("mississippi");
   System.out.println(s);
   }
   
   public static String undouble(String s){
      if (s.equals("")){
         return "";
      }
      String result = "";
      
      String prev = "";
      
      for (int i = 0; i < s.length(); i++){
         String curr = s.substring(i, i + 1);
         if (curr.equals(prev)){
         
         } else {
            result += curr;
         }
         prev = curr;
      }
      return result;
      
   }
}