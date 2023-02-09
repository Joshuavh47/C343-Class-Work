public class NodeDL<E> {
    E data;
    NodeDL<E> prev;
    NodeDL<E> next;

    public NodeDL(E elem) {
        this.data = elem;
    }

    public String toString() {
        return "" + this.data;
    }

    public E getData() {
        return data;
    }

    // TODO: Return whether the other has same type and same pointers
    public boolean equals(Object o){

        if(o instanceof NodeDL){
            NodeDL<E> other = (NodeDL<E>) o;
            if(this.toString().equals(other.toString())){
                return true;
            }
            return false;
        }
        return false;
    }
}
