//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 9537 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//Java：两数之和
public class P1TwoSum {
    public static void main(String[] args) {
        Solution solution = new P1TwoSum().new Solution();
        int[] ints = {2, 43, 12, 134, 432};
        int[] resultArr = solution.twoSum(ints, 14);
        System.out.println(Arrays.toString(resultArr));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int first = Integer.MIN_VALUE;
            int second = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        first = i;
                        second = j;
                        break;
                    }
                }
            }
            return new int[]{first, second};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}