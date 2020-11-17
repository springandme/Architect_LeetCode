package com.algorithm.kmp;

/**
 * @ClassName ViolentMatch
 * @Description 暴力匹配算法实现
 * @Author liushi
 * @Date 2020/11/15 9:55
 * @Version V1.0
 **/
public class ViolentMatch {


    public static void main(String[] args) {
        String str1 = "123412347612345";
        String str2 = "12345";
        String str3 = "BBC ABCDAB ABCDABCDABDE";
        String str4 = "ABCDABD";
        int match = violenceMatch(str1, str2);
        System.out.println(match);
    }

    /**
     * 暴力匹配算法实现
     *
     * @param str1 长串
     * @param str2 子串
     * @return 若是子串, 返回的是第一个匹配的下标, 若不是返回-1
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        // i索引指向s1,j索引指向s2
        int i = 0, j = 0;
        // 保证匹配时,不越界
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                // 如果匹配失败,i需要回溯
                // 长串的下标仍要继续向前移动,移动多长由这行代码确定 i = i - (j - 1);
                i = i - (j - 1);
                // 子串索引重置为0
                j = 0;
            }
        }

        // 怎么算找到或没找到呢
        // 如果j的索引是s2Len-1,就表示找到了,若找到则返回, i-s2Len-->第一次找到的下标,没有找到则返回-1
        return j == s2Len ? i - s2Len : -1;
    }

}
