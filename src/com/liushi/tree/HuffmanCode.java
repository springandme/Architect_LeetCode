package com.liushi.tree;

import java.io.*;
import java.util.*;

/**
 * @ClassName HuffmanCode
 * @Description 赫夫曼编码
 * @Author liushi
 * @Date 2020/10/26 20:54
 * @Version V1.0
 **/
public class HuffmanCode {

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 思路:
     * 1.将赫夫曼编码表存放在Map<Byte,String>形式
     * o[111]: 1000 u[117]: 10010 d[100]: 100110 y[121]: 100111 i[105]: 101
     * 2.在生成赫夫曼编码表示,需要拼接路径,定义一个StringBuilder存储某个叶子节点的路径
     */
    public static StringBuilder stringBuilder = new StringBuilder();

    public static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        // 测试压缩文件
        // String srcFile = "E://bizi//sun.bmp";
        // String dstFile = "e://bizi//dst.zip";
        // zipFile(srcFile, dstFile);
        // System.out.println("压缩文件成功~");

        // 测试解压文件
        // String zipFile = "e://bizi//dst.zip";
        // String dstFile = "e://bizi//sun2.bmp";
        // unZipFile(zipFile, dstFile);
        // System.out.println("解压文件成功~");

        String content = "i like like like java do you like a java";
        // 将content转为字节数组  ,以ASCII为转换方式 比如 字符 a转为97
        byte[] bytes = content.getBytes();
        // 发送huffmanCodesBytes数组
        byte[] huffmanZip = huffmanZip(bytes);
        System.out.println("压缩后的结果是: " + Arrays.toString(huffmanZip));
        double proportion = (double) (bytes.length - huffmanZip.length) / bytes.length;
        System.out.println("压缩率为:=" + proportion);

        // 测试一把byteToBitString方法
        // String toBitString = byteToBitString((byte) 1);
        // System.out.println(toBitString);
        byte[] decode = decode(huffmanCodes, huffmanZip);
        String decodeStr = new String(decode);
        System.out.println("解压后的结果是: " + decodeStr);


