import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class HashTableTests {
    @Test
    public void put(){
        HashTable h=new HashTable();
        h.put("a","a1");
        h.put("b","b1");
        h.put("c","c1");
        h.put("d","d1");
        h.put("e","e1");
        h.put("f","f1");
        h.put("g","g1");
        h.put("h","h1");
        h.put("i","i1");
        h.put("j","j1");
        h.put("k","k1");
        h.put("l","l1");
        h.put("m","m1");
        h.put("n","n1");
        h.put("o","o1");
        h.put("p","p1");
        h.put("q","q1");
        h.put("r","r1");
        assertEquals("a1",h.get("a"));
        assertEquals("b1",h.get("b"));
        assertEquals("c1",h.get("c"));
        assertEquals("d1",h.get("d"));
        assertEquals("e1",h.get("e"));
        assertEquals("f1",h.get("f"));
        assertEquals("g1",h.get("g"));
        assertEquals("h1",h.get("h"));
        assertEquals("i1",h.get("i"));
        assertEquals("j1",h.get("j"));
        assertEquals("k1",h.get("k"));
        assertEquals("l1",h.get("l"));
        assertEquals("m1",h.get("m"));
        assertEquals("n1",h.get("n"));
        assertEquals("o1",h.get("o"));
        assertEquals("p1",h.get("p"));
        assertEquals("q1",h.get("q"));
        assertEquals("r1",h.get("r"));

        //asserts that changing a key that is present in the HashTable actually changes the value
        h.put("a", "a2");
        assertEquals("a2",h.get("a"));

        //makes sure that putting another entry into the HashTable edits the value of the corresponding key instead of
        //adding a new entry altogether
        ArrayList<Entry> temp=(ArrayList<Entry>)h.getEntries();
        int aCount=0;
        for(int i=0;i<temp.size();i++){
            if(temp.get(i)!=null&&temp.get(i).getKey().equals("a")){
                aCount++;
            }
        }
        assertEquals(1,aCount);


    }

    @Test
    public void get(){
        HashTable h=new HashTable();
        h.put("a","a1");
        h.put("b","b1");
        h.put("c","c1");
        h.put("d","d1");
        h.put("e","e1");
        h.put("f","f1");
        h.put("g","g1");
        h.put("h","h1");
        h.put("i","i1");
        h.put("j","j1");
        h.put("k","k1");
        h.put("l","l1");
        h.put("m","m1");
        h.put("n","n1");
        h.put("o","o1");
        h.put("p","p1");
        h.put("q","q1");
        h.put("r","r1");
        assertEquals("a1",h.get("a"));
        assertEquals("b1",h.get("b"));
        assertEquals("c1",h.get("c"));
        assertEquals("d1",h.get("d"));
        assertEquals("e1",h.get("e"));
        assertEquals("f1",h.get("f"));
        assertEquals("g1",h.get("g"));
        assertEquals("h1",h.get("h"));
        assertEquals("i1",h.get("i"));
        assertEquals("j1",h.get("j"));
        assertEquals("k1",h.get("k"));
        assertEquals("l1",h.get("l"));
        assertEquals("m1",h.get("m"));
        assertEquals("n1",h.get("n"));
        assertEquals("o1",h.get("o"));
        assertEquals("p1",h.get("p"));
        assertEquals("q1",h.get("q"));
        assertEquals("r1",h.get("r"));

        h.remove("a");
        assertEquals(null,h.get("a"));
        //tests to make sure removed pairs are marked as tombstones
        ArrayList<Entry> temp=(ArrayList<Entry>)h.getEntries();
        int indexOfA = 0;
        for(int i=0;i<temp.size();i++){
            if(temp.get(i)!=null&&temp.get(i).getKey().equals("a")){
                indexOfA=i;
                break;
            }
        }
        boolean dead=false;
        boolean keyIsA=false;
        if(temp.get(indexOfA).getType()==Entry.Type.TOMBSTONE){
            dead=true;
        }
        if(temp.get(indexOfA).getKey().equals("a")){
            keyIsA=true;
        }
        //asserts that tombstone is marked and that it is not reading some random entry
        assertEquals(true,dead&&keyIsA);
    }

    @Test
    public void remove(){
        HashTable h=new HashTable();
        h.put("a","a1");
        h.put("b","b1");
        h.put("c","c1");
        h.put("d","d1");
        h.put("e","e1");
        h.put("f","f1");
        h.put("g","g1");
        h.put("h","h1");
        h.put("i","i1");
        h.put("j","j1");
        h.put("k","k1");
        h.put("l","l1");
        h.put("m","m1");
        h.put("n","n1");
        h.put("o","o1");
        h.put("p","p1");
        h.put("q","q1");
        h.put("r","r1");
        assertEquals("a1",h.get("a"));
        assertEquals("b1",h.get("b"));
        assertEquals("c1",h.get("c"));
        assertEquals("d1",h.get("d"));
        assertEquals("e1",h.get("e"));
        assertEquals("f1",h.get("f"));
        assertEquals("g1",h.get("g"));
        assertEquals("h1",h.get("h"));
        assertEquals("i1",h.get("i"));
        assertEquals("j1",h.get("j"));
        assertEquals("k1",h.get("k"));
        assertEquals("l1",h.get("l"));
        assertEquals("m1",h.get("m"));
        assertEquals("n1",h.get("n"));
        assertEquals("o1",h.get("o"));
        assertEquals("p1",h.get("p"));
        assertEquals("q1",h.get("q"));
        assertEquals("r1",h.get("r"));
        h.remove("a");
        h.remove("b");
        h.remove("c");
        h.remove("d");
        h.remove("e");
        h.remove("f");
        h.remove("g");
        h.remove("h");
        h.remove("i");
        h.remove("j");
        h.remove("k");
        h.remove("l");
        h.remove("m");
        h.remove("n");
        h.remove("o");
        h.remove("p");
        h.remove("q");
        h.remove("r");
        assertEquals(null,h.get("a"));
        assertEquals(null,h.get("b"));
        assertEquals(null,h.get("c"));
        assertEquals(null,h.get("d"));
        assertEquals(null,h.get("e"));
        assertEquals(null,h.get("f"));
        assertEquals(null,h.get("g"));
        assertEquals(null,h.get("h"));
        assertEquals(null,h.get("i"));
        assertEquals(null,h.get("j"));
        assertEquals(null,h.get("k"));
        assertEquals(null,h.get("l"));
        assertEquals(null,h.get("m"));
        assertEquals(null,h.get("n"));
        assertEquals(null,h.get("o"));
        assertEquals(null,h.get("p"));
        assertEquals(null,h.get("q"));
        assertEquals(null,h.get("r"));


        //tests to make sure removed pairs are marked as tombstones
        ArrayList<Entry> temp=(ArrayList<Entry>)h.getEntries();
        int indexOfA = 0;
        for(int i=0;i<temp.size();i++){
            if(temp.get(i)!=null&&temp.get(i).getKey().equals("a")){
                indexOfA=i;
                break;
            }
        }
        boolean dead=false;
        boolean keyIsA=false;
        if(temp.get(indexOfA).getType()==Entry.Type.TOMBSTONE){
            dead=true;
        }
        if(temp.get(indexOfA).getKey().equals("a")){
            keyIsA=true;
        }
        //asserts that tombstone is marked and that it is not reading some random entry
        assertEquals(true,dead&&keyIsA);
    }

    @Test
    public void rehash(){
        //asserts that the capacity doubles and finds the next prime value once the load factor >= .5
        HashTable h=new HashTable();
        h.put("a","a1");
        assertEquals(1,h.getSize());
        assertEquals(11,h.getCapacity());
        h.put("b","b1");
        assertEquals(2,h.getSize());
        assertEquals(11,h.getCapacity());
        h.put("c","c1");
        assertEquals(3,h.getSize());
        assertEquals(11,h.getCapacity());
        h.put("d","d1");
        assertEquals(4,h.getSize());
        assertEquals(11,h.getCapacity());
        h.put("e","e1");
        assertEquals(5,h.getSize());
        assertEquals(11,h.getCapacity());
        h.put("f","f1");
        assertEquals(6,h.getSize());
        assertEquals(23,h.getCapacity());
        h.put("g","g1");
        assertEquals(7,h.getSize());
        assertEquals(23,h.getCapacity());
        h.put("h","h1");
        assertEquals(8,h.getSize());
        assertEquals(23,h.getCapacity());
        h.put("i","i1");
        assertEquals(9,h.getSize());
        assertEquals(23,h.getCapacity());
        h.put("j","j1");
        assertEquals(10,h.getSize());
        assertEquals(23,h.getCapacity());
        h.put("k","k1");
        assertEquals(11,h.getSize());
        assertEquals(23,h.getCapacity());
        h.put("l","l1");
        assertEquals(12,h.getSize());
        assertEquals(47,h.getCapacity());
        h.put("m","m1");
        assertEquals(13,h.getSize());
        assertEquals(47,h.getCapacity());
        h.put("n","n1");
        assertEquals(14,h.getSize());
        assertEquals(47,h.getCapacity());
        h.put("o","o1");
        assertEquals(15,h.getSize());
        assertEquals(47,h.getCapacity());
        h.put("p","p1");
        assertEquals(16,h.getSize());
        assertEquals(47,h.getCapacity());
        h.put("q","q1");
        assertEquals(17,h.getSize());
        assertEquals(47,h.getCapacity());
        h.put("r","r1");
        assertEquals(18,h.getSize());
        assertEquals(47,h.getCapacity());
        assertEquals("a1",h.get("a"));
        assertEquals("b1",h.get("b"));
        assertEquals("c1",h.get("c"));
        assertEquals("d1",h.get("d"));
        assertEquals("e1",h.get("e"));
        assertEquals("f1",h.get("f"));
        assertEquals("g1",h.get("g"));
        assertEquals("h1",h.get("h"));
        assertEquals("i1",h.get("i"));
        assertEquals("j1",h.get("j"));
        assertEquals("k1",h.get("k"));
        assertEquals("l1",h.get("l"));
        assertEquals("m1",h.get("m"));
        assertEquals("n1",h.get("n"));
        assertEquals("o1",h.get("o"));
        assertEquals("p1",h.get("p"));
        assertEquals("q1",h.get("q"));
        assertEquals("r1",h.get("r"));
    }
}
