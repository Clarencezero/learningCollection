package com.zhangwenfeng.learningcollection.algorithms.tree;


import java.util.Iterator;
import java.util.Stack;

public class UnidrectionalLinkList<T> {
    private Node<T> head;
    private Node<T> next;
    private int size;
    private class Node<T> {
        T value;
        Node<T> next;
        public Node() {}
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    private class Itr implements Iterator<T>{
        Node<T> cur;
        Node<T> pre;
        public Itr() {
            cur = head;
        }

        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            Node<T> node = cur;
            cur = node.next;
            return node.value;
        }
    }

    // 1、添加
    public void put(T value) {
        if (head == null) {
            head  = new Node<>(value, null);
            next = head;
        } else {
            Node<T> node = new Node<>(value, null);
            next.setNext(node);
            next = node;
        }
        size++;
    }

    // 2、遍历
    public void printAllElements() {
        if (head == null) {
            System.out.println("No Elements!!!");
            return;
        }
        Node<T> cur = head;
        do {
            System.out.println(cur);
            cur = cur.next;
        } while (cur != null);

    }

    // 3、获取总数
    public int getSize() {
        return size;
    }

    // 4、迭代器
    public Iterator<T> iterator() {
        return new Itr();
    }

    // 5-1、单链表反转(利用stack栈)
    public void reverseLinkList() {
        // 1、遍历,放到栈里面
        Stack<Node<T>> stack = new Stack<>();
        Node<T> cur = head;
        do {
            Node<T> node = new Node<>(cur.value, null);
            stack.push(node);
            cur = cur.next;
        } while (cur != null);

        head = stack.pop();
        cur = head;
        while (!stack.empty()) {
                cur.next = stack.pop();
                cur = cur.next;
        }
    }
    // 5-2、单链表反转(利用递归方式)
    public void reverseLinkListByRecur() {
        Node<T> head1 = reverseLinkListByRecursive(head);
        System.out.println(head1);

    }
    private Node<T> reverseLinkListByRecursive(Node<T> node) {
        if (node.next == null || node == null) {
            return node;
        }
        Node<T> newHead = reverseLinkListByRecursive(node.next);
//        node.next.next = node;
        newHead.next = node;
        node.next = null;
        return newHead;
    }

}
