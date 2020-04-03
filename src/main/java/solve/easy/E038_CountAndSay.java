package solve.easy;

/**
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 1.     1
 * 2.     11（一个1）
 * 3.     21（两个1）
 * 4.     1211（一个2，一个1）
 * 5.     111221（一个1，一个2，两个1）
 * <p>
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * 注意：整数顺序将表示为一个字符串。
 *
 * @author minwei
 */
public class E038_CountAndSay {

    public static void main(String[] args) {
        // 312211 "三个1" "两个二" ,  "一个一"
        System.out.println(countAndSay(6));
    }


    public static String countAndSay(int n) {
        int i = 1;
        String res = "1";
        while (i < n) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            char c = res.charAt(0);

            // 从左往右扫描
            for (int j = 0; j <= res.length(); j++) {
                // 如果字符串没有结束 每出现一次该字符 统计次数加1
                if (j != res.length() && res.charAt(j) == c) {
                    count++;
                } else {
                    // 否则出现了新的字符 或者字符串已经结束
                    // 先加入次数 再加入该字符
                    sb.append(count);
                    sb.append(c);
                    // 如果字符串还没有结束 以新的字符继续循环
                    if (j != res.length()) {
                        count = 1;
                        c = res.charAt(j);
                    }
                }
            }
            // 作为下次循环处理的源字符串
            res = sb.toString();
            i++;
        }
        return res;
    }


}
