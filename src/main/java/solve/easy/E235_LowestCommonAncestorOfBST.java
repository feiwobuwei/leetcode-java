package solve.easy;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * * <p>
 * p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2
 * 因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * @author minwei
 * @see solve.middle.M236_LowestCommonAncestorOfBT
 */
public class E235_LowestCommonAncestorOfBST {

    int[] nums = {6, 2, 8, 0, 4, 7, 9, -1, -1, 3, 5};
    private BinaryTree binaryTree = new BinaryTree(nums, true);
    private TreeNode n1 = BinaryTree.search(2, binaryTree.getRoot());
    private TreeNode n2 = BinaryTree.search(8, binaryTree.getRoot());

    private TreeNode n3 = BinaryTree.search(4, binaryTree.getRoot());

    @Test
    public void test() {
        TreeNode node = lowestCommonAncestor(binaryTree.getRoot(), n1, n2);
        if (node != null) {
            System.out.println(node.val);
        }

        TreeNode node2 = lowestCommonAncestor(binaryTree.getRoot(), n3, n1);
        if (node2 != null) {
            System.out.println(node2.val);
        }
    }

    @Test
    public void test2() {
        TreeNode node = lowestCommonAncestor2(binaryTree.getRoot(), n1, n2);
        System.out.println(node.val);

        TreeNode node2 = lowestCommonAncestor2(binaryTree.getRoot(), n3, n1);
        System.out.println(node2.val);
    }

    /**
     * 递归 -- 35.55%
     * <p>
     * 保证p的值小于q
     * <p>
     * time O(lgn)
     * space O(1)
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val == q.val) {
            return null;
        }
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    /**
     * 迭代 -- 91.97%
     * <p>
     * 保证p的值小于q
     * <p>
     * time O(lgn)
     * space O(1)
     */
    private TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val == q.val) {
            return null;
        }
        if (p.val > q.val) {
            return lowestCommonAncestor2(root, q, p);
        }
        while (root.val < p.val || root.val > q.val) {
            if (root.val < p.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }
}
