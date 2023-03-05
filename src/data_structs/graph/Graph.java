package data_structs.graph;

import data_structs.array.Array;
import data_structs.priorityqueue.*;
import data_structs.hashing.HashTable;


public class Graph {

    private final HashTable<Character, HashTable<Character, Integer>> adjacencyList;
    private final int MAX_NODES;

    public Graph(int maxNodes)
    {
        this.MAX_NODES = maxNodes;
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
        Graph graph = new Graph(9);
        graph.addNode('A');
        graph.addNode('B');
        graph.addNode('D');
        graph.addNode('C');
        graph.addNode('H');
        graph.addNode('G');
        graph.addConnection('A', 'B', 5);
        graph.addConnection('B', 'C', 4);
        graph.addConnection('C', 'D', 5);
        System.out.println(graph);
    }

    @Override
    public String toString()
    {
        String adjString = adjacencyList.toString();

        return "GRAPH = \n"+ adjString;
    }

    //Dijkstra's shortest path algorithm



}
