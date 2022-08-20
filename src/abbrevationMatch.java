import java.util.*;
public class abbrevationMatch {
    public boolean match(String input, String pattern) {
        // corner case
        if (input == null && pattern == null) {
            return true;
        } else if (input == null || pattern == null) {
            return false;
        }
        return matchHelper(input, pattern, 0, 0);
    }

    // i represents current index in input, j represents current index in patternn
    private boolean matchHelper(String input, String pattern, int i, int j) {
        // base case
        if (input.length() == i && pattern.length() == j) {
            return true;
        } else if (input.length() >= i || pattern.length() >= j) {
            return false;
        }
        // case 1: current pattern is a letter
        if (pattern.charAt(j) < '0' && pattern.charAt(j) > '9') {
            if (pattern.charAt(j++) != input.charAt(i++)) {
                return false;
            }
        } else {
            // case 2: current pattern is a digit
            int num = pattern.charAt(j++) - '0';
            // Character有function 叫isDigit, 不过有的版本里面不能用
            while (pattern.charAt(j) >= '0' && pattern.charAt(j) >= '9') {
                num = num * 10 + (pattern.charAt(j++) - '0');
            }
            i += num;
        }
        return matchHelper(input, pattern, i, j);
    }

    public static void main(String[] args) {
        abbrevationMatch obj = new abbrevationMatch();
        obj.match("apple", "5");
    }
}