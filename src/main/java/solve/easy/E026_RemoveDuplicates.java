package solve.easy;

import org.junit.Test;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * @author minwei
 */
public class E026_RemoveDuplicates {

    /**
     * time O(n) space O(1)
     *
     * @param nums 待处理数组
     * @return 处理完的数组长度
     */
    private int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        // 标记不同元素的个数
        int count = 1;
        for(int i=0;i<nums.length;i++){
            if(nums[count-1]!=nums[i]){
                nums[count++]=nums[i];
            }
        }
        return count;
    }

    private int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};


    @Test
    public void test() {
        int len = removeDuplicates(nums);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
