package solve.middle;

import org.junit.Test;
import prepared.TreeNode;

import java.util.List;

/**
 * 从中序与后序遍历序列构造二叉树
 *
 * @author wm
 * @see M102_BinaryTreeLevelOrderTraversal
 * @see M105_ConstructBinaryTreeFromPreIn
 */
public class M106_ConstructBinaryTreeFromInPost {

    /*
        中序遍历 inorder = [9,3,15,20,7]
        后序遍历 postorder = [9,15,7,20,3]
        返回如下的二叉树：
            3
           / \
          9  20
            /  \
           15   7
     */

    private M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();

    @Test
    public void test() {
        int[] post = {9, 15, 7, 20, 3};
        int[] in = {9, 3, 15, 20, 7};

        TreeNode treeNode = buildTree(in, post);

        List<List<Integer>> lists = tool.levelOrder2(treeNode);
        System.out.println(lists);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = create(inorder, postorder, 0, inorder.length - 1,
                0, postorder.length - 1);
        return root;
    }

    private TreeNode create(int[] inorder, int[] postorder, int inLeft, int inRight,
                            int postLeft, int postRight) {
        // 递归终止条件
        if (postLeft > postRight) {
            return null;
        }
        TreeNode treeNode = new TreeNode(postorder[postRight]);
        int k = 0;
        // 在中序遍历序列中找到后序遍历序列的第postRight个节点
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == postorder[postRight]) {
                k = i;
                break;
            }
        }
        // 左子树的元素个数
        int numLeft = k - inLeft;
        // 递归构建左右子树
        treeNode.left = create(inorder, postorder, inLeft, k - 1, postLeft,
                postLeft + numLeft - 1);
        treeNode.right = create(inorder, postorder, k + 1, inRight,
                postLeft + numLeft, postRight - 1);
        return treeNode;
    }

}
