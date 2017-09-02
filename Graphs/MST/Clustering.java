import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {
    static PriorityQueue<Edge> queue;
    static Node[] nodes;

    private static class Node{
        int a;
        int b;
        int parent;
        int rank;

        public Node(int a, int b, int parent) {
            this.a = a;
            this.b = b;
            this.parent = parent;
            this.rank = 0;
        }
    }

    private static class Edge implements Comparable<Edge>{
        int x;
        int y;
        double weight;

        public Edge(int x, int y, double weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.weight > o.weight) return 1;
            else return -1;
        }
    }

    private static double distance(int x1, int y1, int x2, int y2){
        double distance = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
        return Math.sqrt(distance);
    }

    private static int find(int i){
        if(i != nodes[i].parent){
            nodes[i].parent = find(nodes[i].parent);
        }
        return nodes[i].parent;
    }

    private static void union(int i, int j){
        int u = find(i);
        int v = find(j);

        if(u == v) return;
        if(nodes[u].rank > nodes[v].rank){
            nodes[v].parent = u;
        } else {
            nodes[u].parent = v;
            if (nodes[u].rank == nodes[v].rank) {
                nodes[v].rank++;
            }
        }
    }

    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
        nodes = new Node[x.length];
        for(int i=0;i<x.length;++i){
            nodes[i] = new Node(x[i], y[i], i);
        }
        queue = new PriorityQueue<>();
        for(int i=0;i<x.length;++i){
            for(int j=i+1;j<x.length;++j){
                queue.add(new Edge(i, j, distance(x[i], y[i], x[j], y[j])));
            }
        }
        int unions = 0;
        while(!queue.isEmpty()){
            Edge edge = queue.poll();
            if(find(edge.x) != find(edge.y)){
                unions++;
                union(edge.x, edge.y);
            }
            if(unions > (x.length-k)) return edge.weight;
        }
        return -1.;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

