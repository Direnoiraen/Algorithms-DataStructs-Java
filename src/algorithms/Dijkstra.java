package algorithms;

import data_structs.array.Array;
import data_structs.linkedlist.LinkedList;
import data_structs.graph.*;
import data_structs.hashing.*;
import data_structs.priorityqueue.*;
public class Dijkstra {
    public static Path<Character> dijkstra(Graph graph, Character startNode, Character endNode)
    {
        //Priority queue of each node key and the shortest distance to them.
        PriorityQueue<PriorityItem<Character, Integer>> pq = new PriorityQueue<>(graph.getAdjacencyList().length());
        //An array of all the nodes in the graph
        Array<HashedObject<Character, HashTable<Character, Integer>>> nodeArray = graph.getAdjacencyList().asArray();
        //Path is a container class, holding a linked list for the nodes in the path and an int for the length
        Path<Character> shortestPath = new Path<>();
        //Populate the priority queue with initial values. Priority of 0 for the start node, "infinity" for the others.
        for(int i = 0; i<nodeArray.length; i++)
        {
            HashedObject<Character, HashTable<Character, Integer>> current = nodeArray.get(i);
            if(nodeArray.get(i).getKey()==startNode) pq.enqueue(new PriorityItem<>(startNode, 0));
            else pq.enqueue(new PriorityItem<>(current.getKey(), Integer.MAX_VALUE));
        }
        HashTable<Character, Boolean> fullyExplored = new HashTable<>(graph.getAdjacencyList().length());//false means discovered but not fully explored whereas true = fully explored.
        HashTable<Character, Character> previousNodes = new HashTable<>(nodeArray.length); //Hash table to contain the previous node in the shortest path
        while(!pq.isEmpty())
        {
            PriorityItem<Character, Integer> currentItem = pq.dequeue(); //Takes the current item from the front of the priority queue.
            if(currentItem.getValue()==startNode) //Edge case for start node
            {
                fullyExplored.add(startNode, false);
                previousNodes.add(startNode, null);
            }
            if(currentItem.getValue() == endNode) {

                shortestPath = compilePath(currentItem.getValue(), currentItem.getPriority(), previousNodes);//Once the end node has been found, compile the shortest path to it and exit the loop.
                break;
            }
            HashedObject<Character, HashTable<Character, Integer>> currentNode = new HashedObject<>(currentItem.getValue(), graph.getAdjacencyList().item(currentItem.getValue())); //Create the current node
            Array<HashedObject<Character, Integer>> connectionArray = currentNode.getValue().asArray(); //An Array of all the connections that node has
            for(int i = 0; i<connectionArray.length; i++) //Iterates through all the connections
            {
                HashedObject<Character, Integer> neighbour = connectionArray.get(i); //Gets the first connected node.
                if(!fullyExplored.contains(neighbour.getKey())) { fullyExplored.add(neighbour.getKey(), false);} //If the node is undiscovered, discover it.
                if(!fullyExplored.item(neighbour.getKey())) //If the node has not been fully explored:
                {
                    PriorityItem<Character, Integer> neighbourInPq = pq.get(pq.getIndex(new PriorityItem<>(neighbour.getKey(), 0))).Value(); //Gets the connected node's PriorityItem from the pq
                    //New Distance from start equals the distance from start to the current node plus the distance from the current node to the neighbouring node.
                    int newDistance = currentItem.getPriority() + graph.edgeWeight(currentItem.getValue(), neighbour.getKey());
                    int oldDistance = neighbourInPq.getPriority(); //Old distance is the neighbouring node's priority value.
                    if(newDistance<oldDistance) //If the new distance is shorter:
                    {
                        neighbourInPq.setPriority(newDistance); //Updates the neighbour's priority
                        pq.update(); //Refreshes the priority queue so that it becomes sorted again.
                        previousNodes.replace(neighbour.getKey(), currentItem.getValue()); //Set the neighbour's previous to the current node
                    }
                }
            }
            fullyExplored.replace(currentItem.getValue(), true); //The current node has been fully explored.
        }
        if(!shortestPath.contains(endNode)) //If the shortest path does not contain the end node, that means no path to the end node has been found.
        {
            throw new UnsupportedOperationException("Could not find a path to end node from start node.");
        }
        return shortestPath;
    }
    //This method traces back from the end node to the start node and inserts them into the path in the correct order
    private static Path<Character> compilePath(Character endNode, int lengthToEnd, HashTable<Character, Character> previousNodes) {

        LinkedList<Character> nodeList = new LinkedList<>();
        Character currentNode = endNode;
        while(previousNodes.item(currentNode)!=null) //Iterate through the nodes until the first node is reached, inserting the node to the front each time
        {
            if (nodeList.isEmpty()) nodeList.append(currentNode);
            else nodeList.insert(currentNode, 0);
            currentNode = previousNodes.item(currentNode);
        }
        nodeList.insert(currentNode, 0); //Insert the start node into the list to finish
        return new Path<>(nodeList, lengthToEnd); //Finally, return the path
    }
}