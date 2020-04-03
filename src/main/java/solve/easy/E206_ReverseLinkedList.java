package solve.easy;

import org.junit.Test;
import prepared.ListNode;

/**
 * 反转单链表
 * 输入 1->2->3->4->5
 *
 * @author minwei
 * @see solve.middle.M024_SwapNodesInPairs
 * @see solve.hard.H025_ReverseNodesInkGroup
 */
public class E206_ReverseLinkedList {

    @Test
    public void testIterative() {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = ListNode.ArrayToList(arr);
        ListNode result = reverseList(head);
        ListNode.print(result);
    }

    @Test
    public void testRecursive() {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = ListNode.ArrayToList(arr);
        ListNode result = reverseList2(head);
        ListNode.print(result);
    }


    /**
     * Iterative 迭代
     * time O(n) space O(1)
     *
     * @param head 头节点
     * @return 反转链表的头节点
     */
    public ListNode reverseList(ListNode head) {
        // 辅助指针 永远位于 操作节点(改变指向) 的前一个节点
        ListNode prev = null;
        // 辅助指针 永远位于 操作节点(改变指向) 先让其位于头节点
        ListNode curr = head;
        // 当 curr ==null 时 退出循环 prev就位于最后一个节点位置
        while (curr != null) {
            // 1 首先必须预存下一个节点。否则改变当前节点对其的引用后 其会因为无对象引用而被GC
            ListNode nextTemp = curr.next;
            // 2.将当前节点的下一个指针更改为指向其前一个元素
            curr.next = prev;
            // 3.prev 向后移动
            prev = curr;
            // 4 curr 向后移动 继续遍历
            curr = nextTemp;
        }
        // 5 .最后返回新的head
        return prev;
    }

    /**
     * Recursive 递归
     * time O(n)
     * space O(n)
     *
     * @param head 头节点
     * @return 反转链表的头节点
     */
    public ListNode reverseList2(ListNode head) {

        // 空链表或者只有一个节点的链表 无需逆转
        // 同时也是递归边界条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode resultNode = reverseList2(head.next);
        head.next.next = head;
        head.next = null;

        // resultNode 就是新链表的头节点(也是原链表的尾节点)
        return resultNode;
    }

}