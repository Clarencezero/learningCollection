package com.zhangwenfeng.learningcollection.algorithms.tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表的实现技巧
 * 1. 全局定义链表长度、起始节点、尾结点。这两个结点永远都为null,不参与数据存储,只用来做标记位
 * 2. 链表初始化的时候,把以上两个节点初始化
 * 3. 链表添加是通过索引添加的 传入size()
 * 4. 实现Iterator边界问题: 添加了一个exceptionCount, 用做并发判断
 */
public class MyLinkedList<T> implements Iterable<T>{
    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;
    private static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;
        public Node(T data, Node<T> pre, Node<T> next) {
            this.data = data;
            this.prev = pre;
            this.next = next;
        }
    }

    public MyLinkedList() {
        clear();
    }

    public void clear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        this.theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, T x) {
        addBefore(getNode(idx), x);
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public T set (int idx, T newVal) {
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * 在某个节点上添加元素,有三个操作步骤:
     * 1. 创建当前新的节点
     * 2. 修改前节点指向新节点
     * 3. 修改后节点指向新节点
     * @param prev
     * @param value
     */
    private void addBefore(Node<T> prev, T value) {
        // 1、create new Node before prev,
        Node<T> newNode = new Node<>(value, prev.prev, prev);

        // 2、flush the pre node
        newNode.prev.next = newNode;

        // 3、flush the next node
        prev.prev = newNode;

        modCount++;
        theSize++;
    }


    /**
     * 删除某个节点,有两个步骤
     * 1. 修改前节点指向新节点
     * 2. 修改后节点指向新节点
     * @param p
     * @return
     */
    private T remove(Node<T> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * 获取某个索引的节点元素。利用二分思想,判断到底是从头开始遍历,还是从尾部开始遍历
     * @param idx
     * @return
     */
    private Node<T> getNode(int idx) {
        Node<T> p;
        if (idx > theSize || idx < 0)
            throw new IndexOutOfBoundsException("索引不存在");

        if (idx < size() / 2) {
            // 如果在前面,从头开始遍历
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }

        return p;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    public class LinkedListIterator implements Iterator<T> {
        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();

            if (!hasNext())
                throw new NoSuchElementException();

            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return  nextItem;
        }


        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }
    }

    public boolean contains(T value) {
        Iterator iterator = MyLinkedList.this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                return true;
            }
        }

        return false;
    }


}
