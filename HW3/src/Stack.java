class EmptyStackE extends Exception{}

public class Stack<E>{
    private DoublyLinkedList<E> st;
    private int size;

    // TODO: default constructor
    public Stack(){
        this.st=new DoublyLinkedList<E>();
        size=0;
    }

    // TODO: Push the element to the top of stack
    public void push(E elem){
        st.insertAtTail(elem);
        size++;
    }

    // TODO: Pop the element off the top of the stack. If nothing to pop, throw EmptyStackE
    public E pop() throws EmptyStackE {
        E data=null;
        if(size==0){
            throw new EmptyStackE();
        }
        else{
            try{
                data=st.deleteAtTail();
                size--;
            }
            catch(EmptyListE e){
                e.printStackTrace();
            }
        }
        return data;
    }

    // TODO: Without affecting the stack, return the element at the top of the stack
    public E peek() throws IndexOutOfBoundsException{
        if(size==0){
            throw new IndexOutOfBoundsException();
        }
        else {
            return st.get(size - 1);
        }
    }

    public int size() {
        return this.size;
    }

    // TODO: Check if some other object is the same Stack
    public boolean equals(Object o){
        Stack<E> oTemp=(Stack<E>) o;
        if(o instanceof Stack){
            if(oTemp.returnList().equals(st)){
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public DoublyLinkedList<E> returnList(){
        return st;
    }

    public String toString(){
        return st.toString();
    }

}
