package solve.easy;


import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 自除数 是指可以被它包含的每一位数除尽的数。
 * <p>
 * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 还有，自除数不允许包含 0 。
 * <p>
 * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
 * 示例 1：
 * 输入：
 * 上边界left = 1, 下边界right = 22
 * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 */
public class E728_SelfDividingNumbers {

    @Test
    public void test() {
        System.out.println(selfDividingNumbers(1, 22));
    }


    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new LinkedList<>();

        for (int i = left; i <= right; i++) {
            int temp = i;
            while (temp % 10 != 0 && i % (temp % 10) == 0) {
                temp = temp / 10;
            }
            if (temp == 0) {
                res.add(i);
            }
        }
        return res;
    }

}
