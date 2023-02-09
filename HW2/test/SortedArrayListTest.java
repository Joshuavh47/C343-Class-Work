import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class SortedArrayListTest {
    /*
    TODO: write test cases testing the accuracy of the methods marked with TODO. Write an efficiency test which asserts that the
    SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
     */
    @Test
    void add() {
        SortedArrayList<Integer> a1=new SortedArrayList<Integer>();
        a1.add(3);
        a1.add(2);
        a1.add(7);
        a1.add(10);
        a1.add(1);
        a1.add(5);
        a1.add(20);
        a1.add(5);



        assertEquals("0: 1\n1: 2\n2: 3\n3: 5\n4: 5\n5: 7\n6: 10\n7: 20\n",a1.toString());


        SortedArrayList<String> a2 = new SortedArrayList<String>();
        a2.add("hello");
        a2.add("hey");
        a2.add("howdy");
        a2.add("hi");
        assertEquals("0: hello\n1: hey\n2: hi\n3: howdy\n",a2.toString());



    }
    @Test
    public void delete(){
        SortedArrayList<Integer> a1=new SortedArrayList<Integer>();
        a1.add(3);
        a1.add(2);
        a1.add(7);
        a1.add(10);
        a1.add(1);
        a1.add(5);
        a1.add(20);
        a1.add(5);

        assertEquals("0: 1\n1: 2\n2: 3\n3: 5\n4: 5\n5: 7\n6: 10\n7: 20\n",a1.toString());
        a1.delete(3);
        assertEquals("0: 1\n1: 2\n2: 3\n3: 5\n4: 7\n5: 10\n6: 20\n",a1.toString());

        SortedArrayList<String> a2 = new SortedArrayList<String>();
        a2.add("hello");
        a2.add("hey");
        a2.add("howdy");
        a2.add("hi");
        assertEquals("0: hello\n1: hey\n2: hi\n3: howdy\n",a2.toString());
        a2.delete(2);
        assertEquals("0: hello\n1: hey\n2: howdy\n",a2.toString());

        assertThrows(IndexOutOfBoundsException.class,()->a1.delete(-1));
        assertThrows(IndexOutOfBoundsException.class,()->a1.delete(100));
    }

    @Test
    public void search(){
        SortedArrayList<Integer> a1=new SortedArrayList<Integer>();
        a1.add(3);
        a1.add(2);
        a1.add(7);
        a1.add(10);
        a1.add(1);
        a1.add(5);
        a1.add(20);
        a1.add(5);

        assertEquals(0,a1.search(1));
        assertEquals(1,a1.search(2));
        assertEquals(2,a1.search(3));
        assertEquals(3,a1.search(5));
        assertEquals(5,a1.search(7));
        assertEquals(6,a1.search(10));
        assertEquals(7,a1.search(20));
        assertEquals(-1,a1.search(-4));
        assertEquals(-1,a1.search(100));


        assertEquals("0: 1\n1: 2\n2: 3\n3: 5\n4: 5\n5: 7\n6: 10\n7: 20\n",a1.toString());


        SortedArrayList<String> a2 = new SortedArrayList<String>();
        a2.add("hello");
        a2.add("hey");
        a2.add("howdy");
        a2.add("hi");
        assertEquals("0: hello\n1: hey\n2: hi\n3: howdy\n",a2.toString());

        assertEquals(0,a2.search("hello"));
        assertEquals(1,a2.search("hey"));
        assertEquals(2,a2.search("hi"));
        assertEquals(3,a2.search("howdy"));
        assertEquals(-1,a2.search("he"));

    }

    @Test
    public void equals(){
        SortedArrayList<Integer> a1=new SortedArrayList<Integer>();
        a1.add(3);
        a1.add(2);
        a1.add(7);
        a1.add(10);
        a1.add(1);
        a1.add(5);
        a1.add(20);
        a1.add(5);
        SortedArrayList<Integer> a2=new SortedArrayList<Integer>();
        a2.add(3);
        a2.add(2);
        a2.add(7);
        a2.add(10);
        a2.add(1);
        a2.add(5);
        a2.add(20);
        a2.add(5);
        assertEquals(true,a1.equals(a2));
        a2.delete(0);
        assertEquals(false,a1.equals(a2));
    }
}
