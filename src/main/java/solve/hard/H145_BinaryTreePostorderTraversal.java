package solve.hard;

import solve.middle.M094_BinaryTreeInorderTraversal;
import solve.middle.M144_BinaryTreePreorderTraversal;
import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的后续遍历
 *
 * @author minwei
 * @see M094_BinaryTreeInorderTraversal
 * @see M144_BinaryTreePreorderTraversal
 */
public class H145_BinaryTreePostorderTraversal {

    private int[] array = {6, 2, 8, 1, 4, -1, -1, -1, -1, 3};
    private BinaryTree bt = new BinaryTree(array, true);

    @Test
    public void test() {
        List<Integer> integers2 = postorderTraversal(bt.getRoot());
        integers2.forEach((i) -> System.out.print(i + " "));
    }

    @Test
    public void test2() {
        List<Integer> integers2 = postorderTraversal2(bt.getRoot());
        integers2.forEach((i) -> System.out.print(i + " "));
    }

    /**
     * 递归 -- 99.67%
     *
     * @param root 根节点
     * @return 中序遍历结果列表
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            if (root.right != null) {
                helper(root.right, res);
            }
            res.add(root.val);
        }
    }

    /**
     * 迭代 -- 58.83%
     * <p>
     * time: O(n)
     * space: O(n)
     */
    private List<Integer> postorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 后面的结果每次都要添加到头部(和前序反过来) 这样前面加入的被挤到后面
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }

        }
        return output;
    }
}
