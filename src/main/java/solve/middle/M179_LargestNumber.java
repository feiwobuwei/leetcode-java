package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: 210
 * <p>
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * @author minwei
 */
public class M179_LargestNumber {

    @Test
    public void test() {
        int[] nums = {3, 30, 34, 5, 9};
        String s = largestNumber(nums);
        // 9 5 34 3 30
        System.out.println(s);
    }


    @Test
    public void test2() {
        int[] nums = {3, 30, 34, 5, 9};

        List<String> list = new ArrayList<>();
        Arrays.stream(nums).forEach(e -> list.add(String.valueOf(e)));
        //  String.compareTo 是字典序(lexicographically)比较
        list.sort(String::compareTo);
        // 字典序 [3, 30, 34, 5, 9]
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test3() {
        Integer[] nums = {3, 30, 34, 5, 9};
        List<Integer> list = Arrays.asList(nums);

        // 升序排列
        // list.sort((o1, o2) -> o1.compareTo(o2));

        // 方法引用 上面lambda表达式的等效
        list.sort(Integer::compareTo);
        // [3, 5, 9, 30, 34]
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test4() {
        int[] a = {1, 3, 4};
        // int -> Integer
        Integer[] ib = IntStream.of(a).boxed().toArray(Integer[]::new);
        // [1, 3, 4]
        System.out.println(Arrays.toString(ib));

    }

    /**
     * list.sort(Comparator<? super E> c) 入参是比较器,其重写的compareTo方法指定了比较规则(策略设计模式)
     * 底层排序的过程其实就是BST(二叉查找树)的插入过程,BST中元素的相对大小规则由入参比较器的compareTo决定
     * <p>
     * 首先第一个元素3作为底层二叉树(TreeSet)的根 (String#compareTo 是字典序(lexicographically)比较)
     * o1=3 o2=30 由于303(o2 + o1) < 330(o1 + o2) 说明在该规则中 o1<o2 3<30 。30作为3的右子节点
     * 之后的每一个元素都先与根比较
     * o1=3 o2=34 343(o2 + o1) > 334(o1 + o2) 说明在该规则中 o1>o2 3>34。34作为3的左子节点
     * o1=3 o2=5 53>35 则5先进入左子树,然后 o1=34 o2=5 534>345 5作为34的左子节点
     * o1=3 o2=9 93>39 则9先进入左子树 然后 o1=34 o2=9 934>349 再进入左子树 然后 o1=5 o2=9 95>59 9作为5的左子节点
     * <p>
     * 插入完毕后 底层二叉树如下
     * <pre>
     *       3
     *      / \
     *     34 30
     *    /
     *   5
     *  /
     * 9
     * </pre>
     * 然后按中序遍历结果返回给集合list
     * <p>
     * time O(n) - 但是遍历了3次
     * space O(n)
     */
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        Arrays.stream(nums).forEach(e -> list.add(String.valueOf(e)));

        list.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        // 3033459
//        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder res = new StringBuilder();
        list.forEach(res::append);
        // 如果第一位是0 说明数组里的元素都是0
        if (res.toString().indexOf('0') == 0) {
            // 此时返回单独一个0,而不是数组长度个数的0
            return "0";
        }
        return res.toString();

    }
}
