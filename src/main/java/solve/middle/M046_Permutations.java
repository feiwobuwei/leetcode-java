package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author minwei
 */
public class M046_Permutations {

    @Test
    public void test1() {
        int[] nums = {1, 2, 3};

        List<List<Integer>> permute = permute(nums);
        System.out.println(permute);
    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 3};

        List<List<Integer>> permute = permute2(nums);
        System.out.println(permute);
    }

    @Test
    public void test3() {
        int[] nums = {1, 2, 3};

        List<List<Integer>> permute = permute3(nums);
        System.out.println(permute);
    }

    /**
     * 回溯法
     */
    public List<List<Integer>> permute(int[] nums) {
        // 记录最终结果
        List<List<Integer>> output = new LinkedList<>();
        // 记录每一层的结果
        ArrayList<Integer> numsList = new ArrayList<>();

        for (int num : nums) {
            numsList.add(num);
        }
        int n = nums.length;
        backtrack(n, numsList, output, 0);
        return output;
    }

    /**
     * 回溯法
     *
     * @param n      目标层数
     * @param list   记录每一种方法的顺序
     * @param output 装载最终结果
     * @param level  当前层数
     */
    private void backtrack(int n, ArrayList<Integer> list, List<List<Integer>> output, int level) {
        // 边界条件 此时数组已经到尾部+1的位置了
        if (level == n) {
            output.add(new ArrayList<>(list));
        }
        for (int i = level; i < n; i++) {
            // 交换(轮流作为头元素) 优化一下 降低不必要的交换
            if (level != i) {
                Collections.swap(list, level, i);
            }
            // 进入下一层
            backtrack(n, list, output, level + 1);
            // 回溯后 复原
            if (level != i) {
                Collections.swap(list, level, i);
            }

        }
    }

    private List<List<Integer>> listList = new ArrayList<>();

    /**
     * 类似方法
     *
     *
     * @see M046_Permutations#permuteNumber(int)
     */
    public List<List<Integer>> permute2(int[] nums) {
        permute(nums, new ArrayList<>());
        return listList;
    }

    private void permute(int[] nums, List<Integer> list) {
        int n = nums.length;
        if (list.size() == n) {
            listList.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            if (list.contains(num)) {
                continue;
            }
            list.add(num);
            permute(nums, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 交换形式 -- 简易写法
     */
    public List<List<Integer>> permute3(int[] nums) {
        permute(nums, 0, new ArrayList<>());
        return listList;
    }

    private void permute(int[] nums, int index, List<Integer> list) {
        int n = nums.length;
        if (list.size() == n) {
            listList.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < n; i++) {
            // 也是轮流作头部元素 改为交换
            swap(nums, i, index);
            list.add(nums[index]);
            permute(nums, index + 1, list);
            list.remove(list.size() - 1);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // ==================================== 分隔线  ======================================= //

    @Test
    public void test() {
        permuteNumber(4);
        System.out.println(count);
    }

    private static int count;

    /**
     * 生成n的所有全排列
     * 把n张牌 放到 n个盒子里面 有多少种放法
     * <p>
     * 相比交换更容易理解的写法
     */
    public static void permuteNumber(int n) {
        // 为了表示方便 索引从1开始的
        int[] card = new int[n + 1];
        int[] box = new int[n + 1];

        dfs(1, n, box, card);
    }

    private static void dfs(int level, int target, int[] box, int[] card) {

        // 如果进入 n+1 层 (即已经没有新盒子了)，则放置完成 输出摆放顺序
        if (level == target + 1) {
            count++; // 每进入该if内部 代表存在一种放法

            // 按盒子顺序输出里面放置的牌的编号
            for (int i = 1; i <= target; i++) {
                System.out.print(box[i] + " ");
            }

            System.out.println();
            return;
        }

        for (int i = 1; i <= target; i++) {
            // 如果该牌还没有放入盒子
            if (card[i] == 0) {
                // level 可以理解为层数 每个盒子代表一层
                box[level] = i;
                // card[i] 置1 代表第i号牌不在手中，已经被放入
                card[i] = 1;
                // 往下一层
                dfs(level + 1, target, box, card);
                // card[i] 置0 代表第i号牌 从盒子拿回手中
                card[i] = 0;
            }
        }
    }

}
