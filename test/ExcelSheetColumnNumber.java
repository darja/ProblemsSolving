import junit.framework.TestCase;

public class ExcelSheetColumnNumber extends TestCase {
    public void test() {
        assertEquals(1, titleToNumber("A"));
        assertEquals(2, titleToNumber("B"));
        assertEquals(26, titleToNumber("Z"));
        assertEquals(27, titleToNumber("AA"));
        assertEquals(28, titleToNumber("AB"));
    }

    public int titleToNumber(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            result = result * 26 + (c - 'A' + 1);
        }

        return result;
    }
}
