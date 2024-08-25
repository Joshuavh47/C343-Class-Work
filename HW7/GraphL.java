import java.util.*;

class CycleDetected extends Exception{};

public class GraphL implements Graph {
    // An Array of nodes where each item points to a list of adjacent nodes
    private List<Node>[] nodeArray;
    // A List of nodes that will be referenced in the nodeArray
    private List<Node> nodes;
    // Number of edges in the graph
    private int numEdge;

    public GraphL(int n){
        this.nodeArray = new ArrayList[n];
        this.nodes = new ArrayList<>();
        init(n);
    }

    // TODO:
    public GraphL(List<Node>[] nodeArray, List<Node> nodes){
        this.nodeArray=nodeArray;
        this.nodes=nodes;
        init(nodes.size());
    }

    // Implement the missing functions here:
    // TODO:
    public void init(int n) {
        if(nodes.size()==0) {
            for (int i = 0; i < n; i++) {
                nodeArray[i] = new ArrayList<Node>();
                nodes.add(new Node(i));
                nodes.get(i).setValue(0);
            }
        }
        numEdge=edgeCount();
    }

    // Hint: may need a reset function for the Runner class (not in the Interface)
    public void reset(){
        for(Node n:nodes){
            n.setUnvisited();
            n.setValue(0);
        }
    }

    // TODO:
    public int nodeCount() {
        return nodes.size();
    }

    // TODO:
    public int edgeCount() {
        int num=0;
        for(int i=0;i<nodeArray.length;i++){
            for(int j=0;j<nodeArray[i].size();j++){
                num++;
            }
        }
        return num;
    }

    // TODO:
    public void addEdge(int v, int w) {
        if(!nodeArray[v].contains(nodes.get(w))){
            nodeArray[v].add(nodes.get(w));
        }
        edgeCount();
    }

    // TODO:
    public void removeEdge(int v, int w) {
        if(nodeArray[v].contains(nodes.get(w))){
            nodeArray[v].remove(nodes.get(w));
        }
        edgeCount();
    }

    // TODO:
    public boolean hasEdge(int v, int w) {
        if(nodeArray[v].contains(nodes.get(w))){
            return true;
        }
        return false;
    }

    // TODO:
    public List<Node> neighbors(int v) {
        return nodeArray[v];
    }

    public boolean hasCycle(Node n,boolean[] inStack){
        if(inStack[nodes.indexOf(n)]){
            return true;
        }
        if(n.getVisited()){
            return false;
        }
        n.setVisited();
        inStack[nodes.indexOf(n)]=true;
        for(Node node:nodeArray[nodes.indexOf(n)]){
            if(hasCycle(node,inStack)){
                return true;
            }
        }
        inStack[nodes.indexOf(n)]=false;
        return false;
    }

    // TODO: Implement topological sort with stack.
    /*
    Potential way to implement:
    1. For each node in the nodes, you can perform DFS
     */
    // Hint: think of how you can use the value of the Node (not the name)
    public List<Node> topologicalSortStack() throws CycleDetected {
        boolean[] inStack=new boolean[nodes.size()];
        for(boolean b:inStack){
            b=false;
        }
        for(Node node:nodes){
            if(hasCycle(node,inStack)){
                throw new CycleDetected();
            }
        }
        for(Node node:nodes){
            node.setUnvisited();
        }
        for(List<Node> arr:nodeArray){
            for(Node node:arr){
                if(node!=null){
                    node.setValue(node.getValue()+1);
                }
            }
        }
        boolean noCycle=false;
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).getValue()==0){
                noCycle=true;
                break;
            }
        }
        if(!noCycle){
            throw new CycleDetected();
        }
        Stack<Node> s=new Stack<>();
        List<Node> l=new ArrayList<>();

        for(Node node:nodes){
            if(!node.getVisited()){
                topologicalSortStack(node,s);
            }
        }
        while(!s.isEmpty()){
            l.add(s.pop());
        }
        reset();
        return l;
    }
    public void topologicalSortStack(Node no,Stack s) throws CycleDetected{

        no.setVisited();
        int count=0;
        for(Node node:nodeArray[nodes.indexOf(no)]){
            if(!node.getVisited()){
                topologicalSortStack(node,s);
            }
        }
        s.push(no);


    }

    // TODO: Implement topological sort with queue.
    /*
    Potential way to implement:
    1. Go through the edges and set the value of the node to the number of incoming edges and unvisited.
    2. Push nodes that have 0 incoming edges into the queue.
    3. Implement BFS
        -Everytime you process a node, decrease it's value.
     */
    public List<Node> topologicalSortQueue() throws CycleDetected {
        List<Node> l=new ArrayList<>();
        LinkedList<Node> q=new LinkedList<>();
        for(List<Node> arr:nodeArray){
            for(Node node:arr){
                if(node!=null){
                    node.setValue(node.getValue()+1);
                }
            }
        }
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).getValue()==0){
                q.add(nodes.get(i));
            }
        }
        int count=0;
        while(!q.isEmpty()){
            Node n=q.removeFirst();
            l.add(n);
            for(Node node:nodeArray[nodes.indexOf(n)]){
                node.setValue(node.getValue()-1);
                if(node.getValue()==0){
                    q.add(node);
                }
            }
            count++;
        }
        if(count!=nodes.size()){
            throw new CycleDetected();
        }
        reset();
        return l;
    }


}