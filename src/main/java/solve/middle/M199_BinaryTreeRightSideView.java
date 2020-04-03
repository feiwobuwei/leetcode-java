package solve.middle;

import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的右视图
 *
 * @author minwei
 */
public class M199_BinaryTreeRightSideView {

    @Test
    public void test() {
        int[] nums = {1, 2, 3, -1, 5, -1, 4};
        BinaryTree binaryTree = new BinaryTree(nums, true);
        List<Integer> list = rightSideView(binaryTree.getRoot());
        System.out.println(list);
    }

    /**
     * 层次遍历
     * BFS
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (null != cur.left) {
                    queue.add(cur.left);
                }
                if (null != cur.right) {
                    queue.add(cur.right);
                }
            }
            // 只添加每层的最后一个值
            result.add(list.get(list.size() - 1));
        }
        return result;
    }
}
