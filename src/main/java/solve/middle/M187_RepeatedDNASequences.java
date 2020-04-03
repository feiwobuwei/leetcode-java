package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * <p>
 * 编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。
 * 示例：
 * <p>
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * @author minwei
 */
public class M187_RepeatedDNASequences {

    String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

    @Test
    public void test() {
        List<String> repeatedDnaSequences = findRepeatedDnaSequences(s);
        System.out.println(repeatedDnaSequences);
    }

    /**
     * 哈希表 -- 82.25%
     * <p>
     * time O(n2) substring 为O(n)
     * space O(n)
     */
    public List<String> findRepeatedDnaSequences(String s) {
        // 键为序列 值为次数
        HashMap<String, Integer> hashMap = new HashMap<>(16);
        for (int i = 0; i < s.length() - 9; i++) {
            String string = s.substring(i, i + 10);
            if (hashMap.containsKey(string)) {
                hashMap.put(string, (hashMap.get(string) + 1));
            } else {
                hashMap.put(string, 1);
            }
        }

        List<String> result = new ArrayList<>();
        for (String string : hashMap.keySet()) {
            if (hashMap.get(string) > 1) {
                result.add(string);
            }
        }
        return result;
    }

}
