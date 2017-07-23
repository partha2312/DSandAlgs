import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    private static int prime = 1000000007;
    private static int x = (int)(Math.random()*(prime))+1;;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static long polyHash(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * x + s.charAt(i)) % prime;
        return hash;
    }

    private static long[] preComputeHashes(Data input){
        int t = input.text.length();
        int p = input.pattern.length();
        long[] h = new long[t - p + 1];
        String s = input.text.substring(t - p);
        h[t-p] = polyHash(s);
        long y = 1;
        for (int i = 1; i <= p; i++) {
            y = (y * x) % prime;
        }

        for (int i = t- p - 1; i >=0; i--) {
            long preHash = x * h[i + 1] + input.text.charAt(i) - y * input.text.charAt(i + p);
            while (preHash < 0) {
                preHash += prime;
            }
            h[i] = preHash % prime;
        }

        return h;

    }

    private static boolean areEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getOccurrences(Data input){
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();

        long pHash = polyHash(input.pattern);

        long[] H = preComputeHashes(input);

        for (int i = 0; i + m <= n; ++i) {
            if (pHash != H[i]) {
                continue;
            }
            if (areEqual(t.substring(i, i + m), s)) {
                occurrences.add(i);
            }
        }

        return occurrences;
    }

    /*private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
	    boolean equal = true;
	    for (int j = 0; j < m; ++j) {
		if (s.charAt(j) != t.charAt(i + j)) {
		     equal = false;
 		    break;
		}
	    }
            if (equal)
                occurrences.add(i);
	}
        return occurrences;
    }*/

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

