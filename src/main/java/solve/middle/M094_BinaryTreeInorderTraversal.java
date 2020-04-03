package solve.middle;


import solve.hard.H145_BinaryTreePostorderTraversal;
import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 *
 * @author wm
 * @see M144_BinaryTreePreorderTraversal
 * @see H145_BinaryTreePostorderTraversal
 */
public class M094_BinaryTreeInorderTraversal {


    private int[] array = {6, 2, 8, 1, 4, -1, -1, -1, -1, 3};
    private BinaryTree bt = new BinaryTree(array, true);

    @Test
    public void test() {
        List<Integer> integers2 = inorderTraversal(bt.getRoot());
        integers2.forEach((i) -> System.out.print(i + " "));
    }

    @Test
    public void test2() {
        List<Integer> integers2 = inorderTraversal2(bt.getRoot());
        integers2.forEach((i) -> System.out.print(i + " "));
    }

    /**
     * 递归
     * <p>
     * time: O(n)
     * space: O(n)
     *
     * @param root 根节点
     * @return 中序遍历结果列表
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }

    /**
     * 迭代
     * <p>
     * time: O(n)
     * space: O(n)
     */
    private List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

}
