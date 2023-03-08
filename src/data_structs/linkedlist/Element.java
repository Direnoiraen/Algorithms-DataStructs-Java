/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structs.linkedlist;
/**
 *
 * @author gavin
 *
 */
public class Element<T extends Comparable<T>> implements Comparable<Element<T>> {
    private final T value;
    private Element<T> previous;
    private Element<T> next;
    public Element(T value, Element<T> previous, Element<T> next)
    {
        this.value = value;
        this.previous = previous;
        this.next = next;           
    }
    @Override
    public String toString()
    {       
        return value.toString();
    }
    public T Value()
    {
        return value;
    }
    public Element<T> Previous()
    {
        return previous;                
    }
    public void Previous(Element<T> value)
    {
        previous = value;                
    }
    public Element<T> Next()
    {
        return next;            
    }
    public void Next(Element<T> value)
    {
        next = value;                
    }
    @Override
    public int compareTo(Element<T> o) {
        return this.Value().compareTo(o.Value());
    }
}

