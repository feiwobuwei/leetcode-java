package solve.easy;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.*;

/**
 * 二叉树的层次遍历 II
 * bottom-up 自底而上
 *
 * @see solve.middle.M102_BinaryTreeLevelOrderTraversal
 *
 * @author wm
 */
public class E107_BinaryTreeLevelOrderTraversal2 {

    private int[] arrays = {3, 9, 20, -1, -1, 15, 7};

    /**
     * 树内部有空节点
     */
    private BinaryTree binaryTree = new BinaryTree(arrays, true);

   @Test
    public void test1() {
        List<List<Integer>> lists = levelOrderBottom(binaryTree.getRoot());
        System.out.println(lists);
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = levelOrderBottom2(binaryTree.getRoot());
        System.out.println(lists);
    }

    /**
     * BFS 宽度优先搜索
     *
     * <p>
     * 时间复杂度和空间复杂度均是O(n)
     *
     * @param root 根节点
     * @return 遍历结果
     */
    private static List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> listList = new ArrayList<>();

        if (root == null) {
            return listList;
        }

        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < qSize; i++) {
                TreeNode treeNode = queue.poll();
                // 防止取值时 NPE
                list.add(Objects.requireNonNull(treeNode).val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            stack.add(list);
        }
        // 用栈来逆序输出层序遍历结果
        while (!stack.isEmpty()) {
            listList.add(stack.pop());
        }
        return listList;
    }

    /**
     * 递归实现
     * <p>
     * time:O(n) space:O(n)
     * 执行用时 : 2 ms 在所有 Java 提交中击败了 98.21% 的用户
     */
    public static List<List<Integer>> levelOrderBottom2(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        levelRecursionBottom(root, result, 0);
        return result;
    }

    /**
     * 递归方法
     */
    private static void levelRecursionBottom(TreeNode node, LinkedList<List<Integer>> result, int level) {
        if (node == null) {
            return;
        }
        // 每往下一层,就再添加一个ArrayList(记录新的这一行)
        if (result.size() < level + 1) {
            // 自底向上 新的一层每次都加在部
            result.addFirst(new ArrayList<>());
        }
        // 在哪一层就加入哪一行
        int curLevel = result.size() - 1 - level;
        result.get(curLevel).add(node.val);

        levelRecursionBottom(node.left, result, level + 1);
        levelRecursionBottom(node.right, result, level + 1);

    }
}
