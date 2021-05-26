import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Solution {
    int[] f ; //并查集find数组

    public int find(int x) {//find函数，判断是否同一个root节点
        return f[x] == x ? x : find(f[x]);
    }

    public int minCostConnectPoints(int[][] points) {
        int h = points.length;
        f=new int[h+1];
        for (int i = 0; i < f.length; i++) {//初始化find数组，让初始每个节点都自成一个集合，互相不联通
            f[i]=i;
        }
        ArrayList<Edge> edges = new ArrayList<>();
        //求边长
        for (int i = 0; i < h; i++) {
            for (int j = i + 1; j < h; j++) {
                int len = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                if (len != 0) {
                    edges.add(new Edge(i, j, len));
                }
            }
        }
        int ans =0;
        Collections.sort(edges);

        for(Edge e:edges){
            int x=e.getX();
            int y =e.getY();
            int len = e.getLen();
            //System.out.println(x+" "+y+" "+len);
            if(find(x)==find(y)) {//如果两个节点是同一个集合中，说明之前已经有其他路径连过了
                continue;
            }
            ans+=len;
            f[find(x)] = find(y);//把两集合合并
        }
        // System.out.println(Arrays.toString(f));
        return ans;
    }
}

class Edge implements Comparable<Edge> {
    private int x;
    private int y;
    private int len;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Edge(int x, int y, int len) {
        this.x = x;
        this.y = y;
        this.len = len;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    @Override
    public int compareTo(Edge o) {//内置比较器实现按len升序
        return Integer.compare(this.len, o.len);
    }
}

