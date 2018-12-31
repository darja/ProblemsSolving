package number;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/maximum-swap/">Problem Description</a>
 */
// todo can be improved
@SuppressWarnings("WeakerAccess")
public class MaximumSwap extends TestCase{
    public int maximumSwap(int num) {
        int len = 0;
        int orig = num;
        while (num > 0) {
            len++;
            num /= 10;
        }

        if (len <= 1) {
            return orig;
        }

        int[] digits = new int[len];
        num = orig;
        int i = len - 1;
        while (num > 0) {
            int digit = num % 10;
            digits[i] = digit;
            i--;
            num /= 10;
        }

        int first = 0;
        int maxDigit = -1;
        int maxDigitPos = -1;

        while (first < len - 1) {
            if (maxDigitPos <= first) {
                maxDigitPos = len - 1;
                maxDigit = digits[maxDigitPos];

                for (i = len - 2; i >= first + 1; --i) {
                    if (digits[i] > maxDigit) {
                        maxDigit = digits[i];
                        maxDigitPos = i;
                    }
                }
            }
            if (digits[first] < maxDigit) {
                int tmp = digits[first];
                digits[first] = maxDigit;
                digits[maxDigitPos] = tmp;
                break;
            } else {
                first++;
            }
        }

        int newNum = 0;
        for (i = 0; i < len; ++i) {
            newNum = newNum * 10 + digits[i];
        }
        return newNum;
    }

    public void test() {
        test(1234, 4231);
        test(432, 432);
        test(2, 2);
        test(0, 0);
        test(1099, 9091);
        test(2019, 9012);
        test(1021, 2011);
        test(98707, 98770);
        test(3202, 3220);
    }

    private void test(int input, int expected) {
        assertEquals(expected, maximumSwap(input));
    }
}
