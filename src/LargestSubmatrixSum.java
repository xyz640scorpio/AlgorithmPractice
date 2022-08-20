import java.util.*;

public class LargestSubmatrixSum {
    public int largest(int[][] matrix) {
        int m = matrix.length; // m is row number
        int n = matrix[0].length; // n is column number
        int[][] prefixSum = new int[m][n];
        // step 1: calculate prefixSum -- prefixSum[i][j] represents the matrix sum of 0, 0 to i, j
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    prefixSum[i][j] = matrix[i][j];
                } else if (i == 0) {
                    prefixSum[i][j] = prefixSum[i][j - 1] + matrix[i][j];
                } else if (j == 0) {
                    prefixSum[i][j] = prefixSum[i - 1][j] + matrix[i][j];
                } else {
                    prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i][j];
                }
            }
        }
        print2DArray(prefixSum);

        // step 2: each matrix sum = prefixSum[k][t] - prefixSum[i - 1][t] - prefixSum[k][j - 1] + prefixSum[i - 1][j - 1]
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i; k < m; k++) {
                    for (int t = j; t < n; t++) {
                        int sum = prefixSum[k][t];
                        if (i > 0) {
                            sum -= prefixSum[i - 1][t];
                        }
                        if (j > 0) {
                            sum -= prefixSum[k][j - 1];
                        }
                        if (i > 0 && j > 0) {
                            sum += prefixSum[i - 1][j - 1];
                        }
                        largest = Math.max(largest, sum);
                    }
                }
            }
        }
        return largest;
    }
    public void print2DArray(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println();
    }
// 0 1 2 3
    public int[] solution1(int[] arr) {
        if (arr.length < 3) {
            return new int[0];
        }
        int[] res = new int[arr.length - 2];
        for (int i = 0; i < arr.length - 2; i++) {
            if ((arr[i] > arr[i + 1] && arr[i + 1] > arr[i + 2]) || (arr[i] < arr[i + 1] && arr[i + 1] < arr[i + 2])) {
                res[i] = 1;
            }
        }
        return res;
    }

    public int solution2(int[] breakpoints, String[] actions, int length) {
        // corner case
        if (length <= 1) {
            return length;
        }

        int line = 1;
        int i = 0;
        for (String action: actions) {
            if (action.equals("next")) {
                line++;
            } else { // continue
                while (i < breakpoints.length && breakpoints[i] <= line ) {
                    i++;
                }
                if (i == breakpoints.length) {
                    line = length;
                    break;
                } else {
                    line = breakpoints[i];
                }
            }
            if (line == length) break;
        }
        return line;
    }


    public int minimumTime(List<Integer> capacities, long processes) {
        Collections.sort(capacities, Collections.reverseOrder());
        int[] arr = new int[capacities.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = capacities.get(i);
        }
        int index = 0;
        int time = 0;
        while (index < arr.length && processes > 0) {
            if (arr[index] > 1) {
                processes -= (int) arr[index];
                arr[index] = arr[index] / 2;
                time++;
                index++;
                if (index >= arr.length) index = 0;
            } else if ((arr[index] == 1 && index != 0)) {
                index = 0;
            } else if (arr[index] == 1 && index == 0) {
                break;
            }
        }
        if (processes > 0) time += (int) processes;
        return time;
    }


    public String[] ysolution22(String word, String[] skeletons) {
        int write = 0;
        for (int read = 0; read < skeletons.length; read++) {
            boolean add = true;
            if (skeletons[read].length() != word.length()) continue;
            for (int i = 0; i < skeletons[read].length(); i++) {
                if (skeletons[read].charAt(i) != '-' && skeletons[read].charAt(i) != word.charAt(i)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                skeletons[write++] = skeletons[read];
            }
        }
        return Arrays.copyOf(skeletons, write);
    }



    public String[] solution3(String[] messages, String[] members) {
        Map<String, Integer> map = new HashMap<>();
        for (String member: members) {
            map.put(member, 0);
        }
        int all = 0;
        for (String s: messages) {
            int i = 0;
            Set<String> set = new HashSet<>();
            boolean isAll = false;
            while (i < s.length()) {
                if (s.charAt(i) == '@' && s.charAt(i + 1) == 'a') {
                    isAll = true;
                    break;
                } else if (s.charAt(i) == '@') {
                    i++;
                    StringBuilder cur = new StringBuilder();
                    while (i < s.length() && s.charAt(i) != ' ') {
                        cur.append(s.charAt(i));
                    }
                    set.add(new String(cur));
                }
            }
            if (isAll) {
                all++;
            } else {
                for (String element: set) {
                    if (map.containsKey(element)) {
                        map.put(element, map.get(element) + 1);
                    }
                }
            }
        }
        return null;
    }

    public int math4() {
        int count = 0;
        for (int i = 100; i <= 999; i++) {
            if (i % 3 == 0 || i % 4 == 0) count++;
        }
        return count;
    }

    public int minimumStartValue(int[] arr) {
        int x = 1;
        int sum = 1;
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] <= 0) {
                x += Math.abs(sum + arr[i]) + 1;
                sum = 1;
            } else {
                sum += arr[i];
            }
        }
        return x;
    }

    public List<Integer> frequencyOfMaxValue(List<Integer> numCars, List<Integer> hourStartQ) {
        int[] maxFrequency = new int[numCars.size()];
        Map<Integer, Integer> map = new HashMap<>(); // num : freq
        int curMax = Integer.MIN_VALUE;
        for (int i = numCars.size() - 1; i >= 0; i--) {
            int curNum = numCars.get(i);
            map.putIfAbsent(curNum, 0);
            map.put(curNum, map.get(curNum) + 1);
            curMax = Math.max(curMax, curNum);
            maxFrequency[i] = map.get(curMax);
        }
        List<Integer> res = new ArrayList<>();
        for (int hour: hourStartQ) {
            res.add(maxFrequency[hour - 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int[] a = new int[]{7, 4, 3, 1, 2};
        for (int num: a) {
            list.add(num);
        }
        int[][] matrix = new int[][]{{1,2}, {1,5}, {2, 5, 2}, {2, 6, 3}, {2, 2, 1}, {2, 3, 2}};
        LargestSubmatrixSum o = new LargestSubmatrixSum();
//        System.out.print(o.minimumTime(list, 15));
        System.out.println(o.math4());
    }
}