import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    char[][] formMatrix(String text){
        char[][] matrix = new char[text.length()][text.length()];
        int row = 0;
        for(int i=text.length()-1; i>=0;--i){
            int col = 0;
            int k = i;
            int j = 0;
            while(k < text.length()){
                matrix[row][col++] = text.charAt(k);
                k++;
            }
            while(j < i){
                matrix[row][col++] = text.charAt(j);
                j++;
            }
            row++;
        }
        return matrix;
    }

    String BWT(String text) {
        StringBuilder result = new StringBuilder();

        // write your code here
        char[][] matrix = formMatrix(text);
        Arrays.sort(matrix, new Comparator<char[]>() {
            @Override
            public int compare(char[] o1, char[] o2) {
                return String.valueOf(o1).compareTo(String.valueOf(o2));
            }
        });
        for(char[] array : matrix){
            result.append(array[array.length-1]);
        }
        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
