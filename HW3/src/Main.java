import java.io.IOException;

public class Main {
    public static void main(String[] args) throws EmptyListE, IndexOutOfBoundsException, EmptyQueueE, EmptyStackE, IOException {
        DoublyLinkedList<String> d=new DoublyLinkedList<String>();
        d.insertAtHead("Hi");
        d.insertAtHead("Hello");
        d.insertAtHead("Hey");
        System.out.println(d.toString());
    }
}
