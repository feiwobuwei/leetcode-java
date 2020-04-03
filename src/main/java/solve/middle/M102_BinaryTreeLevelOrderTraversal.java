package solve.middle;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次遍历
 * top-down 自顶而下
 *
 * @author wm
 * @see solve.easy.E107_BinaryTreeLevelOrderTraversal2
 * @see M103_BinaryTreeZigzagLevelOrderTraversal
 */
public class M102_BinaryTreeLevelOrderTraversal {

    private int[] arrays = {3, 9, 20, -1, -1, 15, 7};

    /**
     * 树内部有空节点
     */
    private BinaryTree binaryTree = new BinaryTree(arrays, true);

    @Test
    public void test1() {
        List<List<Integer>> lists = levelOrder(binaryTree.getRoot());
        System.out.println(lists);
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = levelOrder2(binaryTree.getRoot());
        System.out.println(lists);
    }

    /**
     * BFS
     * <p>
     * time:O(n)
     * space:O(n)
     *
     * @param root 根节点
     * @return 列表集合的列表
     */
    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 当前层数有几个节点
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    throw new IllegalArgumentException("出列元素为null");
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                list.add(cur.val);
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 递归实现 -- 92.59%
     * <p>
     * time:O(n)
     * space:O(n)
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        levelRecursion(root, result, 0);
        return result;
    }

    private void levelRecursion(TreeNode node, LinkedList<List<Integer>> result, int level) {
        if (node == null) {
            return;
        }
        // 每往下一层,就再添加一个ArrayList(记录新的这一行)
        if (result.size() < level + 1) {
            // 自顶向下 新的一层每次都加在尾部
            result.addLast(new ArrayList<>());
        }
        // 在哪一层就加入哪一行
        result.get(level).add(node.val);

        levelRecursion(node.left, result, level + 1);
        levelRecursion(node.right, result, level + 1);
    }
}
