package data_structs.array;
import java.util.Arrays;


public class Array <E extends Comparable<E>>{

    private Object[] arr;
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

    public E get(int i)
    {
        @SuppressWarnings("unchecked")
        final E e = (E) arr[i];
        return e;
    }

    public E[] getArray()
    {
        return (E[]) arr;
    }

    public void set(int i, E data)
    {
        arr[i] = data;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(arr);
    }

    public static <T extends Comparable<T>> Array<T> mergeSort(Array<T> arr)
    {
        Array<T> leftArr =  new Array<>((arr.length + 1) / 2);
        System.arraycopy(arr.getArray(), 0, leftArr.getArray(), 0, leftArr.length);

        Array<T> rightArr = new Array<>(arr.length - leftArr.length);
        System.arraycopy(arr.getArray(), leftArr.length, rightArr.getArray(), 0, rightArr.length);

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





}
