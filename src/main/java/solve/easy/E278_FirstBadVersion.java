package solve.easy;

import org.junit.Test;

/**
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。 
 * <p>
 * 你应该尽量减少对调用 API 的次数。即时间复杂度越低越好
 *
 * @author minwei
 * @see E035_SearchInsertPosition
 */
public class E278_FirstBadVersion extends VersionControl {

    @Test
    public void test() {
        System.out.println(firstBadVersion(5));
    }

    @Test
    public void test2() {
        System.out.println(firstBadVersion2(5));
    }

    @Test
    public void test3() {
        System.out.println(firstBadVersion3(5));
    }

    /**
     * 暴力法 -- 超时
     * <p>
     * O(n)
     */
    public int firstBadVersion(int n) {
        for (int i = 1; i < n; i++) {
            if (isBadVersion(i)) {
                return i;
            }
        }
        return n;
    }

    /**
     * 二分法 + 迭代法
     * <p>
     * O(lgn)
     */
    public int firstBadVersion2(int n) {
        int low = 1;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // 如果当前版本有问题
            if (isBadVersion(mid)) {
                // 如果靠前的版本没有问题 那么就正好找到了
                if (!isBadVersion(mid - 1)) {
                    return mid;
                } else {
                    // 否则靠前的版本也有问题 那么目标在前半段
                    high = mid - 1;
                }
                // 如果当前版本没有问题
            } else {
                // 如果靠后的版本有问题 那么就正好找到了
                if (isBadVersion(mid + 1)) {
                    return mid + 1;
                } else {
                    // 否则靠后的版本也没问题 那么目标在后半段
                    low = mid + 1;
                }
            }
        }

        return n;
    }

    public int firstBadVersion3(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    @Override
    boolean isBadVersion(int version) {
        return version >= 5;
    }

}

abstract class VersionControl {

    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * @param version 版本号
     * @return <4时 返回false 代表没有错误
     */
    abstract boolean isBadVersion(int version);
}
