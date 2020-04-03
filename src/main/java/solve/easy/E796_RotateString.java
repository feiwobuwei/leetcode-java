package solve.easy;

public class E796_RotateString {

    public static void main(String[] args) {
        String A = "abcde";
        String B = "cdeab";

        System.out.println(rotateString(A, B));

    }

    public static boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}
