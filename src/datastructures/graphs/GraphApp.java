package datastructures.graphs;

import java.util.LinkedList;
import java.util.Stack;

//This is same as Node in Trees or Link in LinkedList
class Vertex {
	String label;
	boolean visited;
	
	public Vertex(String label){
		this.label = label;
		this.visited = false;
	}
}

class Graph{
	//private static final int MAX_VERTICES = 20;	//default maximum no.of vertices  - required to initialize adj matrix
	private Vertex[] vertices; 		//List of all vertices
	private int[][] adjMatrix;		//adjacency matrix to denote 1 if there is edge, else 0
	private int currentVertices;	//current no. of vertices
	
	//Stack for DFS implementation : Using Java Stack
	Stack<Integer> stack;			//DFS : stack will hold index of vertex in vertices array
	LinkedList<Integer> queue;			//BFS : acts as queue and will hold index in vertex in vertices
	
	//constructor
	public Graph(int no_of_vertices) {
		vertices = new Vertex[no_of_vertices];
		adjMatrix = new int[no_of_vertices][no_of_vertices];
		currentVertices = 0;
		stack = new Stack<Integer>();
		queue = new LinkedList<Integer>();
		
		//initialize the adjMatrix with 0
		for(int i=0; i < no_of_vertices; i++) {
			for(int j=0; j < no_of_vertices; j++) {
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	//Basic Methods
	public void addVertex(String label){
		vertices[currentVertices] = new Vertex(label);
		currentVertices++;	//increment current no of vertices
	}
	
	public void addEdge(int start, int end){
		adjMatrix[start][end] = 1;		//for now doing undirected, so set both a,b and b,a to 1 if edge
		adjMatrix[end][start] = 1;
	}
	
	public void displayVertex(int index){
		System.out.print(vertices[index].label);
	}
	
	//just to see how graph looks are vertices and edges added
	public void printAdjMatrix(){
		for(int i=0; i < adjMatrix.length; i++){
			for(int j=0; j < adjMatrix.length; j++){
				System.out.print(adjMatrix[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	public int getAdjUnvisitedVertex(int v){
		for(int i=0; i < currentVertices; i++){
			//if vertex v row, check for 1 and if corresponsing vertex is not visited, return its index
			if(adjMatrix[v][i] == 1 && vertices[i].visited == false){
				return i;
			}
		}
		return -1;
	}
	
	 //Searching Methods : DFS and BFS
	/**
	 * DFS Rules 
	 * RULE 1 : Visit an adjacent unvisited vertex, mark it, and push it on the stack.
	 * RULE 2 : If you can’t follow Rule 1, then, pop a vertex off the stack.
	 * RULE 3 : If you can’t follow Rule 1 or Rule 2, you’re done.
	 */
	public void DFS(){
		
		//Start at vertex 0				//RULE 1
		vertices[0].visited = true;		//mark vertex
		displayVertex(0);				//display the vertex
		stack.push(0);					//push vertex into stack
		
		while(!stack.isEmpty()){		//while stack is not empty
			
			//get unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex(stack.peek());
			
			if(v == -1){
				stack.pop();	//RULE 2
			}else{
								//do RULE 1
				vertices[v].visited = true;
				displayVertex(v);
				stack.push(v);
			}
		}//end while
		
		//RULE 3 : Stack empty, we are done
		for(int i=0; i < currentVertices; i++){
			vertices[i].visited	= false;		//reset flag
		}
	}//end dfs	
	
	
	
	/**
	 * BFS Rules
	 * RULE 1 : Visit the next unvisited vertex that’s adjacent to the current vertex, mark it and insert it into the queue.
	 * RULE 2 : If you can’t carry out Rule 1 because there are no more unvisited vertices, remove a vertex from the queue and make it the current vertex.
	 * RULE 3 : If you can’t carry out Rule 2 because the queue is empty, you’re done.
	 */
	public void BFS(){
		//start at vertex 0				RULE 1
		vertices[0].visited = true;		//mark it
		displayVertex(0);				//display it
		queue.add(0);					//add it
		int v2;
		
		while(!queue.isEmpty()){	//while queue is not empty
			
			int v1 = queue.remove();
			
			//until it has no unvisited neighbours
			while((v2 = getAdjUnvisitedVertex(v1)) != -1){
				vertices[v2].visited = true;		//mark it
				displayVertex(v2);				//display it
				queue.add(v2);					//add it
			}
			
		}//end main while
		
		//RULE 3 : Queue empty, we are done
		for(int i=0; i < currentVertices; i++){
			vertices[i].visited	= false;		//reset flag
		}
	}
	
}



public class GraphApp {
	
	public static void main(String[] args) {
		int no_of_vertices = 5;
		Graph graph = new Graph(no_of_vertices);
		
		graph.addVertex("A");	//0
		graph.addVertex("B");	//1
		graph.addVertex("C");	//2
		graph.addVertex("D");	//3
		graph.addVertex("E");	//4
		
		graph.addEdge(0, 1);	//A - B
		graph.addEdge(1, 2);	//B - C
		graph.addEdge(0, 3);	//A - D
		graph.addEdge(3, 4);	//D - E
		
		System.out.println("Print adjacency matrix : ");
		graph.printAdjMatrix();
		
		System.out.print("DSF : ");
		graph.DFS();
		
		System.out.println();
		
		System.out.print("BFS : ");
		graph.BFS();


	}

}
