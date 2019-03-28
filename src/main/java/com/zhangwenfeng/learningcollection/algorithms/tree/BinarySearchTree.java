package com.zhangwenfeng.learningcollection.algorithms.tree;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;
    public BinarySearchTree() {
        root = null;
    }

    public void makeEmepty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() throws UnderflowException {
        if (isEmpty()) throw new UnderflowException("findMin exception, Tree is empty.");
        return findMin(root).element;
    }

    public T findMax() throws UnderflowException {
        if (isEmpty()) throw new UnderflowException("findMax exception, Tree is empty.");
        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    private boolean contains(T x, BinaryNode<T> t) {
        // 节点判断大小 -> 小: 遍历左节点
        if (t == null) return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            // 遍历左节点
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) return null;
        else if (t.left == null) return t;
        else return findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null) return null;
        else if (t.right == null) return t;
        else return findMax(t.right);
    }

    /**
     * 使用递归完成二叉树插入过程。注意如下:
     * 1. 递归结束点为 null 值, 如果t为null, 则表明此刻应该新建一个节点
     * 2. 判断过程也是逐一比较,直到找到节点为null
     * 3. 如果值存在,则不做任何操作。重复元的插入可以通过在节点记录中保留一个附加域以指示发生的频率来处理。
     *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            这对整个树增加了附加空间,但是,对了比将重复信息放到树中要好(它将使得树的深度变得很大)
     * @param x
     * @param t
     * @return
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) return new BinaryNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else;

        return t;
    }

    /**
     * 二叉树的删除让我想到了分治,因为在递归删除的过程中, 需要把右子树的最小元素找到,并删除它
     * 二叉树的递归删除理解还是需要一定时间的。思路如下: 比如删除A节点,判断A节点右树是否还有元素, 如果有的话,先找到最小元素, 再递归删除这个最小的元素。终止点:
     * 1. 比较, 如果小于,递归删除左边,如果大于,递归删除右边
     * 2. 找到删除点,判断是否有双节点,如果有,则获取右节点最小值,再通过赋值将该元素删除
     *
     * 为什么找右子树的Element
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) return t;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            // 找到最小的做赋值操作
            t.element = this.findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            // 其实, 这是最简单的一步,如果不存在两个节点,就会跳转到这里, 判断左节中是否有数据,如果有,则指向左节点,如果没有,则指向右节点
            t = t.left != null ? t.left : t.right;
        }
        return t;
     }

    private void printTree(BinaryNode<T> t) {

    }

    private static class BinaryNode<T> {
        private T element;
        private BinaryNode<T> left;
        private BinaryNode<T> right;
        BinaryNode(T theElement) {
            this(theElement, null, null);
        }
        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
        }
    }



}
