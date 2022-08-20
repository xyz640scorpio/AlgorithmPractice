public class binarySearch {
    // find the index of the largest value less than or equal to target
    // input: int[] array, int target, array is sorted ascending array
    public int findUpperBound(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (a[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // a[left] <= a[right]
        if (a[right] <= target) {
            return right;
        }
        if (a[left] <= target) {
            return left;
        }
        return - 1; // if doesn't exist, return -1
    }

    //  1, 2, 3, 4, 5, 6, 7, 8, 9 --> lowerBound = 2.5      upperBound = 7.5
    // lowerBound <= x <= upperBound
    // for lowerBound
    // 要找 最小的 >= lowerBound 的值
    // value >= lowerBound, value的index可能是我们的答案 ==> 保留
    // value < lowerBound, value的index必不是答案 ==> 删了
    // a[left] ~ a[right] 搜索范围, left, right, mid都是index
    // a[mid] < target, mid必不是答案，target的值一定比a[mid]大，大于等于target的最小值在a[mid]右边 因为sorted array
    // a[mid] >= target, mid有可能是答案，但是还有可能存在结果比a[mid]小但是仍大于等于target, 这个值一定在a[mid]左边，但是mid有可能是结果，
    // 所以 right = mid， mid不能扔
    // left <= right --> left = right - 1 or right


    // find the index of the smallest value greater than or equal to target
    // input: int[] array, int target, array is sorted ascending array
    public int findLowerBound(int[] a, int target) {
        int left = 0; // left index;
        int right = a.length - 1; // right index
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // a[left] <= a[right]
        if (a[left] >= target) {
            return left;
        }
        if (a[right] >= target) {
            return right;
        }
        return -1; // if doesn't exist, return -1
    }

    // 要找 最小的 > target 的值
    public int findStrictLowerBound(int[] a, int target) {
        int left = 0; // left index;
        int right = a.length - 1; // right index
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (a[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // a[left] <= a[right]
        if (a[left] > target) {
            return left;
        }
        if (a[right] > target) {
            return right;
        }
        return -1; // if doesn't exist, return -1
    }

    // 要找 最大的 < target 的值
    public int findStrictUpperBound(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (a[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // a[left] <= a[right]
        if (a[right] < target) {
            return right;
        }
        if (a[left] < target) {
            return left;
        }
        return - 1; // if doesn't exist, return -1
    }


}
