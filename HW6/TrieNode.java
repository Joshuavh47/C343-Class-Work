import java.util.ArrayList;
import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    int frequency;
    int count;
    int wordCount=0;
    // TODO: initialize the TrieNode's properties
    public TrieNode() {
        this.children=new HashMap<>();
        this.isWord=false;
        this.frequency=1;
        this.count=frequency;
    }

    public String toString () {
        return children.toString();
    }

}
