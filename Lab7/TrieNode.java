//Partners: Josh Eres and Jonathan Wells

public class TrieNode {
    TrieNode [] children;
    boolean isWord;

    public TrieNode() {
        this.children=new TrieNode[26];
        this.isWord=false;
    }

    boolean isEmpty() {
        for (int i = 0; i<children.length;i++){
            if (!isEmpty(i)){
                return false;
            }
        }
        return true;

    }

    boolean isEmpty(int index){
        if(index>=children.length||this.children[index]==null){
            return true;
        }
        return false;
    }

    boolean isWord(){
        return isWord;
    }

    public TrieNode[] getChildren(){
        return this.children;
    }

    public void setChild(TrieNode node, char letter){
        children[letter-'a']=node;
    }

    public void setWordTrue(){
        isWord=true;
    }

    public void setWordFalse(){
        isWord=false;
    }


}
