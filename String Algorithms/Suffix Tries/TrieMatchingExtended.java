import java.io.*;
import java.util.*;



public class TrieMatchingExtended implements Runnable {
	class Node
	{
		public static final int Letters =  4;
		public static final int NA      = -1;
		public int next [];
		public boolean patternEnd;

		Node ()
		{
			next = new int [Letters];
			Arrays.fill (next, NA);
			patternEnd = false;
		}
	}
	List<Node> trie = new ArrayList<>();
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
	}

	void buildTrie(String pattern){
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
			if(j == pattern.length() - 1){
				trie.get(current).patternEnd = true;
			}
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
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
				if (trie.get(v).patternEnd) {
					result.add(i);
					break;
				} else if (trie.get(v).next[symbol] != -1) {
					v = trie.get(v).next[symbol];
					k++;
					if (k == text.length()) { //进行特判
						if (trie.get(v).patternEnd)
							result.add(i);
						break;
					}
					symbol = letterToIndex(text.charAt(k));

				} else
					break;
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
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