        /*
        分步过程
        byte[] contentBytes = content.getBytes();
        String toString1 = Arrays.toString(contentBytes);
        System.out.println(toString1);
        // System.out.println(contentBytes.length); // 40
        List<Node> nodes = getNodes(contentBytes);
        nodes.forEach(System.out::println);

        // 测试一把,创建的二叉树
        Node huffmanTree = createHuffmanTree(nodes);
        preOrder(huffmanTree);

        // 测试一把是否生成了对应的赫夫曼编码
        Map<Byte, String> codes = getCodes(huffmanTree);
        System.out.println("\n~生成的赫夫曼编码表= " + codes);

        byte[] zip = zip(contentBytes, codes);
        String toString = Arrays.toString(zip); // 40
        System.out.println(toString);
        */
    }

    /**
     * 编写一个方法,完成对压缩文件的解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到那个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        // 1.定义文件输入流
        InputStream is = null;
        // 2.定义文件对象输入流
        ObjectInputStream ois = null;
        // 3.定义文件的输出流
        OutputStream os = null;

        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取bytes数组huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取到赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            // 进行解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes这个数组写入到目标we你按dstFile
            os = new FileOutputStream(dstFile);
            // 写数据到dstFile
            os.write(bytes);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 编写方法,将一个文件进行压缩
     * 写入dstFile文件时,将赫夫曼编码压缩的byte数组和编码表一起写入到对象流输出流中
     *
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到那个目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        // 定义输出流
        FileOutputStream fileOutputStream = null;
        // 定义文件对象输出流
        ObjectOutputStream objectOutputStream = null;
        // 定义文件的输入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] bytes = new byte[fileInputStream.available()];
            // 读取文件
            fileInputStream.read(bytes);
            // 直接对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            // 创建文件输出流,准备存放压缩文件
            fileOutputStream = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // 把赫夫曼编码后的字节数组写入到压缩文件
            objectOutputStream.writeObject(huffmanBytes);
            // 这里我们以对象流的方式写入赫夫曼编码,是为了以后我们回复源文件时使用
            // 注意:一定要把赫夫曼编码写入到压缩文件
            objectOutputStream.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 完成数据的解压
     * 思路
     * 1.将huffmanCodesBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * 重写先转成赫夫曼编码对应的二进制的字符串"1010100110111101111010011011......"
     *~2.赫夫曼编码对应的二进制的字符串"101010011011...."==>对照赫夫曼编码 ==>"i like like like java you"
     */

    /**
     * 将一个byte转成一个二进制字符串
     *
     * @param b    传入的byte
     * @param flag 标志是否需要补高位,如果是true,表示需要补高位,如果false表示不补,如果是最后一个字节,无须补高位
     * @return 是该b对应的二进制的字符串 (注意是按补码返回)
     */
    private static String byteToBitString(byte b, boolean flag) {
        // 使用变量保存b,将b转为int
        int temp = b;
        // 如果是正数,我们还需要存在补高位

        if (flag) {
            // |与运算, 按位与 256 1 0000 0000 | 0000 0001 => 1 0000 0001
            temp |= 256;
        }

        // 这里返回的是temp对应的二进制的补码
        String binaryString = Integer.toBinaryString(temp);
        if (flag) {
            return binaryString.substring(binaryString.length() - 8);
        } else {
            return binaryString;
        }
    }

    /**
     * 编写一个方法,完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的byte数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1.先得到huffmanBytes对应的二进制的字符串,形式1010100010111....
        StringBuilder stringBuilder = new StringBuilder();
        // 2.将byte数组转出二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(huffmanBytes[i], !flag));
        }
        System.out.println("赫夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());
        // 3.把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换[key-value调换],因为反向查询 a ->100 100-> a
        Map<String, Byte> map = new HashMap<>();
        Set<Byte> keySet = huffmanCodes.keySet();
        for (Byte aByte : keySet) {
            String value = huffmanCodes.get(aByte);
            map.put(value, aByte);
        }
        System.out.println("赫夫曼编码表map= " + map);

        // 创建一个集合,存放byte
        List<Byte> list = new ArrayList<>();
        // i 可以理解成索引,扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;          //  小的计数器
            boolean flag = true;

            while (flag) {
                // 取出一个 '1' '0'
                // i不动,让count移动,直到匹配一个字符
                String key = stringBuilder.substring(i, i + count);
                if (!map.containsKey(key)) {
                    // 没有匹配到
                    count++;
                } else {
                    // 匹配到,把匹配到的key加入到list,并退出while
                    list.add(map.get(key));
                    flag = false;
                }
            }
            // i直接移动到count位置
            i += count;
        }
        // System.out.println("list= " + list);
        // 把list中的数据放入到byte[] 并返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 使用一个方法,将前面的方法封装起来,把一个字符串处理为压缩后的字节数组
     *
     * @param contentBytes 需要压缩处理字节数组
     * @return 是经过赫夫曼编码处理后的字节数组(压缩后的字节数组)
     */
    private static byte[] huffmanZip(byte[] contentBytes) {
        // 统计每个字串出现的次数
        List<Node> nodes = getNodes(contentBytes);

        // 根据这个list集合,创建的二叉树
        Node huffmanTree = createHuffmanTree(nodes);

        // 根据二叉树,创建赫夫曼编码,规定左子树为0,右子树为1,最终结果为o[111]: 1000 u[117]: 10010 d[100]: 100110 y[121]: 100111 i[105]: 101
        Map<Byte, String> codes = getCodes(huffmanTree);

        // 根据生成赫夫曼编码,压缩得到压缩后的赫夫曼编码字节数组
        return zip(contentBytes, codes);
    }


    /**
     * 编写一个方法,将字符串对应的byte[]数组,通过生成的赫夫曼编码包,返回一个赫夫曼编码压缩后的byte[]
     * 举例: String content = "i like like like java do you like a java";
     * byte[] contentBytes = content.getBytes();
     * 返回的是"字符串" 10101001101111011110100110111101111010011011110111101000011000011100110011110000110
     * => 对应的byte[] huffmanCodeBytes,即8位对应一个byte,放入发哦huffmanCodeBytes
     *
     * @param bytes        这是原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        // 1.利用huffmanCodes 将bytes转出赫夫曼编码的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes数组
        for (byte aByte : bytes) {
            // System.out.println(aByte);
            stringBuilder.append(huffmanCodes.get(aByte));
        }
        // 10101000101111111100100010111111110010001011111111001001010011011100011100000110111
        // System.out.println(stringBuilder);
        // 133
        // System.out.println(stringBuilder.length());

        // 将"10101000101111111100100010" 转出byte[]数组

        // 统计返回byte[] huffmanCodeBytes[]长度
        int len;
        // 一句话 int len = (stringBuilder.length() + 7) / 8;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        // 创建存储压缩后的byte数组
        byte[] huffmanCodesBytes = new byte[len];
        // 记录是第几个byte
        int count = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            // 因为是每8位对应一个byte,所以步长+8
            String strByte;
            // 说明最后一段不够8位了
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转出一个byte[使用Integer类中parseInt将字符串转为二进制数字],放入到huffmanCodesBytes
            huffmanCodesBytes[count] = (byte) Integer.parseInt(strByte, 2);
            count++;
        }

        return huffmanCodesBytes;
    }

    /**
     * 为了调用方便,我们重载getCodes
     *
     * @param root
     * @return
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能:将传入的node结点的所有叶子节点的赫夫曼编码得到,并放入到HuffmanCodes集合中
     *
     * @param node          传入结点
     * @param code          路径:左子结点是0,右子结点为0
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code加入到stringBuilder2
        stringBuilder2.append(code);
        // 如果node==null不处理
        if (node != null) {
            // 判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {
                // 非叶子结点
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                // 说明是一个叶子结点,就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 将准备构建赫夫曼树的Node结点放到List
     *
     * @param bytes 接收字节数组
     * @return 返回的就是List形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 1.先创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        // 遍历bytes,统计每一个bytes出现的次数->map  HashMap不允许key重复
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (map.containsKey(b)) {
                map.put(b, map.get(b) + 1);
            } else {
                map.put(b, 1);
            }
        }

        // 遍历map.将每一个键值对转出Node对象,并加入nodes集合
        Set<Byte> keySet = map.keySet();
        for (Byte aByte : keySet) {
            Integer value = map.get(aByte);
            nodes.add(new Node(aByte, value));
        }

        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            // 排序,根据weight从小到大排序
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树,它的根节点没有data,只有权值
            Node parents = new Node(null, leftNode.wight + rightNode.wight);

            parents.left = leftNode;
            parents.right = rightNode;

            // 将已经处理的两颗二叉树结点从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树,加入到nodes
            nodes.add(parents);
        }

        return nodes.get(0);
    }

    /**
     * 前序遍历的方法
     *
     * @param root
     */
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrderTraverse();
        } else {
            System.out.println("赫夫曼树为空!");
        }
    }
}


/**
 * 创建node,待数据和权值
 */
class Node implements Comparable<Node> {
    // 存放数据[字符]本身,比如'a' => 97  ' ' =>32
    public Byte data;
    // 权值,表示字符出现的次数
    public int wight;
    public Node left;
    public Node right;

    public Node() {
        super();
    }


    public Node(Byte data, int wight) {
        super();
        this.data = data;
        this.wight = wight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.wight - o.wight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", wight=" + wight +
                '}';
    }

    public void preOrderTraverse() {
        System.out.print(this.wight + " ");
        if (this.left != null) {
            this.left.preOrderTraverse();
        }
        if (this.right != null) {
            this.right.preOrderTraverse();
        }
    }
}
