import java.util.*;

public class NegativeCycle {
    static int[] dist;
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        int n = adj.length;
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE/2);
        dist[0] = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int foo = 0;
                for(int neighbor : adj[j]){
                    int temp = dist[j] + cost[j].get(foo++);
                    if(dist[neighbor] > temp){
                        dist[neighbor] = temp;
                        if(i == (n-1)) {
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
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
        System.out.println(negativeCycle(adj, cost));
    }
}

