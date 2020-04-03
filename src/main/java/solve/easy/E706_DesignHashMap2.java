package solve.easy;

import org.junit.Test;

import java.util.Arrays;

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
public class E706_DesignHashMap2 {

    @Test
    public void test() {
        MyHashMap2 hashMap = new MyHashMap2();
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

}

class MyHashMap2 {

    private int[] table;

    public MyHashMap2() {
        table = new int[1000000];
        Arrays.fill(table, -1);
    }

    public void put(int key, int value) {
        table[key] = value;
    }

    public int get(int key) {
        return table[key];
    }

    public void remove(int key) {
        table[key] = -1;
    }
}
