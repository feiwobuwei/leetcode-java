package solve.middle;

import org.junit.Test;
import prepared.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * <p>
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * @author minwei
 */
public class M061_RotateList {

    @Test
    public void test() {
        ListNode listNode = ListNode.ArrayToList(new int[]{1, 2, 3, 4, 5});
        ListNode result = rotateRight(listNode, 2);
        ListNode.print(result);
    }


    /**
     * 直接找准在哪个点进行切断 - 99.90%
     * <p>
     * time O(n)
     * space O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 哑节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        int len = 0;
        // 获取链表长度
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        k = k % len;
        if (k == 0) {
            return head;
        }
        ListNode cur1 = dummyHead;
        ListNode cur2 = dummyHead;
        for (int i = 0; i < k; i++) {
            cur1 = cur1.next;
        }
        // 当cur1到达最后一个节点时, cur2就在第k个节点
        while (cur1.next != null) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        cur1.next = dummyHead.next;
        dummyHead.next = cur2.next;
        cur2.next = null;
        return dummyHead.next;
    }

}
