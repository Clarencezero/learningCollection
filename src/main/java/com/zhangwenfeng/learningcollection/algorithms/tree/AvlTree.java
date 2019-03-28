package com.zhangwenfeng.learningcollection.algorithms.tree;

public class AvlTree<T extends Comparable<? super T>> {
    private static final int ALLOWED_BALANCE = 1;
    private AvlNode<T> root;
    public AvlTree() {
        root = null;
    }



    private static class AvlNode<T> {
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        AvlNode(T theElement) {
            this(theElement, null, null);
        }

        public AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
            // 在初始的时候给了一个零值
            height = 0;
        }
    }

    /**
     * 获取某个节点的高度。需要注意的是null值的判断,如果为null的话,则返回-1
     */
    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    // 1. INSERT
    public void insert(T x) {
        root = insert(x, root);
    }
    public AvlNode<T> insert(T x, AvlNode<T> t) {

        if (t == null) return new AvlNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else;

        return balance(t);
    }

    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null) return t;
        // 左节点出现不平衡。
        if (height(t.left) - height(t.right) > ALLOWED_BALANCE) {
            // 左节点高度 - 右节点高度 > 允许高度范围
            if (height(t.left.left) >= height(t.left.right)) {
                // 左 左 情况 (右旋转)
                t = rotateWithLeftChild(t);
            } else {
                // 左 右 情况 (双旋转) 先左旋转,后右旋转
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_BALANCE) {
            if (height(t.right.right) > height(t.right.left)) {
                // 右 右 情况(左旋转)
                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }

        // 更新节点高度
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }


    /**
     * 单旋转之 右旋转
     * 注意: 必须先更新原top节点的高度,才能更新新top节点的高度
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> top) {
        // L节点
        AvlNode<T> l = top.left;

        // top节点指向L节点的右子树
        top.left = l.right;

        // l节点的右子树更新为top节点
        l.right = top;

        // 更新top节点的高度
        top.height = Math.max(height(top.left), height(top.right)) + 1;

        // 更新l节点的高度
        l.height = Math.max(height(l.left), top.height) + 1;
        return l;
    }

    /**
     * 单旋转之 左旋转
     */
    private AvlNode<T> rotateWithRightChild(AvlNode<T> top) {
        // L节点
        AvlNode<T> l = top.right;

        // 更新top right节点
        top.right = l.left;

        // 更新l的 left节点
        l.left = top;

        // 更新原top节点的高度(找了很久的BUG,就是忘记+1了........
        top.height = Math.max(height(top.left), height(top.right)) + 1;

        // 更新l节点的高度
        l.height = Math.max(top.height, height(l.right)) + 1;

        return l;
    }


    /**
     * 双旋转之 左右旋转(先左旋转、再右旋转)。向节点左子树的右节点插入元素
     */
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> top) {
        // 1. 先进行左旋转
        top.left = rotateWithRightChild(top.left);

        // 2. 再进行右旋转
        return rotateWithLeftChild(top);
    }


    private AvlNode<T> doubleWithRightChild(AvlNode<T> top) {
        // 1. 先进行右旋转
        top.right = rotateWithLeftChild(top.right);

        // 2. 再进行左旋转
        return rotateWithRightChild(top);
    }


    // REMOVE
    public void remove(T x) {
        root = remove(x, root);
    }

    // 删除操作要点:
    // 1. 为空,则直接返回,不做任何操作
    // 2. 如果待删除的节点同时存在左子对和右子树,则可以选择左子树最大的元素或右子树最大的元素做替换
    // 3. 平衡节点
    private AvlNode<T> remove(T x, AvlNode<T> t) {
        if (t == null) return t;

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            T minElement = findMin(t.right);
            t.right = remove(minElement, t.right);
        } else {
            t = t.left != null ? t.left : t.right;
        }
        return balance(t);
    }

    private T findMin(AvlNode<T> t) {
        if (t.left == null) return t.element;
        return findMin(t.left);
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    // CONTAINS
    private boolean contains(T x, AvlNode<T> t) {
        if (t == null) return false;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    // 树的遍历 之中序遍历
    public void printTreeByMidOrder() {
        if (isEmpty())
            System.out.println("Empty Tree!!!");
        printTreeByMidOrder(root);
    }

    /**
     * 递归遍历,简单就是化繁为简,要时刻想着他们都是一样的方式,不管父还是子。需要把这些行为抽象出来
     * 中序遍历: 先左子树 -> 节点 -> 右子树
     *
     * 使用中序遍历可以解决将项排序列出的问题
     */
    private void printTreeByMidOrder(AvlNode<T> t) {
        if (t != null) {
            printTreeByMidOrder(t.left);
            System.out.println(t.element + "  ");
            printTreeByMidOrder(t.right);
        }
    }


    /**
     * 前序遍历: 先遍历节点,再遍历左子树和右子树
     */
    public void printTreeByPreOrder() {
        if (isEmpty())
            System.out.println("Empty Tree!!!");
        printTreeByPreOrder(root);
    }

    private void printTreeByPreOrder(AvlNode<T> root) {
        if (root != null) {
            System.out.println(root.element);
            printTreeByPreOrder(root.left);
            printTreeByPreOrder(root.right);
        }
    }


    /**
     * 后序遍历
     * 使用后序遍历可以解决 如:
     *  - 计算一个节点的高度。因为顶点需要得知子节点的高度才能计算得到,需要一步一步从底往上走
     */
    public void printTreeByBackOrder() {
        if (isEmpty())
            System.out.println("Empty Tree!!!");
        printTreeByBackOrder(root);
    }

    private void printTreeByBackOrder(AvlNode<T> root) {
        if (root != null) {
            printTreeByBackOrder(root.left);
            printTreeByBackOrder(root.right);
            System.out.println(root.element);
        }
    }
}

