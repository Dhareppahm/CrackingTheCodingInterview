package concepts_algorithms.recursion_dp;

import java.util.*;

/**
 Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 the bottom right
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
 1. Let r - rows and c - cols, start from end of grid and exit when reach (0,0)
 2. From end, we can go (r, c-1) and (r-1,c).
 3. From (r,c-1) we can go (r-1,c-1) and (r,c-2) & from (r-1,c) we can go (r-1,c-1) and (r-2,c)
 4. Use memoization to reduce duplicate work of (r-1,c-1)

 Without memoization - Complexity is O(2^r+c) as 2 ways at each cell
 With memoization - Complexity O(rc)

 Eg: T - can go to, F - off limits
 maze = {
            {T, F, T, T}
            {T, T, T, T}
            {T, F, F, T}
            {T, T, F, T}
            {T, T, T, T}
        }
 */
public class RobotInGrid {

    private static boolean[][] maze = {
                                {true, false,true, true},
                                {true, false, true, true},
                                {true, false,false,true},
                                {true, false, true,true},
                                {true, true, true, true}
                            };

    public static void main(String[] args) {

        RobotInGrid rig = new RobotInGrid();
        int rows = maze.length;
        int cols = maze[0].length;

        List<Point> path = new ArrayList<>();       //store path traversed
        Set<Point> visitedPoints = new HashSet<>();  //memoization - cache results

        boolean isReachable = rig.findPath(rows-1, cols-1, path, visitedPoints);
        System.out.println(isReachable);
        System.out.println(Arrays.toString(path.toArray()));
    }


    public boolean findPath(int r, int c, List<Point> path, Set<Point> visitedPoints){

        System.out.println("r="+r+" c="+c);

        if(r < 0 || c < 0)  return false;

        if(!maze[r][c]) return false;       //F if off limits cell

        Point p = new Point(r,c);
        if(visitedPoints.contains(p)) {      //already visited, then return
            return false;
        }

        boolean isOrigin = (r == 0 && c == 0);
        if(isOrigin || findPath(r-1,c, path,visitedPoints) || findPath(r,c-1, path,visitedPoints)){
            path.add(p);
            return true;       //return true as reached origin or till reach origin
        }

        return false;           //else return
    }

}

//Temp class to store the point coordinates
class Point{
    int r;
    int c;
    public Point(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Point{ r=" + r +" c=" + c +" }";
    }
}
