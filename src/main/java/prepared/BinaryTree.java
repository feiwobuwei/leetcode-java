package prepared;

import java.util.HashMap;
import java.util.Map;

/**
 * 辅助 二叉树类
 * <p>
 * 下标从0开始存储，则编号为i的结点的主要关系为
 * 双亲：向下取整 （(i-1)/2）
 * 左孩子：2i+1
 * 右孩子：2i+2
 *
 * @author minwei
 */
public class BinaryTree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 构造器
     *
     * @param array 输入数组
     * @param b     false代表没有空节点,true代表树内有空节点
     */
    public BinaryTree(int[] array, boolean b) {
        if (b) {
            root = createBinaryTreeByArray2(array, 0);
        } else {
            root = createBinaryTreeByArray(array, 0);
        }
    }

    /**
     * 根据数组创建二叉树(非BST)
     * 树内部无空节点
     *
     * @param array 输入数组
     * @param index 数组索引
     * @return 每次返回当前节点 最终返回根节点
     */
    private static TreeNode createBinaryTreeByArray(int[] array, int index) {
        TreeNode tn = null;
        if (index < array.length) {
            int value = array[index];
            tn = new TreeNode(value);
            tn.left = createBinaryTreeByArray(array, 2 * index + 1);
            tn.right = createBinaryTreeByArray(array, 2 * index + 2);
        }
        return tn;
    }

    /**
     * 根据数组创建二叉树(非BST)
     * 树内部有空节点
     *
     * @param array 输入数组
     * @param index 当前递归节点的索引
     * @return 最终生成的树的根节点
     */
    private static TreeNode createBinaryTreeByArray2(int[] array, int index) {
        if (array[0] == -1) {
            return null;
        }
        TreeNode tn = null;
        if (index < array.length) {
            if (array[index] != -1) {
                int value = array[index];
                tn = new TreeNode(value);
                int[] cur = count(array, index + 1);
                tn.left = createBinaryTreeByArray2(array, 2 * index + 1 - 2 * cur[index]);
                tn.right = createBinaryTreeByArray2(array, 2 * index + 2 - 2 * cur[index]);
            }
        }
        return tn;
    }

    /**
     * 对一个数组 统计其到指定位置 元素-1出现的次数
     */
    private static int[] count(int[] array, int limit) {
        int init = 0;
        int[] initArray = new int[limit];

        for (int i = 0; i < limit; i++) {
            if (array[i] == -1) {
                init++;
            }
            initArray[i] = init;
        }
        return initArray;
    }

    /**
     * BST查找方法
     */
    public static TreeNode search(int target, TreeNode root) {
        if (root == null || target == root.val) {
            return root;
        }
        if (target < root.val) {
            return search(target, root.left);
        } else {
            return search(target, root.right);
        }
    }

    /**
     * BT查找方法
     */
    public static TreeNode search2(int target, TreeNode root) {
        traversal(root);
        return nodes.get(target);

    }

    private static Map<Integer, TreeNode> nodes = new HashMap<>();

    private static void traversal(TreeNode root) {

        if (root == null) {
            return;
        }
        nodes.put(root.val, root);
        if (root.left != null) {
            nodes.put(root.left.val, root.left);
            traversal(root.left);
        }
        if (root.right != null) {
            nodes.put(root.right.val, root.right);
            traversal(root.right);
        }
    }


}


