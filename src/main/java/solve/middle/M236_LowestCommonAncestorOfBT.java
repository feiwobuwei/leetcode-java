package solve.middle;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

/**
 * 与235的区别是这里不是二叉搜索树 BST 而是二叉树
 * LCA问题
 *
 * @author minwei
 * @see solve.easy.E235_LowestCommonAncestorOfBST
 */
public class M236_LowestCommonAncestorOfBT {

    int[] nums = {3, 5, 1, 6, 2, 0, 8, -1, -1, 7, 4};
    private BinaryTree binaryTree = new BinaryTree(nums, true);
    private TreeNode n1 = BinaryTree.search2(5, binaryTree.getRoot());
    private TreeNode n2 = BinaryTree.search2(1, binaryTree.getRoot());

    private TreeNode n3 = BinaryTree.search2(4, binaryTree.getRoot());

    @Test
    public void test() {
        TreeNode node = lowestCommonAncestor(binaryTree.getRoot(), n1, n2);
        System.out.println(node.val);

        TreeNode node2 = lowestCommonAncestor(binaryTree.getRoot(), n3, n1);
        System.out.println(node2.val);
    }

    /**
     * 递归
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // p q是父子关系(可以不是直接连接)
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // p q 不同子树 OK
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            // 右边没有找到 都在左边
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }

    private TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }


}
