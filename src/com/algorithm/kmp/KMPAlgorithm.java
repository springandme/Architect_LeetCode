package com.algorithm.kmp;

import java.util.Arrays;

/**
 * @ClassName KMPAlgorithm
 * @Description TODO
 * @Author liushi
 * @Date 2020/11/16 20:24
 * @Version V1.0
 **/
public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int result = kmpSearch(str1, str2, next);
        System.out.println("resultIndex:" + result);
    }


    /**
     * 写出我们的kmp搜索算法
     *
     * @param str1 主串
     * @param str2 子串
     * @param next 部分匹配表,是字串对应的部分匹配表
     * @return 如果是-1就是没有匹配值,否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        int i, j;
        // 遍历
        for (i = 0, j = 0; i < str1.length() && j < str2.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
        }

        return j == str2.length() ? i - j : -1;
    }


    /**
     * 获取到一个字符串(子串)的部分匹配值表
     *
     * @param strDest 子串
     * @return 返回字符串(子串)的部分匹配值表
     */
    public static int[] kmpNext(String strDest) {
        // 创建一个next数组保存部分匹配值
        int[] next = new int[strDest.length()];
        // 如果字符串是长度为1,它对应的部分匹配值就是0
        next[0] = 0;

        // 自己的写法
/*        int max = 0;
        for (int i = 1; i < next.length; i++) {
            String substring = strDest.substring(0, i + 1);
            for (int j = 1; j < substring.length(); j++) {
                // 获得前缀
                String prefix = substring.substring(0, j);
                // 获得后缀
                String suffix = substring.substring(substring.length() - j);
                if (prefix.equals(suffix) && prefix.length() > max) {
                    max = prefix.length();
                }
            }
            next[i] = max;
            max = 0;
        }*/

        for (int i = 1, j = 0; i < strDest.length(); i++) {
            // 当strDest.charAt(i) != strDest.charAt(j),我们需要从next[j-1]获取新的j
            // 直到我们发现有strDest.charAt(i) == strDest.charAt(j)成立才退出
            // 这时kmp算法的核心点
            while (j > 0 && strDest.charAt(i) != strDest.charAt(j)) {
                j = next[j - 1];
            }
            // strDest.charAt(i) == strDest.charAt(j)满足时,部分匹配值就是 +1
            if (strDest.charAt(i) == strDest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }

}
