package datastructures.treesgraphs.buildorder.bydfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 Problem 7: Build Order
 You are given a list of projects and a list of dependency projects. All the dependency projects should be built before
 the project is. Find the build order that will allow the projects to be built. If there is no valid build order, return error

 Eg:
 Input:
 Projects: a,b,c,d,e,f
 Dependencies: (a,d) (f,b) (b,d) (f,a) (d,c)  - 2nd projects dependent on 1st
 Output: f,e,a,b,d,c
 --------------------------------------------------------------------------------------------------------------------
 Approach 2: Using DFS
 1.

 */
class Project {      //like Node, same from topological sort but with STATE added to detect cycle in DFS
    private String name;
    private int dependencies = 0;
    private ArrayList<Project> children = new ArrayList<Project>();     //holds dependencies
    private HashMap<String, Project> mapChildren = new HashMap<>();     //holds children for quick lookup
    //Add State
    public enum State {COMPLETE, PARTIAL, BLANK};
    public State state = State.BLANK;                   //initialize to BLANK

    public Project(String name){
        this.name = name;
    }

    public void addDependency(Project node){
        if(!mapChildren.containsKey(node.name)){
            children.add(node);                 //add into list
            mapChildren.put(node.name, node);   //add into map
            node.incrementDependencies();       //increase the dependency on project
        }
    }

    public void incrementDependencies(){ dependencies++; }
    public void decrementDependencies(){ dependencies--; }

    //Getters
    public ArrayList<Project> getChildren() {
        return children;
    }
    public int getDependencies() {
        return dependencies;
    }
    public String getName() {
        return name;
    }
    //Getter - Setter
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}

class Graph{
    private ArrayList<Project> projects = new ArrayList<>();
    private HashMap<String, Project> map = new HashMap<>();

    //create new project node if not present. Return project with the name
    public Project getOrCreateNode(String name){
        if(!map.containsKey(name)){
            Project node = new Project(name);
            projects.add(node);
            map.put(name, node);
        }
        return map.get(name);
    }

    //A -> B (means B is dependent on A)
    public void addEdge(String startName, String endName){
        Project start = getOrCreateNode(startName);
        Project end = getOrCreateNode(endName);
        start.addDependency(end);       //add dependency edge
    }

    public ArrayList<Project> getProjects(){
        return projects;
    }
}

public class BuildOrderDFS {

    /**
     * Build Graph from Input array of Projects and Dependencies
     */
    public Graph buildGraph(String[] projects, String[][] dependencies){
        Graph graph = new Graph();

        //First create the projects
        for(String projectName : projects){
            graph.getOrCreateNode(projectName);
        }

        //Then create dependencies  {A, B} => B is dependent on A
        for(String[] dependency : dependencies){
            String start = dependency[0];
            String end = dependency[1];
            graph.addEdge(start, end);
        }
        return graph;
    }

    /**
     * Find the correct build order
     */
    public Stack<Project> findBuildOrderOfProjects(Graph graph) {
        ArrayList<Project> projects = graph.getProjects();

        Stack<Project> stack = new Stack<>();
        for(Project project : projects){
            if(project.getState().equals(Project.State.BLANK)){     //check if BLANK
                if(!dfsHelper(project, stack)){     //if error occurred, return null
                    return null;
                }
            }
        }

        return stack;               //stack with build order
    }

    private boolean dfsHelper(Project project, Stack<Project> stack) {
        if(project.getState().equals(Project.State.PARTIAL)){       //Cycle
            return false;
        }

        if(project.getState().equals(Project.State.BLANK)){
            project.setState(Project.State.PARTIAL);
            for(Project children : project.getChildren()){
                if(!dfsHelper(children, stack)){     //if error occurred, return null
                    return false;
                }
            }

            project.setState(Project.State.COMPLETE);   //set Complete and Push to stack
            stack.push(project);        //push to top  NOTE: don't use add() method as it adds at end
        }
        return true;
    }


    public static void main(String[] args) {
        BuildOrderDFS boDfs = new BuildOrderDFS();
        String[] projects = {"a", "b", "c", "d", "e", "f"};
        String[][] dependencies = {
                {"a", "d"},
                {"f", "b"},
                {"b", "d"},
                {"f", "a"},
                {"d", "c"}};

        Graph graph = boDfs.buildGraph(projects, dependencies);
        Stack<Project> buildOrderStack = boDfs.findBuildOrderOfProjects(graph);
        if(buildOrderStack == null){
            System.out.println("Error: Circular dependency found in project. Cannot be built.");
        }else{
            System.out.println("Build order by DFS is: ");
            while (!buildOrderStack.isEmpty()){
                System.out.print(buildOrderStack.pop().getName() + " ");
            }
        }
    }


}
