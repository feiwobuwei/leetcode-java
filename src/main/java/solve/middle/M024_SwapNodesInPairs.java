package solve.middle;

import org.junit.Test;
import prepared.ListNode;

/**
 * 两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author wm
 * @see solve.easy.E206_ReverseLinkedList   前置
 * @see solve.hard.H025_ReverseNodesInkGroup  进阶
 */
public class M024_SwapNodesInPairs {

    @Test
    public void test() {
        ListNode listNode = ListNode.ArrayToList(new int[]{1, 2, 3, 4});

        ListNode result = swapPairs(listNode);
        ListNode.print(result);
    }

    @Test
    public void test2() {
        ListNode listNode = ListNode.ArrayToList(new int[]{1, 2, 3, 4, 5});

        ListNode result = swapPairs2(listNode);
        ListNode.print(result);
    }

    @Test
    public void test3() {
//        ListNode listNode = ListNode.ArrayToList(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = ListNode.ArrayToList(new int[]{1, 2, 3, 4, 5, 6});

        ListNode result = swapPairs3(listNode);
        ListNode.print(result);
    }


    // =================================================

    /**
     * 迭代 -- 100.00%
     * <p>
     * time: O(n)
     * space: O(1)
     *
     * @param head 初始链表的头部节点
     * @return 处理完链表的头部节点
     */
    public ListNode swapPairs(ListNode head) {
        // 只有一个节点返回自身,没有节点返回空
        if (head == null || head.next == null) {
            return head;
        }

        // 头节点前面的辅助哑节点
        ListNode dummy = new ListNode(0);

        dummy.next = head;
        ListNode l1 = dummy;
        ListNode l2 = head;

        // l1总是位于要交换的节点对的前一个节点
        // l2总是位于要交换的节点对的第一个节点
        while (l2 != null && l2.next != null) {
            ListNode nextStart = l2.next.next;
            l1.next = l2.next;
            l2.next.next = l2;
            l2.next = nextStart;
            l1 = l2;
            l2 = l2.next;
        }
        return dummy.next;
    }

    /**
     * 递归
     * <p>
     * time: O(n)
     * space: O(1)
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        head.next = swapPairs2(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }


    /**
     * 迭代 -- 100.00%
     * <p>
     * time: O(n)
     * space: O(1)
     *
     * @param head 初始链表的头部节点
     * @return 处理完链表的头部节点
     */
    public ListNode swapPairs3(ListNode head) {

        // 如果链表长度为0或者1 直接返回
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;

        // 定义2个辅助节点
        ListNode first = head;
        ListNode second = head.next;

        while (second != null) {
            ListNode tmp = second.next;

            first.next = second.next;
            second.next = first;
            pre.next = second;

            if (tmp == null) {
                break;
            }

            pre = first;
            first = tmp;
            second = tmp.next;
        }

        return dummy.next;
    }

}
