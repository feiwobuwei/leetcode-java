package solve.easy;

import org.junit.Test;
import prepared.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * @author wm
 */
public class E083_DeleteDuplicates {

    private int[] nums = {1, 1, 2, 3, 3};
    private ListNode head = ListNode.ArrayToList(nums);

    @Test
    public void test() {
        ListNode listNode = deleteDuplicates(head);
        System.out.println(listNode);
        ListNode.print(listNode);
    }

    /**
     * 直接法
     */
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }


}
