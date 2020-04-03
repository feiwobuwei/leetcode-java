package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * <pre>
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * </pre>
 *
 * @author minwei
 */
public class M077_Combinations {

    @Test
    public void test() {
        // [[1, 2], [1, 3], [2, 3], [1, 4], [2, 4], [3, 4]]
        List<List<Integer>> combine = combine(4, 2);
        System.out.println(combine);
    }

    @Test
    public void test2() {
        // [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
        List<List<Integer>> combine = combine2(4, 2);
        System.out.println(combine);
    }

    /**
     * 回溯法 -- 16.78%
     * <p>
     * 时间复杂度是O(n ^ k)
     * 空间复杂度是O(k)。
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(new LinkedList<>(), n, k, result);
        return result;
    }

    private void combine(List<Integer> list, int n, int k, List<List<Integer>> result) {
        if (list.size() == k) {
            result.add(new LinkedList<>(list));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!list.isEmpty() && list.get(0) <= i) {
                continue;
            }
            list.add(0, i);
            combine(list, n, k, result);
            list.remove(0);
        }
    }

    // ====================================================================== //

    /**
     * 回溯法 -- 100%
     * 组合递推公式 C(k,n)=C(k-1,n)+C(k-1,n-1)
     * <p>
     * 将原问题——在[1, n]里面求k个数的所有组合拆解成2个子问题：
     * 1）先确定选择1, 再在[2, n]里面求k-1个数的所有组合
     * 2）先确定不选择1, 再在[2, n]里面求k个数的所有组合。
     * <p>
     * 时间复杂度是O(n ^ k)
     * 空间复杂度是O(k)。
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(new ArrayList<>(), 1, n, k, result);
        return result;
    }

    private void combine(List<Integer> list, int begin, int end, int k, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        // 可选区间不能小于K 不然没有这样的组合
        if (end - begin + 1 < k) {
            return;
        }
        list.add(begin);
        combine(list, begin + 1, end, k - 1, result);
        list.remove(list.size() - 1);
        combine(list, begin + 1, end, k, result);
    }

}
