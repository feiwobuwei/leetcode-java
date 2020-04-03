package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字母异位词分组(字谜分组)
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * @author minwei
 */
public class M049_GroupAnagrams {

    private String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};

    @Test
    public void test() {
        List<List<String>> lists = groupAnagrams(strings);
        System.out.println(lists);
    }

    @Test
    public void test2() {
        List<List<String>> lists = groupAnagrams2(strings);
        System.out.println(lists);
    }

    /**
     * 暴力法 -- 提交超时
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        for (String str : strs) {
            boolean newAnagrams = true;
            for (List<String> list : result) {
                if (list.isEmpty()) {
                    continue;
                }
                if (isAnagrams(str, list.get(0))) {
                    newAnagrams = false;
                    list.add(str);
                    break;
                }
            }
            // 如果出现了新的 添加到结果中
            if (newAnagrams) {
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);
            }
        }

        return result;
    }

    /**
     * 比较2个字符串是否是 易位词
     * 直接统计2个字符串出现的字符数量是否一样多
     * <p>
     * time O(max(l1,l2))
     */
    private boolean isAnagrams(String s1, String s2) {
        int[] flags = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            flags[s1.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < s2.length(); i++) {
            flags[s2.charAt(i) - 'a'] -= 1;
        }
        for (int flag : flags) {
            if (flag != 0) {
                // 一旦任意一个字符数不一样多 立即返回false
                return false;
            }
        }
        return true;
    }

    /**
     * 哈希表 -- 97.41%
     * <p>
     * 空间换时间
     */
    public List<List<String>> groupAnagrams2(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> hashMap = new HashMap<>(16);

        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            // 还原为易构词的字母序形式
            String tempString = String.valueOf(array);
            if (hashMap.containsKey(tempString)) {
                hashMap.get(tempString).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                hashMap.put(tempString, list);
            }
        }
        // 结果处理
        for (String string : hashMap.keySet()) {
            result.add(hashMap.get(string));
        }
        return result;
    }

}
