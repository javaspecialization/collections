package com.example;

public interface List<E> {
    /**
     * Appends the specified element to the end of this list
     *
     * <p>Refuse to add null elements.
     *
     * @param e element to be appended to this list
     * @return {@code true}
     * @throws NullPointerException if the specified element is null
     */
    boolean add(E e);

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
    boolean add(int index, E element);

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>Refuses to add null elements.</p>
     *
     * @param e element to be appended to the end of this list
     * @throws NullPointerException if the specified element is null
     */
    void addLast(E e);

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * <p>Refuses to add null elements.</p>
     *
     * @param e element to be inserted at the beginning of this list
     * @throws NullPointerException if the specified element is null
     */
    void addFirst(E e);

    /**
     * Removes all elements from this list.
     *
     * <p>The list will be empty after this call returns.</p>
     */
    void clear();

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element, otherwise {@code false}
     */
    boolean contains(Object o);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    E get(int index);

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element, or {@code -1} if this list does not contain the element
     */
    int indexOf(Object o);

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements, otherwise {@code false}
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();
}
