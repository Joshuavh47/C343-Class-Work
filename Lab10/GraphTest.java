import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void unweighted() {
        //used the same graph as in the example
        ArrayList<Node> arr=new ArrayList<>();
        for(int i=0;i<6;i++){
            arr.add(new Node(i));
        }
        HashMap<Node, List<Node>> h=new HashMap<>();
        List<Node> l1=new ArrayList<>();
        for(int i=0;i<6;i++) {
            h.put(arr.get(i), new ArrayList<Node>());
        }
        h.get(arr.get(0)).add(arr.get(1));
        h.get(arr.get(1)).add(arr.get(0));
        h.get(arr.get(1)).add(arr.get(2));
        h.get(arr.get(1)).add(arr.get(3));
        h.get(arr.get(2)).add(arr.get(1));
        h.get(arr.get(2)).add(arr.get(5));
        h.get(arr.get(3)).add(arr.get(1));
        h.get(arr.get(3)).add(arr.get(4));
        h.get(arr.get(4)).add(arr.get(3));
        h.get(arr.get(4)).add(arr.get(5));
        h.get(arr.get(5)).add(arr.get(2));
        h.get(arr.get(5)).add(arr.get(4));
        Graph g=new Graph(arr,h);
        g.unweighted(arr.get(0));
        assertEquals(0,arr.get(0).getDistance());
        assertEquals(1,arr.get(1).getDistance());
        assertEquals(2,arr.get(2).getDistance());
        assertEquals(2,arr.get(3).getDistance());
        assertEquals(3,arr.get(4).getDistance());
        assertEquals(3,arr.get(5).getDistance());

        assertEquals(0,arr.get(1).getPath().getName());
        assertEquals(1,arr.get(2).getPath().getName());
        assertEquals(1,arr.get(3).getPath().getName());
        assertEquals(3,arr.get(4).getPath().getName());
        assertEquals(2,arr.get(5).getPath().getName());
        arr.add(new Node(7));

        //makes sure that nodes that have no neighbors have distance of Integer.MAX_VALUE
        h.put(arr.get(arr.size()-1),new ArrayList<Node>());
        h.get(arr.get(arr.size()-1)).add(arr.get(arr.size()-1));
        g.unweighted(arr.get(0));
        assertEquals(0,arr.get(0).getDistance());
        assertEquals(1,arr.get(1).getDistance());
        assertEquals(2,arr.get(2).getDistance());
        assertEquals(2,arr.get(3).getDistance());
        assertEquals(3,arr.get(4).getDistance());
        assertEquals(3,arr.get(5).getDistance());
        assertEquals(Integer.MAX_VALUE,arr.get(arr.size()-1).getDistance());

        assertEquals(0,arr.get(1).getPath().getName());
        assertEquals(1,arr.get(2).getPath().getName());
        assertEquals(1,arr.get(3).getPath().getName());
        assertEquals(3,arr.get(4).getPath().getName());
        assertEquals(2,arr.get(5).getPath().getName());


    }

    @Test
    void pathAndDistance() {
        ArrayList<Node> arr=new ArrayList<>();
        for(int i=0;i<6;i++){
            arr.add(new Node(i));
        }
        HashMap<Node, List<Node>> h=new HashMap<>();
        List<Node> l1=new ArrayList<>();
        for(int i=0;i<6;i++) {
            h.put(arr.get(i), new ArrayList<Node>());
        }
        h.get(arr.get(0)).add(arr.get(1));
        h.get(arr.get(1)).add(arr.get(0));
        h.get(arr.get(1)).add(arr.get(2));
        h.get(arr.get(1)).add(arr.get(3));
        h.get(arr.get(2)).add(arr.get(1));
        h.get(arr.get(2)).add(arr.get(5));
        h.get(arr.get(3)).add(arr.get(1));
        h.get(arr.get(3)).add(arr.get(4));
        h.get(arr.get(4)).add(arr.get(3));
        h.get(arr.get(4)).add(arr.get(5));
        h.get(arr.get(5)).add(arr.get(2));
        h.get(arr.get(5)).add(arr.get(4));
        Graph g=new Graph(arr,h);
        g.unweighted(arr.get(0));

        //makes sure the paths are correct
        ArrayList<Node> path0=g.pathAndDistance(arr.get(0),0);
        Node[] n0=path0.toArray(new Node[1]);
        Node[] expected0={arr.get(0)};
        assertArrayEquals(expected0,n0);

        ArrayList<Node> path1=g.pathAndDistance(arr.get(1),0);
        Node[] n1=path1.toArray(new Node[2]);
        Node[] expected1={arr.get(0),arr.get(1)};
        assertArrayEquals(expected1,n1);

        ArrayList<Node> path2=g.pathAndDistance(arr.get(2),0);
        Node[] n2=path2.toArray(new Node[3]);
        Node[] expected2={arr.get(0),arr.get(1),arr.get(2)};
        assertArrayEquals(expected2,n2);

        ArrayList<Node> path3=g.pathAndDistance(arr.get(3),0);
        Node[] n3=path3.toArray(new Node[3]);
        Node[] expected3={arr.get(0),arr.get(1),arr.get(3)};
        assertArrayEquals(expected3,n3);

        ArrayList<Node> path4=g.pathAndDistance(arr.get(4),0);
        Node[] n4=path4.toArray(new Node[4]);
        Node[] expected4={arr.get(0),arr.get(1),arr.get(3),arr.get(4)};
        assertArrayEquals(expected4,n4);

        ArrayList<Node> path5=g.pathAndDistance(arr.get(5),0);
        Node[] n5=path5.toArray(new Node[4]);
        Node[] expected5={arr.get(0),arr.get(1),arr.get(2),arr.get(5)};
        assertArrayEquals(expected5,n5);

        //tests if the method works for disconnected nodes
        arr.add(new Node(7));
        h.put(arr.get(arr.size()-1),new ArrayList<Node>());
        h.get(arr.get(arr.size()-1)).add(arr.get(arr.size()-1));
        g.unweighted(arr.get(0));

        ArrayList<Node> pathDisconnected=g.pathAndDistance(arr.get(arr.size()-1),0);
        Node[] nDisconnected=pathDisconnected.toArray(new Node[1]);
        Node[] expectedDisconnected={arr.get(arr.size()-1)};
        assertArrayEquals(expectedDisconnected,nDisconnected);
        assertEquals(Integer.MAX_VALUE,arr.get(arr.size()-1).getDistance());


    }
}