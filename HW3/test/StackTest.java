import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    public void push(){
        Stack<String> s=new Stack<String>();
        s.push("Hi");
        assertEquals("Hi ",s.toString());
        s.push("Hey");
        assertEquals("Hi Hey ",s.toString());
        s.push("Hello");
        assertEquals("Hi Hey Hello ",s.toString());
    }

    @Test
    public void pop() throws EmptyStackE {
        Stack<String> s=new Stack<String>();
        s.push("Hi");
        assertEquals("Hi ",s.toString());
        s.push("Hey");
        assertEquals("Hi Hey ",s.toString());
        s.push("Hello");
        assertEquals("Hi Hey Hello ",s.toString());
        assertEquals("Hello",s.pop());
        assertEquals("Hi Hey ",s.toString());
        assertEquals("Hey",s.pop());
        assertEquals("Hi ",s.toString());
        assertEquals("Hi",s.pop());
        assertEquals("",s.toString());
        assertThrows(EmptyStackE.class,()->s.pop());
    }

    @Test
    public void peek(){
        Stack<String> s=new Stack<String>();
        s.push("Hi");
        assertEquals("Hi ",s.toString());
        assertEquals("Hi",s.peek());
        s.push("Hey");
        assertEquals("Hi Hey ",s.toString());
        assertEquals("Hey",s.peek());
        s.push("Hello");
        assertEquals("Hi Hey Hello ",s.toString());
        assertEquals("Hello",s.peek());
    }

    @Test
    public void equals(){
        Stack<String> s=new Stack<String>();
        s.push("Hi");
        assertEquals("Hi ",s.toString());
        s.push("Hey");
        assertEquals("Hi Hey ",s.toString());
        s.push("Hello");
        assertEquals("Hi Hey Hello ",s.toString());
        Stack<String> s1=new Stack<String>();
        s1.push("Hi");
        assertEquals("Hi ",s1.toString());
        s1.push("Hey");
        assertEquals("Hi Hey ",s1.toString());
        s1.push("Hello");
        assertEquals("Hi Hey Hello ",s1.toString());
        assertEquals(true,s.equals(s1));
        s1.push("asdf");
        assertEquals(false,s.equals(s1));
        Stack<String> s3=new Stack<String>();
        s3.push("Hey");
        assertEquals("Hey ",s3.toString());
        s3.push("Hi");
        assertEquals("Hey Hi ",s3.toString());
        s3.push("Hello");
        assertEquals("Hey Hi Hello ",s3.toString());
        assertEquals(false,s.equals(s3));
    }

    @Test
    public void isEmpty(){
        Stack<String> s=new Stack<String>();
        assertEquals(true,s.isEmpty());
        s.push("asdf");
        assertEquals(false,s.isEmpty());
    }
}
