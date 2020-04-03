package solve.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * <p>
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 *
 * @author minwei
 */
public class E412_FizzBuzz {

    public static void main(String[] args) {

        List<String> list = fizzBuzz(15);
        System.out.println(list);

        System.out.println("==========================");

        List<String> list2 = fizzBuzz(15);
        System.out.println(list2);

        System.out.println("==========================");

        List<String> list3 = fizzBuzz(15);
        System.out.println(list3);
    }

    /**
     * one-pass
     * <p>
     * time O(n)
     * space O(n)
     */
    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                if (i % 15 == 0) {
                    list.add("FizzBuzz");
                } else {
                    list.add("Fizz");
                }
            } else if (i % 5 == 0) {
                if (i % 15 == 0) {
                    list.add("FizzBuzz");
                } else {
                    list.add("Buzz");
                }
            } else {
                // String.valueOf(i);
                // Integer.toString(i);
                list.add(i + "");
            }
        }
        return list;

    }

    /**
     * 字符串连接
     * <p>
     * 如果能被 3 整除，就把对应的 Fizz 连接到答案字符串，
     * 如果能被 5 整除，就把 Buzz 连接到答案字符串。
     * 这样可以再添加 例如如果能被7整除之类的规则
     */
    public static List<String> fizzBuzz2(int n) {
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            StringBuilder s = new StringBuilder();

            if (i % 3 == 0) {
                s.append("Fizz");
                if (i % 5 == 0) {
                    s.append("Buzz");
                }
            } else if (i % 5 == 0) {
                s.append("Buzz");
            } else {
                s.append(i);
            }
            list.add(s.toString());
        }
        return list;
    }

    /**
     * 哈希表
     */
    public List<String> fizzBuzz3(int n) {

        List<String> ans = new ArrayList<>();

        // 保存规则字典
        HashMap<Integer, String> fizzBizzDict = new HashMap<>();

        fizzBizzDict.put(3, "Fizz");
        fizzBizzDict.put(5, "Buzz");

        for (int i = 1; i <= n; i++) {
            StringBuilder numAnsStr = new StringBuilder();
            for (Integer key : fizzBizzDict.keySet()) {
                if (i % key == 0) {
                    numAnsStr.append(fizzBizzDict.get(key));
                }
            }
            // 如果无法被上述2个数整除 就加入自身
            if ("".equals(numAnsStr.toString())) {
                numAnsStr.append(i);
            }

            ans.add(numAnsStr.toString());
        }
        return ans;
    }


}
