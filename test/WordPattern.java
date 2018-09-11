import junit.framework.TestCase;

import java.util.HashMap;
import java.util.HashSet;

public class WordPattern extends TestCase {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split("\\s");

        if (str.length() == 0) {
            return words[0].length() == 0;
        }

        if (words.length != pattern.length()) {
            return false;
        }

        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> uniqueWords = new HashSet<>();

        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (map.containsKey(c)) {
                String existedWord = map.get(c);
                if (!existedWord.equals(word)) {
                    return false;
                }
            } else {
                if (uniqueWords.contains(word)) {
                    return false;
                }

                map.put(c, word);
                uniqueWords.add(word);
            }
        }

        return true;
    }

    public void test() {
        assertTrue(wordPattern("abba", "cat dog dog cat"));
        assertTrue(wordPattern("aa", "cat cat"));
        assertTrue(wordPattern("abc", "cat dog parrot"));
        assertTrue(wordPattern("a", "cat"));
        assertTrue(wordPattern("", ""));

        assertFalse(wordPattern("abba", "cat dog dog dog"));
        assertFalse(wordPattern("abba", "dog dog dog dog"));
        assertFalse(wordPattern("abba", "cat dog cat dog"));
        assertFalse(wordPattern("", "cat cat"));
        assertFalse(wordPattern("a", "cat cat"));
        assertFalse(wordPattern("aa", "cat"));
        assertFalse(wordPattern("aaa", "cat"));
    }
}
