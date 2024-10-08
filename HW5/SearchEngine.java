import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {
    private int mode;
    private Dictionary<String, Node> nodeTable;

    // build everything bahahah
    // TODO: mode 5 = Openaddressing mode 6 = Chaining; build
    public SearchEngine(int mode) throws IOException {
        this.mode = mode;
        if(mode==5){
            nodeTable=new HashTableOpenAddressing<>(1);
        }
        else if(mode==6){
            nodeTable=new HashTableWithChaining<>();
        }
    }

    public Dictionary<String, Node> getNodeTree(){
        return this.nodeTable;
    }

    // TODO: assumes that the file exists already
    public void buildList() throws IOException {
        System.out.println("reading");
        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            //if(url.equals("https://en.wikipedia.org/wiki/Kamala_Harris"))            System.out.println(text);

            // System.out.println(text);
            String[] words = text.split("\\s+"); // splits by whitespace
            int count = 0;
            for (String word : words) {
                Node n=nodeTable.get(word);
                if(n!=null){
                    if(!n.containsReference(url)) {
                        n.insertReference(url);
                    }
                }
                else{
                    nodeTable.put(word,new Node(word,url));
                }

            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }

    // TODO: return the results from one term
    public ArrayList<String> search(String term) {
        System.out.println("Searching for " + term + " using data structure mode " + mode + "...");
        if(getNodeTree().containsKey(term)) {
            return nodeTable.get(term).getReferences();
        }
        return new ArrayList<String>();
    }

    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);

        System.out.println("Enter mode as in what data structure to use:");
        System.out.println("    5. HashTableOpenAddressing ");
        System.out.println("    6. HashTableWithChaining");

        int mode = input.nextInt();

        System.out.println("Building Search Engine...");
        SearchEngine engine = new SearchEngine(mode);

        String answer = "y";
        while (answer.equals("y")) {
            input.nextLine(); // consume the remaining newline character
            System.out.print("Search (enter a term to query): ");
            String term = input.nextLine();
            engine.buildList();
            List<String> list = engine.search(term);
            for(String s:list){
                System.out.println(s);
            }
            System.out.print("Would you like to search another term (y/n)? ");
            answer = input.nextLine();
        }
        input.close();
    }
}