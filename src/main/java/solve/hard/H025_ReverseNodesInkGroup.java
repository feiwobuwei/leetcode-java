package solve.hard;

import solve.easy.E206_ReverseLinkedList;
import org.junit.Test;
import prepared.ListNode;

/**
 * K 个一组翻转链表
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author minwei
 * @see solve.middle.M024_SwapNodesInPairs
 * @see solve.easy.E206_ReverseLinkedList
 */
public class H025_ReverseNodesInkGroup {

    /**
     * 工具类 -- 反转单链表
     */
    private E206_ReverseLinkedList tool = new E206_ReverseLinkedList();

    @Test
    public void test() {
        ListNode l = ListNode.ArrayToList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});

        ListNode result = reverseKGroup(l, 3);
        ListNode.print(result);
    }


    /**
     * 迭代法 -- 99.68%
     * <p>
     * 1 链表分区为已翻转部分+待翻转部分+未翻转部分
     * 2 每次翻转前，要确定翻转链表的范围，这个必须通过 k 次循环来确定
     * 3 需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
     * 4 初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
     * 5 经过k次循环，end 到达末尾，记录待翻转链表的后继 next = end.next
     * 6 翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
     * 7 特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，
     * 说明整个链表反转完毕，直接返回即可
     * <p>
     * time O(nk)
     * space O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 哑节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 待翻转链表的前驱
        ListNode pre = dummy;
        // 待翻转链表的末尾
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 未翻转部分不足k 可以结束了
            if (end == null) {
                break;
            }
            // 反转前的头 反转后的尾
            ListNode start = pre.next;

            // 下次反转链表的头 提前保存 防止GC
            ListNode next = end.next;
            // 将待反转部分与未反转部分(也即本次不反转)切断
            end.next = null;
            // 反转[pre,end]区间链表 并返回反转后的头
            pre.next = tool.reverseList(start);
            // 反转后 start是已反转部分的尾 重新连上
            start.next = next;

            // pre end 每次循环初始都位于待反转部分的前一个节点
            pre = start;
            end = start;
        }
        return dummy.next;

    }

}
