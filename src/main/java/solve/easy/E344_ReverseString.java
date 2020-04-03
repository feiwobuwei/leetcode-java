package solve.easy;

/**
 * 反转字符串
 *
 * @author minwei
 */
public class E344_ReverseString {

    public static void main(String[] args) {
        char[] chars = new char[]{'h', 'e', 'l', 'l', 'o' };
        reverseString(chars);
        System.out.println(chars);
    }

    /**
     * 双指针法
     * <p>
     * time O(n)
     * space O(1)
     */
    private static void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            swap(s, i, j);
            i++;
            j--;
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
