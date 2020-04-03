package solve.middle;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.List;

/**
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <pre>
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * </pre>
 * 将其展开为：
 * <pre>
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * </pre>
 *
 * @author minwei
 * @see M102_BinaryTreeLevelOrderTraversal
 */
public class M114_FlattenBinaryTreeToLinkedList {

    private int[] array = {1, 2, 5, 3, 4, -1, 6};
    private BinaryTree binaryTree = new BinaryTree(array, true);
    private M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();


    @Test
    public void test() {

        // 层次遍历工具
        List<List<Integer>> lists = tool.levelOrder2(binaryTree.getRoot());
        System.out.println(lists);

        System.out.println("=====================");
        flatten(binaryTree.getRoot());
        lists = tool.levelOrder2(binaryTree.getRoot());
        System.out.println(lists);
    }

    @Test
    public void test2() {

        List<List<Integer>> lists = tool.levelOrder2(binaryTree.getRoot());
        System.out.println(lists);

        System.out.println("=====================");
        flatten2(binaryTree.getRoot());
        lists = tool.levelOrder2(binaryTree.getRoot());
        System.out.println(lists);
    }


    /**
     * 1 将左子树插入到右子树的地方
     * 2 将原来的右子树接到左子树的最右边节点
     * 3 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     *
     * @param root 树根节点
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            // 左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode temp = root.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                // 将原来的右子树接到左子树的最右边节点
                temp.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;

            }
        }
    }

    /**
     * 巧妙利用全局变量
     */
    private TreeNode pre = null;

    /**
     * 递归
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten2(root.right);
        flatten2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

}
