import java.util.*;

public class Bipartite {
    static int[] isUsed;
    static int[] colors;

    static int nextIdx(){
        for(int i=0;i<isUsed.length;++i){
            if(isUsed[i] == 0) return i;
        }
        return -1;
    }

    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        isUsed = new int[adj.length];
        colors = new int[adj.length];
        Arrays.fill(colors, -1);
        Queue<Integer> queue = new LinkedList<>();
        int idx = 0;
        while(idx >= 0){
            queue.add(idx);
            isUsed[idx] = 1;
            colors[idx] = 0;
            while(!queue.isEmpty()){
                Integer current = queue.poll();
                for(int neighbour : adj[current]){
                    if(isUsed[neighbour] > 0) {
                        if(colors[current] == colors[neighbour])
                            return 0;
                    } else {
                        isUsed[neighbour] = 1;
                        queue.add(neighbour);
                        colors[neighbour] = colors[current] == 0 ? 1 : 0;
                    }
                }
            }
            idx = nextIdx();
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

