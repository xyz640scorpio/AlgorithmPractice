import java.util.*;

public class IMC {

    public static void main(String[] args) {
        int[] res = q2(new int[]{0,0,1,4}, new int[]{0,1,1,0});
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
        System.out.println(q1dp(1, 1, 4, 7, 6));
    }
    public static int[] q2(int[] arrival, int[] street) {
        int time = 0;
        int prev = -1; // -1 represents no car, 0 for main street, 1 for 1st Ave
        int last = arrival[arrival.length - 1];
        Queue<Integer> mainStreet = new ArrayDeque<>();
        Queue<Integer> ave = new ArrayDeque<>();
        int[] res = new int[arrival.length];
        int i = 0;
        while (time <= last || !mainStreet.isEmpty() || !ave.isEmpty()) {
            // add all current cars into two waiting list of street
            while (i < arrival.length && arrival[i] <= time) {
                if (street[i] == 0) {
                    mainStreet.offer(i);
                } else {
                    ave.offer(i);
                }
                i++;
            }

            // see whether th
            if (mainStreet.isEmpty() && ave.isEmpty()) {
                prev = -1;
            } else if (mainStreet.isEmpty()) {
                res[ave.poll()] = time;
                prev = 1;
            } else if (ave.isEmpty()) {
                res[mainStreet.poll()] = time;
                prev = 0;
            } else {
                if (prev == 0) {
                    res[mainStreet.poll()] = time;
                } else {
                    res[ave.poll()] = time;
                    prev = 1;
                }
            }
            time++;
        }
        return res;
    }
    public static String q1(int c, int x1, int y1, int x2, int y2) {
        // corner case
        if (isObstacle(x1, y1) || isObstacle(x2, y2)) return "No";
        if (dfs(c, x1, y1, x2, y2)) {
            return "Yes";
        }
        return "No";
    }

    public static boolean dfs(int c, int x1, int y1, int x2, int y2) {
        // termination condition
        if (x1 == x2 && y1 == y2) {
            return true;
        }
        if (x1 > x2 || y1 > y2) {
            return false;
        }
        // recursive cases
        return dfs(c, x1 + y1, y1, x2, y2) || dfs(c, x1, x1 + y1, x2, y2) || dfs(c, x1 + c, y1 + c, x2, y2);
    }

    private static boolean isObstacle(int x,int y){
        double sqrt = Math.sqrt(x + y);
        return sqrt - (int) sqrt == 0;
    }

    public static String q1dp(int c, int x1,int y1,int x2,int y2) {
        if (isObstacle(x1, y1) || isObstacle(x2, y2) || x1 > x2 || y1 > y2) return "No";
        boolean[][] dp = new boolean[x2 + 1][y2 + 1];
        dp[x1][y1] = true;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                // current position is(i, j), previous possible 3 positions, (i - c, j - c),
                // (i - j, j), (i, j - i)
                if (isObstacle(i, j) || (i == x1 && j == y1)) {
                    continue;
                } else {
                    if ((!outOfBound(i - c, j - c, dp.length, dp[0].length) && dp[i - c][j - c]) ||
                            (!outOfBound(i - j, j, dp.length, dp[0].length) && dp[i - j][j]) ||
                            (!outOfBound(i, j - i, dp.length, dp[0].length) && dp[i][j - i])) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        if (dp[x2][y2]) return "Yes";
        return "No";
    }

    private static boolean outOfBound(int x, int y, int rows, int cols) {
        return x < 0 || y < 0 || x >= rows || y >= cols;
    }

    public static String solution(int c, int x1,int y1,int x2,int y2) {
        //corner case
        if(isSquare(x1,y1) || isSquare(x2,y2)){
            return "No";
        }
        int longLength = Math.max(Math.max(x1,y1),Math.max(x2,y2));
        boolean[][] dp = new boolean[longLength+1][longLength+1];
        //init
        dp[x1][y1] = true;
        for(int i = x1;i < x2 + 1;i++){
            for(int j = y1; j < y2 + 1;j++){
                //case 1 perfect square or init case
                if(isSquare(i,j) || (i == x1 && j == y1)){
                    continue;

                    //we check if constant c can be reached from last point
                }else if((i-c)>=1 && (j-c) >=0 && dp[i-c][j-c]) {
                    dp[i][j] = true;

                    //we check if dp[i][j] can be reached from x+y or y+x
                }else if((i-j>=0 && dp[i-j][j]) || (j-i>=0 && dp[i][j-i])){
                    dp[i][j]= true;
                }else{
                    dp[i][j] = false;
                }
            }
        }

        if(dp[x2][y2]) {
            return "Yes";
        }else{
            return "No";
        }
    }

    private static boolean isSquare(int x,int y){
        if(x + y >= 0){
            int sqrt = (int)Math.sqrt(x + y);
            return (sqrt*sqrt) == (x + y);
        }
        return false;
    }
}
