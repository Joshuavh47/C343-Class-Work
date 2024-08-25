import java.sql.Array;
import java.util.*;

public class Trie {
    TrieNode root;
    int wordCount=0;

    public Trie(){
        root = new TrieNode();
    }

    public Trie(TrieNode root){
        this.root = root;
    }

    // Setters & Getters
    public TrieNode getRoot(){
        return this.root;
    }

    public void setRoot(TrieNode root){
        this.root = root;
    }

    // Actual methods -- part of Lab7
    // TODO:
    void insert(String word) {
        String w=word.toLowerCase();
        TrieNode t=root;
        for(int i=0;i<w.length();i++){

            if(!t.children.containsKey(w.charAt(i))){
                t.children.put(w.charAt(i),new TrieNode());
                t=t.children.get(w.charAt(i));
            }
            else{
                t=t.children.get(w.charAt(i));
                t.frequency++;
                t.count++;
            }
            if(i==w.length()-1){
                t.isWord=true;
                t.wordCount++;
            }
        }
        wordCount++;
    }

    // TODO:
    boolean search(String word) {
        String w=word.toLowerCase();
        TrieNode t=root;
        for(int i=0;i<w.length();i++){
            if(!t.children.containsKey(w.charAt(i))){
                return false;
            }
            else{
                t=t.children.get(w.charAt(i));
            }
            if(i==w.length()-1&&!t.isWord){
                return false;
            }

        }
        return true;
    }

    /*
    TODO: Remove the TrieNodes associated with the word. There are 3 cases to be concerned with.
        - key is unique: no part of key contains another key nor is the key itself a prefix of another key in the trie: remove all nodes
        - key is prefix key of another key: unmark the leaf node
        - key has at least one other word as a prefix: delete the nodes from the end of the key :p
        This is NOT the delete you implemented in lab.
 */
    void delete(String word){
        String w=word.toLowerCase();
        TrieNode t=root;
        TrieNode endOfWord;
        ArrayList<TrieNode> wordNodes=new ArrayList<>();
        for(int i=0;i<w.length();i++){
            if((t.isWord||t.children.size()>1)&&t!=root){
                wordNodes.add(t);
            }
            if(!t.children.containsKey(w.charAt(i))){
                return;
            }
            else{
                t=t.children.get(w.charAt(i));
            }
            if(i==w.length()-1&&!t.isWord){
                return;
            }

        }
        endOfWord=t;
        if(!endOfWord.children.isEmpty()&&wordNodes.size()==0){
            wordCount--;
            deleteUnmark(word);
        }
        else if(endOfWord.children.isEmpty()&&wordNodes.size()==0){
            root.children.remove(w.charAt(0));
        }
        else if(wordNodes.size()>=1&&endOfWord.children.isEmpty()){
            t=root;
            for(int i=0;i<w.length();i++){
                t=t.children.get(w.charAt(i));
                t.frequency--;
                t.count=t.frequency;
                if(t==wordNodes.get(wordNodes.size()-1)){
                    t.children.remove(w.charAt(i+1));
                    break;
                }
            }
            wordCount--;
        }
        else if(!endOfWord.children.isEmpty()&&wordNodes.size()>=1){
            deleteUnmark(w);
        }


    }
    public void deleteUnmark(String word){
        String w=word.toLowerCase();
        TrieNode t=root;
        TrieNode endOfWord;

        for(int i=0;i<w.length();i++){


            t=t.children.get(w.charAt(i));
            t.frequency--;
            t.count=t.frequency;
        }
        t.isWord=false;
        wordCount--;
    }


    int getWordCountNode(String s){
        String w=s.toLowerCase();
        TrieNode t=root;
        for(int i=0;i<w.length();i++){
            t=t.children.get(w.charAt(i));
        }
        return t.wordCount;
    }

    // TODO: Gets all possible words with the prefix through traversing the Trie. If it's a word, then turn it into an Entry.
    //       If not, ignore. Put the Entry's into a list and return.
    //       Hint: Look at your MazeSolver with a stack for inspiration for the traversal.
    //       EX: If you have prefix "ca", then it should look at all combinations of the words starting with "ca".
    public ArrayList<Entry> generateWordsFromPrefix(String prefix){
        ArrayList<Entry> entryArr=new ArrayList<>();
        String newPrefix=prefix.toLowerCase();
        ArrayList<String> s=new ArrayList<>();
        TrieNode tn=root;
        for(int i=0;i<newPrefix.length();i++){
            tn=tn.children.get(newPrefix.charAt(i));
        }

        for(int i=0;i<wordCount;i++){

            s=generateWordsFromPrefix(s,tn,newPrefix);
        }
        for(String word:s){
            entryArr.add(new Entry(getWordCountNode(word),word));
        }



        restoreCount();
        return entryArr;
    }
    public ArrayList<String> generateWordsFromPrefix(ArrayList<String> arr,TrieNode tn, String prefix){
        ArrayList<Entry> ls = new ArrayList<>();
        ArrayList<String> strings=arr;

        //HashMap<Character, TrieNode> h=root.children;
        String str=prefix;
        TrieNode t=tn;
        Iterator<Character> it=t.children.keySet().iterator();
        while(it.hasNext()){
            char ch=it.next();

            if(t.children.get(ch).count==0){
                if(!it.hasNext()){
                    break;
                }
                ch=it.next();

            }
            if(t.children.get(ch).count!=0){
                str+=ch;
                t.children.get(ch).count--;
                t=t.children.get(ch);
                it=t.children.keySet().iterator();
            }
            if(t.isWord&&!strings.contains(str)){
                strings.add(str);
            }
            if(t.children.keySet().isEmpty()){
                break;

            }


        }


        return strings;
    }

    public void restoreCount(){
        TrieNode t=root;
        Stack<TrieNode> s=new Stack<>();
        s.push(t);
        while(!s.isEmpty()){
            t=s.pop();
            if(t.count!=t.frequency){
                t.count=t.frequency;
            }
            Iterator<Character> it=t.children.keySet().iterator();
            while(it.hasNext()){
                s.push(t.children.get(it.next()));
            }
        }

    }


}
