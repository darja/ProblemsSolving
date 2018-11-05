import util.ListTestCase;

import java.util.*;

@SuppressWarnings("WeakerAccess")
public class WordSquares extends ListTestCase {
    public List<List<String>> wordSquares(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word, 0);
        }

        List<List<String>> result = new LinkedList<>();
        LinkedList<String> interim = new LinkedList<>();
        for (String w : words) {
            interim.add(w);
            wordSquaresRec(result, interim, trie, words[0].length());
            interim.removeLast();
        }

        return result;
    }

    private void wordSquaresRec(List<List<String>> result, LinkedList<String> interim, Trie root, int squareSize) {
        int prefixSize = interim.size();

        if (prefixSize == squareSize) {
            result.add(new ArrayList<>(interim));

        } else {
            Trie prefixTrie = root;
            for (String word : interim) {
                prefixTrie = prefixTrie.children.get(word.charAt(prefixSize));
                if (prefixTrie == null) break;
            }

            if (prefixTrie != null) {
                for (String s: prefixTrie.strings) {
                    interim.add(s);
                    wordSquaresRec(result, interim, root, squareSize);
                    interim.removeLast();
                }
            }
        }
    }

    private class Trie {
        Map<Character, Trie> children = new HashMap<>();
        List<String> strings = new LinkedList<>();

        void insert(String s, int fromIndex) {
            strings.add(s);

            if (fromIndex >= s.length()) return;

            char currentChar = s.charAt(fromIndex);
            if (!children.containsKey(currentChar)) {
                Trie newChild = new Trie();
                children.put(currentChar, newChild);
            }
            Trie child = children.get(currentChar);
            child.insert(s, fromIndex + 1);
        }

        @Override
        public String toString() {
            return String.format("Trie [%s]", children.keySet());
        }
    }

    private void testOne(String[] words, String[][] expected) {
        List<List<String>> actual = wordSquares(words);
        assertEquals("Squares count", expected.length, actual.size());

        for (int i = 0; i < expected.length; i++) {
            String[] expectedItem = expected[i];
            boolean found = false;
            for (List<String> actualItem : actual) {
                if (areListsEqualOrdered(expectedItem, actualItem)) {
                    found = true;
                    break;
                }
            }
            assertTrue(String.format("Word square %d not found", i), found);
        }
    }

    public void test1() {
        String[] words = new String[]{"area", "lead", "wall", "lady", "ball"};
        String[][] expected = new String[][]{
            new String[]{"wall", "area", "lead", "lady"},
            new String[]{"ball", "area", "lead", "lady"}
        };
        testOne(words, expected);
    }

    public void test2() {
        String[] words = new String[]{"abat", "baba", "atan", "atal"};
        String[][] expected = new String[][]{
            new String[]{"baba", "abat", "baba", "atan"},
            new String[]{"baba", "abat", "baba", "atal"}
        };
        testOne(words, expected);
    }

    public void test3() {
        String[] words = new String[]{"abaa", "aaab", "baaa", "aaba"};
        String[][] expected = new String[][]{
            new String[]{"aaab", "aaba", "abaa", "baaa"},
            new String[]{"aaab", "abaa", "aaba", "baaa"},
            new String[]{"aaba", "aaab", "baaa", "abaa"},
            new String[]{"aaba", "abaa", "baaa", "aaab"},
            new String[]{"abaa", "baaa", "aaab", "aaba"},
            new String[]{"abaa", "baaa", "aaba", "aaab"},
            new String[]{"baaa", "aaab", "aaba", "abaa"},
            new String[]{"baaa", "aaba", "abaa", "aaab"},
            new String[]{"baaa", "abaa", "aaab", "aaba"},
            new String[]{"baaa", "abaa", "aaba", "aaab"}
        };
        testOne(words, expected);
    }
}
