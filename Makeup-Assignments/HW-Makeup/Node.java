import java.util.ArrayList;
import java.lang.Comparable;

// For building the SearchEngine

public class Node implements Comparable<Node> {
    private String keyword;
    private ArrayList<String> references;

    public Node(String keyword){
        this.keyword = keyword;
        this.references = new ArrayList<String>();
    }
    public Node(String keyword, String website){
        this.keyword = keyword;
        this.references = new ArrayList<String>();
        this.references.add(website);
    }

    public String getKeyword(){
        return this.keyword;
    }

    public ArrayList<String> getReferences(){
        return this.references;
    }
    public String data(){
        return keyword;
    }
//    public int compareTo(Object o){
//        if(o instanceof Node){
//            Node other = (Node) o;
//            return this.keyword.compareTo(other.keyword);
//        }
//        return -1;
//    }

    public void insertReference(String website){
        this.references.add(website);
    }

    public boolean equals (Object o) {
        if (o instanceof Node) {
            Node other = (Node) o;
            return this.keyword.equals(other.keyword);
        }
        else return false;
    }

    public String toString(){
        return this.keyword + " " + this.references;
    }

    public int compareTo(Node o) {
        return this.keyword.compareTo(o.keyword);
    }
}