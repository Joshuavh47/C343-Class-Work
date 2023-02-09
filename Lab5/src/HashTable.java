import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HashTable {
    private final int INITIAL_CAPACITY = 11;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private List<LinkedList<Entry>> entries;

    public HashTable() {
        this.entries=new ArrayList<LinkedList<Entry>>();
        for(int i=0;i<capacity;i++){
            entries.add(new LinkedList<Entry>());
        }
    }

    public List<LinkedList<Entry>> getEntries() {
        return entries;
    }

    public int getSize() {
        return size;
    }

    private int hash(String key) {
        return key.hashCode();
    }

    private int getBucketIndex(String key){
        int hashCode = hash(key);
        int index = hashCode % capacity;
        if(index<0){
            index*=-1;
        }
        return index;
    }

    public void put(String key, String value) {
        int index=getBucketIndex(key);
        int hashCode=hash(key);

        for(int i=0;i<entries.get(index).size();i++){
            if(hashCode==hash(entries.get(index).get(i).getKey())&&key.equals(entries.get(index).get(i).getKey())){
                entries.get(index).get(i).setValue(value);
                return;
            }
        }

        size++;
        Entry newEntry=new Entry(key,value);
        if(entries.get(index)==null){
            entries.set(index,new LinkedList<Entry>());
        }
        entries.get(index).add(newEntry);
        if((1.0*size)/capacity>=0.5){
            rehash();
        }

    }

    public String get(String key) {
        int index=getBucketIndex(key);
        int hashCode=hash(key);
        for(int i=0;i<entries.get(index).size();i++){
            if(hashCode==hash(entries.get(index).get(i).getKey())&&key.equals(entries.get(index).get(i).getKey())){
                return entries.get(index).get(i).getValue();
            }
        }
        return null;
    }

    public void remove(String key) {
        int index=getBucketIndex(key);
        int hashCode=hash(key);
        for(int i=0;i<entries.get(index).size();i++){
            if(hashCode==hash(entries.get(index).get(i).getKey())&&key.equals(entries.get(index).get(i).getKey())){
                entries.get(index).remove(i);
                size--;
                return;
            }
        }
    }

    private void rehash() {
        ArrayList<Entry> temp=new ArrayList<Entry>();
        for(int i=0;i<entries.size();i++){
            for(int j=0;j<entries.get(i).size();j++){
                if(entries.get(i).get(j)!=null){
                    temp.add(entries.get(i).get(j));
                }
            }
        }
        capacity*=2;
        entries=new ArrayList<LinkedList<Entry>>(capacity);
        for(int i=0;i<capacity;i++){
            entries.add(new LinkedList<Entry>());
        }
        size=0;
        for(int i=0;i<temp.size();i++){
            put(temp.get(i).getKey(),temp.get(i).getValue());
        }
    }

    public int getCapacity() {
        return capacity;
    }
}
