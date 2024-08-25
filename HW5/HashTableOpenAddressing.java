import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class HashTableOpenAddressing<K, V> extends Dictionary<K,V>{

    private int capacity;  // size of the table
    private int size=0;  // number of elements in the table
    private int previousPrime; //store prev prime so that it is not calculated again and again in double hashing.
    private int mode;

    public static int LINEARPROBING = 1;
    public static int QUADRATICPROBING = 2;
    public static int DOUBLEHASHING = 3;
    private double loadFactor;
    private Entry<K, V>[] table;

    public HashTableOpenAddressing() {
        this(DOUBLEHASHING, 11, 0.75);  // default initial capacity of 10
        this.table = (Entry<K, V>[]) new Entry[this.capacity];
    }

    public HashTableOpenAddressing(int mode) {
        this( mode, 11, 0.75);
        this.table = (Entry<K, V>[]) new Entry[this.capacity];
    }

    public HashTableOpenAddressing(int capacity, double loadFactor) {
        this(DOUBLEHASHING, capacity, loadFactor);
        if(!isPrime(capacity)) {
            this.capacity = nextPrime(capacity);
        }
        loadFactorCheck();
        this.table = (Entry<K, V>[]) new Entry[this.capacity];
    }



    /*
    TODO:
    This constructor takes a mode, capacity, loadFactor, and sets those variables + relevant variables
    according to such. Additionally, it will set up the table according to the capacity.
    If the mode is DOUBLEHASHING, please calculate the previousPrime and set it.
     */
    public HashTableOpenAddressing(int mode, int capacity, double loadFactor) {
        if(mode==1){
            this.mode=1;
            this.capacity=capacity;
            this.loadFactor=loadFactor;
        }
        else if(mode==2){
            this.mode=2;
            this.capacity=capacity;
            this.loadFactor=loadFactor;
        }
        else if(mode==3){
            this.mode=3;
            this.capacity=capacity;
            this.loadFactor=loadFactor;
            if(!isPrime(this.capacity)) {
                this.capacity = nextPrime(this.capacity+1);
            }
            this.previousPrime=previousPrime(capacity-1);
        }
        if(!isPrime(this.capacity)) {
            this.capacity = nextPrime(this.capacity+1);
        }
        loadFactorCheck();
        this.table = (Entry<K, V>[]) new Entry[this.capacity];
    }



    public void loadFactorCheck(){
        if((1.0/this.capacity)>=this.loadFactor){
            this.loadFactor=(1.0/this.capacity)+.1;
        }
    }

    private int previousPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number--;
        }
    }


    // TODO:
    //  second hash should be prevPrime - (key % prevPrime)...shouldn't be negative
    private int hash2(K key) {
        this.previousPrime=previousPrime(capacity/2);
        return abs(previousPrime(capacity/2)-(hash(key)%previousPrime(capacity/2)));
    }


    // TODO: gets the next index given the index and the offset. Please take into account the mode.
    private int getNextIndex(K key, int offset) {
        if(mode==QUADRATICPROBING) {
            return abs((hash(key) + (offset * offset)) % capacity);
        }
        else if(mode==LINEARPROBING){
            return abs((hash(key)+offset)%capacity);
        }
        else if(mode==DOUBLEHASHING){
            return abs((hash(key)+offset*hash2(key))%capacity);
        }
        return -1;
    }

    // TODO:
    //  Put a key, value pair into the table.
    //  If the key already exists/inactive, override it. Else, put it into the table.
    //  Throw a RuntimeException if there is an infinite loops.
    public void put(K key, V value) {
        if(mode==DOUBLEHASHING){
            putDoubleHashing(key,value);
        }
        else if(mode==LINEARPROBING||mode==QUADRATICPROBING){
            putProbing(key,value);
        }
        if((1.0*size/capacity)>=loadFactor){
            resize();
        }
    }
    public void putDoubleHashing(K key, V value){
        int count=0;
        int index=getNextIndex(key,count);
        ArrayList<Integer> temp=new ArrayList<Integer>();
        for(int i=0;i<capacity;i++){
            temp.add((Integer)i);
        }
        while(table[index]!=null&&table[index].getActive()){
            if(table[index].getKey().equals(key)){
                table[index].setValue(value);
                table[index].setActive(true);
                return;
            }
            temp.remove((Integer)index);
            count++;
            index=getNextIndex(key,count);
            if((double)temp.size()/capacity<.5){
                throw new RuntimeException();
            }
        }
        table[index]=new Entry<K,V>(key,value);
        size++;

    }

    public void putProbing(K key, V value){
        if(mode==LINEARPROBING){
            int count=0;
            int index=getNextIndex(key,count);

            while(table[index]!=null&&table[index].getActive()){
                if(table[index].getKey().equals(key)){
                    table[index].setValue(value);
                    table[index].setActive(true);
                    return;
                }
                count++;
                index=getNextIndex(key,count);
                if(count==capacity){
                    throw new RuntimeException();
                }
            }
            table[index]=new Entry<K,V>(key,value);
            size++;
        }
        if(mode==QUADRATICPROBING){
            int count=0;
            ArrayList<Integer> temp=new ArrayList<Integer>();
            for(int i=0;i<this.capacity;i++){
                temp.add((Integer)i);
            }
            int index=getNextIndex(key,count);

            while(table[index]!=null&&table[index].getActive()){
                if(table[index].getKey().equals(key)){
                    table[index].setValue(value);
                    table[index].setActive(true);
                    return;
                }
                temp.remove((Integer)index);
                count++;
                index=getNextIndex(key,count);
                if((1.0*temp.size()/this.capacity)<.5){
                    throw new RuntimeException();
                }
            }
            table[index]=new Entry<K,V>(key,value);
            size++;
        }
    }

    // TODO:
    //  Retrieves the value of a key in the table.
    //  If there is an infinite loop, throw a RuntimeException.
    //  Return null if not there.
    public V get(K key) {
        if(mode==DOUBLEHASHING){
            return getDoubleHashing(key);
        }
        else if(mode==LINEARPROBING||mode==QUADRATICPROBING){
            return getProbing(key);
        }
        return null;
    }

    public V getDoubleHashing(K key){
        int count=0;
        int index=getNextIndex(key,count);
        ArrayList<Integer> temp=new ArrayList<Integer>();
        for(int i=0;i<capacity;i++){
            temp.add((Integer)i);
        }
        while(table[index]!=null){
            if(table[index].getActive()&&table[index].getKey().equals(key)){
                return table[index].getValue();
            }
            temp.remove((Integer)index);
            count++;
            index=getNextIndex(key,count);
            if((double)temp.size()/capacity<.5){
                throw new RuntimeException();
            }
        }
        return null;
    }
    public V getProbing(K key){
        if(mode==LINEARPROBING){
            int count=0;
            int index=getNextIndex(key,count);

            while(table[index]!=null){
                if(table[index].getActive()&&table[index].getKey().equals(key)){
                    return table[index].getValue();
                }
                count++;
                index=getNextIndex(key,count);
                if(count==capacity){
                    throw new RuntimeException();
                }
            }
            return null;
        }
        if(mode==QUADRATICPROBING){
            int count=0;
            ArrayList<Integer> temp=new ArrayList<Integer>();
            for(int i=0;i<capacity;i++){
                temp.add((Integer)i);
            }
            int index=getNextIndex(key,count);

            while(table[index]!=null){
                if(table[index].getActive()&&table[index].getKey().equals(key)){
                    return table[index].getValue();
                }
                temp.remove((Integer)index);
                count++;
                index=getNextIndex(key,count);
                if((double)temp.size()/capacity<.5){
                    throw new RuntimeException();
                }
            }
            return null;
        }
        return null;
    }

    // TODO: Searches the table to see if the key exists or not.
    public boolean containsKey(K key) {
        if(get(key)==null){
            return false;
        }
        return true;
    }

    // TODO:
    //  Set the key as inactive if it exists in the table. Return true.
    //  If there is no key, return false.
    //  If there's an infinite loop, throw a RuntimeException.
    public boolean remove(K key) {
        if(mode==DOUBLEHASHING){
            return removeDoubleHashing(key);
        }
        else if(mode==LINEARPROBING||mode==QUADRATICPROBING){
            return removeProbing(key);
        }
        return false;
    }

    public boolean removeDoubleHashing(K key){
        int count=0;
        int index=getNextIndex(key,count);
        ArrayList<Integer> temp=new ArrayList<Integer>();
        for(int i=0;i<capacity;i++){
            temp.add((Integer)i);
        }
        while(table[index]!=null&&table[index].getActive()){
            if(table[index].getKey().equals(key)){
                if(table[index].getActive()) {
                    table[index].setActive(false);
                    size--;
                    return true;
                }
                else{
                    continue;
                }

            }
            temp.remove((Integer)index);
            count++;
            index=getNextIndex(key,count);
            if((double)temp.size()/capacity<.5){
                throw new RuntimeException();
            }
        }
        return false;
    }

    public boolean removeProbing(K key){
        if(mode==LINEARPROBING){
            int count=0;
            int index=getNextIndex(key,count);

            while(table[index]!=null){
                if(table[index].getKey().equals(key)){
                    table[index].setActive(false);
                    size--;
                    return true;
                }
                count++;
                index=getNextIndex(key,count);
                if(count==capacity){
                    throw new RuntimeException();
                }
            }
            return false;
        }
        if(mode==QUADRATICPROBING){
            int count=0;
            ArrayList<Integer> temp=new ArrayList<Integer>();
            for(int i=0;i<capacity;i++){
                temp.add((Integer)i);
            }
            int index=getNextIndex(key,count);

            while(table[index]!=null){
                if(table[index].getKey().equals(key)&&table[index].getActive()){
                    if(table[index].getActive()) {
                        table[index].setActive(false);
                        size--;
                        return true;
                    }
                    else{
                        continue;
                    }
                }
                temp.remove((Integer)index);
                count++;
                index=getNextIndex(key,count);
                if((double)temp.size()/capacity<.5){
                    throw new RuntimeException();
                }
            }
            return false;
        }
        return false;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // TODO:
    //  Calculate the absolute hash of the key. Do not overthink this.
    private int hash(K key) {
        return key.hashCode();
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
        for(Entry<K,V> e:table){
            if(e!=null&&e.getActive()){
                temp.add(e);
            }
        }

        this.capacity = nextPrime(capacity*2);
        this.size=0;
        this.table = (Entry<K, V>[]) new Entry[capacity];
        for(Entry<K,V> e:temp){
            put(e.getKey(),e.getValue());
        }
    }

    public int capacity(){
        return this.capacity;
    }

    public double getLoadFactor(){
        return this.loadFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;

        for (Entry<K, V> entry : table) {
            sb.append(index + ": ");
            index++;
            if (entry != null&&entry.getActive()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            sb.append(";");
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    private class Entry<K, V> {
        private K key;
        private V value;

        private boolean isActive;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            isActive = true;
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

        public boolean getActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
    }

    public static void main(String []args ) {
        HashTableOpenAddressing<Integer, Integer> hashTable = new HashTableOpenAddressing<>(DOUBLEHASHING, 10, .75);


        for (int i = 0; i < 280; i += 10) {
            hashTable.put(i, i);

            System.out.println(hashTable.get(i));
            System.out.println(hashTable);
        }
        for (int i = 0; i < 280; i += 10) {
            hashTable.remove(i);

            System.out.println(hashTable.get(i));
            System.out.println(hashTable);
        }
        System.out.println(hashTable);
    }

}
