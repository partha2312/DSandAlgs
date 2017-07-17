import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> optimal_sequence_dp(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> sequence = new ArrayList<>();
        for(int i=2;i<=n;++i){
            dp[i] = Integer.MAX_VALUE;
            if(i % 3 == 0){
                if((dp[i/3] + 1) < dp[i]){
                    dp[i] = Math.min(dp[i/3] + 1, dp[i]);
                    map.put(i, i/3);
                }
            }
            if(i % 2 == 0){
                if((dp[i/2] < dp[i])){
                    dp[i] = Math.min(dp[i/2] + 1, dp[i]);
                    map.put(i, i/2);
                }
            }
            if((dp[i-1] + 1) < dp[i]) {
                dp[i] = Math.min(dp[i-1] + 1, dp[i]);
                map.put(i, (i-1));
            }
        }
        sequence.add(n);
        while(n != 1){
            n = map.get(n);
            sequence.add(n);
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        /*List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
        System.out.println();*/
        List<Integer> sequence = optimal_sequence_dp(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

