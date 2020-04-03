package solve.middle;

import org.junit.Test;
import prepared.ListNode;

/**
 * 奇偶链表
 * <p>
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * @author minwei
 */
public class M328_OddEvenLinkedList {


    @Test
    public void test() {
        ListNode head = ListNode.ArrayToList(new int[]{1, 2, 3, 4, 5});
        oddEvenList(head);
        ListNode.print(head);
    }

    /**
     * 将奇节点放在一个链表里，偶链表放在另一个链表里。
     * 然后将奇数链表的尾指针指向偶数链表的头指针
     * <p>
     * time O(n)
     * space O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head;
        ListNode even = head.next;
        // 偶节点起点 保持引用防止被GC
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;

    }
}
