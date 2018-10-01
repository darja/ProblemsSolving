import junit.framework.TestCase

class DivideTwoIntegers: TestCase() {
    fun divide(dividend: Int, divisor: Int): Int {
        if (divisor == Int.MIN_VALUE) {
            return if (dividend == Int.MIN_VALUE) 1 else 0;
        } else if (divisor == 1) {
            return dividend;
        } else if (divisor == -1) {
            return if (dividend == Int.MIN_VALUE) Int.MAX_VALUE else -dividend;
        }

        val sign = if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) 1 else -1

        var minIntOverflow = 0
        var absDividend = if (dividend > 0) dividend else -dividend
        val absDivisor = if (divisor > 0) divisor else -divisor

        if (dividend == Int.MIN_VALUE) {
            minIntOverflow = 1
            absDividend = Int.MAX_VALUE;
        }

        if (absDivisor == 1) {
            return absDividend * sign;
        }

        var result = 0
        if (minIntOverflow > 0) {
            result++
            absDividend = absDividend - absDivisor + 1
        }

        while (absDividend >= absDivisor) {
            absDividend -= absDivisor
            result++
        }

        return result * sign
    }

    public fun testNormal() {
        testSingle(4, 2, 2)
        testSingle(4, -2, -2)
        testSingle(-4, 2, -2)
        testSingle(-4, -2, 2)

        testSingle(1, 2, 0)
        testSingle(-1, 2, 0)
        testSingle(1, -2, 0)
        testSingle(-1, -2, 0)

        testSingle(3, 2, 1)
        testSingle(-3, 2, -1)
        testSingle(3, -2, -1)
        testSingle(-3, -2, 1)
    }

    fun testEdge() {
        testSingle(Integer.MIN_VALUE, -1, Integer.MAX_VALUE)
        testSingle(Integer.MIN_VALUE, 1, Integer.MIN_VALUE)
        testSingle(Integer.MIN_VALUE, 2, Integer.MIN_VALUE / 2)
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE / 2 + 1, 2)
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE / 2 - 1, 1)
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE + 2, 1)
        testSingle(Integer.MIN_VALUE, Integer.MIN_VALUE, 1)
        testSingle(Integer.MIN_VALUE, Integer.MAX_VALUE, -1)
        testSingle(Integer.MAX_VALUE, Integer.MIN_VALUE, 0)
        testSingle(Integer.MAX_VALUE, Integer.MAX_VALUE / 3, 3)
        testSingle(Integer.MAX_VALUE, Integer.MAX_VALUE / 3 + 4, 2)
    }

    private fun testSingle(dividend: Int, divisor: Int, expected: Int) {
        TestCase.assertEquals(expected, divide(dividend, divisor))
    }

}