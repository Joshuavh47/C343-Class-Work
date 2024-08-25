import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LabTrieTest {

    Trie knownGoodTrie;
    @BeforeEach
    public void initKnownGoodTrie() {
        knownGoodTrie = new Trie();
        knownGoodTrie.root.children.put('a', new TrieNode()); // "a"
        knownGoodTrie.root.children.get('a').isWord = true;

        knownGoodTrie.root.children.get('a').children.put('b', new TrieNode()); // "abz"
        knownGoodTrie.root.children.get('a').children.get('b').children.put('z', new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('z').isWord = true;

        knownGoodTrie.root.children.get('a').children.get('b').children.put('d', new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('d').children.put('c', new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').children.put('y',new TrieNode());
        knownGoodTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').children.get('y').isWord = true;
    }

    @Test
    // 0 pts
    public void TrieWithHashTableConstructor() {
        Trie testTrie = new Trie();
        assertFalse(testTrie.root.isWord);
        assertTrue(testTrie.root.children.isEmpty());
    }

    @Test
    // 2 pts
    public void insert() {
        Trie testTrie = new Trie();

        testTrie.insert("a");
        assertEquals(true, testTrie.root.children.get('a').isWord,
                "did not properly insert \"a\"");
        for (int i = 'a'; i < 'z'; i++) {
            assertNull(testTrie.root.children.get(i), "added too many children to the root");
        }

        testTrie.insert("abz");
        assertEquals(true, testTrie.root.children.get('a').isWord,
                "expected isWord to be false");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').isWord,
                "expected isWord to be false");
        assertEquals(true, testTrie.root.children.get('a').children.get('b').children.get('z').isWord,
                "expected isWord to be true");

        testTrie.insert("abdcy");
        assertEquals(true, testTrie.root.children.get('a').isWord,
                "expected isWord to be false");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').isWord,
                "expected isWord to be false");
        assertEquals(true, testTrie.root.children.get('a').children.get('b').children.get('z').isWord, // z from last insert
                "expected isWord to be true");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').children.get('d').isWord,
                "expected isWord to be true");
        assertEquals(false, testTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').isWord,
                "expected isWord to be true");
        assertEquals(true, testTrie.root.children.get('a').children.get('b').children.get('d').children.get('c').children.get('y').isWord,
                "expected isWord to be true");
    }

    @Test
    // 2 pts
    public void search() {
        assertEquals(true, knownGoodTrie.search("a"));
        assertEquals(true, knownGoodTrie.search("abz"));
        assertEquals(true, knownGoodTrie.search("abdcy"));

        assertEquals(false, knownGoodTrie.search("af"));
        assertEquals(false, knownGoodTrie.search("ballon"));
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

}