package solve.middle;

import org.junit.Test;

/**
 * 盛最多水的容器
 *
 * @author wm
 */
public class M011_ContainerWithMostWater {

    private int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};

    @Test
    public void test() {
        int result = maxArea(nums);
        System.out.println(result);
    }

    @Test
    public void test2() {
        int result = maxArea2(nums);
        System.out.println(result);
    }


    /**
     * 暴力法 -- 提交超时
     * <p>
     * time O(N^2)
     * space O(1)
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                // 取较小高度
                int minHeight = Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i));
            }
        }
        return maxArea;
    }

    /**
     * 双指针法
     * <p>
     * time O(N)
     * space O(1)
     */
    public int maxArea2(int[] height) {
        int maxArea = 0;
        // 头指针
        int l = 0;
        // 尾指针
        int r = height.length - 1;
        while (l < r) {
            int minHeight = Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, minHeight * (r - l));
            // 寻找更高的墙
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }


}
