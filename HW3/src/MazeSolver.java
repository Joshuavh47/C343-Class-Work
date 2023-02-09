import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Feel free to reuse from HW1

public class MazeSolver {
    static char[][] maze;
    static boolean[][] key;
    static int m, n; // dimensions of the maze

    /*
    TODO: setMaze - sets up the board
    This method will take in a String, file, which is the path of the file we want to look at.
    Using BufferedReader and FileReader, write this method so that it sets the rows, m, and columns, n,
    to the first line of input. Then it fills the maze with the maze from the file.
     */
    public static void setMaze(String file) throws IOException {
        try{
            File f=new File(file);
            Scanner s= new Scanner(f);
            String[] dimStr=s.nextLine().split(" ");
            maze=new char[Integer.parseInt(dimStr[0])][Integer.parseInt(dimStr[0])];
            for(int i=0;i<Integer.parseInt(dimStr[0]);i++){
                maze[i]=s.nextLine().toCharArray();
            }
            s.close();
            for(char[] i:maze){
                for(char j:i){
                    System.out.print(j);
                }
                System.out.println();
            }
            key=new boolean[maze.length][maze[0].length];
            for(int i=0;i<key.length;i++){
                for(int j=0;j<key[0].length;j++){
                    key[i][j]=false;
                }
            }

        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    /*
    TODO: isValid - checks if a position on the board has not been visited and is within bounds
     */
    public static boolean isValid(int x, int y) {
        if(x<maze[0].length&&y<maze.length&&x>=0&&y>=0) {
            if (maze[y][x] == '.' || maze[y][x] == 'S' || maze[y][x] == 'G') {
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static boolean isInBounds(int x, int y){
        if(x<maze[0].length&&y<maze.length&&x>=0&&y>=0) {
            return true;
        }
        return false;
    }

    public static int[] findS(){
        int[] coordinates = new int[2];
        for(int i=0;i<maze[0].length;i++){
            for(int j=0;j<maze.length;j++){
                if(maze[j][i]=='S'){
                    coordinates[0]=i;
                    coordinates[1]=j;
                }
            }
        }
        return coordinates;
    }


    /*
    TODO: Using a stack, solve the maze WITHOUT recursion.
    Pseudo:
    1) Push start position onto Stack.
    2) While it's not empty;
        3) Pop from the stack to get the current considered location
        4) If it's already explored ignore
        5) If it's the goal, return true
        6) If it's not the goal, then explore it.
        7) You will need to compute all the possible neighbors. Then push those on the stack
    8) Return false
     */

    public static boolean solveMazeStack(int x, int y)  throws EmptyStackE {
        Stack<Coord> s=new Stack<Coord>();
        s.push(new Coord(findS()[0],findS()[1]));
        Coord cur = s.peek();
        while(!s.isEmpty()){
            cur=s.pop();

            if(isValid(cur.getX(), cur.getY())) {
                if(maze[cur.getY()][cur.getX()]=='G'){
                    return true;
                }
                if(!key[cur.getY()][cur.getX()]){
                    key[cur.getY()][cur.getX()]=true;
                    if(isValid(cur.getX()+1, cur.getY())){
                        s.push(new Coord(cur.getX()+1,cur.getY()));
                    }
                    if(isValid(cur.getX(), cur.getY()+1)){
                        s.push(new Coord(cur.getX(),cur.getY()+1));
                    }
                    if(isValid(cur.getX()-1, cur.getY())){
                        s.push(new Coord(cur.getX()-1,cur.getY()));
                    }
                    if(isValid(cur.getX(), cur.getY()-1)){
                        s.push(new Coord(cur.getX(),cur.getY()-1));
                    }
                }
            }
        }
        return false;
    }

    // TODO: Using a queue, solve the maze. Be sure to explain your algorithm for full points.
    /*
    The queue version of solving the maze uses the exact same logic as the stack version except with the corresponding
    method names changed to match the queue implementation. Unlike the stack which uses a depth-first search, the queue
    implementation uses a breadth-first search. The best way to explain the difference between these is that a
    breadth-first search is like filling the maze up with water, exhausting every possible valid space until the goal
    is found (this is do to the first-in-first-out nature of queues,) and a depth-first search is like a snake trying
    different paths in the maze, and if the snake hits a dead end, it backtracks until there is a space with an
    unvisited neighbor for it to try again. The reason these both can be implemented in relatively the same way is
    because since the queue is first-in-first-out, instead of backtracking it just adds more available valid spaces to
    the queue to check them, and if it happens to come across the goal then it just returns true. Since stacks are
    first-in-last-out, you can use backtracking because all you have to do is keep popping off the stack and pushing
    the neighboring coordinates so that untested neighbors can be visited (until it finds the goal.)
     */
    public static boolean solveMazeQueue(int x, int y) throws EmptyQueueE{
        Queue<Coord> q = new Queue<Coord>();
        q.enqueue(new Coord(findS()[0],findS()[1]));
        Coord cur = q.peek();
        while(!q.isEmpty()){
            cur=q.dequeue();

            if(isValid(cur.getX(), cur.getY())) {
                if(maze[cur.getY()][cur.getX()]=='G'){
                    return true;
                }
                if(!key[cur.getY()][cur.getX()]){
                    key[cur.getY()][cur.getX()]=true;
                    if(isValid(cur.getX()+1, cur.getY())){
                        q.enqueue(new Coord(cur.getX()+1,cur.getY()));
                    }
                    if(isValid(cur.getX(), cur.getY()+1)){
                        q.enqueue(new Coord(cur.getX(),cur.getY()+1));
                    }
                    if(isValid(cur.getX()-1, cur.getY())){
                        q.enqueue(new Coord(cur.getX()-1,cur.getY()));
                    }
                    if(isValid(cur.getX(), cur.getY()-1)){
                        q.enqueue(new Coord(cur.getX(),cur.getY()-1));
                    }
                }
            }
        }
        return false;
    }

    // TODO: Solve the board. Mode 1 = stack solving. Mode 2 = queue solving.
    // 1: stack
    // 2: queue
    public static boolean solve(String file, int mode) throws IOException, EmptyStackE, EmptyQueueE {
        boolean solved = false;
        if(mode==1){
            setMaze(file);
            solved=solveMazeStack(findS()[0],findS()[1]);
        }
        else if(mode==2){
            setMaze(file);
            solved=solveMazeQueue(findS()[0],findS()[1]);
        }

        System.out.println("Maze can be solved: " + solved);
        return solved;
    }


}