//Partners: Josh Eres and Jonathan Wells

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot(){
        return root;
    }
    /**
     * Insert the word into the Trie by making new TrieNodes and marking the last TrieNode as a word.
     * @param word The word to be inserted
     */
    void insert(String word) {
        insertHelper(root, word);
    }



    void insertHelper(TrieNode node, String word) {
        /*
        Checks to see if the word is empty because instead of a for loop, our code takes the substring of the word from
        index 1 to the end to the word each time the recursive method is called, effectively only manipulating the first
        character of the word and then chopping it off until the word length is 0.
        */
        if(word.isEmpty()){
            node.setWordTrue();
            return;
        }
        /*
        Since we are only working with lowercase letters, we subtract the integer representation of the first letter of
        the word during each iteration of the method to put 'a' in the first spot of the children array, 'b' in the
        second, and so on.
         */
        int index = word.charAt(0) - 'a';
        /*
        If there isn't an existing node that we can use for the word, we make a new node (basically a new branch of the
        tree) in the corresponding index.
         */
        if(node.children[index] == null){
            node.children[index] = new TrieNode();
        }

        insertHelper(node.children[index], word.substring(1));
    }

    /**
     * Given a word, returns if it is represented in this Trie.
     * @param word The word to be searched for
     */
    boolean search(String word) {
        return searchHelper(root, word);
    }

    boolean searchHelper(TrieNode node, String word) {
        /*
        This is basically the same logic as the insert, except when we get to the end of the word (when the word's
        length is 0 because of the substring(1) due to recursion) we check if the node of the last letter of the word
        is set as a word. If it is, then it returns true because of the isWord() method call, and vice versa. This
        makes sense because if there is a word that contains a substring of a bigger word and we don't check to make
        sure that it is a word, we could give a false positive (ex. "aqua" vs "aquamarine")
         */
        if(word.isEmpty()){
            return node.isWord();
        }
        int index = word.charAt(0) - 'a';
        if(node.children[index] == null){
            return false;
        }
        return searchHelper(node.children[index], word.substring(1));
    }




    /**
     * Marks the TrieNode representing the last char in the given word is no longer a word.
     * @param word The word to be deleted
     */
    void delete(String word) {
        if(!search(word)){
            return;
        }
        deleteHelper(root,word);
    }

    void deleteHelper(TrieNode node, String word) {
        /*
        Since space conservation isn't a huge issue here, we use a lazy delete. We do not check if there are nodes
        after the word's ending that do not have word markers because during the insert, we can just make another tree
        branch at the end of the common characters (ex. deleting "saturday" and then adding "satisfying".) This uses
        similar logic to both the insert and search methods, as it uses substrings to shorten the word while
        manipulating the first character with every recursive method call, then at the end it just sets the last node
        of the word's isWord() to false;
         */
        if (word.length() == 0) {
            node.setWordFalse();
            return;
        }

        int index = word.charAt(0) - 'a';

        deleteHelper(node.children[index], word.substring(1));

    }

    public static void main(String[] args){
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("catch");
        trie.insert("aqua");
        trie.insert("a");
        trie.insert("zebra");
        trie.insert("algebra");
        trie.insert("catacomb");

        System.out.println(trie.search("cat"));
        System.out.println(trie.search("catch"));
        System.out.println(trie.search("c"));
        System.out.println(trie.search("hello"));
        System.out.println(trie.search("a"));
        //System.out.println(trie.search("car"));
        System.out.println(trie.root.getChildren().length);

    }
}
