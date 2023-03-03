package data_structs.hashing;

public class HashedObject<K extends Comparable<K>, E> implements Comparable<HashedObject<K, E>>{

    private final K key;
    private E value;
    private boolean isDeleted = false;


    public HashedObject(K key, E value) {
        this.key = key;
        this.value = value;
    }


    public E getValue() {
        E value = this.value;

        if(isDeleted())
        {
            value=null;
        }

        return value;
    }
    public K getKey() {
        return key;
    }

    public void delete()
    { isDeleted=true; }

    public boolean isDeleted()
    {
        return isDeleted;
    }

    public void setValue(E value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return String.format("%s, %s", this.key, this.value);
    }

    @Override
    public int compareTo(HashedObject<K, E> o) {
        return 0;
    }
}
