package data_structs.graph;

import data_structs.array.Array;
import data_structs.priorityqueue.*;
import data_structs.hashing.HashTable;


public class Graph {

    private final int MAX_NODES;
    private final HashTable<Character, HashTable<Character, Integer>> adjacencyList;

    public Graph(int maxNodes)
    {
        MAX_NODES = maxNodes;
        adjacencyList = new HashTable<>(MAX_NODES);

    }

    public void addNode(Character key)
    {
        adjacencyList.add(key, new HashTable<>(MAX_NODES));
    }

    public void addConnection(Character startNode, Character endNode, Integer weight)
    {
        adjacencyList.item(startNode).add(endNode, weight);
        adjacencyList.item(endNode).add(startNode, weight);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addNode('A');
        graph.addNode('B');
        graph.addNode('C');
        graph.addNode('D');
        graph.addNode('E');
        graph.addNode('F');
        graph.addNode('G');
        graph.addConnection('A', 'B', 5);
        graph.addConnection('A', 'D', 8);
        graph.addConnection('A', 'E', 4);
        graph.addConnection('B', 'C', 4);
        graph.addConnection('C', 'D', 5);
        graph.addConnection('C', 'G', 2);
        graph.addConnection('D', 'E', 7);
        graph.addConnection('D', 'F', 6);
        System.out.println(graph);
    }

    @Override
    public String toString()
    {
        String adjString = adjacencyList.toString();

        return "GRAPH = \n"+ adjString;
    }

    



}
