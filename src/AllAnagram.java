import java.util.*;
public class AllAnagram {
    public static List<Integer> allAnagrams(String s, String l) {

        List<Integer> res = new ArrayList<Integer>();
        // corner case
        if (s == null || l == null || s.isEmpty() || l.isEmpty() || s.length() < l.length()) {
            return res;
        }
        char[] la = l.toCharArray();
        char[] sa = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < sa.length; i++) {
            char cur = sa[i];
            if (map.containsKey(cur)) {
                map.put(cur, map.get(cur) + 1);
            } else {
                map.put(cur, 1);
            }
        }
        int unmatch = map.size();
        int last = 0;
        int first = 0;
        while (last < sa.length) {
            Integer count = map.get(la[last]);
            if (count != null) {
                map.put(la[last++], --count);
            }
            if (count == 0) {
                unmatch--;
            }
        }
        if (unmatch == 0) {
            res.add(first);
        }
        while (last < la.length) {
            // remove the first character
            Integer count = map.get(la[first]);
            if (count != null) {
                map.put(la[first], ++count);
                first++;
            }
            if (count > 0) {
                unmatch++;
            }
            // add the last character
            count = map.get(la[last]);
            if (count != null)  {
                map.put(la[last], --count);
                last++;
            }
            if (count == 0) {
                unmatch--;
            }
            if (unmatch == 0) {
                res.add(first);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] input = new String[]{"aab","ababacbbaac"};
        allAnagrams(input[0], input[1]);
    }
}
