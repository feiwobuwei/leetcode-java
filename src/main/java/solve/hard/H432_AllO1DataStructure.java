package solve.hard;

import org.junit.Test;

import java.util.HashMap;

/**
 * 实现一个数据结构支持以下操作：
 * <p>
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。
 * 否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * <p>
 * 挑战：以 O(1) 的时间复杂度实现所有操作。
 *
 * @author minwei
 */
public class H432_AllO1DataStructure {

    @Test
    public void test() {
        AllOne obj = new AllOne();
        obj.inc("1");
        obj.inc("2");
        obj.inc("3");
        String max = obj.getMaxKey();

        obj.inc("1");
//        obj.dec("2");

        String min = obj.getMinKey();

        System.out.println(max);
        System.out.println(min);
    }

}

class AllOne {

    private HashMap<String, Integer> hashMap;
    private Integer min;
    private Integer max;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        hashMap = new HashMap<>();
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (hashMap.containsKey(key)) {
            Integer temp = hashMap.get(key);
            max = Math.max(max, temp);
            hashMap.put(key, temp + 1);
        } else {
            hashMap.put(key, 1);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {

    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (max == Integer.MAX_VALUE) {
            return "";
        } else {
            return max.toString();
        }
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (min == Integer.MIN_VALUE) {
            return "";
        } else {
            return min.toString();
        }
    }


}

