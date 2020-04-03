package solve.easy;

import javafx.util.Pair;
import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树高度
 *
 * @author wm
 */
public class E104_MaximumDepthOfBinaryTree {

    int[] arrays = {3, 9, 20, -1, -1, 15, 7};
    private BinaryTree binaryTree = new BinaryTree(arrays, true);

    @Test
    public void test1() {
        int i = maxDepth(binaryTree.getRoot());
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = maxDepth2(binaryTree.getRoot());
        System.out.println(i);
    }


    /**
     * 递归
     * <p>
     * 树的题目 递归总比迭代快
     * time O(n)
     * space O(n)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 迭代
     * <p>
     * leetcode 需要引入 javafx.util.Pair;
     * 速度很慢 % 7.34
     * <p>
     * time O(n)
     * space O(n)
     */
    public int maxDepth2(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair<>(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            // 队列移除头元素
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int currentDepth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, currentDepth);
                stack.add(new Pair<>(root.left, currentDepth + 1));
                stack.add(new Pair<>(root.right, currentDepth + 1));
            }
        }
        return depth;
    }

}
