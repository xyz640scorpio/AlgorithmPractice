// Time Complexity: O(nlogn)
// Space Complexity: O(n)
public class MergeSort {
    public int[] mergeSort(int[] a) {
        // corner case
        if (a == null || a.length <= 1) {
            return a;
        }
        // merge sort
        return mergeSort(a, 0, a.length - 1);
    }

    private int[] mergeSort(int[] a, int left, int right) {
        if (left == right) {
            return new int[]{a[left]};
        }
        int mid = left + (right - left) / 2;
        int[] leftResult = mergeSort(a, left, mid);
        int[] rightResult = mergeSort(a, mid + 1, right);
        return merge(leftResult, rightResult);
    }

    private int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        int[] res = new int[left.length + right.length];
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                res[resultIndex++] = left[leftIndex++];
            } else {
                res[resultIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            res[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            res[resultIndex++] = right[rightIndex++];
        }
        return res;
    }
}

