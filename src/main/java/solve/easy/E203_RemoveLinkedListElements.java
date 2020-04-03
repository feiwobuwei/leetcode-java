package solve.easy;

import org.junit.Test;
import prepared.ListNode;

/**
 * 移除链表元素
 * <p>
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @author minwei
 */
public class E203_RemoveLinkedListElements {

    @Test
    public void test() {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode listNode = ListNode.ArrayToList(nums);
        ListNode result = removeElements(listNode, 6);
        ListNode.print(result);
    }

    @Test
    public void test2() {
        int[] nums = {1, 1};
        ListNode listNode = ListNode.ArrayToList(nums);
        ListNode result = removeElements(listNode, 1);
        ListNode.print(result);
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
                head = pre.next;
            } else {
                pre = pre.next;
                head = head.next;
            }
        }
        return dummy.next;
    }

}
