package javatest; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.Sort
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/8/10 11:31
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.util.Arrays;

/**
 * 〈排序
 * 〈〉
 *
 * @author MyAcme
 * @create 2020/8/10
 * @since 1.0.0
 */

public class Sort {
    /**
     * 冒泡
     *
     * @param arr
     */
    public static void sortMp(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 集合排序
     *
     * @param arr
     */
    public static void arraySort(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void sortXz(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void sortCr(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0 && arr[j] < arr[j - 1]; j--) {
                arr[j] = arr[j] + arr[j - 1];
                arr[j - 1] = arr[j] - arr[j - 1];
                arr[j] = arr[j] - arr[j - 1];
            }
        }
    }

    /**
     * 快速排序
     * @param arr
     * @param start
     * @param end
     */
    public static void sortKs(int[] arr, int start, int end) {
        if (start < end) {
            int mid = arr[start];
            int low = start;
            int high = end;
            while (low < high) {
                while (low < high && arr[high] >= mid) {
                    high--;
                }
                arr[low] = arr[high];
                while (low < high && arr[low] <= mid) {
                    low++;
                }
                arr[high] = arr[low];
                System.out.println(Arrays.toString(arr));
            }
            arr[low] = mid;
            System.out.println(Arrays.toString(arr));
            System.out.println(mid + " " + low + " " + high);
            System.out.println("-------");
            sortKs(arr, start, low);
            sortKs(arr, low + 1, end);
        }
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void sortXr(int[] arr) {
        for (int d = arr.length / 2; d > 0; d /= 2) {
            for (int i = d; i < arr.length; i++) {
                for (int j = i - d; j >= 0 && arr[j] > arr[j + d]; j -= d) {
                    arr[j] = arr[j] + arr[j + d];
                    arr[j + d] = arr[j] - arr[j + d];
                    arr[j] = arr[j] - arr[j + d];
                    System.out.println(Arrays.toString(arr));
                }
            }
        }
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void sortXr1(int[] arr) {
        for (int d = arr.length / 2; d > 0; d /= 2) {
            for (int i = 0; i < arr.length - d; i++) {
                for (int j = i; j >= 0 && arr[j] > arr[j + d]; j -= d) {
                    arr[j] = arr[j] + arr[j + d];
                    arr[j + d] = arr[j] - arr[j + d];
                    arr[j] = arr[j] - arr[j + d];
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }


    /**
     * 归并排序
     * @param arr
     * @param start
     * @param end
     */
    public static void sortGb(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sortGb(arr, start, mid);
            sortGb(arr, mid + 1, end);
            int temp[] = new int[end - start + 1];
            int i = start;
            int j = mid + 1;
            int index = 0;
            while (i <= mid && j <= end) {
                if (arr[i] <= arr[j]) {
                    temp[index] = arr[i];
                    i++;
                    index++;
                } else {
                    temp[index] = arr[j];
                    j++;
                    index++;
                }
            }
            while (i <= mid) {
                temp[index] = arr[i];
                i++;
                index++;
            }
            while (j <= end) {
                temp[index] = arr[j];
                j++;
                index++;
            }
            System.arraycopy(temp, 0, arr, start, temp.length);
        }
    }

    /**
     * 基数
     *
     * @param arr
     */
    public static void sortJs(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = max > arr[i] ? max : arr[i];
        }
        int[][] num = new int[10][arr.length];
        int[] count = new int[10];
        for (int i = 0, n = 1; i < (max + "").length(); i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int oneNum = arr[j] / n % 10;
                num[oneNum][count[oneNum]] = arr[j];
                count[oneNum]++;
            }
            int index = 0;
            for (int j = 0; j < count.length; j++) {
                if (count[j] != 0) {
                    for (int k = 0; k < count[j]; k++) {
                        arr[index] = num[j][k];
                        index++;
                    }
                }
                count[j] = 0;
            }
        }
    }


    /**
     * 计数排序
     *
     * @param array
     * @return
     */
    private static int[] countSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int min = array[0], max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
            if (max < array[i]) {
                max = array[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            count[array[i] - min]++;
        }
        int i = 0;
        int index = 0;
        while (index < array.length) {
            if (count[i] != 0) {
                array[index] = i + min;
                count[i]--;
                index++;
            } else {
                i++;
            }
        }
        return array;
    }


    public static void main(String[] args) {
//        Random random = new Random();
//        int[] arr = new int[5];
//        for (int i = 0; i < 5; i++) {
//            arr[i] = random.nextInt(20);
//        }
//        System.out.println(Arrays.toString(arr));
        int[] arr = {5, 1, 3};
        long startTime = System.currentTimeMillis();
//        System.out.print("冒泡：");
//        sortMp(arr);
//        System.out.print("方法：");
//        arraySort(arr);
//        System.out.print("选择：");
//        sortXz(arr);
//        System.out.print("插入：");
//        sortCr(arr);
//        System.out.print("快速：");
//        sortKs(arr, 0, arr.length - 1);
//        System.out.print("希尔：");
//        sortXr1(arr);
//        System.out.print("归并：");
        sortGb(arr,0,arr.length-1);
//        System.out.print("基数：");
//        sortJs(arr);
        long endTime = System.currentTimeMillis();
        long excTime = endTime - startTime;
        System.out.println("执行时间：" + excTime + "ms");
        System.out.println(Arrays.toString(arr));
    }
}