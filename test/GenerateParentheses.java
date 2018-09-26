import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses extends TestCase {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        if (n > 0) {
            generateIter("", 0, 0, result, n);
        }

        return result;
    }

    private void generateIter(String str, int open, int close, List<String> list, int parenCount) {
        if (str.length() == parenCount * 2) {
            list.add(str);
            return;
        }

        if (open < parenCount) {
            generateIter(str + "(", open + 1, close, list, parenCount);
        }

        if (open > close) {
            generateIter(str + ")", open, close + 1, list, parenCount);
        }
    }

    public void test() {
        testSingle(0, new String[0]);
        testSingle(1, new String[] {"()"});
        testSingle(2, new String[] {"()()", "(())"});
        testSingle(3, new String[] {"()()()", "(())()", "((()))", "()(())", "(()())"});
        testSingle(4, new String[] {"()()()()", "(()()())","(())(())",
            "((())())", "(())()()", "()(())()",
            "(((())))", "((()))()", "()((()))",
            "(()(()))", "()()(())",
            "((()()))", "()(()())", "(()())()"});
    }

    private void testSingle(int input, String[] expected) {
        long start = System.nanoTime();
        List<String> result = generateParenthesis(input);
        long end = System.nanoTime();

        System.out.printf("%d: Time: %d mks\n", input, (end - start) / 1000);

        assertEquals(result.size(), expected.length);

        for (String s : expected) {
            assertTrue(result.contains(s));
        }
    }

}
