package solve.easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 */
public class E088_MergeSortedArray {

    /**
     * 双指针法
     * <p>
     * time O(m+n) space O(1)
     *
     * @param nums1 合并数组1
     * @param m     数组1中要取出进行排序的元素的数量
     * @param nums2 被合并数组2
     * @param n     数组2中要取出进行排序的元素的数量
     */
    private void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        // 合并后最大元素的下标
        int k = m + n - 1;

        // 逆向填充数组
        while (i >= 0 && j >= 0) {
            // k 总是取两者较大者
            nums1[k--] = (nums1[i] >= nums2[j]) ? nums1[i--] : nums2[j--];
        }

        // 只有当nums2中最后x个数都小于nums1中最后一个数(也就是i=0且j>0),才会使用到以下循环
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /**
     * 合并后排序
     * <p>
     * 为O((n + m)log(n + m))
     * 这种方法没有利用两个数组本身已经有序这一点。
     */
    private void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 把nums2赋值到nums1中

        //   public static native void arraycopy(Object src,  int  srcPos,
        //                                        Object dest, int destPos,
        //                                        int length);
        System.arraycopy(nums2, 0, nums1, m, n);
        // DualPivotQuicksort.sort 底层是双主元快排 nlog(n)
        Arrays.sort(nums1);
    }


    @Test
    public void test1() {
        int[] test1 = {1, 2, 3, 0, 0, 0};
        int[] test2 = {2, 5, 6};

        merge2(test1, 3, test2, 3);

        for (int i1 : test1) {
            System.out.print(i1 + " ");
        }
    }

    @Test
    public void test2() {
        int[] test1 = {4, 5, 6, 7, 0, 0, 0, 0};
        int[] test2 = {1, 2, 3, 4};

        merge(test1, 4, test2, 4);

        for (int i1 : test1) {
            // 1 2 3 4 4 5 6 7 0
            System.out.print(i1 + " ");
        }

    }

    @Test
    public void test3() {
        int[] test1 = {4, 5, 6, 7, 0, 0, 0, 0};
        int[] test2 = {1, 2, 3, 4};

        merge2(test1, 4, test2, 4);

        for (int i1 : test1) {
            // 1 2 3 4 4 5 6 7
            System.out.print(i1 + " ");
        }

    }

}
