import junit.framework.TestCase;

public class ReverseString extends TestCase {
    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        char tmp;
        while (start < end) {
            tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;

            start++;
            end--;
        }
        return new String(chars);
    }

    public void test() {
        testCase("", "");
        testCase("a", "a");
        testCase("ab", "ba");
        testCase("abc", "cba");
        testCase("abbba", "abbba");
    }

    private void testCase(String input, String expectedResult) {
        assertEquals(expectedResult, reverseString(input));
    }
}
