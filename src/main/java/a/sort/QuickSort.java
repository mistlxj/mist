package a.sort;

import java.util.Arrays;
import java.util.LinkedList;

public class QuickSort {
    public static int[] quickSort(int[] arr, int start, int end) {
        int index = 0;
        if (start < end) {
            index = partition(arr, start, end);
            quickSort(arr, 0, index - 1);
            quickSort(arr, index + 1, end);
        }
        return arr;
    }

    public static int[] nonRecQuickSort(int[] arr, int start, int end) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        if (start < end) {
            stack.push(end);
            stack.push(start);
            while(!stack.isEmpty()) {
                int l = stack.pop();
                int r = stack.pop();
                int index = partition(arr, l, r);
                if (l < index) {
                    stack.push(index - 1);
                    stack.push(l);
                }
                if (r > index) {
                    stack.push(r);
                    stack.push(index + 1);
                }
            }
        }
        return arr;
    }

    private static int partition(int[] arr, int start, int end) {
        int tmp = arr[start];
        while (start < end) {
            while(start < end && arr[end] >= tmp)
                end--;
            arr[start] = arr[end];

            while (start < end && arr[start] <= tmp)
                start++;
            arr[end] = arr[start];
        }
        arr[start] = tmp;
        return start;
    }

    public static void main(String[] args) {
        int[] arr = {23,5,8,36,1,9};
        int[] res = quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(res));


        int[] brr = {23,5,8,36,1,9};
        int[] bes = quickSort(brr, 0, arr.length - 1);
        System.out.println(Arrays.toString(bes));
    }
}
