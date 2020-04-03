package solve.middle;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作：
 * 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * LRUCache cache = new LRUCache( 2 /* 缓存容量
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);      // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);      // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);      // 返回 -1 (未找到)
 * cache.get(3);      // 返回  3
 * cache.get(4);      // 返回  4
 * <p>
 * 进阶:
 * 你是否可以在 O(1)时间复杂度内完成这两种操作？
 *
 * @author minwei
 */
public class M146_LRUCache {

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        // 返回  1
        System.out.println(cache.get(1));
        // 该操作会使得密钥 2 作废
        cache.put(3, 3);
        // 返回 -1 (未找到)
        System.out.println(cache.get(2));
        // 该操作会使得密钥 1 作废
        cache.put(4, 4);
        // 返回 -1 (未找到)
        System.out.println(cache.get(1));
        // 返回  3
        System.out.println(cache.get(3));
        // 返回  4
        System.out.println(cache.get(4));
    }

}

/**
 * LinkedHashMap
 *
 * @see java.util.LinkedHashMap
 */
class LRUCache {

    private Node head;
    private Node end;

    private int limit;
    private HashMap<Integer, Node> hashMap;


    public LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        }
        refreshNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = hashMap.get(key);
        if (node == null) {
            // 如果key不存在 插入key-value
            if (hashMap.size() >= limit) {
                int oldkey = removeNode(head);
                hashMap.remove(oldkey);
            }
        } else {
            remove(node.key);
        }
        // 按原来的键和新的值设置一个新节点
        node = new Node(key, value);
        addLast(node);
        hashMap.put(key, node);
    }

    // ===================== 其余均为辅助方法 ======================= //

    public void remove(int key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     * 刷新被访问的节点位置
     *
     * @param node 被访问的节点
     */
    public void refreshNode(Node node) {

        // 如果访问的是尾节点 无需移动节点
        if (node == end) {
            return;
        }
        // 移除节点
        removeNode(node);
        // 重新插入节点
        addLast(node);
    }

    /**
     * 删除节点
     *
     * @param node 要删除的节点
     * @return 被删除节点的键
     */
    public int removeNode(Node node) {

        // 如果只有一个节点 即此时既是头节点 也是尾节点
        if (node == head && node == end) {
            // 将链表置空
            head = null;
            end = null;
        } else if (node == end) {
            // 移除尾节点 GC
            end.pre.next = null;
            end = end.pre;
        } else if (node == head) {
            // 移除头节点
            head.next.pre = null;
            head = head.next;
        } else {
            // 移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;

    }

    /**
     * 尾部插入节点
     *
     * @param node 待插入节点
     */
    public void addLast(Node node) {
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }

}

class Node {
    int key;
    int value;

    Node pre;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

