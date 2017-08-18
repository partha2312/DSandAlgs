import java.util.*;

public class ConnectedComponents {

    public static int isCompleted(int[] isComplete){
        for(int i=0;i<isComplete.length;++i){
            if(isComplete[i] == 0) return i;
        }
        return -1;
    }

    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int[] isComplete = new int[adj.length];
        int complete = 1;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        int idx = 0;
        while(idx >= 0){
            stack.push(idx);
            while(!stack.empty()){
                int curr = stack.pop();
                isComplete[curr] = complete;
                set.add(curr);
                for(int child : adj[curr]){
                    if(!set.contains(child))stack.push(child);
                }
            }
            idx = isCompleted(isComplete);
            ++complete;
        }
        return complete-1;
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
        System.out.println(numberOfComponents(adj));
    }
}

