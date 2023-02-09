import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    public void enqueue() throws EmptyQueueE {
        Queue<String> q=new Queue<String>();
        q.enqueue("Hi");
        q.enqueue("Hey");
        q.enqueue("Hello");
        assertEquals("Hi",q.dequeue());
        assertEquals("Hey",q.dequeue());
        assertEquals("Hello",q.dequeue());
    }

    @Test

    public void dequeue() throws EmptyQueueE{
        Queue<String> q=new Queue<String>();
        q.enqueue("Hi");
        q.enqueue("Hey");
        q.enqueue("Hello");
        assertEquals("Hi",q.dequeue());
        assertEquals("Hey",q.dequeue());
        assertEquals("Hello",q.dequeue());
        assertThrows(EmptyQueueE.class,()->q.dequeue());
    }

    @Test
    public void peek() throws EmptyQueueE {
        Queue<String> q=new Queue<String>();
        q.enqueue("Hi");
        q.enqueue("Hey");
        q.enqueue("Hello");
        assertEquals("Hi",q.peek());
        q.dequeue();
        assertEquals("Hey",q.peek());
        q.dequeue();
        assertEquals("Hello",q.peek());
    }

    @Test
    public void equals() throws EmptyQueueE {
        Queue<String> q=new Queue<String>();
        q.enqueue("Hi");
        q.enqueue("Hey");
        q.enqueue("Hello");
        Queue<String> q1=new Queue<String>();
        q1.enqueue("Hi");
        q1.enqueue("Hey");
        q1.enqueue("Hello");
        assertEquals(true,q.equals(q1));
        q1.dequeue();
        assertEquals(false,q.equals(q1));
        q1.enqueue("asdf");
        assertEquals(false,q.equals(q1));
    }

    @Test
    public void isEmpty(){
        Queue<String> q=new Queue<String>();
        assertEquals(true,q.isEmpty());
        q.enqueue("asdf");
        assertEquals(false,q.isEmpty());
    }
}
