//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 512 👎 0

package leetcode.editor.cn;

//Java：二进制求和

/**
 * 把字符串转换成字节数组
 */
public class P67AddBinary {
    public static void main(String[] args) {
        String specialCase =
                "0100110001001100111110111011011100111100011111011101101110011101001011011010111100011110111000000000100011100001000001000000000011101101000001100000000000000001";
        Solution solution = new P67AddBinary().new Solution();
        String result = solution.addBinary("1010", "1011");
        System.out.println(result);
        System.out.println(solution.addBinary2("1010", "1011"));
        // byte[] bytes = solution.bitString2ByteArray(
        //         "0100110001001100111110111011011100111100011111011101101110011101001011011010111100011110111000000000100011100001000001000000000011101101000001100000000000000001");
        // // System.out.println(Arrays.toString(bytes));
        // long binToOct = solution.binToOct(specialCase);
        // System.out.println(binToOct);
        // String binaryString = Long.toBinaryString(binToOct);
        // System.out.println(binaryString);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public long binToOct(String bin) {
            long total = 0;
            char[] binCharArray = bin.toCharArray();
            for (int i = 0; i < binCharArray.length; i++) {
                total = total + binCharArray[i] - 48;
                if (i != binCharArray.length - 1) {
                    total = total << 1;
                }
            }
            return total;
        }

        public byte[] bitString2ByteArray(String bitString) {
            int size = bitString.length() / 8;
            byte[] bytes = new byte[size];

            for (int i = 0, total = 0; i < size; i++) {
                byte b = 0x00;
                for (int j = 0; j < 8; j++, total++) {
                    b |= (byte) ((bitString.charAt(total) - 48) << (7 - j));
                }
                bytes[i] = b;
            }
            return bytes;
        }

        public String addBinary2(String a, String b) {
            StringBuilder builder = new StringBuilder();
            // 表示进位
            int carry = 0;
            // 数组的最后一个index都是,长度-1
            // carry > 0表示 这一步判断,计算结果的位数长度大于原来的两个数字长度,比如 1+11 -> 100,
            // 如果没有这句话计算结果就是 1+11 -> 00
            for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
                // i--,j--都一直会执行的
                // 注意这下,这里的  a.charAt(i) - '0'  -> charAt会根据下标返回一个char元素
                // 并且 字符 - 字符 ,例如 '9' - '0'--> 57-48=9 / 'A' - '0' = 65 - 48--> 17
                // 字符 - 字符 --> 都把字符串转成ASCII十进制数字,然后在进行计算,会返回一个int整型结构
                // 所以这里有一处精妙的地方,就是用根据下标获得到字符 - '0',会得出一个int整型数据
                int val1 = i >= 0 ? a.charAt(i) - '0' : 0;
                int val2 = j >= 0 ? b.charAt(j) - '0' : 0;
                int sum = val1 + val2 + carry;
                carry = sum / 2;
                builder.append(sum % 2);
            }
            return builder.reverse().toString();
        }

        public String addBinary(String a, String b) {
            StringBuilder stringBuilder = new StringBuilder();
            // 表示进位
            int carry = 0;
            for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
                int sum = carry;
                sum += i >= 0 ? a.charAt(i) - '0' : 0;
                sum += j >= 0 ? b.charAt(j) - '0' : 0;
                stringBuilder.append(sum % 2);
                carry = sum / 2;
            }
            stringBuilder.append(carry == 1 ? carry : "");
            return stringBuilder.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}