package solve.hard;

import org.junit.Test;
import prepared.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * @author minwei
 */
public class H023_MergeSortedLists {

    private ListNode l1 = ListNode.ArrayToList(new int[]{1, 4, 5});
    private ListNode l2 = ListNode.ArrayToList(new int[]{1, 3, 4});
    private ListNode l3 = ListNode.ArrayToList(new int[]{2, 6});
    private ListNode[] listNodes = {l1, l2, l3};

    @Test
    public void test() {
        ListNode result = mergeKLists(listNodes);
        ListNode.print(result);
    }

    @Test
    public void test2() {
        ListNode result = mergeKLists2(listNodes);
        ListNode.print(result);
    }

    @Test
    public void test3() {
        ListNode result = mergeKLists3(listNodes);
        ListNode.print(result);
    }

    @Test
    public void test4() {
        ListNode result = mergeKLists4(listNodes);
        ListNode.print(result);
    }

    /**
     * 暴力法 迭代法
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int n;
        if (lists == null || (n = lists.length) == 0) {
            return null;
        }
        // 做一个备份
        ListNode[] curs = new ListNode[n];
        System.arraycopy(lists, 0, curs, 0, n);
        ListNode dummyHead = new ListNode(-1), cur = dummyHead;
        do {
            // index索引的作用是寻找一个非空的链表
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (curs[i] != null) {
                    break;
                }
                index++;
            }
            if (index == n) {
                break;
            }
            int minIndex = index;
            for (int i = index + 1; i < n; i++) {
                if (curs[i] != null && curs[i].val < curs[minIndex].val) {
                    minIndex = i;
                }
            }
            cur.next = curs[minIndex];
            cur = cur.next;
            curs[minIndex] = curs[minIndex].next;
        } while (true);

        return dummyHead.next;
    }

    /**
     * 优先队列 -- 71.94%
     * <p>
     * time O(mlogm) 其中m为lists数组中链表总节点个数
     * space O(m)
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        int n;
        if (lists == null || (n = lists.length) == 0) {
            return null;
        }
        // 最小优先队列
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            ListNode cur = lists[i];
            while (cur != null) {
                pq.add(cur.val);
                cur = cur.next;
            }
        }
        // 哑节点
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (!pq.isEmpty()) {
            // 每次最小值出列 此处创建了一个新的等值节点
            cur.next = new ListNode(pq.poll());
            cur = cur.next;
        }
        return dummyHead.next;
    }

    /**
     * 优先队列
     * <p>
     * 时间复杂度是O(mlogn)，其中m为lists数组中链表总节点个数，n为lists数组的长度。
     * 空间复杂度是O(n)。
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        int n;
        if (lists == null || (n = lists.length) == 0) {
            return null;
        }
        // 限制大小为链表数组的长度
        PriorityQueue<ListNode> pq =
                new PriorityQueue<>(n, Comparator.comparingInt(node -> node.val));
        for (int i = 0; i < n; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();
            if (temp.next != null) {
                // 每次出去1个 又添加1个
                pq.add(temp.next);
            }
            cur.next = temp;
            cur = cur.next;
        }
        return dummyHead.next;
    }

    /**
     * 分治法 -- 99.50%
     * <p>
     * time: O(nlogk) k是链表数量 n链表中的最大节点数
     * space: O(n)
     */
    public ListNode mergeKLists4(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int low, int high) {
        if (low >= high) {
            return lists[low];
        }
        int mid = (high - low) / 2 + low;
        ListNode l1 = sort(lists, low, mid);
        ListNode l2 = sort(lists, mid + 1, high);
        return merge(l1, l2);
    }

    /**
     * @see solve.easy.E021_MergeTwoSortedList
     */
    private ListNode merge(ListNode l1, ListNode l2) {

        // 边界情况
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }

    }

}
