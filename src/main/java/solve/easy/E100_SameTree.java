package solve.easy;

import prepared.BinaryTree;
import prepared.TreeNode;

/**
 * 是否是相同的二叉树
 *
 * @author minwei
 */
public class E100_SameTree {

    /**
     * 递归法
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }


    public static void main(String[] args) {

        int[] test1 = {1, 2, 3};
        BinaryTree bt1 = new BinaryTree(test1, false);

        System.out.println(isSameTree(bt1.getRoot(), bt1.getRoot()));

        int[] test2 = {1, 2, 1};
        int[] test3 = {1, 1, 2};

        BinaryTree bt2 = new BinaryTree(test2, false);
        BinaryTree bt3 = new BinaryTree(test3, false);

        System.out.println(isSameTree(bt2.getRoot(), bt3.getRoot()));
    }
}
