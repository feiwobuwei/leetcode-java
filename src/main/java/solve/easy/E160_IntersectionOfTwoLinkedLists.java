package solve.easy;

import org.junit.Before;
import org.junit.Test;
import prepared.ListNode;

import java.util.Stack;

/**
 * 相交链表
 *
 * @author minwei
 * @see E141_LinkedListCycle
 */
public class E160_IntersectionOfTwoLinkedLists {

    private ListNode headA;
    private ListNode headB;

    @Before
    public void ready() {
        headA = new ListNode(4);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(8);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        headA.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        headB = new ListNode(5);
        ListNode listNode6 = new ListNode(0);
        ListNode listNode7 = new ListNode(1);
        headB.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode3;
    }

    @Test
    public void test() {
        ListNode res = getIntersectionNode(headA, headB);
        System.out.println(res.val);
    }

    @Test
    public void test2() {
        ListNode res = getIntersectionNode2(headA, headB);
        System.out.println(res.val);
    }

    /**
     * 双指针法
     * <p>
     * time O(m+n)
     * space O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 双栈
     * <p>
     * time O(m+n)
     * space O(m)或O(n)
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = helper(headA);
        Stack<ListNode> stackB = helper(headB);

        if (stackA.isEmpty() || stackB.isEmpty()) {
            return null;
        }

        // 先看两链表的最后一个节点
        ListNode lastSame = stackA.pop();
        // 不同说明不相交
        if (lastSame != stackB.pop()) {
            return null;
        }

        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            ListNode node = stackA.pop();
            if (node != stackB.pop()) {
                return lastSame;
            }
            lastSame = node;
        }
        // 两链表在最后节点处相交
        return lastSame;
    }

    private Stack<ListNode> helper(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (null != cur) {
            stack.push(cur);
            cur = cur.next;
        }
        return stack;
    }

}
