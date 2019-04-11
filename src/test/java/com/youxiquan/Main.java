package com.youxiquan;



import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 最长公共子序列
 * @author lzb
 * 2019/3/1
 */
public class Main {
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        while(scan.hasNextLine()){
//            String s = scan.nextLine();
//            System.out.println(s.length()-lcs(s,new StringBuffer(s).reverse().toString()));
////            test2(s);
//        }
//        scan.close();

//        test3();
        test3();
        new Thread();
    }
    public static int lcs(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int c[][] = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        return c[len1][len2];
    }

    public static void test2(String s ){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>=97){
                System.out.print(s.charAt(i));
            }
        }
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<97){
                System.out.print(s.charAt(i));
            }
        }
        System.out.println("");
    }

    public static void test3(){
        Scanner scan =  new Scanner(System.in);
        Map<Integer,Integer> map = new HashMap<>();
        while(scan.hasNext()){
            int n = scan.nextInt();
            int s=0;
            int k=0;
            int min=Integer.MAX_VALUE;
            int max=Integer.MIN_VALUE;
            map.clear();
            int arr[] = new int[n];
            for(int i=0;i<n;i++){
                int x = scan.nextInt();
                min = min<x?min:x;
                max = max>x?max:x;
                s = map.merge(x,1,Integer::sum);
                if(s>1) k=1;
                else{
                    arr[i]=x;
                }
            }
            if(min==max){
                System.out.println(n*(n-1)/2+" "+n*(n-1)/2);
                continue;
            }
            if(k==0){
                int dmin = arr[1]-arr[0];
                s=1;
                Arrays.sort(arr);
                for(int i=2;i<n;i++){
                    k=arr[i]-arr[i-1];
                    if(k<dmin){
                        dmin=k;
                        s=1;
                    }else if(k==dmin){
                        s++;
                    }
                }
            }else{
                s=0;
                for(Map.Entry<Integer,Integer> entry: map.entrySet()){
                    int v = entry.getValue();
                    if(v>1){
                        s = s + v*(v-1)/2;
                    }
                }
            }

            int ss = map.get(max)*map.get(min);
            System.out.println(s+" "+ss);
        }







    }
}
