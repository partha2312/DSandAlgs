import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
		    // Replace this code with a faster implementation
			ArrayList<Integer>[] arr = new ArrayList[n+1];
			for(int i=0;i<n;++i){
				int curr = parent[i];
				if(arr[curr+1] == null){
					arr[curr+1] = new ArrayList<>();
				}
				arr[curr+1].add(i);
			}
			int count = 0;
			Queue<Integer> queue = new LinkedList<>();
            queue.add(arr[0].get(0));
            queue.add(-1);
            while(!queue.isEmpty()){
                Integer curr = queue.poll();
                if(curr == -1){
                    if(!queue.isEmpty()) {
                        queue.add(-1);
                    }
                    ++count;
                    continue;
                }
                List<Integer> temp = arr[curr + 1];
                if(temp != null){
                    for(Integer children : temp){
                        queue.add(children);
                    }
                }
            }
			return count;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
