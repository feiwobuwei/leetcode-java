package solve.middle;

import prepared.ListNode;

/**
 * 删除链表的倒数第N个节点
 *
 * @author minwei
 */
public class M019_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = ListNode.ArrayToList(arr);

        ListNode result = removeNthFromEnd2(head, 2);
        ListNode.print(result);
    }

    /**
     * 两次遍历法 two-pass
     * <p>
     * time:O(L) space:O(1)
     *  L -- 列表的长度
     * 首先计算了列表的长度 L ,其次找到第 (L−n) 个结点。 操作执行了 2L−n 步
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 前面加一个虚拟(哑)节点是为了防止链表只有一个节点时,倒数第二行代码的NPE问题
        // 以及只有一个节点 要删除自己的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        // 循环结束时 first位于要删除节点的前面一个节点
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        // 返回依然是head
        return dummy.next;
    }

    /**
     * 双指针法  one-pass
     * <p>
     * 一个前移n步 然后相同幅度前进
     * 当靠前的到达终点 靠后的就是目标位置
     * <p>
     * time:O(L) space:O(1)
     *
     * @param head 链表头节点
     * @param n    倒数第n个
     * @return 处理完后链表头节点
     */
    private static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 前面加一个虚拟(哑)节点是为了防止链表只有一个节点时,倒数第二行代码的NPE问题
        // 以及只有一个节点 要删除自己的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // 让一个节点先移动n步
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next= second.next.next;

        return dummy.next;
    }


}
