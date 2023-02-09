import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest {
    @Test
    public void insertAtHead(){
        DoublyLinkedList<String> d=new DoublyLinkedList<String>();
        d.insertAtHead("Hi");
        assertEquals("Hi ",d.toString());
        d.insertAtHead("Hello");
        assertEquals("Hello Hi ",d.toString());
        d.insertAtHead("Hey");
        assertEquals("Hey Hello Hi ",d.toString());
    }

    @Test
    public void insertAtTail(){
        DoublyLinkedList<String> d=new DoublyLinkedList<String>();
        d.insertAtTail("Hi");
        assertEquals("Hi ",d.toString());
        d.insertAtTail("Hello");
        assertEquals("Hi Hello ",d.toString());
        d.insertAtTail("Hey");
        assertEquals("Hi Hello Hey ",d.toString());
    }

    @Test
    public void deleteAtHead() throws EmptyListE {
        DoublyLinkedList<String> d=new DoublyLinkedList<String>();
        d.insertAtTail("Hi");
        assertEquals("Hi ",d.toString());
        d.insertAtTail("Hello");
        assertEquals("Hi Hello ",d.toString());
        d.insertAtTail("Hey");
        assertEquals("Hi Hello Hey ",d.toString());
        d.deleteAtHead();
        assertEquals("Hello Hey ",d.toString());
        d.deleteAtHead();
        assertEquals("Hey ",d.toString());
        d.deleteAtHead();
        assertEquals("",d.toString());
        assertThrows(EmptyListE.class,()->d.deleteAtHead());

    }

    @Test
    public void deleteAtTail() throws EmptyListE {
        DoublyLinkedList<String> d=new DoublyLinkedList<String>();
        d.insertAtTail("Hi");
        assertEquals("Hi ",d.toString());
        d.insertAtTail("Hello");
        assertEquals("Hi Hello ",d.toString());
        d.insertAtTail("Hey");
        assertEquals("Hi Hello Hey ",d.toString());
        d.deleteAtTail();
        assertEquals("Hi Hello ",d.toString());
        d.deleteAtTail();
        assertEquals("Hi ",d.toString());
        d.deleteAtTail();
        assertEquals("",d.toString());
        assertThrows(EmptyListE.class,()->d.deleteAtTail());
    }

    @Test
    public void get() throws IndexOutOfBoundsException{
        DoublyLinkedList<String> d=new DoublyLinkedList<String>();
        d.insertAtTail("Hi");
        assertEquals("Hi ",d.toString());
        d.insertAtTail("Hello");
        assertEquals("Hi Hello ",d.toString());
        d.insertAtTail("Hey");
        assertEquals("Hi Hello Hey ",d.toString());
        assertEquals("Hi",d.get(0));
        assertEquals("Hello",d.get(1));
        assertEquals("Hey",d.get(2));
        assertThrows(IndexOutOfBoundsException.class,()->d.get(1000));
    }

    @Test
    public void search(){
        DoublyLinkedList<String> d=new DoublyLinkedList<String>();
        d.insertAtTail("Hi");
        assertEquals("Hi ",d.toString());
        d.insertAtTail("Hello");
        assertEquals("Hi Hello ",d.toString());
        d.insertAtTail("Hey");
        assertEquals("Hi Hello Hey ",d.toString());
        assertEquals(0,d.search("Hi"));
        assertEquals(1,d.search("Hello"));
        assertEquals(2,d.search("Hey"));
        assertEquals(-1,d.search("askdfjhaskfj"));
    }

    @Test
    public void equals(){
        DoublyLinkedList<String> d1=new DoublyLinkedList<String>();
        d1.insertAtTail("Hi");
        assertEquals("Hi ",d1.toString());
        d1.insertAtTail("Hello");
        assertEquals("Hi Hello ",d1.toString());
        d1.insertAtTail("Hey");
        assertEquals("Hi Hello Hey ",d1.toString());
        DoublyLinkedList<String> d2=new DoublyLinkedList<String>();
        d2.insertAtTail("Hi");
        assertEquals("Hi ",d2.toString());
        d2.insertAtTail("Hello");
        assertEquals("Hi Hello ",d2.toString());
        d2.insertAtTail("Hey");
        assertEquals("Hi Hello Hey ",d2.toString());
        assertEquals(true,d1.equals(d2));
        DoublyLinkedList<String> d3=new DoublyLinkedList<String>();
        d3.insertAtTail("asdf");
        assertEquals("asdf ",d3.toString());
        d3.insertAtTail("Hello");
        assertEquals("asdf Hello ",d3.toString());
        d3.insertAtTail("Hey");
        assertEquals("asdf Hello Hey ",d3.toString());
        assertEquals(false,d1.equals(d3));
        d1.insertAtTail("asdf");
        assertEquals(false,d1.equals(d2));
        assertEquals(false,d2.equals(d3));
    }
}
