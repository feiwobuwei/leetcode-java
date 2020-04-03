package prepared;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 预定义的链表节点类 用于链表相关题目
 *
 * @author minwei
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 按一定格式输出链表
     *
     * @param result 待打印链表的头节点
     */
    public static void print(ListNode result) {
        while (result != null) {
            if (result.next != null) {
                System.out.print(result.val + "->");
                result = result.next;
            } else {
                System.out.println(result.val);
                result = result.next;
            }
        }
    }

    /**
     * 根据给定顺序数组生成链表
     *
     * @param arr 给定的数组
     * @return 头部节点
     */
    public static ListNode ArrayToList(int[] arr) {

        ListNode head = new ListNode(arr[0]);

        // 游标
        ListNode cursor = head;

        ListNode temp;
        int index = 1;

        while (index != arr.length) {
            temp = new ListNode(arr[index]);
            cursor.next = temp;
            index++;
            cursor = temp;
        }

        return head;
    }

    /**
     * 根据给定顺序数组生成带环的链表
     *
     * @param arr 给定的数组
     * @return 头部节点
     */
    public static ListNode ArrayToCycleList(int[] arr) {

        ListNode head = new ListNode(arr[0]);

        // 游标
        ListNode cursor = head;

        ListNode temp;
        int index = 1;

        Map<Integer, ListNode> map = new HashMap<>(16);
        map.put(arr[0], head);

        while (index < arr.length) {
            // 不存在就添加
            if (!map.keySet().contains(arr[index])) {
                temp = new ListNode(arr[index]);
                cursor.next = temp;
                map.put(arr[index], temp);
                index++;
                cursor = temp;
            } else {
                // 存在就取出 补成环
                cursor.next = map.get(arr[index]);
                index++;
            }
        }
        return head;
    }


    /**
     * 处理E141 链表是否有环 使用HashSet时添加的
     *
     * @see solve.easy.E141_LinkedListCycle#hasCycle2(ListNode)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListNode listNode = (ListNode) o;
        return val == listNode.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
