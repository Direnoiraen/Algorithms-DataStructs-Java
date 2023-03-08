package algorithm_tests;

import algorithms.Dijkstra;
import data_structs.array.Array;
import data_structs.graph.Graph;
import data_structs.graph.Path;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DijkstraTests {

    @Test
    public void superSimpleGraph()
    {
        Graph graph = new Graph(20);
        graph.addNodes('B', 'C', 'D', 'A');
        graph.addConnection('B', 'C', 5);
        graph.addConnection('B', 'D', 7);
        graph.addConnection('C', 'D', 2);
        graph.addConnection('C', 'A', 11);
        graph.addConnection('D', 'A', 3);
        System.out.println(graph);
        Path<Character> shortestPath = Dijkstra.dijkstra(graph, 'B', 'A');
        System.out.println(shortestPath);

    }

    @Test
    public void simpleGraph()
    {
        Graph graph = new Graph(20);
        graph.addNodes('A', 'B', 'C', 'D', 'E', 'F', 'G');
        graph.addConnections('A', new Character[]{'B', 'C'}, new Integer[]{4, 2});
        graph.addConnections('B', new Character[]{'C', 'D'}, new Integer[]{3, 7});
        graph.addConnections('C', new Character[]{'D'}, new Integer[]{3});
        graph.addConnections('D', new Character[]{'E', 'F'}, new Integer[]{1,2});
        graph.addConnections('E', new Character[]{'F', 'G'}, new Integer[]{5, 7});
        graph.addConnection('F', 'G', 5);
        Path<Character> shortestPath= Dijkstra.dijkstra(graph, 'A', 'G');
        System.out.println(graph);
        System.out.println(shortestPath);
        assertEquals(new Array<>(new Character[]{'A', 'C', 'D', 'F', 'G'}), shortestPath.asList().asArray());
        assertEquals(12, shortestPath.getLength());
    }

    @Test
    public void complexGraph()
    {
        Graph graph = new Graph(20);
        graph.addNodes('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
        graph.addConnections('D', new Character[]{'A', 'B', 'E', 'G', 'F', 'C'}, new Integer[]{8, 3, 9, 10, 4, 1});
        graph.addConnections('G', new Character[]{'E', 'H', 'J', 'I', 'F'}, new Integer[]{6, 3, 15, 5, 5});
        graph.addConnections('A', new Character[]{'B', 'C'}, new Integer[]{4, 5});
        graph.addConnections('E', new Character[]{'B', 'H'}, new Integer[]{12, 10});
        graph.addConnections('F', new Character[]{'C', 'I'}, new Integer[]{11, 11});
        graph.addConnections('J', new Character[]{'I', 'H'}, new Integer[]{8, 14});
        Path<Character> shortestPath= Dijkstra.dijkstra(graph, 'A', 'J');
        System.out.println(graph);
        System.out.println(shortestPath);
        assertEquals(new Array<>(new Character[]{'A','C','D','F','G','I','J'}), shortestPath.asList().asArray());
        assertEquals(28, shortestPath.getLength());
    }
}
