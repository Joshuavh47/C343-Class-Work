import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrieNodeTest {
    //TODO: Your own JUnit tests for every method
    @Test
    public void isEmpty(){
        TrieNode test = new TrieNode();
        assertFalse(test.isWord());
        assertTrue(test.isEmpty());
        test.children[0]=new TrieNode();
        test.isEmpty(0);
        //an obviously out of bounds index to make sure it returns true
        assertTrue(test.isEmpty(100000));
    }

    @Test
    public void isWord(){
        TrieNode a=new TrieNode();
        a.setWordTrue();
        assertTrue(a.isWord());
        Trie t=new Trie();
        t.insert("cat");
        assertTrue(t.getRoot().getChildren()[2].getChildren()[0].getChildren()[19].isWord());
        t.insert("a");
        assertTrue(t.getRoot().getChildren()[0].isWord());
    }

    @Test
    public void setChild(){
        TrieNode t=new TrieNode();
        t.setChild(new TrieNode(),'a');
        t.setChild(new TrieNode(),'b');
        t.setChild(new TrieNode(),'c');
        assertFalse(t.isEmpty(0));
        assertFalse(t.isEmpty(1));
        assertFalse(t.isEmpty(2));
    }




}
