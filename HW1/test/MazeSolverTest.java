import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTest {
    /*
    TODO - write JUnit tests testing the boards we gave you on the assignment
     */
    @Test
    public void testMaze1() throws IOException, FileNotFoundException {
        assertDoesNotThrow(()->MazeSolver.solve("maze1.txt"));
        assertEquals(true,MazeSolver.solve("maze1.txt"));
    }
    @Test
    public void testMaze2() throws IOException, FileNotFoundException {
        assertDoesNotThrow(()->MazeSolver.solve("maze2.txt"));
        assertEquals(true,MazeSolver.solve("maze2.txt"));
    }
    @Test
    public void testMaze3() throws IOException, FileNotFoundException {
        assertDoesNotThrow(()->MazeSolver.solve("maze3.txt"));
        assertEquals(false,MazeSolver.solve("maze3.txt"));
    }


}
