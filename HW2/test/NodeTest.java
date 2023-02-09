import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
        /*
    TODO: write test cases testing the accuracy of the methods marked with TODO.s.
     */
    @Test
    public void compareTo(){
        Node n1=new Node("test",1);
        Node n2=new Node("test",1);
        assertEquals(0,n1.compareTo(n2));
        Node n3=new Node("a",1);
    }

    @Test
    public void equals(){
        Node n1=new Node("test",1);
        Node n2=new Node("test",1);
        assertEquals(true,n1.equals(n2));
        Node n3=new Node("a",1);
        assertEquals(false,n1.equals(n3));
    }
}
