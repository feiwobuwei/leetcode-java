package solve.easy;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 *
 * @author wm
 */
public class E101_SymmetricTree {

    private int[] array = {1, 2, 2, 3, 4, 4, 3};
    private BinaryTree bt = new BinaryTree(array, false);

    @Test
    public void test1() {
        System.out.println(isSymmetric(bt.getRoot()));
    }

    @Test
    public void test2() {
        System.out.println(isSymmetric2(bt.getRoot()));
    }

    /**
     * 递归法
     * time:O(n) space:O(n)
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    /**
     * 迭代法 BFS
     * time:O(n) space: O(n)
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }


}
