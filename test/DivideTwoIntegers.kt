import junit.framework.TestCase

class DivideTwoIntegers: TestCase() {
    fun divide(dividend: Int, divisor: Int): Int {
        val sign = if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) 1 else -1

        var minIntOverflow = 0
        if (dividend == Int.MIN_VALUE) {
            if (divisor == Int.MIN_VALUE) {
                return 1
            } else {
                minIntOverflow = 1
            }
        }

        if (divisor == Int.MIN_VALUE) {
            return 0
        }

        var absDividend = if (dividend > 0) dividend else -dividend
        val absDivisor = if (divisor > 0) divisor else -divisor

        var result = 0
        if (minIntOverflow > 0) {
            result++
            absDividend = absDividend - absDivisor + 1
        }

        while (absDividend > absDivisor) {
            absDividend -= absDivisor
            result++
        }

        return result * sign
    }

    fun test() {
//        testSingle(4, 2, 2)
        testSingle(1, 2, 0)
        testSingle(3, 2, 1)

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