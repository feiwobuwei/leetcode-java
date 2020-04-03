package solve.easy;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小高度
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * @author wm
 * @see E110_BalancedBinaryTree
 */
public class E111_MinimumDepthOfBinaryTree {

    private int[] arrays = {3, 9, 20, -1, -1, 15, 7};

    private BinaryTree binaryTree = new BinaryTree(arrays, true);

    @Test
    public void test1() {
        int minDepth = minDepth(binaryTree.getRoot());
        System.out.println(minDepth);
    }

    @Test
    public void test2() {
        int minDepth = minDepth2(binaryTree.getRoot());
        System.out.println(minDepth);
    }

    /**
     * 递归
     * <p>
     * time O(n)
     * space O(lgn)
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 只有一个根节点
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        int minDepth = Integer.MAX_VALUE;

        if (root.left != null) {
            minDepth = Math.min(minDepth(root.left), minDepth);
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth(root.right), minDepth);
        }
        return minDepth + 1;

    }

    /**
     * BFS 迭代
     * <p>
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 1;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode treeNode = queue.poll();
                assert treeNode != null;
                if (treeNode.left == null && treeNode.right == null) {
                    return result;
                }
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            result++;
        }
        return -1;
    }


}
