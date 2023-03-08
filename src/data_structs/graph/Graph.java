package data_structs.graph;
import data_structs.hashing.HashTable;
public class Graph {
    private final HashTable<Character, HashTable<Character, Integer>> adjacencyList;
    private final int MAX_NODES;
    public Graph(int maxNodes)
    {
        this.MAX_NODES = maxNodes;
        adjacencyList = new HashTable<>(MAX_NODES);
    }
    public void addNodes(Character ... keys) { for(Character current: keys) adjacencyList.add(current, new HashTable<>(MAX_NODES));}
    public void addConnection(Character startNode, Character endNode, Integer weight)
    {
        adjacencyList.item(startNode).add(endNode, weight);
        adjacencyList.item(endNode).add(startNode, weight);
    }
    public void addConnections(Character startNode, Character[] endNodes, Integer[] weights)
    {
        if(endNodes.length!=weights.length) throw new IllegalArgumentException("Number of end nodes must equal number of weights");
        for(int i = 0; i<endNodes.length; i++) this.addConnection(startNode, endNodes[i], weights[i]);
    }
    public HashTable<Character, HashTable<Character, Integer>> getAdjacencyList(){ return adjacencyList;}
    @Override
    public String toString()
    {
        String adjString = adjacencyList.toString();

        return "GRAPH = \n"+ adjString;
    }
    public Integer edgeWeight(Character startNode, Character endNode)
    {
        return adjacencyList.item(startNode).item(endNode);
    }
}
