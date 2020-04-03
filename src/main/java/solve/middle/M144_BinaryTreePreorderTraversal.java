package solve.middle;

import solve.hard.H145_BinaryTreePostorderTraversal;
import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 *
 * @author minwei
 * @see M094_BinaryTreeInorderTraversal
 * @see H145_BinaryTreePostorderTraversal
 */
public class M144_BinaryTreePreorderTraversal {

    private int[] array = {6, 2, 8, 1, 4, -1, -1, -1, -1, 3};
    private BinaryTree bt = new BinaryTree(array, true);

    @Test
    public void test() {
        List<Integer> integers2 = preorderTraversal(bt.getRoot());
        integers2.forEach((i) -> System.out.print(i + " "));
    }

    @Test
    public void test2() {
        List<Integer> integers2 = preorderTraversal2(bt.getRoot());
        integers2.forEach((i) -> System.out.print(i + " "));
    }

    /**
     * 递归
     *
     * @param root 根节点
     * @return 中序遍历结果列表
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            if (root.left != null) {
                helper(root.left, res);
            }
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
    private List<Integer> preorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }


}
