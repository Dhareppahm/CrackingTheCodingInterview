package datastructures.treesgraphs.buildorder.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;

/**
 Problem 7: Build Order
 You are given a list of projects and a list of dependency projects. All the dependency projects should be built before
 the project is. Find the build order that will allow the projects to be built. If there is no valid build order, return error

 Eg:
 Input:
    Projects: a,b,c,d,e,f
    Dependencies: (a,d) (f,b) (b,d) (f,a) (d,c)  - 2nd projects dependent on 1st
    Output: f,e,a,b,d,c

    Such problem is famous and category of TOPOLOGICAL SORT
 ------------------------------------------------------------------------------------------------------------------------------
 Approach 1: TOPOLOGICAL SORT
 1. First add any project nodes with no incoming edge. As they have no dependency they can be built. Add them to result build order
 2. Remove outgoing links from such nodes.
 3. Repeat step 1 and 2
 4. If nodes remain, then return error as there is cycle and projects cannot be build.
 5. Return build order

 */
class Project{      //like Node
    private String name;
    private int dependencies = 0;
    private ArrayList<Project> children = new ArrayList<Project>();     //holds dependencies
    private HashMap<String, Project> mapChildren = new HashMap<>();     //holds children for quick lookup

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

public class BuildOrder {

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
    public Project[] findBuildOrderOfProjects(Graph graph){
        ArrayList<Project> projects = graph.getProjects();

        Project[] buildOrder = new Project[projects.size()];

        //Add roots (non-dependants) projects first to build order
        int index = addNonDependents(buildOrder, projects, 0);     //0 as start inserting from index 0 in array

        int toBeProcessed = 0;
        while(toBeProcessed < buildOrder.length){
            Project current = buildOrder[toBeProcessed];        //get projects from build order

            if(current == null) {         //Shouldn't be null unless there is circular dependency in which case its error
                return null;
            }

            //Remove current project from dependency
            ArrayList<Project> children = current.getChildren();
            for(Project child : children){
                child.decrementDependencies();
            }

            //Add children that have no more dependency on them
            index = addNonDependents(buildOrder, children, index);
            toBeProcessed++;
        }

        return buildOrder;
    }

    //Add the non-dependent nodes to build order
    private int addNonDependents(Project[] buildOrder, ArrayList<Project> projects, int index) {
        for(Project p : projects){
            if(p.getDependencies() == 0){
                buildOrder[index++] = p;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        BuildOrder obj = new BuildOrder();

        /*String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[][] dependencies = {
                {"a", "b"},
                {"b", "c"},
                {"a", "c"},
                {"a", "c"},
                {"d", "e"},
                {"b", "d"},
                {"e", "f"},
                {"a", "f"},
                {"h", "i"},
                {"h", "j"},
                {"i", "j"},
                {"g", "j"}};*/
        String[] projects = {"a", "b", "c", "d", "e", "f"};
        String[][] dependencies = {
                {"a", "d"},
                {"f", "b"},
                {"b", "d"},
                {"f", "a"},
                {"d", "c"}};

        Graph graph = obj.buildGraph(projects, dependencies);
        Project[] buildOrder = obj.findBuildOrderOfProjects(graph);

        if(buildOrder == null){
            System.out.println("Error: Circular dependency found in project. Cannot be built.");
        }else{
            System.out.println("Build order by TOPOLOGICAL SORT is: ");
            for(Project p : buildOrder){
                System.out.print(p.getName()+ " ");
            }
        }
    }

}
