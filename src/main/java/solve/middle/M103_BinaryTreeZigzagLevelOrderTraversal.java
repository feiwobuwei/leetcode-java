package solve.middle;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 *
 * @author minwei
 * @see M102_BinaryTreeLevelOrderTraversal
 */
public class M103_BinaryTreeZigzagLevelOrderTraversal {

    private int[] arrays = {3, 9, 20, -1, -1, 15, 7};

    /**
     * 树内部有空节点
     */
    private BinaryTree binaryTree = new BinaryTree(arrays, true);


    @Test
    public void test() {
        List<List<Integer>> lists = zigzagLevelOrder(binaryTree.getRoot());
        System.out.println(lists);
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = zigzagLevelOrder2(binaryTree.getRoot());
        System.out.println(lists);
    }


    /**
     * 迭代 -- 100.00%
     * 对一些层进行反转即可。
     * <p>
     * 时间复杂度和空间复杂度均是O(n)，其中n是二叉树中的节点个数。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 0;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < qSize; i++) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            if ((index & 1) == 1) {
                Collections.reverse(list);
            }
            res.add(list);
            index++;
        }
        return res;
    }

    /**
     * 双栈
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 存取奇数行 正序
        Stack<TreeNode> stack1 = new Stack<>();
        // 存取偶数行 逆序 先右再左
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    TreeNode treeNode = stack1.pop();
                    list.add(treeNode.val);
                    // 压入偶栈
                    if (treeNode.left != null) {
                        stack2.push(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        stack2.push(treeNode.right);
                    }
                }
            } else {
                while (!stack2.isEmpty()) {
                    TreeNode treeNode = stack2.pop();
                    list.add(treeNode.val);
                    // 压入奇栈
                    if (treeNode.right != null) {
                        stack1.push(treeNode.right);
                    }
                    if (treeNode.left != null) {
                        stack1.push(treeNode.left);
                    }
                }
            }
            res.add(list);
        }
        return res;
    }
}
