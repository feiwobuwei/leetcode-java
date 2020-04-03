package solve.easy;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.LinkedList;

/**
 * 路径总和
 *
 * @author wm
 */
public class E112_PathSum {

    private int[] arr = {5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, -1, 1};

    @Test
    public void test() {
        BinaryTree binaryTree = new BinaryTree(arr, true);
        boolean b = hasPathSum(binaryTree.getRoot(), 22);
        System.out.println(b);

    }

    @Test
    public void test2() {
        BinaryTree binaryTree = new BinaryTree(arr, true);
        boolean b = hasPathSum2(binaryTree.getRoot(), 22);
        System.out.println(b);

    }

    /**
     * 递归
     *
     * @param root 树的根节点
     * @param sum  路径和
     * @return 是否存在该路径和
     */
    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }
        // 叶节点为边界条件
        // 如果left和right都是false,那么递归往上最终结果也是false
        // 只要有一个是true 那么结果就是true 然后一路回溯到根节点 就是对应路径
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        // 看看左子树有没有 sum - root.val 的路径和
        boolean left = (root.left != null) && hasPathSum(root.left, sum - root.val);
        // 看看右子树有没有 sum - root.val 的路径和
        boolean right = (root.right != null) && hasPathSum(root.right, sum - root.val);
        return left || right;
    }

    /**
     * BFS - 迭代
     */
    @SuppressWarnings({"ConstantConditions"})
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        LinkedList<TreeNode> nodeStack = new LinkedList<>();
        LinkedList<Integer> sumStack = new LinkedList<>();
        nodeStack.add(root);
        sumStack.add(sum - root.val);

        TreeNode node;
        int currSum;
        while (!nodeStack.isEmpty()) {
            node = nodeStack.pollLast();
            currSum = sumStack.pollLast();
            if ((node.right == null) && (node.left == null) && (currSum == 0)) {
                return true;
            }

            if (node.right != null) {
                nodeStack.add(node.right);
                sumStack.add(currSum - node.right.val);
            }
            if (node.left != null) {
                nodeStack.add(node.left);
                sumStack.add(currSum - node.left.val);
            }
        }
        return false;
    }


}
