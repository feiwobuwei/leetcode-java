package solve.easy;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

/**
 * 平衡二叉树
 *
 * @author wm
 */
public class E110_BalancedBinaryTree {

    private int[] arrays = {3, 9, 20, -1, -1, 15, 7};

    /*
        3
       / \
      9  20
        /  \
       15   7
     */

    /**
     * 树内部有空节点
     */
    private BinaryTree binaryTree = new BinaryTree(arrays, true);

    @Test
    public void test1() {
        boolean balanced = isBalanced(binaryTree.getRoot());
        System.out.println(balanced);
    }


    @Test
    public void test2() {
        boolean balanced = isBalanced2(binaryTree.getRoot());
        System.out.println(balanced);
    }

    /**
     * 递归 从顶至底 -- 54.46%
     * <p>
     * 产生大量重复的节点访问和计算，最差情况下时间复杂度 O(N^2)
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 短路法 从底至顶 -- 100%
     *
     * 一旦有一个不平衡 出现了-1 从该点往上全部都会变为-1
     * 最终根节点也是-1
     */
    public boolean isBalanced2(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = depth(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

}
