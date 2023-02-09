import org.w3c.dom.Node;

class EmptyListE extends Exception{}

public class DoublyLinkedList<E> {

    private NodeDL<E> head;
    private NodeDL<E> tail;
    private int size;

    // TODO: default constructor
    public DoublyLinkedList(){
        this.head=null;
        this.tail=null;
        size=0;
    }

    // TODO: secondary constructor
    public DoublyLinkedList(NodeDL<E> head, NodeDL<E> tail){
        if(head!=null&&tail!=null) {
            this.head = head;
            this.tail = tail;
            size = 2;
        }
        else if(head==null&&tail!=null){
            this.head=tail;
            this.tail=tail;
            size=1;
        }
        else{
            this.head=head;
            this.tail=head;
            size=1;
        }
    }

    public int size() {
        return this.size;
    }

    // TODO: Insert elem at the start of the DoublyLinkedList
    void insertAtHead(E elem){
        NodeDL<E> temp=new NodeDL<E>(elem);
        if(size==0){
            head=temp;
            tail=temp;
        }
        if(size==1&&head==null){
            this.head=tail;
        }
        temp.next=this.head;
        temp.next.prev=temp;
        this.head=temp;
        size++;
    }



    // TODO: Insert elem at the end of the DoublyLinkedList
    void insertAtTail(E elem){
        NodeDL<E> temp=new NodeDL<E>(elem);
        if(size==0){
            head=temp;
            tail=temp;
            size++;
        }
        else {
            if (size == 1 && head == null) {
                this.head = this.tail;
            }
            temp.prev = this.tail;
            temp.prev.next=temp;
            this.tail = temp;
            size++;
        }
    }

    // TODO: Delete the element from the start of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtHead() throws EmptyListE{
        E data=null;
        if(size==0){
            throw new EmptyListE();
        }
        else if(size==1){
            data=head.getData();
            head=null;
            tail=null;
            size--;
            return data;
        }
        else if(size==2){
            data=head.getData();
            head=tail;
            size--;
        }
        else{
            data=head.getData();
            this.head=head.next;
            head.prev=null;
            size--;

        }
        return data;
    }



    // TODO: Delete the element from the end of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtTail() throws EmptyListE{
        E data=null;
        if(size==0){
            throw new EmptyListE();
        }
        else if(size==1){
            data=head.getData();
            head=null;
            tail=null;
            size--;
            return data;
        }
        else if(size==2){
            data=tail.getData();
            tail=head;
            size--;
        }
        else{
            data=tail.getData();
            this.tail=this.tail.prev;
            this.tail.next=null;
            size--;

        }
        return data;
    }

    // TODO: Get the element at some position. If it's not possible, throw an IndexOutOfBoundsException
    E get (int index) throws IndexOutOfBoundsException{
        NodeDL<E> temp=head;
        if(index>=size){
            throw new IndexOutOfBoundsException();
        }
        for(int i=0;i<index;i++){
            temp=temp.next;
        }
        return temp.getData();
    }

    // TODO: Search the DoublyLinkedList for elem. If not found, return -1;
    public int search(E elem){
        NodeDL<E> temp=head;
        for(int i=0;i<size;i++){
            if(temp.getData().equals(elem)){
                return i;
            }
            temp=temp.next;
        }
        return -1;

    }

    // TODO: When passed some object, return true if it's a DoublyLinkedList, has the same elements in the same order.
    public boolean equals(Object o){
        if(o instanceof DoublyLinkedList){
            DoublyLinkedList<E> oTemp = (DoublyLinkedList<E>) o;
            if(size==oTemp.size){
                for(int i=0;i<size;i++){
                    if(!get(i).equals(oTemp.get(i))){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;

    }

    public String toString(){
        String ret = "";
        NodeDL<E> temp = head;
        for(int i = 0; i < size; i++){
            ret += temp.data + " ";
            temp = temp.next;
        }
        return ret;
    }
}
