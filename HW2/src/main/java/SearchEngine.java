import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class SearchEngine {

    private int mode;
    private List<Node> nodeList;

    // TODO: build the SearchEngine's nodelist according to mode (1 = ArrayList; 2 = SortedArrayList); build the searchEngine
    public SearchEngine(int mode) throws IOException {
        this.mode = mode;
        if(mode==1){
            nodeList = new ArrayList<Node>();
        }
        else if(mode==2){
            nodeList = new SortedArrayList<Node>();
        }
    }

    public boolean contains(String str) throws IOException{
        for(int i=0;i<nodeList.size();i++){
            if(nodeList.get(i).getKeyword().equalsIgnoreCase(str)){
                return true;
            }
        }
        return false;
    }

    public List<Node> getNodeList(){
        return this.nodeList;
    }

    // TODO: Go through the dataset and then create a new Node if the word hasn't been seen before. Add the current URL to its references
    // if it hasn't been seen. If the node has been created already, add the current URL to its references. Add the Node to the the
    // SearchEngine's nodeList
    public void buildList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            String[] words = text.split("\\s+"); // splits by whitespace
            HashSet<String> h=new HashSet<String>();
            for(String word1:words){
                h.add(word1);
            }
            for(String word : h){
                if(contains(word)) {
                    for (int i = 0; i < nodeList.size(); i++) {
                        if (nodeList.get(i).getKeyword().equalsIgnoreCase(word)) {
                            if (nodeList.get(i).getReferences().search(url) == -1) {
                                nodeList.get(i).insertReference(url);
                            }
                        }

                    }
                }
                else{
                    Node temp=new Node(word,mode);
                    temp.insertReference(url);
                    nodeList.add(temp);
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }

    // TODO: Return the node's reference list - if the term isn't found, return an empty list
    public List<String> search(String term) {
        System.out.println("Searching for " + term + " using data structure mode " + mode + "...");
        // Search logic goes here
        // Example code for displaying results
        try {
            buildList();
        }
        catch(IOException e){

        }
        int count=1;
        int index=nodeList.search(new Node(term,mode));

        if(index==-1){
            if(mode==1){
                return new ArrayList<String>();
            }
            if(mode==2){
                return new SortedArrayList<String>();
            }
        }
        else{

            List<String> result = nodeList.get(index).getReferences();
            System.out.println("Search results for " + term + ": ");
            for(int i=0; i<result.size(); i++){
                System.out.println("URL "+count+": "+result.get(i));
                count++;
            }
            return result;
        }
        return new ArrayList<String>();

    }

    public static void main(String[] args) throws IOException {
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
            engine.search(term);
            System.out.print("Would you like to search another term (y/n)? ");
            answer = input.nextLine();
        }
        input.close();
    }
}
