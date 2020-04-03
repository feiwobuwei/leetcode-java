package solve.middle;

import org.junit.Test;
import prepared.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author wm
 * @see M102_BinaryTreeLevelOrderTraversal
 * @see M106_ConstructBinaryTreeFromInPost
 */
public class M105_ConstructBinaryTreeFromPreIn {

    /*
      根据一棵树的前序遍历与中序遍历构造二叉树。
      postorder = [3,9,20,15,7]
      inorder = [9,3,15,20,7]
          3
         / \
        9  20
          /  \
         15   7
    */

    private M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();

    @Test
    public void test() {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};

        TreeNode treeNode = buildTree2(pre, in);

        List<List<Integer>> lists = tool.levelOrder2(treeNode);
        System.out.println(lists);
    }

    @Test
    public void test2() {
        int[] pre = {6, 2, 1, 4, 3, 8};
        int[] in = {1, 2, 3, 4, 6, 8};
        TreeNode treeNode = buildTree(pre, in);

        List<List<Integer>> lists = tool.levelOrder2(treeNode);
        // [[6], [2, 8], [1, 4], [3]]
        System.out.println(lists);
    }

    /**
     * 非纯函数
     * 递归 -- 94.64%
     * <p>
     * 时间复杂度是O(n)
     * 空间复杂度是O(n)
     *
     * @param preorder 前序遍历结果
     * @param inorder  中序遍历结果
     * @return 根节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> inOrderHashMap = new HashMap<>(8);
        for (int i = 0; i < inorder.length; i++) {
            // 键为中序遍历元素值 值为元素索引
            inOrderHashMap.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inOrderHashMap);
    }

    /**
     * 前序数组游标 当到达末尾说明所有节点已使用完毕
     */
    private int global = 0;

    /**
     * 辅助方法
     *
     * @param preOrder 前序遍历结果
     * @param inStart  该次递归中 中序遍历数组中的头索引
     * @param inEnd    该次递归中 中序遍历数组中的尾索引
     * @param indexes  中序遍历键值对:元素值-索引
     * @return 最终返回树的根节点 而中间每次递归返回上一次方法调用构建节点的左子节点或右子节点或空
     */
    private TreeNode helper(int[] preOrder, int inStart, int inEnd,
                            Map<Integer, Integer> indexes) {
        if (inStart > inEnd || global >= preOrder.length) {
            return null;
        }
        // 找到中序遍历中 前序遍历第global个(第1次是0)元素 所对应的索引
        int idx = indexes.get(preOrder[global]);
        // 第一次找到的就是树的根节点(因为前序遍历是先根)
        // 构建一个节点,中序数组中根元素左边的元素都会成为左子树 右边的元素都会成为右子树

        // 以后每次数组-1位置的元素就是本次root的左子节点 数组+1位置的元素就是root的右子节点 (中序遍历性质)
        TreeNode root = new TreeNode(preOrder[global]);
        // 每使用掉一个元素 游标右移
        global++;
        root.left = helper(preOrder, inStart, idx - 1, indexes);
        root.right = helper(preOrder, idx + 1, inEnd, indexes);
        return root;
    }

    // ================================ 分隔线 ================================== //

    /**
     * 纯函数
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return create(preorder, inorder, 0, preorder.length - 1,
                0, inorder.length - 1);
    }

    private TreeNode create(int[] preorder, int[] inorder, int preLeft, int preRight,
                            int inLeft, int inRight) {
        // 递归终止条件
        if (preLeft > preRight) {
            return null;
        }

        TreeNode treeNode = new TreeNode(preorder[preLeft]);
        int k = 0;

        // 在中序遍历序列中找到前序遍历序列的第preLeft个节点
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == preorder[preLeft]) {
                k = i;
                break;
            }
        }
        // 左子树的元素个数
        int numLeft = k - inLeft;

        // 递归构建左右子树
        treeNode.left = create(preorder, inorder, preLeft + 1,
                preLeft + numLeft, inLeft, k - 1);
        treeNode.right = create(preorder, inorder, preLeft + numLeft + 1,
                preRight, k + 1, inRight);

        return treeNode;
    }


}
