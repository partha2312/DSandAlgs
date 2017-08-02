import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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

    class Node implements Comparable<Node>{
        public int idx;
        public char c;

        public Node(int idx, char c){
            this.c = c;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            if(this.c == o.c){
                if(this.idx < o.idx) return -1;
                else return 1;
            }else{
                if(this.c < o.c) return -1;
                else return 1;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if(!(o instanceof Node)) return false;
            Node temp  = (Node) o;
            return temp.c == this.c &&
                    temp.idx == this.idx;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + c;
            result = 31 * result + idx;
            return result;
        }
    }

    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();
        // write your code here

        Node[] bwtNode = new Node[bwt.length()];
        Node[] sortedNode = new Node[bwt.length()];

        for(int i=0;i<bwt.length();++i){
            bwtNode[i] = new Node(i, bwt.charAt(i));
            sortedNode[i] = new Node(i, bwt.charAt(i));
        }

        Arrays.sort(sortedNode);

        int index = 0;
        HashMap<Node, Integer> map = new HashMap<>();
        for(Node node : sortedNode){
            map.put(node, index++);
        }

        Node current = bwtNode[0];
        result.append(current.c);
        while(1==1){
            int idx = map.get(current);
            current = bwtNode[idx];
            if(current.c == '$') {
                break;
            }
            result.append(current.c);
        }
        return result.reverse().toString()+"$";
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
