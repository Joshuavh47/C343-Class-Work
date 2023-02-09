import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SortedLinkedListTests {



    @Test
    public void insertSorted(){
        SortedLinkedList s1=new SortedLinkedList();
        s1.insertSorted(6);
        s1.insertSorted(-20);
        s1.insertSorted(10);
        s1.insertSorted(7);
        s1.insertSorted(6);

        assertEquals("-20, 6, 6, 7, 10",s1.toString());
    }

    @Test
    public void delete(){
        SortedLinkedList s1=new SortedLinkedList();
        s1.insertSorted(6);
        s1.insertSorted(-20);
        s1.insertSorted(10);
        s1.insertSorted(7);
        s1.insertSorted(6);
        assertEquals("-20, 6, 6, 7, 10",s1.toString());
        s1.delete(0);
        assertEquals("6, 6, 7, 10",s1.toString());
        s1.delete(3);
        assertEquals("6, 6, 7",s1.toString());
        s1.delete(1);
        assertEquals("6, 7",s1.toString());
        assertThrows(IndexOutOfBoundsException.class, ()->s1.delete(-1));
        assertThrows(IndexOutOfBoundsException.class, ()->s1.delete(1000));

    }

    @Test
    public void get(){
        SortedLinkedList s1=new SortedLinkedList();
        s1.insertSorted(6);
        s1.insertSorted(-20);
        s1.insertSorted(10);
        s1.insertSorted(7);
        s1.insertSorted(6);
        assertEquals("-20, 6, 6, 7, 10",s1.toString());
        assertEquals(-20,s1.get(0));
        assertEquals(6,s1.get(1));
        assertEquals(6,s1.get(2));
        assertEquals(7,s1.get(3));
        assertEquals(10,s1.get(4));
        assertThrows(IndexOutOfBoundsException.class, ()->s1.get(-1));
        assertThrows(IndexOutOfBoundsException.class, ()->s1.get(1000));
    }

    @Test
    public void search(){
        SortedLinkedList s1=new SortedLinkedList();
        s1.insertSorted(6);
        s1.insertSorted(-20);
        s1.insertSorted(10);
        s1.insertSorted(7);
        s1.insertSorted(6);
        assertEquals("-20, 6, 6, 7, 10",s1.toString());
        assertEquals(0,s1.search(-20));
        assertEquals(1,s1.search(6));
        assertEquals(3,s1.search(7));
        assertEquals(4,s1.search(10));
        assertEquals(-1,s1.search(100));
        assertEquals(-1,s1.search(-100));
    }

    /*
    TODO: tests
    - Make sure you have 100% code coverage
        + This also means you should break your tests up by method
    - Make sure you test the full functionality of this class...
      think edge cases (bounds, exceptions, etc...)
    - Use JUnit (you will not receive points for testing if you do
      not use JUnit)
     */
}
