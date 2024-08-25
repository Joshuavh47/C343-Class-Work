import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class TrieTest {
    @Test
    public void insertAndSearch(){
        Trie t=new Trie();
        t.insert("Hello");
        t.insert("HelloWorld");
        t.insert("helicopter");
        t.insert("apple");
        t.insert("apples");
        t.insert("apply");
        t.insert("tech");
        t.insert("technology");
        t.insert("teacher");
        t.insert("baseball");
        t.insert("basic");
        t.insert("base");
        t.insert("pretend");
        t.insert("preworkout");
        t.insert("hello");
        t.insert("hello");
        t.insert("apple");
        //inserted words should return true
        assertEquals(true,t.search("hello"));
        assertEquals(true,t.search("helloworld"));
        assertEquals(true,t.search("apple"));
        assertEquals(true,t.search("apples"));
        assertEquals(true,t.search("apply"));
        assertEquals(true,t.search("tech"));
        assertEquals(true,t.search("technology"));
        assertEquals(true,t.search("teacher"));
        assertEquals(true,t.search("baseball"));
        assertEquals(true,t.search("basic"));
        assertEquals(true,t.search("base"));
        assertEquals(true,t.search("pretend"));
        assertEquals(true,t.search("preworkout"));
        //words that aren't inserted should return false
        assertEquals(false,t.search("a"));
        assertEquals(false,t.search("h"));
        assertEquals(false,t.search("he"));
    }

    @Test
    public void delete(){
        Trie t=new Trie();
        t.insert("Hello");
        t.insert("HelloWorld");
        t.insert("helicopter");
        t.insert("apple");
        t.insert("apples");
        t.insert("apply");
        t.insert("tech");
        t.insert("technology");
        t.insert("teacher");
        t.insert("baseball");
        t.insert("basic");
        t.insert("base");
        t.insert("pretend");
        t.insert("preworkout");
        t.insert("hello");
        t.insert("hello");
        t.insert("apple");
        t.insert("pear");
        t.insert("hell");
        t.delete("pear");
        t.delete("hell");
        t.insert("super");
        t.delete("super");

        //asserts that non-deleted words that are added to the trie are still there
        assertEquals(true,t.search("hello"));
        assertEquals(true,t.search("helloworld"));
        assertEquals(true,t.search("apple"));
        assertEquals(true,t.search("apples"));
        assertEquals(true,t.search("apply"));
        assertEquals(true,t.search("tech"));
        assertEquals(true,t.search("technology"));
        assertEquals(true,t.search("teacher"));
        assertEquals(true,t.search("baseball"));
        assertEquals(true,t.search("basic"));
        assertEquals(true,t.search("base"));
        assertEquals(true,t.search("pretend"));
        assertEquals(true,t.search("preworkout"));

        //asserts that words that aren't prefixes and have no words after are deleted
        assertEquals(false,t.search("super"));

        //asserts that words that have no prefixes but have words after them are deleted properly
        assertEquals(false, t.search("hell"));
        assertEquals(true,t.search("hello"));
        assertEquals(true,t.search("helloworld"));

        //asserts that words with prefixes AND words after are properly deleted
        t.insert("hell");
        t.delete("hello");
        assertEquals(true,t.search("hell"));
        assertEquals(false,t.search("hello"));
        assertEquals(true,t.search("helloworld"));


    }

    @Test
    public void generateWordsFromPrefix(){
        Trie t=new Trie();
        t.insert("Hello");
        t.insert("HelloWorld");
        t.insert("helicopter");
        t.insert("apple");
        t.insert("apples");
        t.insert("apply");
        t.insert("tech");
        t.insert("technology");
        t.insert("teacher");
        t.insert("baseball");
        t.insert("basic");
        t.insert("base");
        t.insert("pretend");
        t.insert("preworkout");
        t.insert("hello");
        t.insert("hello");
        t.insert("apple");
        ArrayList<Entry> e= t.generateWordsFromPrefix("");
        String out="";
        for(Entry en:e){
            out+=en.toString()+"\n";
        }
        out=out.substring(0,out.length()-1);
        assertEquals("pretend (frequency: 1)\npreworkout (frequency: 1)\napple (frequency: 2)\napples (frequency: 1)\napply (frequency: 1)\nbase (frequency: 1)\nbaseball (frequency: 1)\nbasic (frequency: 1)\nteacher (frequency: 1)\ntech (frequency: 1)\ntechnology (frequency: 1)\nhelicopter (frequency: 1)\nhello (frequency: 3)\nhelloworld (frequency: 1)",out);

        out="";
        e.clear();
        e=t.generateWordsFromPrefix("he");
        for(Entry en:e){
            out+=en.toString()+"\n";
        }
        out=out.substring(0,out.length()-1);
        assertEquals("helicopter (frequency: 1)\nhello (frequency: 3)\nhelloworld (frequency: 1)",out);
    }
}
