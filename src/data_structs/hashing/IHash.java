/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structs.hashing;

/**
 *
 * @author dw
 */
public interface IHash<K extends Comparable<K>, E> {
    void add(K Key, E Value);
    void delete(K Key);
    E item(K Key);
    boolean contains(K Key);
    int length();
    boolean isEmpty();    
}
