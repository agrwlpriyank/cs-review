 /**
 * Represents a single node of the prefix trie
 */
class TrieNode {	
	// Assuming the possible keys to be lowercase roman alphabets
	int alpha_size = 26;
	private TrieNode[] links;
	public TrieNode() {
		links = new TrieNode[alpha_size];
	}
	private boolean isEnd;
	
	public boolean containsKey(char c) {
		return this.links[c - 'a'] != null;
	}
	public TrieNode get(char c) {
		if(this.containsKey(c)) {
			return this.links[c - 'a'];
		}
		else {
			return null;
		}
	}
	public void put(char c) {
		if(!this.containsKey(c)) {
			this.links[c - 'a'] = new TrieNode();;
		}
	}
	public boolean getEnd() {
		return this.isEnd;
	}
	public void setEnd() {
		isEnd = true;
	}
	public void resetEnd() {
		isEnd = false;
	}
}

class Trie {
	TrieNode root;
	public Trie() {
		root = new TrieNode();
	}
	
	 /**
     * This method is used to insert a particular word in the trie. Time Complexity - O(word.length)
     * @param word   String word to be inserted
     * @return void
     */
	public void insert(String word) {
		TrieNode n = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(!n.containsKey(c)) {
				n.put(c);
			}
			n = n.get(c);
		}
		n.setEnd();
	}
	
	/**
     * This method is used to search the given word. Time Complexity - O(word.length)
     * @param word   String word to be searched
     * @return returns the TrieNode corresponding to the last character of the word.
     */
	private TrieNode search(String word) {
		TrieNode n = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(n.containsKey(c)) {
				n = n.get(c);
			} else {
				return null;
			}
		}
		return n;
	}
	
	public boolean searchWord(String word) {
		TrieNode n = search(word);
		return n != null && n.getEnd();
	}
	public boolean searchPrefix(String word) {
		TrieNode n = search(word);
		return n != null;
	}
	
	public void display() {
		displayHelper(root, "");
	}
	private void displayHelper(TrieNode n, String word) {
		if(n == null) {
			return;
		}
		if(n.getEnd()) {
			System.out.println(word);
		}
		for(int i = 0; i < n.alpha_size; i++) {
			char c = (char) (i + 'a');
			if(n.containsKey(c)) {
				displayHelper(n.get(c), word + c);
			}
		}
	}
}


class PrefixTrie {
	public static void main(String[] args) {
		Trie obj = new Trie();
		obj.insert("a");
		obj.insert("apple");
		obj.insert("application");
		obj.insert("boy");
		obj.insert("cat");
		System.out.println(obj.searchWord("apple"));
		System.out.println(obj.searchWord("app"));
		System.out.println(obj.searchPrefix("bo"));
		obj.display();
	
	}
}