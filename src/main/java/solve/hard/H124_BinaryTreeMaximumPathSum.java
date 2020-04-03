package solve.hard;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 示例 1:
 * 输入: [1,2,3]
 * <pre>
 *        1
 *       / \
 *      2   3
 * </pre>
 * 输出: 6
 * <p>
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 * <pre>
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * 输出: 42
 *
 * @author minwei
 */
public class H124_BinaryTreeMaximumPathSum {

    private int[] nums = {-10, 9, 20, -1, -1, 15, 7};

    /*
       -10
       / \
      9  20
        /  \
       15   7
     */
    private int max = Integer.MIN_VALUE;

    @Test
    public void test() {
        BinaryTree binaryTree = new BinaryTree(nums, true);
        int i = maxPathSum(binaryTree.getRoot());
        System.out.println(i);
    }

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        // 求的过程中考虑包含当前根节点的最大路径
        max = Math.max(max, root.val + left + right);

        // 只返回包含当前根节点和左子树或者右子树的路径
        return root.val + Math.max(left, right);
    }

}
