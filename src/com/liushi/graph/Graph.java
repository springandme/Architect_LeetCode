package com.liushi.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * @ClassName Graph
 * @Description TODO
 * @Author liushi
 * @Date 2020/11/4 8:51
 * @Version V1.0
 **/
public class Graph {

    //
    public StringBuilder stringBuilder;
    // 存储顶点集合
    private ArrayList<String> vertexList;
    // 存储图对应的邻接矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 定义一个数组boolean[],记录某个结点是否被访问
    private boolean[] isVisited;

    public Graph(int vertexNum) {
        // 初始化矩阵和vertexList
        this.edges = new int[vertexNum][vertexNum];
        this.vertexList = new ArrayList<>(vertexNum);
        this.numOfEdges = 0;
        this.isVisited = new boolean[vertexNum];
        stringBuilder = new StringBuilder();
    }

    public static void main(String[] args) {
        // 测试一把图是否创建OK
        // String[] vertexes = {"A", "B", "C", "D", "E"};
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        int n = vertexes.length;
        // 创建图对象
        Graph graph = new Graph(n);
        // 循环的添加顶点
        for (String value : vertexes) {
            graph.insertVertex(value);
        }

        // 添加边
        /*graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);*/

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示图
        graph.showGraph();

        // 测试一把,我们的dfs遍历是否OK
        System.out.println("深度优先遍历");
        graph.dfs();
        System.out.println("广度优先遍历");
        graph.myBfs();

    }


    /**
     * 得到index顶点第一个邻接结点的下标w
     *
     * @param index index顶点的下标
     * @return 如果存在就返回对应的下标, 否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] != 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 根据前一个邻接结点的下标来获得下一个邻接结点下标
     *
     * @param v1 表示顶点v1的下标
     * @param v2 v1的第一个邻接下标
     * @return 如果存在就返回对应的下标, 否则返回-1
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] != 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 对dfs进行重载,遍历我们所有的结点,并进行dfs
     */
    public void dfs() {
        // 将isVisited数组中的元素全部初始化为false
        Arrays.fill(isVisited, false);
        // 遍历所有的结点,进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
        String result = stringBuilder.toString().substring(0, stringBuilder.length() - 2);
        System.out.println("\n" + result);
    }

    /**
     * 深度优先遍历算法 Traverse
     * 1) 访问初始结点 i， 并标记结点 i 为已访问。
     * 2) 查找结点 i 的第一个邻接结点 w。
     * 3) 若 w 存在， 则继续执行 4， 如果 w 不存在， 则回到第 1 步,将从i的下一个结点继续。
     * 4) 若 w 未被访问， 对 w 进行深度优先遍历递归（即把 w 当做另一个 i， 然后进行步骤 123） 。
     * 5) 查找结点 i 的 w 邻接结点的下一个邻接结点， 转到步骤 3。
     *
     * @param isVisited 记录某个结点是否被访问的数组
     * @param i         i第一次就是0
     */
    private void dfs(boolean[] isVisited, int i) {
        // 1.访问初始结点 i， 并标记结点 i 为已访问
        // 首先我们访问该结点,输出     ---使用stringBuilder目的就是连接打印的内容,更加美观而已
        stringBuilder.append(getValueByIndex(i)).append("->");
        System.out.print(getValueByIndex(i) + "->");
        // 将该结点设置已经访问
        isVisited[i] = true;

        // 2.查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        // 3.若 w 存在,则继续执行4,如果 w 不存在,则回到第1步[直接跳出这个函数],将从i的下一个结点继续
        // 说明有邻接结点
        while (w != -1) {
            // 4.若w未被访问,对w进行dfs(即把 w 当做另一个i,然后进行步骤123)
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w结点已经被访问,查找结点i的w邻接结点的下一个邻接结点,
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 广度优先遍历
     * 思路:就是以v为起始点,由近至远,依次访问和v有路径相同且路径长度为1,2,......的顶点
     */
    public void bfs() {
        // int w;
        // 使用辅助队列deque和访问标志数组isVisited[]
        Deque<Integer> deque = new ArrayDeque<>();
        // 对图的所有顶点
        for (int v = 0; v < getNumOfVertex(); v++) {
            // 顶点v尚未被访问
            if (!isVisited[v]) {
                isVisited[v] = true;
                System.out.print(getValueByIndex(v) + " ");
                stringBuilder.append(getValueByIndex(v)).append("->");
                // 将顶点v索引进队列
                deque.offer(v);
                // 队列非空
                while (!deque.isEmpty()) {
                    // 队列头元素出队并置为poll
                    int poll = deque.poll();
                    // 从poll的第一个邻接顶点w开始
                    int w = getFirstNeighbor(poll);
                    while (w > 0) {
                        if (!isVisited[w]) {
                            isVisited[w] = true;
                            System.out.print(getValueByIndex(w) + " ");
                            stringBuilder.append(getValueByIndex(w)).append("->");
                            deque.offer(w);
                        }
                        w = getNextNeighbor(poll, w);
                    }
                }
            }
        }
        System.out.println();
        System.out.println(stringBuilder.toString().substring(0, stringBuilder.length() - 2));
    }


    /**
     * 遍历所有的结点,都进行广度优先遍历
     */
    public void myBfs() {
        // 将isVisited数组中的元素全部初始化为false
        Arrays.fill(isVisited, false);
        stringBuilder.replace(0, stringBuilder.length(), "");
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
        System.out.println();
        System.out.println(stringBuilder.toString().substring(0, stringBuilder.length() - 2));
    }

    /**
     * 对一个结点进行广度优先遍历的方法
     *
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited, int i) {
        // 表示队列的头结点对应下标
        int u;
        // 邻接结点w
        int w;
        // 队列,记录结点访问的顺序
        Deque<Integer> deque = new ArrayDeque<>();
        // 访问结点,输出结点信息
        System.out.print(getValueByIndex(i) + "->");
        stringBuilder.append(getValueByIndex(i)).append("->");
        // 标记为被访问
        isVisited[i] = true;
        // 加入队列
        deque.offer(i);
        while (!deque.isEmpty()) {
            // 取出队列的头结点下标
            u = deque.poll();
            // 得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w > 0) {
                // 是否被访问过
                if (!isVisited[w]) {
                    // 标记已经被访问
                    isVisited[w] = true;
                    // 入队列
                    deque.offer(w);
                    System.out.print(getValueByIndex(w) + "->");
                    stringBuilder.append(getValueByIndex(w)).append("->");
                }
                // 如果访问过了, 以u为前驱点,找到w后面的,u的下一个邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * @return 返回结点的个数
     */
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    /**
     * @return 返回边的数目
     */
    public int getNumOfVertex() {
        return this.vertexList.size();
    }


    /**
     * 返回结点i(下标)对应的数据 0->"v1" 1->"v2" 2->"v3" 或 0->"A" 1->"B" 2->"C"
     *
     * @param i 结点i下标
     * @return 返回结点i(下标)对应的数据
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        System.out.print("  ");
        for (String s : vertexList) {
            System.out.print(s + " ");
        }
        System.out.println();

        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(vertexList.get(i) + " ");
            for (int j = 0; j < vertexList.size(); j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 插入顶点
     *
     * @param vertex 顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * v1-v2
     *
     * @param v1     表示点的下标,即是第几个顶点 "A"-"B"  "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        // 无向图,双方都要写一遍
        this.edges[v1][v2] = weight;
        this.edges[v2][v1] = weight;
        // 边数目递增
        numOfEdges++;
    }
}
