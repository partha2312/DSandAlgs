import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int m = w.length + 1;
        int n = W + 1;
        int[][] dp = new int[m][n];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(i == 0 || j == 0){
                    continue;
                }
                dp[i][j] = dp[i-1][j];
                if(w[i-1] <= j){
                    dp[i][j] = Math.max(dp[i-1][j-w[i-1]] + w[i-1], dp[i][j]);
                }
            }
        }
        return dp[w.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

