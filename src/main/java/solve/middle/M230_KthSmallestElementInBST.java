package solve.middle;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

/**
 * 二叉搜索树中第K小的元素
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * <pre>
 *  输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 *
 * </pre>
 * <p>
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，
 * 你将如何优化 kthSmallest 函数？
 *
 * @author minwei
 * @see M094_BinaryTreeInorderTraversal
 */
public class M230_KthSmallestElementInBST {

    int[] nums = {3, 1, 4, -1, 2};
    private BinaryTree binaryTree = new BinaryTree(nums, true);

    @Test
    public void test() {
        int i = kthSmallest(binaryTree.getRoot(), 2);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = kthSmallest2(binaryTree.getRoot(), 2);
        System.out.println(i);
    }

    private int i = 0;
    private int val = 0;

    /**
     * 中序遍历 提前终止
     */
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return val;
    }

    public void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inorder(root.left, k);
        if (k == ++i) {
            val = root.val;
        }
        inorder(root.right, k);
    }

    /**
     * 递归 - 不使用成员变量
     */
    public int kthSmallest2(TreeNode root, int k) {
        int leftCount = count(root.left);
        // 左子树有k-1个节点 那么当前节点就是第k小
        if (leftCount == k - 1) {
            return root.val;
        } else if (leftCount < k - 1) {
            // 左子树少于k-1个节点 那么第k小元素在当前节点的右子树
            return kthSmallest2(root.right, k - 1 - leftCount);
        } else {
            // 左子树多于k-1个节点 那么第k小 元素在左子树
            return kthSmallest2(root.left, k);
        }
    }

    /**
     * 辅助方法 统计一个节点的所有子节点数目 包含自己
     */
    public int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return count(node.left) + count(node.right) + 1;
    }

}
