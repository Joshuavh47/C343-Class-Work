import java.lang.IndexOutOfBoundsException;


public class MyArrayList{
    private int capacity;
    private int size;
    private int[] list;

    // TODO: default constructor - store 10 elements with nothing in the array
    private int[] arr;
    public MyArrayList(){
        arr=new int[10];


    }

    // TODO: secondary constructor - store some capacity elements with nothing in the array
    public MyArrayList(int capacity){
        arr=new int[capacity];
    }

    // TODO: insert - insert some value at the index; if the index is not possible, throw the exception
    // Resize to double the capacity if array is full.
    public void insert(int index, int value) throws IndexOutOfBoundsException{
        if(index<0){
            throw new IndexOutOfBoundsException();
        }
        else if(index>arr.length){
            throw new IndexOutOfBoundsException();
        }
        else{
            int[] newArr=new int[arr.length+1];
            for(int i=0;i<index;i++){
                newArr[i]=arr[i];
            }
            newArr[index]=value;
            for(int i=index+1;i<newArr.length;i++){
                newArr[i]=arr[i-1];
            }
            arr=newArr;
        }
    }

    // TODO: delete - deletes a value at the index; if index is not possible throw the exception
    // No empty spaces in the array should be left between elements
    public void delete(int index) throws IndexOutOfBoundsException{
        if(index<0){
            throw new IndexOutOfBoundsException();
        }
        else if(index>=arr.length){
            throw new IndexOutOfBoundsException();
        }
        else if(arr.length>1){
            int[] newArr=new int[arr.length-1];
            for(int i=0;i<index;i++){
                newArr[i]=arr[i];
            }
            for(int i=index+1;i<newArr.length;i++){
                newArr[i-1]=arr[i];
            }
            arr=newArr;
        }
        else if(arr.length==1){
            arr=new int[10];
        }
    }

    // TODO: get - returns the value at the index; if index not possible throw exception
    public int get(int index) throws IndexOutOfBoundsException{
        if(index<0){
            throw new IndexOutOfBoundsException();
        }
        else if(index>=arr.length){
            throw new IndexOutOfBoundsException();
        }
        else{
            return arr[index];
        }
    }

    // TODO: search - searches for the value and returns its found index; if not found return -1
    public int search(int value){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }

}
