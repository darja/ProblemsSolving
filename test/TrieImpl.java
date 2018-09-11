import junit.framework.TestCase;

import java.util.HashMap;

public class TrieImpl extends TestCase {

    public void testTrie() {
        Trie trie = new Trie();
        trie.insert("image");
        trie.insert("imagination");
        trie.insert("last");
        trie.insert("and");

        assertTrue(trie.search("image"));
        assertTrue(trie.startsWith("imag"));
        assertFalse(trie.search("imag"));
        assertFalse(trie.search("imagi"));
        assertFalse(trie.search("an"));
        assertTrue(trie.search("and"));

        trie.insert("abc");
        assertTrue(trie.search("abc"));
        assertTrue(trie.startsWith("ab"));
        assertFalse(trie.search("ab"));

        trie.insert("ab");
        assertTrue(trie.search("abc"));
        assertTrue(trie.startsWith("ab"));
        assertTrue(trie.search("ab"));
    }

    public class TrieNode {
        private HashMap<Character, TrieNode> children;

        // Initialize your data structure here.
        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
        }

        public TrieNode createOrGet(char key) {
            if (!children.containsKey(key)) {
                children.put(key, new TrieNode());
            }
            return children.get(key);
        }

        public TrieNode getChild(char key) {
            return children.get(key);
        }

        public boolean isEnd() {
            return children.containsKey(null);
        }

        public void setEnd() {
            children.put(null, null);
        }
    }

    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                node = node.createOrGet(c);
            }
            node.setEnd();
        }

        private TrieNode getEndNodeForPrefix(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); ++i) {
                char c = prefix.charAt(i);
                node = node.getChild(c);
                if (node == null) {
                    return null;
                }
            }
            return node;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode end = getEndNodeForPrefix(word);
            return end != null && end.isEnd();
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode end = getEndNodeForPrefix(prefix);
            return end != null;
        }
    }
}
