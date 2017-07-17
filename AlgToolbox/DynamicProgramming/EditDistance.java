import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    //write your code here
    int m = s.length();
    int n = t.length();
    int[][] dp = new int[m + 1][n + 1];
    for(int i = 0; i <= n; ++i){
      dp[0][i] = i;
    }
    for(int i = 0; i <= m; ++i){
      dp[i][0] = i;
    }
    for(int i = 1; i <= m; ++i){
      for(int j = 1; j <= n; ++j){
        char ch1 = s.charAt(i-1);
        char ch2 = t.charAt(j-1);
        int a = dp[i-1][j] + 1;
        int b = dp[i][j-1] + 1;
        int c;
        if(ch1 == ch2){
          c = dp[i-1][j-1];
        }else{
          c = dp[i-1][j-1] + 1;
        }
        dp[i][j] = Math.min(a, Math.min(b, c));
      }
    }
    return dp[m][n];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
