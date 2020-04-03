package solve.easy;

import prepared.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个链表是否为回文链表。
 * <p>
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author minwei
 */
public class E234_PalindromeLinkedList {

    public static void main(String[] args) {
        ListNode ready = ListNode.ArrayToList(new int[]{1, 2, 3, 2, 1});
        ListNode ready2 = ListNode.ArrayToList(new int[]{1, 2});
        boolean palindrome = isPalindrome(ready);
        System.out.println(palindrome);
        palindrome = isPalindrome2(ready);
        System.out.println(palindrome);

        System.out.println("=========================");
        boolean palindrome2 = isPalindrome(ready2);
        System.out.println(palindrome2);
        palindrome2 = isPalindrome2(ready2);
        System.out.println(palindrome2);
    }

    /**
     * one-pass
     * <p>
     * time O(n) space O(n)
     */
    private static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        List<ListNode> list = new ArrayList<>();
        int index = 0;
        while (head != null) {
            list.add(index++, head);
            head = head.next;
        }

        int top = 0;
        int tail = list.size() - 1;
        while (top < tail) {
            if (list.get(top++).val != list.get(tail--).val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 快慢指针法
     * <p>
     * time O(n) space O(1)
     */
    private static boolean isPalindrome2(ListNode head) {

        // 0个和1个节点的情况
        if (head == null || head.next == null) {
            return true;
        }

        // 快慢指针法确定中点
        ListNode fast = head.next.next;
        ListNode slow = head.next;

        // 5个节点 slow位于3 此时fast!=null
        // 4个节点 slow位于3 此时fast==null
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 反转前半段 结束循环时 pre是左半段的头节点 E206 迭代法
        ListNode pre = null;
        while (head != slow) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

        // 如果是奇数个节点，slow右移一格
        if (fast != null) {
            slow = slow.next;
        }

        // 前后两段逐项比较
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }

        return true;
    }
}
