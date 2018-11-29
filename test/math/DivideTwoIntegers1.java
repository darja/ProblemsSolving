package math;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/divide-two-integers/">Problem Description</a>
 */
public class DivideTwoIntegers1 extends TestCase {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

        if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE) {
                return 1;
            } else {
                return 0;
            }
        }

        int sign = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0 ? 1 : -1;

        long absDivisor = Math.abs((long)divisor);
        long absDivident = Math.abs((long)dividend);
        long quotient = 0;

        while (absDivident >= absDivisor) {
            absDivident -= absDivisor;
            quotient++;
        }

        return (int)quotient * sign;
    }

    public void test() {
        testSingle(4, 2, 2);
        testSingle(1, 2, 0);
        testSingle(3, 2, 1);

        testSingle(3, 0, Integer.MAX_VALUE);
        testSingle(Integer.MIN_VALUE, 2, Integer.MIN_VALUE / 2);
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE / 2 + 1, 2);
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE / 2 - 1, 1);
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE + 2, 1);
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE, 1);
    }

    private void testSingle(int divident, int divisor, int expected) {
        assertEquals(expected, divide(divident, divisor));
    }
}
