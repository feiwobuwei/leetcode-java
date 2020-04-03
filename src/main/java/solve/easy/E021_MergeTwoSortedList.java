package solve.easy;

import org.junit.Test;
import prepared.ListNode;

/**
 * 合并两个有序链表
 * 1 2 4
 * 1 3 4
 *
 * @author minwei
 */
public class E021_MergeTwoSortedList {

    private ListNode ready1 = ListNode.ArrayToList(new int[]{1});
    private ListNode ready2 = ListNode.ArrayToList(new int[]{2, 3, 4});

    @Test
    public void test1() {
        ListNode result = mergeTwoLists(ready1, ready2);
        ListNode.print(result);
    }

    @Test
    public void test2() {
        ListNode result = mergeTwoLists2(ready1, ready2);
        ListNode.print(result);

    }

    /**
     * 迭代法
     * cur dummy最开始为同一节点
     * cur 作为不断移动的指针 dummy作为不动的指针
     * cur 不断链接到新的节点,以dummy为起点的链表也就建成
     * 最后dummy.next就是新链表的头节点
     *
     * <p>
     * time O(n) space O(1)
     *
     * @param l1 链表1的头节点
     * @param l2 链表2的头节点
     * @return 合并链表的头节点
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 哑节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 循环结束时必定是 有一个链表到了null 另一个到了最后一个节点
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }

        // 最后一个节点 可能在链表1中 也可能在链表2中
        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }
        return dummy.next;
    }

    /**
     * 递归法
     * <p>
     * 只要存在更大的节点值 就延迟返回
     * 类似将两副牌合并 大的放下面
     * <p>
     * time O(n)
     * space O(n)
     */
    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            // 边界条件 可以把null理解为无穷大
            return l2;
        }
        if (l2 == null) {
            // 边界条件
            return l1;
        }

        if (l1.val < l2.val) {
            // 如果l1小于l2 那么合并链表当前节点就是l1 下一个节点是 l1的下一个与l2中的较小值
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            // 如果l1大于等于l2 那么合并链表当前节点就是l2 下一个节点是 l2的下一个与l1中的较小值
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }


}
