package solve.easy;

import solve.middle.M102_BinaryTreeLevelOrderTraversal;
import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.List;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * 输入：
 * <pre>
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * </pre>
 * <p>
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * <p>
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，
 * 但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * @author minwei
 * @see M102_BinaryTreeLevelOrderTraversal
 */
public class E226_InvertBinaryTree {

    private M102_BinaryTreeLevelOrderTraversal tool =
            new M102_BinaryTreeLevelOrderTraversal();

    int[] nums = {4, 2, 7, 1, 3, 6, 9};
    private BinaryTree binaryTree = new BinaryTree(nums, false);

    @Test
    public void test() {
        TreeNode node = invertTree(binaryTree.getRoot());
        List<List<Integer>> lists = tool.levelOrder2(node);
        System.out.println(lists);
    }

    /**
     * 递归 - 100%
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 类似交换三段式
        TreeNode tempNode = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tempNode;
        return root;
    }
}
