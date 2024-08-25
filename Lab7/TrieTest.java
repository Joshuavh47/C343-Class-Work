import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {
    //TODO: Your own JUnit tests for every method
    @Test
    public void TrieTest(){
        Trie test = new Trie();
        test.insert("cat");
        test.insert("catch");
        test.insert("aqua");
        System.out.println(test.root.isWord());
        test.insert("a");
        System.out.println(test.root.isWord());
        test.insert("zebra");
        test.insert("algebra");
        test.insert("catacomb");


        assertTrue(test.search("cat"));
        assertTrue(test.search("catch"));
        assertFalse(test.search("c"));
        assertFalse(test.search("hello"));
        assertTrue(test.search("a"));

        test.delete("cat");
        assertFalse(test.search("cat"));
        assertTrue(test.search("catch"));
        test.delete("zebra");
        assertFalse(test.search("zebra"));
        assertFalse(test.search("zebr"));

        test.insert("aquamarine");
        test.delete("aquamarine");
        assertTrue(test.search("aqua"));
        assertFalse(test.search("aquamarine"));


    }

    @Test
    public void insert(){
        Trie test = new Trie();
        test.insert("cat");
        test.insert("catch");
        test.insert("aqua");
        test.insert("a");
        test.insert("zebra");
        test.insert("algebra");
        test.insert("catacomb");
        assertTrue(test.search("cat"));
        assertTrue(test.search("catch"));
        assertFalse(test.search("c"));
        assertFalse(test.search("hello"));
        assertTrue(test.search("a"));
        //tested a few words manually to make sure every letter is inserted correctly and the chaining is ok
        assertFalse(test.getRoot().isEmpty(2));
        assertFalse(test.getRoot().getChildren()[2].isEmpty(0));
        assertFalse(test.getRoot().getChildren()[2].getChildren()[0].isEmpty(19));
        assertFalse(test.getRoot().isEmpty(0));
        //manually tested if the last letter of some of the inserted words is marked as a word
        assertTrue(test.getRoot().getChildren()[0].isWord());
        assertTrue(test.getRoot().getChildren()[2].getChildren()[0].getChildren()[19].isWord());
        assertTrue(test.getRoot().getChildren()[2].getChildren()[0].getChildren()[19].getChildren()[2].getChildren()[7].isWord());
    }
    @Test
    public void delete(){
        Trie test = new Trie();
        test.insert("cat");
        test.insert("catch");
        test.insert("aqua");
        test.insert("a");
        test.insert("zebra");
        test.insert("algebra");
        test.insert("catacomb");
        assertTrue(test.search("cat"));
        assertTrue(test.search("catch"));
        assertFalse(test.search("c"));
        assertFalse(test.search("hello"));
        assertTrue(test.search("a"));

        test.delete("catch");
        assertFalse(test.search("catch"));
        assertTrue(test.search("cat"));
        test.delete("zebra");
        assertFalse(test.search("zebra"));
        test.delete("");//Shouldnt do anything

    }

    @Test
    public void search(){
        Trie test = new Trie();
        test.insert("cat");
        test.insert("catch");
        test.insert("aqua");
        test.insert("a");
        test.insert("zebra");
        test.insert("algebra");
        test.insert("catacomb");

        assertTrue(test.search("cat"));
        assertTrue(test.search("catch"));
        assertTrue(test.search("a"));
        assertFalse(test.search("c"));
        assertFalse(test.search("hello"));
        //added a couple tests for when there is an already added word with an extra character
        //(tests the isWord() check)
        assertFalse(test.search("aquam"));
        assertFalse(test.search("an"));
        //added tests for when an existing word is unfinished
        assertFalse(test.search("ca"));
    }
}