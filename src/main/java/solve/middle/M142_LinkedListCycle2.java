package solve.middle;

import org.junit.Before;
import org.junit.Test;
import prepared.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶:你是否可以不用额外空间解决此题？
 *
 * @author minwei
 * @see solve.easy.E141_LinkedListCycle
 */
public class M142_LinkedListCycle2 {

    private ListNode head;

    @Before
    public void prepare() {
        head = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(0);
        ListNode l4 = new ListNode(-4);
        head.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l2;
    }

    @Test
    public void test() {
        ListNode listNode = detectCycle(head);
        System.out.println(listNode.val);
    }

    @Test
    public void test2() {
        ListNode listNode = detectCycle2(head);
        System.out.println(listNode.val);
    }


    /**
     * 哈希表 -- 26.88%
     * <p>
     * time O(n)
     * space O(n)
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }

    /**
     * 快慢双指针 -- 慢指针每次移动1步，快指针每次移动2步。
     * <p>
     * 1 设链表头到入环点的距离为x,那么当慢指针到达入环点时,快指针已经在环里面走了x
     * 2 设环的长度为L 快节点与慢节点的间距为 L- x。
     * 3 为了追上慢节点。慢指针会走 L-x步，快指针会走 2(L-x)步
     * 4 相遇点离入环点距离为 L-x。(相当于在环里再走x步就可以到达入环点)
     * 4 把快指针移回到头节点 并速度也调为1。快指针离入环点的距离也是x。
     * 5 当两指针再次相遇时,相遇点就是入环点
     * <p>
     * 时间复杂度是O(n)，其中n为链表中环的长度。
     * 空间复杂度是O(1)。
     */
    public ListNode detectCycle2(ListNode head) {
        // 0个或1个节点 无环
        if (head == null || head.next == null) {
            return null;
        }
        // 2个节点
        if (head.next.next == head) {
            return head;
        }
        // 接下来的情况均是3个节点以上
        ListNode slow = head;
        ListNode fast = head;
        do {
            slow = slow.next;
            // 到达了尾部 说明没有环
            if (slow == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            if (fast == null) {
                return null;
            }
        } while (slow != fast);

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            // 调速
            fast = fast.next;
        }
        // 相遇点
        return slow;
    }
}
