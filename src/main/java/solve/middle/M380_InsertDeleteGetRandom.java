package solve.middle;

import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

/**
 * 常数时间插入、删除和获取随机元素
 * <p>
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 * <p>
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 *
 * @author minwei
 */
public class M380_InsertDeleteGetRandom {

    @Test
    public void test() {
        RandomizedSet obj = new RandomizedSet();
        boolean param1 = obj.insert(1);
        boolean param2 = obj.remove(2);

        System.out.println(param1);
        System.out.println(param2);
        obj.insert(2);

        int param3 = obj.getRandom();
        System.out.println(param3);
    }

}

/**
 * 哈希表(查找 O(1)) + 数组列表(查找 O(1)) + 链表 (增删 O(1))
 */
class RandomizedSet {

    private Random r;
    private HashMap<Integer, Integer> map1;
    private HashMap<Integer, Integer> map2;
    int size;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        //set = new HashSet<>();
        r = new Random();
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        size = 0;
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map2.containsKey(val)) {
            return false;
        }
        map1.put(size, val);
        map2.put(val, size);
        size++;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map2.containsKey(val)) {
            map1.remove(map2.get(val));
            map2.remove(val);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int num = r.nextInt(size);
        if (map1.containsKey(num)) {
            return map1.get(num);
        } else {
            return getRandom();
        }
    }

}


