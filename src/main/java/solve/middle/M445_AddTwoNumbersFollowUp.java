package solve.middle;

import org.junit.Test;
import prepared.ListNode;

import java.util.Stack;

/**
 * 两数相加 II
 * <p>
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * @author wm
 * @see M002_AddTwoNumbers
 */
public class M445_AddTwoNumbersFollowUp {

    private int[] nums1 = {7, 2, 4, 3};
    private int[] nums2 = {5, 6, 4};

    private ListNode head1 = ListNode.ArrayToList(nums1);
    private ListNode head2 = ListNode.ArrayToList(nums2);


    @Test
    public void test() {
        ListNode listNode = addTwoNumbers(head1, head2);
        ListNode.print(listNode);
    }

    @Test
    public void test2() {
        ListNode listNode = addTwoNumbers2(head1, head2);
        ListNode.print(listNode);
    }

    /**
     * 利用栈
     * <p>
     * time O(m+n)
     * space O(m+n)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 先定义两个栈保存两个链表的元素
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        // 存储结果的栈
        Stack<Integer> result = new Stack<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        // 与002一样,将值进行初始化
        ListNode dummyHead = new ListNode(0);
        ListNode p = new ListNode(stack1.pop());
        ListNode q = new ListNode(stack2.pop());
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            result.push(sum % 10);

            // 这里必须手动置空,不然无限循环。栈尾部没有哑节点
            if (stack1.size() != 0) {
                p = new ListNode(stack1.pop());
            } else {
                p = null;
            }

            if (stack2.size() != 0) {
                q = new ListNode(stack2.pop());
            } else {
                q = null;
            }
        }

        // 如果最高有进位,也要压入栈中
        if (carry > 0) {
            result.push(carry);
        }

        ListNode cur = dummyHead;

        // 然后复原链表
        while (result.size() != 0) {
            cur.next = new ListNode(result.pop());
            cur = cur.next;
        }

        return dummyHead.next;
    }


    /**
     * 不使用额外的数据结构 -- 100%
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode rev1 = reverseList(l1);
        ListNode rev2 = reverseList(l2);

        ListNode res = null;
        ListNode pre;
        int carry = 0;
        while (rev1 != null || rev2 != null) {
            int num1 = rev1 != null ? rev1.val : 0;
            int num2 = rev2 != null ? rev2.val : 0;
            int temp = num1 + num2 + carry;
            carry = temp / 10;
            pre = res;
            res = new ListNode(temp % 10);
            res.next = pre;
            rev1 = rev1 != null ? rev1.next : null;
            rev2 = rev2 != null ? rev2.next : null;
        }
        if (carry == 1) {
            pre = res;
            res = new ListNode(1);
            res.next = pre;
        }
        return res;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

}
