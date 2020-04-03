package solve.easy;

import solve.middle.M102_BinaryTreeLevelOrderTraversal;
import prepared.TreeNode;

import java.util.List;

/**
 * 有序数组转换二叉搜索树
 *
 * @author minwei
 * @see solve.middle.M109_ConvertSortedListToBST
 */
public class E108_ConvertSortedArrayToBST {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};

        TreeNode treeNode = sortedArrayToBST(nums);

        // 官方预期结果 [0,-10,5,null,-3,null,9]
        System.out.println("==========================");
        // [[0], [-10, 5], [-3, 9]]

        // 借用M102的层次遍历 查看结果
        M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();
        List<List<Integer>> lists = tool.levelOrder2(treeNode);
        System.out.println(lists);
    }


    /**
     * 二分法 - 递归法
     * <p>
     * O(lgn)
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTRecursive(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = sortedArrayToBSTRecursive(nums, left, mid - 1);
        treeNode.right = sortedArrayToBSTRecursive(nums, mid + 1, right);
        return treeNode;
    }


}
