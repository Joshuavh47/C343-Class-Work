import java.io.*;
import java.util.*;

public class MazeSolver {
    static char[][] maze;
    static boolean[][] key;
    /*
    TODO: setMaze - sets up the board
    This method will take in a String, file, which is the path of the file we want to look at.
    Using BufferedReader and FileReader, write this method so that it sets the rows, m, and columns, n,
    to the first line of input. Then it fills the maze with the maze from the file.
     */
    public static void setMaze(String file) throws FileNotFoundException, IOException {
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

    /*
    TODO: solveMaze - recursive function which will traverse the maze and find whether it's solveable S -> G
    The input is the index that we want to check: x = row, y = column
    ------ Hint -------
    Cell(i,j) -> if it’s a wall return false
    Cell(i,j) is ‘G’ return true
    Otherwise, mark cell(i,j) as visited (maybe make it a wall) and
    return if you can find a way out from the top, bottom, left, or right…
     */
    public static boolean solveMaze(int x, int y) {

        if(isValid(x,y)) {
            if(maze[y][x]=='G'){
                key[y][x]=true;
                return true;
            }
            if (key[y][x]) {
                return false;
            }

            key[y][x] = true;
            if (solveMaze(x, y - 1)) {
                return true;
            }
            if (solveMaze(x + 1, y)) {
                return true;
            }
            if (solveMaze(x, y + 1)) {
                return true;
            }

            if (solveMaze(x - 1, y)) {
                return true;
            }
            key[y][x]=false;
            return false;
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
    TODO: solve - sets the maze up, solves the board, print whether it can be solved or not, returns whether its solvable or not
     */
    public static boolean solve(String file) throws IOException {
        setMaze(file);
        int[] sCoords=findS();
        boolean solution = solveMaze(sCoords[0],sCoords[1]);
        System.out.println("Maze can be solved: " + solution);
        return solution;

    }



}