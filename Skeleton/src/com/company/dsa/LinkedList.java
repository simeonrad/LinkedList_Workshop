package com.company.dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public LinkedList(Iterable<T> iterable) {
        iterable.forEach(this::addLast);
    }

    @Override
    public void addFirst(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node newNode = new Node(value);
            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The List is empty!!!");
        }
        return head.value;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The List is empty!!!");
        }
        return tail.value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public int indexOf(T value) {
        Node current = head;
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (current.value == value) {
                index = i;
                break;
            }
            else {
                current = current.next;
            }
        }
        return index;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The List is empty!!!");
        }
        Node currentHead = head;
        head = currentHead.next;
        size--;
        return currentHead.value;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The List is empty!!!");
        }
        Node currentTail = tail;
        tail = currentTail.prev;
        size--;
        return currentTail.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }
}
