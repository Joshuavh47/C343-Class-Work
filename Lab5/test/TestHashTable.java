import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestHashTable {
    @Test
    public void put(){
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
        //this is when the HashTable rehashes
        assertEquals(22,h.getCapacity());
        h.put("g","g1");
        assertEquals(7,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("h","h1");
        assertEquals(8,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("i","i1");
        assertEquals(9,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("j","j1");
        assertEquals(10,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("k","k1");
        assertEquals(11,h.getSize());
        //the HashTable should rehash here as well because size/capacity>=.5
        assertEquals(44,h.getCapacity());
        h.put("l","l1");
        assertEquals(12,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("m","m1");
        assertEquals(13,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("n","n1");
        assertEquals(14,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("o","o1");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        //putting more than the capacity (11, 22) so this also indirectly tests the rehash method (multiple times)
        //this also tests the get method (obviously) as it is getting called in order to assertEquals, as well as the
        //getSize and getCapacity methods
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

        //testing that the size and capacity stay the same if a key is put in the HashTable that already exists.
        //size should stay at 15 and capacity should stay at 44 (due to rehashing and the load factor being >= .5)
        //because we are just "overwriting" the values for each key when we put entries in out HashTable in which the
        //keys already exist
        h.put("a","a2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("b","b2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("c","c2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("d","d2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("e","e2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("f","f2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("g","g2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("h","h2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("i","i2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("j","j2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("k","k2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("l","l2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("m","m2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("n","n2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("o","o2");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());

        assertEquals("a2",h.get("a"));
        assertEquals("b2",h.get("b"));
        assertEquals("c2",h.get("c"));
        assertEquals("d2",h.get("d"));
        assertEquals("e2",h.get("e"));
        assertEquals("f2",h.get("f"));
        assertEquals("g2",h.get("g"));
        assertEquals("h2",h.get("h"));
        assertEquals("i2",h.get("i"));
        assertEquals("j2",h.get("j"));
        assertEquals("k2",h.get("k"));
        assertEquals("l2",h.get("l"));
        assertEquals("m2",h.get("m"));
        assertEquals("n2",h.get("n"));
        assertEquals("o2",h.get("o"));
    }

    @Test
    public void remove(){
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
        //this is when the HashTable rehashes
        assertEquals(22,h.getCapacity());
        h.put("g","g1");
        assertEquals(7,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("h","h1");
        assertEquals(8,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("i","i1");
        assertEquals(9,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("j","j1");
        assertEquals(10,h.getSize());
        assertEquals(22,h.getCapacity());
        h.put("k","k1");
        assertEquals(11,h.getSize());
        //the HashTable should rehash here as well because size/capacity>=.5
        assertEquals(44,h.getCapacity());
        h.put("l","l1");
        assertEquals(12,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("m","m1");
        assertEquals(13,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("n","n1");
        assertEquals(14,h.getSize());
        assertEquals(44,h.getCapacity());
        h.put("o","o1");
        assertEquals(15,h.getSize());
        assertEquals(44,h.getCapacity());
        //putting more than the capacity (11, 22) so this also indirectly tests the rehash method
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

        //we test the size, which should be decreasing after each remove method is called for each corresponding Entry
        //key. but, the capacity stays the same because the only factor that changes the capacity (rehashing) is if
        //the load factor >=.5 (size/capacity>=.5)
        h.remove("a");
        assertEquals(14,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("a"));
        h.remove("b");
        assertEquals(13,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("b"));
        h.remove("c");
        assertEquals(12,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("c"));
        h.remove("d");
        assertEquals(11,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("d"));
        h.remove("e");
        assertEquals(10,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("e"));
        h.remove("f");
        assertEquals(9,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("f"));
        h.remove("g");
        assertEquals(8,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("g"));
        h.remove("h");
        assertEquals(7,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("h"));
        h.remove("i");
        assertEquals(6,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("i"));
        h.remove("j");
        assertEquals(5,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("j"));
        h.remove("k");
        assertEquals(4,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("k"));
        h.remove("l");
        assertEquals(3,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("l"));
        h.remove("m");
        assertEquals(2,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("m"));
        h.remove("n");
        assertEquals(1,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("n"));
        h.remove("o");
        assertEquals(0,h.getSize());
        assertEquals(44,h.getCapacity());
        assertEquals(null,h.get("o"));
    }
}