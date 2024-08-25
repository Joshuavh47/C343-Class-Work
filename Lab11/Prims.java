public class Prims {

    /** MST
     *
     * Implement Prim's Minimum Spanning Tree Algorithm
     *
     * Prim's algorithm is a greed algorithm that will
     * find the minimum edge of all edges connected to
     * the MST.
     *
     * This means that you will need to know which nodes
     * you have visited, which nodes are in the MST, and
     * which nodes you can reach from the edges in the
     * MST already.
     *
     * For a quick, more visual explanation see:
     *  https://www.youtube.com/watch?v=cplfcGZmX7I
     *
     *
     * One important note about the mst array.
     * It does not contain information on the weights,
     * only the nodes of the edges.
     *         i        = u
     *       mst[i]     = v
     * graph[i][mst[i]] = weight(u, v)
     *
     * @param graph
     * @return a Minimum Spanning Tree of the graph
     */
    public int[] mst(Graph graph) {
        int[] in=new int[graph.getNumNodes()];
        int[] out=new int[graph.getNumNodes()];
        for(int i=0;i< graph.getNumNodes();i++){
            for(int j=0;j< graph.getNumNodes();j++){
                if(graph.getEdge(i,j)!=0){
                    in[j]++;
                    out[i]++;
                }
            }
        }
        for(int i=0;i< graph.getNumNodes();i++){
            if(in[i]==0&&out[i]==0){
                throw new DisconnectedGraphException();
            }
        }
        int[] tree=new int[graph.getNumNodes()];
        Boolean[] mstSet=new Boolean[graph.getNumNodes()];
        int[] weights=new int[graph.getNumNodes()];
        for(int i=0;i< graph.getNumNodes();i++){
            weights[i]=Integer.MAX_VALUE;
            mstSet[i]=false;
        }
        weights[0]=0;
        tree[0]=-1;
        for(int i=0;i< graph.getNumNodes();i++){
            int u=minIndex(weights,mstSet, graph.getNumNodes());
            mstSet[u]=true;
            for(int v=0;v<graph.getNumNodes();v++){
                if(graph.getEdge(u,v)!=0&&!mstSet[v]&&graph.getEdge(u,v)<weights[v]){
                    tree[v]=u;
                    weights[v]= graph.getEdge(u,v);
                }
            }
        }
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.getNumNodes(); i++)
            System.out.println(tree[i] + " - " + i + "\t" + graph.getEdge(i,tree[i]));
        return tree;

    }

    /** MININDEX
     *
     * When given a mst in progress, return the index of the weight
     * array of the minimum edge weight that is NOT in the MST.
     *
     * the mstSet array returns true if a node u is in the mst.
     *
     * @param weights array of weights
     * @param mstSet array of nodes in the MST
     * @param numNodes number of nodes in a MST
     * @return index of the smallest weight.
     */
    int minIndex(int weights[], Boolean mstSet[], int numNodes)
    {
        int min=Integer.MAX_VALUE;
        int index=-1;
        for(int i=0;i<numNodes;i++){
            if(!mstSet[i]&&weights[i]<min){
                min=weights[i];
                index=i;
            }
        }
        return index;
    }

}
