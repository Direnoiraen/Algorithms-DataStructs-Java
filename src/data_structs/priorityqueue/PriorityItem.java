package data_structs.priorityqueue;
public class PriorityItem <E, P extends Comparable<P>> implements Comparable<PriorityItem<E, P>>{
    private E value;
    private P priority;
    public PriorityItem(E value, P priority)
    {
        this.value = value;
        this.priority = priority;
    }
    @Override
    public int compareTo(PriorityItem<E, P> o) {
        return this.priority.compareTo(o.priority);
    }
    public P getPriority()
    {
        return priority;
    }
    public void setPriority(P priority)
    {
        this.priority = priority;
    }
    public E getValue()
    {
        return value;
    }
    public void setValue(E value)
    {
        this.value = value;
    }
    @Override
    public String toString()
    {
        return String.format("(%s, %s)", value.toString(), priority.toString());
    }
}
