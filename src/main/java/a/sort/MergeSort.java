package a.sort;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr, int low, int high) {
        int mid = (low + high) >> 1;
        if(low < high) {
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] res = new int[arr.length];
        int i = low;
        int start1 = low;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = high;

        while (start1 <= end1 && start2 <= end2)
            res[i++] = (arr[start1] > arr[start2]) ? arr[start2++] : arr[start1++];
        while (start1 <= end1)
            res[i++] = arr[start1++];
        while (start2 <= end2)
            res[i++] = arr[start2++];

        //复制
        for(int j=low;j<= high;j++) {
            arr[j] = res[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {6,5,3,1,8,7,2,4};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
