package solve.middle;

import org.junit.Test;
import prepared.ListNode;
import prepared.TreeNode;

import java.util.List;

/**
 * 有序链表转换二叉搜索树
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5],
 *
 * @author minwei
 * @see solve.easy.E108_ConvertSortedArrayToBST
 */
public class M109_ConvertSortedListToBST {

    int[] test = {-10, -3, 0, 5, 9};
    private ListNode listNode = ListNode.ArrayToList(test);


    @Test
    public void test() {
        TreeNode treeNode = sortedListToBST(listNode);
        // 官方预期结果 [0, -3, 9, -10, null, 5],
        System.out.println("==========================");
        // [[0], [-3, 9], [-10, 5]]
        // [[0], [-10, 5], [-3, 9]]

        // 借用M102的层次遍历 查看结果
        M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();
        List<List<Integer>> lists = tool.levelOrder2(treeNode);
        System.out.println(lists);
    }

    @Test
    public void test2() {
        TreeNode treeNode = sortedListToBST2(listNode);
        // 借用M102的层次遍历 查看结果
        M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();
        List<List<Integer>> lists = tool.levelOrder2(treeNode);
        System.out.println(lists);
    }

    @Test
    public void test3() {
        TreeNode treeNode = sortedListToBST3(listNode);
        // 借用M102的层次遍历 查看结果
        M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();
        List<List<Integer>> lists = tool.levelOrder2(treeNode);
        System.out.println(lists);
    }

    /**
     * 递归
     * <p>
     * time O(n)
     * space O(n)
     */
    public TreeNode sortedListToBST(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // Find the solve.middle element for the list.
        ListNode mid = this.findMiddleElement(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return node;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;
    }


    private ListNode findMiddleElement(ListNode head) {

        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    /**
     * 递归 + 转成数组
     * 空间换时间
     * <p>
     * time O(n)
     * space O(n)
     */
    public TreeNode sortedListToBST2(ListNode head) {

        // TODO
        return null;

    }

    /**
     * 中序遍历模拟
     * 二分法+递归
     * <p>
     * time O(n)
     * space O(lgn)
     */
    public TreeNode sortedListToBST3(ListNode head) {

        int size = this.findSize(head);
        return convertListToBST(0, size - 1, head);
    }

    /**
     * 辅助方法
     * 获取一个链表的长度
     */
    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r, ListNode head) {
        // 编辑条件
        if (l > r) {
            return null;
        }

        int mid = (r - l) / 2 + l;

        // 首先对左半段递归
        TreeNode left = this.convertListToBST(l, mid - 1, head);

        // 处理当前节点
        TreeNode node = new TreeNode(head.val);
        node.left = left;
        //
        head = head.next;

        // 然后对右半段递归
        node.right = this.convertListToBST(mid + 1, r, head);
        return node;
    }

}
