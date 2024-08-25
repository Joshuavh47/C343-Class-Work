import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class HashTableWithChaining<K, V> extends Dictionary<K,V>{

    private int capacity;  // size of the table
    private int size=0;  // number of elements in the table

    private double loadFactor;
    private List<LinkedList<Entry<K, V>>> table;  // hash table

    // Entry class to hold key-value pairs
    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public HashTableWithChaining() {
        this(11, 0.75);  // default initial capacity of 10
        this.table=new ArrayList<LinkedList<Entry<K,V>>>();
        for(int i=0;i<capacity;i++){
            table.add(new LinkedList<Entry<K,V>>());
        }

    }

    public HashTableWithChaining(int capacity) {
        this( capacity, 0.75);
        this.table=new ArrayList<LinkedList<Entry<K,V>>>();
        for(int i=0;i<capacity;i++){
            table.add(new LinkedList<Entry<K,V>>());
        }
    }

    /*
    TODO:
    This constructor takes a capacity and loadFactor, and sets those variables + relevant variables
    according to such. Additionally, it will set up the table according to the capacity.
     */
    public HashTableWithChaining(int capacity, double loadFactor) {
        this.capacity=capacity;
        this.loadFactor=loadFactor;
        this.table=new ArrayList<LinkedList<Entry<K,V>>>();
        for(int i=0;i<capacity;i++){
            table.add(new LinkedList<Entry<K,V>>());
        }
    }
    public double getLoadFactor(){
        return loadFactor;
    }

    // TODO:
    //  Put a key, value pair into the table.
    //  If the key already exists, update it with the new value.
    //  If there is no key at that index, add it into the table.
    //  Resize when the size is > the loadFactor * capacity.
    //  Remember that multiple keys can exist at the same index.
    public void put(K key, V value) {
        int index=abs(hash(key)%capacity);
        boolean found=false;
        for(Entry<K,V> e:table.get(index)){
            if(e.getKey().equals(key)){
                e.setValue(value);
                found=true;
                break;
            }
        }
        if(!found){
            table.get(index).add(new Entry<K,V>(key,value));
            size++;
        }
        if((double)size/capacity>=loadFactor){
            resize();
        }

    }

    private boolean isPrime(int number) {
        for( int i = 2; i <= number / 2; i++ ) {
            if( number % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number++;
        }
    }

    // TODO:
    //  Set the capacity to the nextPrime of the capacity doubled.
    //  Calculate the previousPrime and set up the new table with the old tables'
    //  contents now hashed to the new.
    private void resize() {
        ArrayList<Entry<K,V>> temp=new ArrayList<Entry<K,V>>();
        for(LinkedList<Entry<K,V>> ll:table){
            for(Entry<K,V> e:ll){
                if(e!=null) {
                    temp.add(e);
                }
            }
        }
        this.capacity=nextPrime(capacity*2);
        size=0;
        table=new ArrayList<LinkedList<Entry<K,V>>>();
        for(int i=0;i<capacity;i++){
            table.add(new LinkedList<Entry<K,V>>());
        }
        for(Entry<K,V> e:temp){
            this.put(e.getKey(),e.getValue());
        }
    }


    // TODO:
    //  Retrieves the value of a key in the table.
    //  Return null if not there.
    public V get(K key) {
        int index=abs(hash(key)%capacity);
        for(Entry<K,V> e:table.get(index)){
            if(e.getKey().equals(key)){
                return e.getValue();
            }
        }
        return null;
    }

    // TODO: Searches the table to see if the key exists or not.
    public boolean containsKey(K key) {
        if(get(key)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    // TODO:
    //  Remove the entry under that key. Return true.
    //  If there is no key, return false.
    public boolean remove(K key) {
        int index=abs(hash(key)%capacity);
        for(Entry<K,V> e:table.get(index)){
            if(e.getKey().equals(key)){
                table.get(index).remove(e);
                size--;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (LinkedList<Entry<K, V>> list : table) {
            list.clear();
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // TODO: Calculate the absolute hash of the key. Do not overthink this.
    private int hash(K key) {
        return key.hashCode();
    }

    public int capacity(){
        return this.capacity;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;
        for (LinkedList<Entry<K, V>> list : table) {
            if(list.size() > 0 ) {
                sb.append(index + ": ");
                for (Entry<K, V> entry : list) {
                    sb.append(entry.getKey() + "=" + entry.getValue() + ",");
                }
                index++;
                sb.append(";");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }


    public static void main(String []args ) {
        HashTableWithChaining<String, Integer> hashTable = new HashTableWithChaining<>();

        hashTable.put("Hi", 2);
        hashTable.put("Ih", 2);
        hashTable.put("Hit", 2);
        hashTable.put("Him", 20);
        hashTable.put("His", 1);
        hashTable.put("Hiasdasd", 2);
        hashTable.put("Hiasdasds", 1);
        hashTable.put("Hiasdasadsd", 2);
        hashTable.put("H12is", 1);
        hashTable.put("H123iasdasd", 2);
        hashTable.put("Hita123s1d3asads", 2);
        System.out.println(hashTable);

    }
}

