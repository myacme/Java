package java; /**
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


import java.util.*;

/**
 * 〈排序
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/8/10

 * @since 1.0.0

 */

public class Sort {
    //冒泡
    public static void sortMp(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    //集合排序
    public static void arraySort(int[] arr){
        Arrays.sort(arr);
    }
    //选择排序
    public static void sortXz(int[] arr){
        for (int i = 0; i < arr.length-1 ; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j:minIndex;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    //插入排序
    public static void sortCr(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j >0 && arr[j] < arr[j-1]; j--) {
                arr[j] = arr[j] + arr[j-1];
                arr[j-1] = arr[j] - arr[j-1];
                arr[j] = arr[j] - arr[j-1];
            }
        }
    }

    //快速排序
    public static void sortKs(int[] arr,int start, int end){
        if (start<end){
            int mid = arr[start];
            int low = start;
            int high = end;
            while (low <high){
                while (low<high && arr[high]>=mid){
                    high--;
                }
                arr[low] = arr[high];
                while (low<high && arr[low]<=mid){
                    low++;
                }
                arr[high] = arr[low];
            }
            arr[low] = mid;
            sortKs(arr, start, low);
            sortKs(arr, low+1, end);
        }
    }

    //希尔排序
    public static void sortXr(int[] arr){
        for (int d = arr.length/2 ; d > 0; d/=2) {
            for (int i = d; i < arr.length; i++) {
                for (int j = i-d; j>=0 && arr[j] >arr[j+d]; j-=d) {
                    arr[j] = arr[j] + arr[j+d];
                    arr[j+d] = arr[j] -arr[j+d];
                    arr[j] = arr[j] - arr[j+d];
                }
            }
        }
    }


    //归并排序
    public static void sortGb(int[] arr,int start,int end){
        if (start<end){
            int mid = (start + end)/2;
            sortGb(arr,start,mid);
            sortGb(arr,mid+1,end);
            int temp[] = new int[end - start + 1];
            int i = start;
            int j = mid+1;
            int index = 0;
            while (i<=mid && j<=end){
                if (arr[i] <= arr[j]){
                    temp[index] = arr[i];
                    i++;
                    index++;
                }else {
                    temp[index] = arr[j];
                    j++;
                    index++;
                }
            }
            while (i<=mid){
                temp[index] = arr[i];
                i++;
                index++;
            }
            while (j<=end){
                temp[index] = arr[j];
                j++;
                index++;
            }
            System.arraycopy(temp, 0, arr, start, temp.length);
        }
    }

    //基数
    public static void sortJs(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = max>arr[i]?max:arr[i];
        }
        int[][] num = new int[10][arr.length];
        int[] count = new int[10];
        for (int i = 0,n=1; i < (max+"").length(); i++,n*=10) {
            for (int j = 0; j < arr.length; j++) {
                int oneNum = arr[j]/n%10;
                num[oneNum][count[oneNum]] = arr[j];
                count[oneNum]++;
            }
            int index = 0;
            for (int j = 0; j < count.length; j++) {
                if (count[j] != 0){
                    for (int k = 0; k < count[j] ; k++) {
                        arr[index] = num[j][k];
                        index++;
                    }
                }
                count[j] = 0;
            }
        }
    }






    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < 100; i++) {
            arr[i] = random.nextInt(20);
        }
        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime);
//        int[] arr = {5,1,3,4,10,6,9,4,5};
//        System.out.print("冒泡：");
//        startTime=System.currentTimeMillis();
//        sortMp(arr);
//        endTime=System.currentTimeMillis();
//        excTime=(float)(endTime-startTime);
//        System.out.println("执行时间："+excTime+"ms");

//        System.out.print("方法：");
//        startTime=System.currentTimeMillis();
//        arraySort(arr);
//        endTime=System.currentTimeMillis();
//        excTime=(float)(endTime-startTime);
//        System.out.println("执行时间："+excTime+"ms");
//
//        System.out.print("选择：");
//        startTime=System.currentTimeMillis();
//        sortXz(arr);
//        endTime=System.currentTimeMillis();
//        excTime=(float)(endTime-startTime);
//        System.out.println("执行时间："+excTime+"ms");
//
//        System.out.print("插入：");
//        startTime=System.currentTimeMillis();
//        sortCr(arr);
//        endTime=System.currentTimeMillis();
//        excTime=(float)(endTime-startTime);
//        System.out.println("执行时间："+excTime+"ms");
//
//        System.out.print("快速：");
//        startTime=System.currentTimeMillis();
//        sortKs(arr,0,arr.length-1);
//        endTime=System.currentTimeMillis();
//        excTime=(float)(endTime-startTime);
//        System.out.println("执行时间："+excTime+"ms");

//        System.out.print("希尔：");
//        startTime=System.currentTimeMillis();
//        sortXr(arr);
//        endTime=System.currentTimeMillis();
//        excTime=(float)(endTime-startTime);
//        System.out.println("执行时间："+excTime+"ms");

//        System.out.print("归并：");
//        startTime=System.currentTimeMillis();
//        sortGb(arr,0,arr.length-1);
//        endTime=System.currentTimeMillis();
//        excTime=(float)(endTime-startTime);
//        System.out.println("执行时间："+excTime+"ms");
        System.out.print("基数：");
        startTime=System.currentTimeMillis();
        sortJs(arr);
        endTime=System.currentTimeMillis();
        excTime=(float)(endTime-startTime);
        System.out.println("执行时间："+excTime+"ms");


        System.out.println(Arrays.toString(arr));
    }


}