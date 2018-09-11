import junit.framework.TestCase;

import java.util.*;

public class RemoveDuplicateLetters extends TestCase {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> letterRemovalCount = new HashMap<>();
        Stack<Character> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (letterRemovalCount.containsKey(c)) {
                letterRemovalCount.put(c, letterRemovalCount.get(c) + 1);
            } else {
                letterRemovalCount.put(c, 0);
            }
        }

        Set<Character> isAdded = new HashSet<>();
        for (int i = 0; i < len; ++i) {
            char currentChar = s.charAt(i);
            if (!stack.isEmpty()) {
                char topChar = stack.peek();
                while (!stack.isEmpty() &&
                    letterRemovalCount.get(topChar) > 0 &&
                    topChar >= currentChar &&
                    !isAdded.contains(currentChar)) {

                    stack.pop();
                    letterRemovalCount.put(topChar, letterRemovalCount.get(topChar) - 1);
                    isAdded.remove(topChar);

                    if (!stack.isEmpty()) {
                        topChar = stack.peek();
                    } else {
                        break;
                    }
                }
            }

            if (!isAdded.contains(currentChar)) {
                stack.push(currentChar);
                isAdded.add(currentChar);
            } else {
                letterRemovalCount.put(currentChar, letterRemovalCount.get(currentChar) - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }

    public void test() {
        assertEquals("bac", removeDuplicateLetters("bbcaac"));
        assertEquals("abc", removeDuplicateLetters("abacb"));
        assertEquals("", removeDuplicateLetters(""));
        assertEquals("a", removeDuplicateLetters("a"));
        assertEquals("a", removeDuplicateLetters("aaaa"));
        assertEquals("abc", removeDuplicateLetters("babacaac"));
        assertEquals("abc", removeDuplicateLetters("bcabc"));
        assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
        assertEquals("acdb", removeDuplicateLetters("badcdb"));
    }
}
