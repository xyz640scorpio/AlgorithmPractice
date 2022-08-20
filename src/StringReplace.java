import java.util.*;
public class StringReplace {
    public String compress(String input) {
        // corner case
        if (input.length() == 0) {
            return input;
        }
        // step 1: traverse the input, compress characters other than single character, record single num
        int write = 0;
        int single = 0;
        char[] a = input.toCharArray();
        for (int i = 0; i < a.length; i++) {
            // last single character or meet single character
            if (i + 1 >= a.length || a[i] != a[i + 1]) {
                single++;
                a[write++] = a[i];
            } else { // meet multiple character
                int j = i;
                //a a a
                //j i i
                while (i + 1 < a.length && a[i] == a[i + 1]) {
                    i++;
                }
                a[write++] = a[i];
                String count = Integer.toString(i - j + 1);
                for (int k = 0; k < count.length(); k++) {
                    a[write++] = count.charAt(k);
                }
            }
        }
        // step 2: reversely traverse the input, copy compressed result, compress single character
        char[] res = new char[write + single];
        int newWrite = res.length - 1;
        int read = write - 1;
        while (read >= 0) {
            if (!Character.isDigit(a[read])) {
                res[newWrite--] = '1';
            } else {
                while (Character.isDigit(a[read])) res[newWrite--] = a[read--];
            }
            res[newWrite--] = a[read--];
        }
        return new String(res);
    }


    public static void main(String[] args) {
        StringReplace o = new StringReplace();
        o.compress("sl");
    }
}
