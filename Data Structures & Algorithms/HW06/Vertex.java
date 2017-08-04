// This class keeps track of the state of a single Vertex
import java.util.Collections;
import java.util.LinkedList;

public class Vertex {
   private String name;
   private Vertex prev;
   public int minDistance = 9999;
   public boolean known;
   public Edge[] adjacencies;
   public LinkedList<Vertex> path;
   
   // Construct a Vertex with a given name
   public Vertex(String name){
      this.name = name;
      this.known = false;
      this.path = new LinkedList<Vertex>();
   }
   
   // Return Vertex name
   public String toString(){
      return name;
   }
}

// This class keeps track of the state of an edge between two vertecies
class Edge {
   public final Vertex source;
   public final Vertex target;
   public final int weight;
   
   // Construct an edge with given source vertex, target vertex, and weight
   public Edge(Vertex source, Vertex target, int weight) {
      this.source = source;
      this.target = target;
      this.weight = weight;
   }
}