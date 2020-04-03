package solve.easy;


import org.junit.Test;

import java.util.Arrays;

/**
 * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。
 * 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
 * 1. 你设计的矩形页面必须等于给定的目标面积。
 * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
 * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
 * <p>
 * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
 * <p>
 * 示例：
 * 输入: 4
 * 输出: [2, 2]
 * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
 * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求.
 * 所以输出长度 L 为 2， 宽度 W 为 2。
 * <p>
 * 说明:
 * 给定的面积不大于 10,000,000 且为正整数。
 * 你设计的页面的长度和宽度必须都是正整数。
 *
 * @author 99497
 */
public class E492_ConstructTheRectangle {

    @Test
    public void test1() {
        System.out.println(Arrays.toString(constructRectangle(4)));
        System.out.println(Arrays.toString(constructRectangle(5)));
        System.out.println(Arrays.toString(constructRectangle(6)));
        System.out.println(Arrays.toString(constructRectangle(12)));
        System.out.println(Arrays.toString(constructRectangle(30)));
        System.out.println(Arrays.toString(constructRectangle(40)));
    }

    @Test
    public void test2() {
        System.out.println(Arrays.toString(constructRectangle2(4)));
        System.out.println(Arrays.toString(constructRectangle2(5)));
        System.out.println(Arrays.toString(constructRectangle2(6)));
        System.out.println(Arrays.toString(constructRectangle2(12)));
        System.out.println(Arrays.toString(constructRectangle2(30)));
        System.out.println(Arrays.toString(constructRectangle2(40)));
    }


    /**
     * L 肯定大于 Math.sqrt(area) 只需要返回最接近根号处的值
     * <p>
     * 25.61%
     */
    public int[] constructRectangle(int area) {

        for (int i = (int) Math.ceil(Math.sqrt(area)); i <= area; i++) {
            int temp = area / i;
            if (i * temp == area) {
                return new int[]{i, temp};
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * 反向
     * <p>
     * 86.10%
     */
    public int[] constructRectangle2(int area) {
        int[] res = new int[2];
        for (int i = (int) Math.sqrt(area); i >= 1; i--) {
            if (area % i == 0) {
                res[0] = area / i;
                res[1] = i;
                return res;
            }
        }
        return res;
    }
}
