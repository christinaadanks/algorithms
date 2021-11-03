import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Program to execute Bellman-Ford algorithm.
 *
 * @author Sam Benefield
 * @author Christina Liu
 *
 * @version 6/15/2021
 */

public class Graph {

   //////////////////////////////
   ///// INSTANCE VARIABLES /////
   //////////////////////////////

   private int V;
   private int E;
   private Edge edge[];
   private Parent par[];

   ///////////////////////
   ///// CONSTRUCTOR /////
   ///////////////////////  

   public Graph(int vertexIn, int edgeIn) {
   
      V = vertexIn;
      E = edgeIn;
      edge = new Edge[edgeIn];
   
      for (int i = 0; i < edgeIn; i++) {
         edge[i] = new Edge();
      }
      
      // hold parent values for each vertex
      par = new Parent[V];
      for (int j = 0; j < V; j++)  {
         par[j] = new Parent();
      }      
   }
   
   
   /////////////////////////////
   ////////// METHODS //////////
   /////////////////////////////
   
   public void executeBellmanFord(Graph graphIn, int sourceIn) {
   
      int V = graphIn.V;
      int E = graphIn.E;
      int graphSource = sourceIn;
      int distance[] = new int[V];
      int parent[] = new int[V];
   
   /* Initializes the distance value for all vertices to MAX_VALUE
      Sets the distance value for the source to 0 */
      for (int i = 0; i < V; i++) {
         distance[i] = Integer.MAX_VALUE;
      }
      distance[graphSource] = 0;
   
   /* Relaxing the edges */
      for (int i = 0; i < V - 1; i++) {
      
         for (int j = 0; j < E; j++) {
         
            int u = edge[j].source;
            int v = edge[j].destination;
            int w = edge[j].weight;
            
            if (distance[u] != Integer.MAX_VALUE && distance[v] > distance[u] + w) { 
               distance[v] = distance[u] + w;
               parent[v] = u;
            }
         }
      }
   
   /* Negative weight cycle check */
      for (int i = 0; i < E; i++) {
      
         int u = edge[i].source;
         int v = edge[i].destination;
         int w = edge[i].weight;
      
         if (distance[u] != Integer.MAX_VALUE && distance[v] > distance[u] + w) {
            System.out.println("Graph cannot have a negative weight cycle...ending program");
            System.exit(0);
         }
      }
      
      
      for (int k = 0; k < V; k++)  {
         if (k == graphSource) {
            par[k].parent = Integer.MAX_VALUE;
         }
         
         else  {
            par[k].parent = parent[k];
         }    
      }
   }
   

   ///////////////////////////
   ///// PRIVATE CLASSES /////
   ///////////////////////////

   private class Edge {
   
      private int source;
      private int destination;
      private int weight;
   
      private Edge() {
         source = 0;
         destination = 0;
         weight = 0;
      }
   }
   
   private class Parent {
      private int parent;
      
      private Parent()  {
         parent = 0;
      }
      
   }
   
   ////////////////////////////
   ////////// DRIVER //////////
   ////////////////////////////
   
   public static void main(String[] args) throws FileNotFoundException, IOException {
   
      int first = 0; //first vertex
      int destination = 0; //destination vertex
      int source = 0; //source vertex
      int weight = 0; //edge weight
      
      int vertexCount = 0; //number of vertices in graph
      int edgeCount = 0; //number of edges in graph
      
      /* scanner */
      String fileName = "";
      Scanner keyboardInput = new Scanner(System.in);
      
      System.out.print("Enter a file name: ");
      fileName = keyboardInput.nextLine();
      
      Scanner scan = new Scanner(new File(fileName));
      Scanner scan2 = new Scanner(new File(fileName));
      Scanner scan3 = new Scanner(new File(fileName));
      
      /* vertex count */
      while (scan.hasNextLine()) {
         String scanner = scan.nextLine();
         vertexCount++; //get vertex count
      }
      
      vertexCount--; //remove first vertex from count
      
      scan.close(); //close scan
      
      /* edge count */
      while (scan2.hasNextLine())   {
         String scanner2 = scan2.nextLine();
         String[] lineArr = scanner2.split(" ");
         
         for (int i = 0; i < lineArr.length; i++)  {
            if (lineArr[i].contains(","))  {
               edgeCount++; //get edge count
            }
         }
      }
      
      scan2.close(); //close scan2
      
      Graph graph = new Graph(vertexCount, edgeCount); //create objects
      
      /* retrieve the first node*/
      String fLine = scan3.nextLine();
      first = Integer.parseInt(fLine); //first node 
      
      int j = 0; //counter for edge array
      
      /* retrieve source, destination, and weight values in edge array */
      while (scan3.hasNextLine()) {
         String line = scan3.nextLine();
         String[] lineArr = line.split(" ");
                  
         for (int i = 0; i < lineArr.length; i++)  {
            
            /* destination & weight values */
            if (lineArr[i].contains(","))  {
               String[] att = lineArr[i].split(","); //split vertex & weight
               
               String d = att[0]; //get destination value
               destination = Integer.parseInt(d); //convert
               graph.edge[j].destination = destination; //insert destination value
               
               String w = att[1]; //get weight value
               weight = Integer.parseInt(w); //convert
               graph.edge[j].weight = weight; //insert weight value
               
               graph.edge[j].source = source; //insert source value
               j++;
            }
            
            /* source value */
            else  {
               String s = lineArr[i]; //get source value
               source = Integer.parseInt(s); //convert
            }
         }
      }
      
      scan3.close(); //close scan3
      
      /* write to file */
      FileWriter bellman = new FileWriter("outputShortestPaths.txt");
      
      /* execute algorithm */
      graph.executeBellmanFord(graph, first);
      
      int p; //parent value
      
      for (int i = 0; i < graph.V; i++)  {
         int pCount = 0; //count how many vertices are in path
         
         if (i != first) { //if vertex isn't graph source
            p = graph.par[i].parent; //parent of first vertex
            pCount++; 
            
            while (graph.par[p].parent != Integer.MAX_VALUE) { //as long as vertex isn't source
               p = graph.par[p].parent; //parent of current vertex
                  pCount++;
            }
            pCount++; //account for graph source
            
            int[] arr = new int[pCount]; //array to hold parent values
            
            int k = i; //current destination vertex
            
            if (k != first) { //if vertex isn't graph source
               int v = 0; //index of new array
               
               arr[v] = k; //insert destination index
               v++;
               
               p = graph.par[k].parent; //get parent of destination index
               arr[v] = p; //insert parent of destination index
               v++;
                  
               while (graph.par[p].parent != Integer.MAX_VALUE)  { //as long as vertex isn't source
                  p = graph.par[p].parent; //get parent of current vertex
                  arr[v] = p; //insert parent of current index
                  v++;
               }
               
               int[] mainArr = new int[arr.length]; //array to hold reversed parent values
               
               int m = 0;
               
               for (int n = arr.length - 1; n >= 0; n--)   { //get reversed array
                  mainArr[n] = arr[m];
                  m++;
               } 
               
               /* write results to file */
               bellman.write(i + ": ");
               for (int b = 0; b < mainArr.length; b++)  {
                  bellman.write(mainArr[b] + " ");
               }
               bellman.write("\n");                             
            }
         }
      }
      bellman.close(); //close file
   }
}
