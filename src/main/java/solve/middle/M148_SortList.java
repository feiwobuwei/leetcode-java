package solve.middle;

import org.junit.Test;
import prepared.ListNode;

/**
 * 在 O(n logn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * <p>
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author minwei
 */
public class M148_SortList {

    @Test
    public void test() {
        ListNode listNode = ListNode.ArrayToList(new int[]{4, 2, 1, 3});
        ListNode node = sortList(listNode);
        ListNode.print(node);
    }

    @Test
    public void test2() {
        ListNode listNode = ListNode.ArrayToList(new int[]{4, 2, 1, 6, 5, 7, 8, 3});
        ListNode node = sortList(listNode);
        ListNode.print(node);
    }

    /**
     * 归并排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode countNode = head;
        // 计算出链表的长度
        int length = 0;
        while (countNode != null) {
            length++;
            countNode = countNode.next;
        }
        return sortList(head, length);
    }

    private ListNode sortList(ListNode head, int count) {
        // 递归结束条件
        if (count <= 1) {
            return head;
        }
        // 位于链表中点(奇数时 向下取整例如5是2)
        ListNode leftEnd = head;
        for (int i = 0; i < count / 2 - 1; i++) {
            leftEnd = leftEnd.next;
        }
        ListNode rightStart = leftEnd.next;
        // 断链
        leftEnd.next = null;
        // 合并两个已经排完序的链表
        // 第二个链表的长度为count - count / 2
        return merge(sortList(head, count / 2), sortList(rightStart, count - count / 2));
    }

    /**
     * 合并链表的递归法
     *
     * @see solve.easy.E021_MergeTwoSortedList
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head;
        if (l1.val <= l2.val) {
            head = l1;
            l1.next = merge(l1.next, l2);
        } else {
            head = l2;
            l2.next = merge(l1, l2.next);
        }
        return head;
    }
}
