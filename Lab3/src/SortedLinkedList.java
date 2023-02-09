import java.lang.IndexOutOfBoundsException;

public class SortedLinkedList {

    /**
     * The 0th indexed Node in the list.
     */
    Node head;

    /**
     * Creates a SortedLinkedList.
     */
    public SortedLinkedList() {
        head = null;
    }

    /**
     * Inserts the given data at a location that maintains sorted order (ascending).
     * @param data The value to be inserted into the list.
     */
    public void insertSorted(int data) {

        boolean inserted=false;
        Node cur=head;
        if(cur==null) {
            head=new Node(data,null);
            cur=head;
            inserted=true;
        }
        if(cur.data>data){
            head=new Node(data,head);
            inserted=true;
        }
        cur=head;
        while(cur.next!=null&&!inserted){

            if(cur.data<=data&&cur.next.data>=data){
                Node n=new Node(data,cur.next);
                cur.next=n;
                inserted=true;
                break;
            }
            cur=cur.next;
        }
        cur=head;
        if(!inserted){
            while(true){
                if(cur.next==null){
                    cur.next=new Node(data,null);
                    break;
                }
                cur=cur.next;
            }
        }
    }

    /**
     * Deletes the Node at the given index. Throws an IndexOutOfBoundException if index
     * is negative or too large.
     * @param idx Index of the int to be deleted.
     */
    public void delete(int idx) {
        int count=0;
        Node cur=head;
        while(cur!=null){
            count++;
            cur=cur.next;
        }

        if(idx<0||idx>=count){
            throw new IndexOutOfBoundsException();
        }
        if(idx==0&&count==1){
            head=null;
            cur=null;
        }
        else if(idx==0&&count>1){
            head=head.next;
        }

        else{

            cur=this.head;

            for(int i=0;i<idx-1;i++){
                cur=cur.next;
            }
            if(count-idx>=2){
                cur.next=cur.next.next;
            }
            else{
                cur.next=null;
            }
        }
    }

    /**
     * Gets the int at the given index. Throws an IndexOutOfBoundException if index
     * is negative or too large.
     * @param idx Index of the int to be return.
     * @return The int at the given index
     */
    public int get(int idx) {
        int count=0;
        Node cur=head;
        while(cur!=null){
            count++;
            cur=cur.next;
        }

        if(idx<0||idx>=count){
            throw new IndexOutOfBoundsException();

        }
        cur=head;
        for(int i=0;i<idx;i++){
            cur=cur.next;
        }
        return cur.data;
    }

    /**
     * Searches for the given int and returns its index if found. If the int is not
     * found, returns -1.
     * @param data The int to search for.
     * @return The data at the given index
     */
    public int search(int data) {
        int count=0;
        Node cur=head;

        while(cur!=null){
            if(cur.data==data){
                return count;
            }
            cur=cur.next;
            count++;
        }
        return -1;
    }

    @Override
    public String toString() {
        return printList();
    }

    /**
     * A recursive helper for toString that generates a String representation of this.
     * @return A String representation of the this.
     */
    private String printList() {
        String listStr = "";
        String delimiter = ", ";
        Node cur = head;
        while (cur != null) {
            listStr += cur.data + delimiter;
            cur = cur.next;
        }

        return listStr.substring(0, listStr.length()-delimiter.length());
    }
}
