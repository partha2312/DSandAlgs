package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Partha on 7/15/17.
 */
public class DiscreetKnapSack {
    static KnapSack[] knapSacks;
    private static class KnapSack implements  Comparable<KnapSack> {
        int weight;
        int value;
        public KnapSack(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
        @Override
        public int compareTo(KnapSack o) {
            if(this.weight < o.weight) return -1;
            else if(this.weight > o.weight) return 1;
            return 0;
        }
    }

    public static int ksRepetetion(int W){
        int[] dp = new int[W + 1];
        dp[0] = 0;
        for(int i=1;i<=W;++i){
            for(int j=0; j<knapSacks.length;++j){
                if(i >= knapSacks[j].weight){
                    int value = dp[i-knapSacks[j].weight] + knapSacks[j].value;
                    if(value > dp[i]) dp[i] = value;
                }else {
                    break;
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        knapSacks = new KnapSack[n];
        for(int i = 0; i < n; i++){
            KnapSack knapSack = new KnapSack(scanner.nextInt(), scanner.nextInt());
            knapSacks[i] = knapSack;
        }
        int m = scanner.nextInt();
        Arrays.sort(knapSacks);
        System.out.println(ksRepetetion(m));

    }
}
