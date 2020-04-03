package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * <p>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * <p>
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * @author minwei
 */
public class M089_GrayCode {

    // [0, 1, 3, 2, 6, 7, 5, 4]

    @Test
    public void test() {
        //000 001 011 010 110 111 101 100
        List<Integer> list = grayCode(3);
        System.out.println(list);
    }

    // [4, 0, 2, 6, 7, 3, 1, 5]

    @Test
    public void test2() {
        List<Integer> list = grayCode2(3);
        System.out.println(list);
    }

    /**
     * 规律法 -- 100%
     * 类似动态规划
     * <p>
     * time (2^n) 已经是最优
     * space O(1)
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);

        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head <<= 1;
        }
        return res;
    }

    /*
    维基百科

    以二进制为 0 值的格雷码为第零项，第一项改变最右边的位元，第二项改变右起第一个为1的位元的左边位元，
    第三、四项方法同第一、二项，如此反复，即可排列出n个位元的格雷码。

    以 n = 3 为例。

    0 0 0 第零项初始化为 0。
    0 0 1 第一项改变上一项最右边的位元
    0 1 1 第二项改变上一项右起第一个为 1 的位元的左边位
    0 1 0 第三项同第一项，改变上一项最右边的位元
    1 1 0 第四项同第二项，改变最上一项右起第一个为 1 的位元的左边位
    1 1 1 第五项同第一项，改变上一项最右边的位元
    1 0 1 第六项同第二项，改变最上一项右起第一个为 1 的位元的左边位
    1 0 0 第七项同第一项，改变上一项最右边的位元
     */

    /**
     * 回溯法
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> gray = new ArrayList<>();
        // 初始化第0项
        gray.add(0);
        for (int i = 1; i < 1 << n; i++) {
            // 得到上一个的值
            int previous = gray.get(i - 1);
            // 同第一项的情况(奇数时)
            if ((i & 1) == 1) {
                // 和1做异或，使得最右边一位取反
                previous ^= 1;
                gray.add(previous);
            } else {
                // 同第二项的情况(偶数时)
                int temp = previous;
                // 寻找右边起第一个为 1 的位元
                for (int j = 0; j < n; j++) {
                    if ((temp & 1) == 1) {
                        // 改变其左邻的位
                        previous ^= (1 << (j + 1));
                        gray.add(previous);
                        break;
                    }
                    temp = temp >> 1;
                }
            }
        }
        return gray;
    }

}
