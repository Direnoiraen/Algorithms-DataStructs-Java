package data_structs.priorityqueue;

import data_structs.linkedlist.*;

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

    public static void main(String[] args)
    {

        PriorityQueue<Integer> pq = new PriorityQueue<>(4);

        pq.enqueue(2);
        System.out.println(pq);
        pq.enqueue(1);
        System.out.println(pq);
        System.out.println(pq);
        System.out.println(pq.isFull());
        pq.dequeue();
        System.out.println(pq.isFull());
        System.out.println(pq);
        pq.enqueue(3);
        System.out.println(pq);
        System.out.println(pq.isFull());
        pq.enqueue(9);
        pq.enqueue(7);
        System.out.println(pq);
        pq.setInverseSort(true);
        System.out.println(pq);
        pq.setInverseSort(false);
        System.out.println(pq);

    }

    public void enqueue(T value)
    {
        if(isFull()) throw new UnsupportedOperationException("Cannot add to a full queue.");

        if(isEmpty())
        {
            queue.append(value);
        }
        else
        {
            int i = 0;
            while((i< queue.length())&&(compare(queue.get(i), value)<0))
            {
                i++;
            }
            queue.insert(value, i);
        }
    }

    public void dequeue()
    {
        queue.pop();
    }

    @Override
    public String toString()
    {
        return queue.toString();
    }

    public boolean isInverseSort() { return inverseSort;}

    public void setInverseSort(boolean mode)
    {
        inverseSort = mode;
        LinkedList<T> temp = new LinkedList<>(queue);
        queue = new LinkedList<>(temp.MAX_SIZE);

        for(int i = 0; i<temp.length(); i++)
        {
            this.enqueue(temp.get(i));
        }

    }

    private int compare(T itemA, T itemB)
    {
        if(inverseSort)
        {
            return itemB.compareTo(itemA);
        }
        else
        {
            return itemA.compareTo(itemB);
        }
    }

}