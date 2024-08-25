import java.util.*;

public class Graph {
    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    // TODO: Initialize the nodes
    public void init(int n){
        for(int i=0;i<n;i++){
            nodes.add(new Node(i));
        }
    }

    // TODO: not tested for credit technically but this method should reset your node's value
    public void reset(){
        for(Node n:nodes){
            n.setValue(Integer.MAX_VALUE);
            n.setPrev(null);
        }
    }

    // TODO:
    public void addEdge(Node src, Node dest, int weight){
        src.getEdges().add(new Edge(dest,weight));
    }

    // TODO: remove the edge from the Node
    public void removeEdge(Node src, Node dest){
        for(Edge e:src.getEdges()){
            if(e.getDestVertex()==dest){
                src.getEdges().remove(e);
            }
        }
    }

    // TODO: Return the shortest path from start to dest with the correct cost of the nodes; return null if no path possible
    public List<Node> shortestPath(int start, int dest){
        reset();
        int newDistance=0;
        HashSet<Node> settled=new HashSet<>();
        Node source=null;
        PriorityQueue<Node> pq=new PriorityQueue<Node>();
        for(Node n:nodes){
            if(n.getName()==start){
                n.setValue(0);
                source=n;
                break;
            }
        }
        if(source==null){
            return null;
        }
        pq.add(source);
        while(settled.size()!=nodes.size()){
            if(pq.isEmpty()){
                break;
            }
            Node node=pq.remove();

            if(!settled.contains(node)){
                settled.add(node);

                for(Edge e:node.getEdges()){
                    Node temp=e.getDestVertex();
                    if(!settled.contains(temp)){
                        newDistance=node.getValue()+e.getWeight();

                        if(newDistance<temp.getValue()){
                            temp.setValue(newDistance);
                            temp.setPrev(node);
                        }
                        pq.add(temp);
                    }
                }

            }


        }

        Node end=null;
        for(Node n:nodes) {
            if(n.getName()==dest){
                end=n;
                break;
            }
        }
        ArrayList<Node> path=new ArrayList<>();
        Node t=end;
        path.add(0,t);
        while(t.getPrev()!=null){
            t=t.getPrev();
            path.add(0,t);
        }
        if(t==source){
            //path.add(0,t);
            return path;
        }
        else{
            return null;
        }




    }
}

