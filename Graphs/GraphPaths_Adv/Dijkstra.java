import java.util.*;

public class Dijkstra {
    static Queue<Node> queue;
    static int[] dist;
    private static class Node implements Comparable<Node>{
        int idx;
        int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if(this.weight < o.weight) return -1;
            else return 1;
        }
    }

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        queue = new PriorityQueue<>();
        dist = new int[adj.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        queue.add(new Node(s, 0));
        while(!queue.isEmpty()){
            Node currentNode = queue.poll();
            Integer currentIdx = currentNode.idx;
            Integer currentDist = currentNode.weight;
            if(currentIdx == t) return currentDist;
            int foo = 0;
            for(Integer neighbor : adj[currentIdx]){
                int temp = currentDist + cost[currentIdx].get(foo++);
                if(dist[neighbor] > temp){
                    dist[neighbor] = temp;
                    queue.add(new Node(neighbor, temp));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

