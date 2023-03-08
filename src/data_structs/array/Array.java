package data_structs.array;

import data_structs.hashing.HashedObject;

import java.util.Arrays;
import java.util.HashSet;


public class Array <E extends Comparable<E>> {


    private final Object[] arr;
    public final int length;


    public Array(int length) {
        this.length = length;
        arr = new Object[length];
    }

    public Array(E[] arr)
    {
        this.arr = arr;
        this.length = arr.length;
    }

    public Array(Array<E> arr)
    {
        this.arr = arr.arr;
        this.length = arr.length;
    }

    public E get(int i)
    {
        @SuppressWarnings("unchecked")
        final E e = (E) arr[i];
        return e;
    }

    public void set(int i, E data)
    {
        arr[i] = data;
    }

    public Array<E> subArray(int initialIndex, int length)
    {
        if(length==0) return new Array<>(0);
        if(initialIndex<0||initialIndex+length>this.length) throw new IllegalArgumentException();

        Array<E> output = new Array<>(length);
        int index = 0;
        for(int i = initialIndex; i<initialIndex+length; i++)
        {
            output.set(index, this.get(i));
            index++;
        }

        return output;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(arr);
    }

    public static <T extends Comparable<T>> Array<T> mergeSort(Array<T> arr)
    {
        Array<T> leftArr =  arr.subArray(0, (arr.length + 1) / 2);

        Array<T> rightArr = arr.subArray(leftArr.length, arr.length- leftArr.length);

        if(arr.length > 2)
        {
            leftArr = mergeSort(leftArr);
            rightArr = mergeSort(rightArr);
        }

        return merge(leftArr, rightArr);


    }


    private static <T extends Comparable<T>> Array<T> merge(Array<T> arr1, Array<T> arr2)
    {
        int p1 = 0;
        int p2 = 0;
        Array<T> merged =  new Array<>(arr1.length+ arr2.length);

        for(int i = 0; i< merged.length; i++)
        {
            if((p1>= arr1.length))
            {
                merged.set(i, arr2.get(p2));
                p2++;
            }
            else if(p2 >= arr2.length)
            {
                merged.set(i, arr1.get(p1));
                p1++;
            }
            else if(arr1.get(p1).compareTo(arr2.get(p2)) < 0)
            {
                merged.set(i, arr1.get(p1));
                p1++;
            }
            else
            {
                merged.set(i, arr2.get(p2));
                p2++;
            }
        }

        return merged;

    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Array)) return false;
        else if (((Array<?>) o).length!=this.length) return false;
        else
        {
            for(int i = 0; i<this.length; i++)
            {
                if(this.get(i)!=((Array<?>) o).get(i)) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Array<HashedObject<Character, Integer>> arr = new Array<>(6);
        arr.set(0, new HashedObject<>('A', 6));
        arr.set(1, new HashedObject<>('B', 8));
        arr.set(2, new HashedObject<>('E', 5));
        arr.set(3, new HashedObject<>('C', 3));
        arr.set(4, new HashedObject<>('D', 2));
        arr.set(5, new HashedObject<>('a', 1));
        System.out.println(arr.subArray(0,6));
        Array<HashedObject<Character, Integer>> larr = arr.subArray(0, (arr.length+1)/2);
        System.out.println(larr);
        System.out.println(arr.subArray(larr.length, arr.length - larr.length));
        Array<HashedObject<Character, Integer>> sort = mergeSort(arr);
        System.out.println(sort);
        System.out.println(larr.equals(arr));
    }


}
