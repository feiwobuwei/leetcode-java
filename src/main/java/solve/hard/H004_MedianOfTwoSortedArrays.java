package solve.hard;

import org.junit.Test;

/**
 * 寻找两个有序数组的中位数
 * <p>
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author minwei
 */
public class H004_MedianOfTwoSortedArrays {

    private int[] num1 = {1, 3};
    private int[] num2 = {2};

    private int[] num3 = {3, 5, 8, 9};
    private int[] num4 = {1, 2, 7, 10, 11, 12};

    @Test
    public void test() {
        double result = findMedianSortedArrays(num1, num2);
        System.out.println(result);

        result = findMedianSortedArrays(num3, num4);
        System.out.println(result);
    }

    @Test
    public void test2() {
        double result = findMedianSortedArrays2(num1, num2);
        System.out.println(result);

        result = findMedianSortedArrays2(num3, num4);
        System.out.println(result);
    }

    /**
     * 合并查找 -- 99.73%
     * <p>
     * 时间复杂度是O(m+n)
     * 空间复杂度是O(1)。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;

        int i = 0, j = 0, index = 0;
        int pre = 0;
        int cur = 0;
        // 找到(n1+n2)/2位置的元素值 以及其前面的一个位置的元素值
        while (index <= n >> 1) {
            pre = cur;
            if (i < n1 && j < n2) {
                if (nums1[i] < nums2[j]) {
                    cur = nums1[i++];
                } else {
                    cur = nums2[j++];
                }
            } else if (i >= n1 && j < n2) {
                cur = nums2[j++];
            } else if (i < n1) {
                cur = nums1[i++];
            }
            index++;
        }
        if ((n & 1) == 0) {
            return 0.5 * (cur + pre);
        }
        return cur;
    }

    /**
     * 二分法 -- 95.65%
     * 参考博客 https://blog.csdn.net/chen_xinjia/article/details/69258706
     * <p>
     * 如果将数组在中间切一刀
     * 设L1为数组1左区间最大数 R1为数组1右区间最小数
     * 设L2为数组2左区间最大数 R2为数组2右区间最小数
     * <pre>
     *         L1   R1
     *      3, 5, | 8, 9
     *      1, 2, 7, | 10, 11, 12
     *            L2   R2
     * </pre>
     * 如果这一刀的位置是正确的，则应有的结果满足 L1<=R2 , L2<=R1 这样就能确保左边元素都小于右边元素
     * <p>
     * 为了减少查找次数，对短的数组进行二分查找。设数组1长度小于数组2
     * 将在数组1切割的位置记为cut1，在数组2切割的位置记为cut2，
     * 如果 L1>R2 (例如8,9间)则cut1应该向左移 才能使数组1较多的数被分配到右边。
     * 如果 L2>R1 (例如3,5间)则cut1应该向右移 才能使数组1较多的数被分配到左边。
     * <p>
     * time: O(log(min(m,n))
     * space: O(1)
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 为保证时间复杂度，总是操作长度更小的那个数组
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        // 数组1切割的位置
        int cut1 = 0;
        // 数组2切割的位置
        int cut2 = 0;
        // 二分法头指针
        int cutL = 0;
        // 二分法尾指针
        int cutR = nums1.length;
        while (cut1 <= nums1.length) {
            // 获取cutL与cutR中间
            cut1 = (cutR - cutL) / 2 + cutL;
            // 为了 len(left)==len(right) 或者  len(left)==len(right)-1
            cut2 = len / 2 - cut1;
            // L1为nums1数组中max(left)
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            // L2为nums2数组中max(left)
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            // R1为nums1数组中min(right)
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            // R2为nums2数组中min(right)
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];
            // 这两种情况不会同时出现,可通过不等式传递证明
            // 若L1>R2,因为L1<R1,L2<R2。故 R1>L1>R2>L2
            if (L1 > R2) {
                // cut1 太大
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                // cut1 太小
                cutL = cut1 + 1;
            } else {
                // 恰好符合要求 且数组长度和为偶数
                if (len % 2 == 0) {
                    // 取两者较大值
                    L1 = L1 > L2 ? L1 : L2;
                    // 取两者较小值
                    R1 = R1 < R2 ? R1 : R2;
                    return (L1 + R1) / 2.0;
                } else {
                    // 数组长度和为奇数 取R1和R2的较小值
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }
}
