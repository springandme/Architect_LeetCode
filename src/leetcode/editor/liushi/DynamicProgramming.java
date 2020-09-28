package leetcode.editor.liushi;

/**
 * @ClassName DynamicProgramming
 * @Description 动态规划问题
 * @Author liushi
 * @Date 2020/9/18 7:42
 * @Version V1.0
 **/
public class DynamicProgramming {

    public static void main(String[] args) {
        int[] dp = {1, 4, 3, 4, 2};
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }

        System.out.println(res);
    }
}
