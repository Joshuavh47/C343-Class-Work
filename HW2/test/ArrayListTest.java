import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
    /*
    TODO: write test cases testing the accuracy of the methods marked with TODO. Write an efficiency test which asserts that the
    SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
     */

    @Test
    void add() {
        ArrayList<String> a=new ArrayList<String>();
        a.add("Hello");
        a.add("Hi");
        ArrayList<String> b=new ArrayList<String>();
        b.add("Hello");
        b.add("Hi");
        b.add("Hey");
        assertEquals("0: Hello\n1: Hi\n",a.toString());
        assertEquals("0: Hello\n1: Hi\n2: Hey\n",b.toString());
    }
    @Test
    public void delete(){
        ArrayList<String> a=new ArrayList<String>();
        a.add("Hello");
        a.add("Hi");
        ArrayList<String> b=new ArrayList<String>();
        b.add("Hello");
        b.add("Hi");
        b.add("Hey");
        b.delete(1);
        a.delete(0);
        assertEquals("0: Hi\n",a.toString());
        assertEquals("0: Hello\n1: Hey\n",b.toString());
        assertThrows(IndexOutOfBoundsException.class,()->b.delete(-1));
        assertThrows(IndexOutOfBoundsException.class,()->b.delete(100));
    }

    @Test
    public void search(){
        ArrayList<String> a=new ArrayList<String>();
        a.add("Hello");
        a.add("Hi");
        ArrayList<String> b=new ArrayList<String>();
        b.add("Hello");
        b.add("Hi");
        b.add("Hey");
        assertEquals("0: Hello\n1: Hi\n",a.toString());
        assertEquals("0: Hello\n1: Hi\n2: Hey\n",b.toString());
        assertEquals(0,b.search("Hello"));
        assertEquals(1,b.search("Hi"));
        assertEquals(2,b.search("Hey"));
        assertEquals(-1,b.search("alskdfjlsakjf"));
    }

    @Test
    public void equals(){
        ArrayList<String> a=new ArrayList<String>();
        a.add("Hello");
        a.add("Hi");
        ArrayList<String> b=new ArrayList<String>();
        b.add("Hello");
        b.add("Hi");
        b.add("Hey");
        assertEquals("0: Hello\n1: Hi\n",a.toString());
        assertEquals("0: Hello\n1: Hi\n2: Hey\n",b.toString());
        b.delete(2);
        assertEquals(true,a.equals(b));
    }



}
