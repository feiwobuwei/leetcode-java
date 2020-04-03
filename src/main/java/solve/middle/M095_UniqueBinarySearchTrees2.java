package solve.middle;

import solve.hard.H145_BinaryTreePostorderTraversal;
import org.junit.Test;
import prepared.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * @author wm
 * @see M096_UniqueBinarySearchTrees
 * @see M094_BinaryTreeInorderTraversal
 */
public class M095_UniqueBinarySearchTrees2 {

    /*
     * 输入: 3
     * 输出:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     *
     */

    /*
       以上的输出对应以下 5 种不同结构的二叉搜索树：

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
     */

    /**
     * 前序遍历
     */
    private M144_BinaryTreePreorderTraversal tool1 = new M144_BinaryTreePreorderTraversal();

    /**
     * 后续遍历
     */
    private H145_BinaryTreePostorderTraversal tool2 = new H145_BinaryTreePostorderTraversal();

    @Test
    public void test() {
        List<TreeNode> treeNodes = generateTrees(3);
        print(treeNodes);
    }

    private void print(List<TreeNode> treeNodes) {
        treeNodes.forEach((i) -> System.out.println(tool1.preorderTraversal(i)));
        System.out.println();
        treeNodes.forEach((i) -> System.out.println(tool2.postorderTraversal(i)));
    }

    /**
     * 递归 -- 99.49%
     * <p>
     * 自底向上的求解过程。
     * 1. 选出根结点后应该先分别求解该根的左右子树集合，也就是根的左子树有若干种，
     * 它们组成左子树集合，根的右子树有若干种，它们组成右子树集合。
     * 2. 然后将左右子树相互配对，每一个左子树都与所有右子树匹配，
     * 每一个右子树都与所有的左子树匹配。然后将两个子树插在根结点上。
     * 3. 最后，把根结点放入列表中。
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    private ArrayList<TreeNode> generateTrees(int left, int right) {
        ArrayList<TreeNode> res = new ArrayList<>();
        if (left > right) {
            res.add(null);
            return res;
        }
        for (int i = left; i <= right; i++) {
            // 以i作为根节点，左子树由[1,i-1]构成
            ArrayList<TreeNode> lefts = generateTrees(left, i - 1);
            // 右子树由[i+1, n]构成
            ArrayList<TreeNode> rights = generateTrees(i + 1, right);
            for (TreeNode leftNode : lefts) {
                for (TreeNode rightNode : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    // 存储所有可能性
                    res.add(root);
                }
            }
        }
        return res;
    }


}
