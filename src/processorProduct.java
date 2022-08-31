public class processorProduct {

    private static int maxProduct(int processor, int threads, int target) {
        if (threads < processor) return 0;
        // left >= right
        int total = 0;
        int numOfOnes = 1;
        int left = target;
        int right = processor - target - 1;
        if (right > left) {
            int temp = left;
            left = right;
            right = temp;
        }
        int max = left + 1;
        if (left == right) numOfOnes = 2;
        for (int i = 1; i <= left - right; i++) {
            total += i;
        }
        for (int i = left - right + 1; i <= left; i++) {
            total += 2 * i;
        }
        total += left + 1;
        System.out.println("total: " + total);
        if (total > threads) {
            int count = 1;
            while (total > threads) {
                int remain = processor - numOfOnes;
                total -= remain;
                if (left - right <= count && count < max) {
                    numOfOnes += 2;
                } else {
                    numOfOnes += 1;
                }
                count++;
                max--;
            }
        } else if (total < threads) {
            max += (threads - total) / processor;
        }
        return max;
    }

    public static void main(String[] args) {

        int processor = 5;
        int threads = 5;
        int target = 0;
//        System.out.println("Bao:" + maxPro(processor, threads, target));
        System.out.println("Xin:" + maxProduct(processor, threads, target));
        int i = -8;
        int j = 5;
        System.out.println(i / j);
    }
}