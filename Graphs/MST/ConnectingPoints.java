import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectingPoints {
    static PriorityQueue<Node> queue;
    private static class Node implements Comparable<Node> {
        int idx;
        double dist;

        public Node(int idx, double dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            if(this.dist > o.dist) return 1;
            else return -1;
        }
    }

    private static void updatePriority(int idx, double distance){
        Node temp = null;
        for(Node node : queue){
            if(node.idx == idx){
                temp = node;
                break;
            }
        }
        queue.remove(temp);
        queue.add(new Node(idx, distance));
    }

    private static double distance(int x1, int y1, int x2, int y2){
        double distance = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
        return Math.sqrt(distance);
    }

    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        double[] cost = new double[x.length];
        Arrays.fill(cost, 10000.0);
        cost[0] = 0;
        queue = new PriorityQueue<>();
        for(int i=0;i<cost.length;++i){
            queue.add(new Node(i, cost[i]));
        }
        HashSet<Integer> set = new HashSet<>();
        while(set.size() < x.length){
            Node v = queue.poll();
            result += v.dist;
            set.add(v.idx);
            for(int i=0;i<x.length;++i){
                if(!set.contains(i)){
                    double distance = distance(x[v.idx], y[v.idx], x[i], y[i]);
                    if(distance < cost[i]){
                        cost[i] = distance;
                        updatePriority(i, distance);
                    }
                }
            }
        }
        return result;
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
        System.out.println(minimumDistance(x, y));
    }
}

