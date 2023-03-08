package data_structs.graph;
import data_structs.linkedlist.LinkedList;
@SuppressWarnings("unused")
public class Path<E extends Comparable<E>> {
    private final LinkedList<E> path;
    private int length;
    public Path() {
        path = new LinkedList<>();
    }
    public Path(LinkedList<E> path, int length) {
        this.path = path;
        this.length = length;
    }
    public void add(E node, int connectionLength) {
        path.append(node);
        length += connectionLength;
    }
    public boolean contains(E node) {
        return path.search(node);
    }
    public int getLength() {
        return length;
    }
    public LinkedList<E> asList() {
        return path;
    }
    @Override
    public String toString() {
        return String.format("Shortest Path: %s, Length: %s", path.asArray(), length);
    }
}
