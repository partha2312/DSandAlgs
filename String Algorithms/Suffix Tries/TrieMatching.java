import java.io.*;
import java.util.*;

/*class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
	}

    public boolean isLeaf() {
    return (next[0] == NA && next[1] == NA && next[2] == NA && next[3] == NA);
}
}*/

public class TrieMatching implements Runnable {
    /*List<Node> trie = new ArrayList<>();
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}*/

	/*void buildTrie(String pattern){
	    int current = 0;
	    for(int j=0;j<pattern.length();++j){
	        Character c = pattern.charAt(j);
	        Integer idx = letterToIndex(c);
	        if(trie.get(current).next[idx] != -1){
	            current = trie.get(current).next[idx];
            }else{
	            trie.add(new Node());
	            trie.get(current).next[idx] = trie.size()-1;
	            current = trie.size()-1;
            }
        }
    }*/

	/*List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		// write your code here
        trie.add(new Node());
        for(String pattern : patterns){
            buildTrie(pattern);
        }
        for (int i = 0; i < text.length(); i++) {
            int k = i;
            int symbol = letterToIndex(text.charAt(k));
            int v = 0;
            while (true) {
                if (trie.get(v). isLeaf()) {
                    result.add(i);
                    break;
                } else if (trie.get(v).next[symbol] != -1) {
                    v = trie.get(v).next[symbol];
                    k++;
                    if (k == text.length()) { //进行特判
                        if (trie.get(v).isLeaf())
                            result.add(i);
                        break;
                    }
                    symbol = letterToIndex(text.charAt(k));

                } else
                    break;
            }
        }
		return result;
	}*/
    private static Node trie = new Node();
    private static class Node{
        public boolean isEnd;
        public Node[] children;
        public Node(){
            children = new Node[4];
            Arrays.fill(children, null);
            isEnd = false;
        }
    }

    private static int letterToIndex (char letter)
    {
        switch (letter)
        {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: assert (false); return -1;
        }
    }

    private static void buildtrie(String pattern){
        Node temp = trie;
        if(pattern.length() == 0){
            return;
        }
        for(int i=0;i<pattern.length();++i){
            Character c = pattern.charAt(i);
            Integer idx = letterToIndex(c);
            if(temp.children[idx] == null){
                temp.children[idx] = new Node();
                temp = temp.children[idx];
            }else{
                temp = temp.children[idx];
            }
            if(i == pattern.length()-1){
                temp.isEnd = true;
            }
        }
    }

    private static List<Integer> solve(String text, Integer n, List<String> patterns){
        List<Integer> result = new ArrayList<>();
        for(String pattern : patterns){
            buildtrie(pattern);
        }
        for(int i=0;i<text.length();++i){
            Node temp = trie;
            int v = i;
            while(1==1){
                Character c = text.charAt(v);
                Integer idx = letterToIndex(c);
                if(temp.children[idx] != null && temp.children[idx].isEnd){
                    result.add(i);
                    break;
                } else if(temp.children[idx] != null){
                    v++;
                    if(v == text.length()){
                        if(temp.children[idx].isEnd){
                            result.add(i);
                        }
                        break;
                    }
                    temp = temp.children[idx];
                }else {
                    break;
                }
            }
        }
        return result;
    }

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
