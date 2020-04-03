package solve.easy;

import org.junit.Test;

/**
 * 不使用任何内建的哈希表库设计一个哈希映射
 * <p>
 * 具体地说，你的设计应该包含以下的功能
 * put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
 * get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
 * remove(key)：如果映射中存在这个键，删除这个数值对。
 *
 * @author wm
 */
public class E706_DesignHashMap {

    @Test
    public void test() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        // 返回 1
        System.out.println(hashMap.get(1));
        // 返回 -1 (未找到)
        System.out.println(hashMap.get(3));
        // 更新已有的值
        hashMap.put(2, 1);
        // 返回 1
        System.out.println(hashMap.get(2));
        // 删除键为2的数据
        hashMap.remove(2);
        // 返回 -1 (未找到)
        System.out.println(hashMap.get(2));

    }

    @Test
    public void test1() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 0);
        hashMap.put(101, 1);
        hashMap.put(201, 2);
        hashMap.put(301, 3);
        // 测试删除中间节点
        hashMap.remove(201);

        System.out.println(hashMap.get(201));

    }

    @Test
    public void test2() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 0);
        hashMap.put(101, 1);
        hashMap.put(201, 2);
        // 测试删除中间节点
        hashMap.remove(201);
        hashMap.remove(1);
        //
        hashMap.remove(1);
        hashMap.remove(2);
        hashMap.remove(101);

        System.out.println(hashMap.get(201));

    }
}

/**
 * 模拟Java的HashMap
 */
class MyHashMap {

    // TODO: 2020/3/26  提交失败

    private Node[] nodes;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        nodes = new Node[100];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        // 获取桶号
        int bucket = key % nodes.length;
        Node head = nodes[bucket];

        // 如果第一个节点就是要更新的键值对
        if (head != null && head.key == key) {
            head.val = value;
            return;
        }

        // 如果第一个节点不是
        if (head != null) {
            // 下一节点存在 且不等于 key 就一直往后走
            while (head.next != null && head.next.key != key) {
                head = head.next;
            }

            // 停止的时候有2种情况，中间就有相同 key 的节点，此时更新 value
            // 一直走到了最后一个节点(说明中间都没有相同值节点) 此时追加该键值对到链表最后
            if (head.next == null) {
                head.next = new Node(key, value, null);
            } else {
                head.val = value;
            }

        } else {
            // head==null  该桶还没有节点 设置该键值对为该桶链表的第一个节点
            nodes[bucket] = new Node(key, value, null);
        }

    }

    /**
     * Returns the value to which the specified key is mapped, or -1
     * if this map contains no mapping for the key
     */
    public int get(int key) {
        // 获取桶号
        int bucket = key % nodes.length;
        Node head = nodes[bucket];

        // 如果第一个节点就是要获取的key
        if (head != null && head.key == key) {
            return head.val;
        }

        // 第一个节点不是
        if (head != null) {
            // 下一节点存在 且不等于 key 就一直往后走
            while (head.next != null && head.next.key != key) {
                head = head.next;
            }

            // 停止的时候有2种情况，中间就有相同 key 的节点，此时返回该节点的value
            // 一直走到了最后一个节点(说明中间都没有相同key节点) 此时返回-1
            if (head.next == null) {
                return -1;
            } else {
                return head.val;
            }
        }

        // head == null
        return -1;
    }

    /**
     * Removes the mapping of the specified value key
     * if this map contains a mapping for the key
     */
    public void remove(int key) {
        // 获取桶号
        int bucket = key % nodes.length;
        Node head = nodes[bucket];

        if (head != null && head.key == key) {
            // 要删除的是链表的第一个节点 此时把头部直接移到下一个节点即可
            nodes[bucket] = head.next;
        }

        // 要删除的不是链表的第一个节点
        if (head != null && head.key != key) {
            // 下一节点存在 且不等于 key 就一直往后走
            while (head.next != null && head.next.key != key) {
                head = head.next;
            }

            // 停止的时候有2种情况，中间就有相同 key 的节点，此时返回该key的值
            // 一直走到了最后一个节点(说明中间都没有相同值节点)
            if (head.next != null) {
                // 删除中间节点
                head.next = head.next.next;
            }
        }

        // 否则 head == null 什么也不做
    }
}

class Node {

    int key;
    int val;
    Node next;

    Node(int key, int val, Node next) {
        this.key = key;
        this.val = val;
        this.next = next;
    }
}