import com.sun.source.tree.BinaryTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTreeTest {

    /*
    TODO: tests
    - Make sure you have 100% code coverage
        + This also means you should break your tests up by method
    - Make sure you test the full functionality of this class...
      think edge cases (bounds, exceptions, etc...)
    - Use JUnit (you will not receive points for testing if you do
      not use JUnit)
     */
    @Test
    public void insert(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(20);
        tree.insert(6);
        tree.insert(22);
        tree.insert(23);
        tree.insert(21);
        assertEquals("2, 3, 4, 6, 20, 21, 22, 23",tree.getInOrderStr());
        assertEquals(4,tree.getRoot().getItem());
        assertEquals(2,tree.getRoot().getLeft().getItem());
        assertEquals(3,tree.getRoot().getLeft().getRight().getItem());
        assertEquals(20,tree.getRoot().getRight().getItem());
        assertEquals(22,tree.getRoot().getRight().getRight().getItem());
        assertEquals(6,tree.getRoot().getRight().getLeft().getItem());
        assertEquals(23,tree.getRoot().getRight().getRight().getRight().getItem());
        assertEquals(21,tree.getRoot().getRight().getRight().getLeft().getItem());
    }

    @Test
    public void remove(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(20);
        tree.insert(6);
        tree.insert(22);
        tree.insert(23);
        tree.insert(21);
        assertEquals("2, 3, 4, 6, 20, 21, 22, 23",tree.getInOrderStr());
        assertEquals(4,tree.getRoot().getItem());
        assertEquals(2,tree.getRoot().getLeft().getItem());
        assertEquals(3,tree.getRoot().getLeft().getRight().getItem());
        assertEquals(20,tree.getRoot().getRight().getItem());
        assertEquals(22,tree.getRoot().getRight().getRight().getItem());
        assertEquals(6,tree.getRoot().getRight().getLeft().getItem());
        assertEquals(23,tree.getRoot().getRight().getRight().getRight().getItem());
        assertEquals(21,tree.getRoot().getRight().getRight().getLeft().getItem());

        BinaryTreeNode tempNode=tree.search(4);
        assertEquals(tempNode,tree.remove(4));

        assertEquals(6,tree.getRoot().getItem());
        assertEquals(2,tree.getRoot().getLeft().getItem());
        assertEquals(3,tree.getRoot().getLeft().getRight().getItem());
        assertEquals(20,tree.getRoot().getRight().getItem());
        assertEquals(22,tree.getRoot().getRight().getRight().getItem());

        assertEquals(23,tree.getRoot().getRight().getRight().getRight().getItem());
        assertEquals(21,tree.getRoot().getRight().getRight().getLeft().getItem());
    }

    @Test
    public void search(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(20);
        tree.insert(6);
        tree.insert(22);
        tree.insert(23);
        tree.insert(21);
        assertEquals("2, 3, 4, 6, 20, 21, 22, 23",tree.getInOrderStr());
        assertEquals(4,tree.getRoot().getItem());
        assertEquals(2,tree.getRoot().getLeft().getItem());
        assertEquals(3,tree.getRoot().getLeft().getRight().getItem());
        assertEquals(20,tree.getRoot().getRight().getItem());
        assertEquals(22,tree.getRoot().getRight().getRight().getItem());
        assertEquals(6,tree.getRoot().getRight().getLeft().getItem());
        assertEquals(23,tree.getRoot().getRight().getRight().getRight().getItem());
        assertEquals(21,tree.getRoot().getRight().getRight().getLeft().getItem());

        assertEquals(tree.getRoot().getItem(),tree.search(4).getItem());
        assertEquals(tree.getRoot().getLeft().getItem(),tree.search(2).getItem());
        assertEquals(tree.getRoot().getLeft().getRight().getItem(),tree.search(3).getItem());
        assertEquals(tree.getRoot().getRight().getItem(),tree.search(20).getItem());
        assertEquals(tree.getRoot().getRight().getRight().getItem(),tree.search(22).getItem());
        assertEquals(tree.getRoot().getRight().getLeft().getItem(),tree.search(6).getItem());
        assertEquals(tree.getRoot().getRight().getRight().getRight().getItem(),tree.search(23).getItem());
        assertEquals(tree.getRoot().getRight().getRight().getLeft().getItem(),tree.search(21).getItem());


    }

    @Test
    public void getPreOrderStr(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(20);
        tree.insert(6);
        tree.insert(22);
        tree.insert(23);
        tree.insert(21);
        assertEquals("2, 3, 4, 6, 20, 21, 22, 23",tree.getInOrderStr());
        assertEquals(4,tree.getRoot().getItem());
        assertEquals(2,tree.getRoot().getLeft().getItem());
        assertEquals(3,tree.getRoot().getLeft().getRight().getItem());
        assertEquals(20,tree.getRoot().getRight().getItem());
        assertEquals(22,tree.getRoot().getRight().getRight().getItem());
        assertEquals(6,tree.getRoot().getRight().getLeft().getItem());
        assertEquals(23,tree.getRoot().getRight().getRight().getRight().getItem());
        assertEquals(21,tree.getRoot().getRight().getRight().getLeft().getItem());

        assertEquals("4, 2, 3, 20, 6, 22, 21, 23",tree.getPreOrderStr());
    }

    @Test
    public void getInOrderStr(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(20);
        tree.insert(6);
        tree.insert(22);
        tree.insert(23);
        tree.insert(21);
        assertEquals("2, 3, 4, 6, 20, 21, 22, 23",tree.getInOrderStr());
        assertEquals(4,tree.getRoot().getItem());
        assertEquals(2,tree.getRoot().getLeft().getItem());
        assertEquals(3,tree.getRoot().getLeft().getRight().getItem());
        assertEquals(20,tree.getRoot().getRight().getItem());
        assertEquals(22,tree.getRoot().getRight().getRight().getItem());
        assertEquals(6,tree.getRoot().getRight().getLeft().getItem());
        assertEquals(23,tree.getRoot().getRight().getRight().getRight().getItem());
        assertEquals(21,tree.getRoot().getRight().getRight().getLeft().getItem());

        assertEquals("2, 3, 4, 6, 20, 21, 22, 23",tree.getInOrderStr());
    }

    @Test
    public void getPostOrderStr(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(20);
        tree.insert(6);
        tree.insert(22);
        tree.insert(23);
        tree.insert(21);
        assertEquals("2, 3, 4, 6, 20, 21, 22, 23",tree.getInOrderStr());
        assertEquals(4,tree.getRoot().getItem());
        assertEquals(2,tree.getRoot().getLeft().getItem());
        assertEquals(3,tree.getRoot().getLeft().getRight().getItem());
        assertEquals(20,tree.getRoot().getRight().getItem());
        assertEquals(22,tree.getRoot().getRight().getRight().getItem());
        assertEquals(6,tree.getRoot().getRight().getLeft().getItem());
        assertEquals(23,tree.getRoot().getRight().getRight().getRight().getItem());
        assertEquals(21,tree.getRoot().getRight().getRight().getLeft().getItem());

        assertEquals("3, 2, 6, 21, 23, 22, 20, 4",tree.getPostOrderStr());
    }

}
