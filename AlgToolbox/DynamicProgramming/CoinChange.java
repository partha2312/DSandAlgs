package DynamicProgramming;

import java.util.Scanner;

/**
 * Created by Partha on 7/13/17.
 */
public class CoinChange {
    static int[] coins;
    public static int getChange(int m){
        int[] dp = new int[m + 1];
        dp[0] = 0;
        for(int i = 1; i < m+1; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int coin : coins){
                if(i >= coin){
                    int numOfCoins = dp[i - coin] + 1;
                    if(numOfCoins < dp[i]) dp[i] = numOfCoins;
                }
            }
        }
        return dp[m];
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        coins = new int[n];
        for(int i = 0; i < n; i++){
            coins[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}
