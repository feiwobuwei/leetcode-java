package solve.middle;

import org.junit.Test;
import prepared.ListNode;


/**
 * 两数相加
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * @author minwei
 * @see M445_AddTwoNumbersFollowUp
 */
public class M002_AddTwoNumbers {

    private int[] nums1 = {2, 4, 3};
    private int[] nums2 = {5, 6, 4};

    private ListNode head1 = ListNode.ArrayToList(nums1);
    private ListNode head2 = ListNode.ArrayToList(nums2);

    private int[] nums3 = {9, 9, 1};
    private int[] nums4 = {2, 0, 8};

    private ListNode head3 = ListNode.ArrayToList(nums3);
    private ListNode head4 = ListNode.ArrayToList(nums4);

    private int[] nums5 = {0, 1};
    private int[] nums6 = {0, 1, 2};

    private ListNode head5 = ListNode.ArrayToList(nums5);
    private ListNode head6 = ListNode.ArrayToList(nums6);

    @Test
    public void test() {
        ListNode listNode = addTwoNumbers(head1, head2);
        ListNode.print(listNode);

        listNode = addTwoNumbers(head3, head4);
        ListNode.print(listNode);


        listNode = addTwoNumbers(head5, head6);
        ListNode.print(listNode);
    }

    /**
     * 初等数学 -- 逐位相加。然后注意特殊情况
     * <p>
     * time O(max(m,n))
     * space O(max(m,n))
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 哑节点
        ListNode dummyHead = new ListNode(0);

        ListNode p = l1;
        ListNode q = l2;
        ListNode curr = dummyHead;
        // 进位
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        // 最高位的处理
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


}
