import java.util.*;
public class AmazonOA {
    private int findPressTime(String input) {
        char[] a = input.toCharArray();
        Integer[] freq = new Integer[26];
        Arrays.fill(freq, 0);
        for (int i = 0; i < a.length; i++) {
            freq[a[i] - '0']++;
        }
        Arrays.sort(freq, Collections.reverseOrder());
        int res = 0;
        for (int i = 0; i < freq.length; i++) {
            if (i < 9) {
                res += freq[i];
            } else if (i < 18) {
                res += freq[i] * 2;
            } else {
                res += freq[i] * 3;
            }
        }
        return res;
    }
}
