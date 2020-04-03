package solve.middle;

import org.junit.Test;
import prepared.ListNode;

/**
 * 对链表进行插入排序
 *
 * @author minwei
 */
public class M147_InsertionSortList {


    @Test
    public void test() {
        int[] nums = {4, 2, 1, 3};
        ListNode listNode = ListNode.ArrayToList(nums);
        ListNode result = insertionSortList(listNode);
        ListNode.print(result);
    }

    /**
     * 按照要求
     * <p>
     * time O(n2)
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 创建哑节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // pre 每次遍历总是指向要插入位置的前一个节点
        ListNode pre = dummy;
        // cur 总是指向有序区的最后一个节点
        ListNode cur = head;
        // lat 总是指向无序区的第一个节点
        ListNode lat;

        // 循环结束时 整个链表已然有序
        while (cur != null) {
            // 记录下一个要插入排序的值
            lat = cur.next;
            // 只有 lat 比 cur 小时才需要向前寻找插入点
            if (lat != null && lat.val < cur.val) {
                // 循环停止时 pre 位于第一个比lat大的节点的前面
                while (pre.next != null && pre.next.val < lat.val) {
                    pre = pre.next;
                }
                // 此时 pre 节点后面的位置就是 lat 要插入的位置
                ListNode tmp = pre.next;
                pre.next = lat;
                cur.next = lat.next;
                lat.next = tmp;
                // pre 需要复位
                pre = dummy;
            } else {
                // lat是null 或者 lat > cur 直接把cur指针右移(如果是前一种情况 下次循环就结束了)
                cur = lat;
            }
        }
        return dummy.next;
    }
}
