package com.example;

/**
 * A singly linked list implementation of {@link List} for {@link Integer} elements.
 *
 * <p>Each element is stored in an inner static class {@link Node<E>} that holds a value and a reference
 * to the next node in the sequence.
 *
 */
public class LinkedList<E> implements List<E> {

    /**
     * Appends the specified element to the end of this list
     *
     * <p>Refuse to add null elements.
     *
     * @param e element to be appended to this list
     * @return {@code true}
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * <p>Refuses to add null elements.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @return {@code true}
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NullPointerException      if the specified element is null
     */
    public boolean add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>Refuses to add null elements.</p>
     *
     * @param e element to be appended to the end of this list
     * @throws NullPointerException if the specified element is null
     */
    public void addLast(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * <p>Refuses to add null elements.</p>
     *
     * @param e element to be inserted at the beginning of this list
     * @throws NullPointerException if the specified element is null
     */
    public void addFirst(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all elements from this list.
     *
     * <p>The list will be empty after this call returns.</p>
     */
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element, otherwise {@code false}
     */
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element, or {@code -1} if this list does not contain the element
     */
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements, otherwise {@code false}
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }
}
