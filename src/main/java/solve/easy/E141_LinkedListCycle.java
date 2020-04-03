package solve.easy;

import prepared.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 *
 * @author minwei
 * @see E160_IntersectionOfTwoLinkedLists
 * @see solve.middle.M142_LinkedListCycle2
 */
public class E141_LinkedListCycle {

    public static void main(String[] args) {
        ListNode ready = ListNode.ArrayToCycleList(new int[]{2, 3, 4, 5, 3});
        boolean b = hasCycle(ready);
        System.out.println(b);

        b = hasCycle2(ready);
        System.out.println(b);


    }

    /**
     * 快慢指针法 时间与空间均最优的解
     * <p>
     * time O(n)
     * space O(1)
     */
    public static boolean hasCycle(ListNode head) {
        // 0个或1个节点 没有环
        if (head == null || head.next == null) {
            return false;
        }

        // 快慢指针法 一个每次走1格 一个每次走2格
        ListNode slow = head;
        ListNode fast = head.next;

        // 如果进了环路 那么快跑者一定可以追上慢跑者 然后返回false
        while (slow != fast) {
            // 可以直接走到头 说明无环
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;
    }

    /**
     * 哈希表
     * <p>
     * time O(n)
     * space O(n)
     */
    public static boolean hasCycle2(ListNode head) {
        // 提示需要重写hashcode 和 equals
        Set<ListNode> covers = new HashSet<>();
        while (head != null) {
            if (covers.contains(head)) {
                return true;
            } else {
                covers.add(head);
            }
            head = head.next;
        }
        return false;
    }


}
