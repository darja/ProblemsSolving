package stack;

import junit.framework.TestCase;

import java.util.Stack;

/**
 * @see <a href="https://leetcode.com/problems/valid-parentheses/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class Braces extends TestCase {
    public void test() {
        assertTrue(isValid("{}[]([])"));
        assertTrue(isValid("{}{{[]([])}}"));
        assertFalse(isValid("{}{{[]([])}"));
        assertFalse(isValid("((([]"));
    }

    public boolean isValid(String s) {
        Stack<Character> chars = new Stack<Character>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            switch (c) {
                case '(':
                case '[':
                case '{':
                    chars.push(c);
                    break;

                case ')':
                    if (!isMatch(chars, '(')) {
                        return false;
                    }
                    break;

                case ']':
                    if (!isMatch(chars, '[')) {
                        return false;
                    }
                    break;

                case '}':
                    if (!isMatch(chars, '{')) {
                        return false;
                    }
                    break;
            }
        }
        return chars.isEmpty();
    }

    private boolean isMatch(Stack<Character> chars, char valid) {
        if (chars.isEmpty()) {
            return false;
        }
        char prev = chars.pop();
        return valid == prev;
    }
}
