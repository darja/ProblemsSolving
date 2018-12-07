package recursion;

import util.ListTestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/letter-case-permutation/">Problem Description</a>
 */
@SuppressWarnings("ALL")
// todo can be improved
public class LetterCasePermutation extends ListTestCase {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new LinkedList<>();
        result.add(S.toLowerCase());
        permutationIter(result, 0, S.length());

        return result;
    }

    private void permutationIter(List<String> result, int index, int maxIndex) {
        String src = result.get(0);
        while (index < maxIndex && !Character.isAlphabetic(src.charAt(index))) {
            index++;
        }

        if (index == maxIndex) {
            return;
        }

        int count = result.size();
        for (int i = 0; i < count; ++i) {
            char[] item = result.get(i).toCharArray();
            item[index] = Character.toUpperCase(item[index]);
            result.add(new String(item));
        }

        permutationIter(result, index + 1, maxIndex);
    }

    public List<String> letterCasePermutation_Code(String S) {
        long maxCode = 1;
        char[] src = S.toCharArray();

        for (char c : src) {
            if (Character.isAlphabetic(c)) {
                maxCode *= 2;
            }
        }

        List<String> result = new ArrayList<>();

        for (long i = 0; i < maxCode; ++i) {
            char[] item = Arrays.copyOf(src, src.length);

            long code = i;
            int j = 0;
            while (true) {
                while (j < src.length && !Character.isAlphabetic(item[j])) j++;
                if (j == src.length) {
                    break;
                }

                if (code % 2 == 1) {
                    item[j] = Character.toUpperCase(item[j]);
                } else {
                    item[j] = Character.toLowerCase(item[j]);
                }
                j++;
                code /= 2;
            }
            result.add(new String(item));
        }

        return result;
    }

    public void test() {
//        testOne("", new String[] {""});
        testOne("1", new String[] {"1"});
        testOne("a", new String[] {"a", "A"});
        testOne("A", new String[] {"a", "A"});
        testOne("a1", new String[] {"a1", "A1"});
        testOne("2a1", new String[] {"2a1", "2A1"});
        testOne("a1b", new String[] {"a1b", "A1b", "a1B", "A1B"});
        testOne("a1b2", new String[] {"a1b2", "A1b2", "a1B2", "A1B2"});
        testOne("a1B2", new String[] {"a1b2", "A1b2", "a1B2", "A1B2"});
    }

    private void testOne(String input, String[] expected) {
        List<String> result = letterCasePermutation(input);
        System.out.printf("Input: %s\nOutput: %s\n\n", input, result);
        assertListsEqual(expected, result);
    }
}
