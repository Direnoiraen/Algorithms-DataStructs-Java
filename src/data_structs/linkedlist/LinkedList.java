/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structs.linkedlist;

import com.sun.istack.internal.NotNull;

import data_structs.array.Array;

/**
 *
 * @author gavin
 */
public class LinkedList<T extends Comparable<T>>
{   //The front of the priority queue, e.g the 1st element
    private Element<T> front;
    private int length = 0;
    public final int MAX_SIZE;


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.append(5);
        list.insert(7, 1);
        list.insert(8, 2);
        list.insert(1, 0);
        list.insert(11, 0);
        System.out.println(list);

    }
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

    
    public Object[] asArray()
    {//Converts the linked list into a an Array
        Array<Element<T>> a = new Array<>(length());
        Element<T> current = front;
        for(int i = 0; i<a.length; i++)
        {
            a.set(i, current);
        }
        return a.getArray();
    }
    
    public boolean isEmpty()
    {//Return if the queue is empty
        return length == 0;
    }

    public boolean isFull() { return length == MAX_SIZE;}

    public void append(T value)
    {//Append to the end of the list 
        Element<T> current;
        Element<T> tail;
        
        if (front != null)
        {       
            current = front;// Start at the front of the LinkedList
            
            while (current.Next() != null)
            {//Iterate through elements in the LinkedList
                current = current.Next();             
            }
            
            tail = new Element<>(value, current, null);// Create new tail Element pointing back to the previous Tail
            current.Next(tail);//Update the end of the LinkedList to point to this new Element
        }
        else
        {//Add the front of the linked list
            front = new Element<>(value, null, null);
        }
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
            if(current.Value().equals(value)) {
                return index;
            }
        }
        throw new IllegalArgumentException();
    }

    public T get(int index)
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
                if(current.Value().equals(value))
                {
                    return true;
                }
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

        return output + current.toString() + " ";

    }

    public Element<T> getFront() {
        return front;
    }
}
