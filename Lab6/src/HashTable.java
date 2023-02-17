import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private final int INITIAL_CAPACITY = 11;
    private List<Entry> entries;
    private int size;
    private int capacity = INITIAL_CAPACITY;

    public HashTable() {
        entries=new ArrayList<Entry>(capacity);
        for(int i=0;i<capacity;i++){
            entries.add(null);
        }
        size=0;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public int getSize() {
        return size;
    }

    /**
     * Generates the hash (index) for the given key and the number of collisions encountered. This should be computed
     * using quadratic probing; please implement the function according to the following requirements:
     *  ('keyHashCode' + collisions^2) % capacity
     */
    private int hash(String key, int collisions) {
        int code=key.hashCode();
        code+=(collisions*collisions);
        code=code%capacity;
        return code;
    }

    public int getCapacity(){
        return capacity;
    }

    /**
     * Inserts the given key-value pair into the HashTable or updates the value of the current Entry if the key is
     * already stored.
     */
    public void put(String key, String value) {
        Entry entry=new Entry(key,value);
        int collisions=0;
        int code=hash(key,collisions);
        while(entries.get(code)!=null&&entries.get(code).getType()==Entry.Type.KV_PAIR){
            if(entries.get(code).getKey().equals(key)){
                entries.get(code).setValue(value);
                return;
            }
            collisions++;
            code=hash(key,collisions);
        }
        entries.set(code,entry);
        size++;

        if(1.0*size/capacity>=.5){
            rehash();
        }
    }

    /**
     * Gets the value from the Entry in the HashTable containing the given key and returns it. Returns null if the key
     * was not found.
     */
    public String get(String key) {
        ArrayList<Integer> checked=new ArrayList<Integer>();
        for(int i=0;i<entries.size();i++){
            checked.add(i);
        }
        int collisions=0;
        int code=hash(key,collisions);
        while(!checked.isEmpty()&&entries.get(code)!=null&&entries.get(code).getType()==Entry.Type.KV_PAIR){
            if(entries.get(code).getKey().equals(key)){
                return entries.get(code).getValue();
            }
            checked.remove((Integer)code);
            collisions++;
            code=hash(key,collisions);
        }
        return null;

    }

    public int getCode(String key) {
        ArrayList<Integer> checked=new ArrayList<Integer>();
        for(int i=0;i<entries.size();i++){
            checked.add(i);
        }
        int collisions=0;
        int code=hash(key,collisions);
        while(!checked.isEmpty()&&entries.get(code)!=null&&entries.get(code).getType()==Entry.Type.KV_PAIR){
            if(entries.get(code).getKey().equals(key)){
                return code;
            }
            checked.remove((Integer)code);
            collisions++;
            code=hash(key,collisions);
        }
        return -1;

    }

    /**
     * If the key is found in the HashTable, marks the corresponding entry as a deleted (a tombstone).
     */
    public void remove(String key) {
        int code=getCode(key);
        if(code!=-1){
            entries.get(code).markTombstone();
            size--;
        }
    }

    /**
     * Increase the capacity of the 'entries' ArrayList (double the capacity then find the next prime) and puts the
     * key-value pairs of the smaller 'entries' into the
     */
    private void rehash() {
        ArrayList<Entry> temp=new ArrayList<Entry>();
        for(int i=0;i<entries.size();i++){
            if(entries.get(i)!=null&&entries.get(i).getType()==Entry.Type.KV_PAIR){
                temp.add(entries.get(i));
            }
        }
        capacity=nextPrime(capacity*2);
        System.out.println(capacity);
        entries=new ArrayList<Entry>(capacity);
        size=0;
        for(int i=0;i<capacity;i++){
            entries.add(null);
        }
        for(int i=0;i<temp.size();i++){
            put(temp.get(i).getKey(),temp.get(i).getValue());
        }
    }

    /**
     * Finds the prime immediately following the given number.
     */
    private int nextPrime(int number) {
        // https://stackoverflow.com/a/57904191
        BigInteger b = new BigInteger(String.valueOf(number));
        return (int) Long.parseLong(b.nextProbablePrime().toString());
    }
}
