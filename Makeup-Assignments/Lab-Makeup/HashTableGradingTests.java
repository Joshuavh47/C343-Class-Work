import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HashTableGradingTests {
    final int STARTING_ASCII_INT = 97; // 'a'

    @Test
    public void putTest() {
        // Check for normal put
        HashTable ht = new HashTable();
        List<Entry> entries = ht.getEntries();
        for (int i = 0; i < 3; i++) {
            String curStr = String.valueOf((char) (STARTING_ASCII_INT + i));
            assertEquals(i, ht.getSize());
            ht.put(curStr, curStr);
        }
        assertEquals(3, ht.getSize());

        assertEquals("c", entries.get(0).getKey());
        assertEquals("c", entries.get(0).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(0).getType());
        assertEquals("a", entries.get(9).getKey());
        assertEquals("a", entries.get(9).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(9).getType());
        assertEquals("b", entries.get(10).getKey());
        assertEquals("b", entries.get(10).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(10).getType());

        // Test duplicate put
        ht.put("c", "z");
        assertEquals(3, ht.getSize());
        assertEquals("z", entries.get(0).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(0).getType());

        // Test negative hashCode put
        ht.put("dfhlaksdhfkahs", "x");
        assertEquals(4, ht.getSize());
        assertEquals("x", entries.get(6).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(6).getType());




        // Check for quadratic probing
        ht = new HashTable();
        entries = ht.getEntries();
        assertEquals(0, ht.getSize());
        ht.put("E", "E");
        assertEquals(1, ht.getSize());
        assertEquals("E", entries.get(3).getKey());
        assertEquals("E", entries.get(3).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(3).getType());
        ht.put("P", "P");
        assertEquals(2, ht.getSize());
        assertEquals("P", entries.get(4).getKey());
        assertEquals("P", entries.get(4).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(4).getType());
        ht.put("f", "f");
        assertEquals(3, ht.getSize());
        assertEquals("f", entries.get(7).getKey());
        assertEquals("f", entries.get(7).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(7).getType());
        ht.put("q", "q");
        assertEquals(4, ht.getSize());
        assertEquals("q", entries.get(1).getKey());
        assertEquals("q", entries.get(1).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(1).getType());
        ht.put("19", "19");
        assertEquals(5, ht.getSize());
        assertEquals("19", entries.get(8).getKey());
        assertEquals("19", entries.get(8).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(8).getType());
    }

    @Test
    public void rehashTest() {
        HashTable ht = new HashTable();
        List<Entry> entries = ht.getEntries();

        // Initialize the HT with 5 values
        assertEquals(0, ht.getSize());
        ht.put("E", "E");
        assertEquals(1, ht.getSize());
        assertEquals("E", entries.get(3).getKey());
        assertEquals("E", entries.get(3).getValue());
        ht.put("P", "P");
        assertEquals(2, ht.getSize());
        assertEquals("P", entries.get(4).getKey());
        assertEquals("P", entries.get(4).getValue());
        ht.put("f", "f");
        assertEquals(3, ht.getSize());
        assertEquals("f", entries.get(7).getKey());
        assertEquals("f", entries.get(7).getValue());
        ht.put("q", "q");
        assertEquals(4, ht.getSize());
        assertEquals("q", entries.get(1).getKey());
        assertEquals("q", entries.get(1).getValue());
        ht.put("19", "19");
        assertEquals(5, ht.getSize());
        assertEquals("19", entries.get(8).getKey());
        assertEquals("19", entries.get(8).getValue());

        ht.put("20", "20");
        entries = ht.getEntries();
        assertEquals(6, ht.getSize());
        assertEquals("E", entries.get(0).getKey());
        assertEquals("E", entries.get(0).getValue());
        assertEquals("f", entries.get(10).getKey());
        assertEquals("f", entries.get(10).getValue());
        assertEquals("P", entries.get(11).getKey());
        assertEquals("P", entries.get(11).getValue());
        System.out.println(ht.hash("19",0));
        assertEquals("19", entries.get(12).getKey());
        assertEquals("19", entries.get(12).getValue());
        assertEquals("20", entries.get(15).getKey());
        assertEquals("20", entries.get(15).getValue());
        assertEquals("q", entries.get(21).getKey());
        assertEquals("q", entries.get(21).getValue());
    }

    @Test
    public void getTest() {
        // Check for non-collision
        HashTable ht = new HashTable();
        for (int i = 0; i < 5; i++) {
            String curStr = String.valueOf((char) (STARTING_ASCII_INT + i));
            ht.put(curStr, curStr);
        }

        assertEquals("a", ht.get("a"));
        assertEquals("b", ht.get("b"));
        assertEquals("c", ht.get("c"));
        assertEquals("d", ht.get("d"));
        assertEquals("e", ht.get("e"));



        // Check for quadratic probing get
        ht = new HashTable();
        ht.put("E", "E");
        ht.put("P", "P");
        ht.put("f", "f");
        ht.put("q", "q");
        ht.put("19", "19");
        assertEquals("E", ht.get("E"));
        assertEquals("P", ht.get("P"));
        assertEquals("f", ht.get("f"));
        assertEquals("q", ht.get("q"));
        assertEquals("19", ht.get("19"));


        assertNull(ht.get(""));
    }

    @Test
    public void removeTest() {
        // Check for normal delete
        HashTable ht = new HashTable();
        List<Entry> entries = ht.getEntries();
        for (int i = 0; i < 3; i++) {
            String curStr = String.valueOf((char) (STARTING_ASCII_INT + i));
            ht.put(curStr, curStr);
        }

        ht.remove("a");
        assertEquals(2, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(9).getType());
        ht.remove("b");
        assertEquals(1, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(10).getType());
        ht.remove("c");
        assertEquals(0, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(0).getType());


        // Set up ht for quadratic probing delete
        ht = new HashTable();
        entries = ht.getEntries();
        assertEquals(0, ht.getSize());
        ht.put("E", "E");
        assertEquals(1, ht.getSize());
        assertEquals("E", entries.get(3).getKey());
        assertEquals("E", entries.get(3).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(3).getType());
        ht.put("P", "P");
        assertEquals(2, ht.getSize());
        assertEquals("P", entries.get(4).getKey());
        assertEquals("P", entries.get(4).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(4).getType());
        ht.put("f", "f");
        assertEquals(3, ht.getSize());
        assertEquals("f", entries.get(7).getKey());
        assertEquals("f", entries.get(7).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(7).getType());
        ht.put("q", "q");
        assertEquals(4, ht.getSize());
        assertEquals("q", entries.get(1).getKey());
        assertEquals("q", entries.get(1).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(1).getType());
        ht.put("19", "19");
        assertEquals(5, ht.getSize());
        assertEquals("19", entries.get(8).getKey());
        assertEquals("19", entries.get(8).getValue());
        assertEquals(Entry.Type.KV_PAIR, entries.get(8).getType());

        for(int i=0;i<entries.size();i++){
            if(entries.get(i)!=null) {
                System.out.println("Key: " + entries.get(i).getKey() + " | Value: " + entries.get(i).getValue()+" | Index: "+i+" | Size: "+ht.getSize());
            }
        }
        // Check quad probing delete
        ht.remove("E");
        assertEquals(4, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(3).getType());
        for(int i=0;i<entries.size();i++){
            if(entries.get(i)!=null) {
                System.out.println("Key: " + entries.get(i).getKey() + " | Value: " + entries.get(i).getValue()+" | Index: "+i+" | Size: "+ht.getSize());
            }
        }
        ht.remove("P");
        assertEquals(3, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(4).getType());
        for(int i=0;i<entries.size();i++){
            if(entries.get(i)!=null) {
                System.out.println("Key: " + entries.get(i).getKey() + " | Value: " + entries.get(i).getValue()+" | Index: "+i+" | Size: "+ht.getSize());
            }
        }
        ht.remove("f");
        assertEquals(2, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(7).getType());
        ht.remove("q");
        assertEquals(1, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(1).getType());
        ht.remove("19");
        assertEquals(0, ht.getSize());
        assertEquals(Entry.Type.TOMBSTONE, entries.get(8).getType());
    }
}
