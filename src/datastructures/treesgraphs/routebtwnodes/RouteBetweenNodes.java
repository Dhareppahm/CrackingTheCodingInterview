package datastructures.treesgraphs.routebtwnodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 Problem 1: Route between nodes
         Given a directed graph, design an algorithm to find out if there is a route between nodes
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Regular BFS_LevelLists search through graph
    2. For BFS_LevelLists use a Queue. Mark start visited and add to queue
    3. While Queue is not empty
        Pop from queue, and loop through adjacent nodes
        If end found: return true
        Else, mark adjacent node visited and add adjacent node to queue
    4. If end not found, return false
 */

class Node{
    String data;
    List<Node> adjacent;        //Hold adjacent nodes to a node
    boolean visited;

    public Node(String data){
        this.data = data;
        adjacent = new ArrayList<>();
        this.visited = false;
    }

    public void addAdjacent(Node x){
        this.adjacent.add(x);
    }
}

class Graph{
    List<Node> nodes;           //All nodes in graph

    public Graph(){
        nodes = new ArrayList<>();
    }

    public void addNode(Node n){
        this.nodes.add(n);
    }

    public Node getNode(String data){
        for(Node n : nodes){
            if(n.data == data)
                return n;
        }
        return null;
    }

    public boolean searchRoute_BFS(Graph graph, Node start, Node end) {

        LinkedList<Node> queue = new LinkedList<>();        //acts like Queue

        start.visited = true;                       //mark start as visited and add into queue
        queue.add(start);

        while(!queue.isEmpty()){
            Node n = queue.removeFirst();           //Dequeue. Remove first node from queue

            System.out.print(n.data + " -> ");      //visit node
            for(Node adj : n.adjacent){             //loop through adjacent nodes of n

                if(adj.visited == false){           //proceed only if Node not visited
                    if(adj.data == end.data){       //if end found, break and return true
                        return true;
                    }else {                         //else add to queue and keep on searching
                        adj.visited = true;
                        queue.add(adj);
                    }
                }
            }
        }

        return false;
    }

}

public class RouteBetweenNodes {

    public static void main(String[] args) {
        Graph graph = createNewGraph();

        Node start = graph.getNode(/*"c"*/ "a");
        Node end = graph.getNode("f");

        boolean isRoutePresent = graph.searchRoute_BFS(graph, start, end);
        System.out.println("Route present : "+ isRoutePresent);
    }

    private static Graph createNewGraph() {
        Graph graph = new Graph();
        /**
         Graph below:
         a -> b
         | \
         c  d -> e -> f
         */
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");

        a.addAdjacent(b);   a.addAdjacent(c);   a.addAdjacent(d);
        d.addAdjacent(e);
        e.addAdjacent(f);

        graph.addNode(a);graph.addNode(b);graph.addNode(c);graph.addNode(d);graph.addNode(e);graph.addNode(f);
        return graph;
    }
}
