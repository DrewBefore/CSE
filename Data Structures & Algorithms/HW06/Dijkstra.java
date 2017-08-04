// This class Finds the shortest path in the weighted digraph from Node A
// To all other nodes using Dijkstra's algorithm.
public class Dijkstra {
   public static void main (String[] args){    
      Vertex a = new Vertex("A-0");
      Vertex b = new Vertex("B-1");
      Vertex c = new Vertex("C-2");
      Vertex d = new Vertex("D-3");
      Vertex e = new Vertex("E-4");
      Vertex f = new Vertex("F-5");
      Vertex g = new Vertex("G-6");
      Vertex[] allVertices = {a, b, c, d, e, f, g};
              
      a.adjacencies = new Edge[] {
         new Edge(a, b, 5),
         new Edge(a, c, 3)
      };
      b.adjacencies = new Edge[] {
         new Edge(b, g, 1),
         new Edge(b, e, 3),
         new Edge(b, c, 2)
      };
      c.adjacencies = new Edge[] {
         new Edge(c, e, 7),
         new Edge(c, d, 7)
      };
      d.adjacencies = new Edge[] {
         new Edge(d, a, 2),
         new Edge(d, f, 6)
      };
      e.adjacencies = new Edge[] {
         new Edge(e, d, 2),
         new Edge(e, f, 1)
      };
      f.adjacencies = new Edge[] {};
      g.adjacencies = new Edge[] {
         new Edge(g, e, 1),
      };
      
      dijkstra(allVertices, a); 
      System.out.println();
      for (int i = 0; i < 7; i++){
         Vertex v = allVertices[i];
         System.out.println(v.toString() + " Path = " + v.path);
      }
   }

   // This method finds the shortest paths to each vertex from a in the graph
   public static void dijkstra(Vertex[] allVertices, Vertex start){
      boolean allKnown = false;
      start.minDistance = 0;
      while (!allKnown){
         Vertex b = NodeWithSmallestCost(allVertices);
         b.known = true;
         for (Edge e : b.adjacencies){
            int c1 = b.minDistance + e.weight;
            int c2 = e.target.minDistance;
            if (c1 < c2) {
               e.target.minDistance = c1;
               e.target.path.clear();
               e.target.path.add(e.source);
               for (Vertex v : e.source.path){
                  e.target.path.add(v);
               }
            }
         }
         System.out.println(b + " distance = " + b.minDistance);
         allKnown = allKnown(allVertices);
         }
      }
     
     // Return true if every Vertex is known
     public static boolean allKnown(Vertex[] v){
      for (Vertex node : v){
         if (node.known == false){
            return false;
         }
      }
      return true;
   }
   
   // Return the Vertex with the smallest distance from A
   public static Vertex NodeWithSmallestCost(Vertex[] vertices) {
      int smallestCost = 9999;
      Vertex smallestVertex = vertices[0];
      for (int i = 0; i < vertices.length; i++){
         if (vertices[i].minDistance < smallestCost && 
               vertices[i].minDistance != 0 && !vertices[i].known){
               
            smallestVertex = vertices[i];
            smallestCost = vertices[i].minDistance;
         }
      }
      return smallestVertex;      
   }
}