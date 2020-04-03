package solve.middle;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

/**
 * 验证BST
 *
 * @author minwei
 */
public class M098_ValidateBinarySearchTree {

    private int[] test = {5, 1, 4, -1, -1, 3, 6};
    private int[] test2 = {2, 1, 4};

    @Test
    public void test() {
        BinaryTree binaryTree = new BinaryTree(test, true);
        boolean validBST = isValidBST(binaryTree.getRoot());
        System.out.println(validBST);
    }

    @Test
    public void test2() {
        BinaryTree binaryTree = new BinaryTree(test2, true);
        boolean validBST = isValidBST(binaryTree.getRoot());
        System.out.println(validBST);
    }


    /**
     * 递归
     * <p>
     * 遍历树的同时保留结点的上界与下界
     * 在比较时不仅比较子结点的值，也要与上下界比较
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTRec(root, null, null);
    }

    private boolean isValidBSTRec(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (!isValidBSTRec(node.right, val, upper)) {
            return false;
        }
        return isValidBSTRec(node.left, lower, val);
    }

}
