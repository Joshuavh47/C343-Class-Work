import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
    private int mode;
    private Tree<Node> nodeTree; // List -> Tree

    // build everything bahahah
    // TODO: mode 3 = BST mode 4 = AVL
    public SearchEngine(int mode) throws IOException {
        this.mode = mode;
        if(mode==3){
            nodeTree= new BST<Node>();
        }
        else if(mode==4){
            nodeTree=new AVL<Node>();
        }
    }

    public Tree<Node> getNodeTree(){
        return this.nodeTree;
    }

    // assumes that the file exists already
    // TODO: tweak logic so that it builds the proper tree
    public void buildList() throws IOException {
        System.out.println("reading");
        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            String[] words = text.split("\\s+"); // splits by whitespace
            for (String word : words) {
                BinaryNode<Node> n = nodeTree.searchNode(word);
                if(n!=null){
                    if(!n.data().getReferences().contains(url)) {
                        n.data().insertReference(url);
                    }
                }
                else{
                    nodeTree.insert(new Node(word,url));
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }

    // return a List ew ._.
    // TODO: Return the reference list of URLs
    public ArrayList<String> search(String term) {
        System.out.println("Searching for " + term + " using data structure mode " + mode + "...");

        try{
            buildList();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        BinaryNode<Node> n=nodeTree.searchNode(term);
        if(n!=null){
            return n.data().getReferences();
        }
        return new ArrayList();
    }

    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);

        System.out.println("Enter mode as in what data structure to use:");
        System.out.println("    1. Array List ");
        System.out.println("    2. Sorted Array List");

        int mode = input.nextInt();

        System.out.println("Building Search Engine...");
        SearchEngine engine = new SearchEngine(mode);

        String answer = "y";
        while (answer.equals("y")) {
            input.nextLine(); // consume the remaining newline character
            System.out.print("Search (enter a term to query): ");
            String term = input.nextLine();
            ArrayList<String> a=engine.search(term);
            System.out.println(a);
            System.out.print("Would you like to search another term (y/n)? ");
            answer = input.nextLine();

        }

        input.close();
    }
}