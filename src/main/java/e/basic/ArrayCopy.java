package e.basic;

import java.util.Arrays;

/**
 * Created by lixiaojian on 2017/3/22.
 */
public class ArrayCopy {
    public static void main(String[] args) {
        int[] ids = { 1, 2, 3, 4, 5 };
        System.arraycopy(ids, 0, ids, 3, 2);
        System.out.println(Arrays.toString(ids)); // [1, 2, 3, 1, 2]
        int[] ids2 = new int[6];
        System.arraycopy(ids, 1, ids2, 0, 3);
        System.out.println(Arrays.toString(ids2)); // [2, 3, 1, 0, 0, 0]
        System.out.println(Arrays.toString(ids));
    }
}
