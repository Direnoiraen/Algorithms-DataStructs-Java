package data_structs.tree;

public class BinaryTree <T extends Comparable> {

    Node<T> root;

    public BinaryTree()
    {
        this.root = null;
    }

    public void add(T nodeValue)
    {
        if(root == null)
        {
            root = new Node<>(nodeValue);
        }
        else
        {
            add(root, nodeValue);
        }
    }

    public void add(Node<T> rootNode, T nodeValue)
    {
        Node<T> temp = new Node<>(nodeValue); //Creates a temporary node with the value outside the tree, so it can be compared with nodes in the tree.

        if(rootNode.compareTo(temp)==0)
        {
            throw new IllegalArgumentException("Cannot enter the same value into the tree twice.");
        }
        else if(rootNode.compareTo(temp)<0)
        {
            if(rootNode.getRight()==null)
            {
                rootNode.setRight(new Node<>(nodeValue));
            }
            else
            {
                add(rootNode.getRight(), nodeValue);
            }
        }
        else
        {
            if(rootNode.getLeft() == null)
            {
                rootNode.setLeft(new Node<>(nodeValue));
            }
            else
            {
                add(rootNode.getLeft(), nodeValue);
            }
        }
    }

    public static void main(String[] args) {

        BinaryTree<String> binTree = new BinaryTree<>();
        binTree.add("Jim");
        binTree.add("Kevin");
        binTree.add("Alice");
        binTree.add("Belinda");

        System.out.println("In order: ");
        binTree.printInOrder(binTree.root);
        System.out.println("\nPre order: ");
        binTree.printPreOrder(binTree.root);
        System.out.println("\nPost order: ");
        binTree.printPostOrder(binTree.root);
        System.out.println("\nSize: "+binTree.size());
    }

    public void printInOrder(Node<T> rootNode) {

        if(rootNode == null){ return;} //Pops the function call off of the stack if the node is empty.

        this.printInOrder(rootNode.getLeft()); //Recursively checks left subtree

        System.out.print(rootNode + ", "); //Visits the root node

        this.printInOrder(rootNode.getRight());//Recursively checks the right subtree


    }

    public void printPreOrder(Node<T> rootNode)
    {
        if(rootNode == null){ return;} //Pops the function call off of the stack if the node is empty.

        System.out.print(rootNode + ", "); //Visits the root node

        this.printPreOrder(rootNode.getLeft()); //Recursively checks left subtree

        this.printPreOrder(rootNode.getRight());//Recursively checks right subtree


    }

    public void printPostOrder(Node<T> rootNode)
    {
        if(rootNode == null){ return;} //Pops the function call off of the stack if the node is empty.

        this.printPreOrder(rootNode.getLeft()); //Recursively checks left subtree

        this.printPreOrder(rootNode.getRight());//Recursively checks right subtree

        System.out.print(rootNode + ", "); //Visits the root node

    }

    public int size()
    {
        return count(root);
    }

    public int count(Node<T> rootNode)
    {
        if(rootNode == null){ return 0;} //If the node equals null pop the function off of the stack and return 0;

        return (count(rootNode.getLeft()) + count(rootNode.getRight()) + 1); //Recursively counts each subtree and then adds one to account for the root node

    }



}

