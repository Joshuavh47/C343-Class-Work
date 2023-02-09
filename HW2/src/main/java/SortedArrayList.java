import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class SortedArrayList<E extends Comparable> extends List<E> {

    private int size;
    private int capacity;
    private Object[] ls;

    // TODO: default: should create a sortedarraylist that is capable of holding 10 element
    public SortedArrayList(){
        this.ls=new Object[10];
        this.size=0;
        this.capacity=10;
    }

    // TODO: second constructor - should create a sortedarraylist that is capable of holding x element that size
    public SortedArrayList(int capacity){
        ls=new Object[capacity];
        this.capacity=capacity;
        this.size=0;
    }

    public int size(){
        return this.size;
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= this.size||index<0){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.ls[index];
    }
    public int getSize(){
        return size;
    }

    // TODO: inserts element while maintaining the sorted order of the contents; resize to double capacity if no space
    public void add(E value) {

        if(capacity==size){
            ls= Arrays.copyOf(ls, capacity*2);
            capacity*=2;
        }
        if(size==0){
            ls[0]=value;
        }
        else{
            int index=-1;
            for(int i=0; i<size;i++){
                if(value.compareTo(ls[i])>0){
                    index=i;
                }
            }
            if(index==-1){
                Object[] temp1=Arrays.copyOfRange(ls,0,size);
                ls=new Object[size+1];
                ls[0]=value;
                for(int i=0;i<temp1.length;i++){
                    ls[i+1]=temp1[i];
                }

            }
            else{
                Object[] temp1=Arrays.copyOfRange(ls,0,index+1);
                Object[] temp2=Arrays.copyOfRange(ls,index+1,size);
                ls=new Object[capacity];
                for(int i=0;i<temp1.length;i++){
                    ls[i]=temp1[i];
                }
                ls[index+1]=value;
                for(int i=0;i<temp2.length;i++){
                    ls[i+1+temp1.length]=temp2[i];
                }

            }


        }
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

    // TODO: search - binary search O(log(n)) for the element; returns -1 if not found
    public int search(E value){
        if(size>=1) {
            return binarySearch(0, this.size-1, value);
        }
        else{
            return -1;
        }

    }
    int binarySearch(int low,int high, E value){
        int middle = low+((high-low)/2);
        if (high<low) {
            return -1;
        }
        if (value.compareTo(ls[middle])==0){
            return middle;
        }
        else if (value.compareTo(ls[middle])<0){
            return binarySearch(low,middle - 1,value);
        }
        else{
            return binarySearch(middle+1,high,value);
        }
    }

    // TODO: given some other sortedarraylist, compare it to see if it has the same contents (also in same order)
    public boolean equals(Object o){
        int count=0;
        SortedArrayList<E> arr=(SortedArrayList<E>) o;
        if(arr.size!=this.size){
            return false;
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (arr.get(i).compareTo(ls[i]) == 0) {
                    count++;
                }
            }
            if (count == this.size && arr.size == this.size) {
                return true;
            }
        }
        return false;
    }

    // helper
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.size; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret;
    }
}
