import junit.framework.TestCase;

import java.util.Stack;

public class DecodeString extends TestCase {
    public String decodeString(String s) {
        if (s.isEmpty()) {
            return s;
        }

        Stack<StringBuilder> processor = new Stack<>();
        processor.push(new StringBuilder());
        Character prevChar = s.charAt(0);
        processor.peek().append(prevChar);

        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '[') {
                processor.push(new StringBuilder());

            } else if (c == ']') {
                String op = processor.pop().toString();
                int quantifier = Integer.parseInt(processor.pop().toString());

                StringBuilder sb;
                if (processor.isEmpty()) {
                    sb = new StringBuilder();
                } else {
                    sb = processor.pop();
                }

                for (int j = 0; j < quantifier; ++j) {
                    sb.append(op);
                }
                processor.push(sb);

            } else if (isDigit(c)) {
                if (!isDigit(prevChar)) {
                    processor.push(new StringBuilder());
                }
                processor.peek().append(c);

            } else {
                processor.peek().append(c);
            }

            prevChar = c;
        }


        return processor.pop().toString();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public void test() {
        testSingle("", "");
        testSingle("a", "a");
        testSingle("2[a]", "aa");
        testSingle("2[a2[b]]", "abbabb");
        testSingle("2[a]b", "aab");
        testSingle("2[a]3[b]", "aabbb");
        testSingle("2[a3[c]]3[de]", "acccacccdedede");
        testSingle("11[ab]", "ababababababababababab");
        testSingle("11[a]12[b]", "aaaaaaaaaaabbbbbbbbbbbb");
        testSingle("3[a]2[bc]", "aaabcbc");
        testSingle("3[a2[c]]", "accaccacc");
        testSingle("2[abc]3[cd]ef", "abcabccdcdcdef");
    }

    private void testSingle(String input, String expected) {
        assertEquals(expected, decodeString(input));
    }
}
