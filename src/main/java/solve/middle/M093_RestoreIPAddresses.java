package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author minwei
 */
public class M093_RestoreIPAddresses {

    String s = "25525511135";

    @Test
    public void test() {
        List<String> list = restoreIpAddresses(s);
        System.out.println(list);
    }

    @Test
    public void test2() {
        List<String> list = restoreIpAddresses2(s);
        System.out.println(list);
    }

    /**
     * 暴力法 -- 95.80%
     * <p>
     * time O(n^3)
     * space O(n)
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();

        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < i + 4; j++) {
                for (int k = j + 1; k < j + 4; k++) {
                    if (i < n && j < n && k < n) {
                        String tmp1 = s.substring(0, i + 1);
                        String tmp2 = s.substring(i + 1, j + 1);
                        String tmp3 = s.substring(j + 1, k + 1);
                        String tmp4 = s.substring(k + 1);
                        if (helper(tmp1) && helper(tmp2) && helper(tmp3) && helper(tmp4)) {
                            res.add(tmp1 + "." + tmp2 + "." + tmp3 + "." + tmp4);
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean helper(String tmp) {
        return tmp != null
                && tmp.length() != 0
                && tmp.length() <= 3
                && (tmp.charAt(0) != '0' || tmp.length() == 1)
                && Integer.parseInt(tmp) <= 255;
    }

    /**
     * 回溯法 -- 95.74%
     */
    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        backtrack(0, "", 4, s, res, n);
        return res;
    }

    /**
     * @param head 要处理的数字的头指针
     * @param tmp  临时存储结果
     * @param flag 剩余要填充的数字个数
     * @param s    原始输入字符串 递归过程中不变
     * @param res  最终返回结果
     * @param n    原始字符串长度 递归过程中不变
     */
    private void backtrack(int head, String tmp, int flag, String s, List<String> res, int n) {
        if (head == n && flag == 0) {
            res.add(tmp.substring(0, tmp.length() - 1));
            return;
        }

        if (flag < 0) {
            return;
        }

        for (int i = head; i < head + 3; i++) {
            if (i < n) {
                if (head == i && s.charAt(i) == '0') {
                    backtrack(i + 1, tmp + s.charAt(i) + ".", flag - 1, s, res, n);
                    break;
                }
                if (Integer.parseInt(s.substring(head, i + 1)) <= 255) {
                    backtrack(i + 1, tmp + s.substring(head, i + 1) + ".", flag - 1, s, res, n);
                }
            }
        }
    }

}
