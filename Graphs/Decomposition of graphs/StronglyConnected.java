import java.util.*;

public class StronglyConnected {

    static Integer CLOCK = 1;
    static int[] used;
    static ArrayList<Integer>[] reverseAdj;
    static int[] postorder;
    static HashMap<Integer, Integer> map;

    private static void dfs(ArrayList<Integer>[] graph, int idx){
        if(used[idx] > 0) return;
        used[idx] = 1;
        CLOCK++;
        for(Integer child : graph[idx]){
            dfs(graph, child);
        }
        postorder[CLOCK] = idx;
        map.put(idx, CLOCK++);
    }

    private static void dfsmain(ArrayList<Integer>[] graph, int idx){
        if(postorder[map.get(idx)] < 0) return;
        else postorder[map.get(idx)] = -1;
        for(Integer child : graph[idx]){
            dfsmain(graph, child);
        }
    }

    private static int returnNextIdx(){
        for(int i=0;i<used.length;++i){
            if(used[i] == 0) return i;
        }
        return -1;
    }

    private static void buildReverseGraph(ArrayList<Integer>[] adj){
        for(int i=0;i<adj.length;++i){
            for(Integer child : adj[i]){
                reverseAdj[child].add(i);
            }
        }
    }

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
        int result = 0;
        int length = adj.length;
        reverseAdj = (ArrayList<Integer>[]) new ArrayList[length];
        for(int i=0;i<length;++i){
            reverseAdj[i] = new ArrayList<>();
        }
        used = new int[length];
        postorder = new int[2*length+1];
        Arrays.fill(postorder, -1);
        map = new HashMap<>();
        buildReverseGraph(adj);
        int idx = 0;
        while(idx >= 0){
            dfs(reverseAdj, idx);
            idx = returnNextIdx();
        }
        for(int i=postorder.length-1;i>=0;i--){
            if(postorder[i] >= 0) {
                ++result;
                dfsmain(adj, postorder[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}
