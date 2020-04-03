package solve.easy;

/**
 * @author minwei
 */
public class E389_FindTheDifference {


    /**
     * 扫描法 -- 65.78%
     * <p>
     * time O(t.length(),26))
     */
    public char findTheDifference(String s, String t) {
        char result = 0;
        char[] counts = new char[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }

        for (int j = 0; j < t.length(); j++) {
            counts[t.charAt(j) - 'a']--;
        }

        for (int k = 0; k < counts.length; k++) {
            if (counts[k] != 0) {
                result = (char) (k + 'a');
            }
        }
        return result;
    }

    /**
     * 字符串替换法 -- 5.05%
     */
    public char findTheDifference2(String s, String t) {
        for (Character c : s.toCharArray()) {
            t = t.replaceFirst(c.toString(), "");
        }
        return t.toCharArray()[0];
    }


    /**
     * 异或法 -- 41.92%
     *
     * @see E136_SingleNumber
     */
    public char findTheDifference3(String s, String t) {

        char result = 0;
        for (int i = 0; i < s.length(); i++) {
            result ^= s.charAt(i);
        }

        for (int j = 0; j < t.length(); j++) {
            result ^= t.charAt(j);
        }

        return result;
    }

}
