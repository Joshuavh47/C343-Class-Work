import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GraphTest {

    // TODO: Write accuracy tests + test different graphs :)

    @Test
    public void shortestPath1(){
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        List<Node> nodes = List.of(n0,n1,n2,n3);
        Graph g = new Graph(nodes);

        g.addEdge(n0, n1, 1);
        g.addEdge(n0, n2, 1);
        g.addEdge(n1, n3, 4);
        g.addEdge(n2, n3,2);

        List<Node> traverse = g.shortestPath(0, 3);
        assertEquals(0, n0.getValue());
        assertEquals(1, n2.getValue());
        assertEquals(3, n3.getValue());
        assertEquals(3, traverse.size());
        g.reset();

        traverse = g.shortestPath(2, 0);
        assertNull(traverse);
    }

    @Test
    public void shortestPath2(){
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        List<Node> nodes = List.of(n0,n1,n2,n3,n4);
        Graph g=new Graph(nodes);

        g.addEdge(n0,n1,10);
        g.addEdge(n0,n2,1);
        g.addEdge(n2,n3,2);
        g.addEdge(n3,n4,3);
        g.addEdge(n4,n1,2);

        assertEquals(g.shortestPath(0,1).toString(),"[0 0, 2 1, 3 3, 4 6, 1 8]");

    }

    @Test
    public void shortestPath3() {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        List<Node> nodes = List.of(n0,n1,n2,n3,n4,n5);
        Graph g=new Graph(nodes);
        g.addEdge(n0,n1,10);
        g.addEdge(n0,n2,5);
        g.addEdge(n0,n3,15);
        g.addEdge(n1,n4,10);
        g.addEdge(n2,n4,1);
        g.addEdge(n3,n4,20);
        g.addEdge(n4,n5,2);

        assertEquals(g.shortestPath(0,5).toString(),"[0 0, 2 5, 4 6, 5 8]");

        //tests that disconnected nodes return null
        g.reset();
        g.removeEdge(n4,n5);
        assertEquals(g.shortestPath(0,5),null);
    }

    @Test
    public void addEdge(){
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        List<Node> nodes = List.of(n0,n1,n2,n3);
        Graph g = new Graph(nodes);

        assertEquals(n0.getEdges().size(),0);
        assertEquals(n1.getEdges().size(),0);
        assertEquals(n2.getEdges().size(),0);
        assertEquals(n3.getEdges().size(),0);

        g.addEdge(n0, n1, 1);
        assertEquals(n0.getEdges().size(),1);
        g.addEdge(n0, n2, 1);
        assertEquals(n0.getEdges().size(),2);
        g.addEdge(n1, n3, 4);
        assertEquals(n1.getEdges().size(),1);
        g.addEdge(n2, n3,2);
        assertEquals(n2.getEdges().size(),1);
        assertEquals(n0.getEdges().get(0).getDestVertex().getName(),1);
        assertEquals(n0.getEdges().get(1).getDestVertex().getName(),2);
        assertEquals(n1.getEdges().get(0).getDestVertex().getName(),3);
        assertEquals(n2.getEdges().get(0).getDestVertex().getName(),3);
    }

    @Test
    public void removeEdge(){
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        List<Node> nodes = List.of(n0,n1,n2,n3);
        Graph g = new Graph(nodes);

        g.addEdge(n0, n1, 1);
        g.addEdge(n0, n2, 1);
        g.addEdge(n1, n3, 4);
        g.addEdge(n2, n3,2);
        assertEquals(n0.getEdges().size(),2);
        g.removeEdge(n0,n1);
        assertEquals(n0.getEdges().size(),1);
        g.removeEdge(n0,n2);
        assertEquals(n0.getEdges().size(),0);
    }

}
