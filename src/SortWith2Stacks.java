import java.util.*;

public class SortWith2Stacks {
    public void sort(LinkedList<Integer> s1) {
        // corner case
        if (s1 == null || s1.size() <= 1) {
            return;
        }
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        sort(s1, s2);
    }
    // 注意分析题干 orginal stack里面存top to down, 从小到大
    // 方法一：buffer里面global min； 方法二：input里面global max
    private void sort(Deque<Integer> s1, Deque<Integer> s2) {
        // part 1: store all values in s2 in descending order
        while(!s1.isEmpty()) {
            // step 1: move all value from s1 to s2, record the current minimum
            int curMin = Integer.MAX_VALUE;
            int count = 0;
            while(!s1.isEmpty()) {
                int cur = s1.pollFirst();
                if (cur < curMin) {
                    curMin = cur;
                    count = 1;
                } else if (cur == curMin) {
                    count++;
                }
                s2.offerFirst(cur);
            }
            // step 2: move all value from s2 to s1, except the current minimum and previous minimums
            while(!s2.isEmpty() && s2.peekFirst() >= curMin) {
                int cur = s2.pollFirst();
                if (cur != curMin) {
                    s1.offerFirst(cur);
                }
            }
            // step 3: add current minimum to s2
            while (count-- > 0) {
                s2.offerFirst(curMin);
            }
        }
        // part 2: move all value from s2 to s1, the descending order change to ascending order
        while(!s2.isEmpty()) {
            s1.offerFirst(s2.pollFirst());
        }
    }
}