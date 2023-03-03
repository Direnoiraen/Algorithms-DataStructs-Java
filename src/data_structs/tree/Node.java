package data_structs.tree;

public class Node<T extends Comparable> implements Comparable<Node>{

    private Node<T> left;
    private Node<T> right;
    private final T data;

    public Node(T data)
    {
        this.data = data;
        left = null;
        right = null;
    }

    @Override
    public String toString()
    {
        return this.getData().toString();
    }

    @Override
    public int compareTo(Node o) {
        return this.getData().compareTo(o.getData());
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}

