package solve.easy;

import java.util.Arrays;

public class E705_DesignHashSet {

    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);

        // 返回 true
        System.out.println(hashSet.contains(1));
        // 返回 false (未找到)
        System.out.println(hashSet.contains(3));

        hashSet.add(2);
        // 返回 true
        System.out.println(hashSet.contains(2));

        hashSet.remove(2);
        // 返回  false (已经被删除)
        System.out.println(hashSet.contains(2));

    }
}

class MyHashSet {

    private boolean[] table;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        table = new boolean[1000000];
    }

    public void add(int key) {
        table[key] = true;
    }

    public void remove(int key) {
        table[key] = false;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return table[key];
    }
}