package com.lpp.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;

    private int[][] edges;

    private int numOfEdges;

    boolean[] isVisited = new boolean[5];


    public static void main(String[] args) {
        int n = 5;
        String vertexValue[] = {"A","B","C","D","E"};

        Graph graph = new Graph(n);

        for (String value : vertexValue) {
            graph.insertVertex(value);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();

//        graph.dfs();
        graph.bfs();
    }

    public Graph(int n) {
        this.edges = new int[n][n];
        vertexList = new ArrayList<>();
        numOfEdges = 0;
    }

    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }

        return -1;
    }
    //根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return  j;
            }
        }
        return -1;
    }

    public void dfs(boolean[] isVisited, int i) {
        System.out.println(getValueByIndex(i)+"->");
        isVisited[i] = true;

        int w = getFirstNeighbor(i);
        while (w != -1) {

            if (!isVisited[w]) {
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs() {
        for (int i = 0; i < getNumOfEdges(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
    }

    public void bfs(boolean[] isVisited, int i) {
        int u;
        int w;
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.println(getValueByIndex(i));

        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);

            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.println(getValueByIndex(w)+"=>");
                    isVisited[w] = true;

                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }
    public void bfs() {
        for (int i = 0; i < getNumOfEdges(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));

        }
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
