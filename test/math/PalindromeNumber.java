package math;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/palindrome-number/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class PalindromeNumber extends TestCase {
    public boolean isPalindrome(int x) {
        int size = getNumberLength(x);

        for (int i = 0; i < size / 2; ++i) {
            if (getDigitAt(x, i) != getDigitAt(x, size - i - 1)) {
                return false;
            }
        }

        return true;
    }

    private int getNumberLength(int x) {
         return (int)Math.log10(Math.abs((long)x)) + 1;
    }

    private int getDigitAt(int x, int pos) {
        int num = x;
        while (getNumberLength(num) > pos + 1) {
            num /= 10;
        }
        return Math.abs(num % 10);
    }

    public void test() {
//        assertEquals(1, getDigitAt(1, 0));
//        assertEquals(1, getDigitAt(12, 0));
//        assertEquals(2, getDigitAt(12, 1));
//        assertEquals(5, getDigitAt(12345, 4));
//        assertEquals(1, getDigitAt(12345, 0));

        testTrue(1);
        testTrue(11);
        testTrue(121);
        testTrue(1221);
        testTrue(12321);

        testTrue(-2147447412);
        testTrue(-12321);

        testFalse(Integer.MIN_VALUE);

        testFalse(12);
        testFalse(124);
        testFalse(1242);
    }

    private void testTrue(int input) {
        assertTrue(isPalindrome(input));
    }

    private void testFalse(int input) {
        assertFalse(isPalindrome(input));
    }
}
