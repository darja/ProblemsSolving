import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateParentheses extends TestCase {
    public List<String> generateParenthesis1(int n) {
        Set<String> result = new HashSet<>();
        if (n == 1) {
            result.add("()");
        } else if (n >= 1) {
            List<String> prevResult = generateParenthesis(n - 1);

            for (String item : prevResult) {
                result.add("()" + item);
                result.add("(" + item + ")");
                result.add(item + "()");
            }
        }
        return new ArrayList<>(result);
    }

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
    }

    private void testSingle(int input, String[] expected) {
        long start = System.nanoTime();
        List<String> result = generateParenthesis(input);
        long end = System.nanoTime();

        System.out.printf("Time: %d ms", (end - start) / 1000);
        System.out.println();

        assertEquals(result.size(), expected.length);

        for (String s : expected) {
            assertTrue(result.contains(s));
        }
    }

}
