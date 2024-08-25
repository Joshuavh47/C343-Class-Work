import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrimsTest {

    /**
     * Some test inspiration...
     *
     * What if a graph had a disconnected node?
     *
     * What if a MST was a line?
     *     Example:
     *      0 > 1
     *      1 > 2
     *      2 > 3
     *
     * What if a MST was NOT in a line?
     *     Example:
     *      0 > 1
     *      0 > 2
     *      0 > 3
     */
    @Test
    void mst() throws DisconnectedGraphException {
        //regular graph (shown here: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/)
        int[][] w={{0,4,0,0,0,0,0,8,0},{4,0,8,0,0,0,0,11,0},{0,8,0,7,0,4,0,0,2},{0,0,7,0,9,14,0,0,0},{0,0,0,9,0,10,0,0,0},{0,0,4,14,10,0,2,0,0},{0,0,0,0,0,2,0,1,6},{8,11,0,0,0,0,1,0,7},{0,2,0,0,0,0,6,7,0}};
        Graph g= new Graph(w);
        Prims p=new Prims();
        int[] test=p.mst(g);
        int[] key={-1,0,1,2,3,2,5,6,2};
        assertArrayEquals(key,test);

        //straight line graph
        int[][] w1={{0,1,0,0},{0,0,1,0},{0,0,0,1},{0,0,0,0}};
        Graph g1=new Graph(w1);
        int[] test1=p.mst(g1);
        int[] key1={-1,0,1,2};
        assertArrayEquals(key1,test1);

        //disconnected graph
        int[][] w2={{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{0,0,0,0,0},{0,0,0,0,0}};
        Graph g2=new Graph(w2);
        assertThrows(DisconnectedGraphException.class,()->p.mst(g2));
    }

    @Test
    void minIndex() {
        int[] weights={0,Integer.MAX_VALUE,12};
        Boolean[] b={true,false,false};
        Prims p=new Prims();
        assertEquals(2,p.minIndex(weights,b,3));
    }
}