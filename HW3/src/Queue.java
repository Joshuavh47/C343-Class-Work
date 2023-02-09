class EmptyQueueE extends Exception{}

public class Queue<E> {
    private DoublyLinkedList<E> q;
    private int size;

    // TODO: default constructor
    public Queue(){
        this.q=new DoublyLinkedList<E>();
    }

    // TODO: Put element at end of queue
    public void enqueue(E elem){
        q.insertAtTail(elem);
        size++;
    }

    // TODO: Return the head of the queue; If there's nothing to return then throw EmptyQueueE
    public E dequeue() throws EmptyQueueE {
        E data=null;
        if(size==0){
            throw new EmptyQueueE();
        }
        else{
            try{
                data=q.deleteAtHead();
                size--;
            }
            catch(EmptyListE e){
                e.printStackTrace();
            }
        }
        return data;
    }

    // TODO: Without affecting the queue, return the element at the top of the queue
    public E peek() throws IndexOutOfBoundsException{
        if(size==0){
            throw new IndexOutOfBoundsException();
        }
        else{
            return q.get(0);
        }
    }

    public int size() {
        return this.size;
    }

    // TODO: Checks if inputted is the same Queue
    public boolean equals(Object o){
        Queue<E> oTemp=(Queue<E>) o;
        if(o instanceof Queue){
            if(q.equals(oTemp.returnList())){
                return true;
            }
        }
        return false;
    }
    public DoublyLinkedList<E> returnList(){
        return q;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public String toString(){
        return "" + q.size();
    }
}
