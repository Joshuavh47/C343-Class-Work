import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTest {
    @Test
    public void stackTest() throws IOException, EmptyStackE, EmptyQueueE {
        assertEquals(true,MazeSolver.solve("maze1.txt",1));
        assertEquals(true,MazeSolver.solve("maze2.txt",1));
        assertEquals(false,MazeSolver.solve("maze3.txt",1));
    }

    @Test
    public void queueTest() throws IOException, EmptyStackE, EmptyQueueE {
        assertEquals(true,MazeSolver.solve("maze1.txt",2));
        assertEquals(true,MazeSolver.solve("maze2.txt",2));
        assertEquals(false,MazeSolver.solve("maze3.txt",2));
    }
}
