/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structs.hashing;

import data_structs.array.Array;

/**
 *
 * @author gavin
 */
public class HashTable<K extends Comparable<K>, E> implements IHash<K, E> {
    private final int MAX_SIZE;
    private final Array<HashedObject<K, E>> hashArray;
    private int size;

    public HashTable(int max)
    {
        this.MAX_SIZE = max;
        hashArray = new Array<>(MAX_SIZE);
        size = 0;
    }

    public int getSize(){ return size;}

    public Array<HashedObject<K,E>> getHashArray()
    {
        return this.hashArray;
    }

    public static void main(String[] args) {
        HashTable<Integer, HashTable<Character, Integer>> hash = new HashTable<>(4);
        hash.add(3, new HashTable<>(3));
        hash.add(6, new HashTable<>(3));
        hash.add(9, new HashTable<>(3));
        hash.add(23, new HashTable<>(3));
        System.out.println(hash);
    }

    public String[][] asArray()
    {
        String[][] strArray = new String[size][2]; //Declare an array of strings of size MAX_SIZE
        int i = 0;

        Array<HashedObject<K, E>> sorted = new Array<>(size);

        for(int j = 0; j<hashArray.length; j++) //Iterate through the hash array
        {
            HashedObject<K, E> current = hashArray.get(j);

            if(current!=null&&!current.isDeleted()) //If the current HashedObject has a value
            {
                sorted.set(i, current);
                i++;
            }

        }

        sorted = Array.mergeSort(sorted);

        for(int k = 0; k<sorted.length; k++)
        {
            strArray[k][0] = sorted.get(k).getKey().toString();
            strArray[k][1] = sorted.get(k).getValue().toString();
        }


        return strArray;

    }



    @Override
    public void add(K key, E value) {

        int index = hashIndex(key);
        final int INITIAL_INDEX = index;

        if(hashArray.get(index)==null)
        {
            hashArray.set(index, new HashedObject<>(key, value));
        }
        else if(hashArray.get(index).getKey()==key)
        {
            throw new IllegalArgumentException();
        }
        else if(hashArray.get(index).isDeleted())
        {
            hashArray.set(index, new HashedObject<>(key, value));
        }
        else
        {
            do {
                index = (index + 1)%MAX_SIZE;

                if(hashArray.get(index)==null||hashArray.get(index).isDeleted())
                {
                    hashArray.set(index, new HashedObject<>(key, value));
                    break;
                }
                if(hashArray.get(index).getKey()==key)
                {
                    throw new IllegalArgumentException();
                }


            }while(index!=INITIAL_INDEX);

            if(index==INITIAL_INDEX)
            {
                throw new UnsupportedOperationException();
            }
        }
        size++;

    }

    @Override
    public E item(K key) {
        int index = hashIndex(key);
        final int INITIAL_INDEX = index;

        do {
            if(hashArray.get(index)==null)
            {
                throw new IllegalArgumentException();
            }
            if(hashArray.get(index).getKey()!=key)
            {
                index = (index+1)%MAX_SIZE;
            }
            else
            {
                return hashArray.get(index).getValue();
            }
        }while(index!=INITIAL_INDEX);

        throw new IllegalArgumentException();
    }

    @Override
    public void delete(K key) {
        int index = hashIndex(key);
        final int INITIAL_INDEX = index;
        boolean isDeleted = false;

        do {
            if(hashArray.get(index)==null)
            {
                throw new IllegalArgumentException();
            }
            if(hashArray.get(index).getKey()==key)
            {
                hashArray.get(index).delete();
                isDeleted = true;
                break;
            }
            index = (index+1)%MAX_SIZE;

        }while(index!=INITIAL_INDEX);

        if(!isDeleted){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean contains(K key) {

        int index = hashIndex(key);
        boolean isContained = false;


        for(int i = 0; i<MAX_SIZE; i++)
        {
            if(hashArray.get(index)!=null&&hashArray.get(index).getKey()==key)
            {
                isContained = true;
                break;
            }
            index = (index+1)%MAX_SIZE;
        }

        return isContained;
    }



    @Override
    public int length() {
        int length = 0;

        for(int i = 0; i<hashArray.length; i++)
        {
            HashedObject<K, E> current = hashArray.get(i);
            if(current!=null&&!current.isDeleted())
            {
                length++;
            }
        }

        return length;
    }

    @Override
    public boolean isEmpty() {
        return length()==0;
    }

    private int hashIndex(K key)
    {
        int index = 0;

        if(key instanceof String)
        {

            String keyString = (String) key;

            for(int i = 0; i<keyString.length(); i++)
            {
                index = index + (int)(keyString.charAt(i));
            }
            index = index % MAX_SIZE;
        }
        else if(key instanceof Integer)
        {
            int intKey = (Integer) key;
            index = intKey % MAX_SIZE;
        }
        else if(key instanceof Character)
        {
            char charKey = (Character) key;
            index = ((int) charKey) % MAX_SIZE;
        }
        else
        {
            throw new IllegalArgumentException("Hash Table can only handle keys of type: String, Integer, Character.");
        }

        return index;

    }

    @Override
    public String toString()
    {
        String[][] strArray = this.asArray();
        String output = "{";

        for(String[] current:strArray)
        {
            if(current!=null)
            {
                output = String.format("%s '%s':%s, ", output, current[0], current[1]);
                if(current[1].contains("{"))
                {
                    output = output + "\n";
                }
            }

        }



        return output + "}";
    }

}