import junit.framework.TestCase;

public class ReverseInteger extends TestCase {
    public int reverse(int x) {
        int sign = x > 0 ? 1 : -1;
        long result = 0;
        int src = Math.abs(x);

        while (src > 0) {
            result = result * 10 + src % 10;
            src /= 10;
        }

        if (result > Integer.MAX_VALUE) {
            result = 0;
        }

        result *= sign;

        return (int)result;
    }

    public void test() {
        testSingle(123, 321);
        testSingle(-123, -321);
        testSingle(100, 1);
        testSingle(-101, -101);
        testSingle(Integer.MAX_VALUE, 0);
        testSingle(Integer.MIN_VALUE, 0);
        testSingle(Integer.MIN_VALUE + 1, 0);
        testSingle(Integer.MAX_VALUE - 1, 0);
    }

    private void testSingle(int input, int expected) {
        assertEquals("Input: " + input, expected, reverse(input));
    }
}
