package solve.middle;

import org.junit.Test;

import java.util.*;

/**
 * 前 K 个高频元素
 * <p>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * <p>
 *
 * @author minwei
 */
public class M347_TopKFrequentElements {

    int[] nums = {1, 1, 1, 2, 2, 3};

    @Test
    public void test() {
        List<Integer> list = topKFrequent(nums, 2);
        System.out.println(list);
    }

    int[] nums2 = {1};

    @Test
    public void test2() {
        List<Integer> list = topKFrequent2(nums2, 1);
        System.out.println(list);
    }

    /**
     * 堆 最小优先队列 -- 36.36%
     * <p>
     * time O(nlogk)
     * space O(n)
     */
    public List<Integer> topKFrequent(int[] nums, int k) {

        // 使用map，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<>(8);
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 最小堆 指定比较器规则是频率最大的出列
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));

        // 遍历map
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                // 每次最小的出列
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;

    }

    /**
     * 桶排序 -- 97.03%
     * <p>
     * time  O(n)
     * space O(n)
     */
    public List<Integer> topKFrequent2(int[] nums, int k) {

        List<Integer> res = new ArrayList<>();
        // 使用map，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<>(8);

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 桶排序 应对可能所有数字都不相同的情况
        int bucketNum = nums.length + 1;

        // 外层ArrayList查询快  内层LinkedList增删快
        ArrayList<LinkedList<Integer>> bucketArr =
                new ArrayList<>(bucketNum);

        // 初始化
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new LinkedList<>());
        }

        for (int key : map.keySet()) {
            // 出现的次数作为桶的下标
            int i = map.get(key);
            bucketArr.get(i).add(key);
        }

        // 倒序遍历数组获取出现顺序从大到小的排列
        for (int i = bucketArr.size() - 1; i >= 0 && res.size() < k; i--) {
            // 跳过空桶
            if (bucketArr.size() == 0) {
                continue;
            }
            res.addAll(bucketArr.get(i));
        }
        return res;
    }

}
