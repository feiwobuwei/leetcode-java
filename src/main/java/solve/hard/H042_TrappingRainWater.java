package solve.hard;

import org.junit.Test;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author minwei
 */
public class H042_TrappingRainWater {

    private int[] test = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

    /**
     * 按列求 常规方法
     * <p>
     * 根据木桶效应，我们只需要看左边最高的墙和右边最高的墙中较矮的一个就够了。
     * 所以，根据较矮的那个墙和当前列的墙的高度可以分为三种情况。
     * <p>
     * 1 较矮的墙的高度大于当前列的墙的高度 可存水=当前高度-较矮墙高
     * 2 较矮的墙的高度小于当前列的墙的高度 无法存水
     * 3 较矮的墙的高度等于当前列的墙的高度 无法存水
     * <p>
     * time O(n2)
     * space O(1)
     *
     * @param height 墙的高度数组
     * @return 可以储存的雨水
     */
    public static int trap1(int[] height) {

        // 记录最终结果
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            // 当前循环时左侧最高的墙的高度
            int left = 0;
            // 当前循环时右侧最高的墙高度
            int right = 0;

            // 找出左侧最高的墙
            for (int j = 0; j < i; j++) {
                if (left < height[j]) {
                    left = height[j];
                }
            }
            // 找出右侧最高的墙
            for (int k = height.length - 1; k > i; k--) {
                if (right < height[k]) {
                    right = height[k];
                }
            }

            if (height[i] < Math.min(left, right)) {
                sum = sum + (Math.min(left, right) - height[i]);
            }

        }

        return sum;
    }

    /**
     * 动态规划
     * <p>
     * 解法一中对于每一列，我们求它左边最高的墙和右边最高的墙，都是重新遍历一遍所有高度，这里我们可以优化一下。
     * <p>
     * 首先用两个数组
     * max_left [i]代表第 i 列左边最高的墙的高度， max_right[i] 代表第 i 列右边最高的墙的高度。
     * <p>
     * 找到递推公式(状态转移方程)
     * max_left [i] = Max(max_left [i-1],height[i-1])。
     * 它前边的墙的左边的最高高度和它前边的墙的高度选一个较大的，就是当前列左边最高的墙了。
     * 右数组类似
     * <p>
     * 典型的 空间换时间
     * time O(n)
     * space O(n)
     */
    public static int trap2(int[] height) {

        // 记录最终结果
        int sum = 0;
        // 记录每次循环时左侧最高的墙的高度值
        int[] max_left = new int[height.length];
        // 记录每次循环时右侧最高的墙的高度值
        int[] max_right = new int[height.length];

        // 从左往右 从第2项开始
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }

        // 从右往左 从第2项开始
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }

        // 不用管最左最右的墙
        for (int i = 1; i < height.length - 1; i++) {
            if (height[i] < Math.min(max_left[i], max_right[i])) {
                sum += Math.min(max_left[i], max_right[i]) - height[i];
            }
        }

        return sum;
    }

    /**
     * 双指针法 单循环双向遍历法
     */
    public static int trap3(int[] height) {
        // 记录最终结果
        int sum = 0;
        // 记录当前循环时左侧最高的墙的高度值
        int max_left = 0;
        // 记录当前循环时右侧最高的墙的高度值
        int max_right = 0;

        // 左指针 总是位于左侧最高墙的右边一格
        int left = 1;
        // 右指针 总是位于右侧最高墙的左边一格
        int right = height.length - 2;

        for (int i = 1; i < height.length - 1; i++) {

            // height [ left - 1 ] 是可能成为 max_left 的变量，
            // 同理，height [ right + 1 ] 是可能成为 right_max 的变量。
            // 只要保证 height [ left - 1 ] < height [ right + 1 ] ，
            // 那么 max_left 就一定小于 max_right。

            // 此时一定左边最高的墙较矮
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                // 左指针右移 相当于解法二中max_left数组循环变量+1
                left++;
                // 否则是右边最高的墙较矮或者高度相等
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                // 右指针右移 相当于解法二中max_right数组循环变量-1
                right--;
            }
        }
        return sum;
    }

    /**
     * 解法四 : 栈 类似 括号匹配
     * <p>
     * 用栈保存每堵墙，当遍历墙的高度的时候
     * 如果当前高度小于栈顶的墙高度，说明这里会有积水，我们将墙的高度的下标入栈。
     * 如果当前高度大于栈顶的墙的高度，说明之前的积水到这里停下，我们可以计算下有多少积水了。
     * 计算完，就把当前的墙继续入栈，作为新的积水的墙。
     * <p>
     * 总体的原则就是，
     * 当前高度小于等于栈顶高度，入栈，指针后移。
     * 当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，
     * 重复第 2 步。直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。
     * <p>
     * time O(n) space O(n)
     */
    public static int trap4(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            // 如果栈不空并且当前墙的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                // 获取栈顶指针对应的元素
                int h = height[stack.peek()];
                // 栈顶元素出栈
                stack.pop();
                // 栈空就结束循环 (一对括号结束或者刚加入的左括号直接被弹出)
                if (stack.empty()) {
                    break;
                }
                // 两堵墙之前的距离。
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            // 当前指向的墙入栈
            stack.push(current);
            // 指针后移
            current++;
        }
        return sum;
    }

    @Test
    public void test1() {
        int i = trap1(test);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = trap2(test);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = trap3(test);
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = trap4(test);
        System.out.println(i);
    }


}
