import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    public void nodeCount() throws IOException{
        //asserts that the correct number of nodes is returned
        GraphL g=Runner.setUpGraph("graph.txt");
        assertEquals(4,g.nodeCount());

        g=Runner.setUpGraph("graph2.txt");
        assertEquals(6,g.nodeCount());

        g=Runner.setUpGraph("graph4.txt");
        assertEquals(8,g.nodeCount());
    }

    @Test
    public void edgeCount() throws IOException {
        //asserts that the correct number of edges are returned
        GraphL g=Runner.setUpGraph("graph.txt");
        assertEquals(4,g.edgeCount());

        g=Runner.setUpGraph("graph2.txt");
        assertEquals(6,g.edgeCount());

        g=Runner.setUpGraph("graph4.txt");
        assertEquals(8,g.edgeCount());
    }

    @Test
    public void addEdge() throws IOException{
        //since the runner uses addEdge() to build the graph, I use it to test
        GraphL g=Runner.setUpGraph("graph.txt");
        assertEquals(1,g.neighbors(0).get(0).getName());
        assertEquals(2,g.neighbors(0).get(1).getName());
        assertEquals(3,g.neighbors(1).get(0).getName());
        assertEquals(3,g.neighbors(2).get(0).getName());

        g=Runner.setUpGraph("graph2.txt");
        assertEquals(0,g.neighbors(4).get(0).getName());
        assertEquals(0,g.neighbors(5).get(0).getName());
        assertEquals(1,g.neighbors(4).get(1).getName());
        assertEquals(2,g.neighbors(5).get(1).getName());
        assertEquals(3,g.neighbors(2).get(0).getName());
        assertEquals(1,g.neighbors(3).get(0).getName());

        g=Runner.setUpGraph("graph4.txt");
        assertEquals(2,g.neighbors(0).get(0).getName());
        assertEquals(4,g.neighbors(0).get(1).getName());
        assertEquals(5,g.neighbors(2).get(0).getName());
        assertEquals(6,g.neighbors(4).get(0).getName());
        assertEquals(7,g.neighbors(6).get(0).getName());
        assertEquals(5,g.neighbors(3).get(0).getName());
        assertEquals(5,g.neighbors(1).get(0).getName());
        assertEquals(7,g.neighbors(5).get(0).getName());
    }

    @Test
    public void removeEdge() throws IOException{
        GraphL g=Runner.setUpGraph("graph.txt");
        assertEquals(1,g.neighbors(0).get(0).getName());
        assertEquals(2,g.neighbors(0).get(1).getName());
        assertEquals(2,g.neighbors(0).size());
        assertEquals(3,g.neighbors(1).get(0).getName());
        assertEquals(3,g.neighbors(2).get(0).getName());

        g.removeEdge(0,1);
        assertEquals(1,g.neighbors(0).size());

        g=Runner.setUpGraph("graph2.txt");
        assertEquals(0,g.neighbors(4).get(0).getName());
        assertEquals(0,g.neighbors(5).get(0).getName());
        assertEquals(1,g.neighbors(4).get(1).getName());
        assertEquals(2,g.neighbors(5).get(1).getName());
        assertEquals(3,g.neighbors(2).get(0).getName());
        assertEquals(1,g.neighbors(3).get(0).getName());
        assertEquals(2,g.neighbors(4).size());
        assertEquals(2,g.neighbors(5).size());

        g.removeEdge(4,0);
        assertEquals(1,g.neighbors(4).size());

        g.removeEdge(5,2);
        assertEquals(1,g.neighbors(5).size());

        g=Runner.setUpGraph("graph4.txt");
        assertEquals(2,g.neighbors(0).get(0).getName());
        assertEquals(4,g.neighbors(0).get(1).getName());
        assertEquals(5,g.neighbors(2).get(0).getName());
        assertEquals(6,g.neighbors(4).get(0).getName());
        assertEquals(7,g.neighbors(6).get(0).getName());
        assertEquals(5,g.neighbors(3).get(0).getName());
        assertEquals(5,g.neighbors(1).get(0).getName());
        assertEquals(7,g.neighbors(5).get(0).getName());
        assertEquals(1,g.neighbors(4).size());

        g.removeEdge(4,6);
        assertEquals(0,g.neighbors(4).size());
    }

    @Test
    public void hasEdge() throws IOException{
        GraphL g=Runner.setUpGraph("graph.txt");
        assertEquals(true,g.hasEdge(0,1));
        assertEquals(true,g.hasEdge(0,2));
        assertEquals(true,g.hasEdge(1,3));
        assertEquals(true,g.hasEdge(2,3));
        assertEquals(false,g.hasEdge(3,0));
        assertEquals(false,g.hasEdge(3,2));

        g=Runner.setUpGraph("graph2.txt");
        assertEquals(true,g.hasEdge(5,0));
        assertEquals(true,g.hasEdge(4,0));
        assertEquals(true,g.hasEdge(4,1));
        assertEquals(true,g.hasEdge(5,2));
        assertEquals(true,g.hasEdge(2,3));
        assertEquals(true,g.hasEdge(3,1));
        assertEquals(false,g.hasEdge(3,0));
        assertEquals(false,g.hasEdge(4,2));

        g=Runner.setUpGraph("graph4.txt");
        assertEquals(true,g.hasEdge(0,2));
        assertEquals(true,g.hasEdge(0,4));
        assertEquals(true,g.hasEdge(2,5));
        assertEquals(true,g.hasEdge(4,6));
        assertEquals(true,g.hasEdge(6,7));
        assertEquals(true,g.hasEdge(3,5));
        assertEquals(true,g.hasEdge(1,5));
        assertEquals(true,g.hasEdge(5,7));
        assertEquals(false,g.hasEdge(1,7));
        assertEquals(false,g.hasEdge(2,6));
    }

    @Test
    public void topologicalSortStack() throws IOException,CycleDetected{
        //It wouldn't let me use assertThrows to test cycles (because the lambdas weren't final)
        // so I left those tests out
        GraphL g=Runner.setUpGraph("graph.txt");
        List<Node> l1=g.topologicalSortStack();
        assertEquals("[0, 2, 1, 3]",l1.toString());

        g=Runner.setUpGraph("graph2.txt");
        List<Node> l2=g.topologicalSortStack();
        assertEquals("[5, 4, 2, 3, 1, 0]",l2.toString());

        g=Runner.setUpGraph("graph4.txt");
        List<Node> l3=g.topologicalSortStack();
        assertEquals("[3, 1, 0, 4, 6, 2, 5, 7]",l3.toString());
    }

    @Test
    public void topologicalSortQueue() throws IOException,CycleDetected{
        //It wouldn't let me use assertThrows to test cycles (because the lambdas weren't final)
        // so I left those tests out
        GraphL g=Runner.setUpGraph("graph.txt");
        List<Node> l1=g.topologicalSortQueue();
        assertEquals("[0, 1, 2, 3]",l1.toString());

        g=Runner.setUpGraph("graph2.txt");
        List<Node> l2=g.topologicalSortQueue();
        assertEquals("[4, 5, 0, 2, 3, 1]",l2.toString());

        g=Runner.setUpGraph("graph4.txt");
        List<Node> l3=g.topologicalSortQueue();
        assertEquals("[0, 1, 3, 2, 4, 5, 6, 7]",l3.toString());
    }

}
