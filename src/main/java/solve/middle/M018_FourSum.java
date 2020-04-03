package solve.middle;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * <p>
 * 注意： 答案中不可以包含重复的四元组。
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author wm
 * @see M015_ThreeSum
 */
public class M018_FourSum {

    private int[] arrays = {1, 0, -1, 0, -2, 2};

    @Test
    public void test() {
        List<List<Integer>> lists = fourSum(arrays, 0);
        for (List list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = fourSum2(arrays, 0);
        for (List list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test3() {
        List<List<Integer>> lists = fourSum3(arrays, 0);
        for (List list : lists) {
            System.out.println(list);
        }
    }

    /**
     * 双指针法 -- 70.97%
     * <p>
     * time: O(n^3)
     * space: O(n)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<>();
        // 也就是返回空
        if (nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 接下来同 M015_ThreeSum
                int low = j + 1, high = nums.length - 1;

                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        // 去重
                        while (low < high && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        while (low < high && nums[high] == nums[high - 1]) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if (sum < target) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }

        return res;
    }


    /**
     * 暴力法 -- 5.09%
     * <p>
     * time: O(n^4)
     * space: O(n)
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> listList = new ArrayList<>();
        int n;
        if (null == nums || (n = nums.length) == 0) {
            return listList;
        }
        // 先排序
        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            //这个判断很重要，不然会超时
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 去重1
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                //这个判断很重要，不然会超时
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 1] > target) {
                    break;
                }
                // 去重2
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < n - 1; k++) {
                    //这个判断很重要，不然会超时
                    if (nums[i] + nums[j] + nums[k] + nums[k + 1] > target) {
                        break;
                    }
                    // 去重3
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    for (int m = k + 1; m < n; m++) {
                        // 去重4
                        if (m > k + 1 && nums[m] == nums[m - 1]) {
                            continue;
                        }
                        if (nums[i] + nums[j] + nums[k] + nums[m] == target) {
                            addToListList(nums[i], nums[j], nums[k], nums[m], listList);
                        }
                    }
                }
            }
        }
        return listList;
    }

    // 使用Pair来优化


    /**
     * 1 两两成对考虑，并用集合来过滤除去重复元素
     * 2 让第一个数组的较大索引小于第二个数组的较小索引
     * <p>
     * time O(n^2)
     * space O(n^2)
     */
    public List<List<Integer>> fourSum3(int[] nums, int target) {
        Set<List<Integer>> listSet = new HashSet<>();
        int n;
        if (null == nums || (n = nums.length) == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        Map<Integer, List<Integer[]>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int num = nums[i] + nums[j];
                // 保证了pair数组里i < j
                Integer[] pair = {i, j};
                if (map.containsKey(num)) {
                    map.get(num).add(pair);
                } else {
                    List<Integer[]> list = new ArrayList<>();
                    list.add(pair);
                    map.put(num, list);
                }
            }
        }
        // 寻找组合
        for (Integer integer : map.keySet()) {
            if (map.containsKey(target - integer)) {
                List<Integer[]> list1 = map.get(integer);
                List<Integer[]> list2 = map.get(target - integer);
                for (Integer[] pair1 : list1) {
                    int index1 = pair1[0], index2 = pair1[1];
                    for (Integer[] pair2 : list2) {
                        int index3 = pair2[0], index4 = pair2[1];
                        // 防重复
                        if (index2 < index3) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[index1]);
                            list.add(nums[index2]);
                            list.add(nums[index3]);
                            list.add(nums[index4]);
                            listSet.add(list);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(listSet);
    }

    private void addToListList(int num1, int num2, int num3, int num4,
                               List<List<Integer>> listList) {
        List<Integer> list = new ArrayList<>();
        list.add(num1);
        list.add(num2);
        list.add(num3);
        list.add(num4);
        listList.add(list);
    }


}
