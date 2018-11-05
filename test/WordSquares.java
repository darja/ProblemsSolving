import util.ListTestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class WordSquares extends ListTestCase {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new LinkedList<>();
        LinkedList<String> interim = new LinkedList<>();
        wordSquaresIter(result, words, interim, words[0].length());

        return result;
    }

    private void wordSquaresIter(List<List<String>> result, String[] words, LinkedList<String> interim, int squareSize) {
        if (interim.size() == squareSize) {
            if (isWordSquare(interim)) {
                result.add(new ArrayList<>(interim));
            }
            return;
        }

        for (String word : words) {
            interim.add(word);
            wordSquaresIter(result, words, interim, squareSize);
            interim.removeLast();
        }
    }

    private boolean isWordSquare(LinkedList<String> words) {
        HashMap<String, Integer> code = new HashMap<>();
        for (String word : words) {
            code.put(word, code.containsKey(word) ? code.get(word) + 1 : 1);
        }

        int len = words.size();
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
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
