import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class ArrayList<E extends Comparable> extends List<E> {
    private int size;
    private int capacity;
    private Object[] ls;

    // TODO: default: should create an arraylist that is capable of holding 10 element
    public ArrayList(){
        ls=new Object[10];
        this.size=0;
        this.capacity=10;
    }

    // TODO: second constructor - should create an arraylist that is capable of holding x element
    public ArrayList(int capacity){
        ls=new Object[capacity];
        this.capacity=capacity;
        this.size=0;
    }

    public int size(){
        return this.size;
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= this.size||index < 0){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.ls[index];
    }

    // TODO: insert --> takes some element and inserts it at the end of the arraylist, resizes to double capacity if no space
    public void add(E value){
        if(this.size==this.capacity){
            ls= Arrays.copyOf(ls,capacity*2);
            capacity*=2;
        }
        ls[size]=value;
        size++;
    }

    // TODO: delete - deletes an element at said index; moves elements such that there are no gaps in between them
    public void delete(int index) throws IndexOutOfBoundsException{
        if(index >= this.size||index < 0){
            throw new IndexOutOfBoundsException();
        }
        if(index==this.size-1){
            ls[index]=null;
            size--;
        }
        else if(index==0){
            ls=Arrays.copyOfRange(ls,1,size);
            size--;
        }
        else{
            Object[] arr=new Object[size-1];
            for(int i=0;i<size;i++){
                if(i<index){
                    arr[i]=ls[i];
                }
                if(i>index){
                    arr[i-1]=ls[i];
                }
            }
            ls=arr;
            size--;

        }
    }

    // TODO: searches one by one to find the element, if it doesn't exist then return -1
    public int search(E value){
        for(int i=0;i<this.size;i++){
            if(value.compareTo(ls[i])==0){
                return i;
            }
        }
        return -1;
    }
    public int getSize(){
        return size;
    }

    // TODO: given some other arraylist, compare it to see if it has the same contents
    public boolean equals(Object o){
        int count=0;
        ArrayList<E> arr=(ArrayList<E>) o;
        if(arr.getSize()!=this.size){
            return false;
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (arr.get(i).compareTo(ls[i]) == 0) {
                    count++;
                }
            }
            if (count == this.size && arr.getSize() == this.size) {
                return true;
            }
        }
        return false;
    }

    // to help you
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.size; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret;
    }

}
