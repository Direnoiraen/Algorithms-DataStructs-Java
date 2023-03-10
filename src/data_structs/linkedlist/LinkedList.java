package data_structs.linkedlist;
import com.sun.istack.internal.NotNull;
import data_structs.array.Array;
/**
 *
 * @author gavin
 */
public class LinkedList<T extends Comparable<T>>
{
    private Element<T> front;
    private int length = 0;
    public final int MAX_SIZE;
    public LinkedList(int maxSize)
    {
        this.MAX_SIZE = maxSize;
    }
    public LinkedList() { this.MAX_SIZE = Integer.MAX_VALUE;}
    public LinkedList(@NotNull LinkedList<T> list)
    {
        this.MAX_SIZE = list.MAX_SIZE;
        this.front = list.getFront();
        this.length = list.length();
    }
    public Array<T> asArray()
    {//Converts the linked list into an Array
        Array<T> output = new Array<>(length);
        Element<T> current = front;
        for(int i = 0; i<length; i++)
        {
            output.set(i, current.Value());
            current = current.Next();
        }
        return output;
    }
    public boolean isEmpty()
    {//Return if the queue is empty
        return length == 0;
    }
    public boolean isFull() { return length == MAX_SIZE;}
    public void append(T value)
    {//Append to the end of the list 
        Element<T> current = front;
        Element<T> tail;
        if (front != null)
        {
            while (current.Next() != null) current = current.Next();
            tail = new Element<>(value, current, null);// Create new tail Element pointing back to the previous Tail
            current.Next(tail);//Update the end of the LinkedList to point to this new Element
        }
        else front = new Element<>(value, null, null);
        length++;
    }
    public T remove(T value)
    {//Remove the first element that has a matching value, and reorganise the queue      
     //Throw an error if the value is not present
        Element<T> current, removed;
        current = front;

        while(current != null)
        {
            if(current.Value().equals(value))
            {
                removed = current;
                current.Previous().Next(current.Next());
                current.Next().Previous(current.Previous());
                length--;
                return removed.Value();
            }
            current = current.Next();
        }
        throw new IllegalArgumentException();
    }

    public T pop()
    {//Remove the 1st element from the queue and reorganise the queue
     //Throw an error if the queue is empty
        Element<T> removed;
        if(length() == 1)
        {
            removed = front;
            front = null;
            return removed.Value();
        }
        if(!isEmpty())
        {
            removed = front;
            front = front.Next();
            front.Previous(null);
            length--;
            return removed.Value();
        }
         throw new UnsupportedOperationException();
    }
    
    public T pop(int index)
    {//Remove from the queue the element at the specified index  reorganise the queue
     //Throw an error if the index is invalid
        Element<T> current = front;

        if(!(index<0)&&!(index>=length))
        {
            while(index>0)
            {
                current = current.Next();
                index--;
            }
            current.Previous().Next(current.Next());
            current.Next().Previous(current.Previous());
            length--;
            return current.Value();
        }
        throw new IndexOutOfBoundsException();  
    }
    
    public void insert(T value, int index)
    {//Insert the item into the correct position in the linked list      
     //Throw an error if the index is invalid

        Element<T> current = front;
        Element<T> added;

        if(index<0||index>length)
        {
            throw new IndexOutOfBoundsException();
        }
        else if(!isEmpty())
        {
            if(index == 0)
            {
                Element<T> temp = front;
                front = new Element<>(value, null, temp);
                temp.Previous(front);
            }
            else
            {
                for(int i = 0; i<index-1; i++)
                {
                    current = current.Next();
                }
                added = new Element<>(value, current, current.Next());
                if(current.Next()!=null) { current.Next().Previous(added);}
                current.Next(added);
            }
            length++;
        }
    }
    
    public int index(T value)
    {//Return the position of the first occurrence of the value in the linked list
     //Throw an error if the value does not exist 
        Element<T> current = front;
        int index = 0;
        if(!isEmpty())
        {
            while(current.Next()!=null&&!current.Value().equals(value))
            {
                current = current.Next();
                index++;
            }
            if(current.Value().equals(value)) return index;
        }
        throw new IllegalArgumentException();
    }

    public Element<T> get(int index)
    {
        Element<T> current = front;
        int currentIndex = 0;
        if(isEmpty()||index>=MAX_SIZE||index<0) throw new IllegalArgumentException();
        if(index == currentIndex) return current;
        while(current.Next()!=null&&index!=currentIndex)
        {
            current = current.Next();
            currentIndex++;
        }
        if(index == currentIndex) return current;
        throw new IllegalArgumentException();
    }

    public T peek(int index)
    {
        Element<T> current = front;
        int currentIndex = 0;
        if(isEmpty()||index>=MAX_SIZE||index<0) throw new IllegalArgumentException();
        if(index == currentIndex) return current.Value();
        while(current.Next()!=null&&index!=currentIndex)
        {
            current = current.Next();
            currentIndex++;
        }
        if(index == currentIndex) return current.Value();
        throw new IllegalArgumentException();
    }
    public int length()
    {//Return the number of elements in the Linked List
        return length;
    }
    public boolean search(T value)
    {//Return true if the value exists in the Linked List
        Element<T> current = front;
        if(!isEmpty())
        {
            for(int i = 0; i< length; i++)
            {
                if(current.Value().equals(value)) return true;
                current = current.Next();
            }
        }
        return false;
    }
    @Override
    public String toString() {
        String output = "";
        if(front == null) return output;
        Element<T> current = front;
        while(current.Next()!=null)
        {
            output = String.format("%s%s ", output, current);
            current = current.Next();
        }
        return output + current + " ";
    }
    public Element<T> getFront() {
        return front;
    }
}
