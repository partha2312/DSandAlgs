import java.util.*;

public class Acyclicity {
    static Set<Integer> set = new HashSet<>();
    static boolean result = true;
    static int[] complete;
    private static void dfs(int idx, ArrayList<Integer>[] adj){
        complete[idx] = 1;
        if(set.contains(idx)) {
            result = false;
            return;
        }
        else {
            set.add(idx);
        }
        for(Integer child : adj[idx]){
            dfs(child, adj);
        }
        set.remove(idx);
    }
    private static int returnNotComplete(){
        for(int i=0;i<complete.length;++i){
            if(complete[i] == 0)return i;
        }
        return -1;
    }
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
        complete = new int[adj.length];
        int idx = 0;
        while(result && idx >= 0){
            dfs(idx,adj);
            idx = returnNotComplete();
        }
        return result ? 0 : 1;
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
        }
        System.out.println(acyclic(adj));
    }
}

