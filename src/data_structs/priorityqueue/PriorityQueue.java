package data_structs.priorityqueue;

import data_structs.linkedlist.*;
import data_structs.array.Array;
/**
 *
 * @author gavin
 */
public class PriorityQueue<T extends Comparable<T>> {
    public LinkedList<T> queue;
    //Default is from low to high, inverse sort is from high to low
    private boolean inverseSort = false;
    public PriorityQueue(int maxSize)
    {
        queue = new LinkedList<>(maxSize);
    }
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    public boolean isFull()
    {
        return queue.isFull();
    }
    public Element<T> get(int i){ return queue.get(i);}
    public void enqueue(T value)
    {
        if(isFull()) throw new UnsupportedOperationException("Cannot add to a full queue.");
        if(isEmpty()) queue.append(value);
        else
        {
            int i = 0;
            while((i< queue.length())&&(compare(queue.peek(i), value)<0)) i++;
            queue.insert(value, i);
        }
    }

    public T dequeue() { return queue.pop();}

    @Override
    public String toString() {return queue.toString();}
    public boolean isInverseSort() { return inverseSort;}
    public void setInverseSort(boolean mode)
    {
        inverseSort = mode;
        this.update();
    }
    public void update(){
        LinkedList<T> temp = new LinkedList<>(queue);
        queue = new LinkedList<>(temp.MAX_SIZE);
        for(int i = 0; i<temp.length(); i++) this.enqueue(temp.peek(i));
    }
    private int compare(T itemA, T itemB)
    {
        if(inverseSort) return itemB.compareTo(itemA);
        else return itemA.compareTo(itemB);
    }
    public int getIndex(T item)
    {
        Array<T> arr = queue.asArray();
        if(item instanceof PriorityItem)
        {
            for(int i = 0; i<arr.length; i++)
            {
                if(((PriorityItem<?, ?>) arr.get(i)).getValue() == ((PriorityItem<?, ?>) item).getValue()){ return i;}
            }
        }
        else
        {
            for(int i = 0; i<arr.length; i++)
            {
                if(arr.get(i) == item){ return i;}
            }
        }
        throw new IllegalArgumentException("Item not found in priority queue.");
    }

}