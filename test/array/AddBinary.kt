package array

import junit.framework.TestCase

/**
 * @see <a href="https://leetcode.com/problems/add-binary/">Problem Description</a>
 */
@Suppress("MemberVisibilityCanBePrivate")
class AddBinary: TestCase() {
    fun addBinary(a: String, b: String): String {
        var long = a
        var short = b

        if (long.length < short.length) {
            long = b
            short = a
        }
        var iLong = long.length - 1
        var iShort = short.length - 1

        val result = IntArray(iLong + 2)
        var i = iLong + 1
        var overflow = 0

        while (iShort >= 0) {
            result[i] = getDigit(long[iLong]) + getDigit(short[iShort]) + overflow
            overflow = result[i] / 2
            result[i] %= 2

            iShort--
            iLong--
            i--
        }

        while (iLong >= 0) {
            result[i] = getDigit(long[iLong]) + overflow
            overflow = result[i] / 2
            result[i] %= 2

            iLong--
            i--
        }

        if (overflow > 0) {
            result[0] = 1
        }

        val sb = StringBuilder()
        if (result[0] > 0) {
            sb.append('1')
        }
        for (ir in 1..(result.size - 1)) {
            sb.append(result[ir])
        }
        return if (sb.isNotEmpty()) sb.toString() else "0"
    }

    private fun getDigit(c: Char) = c - '0'

    fun testOne(a: String, b: String, expected: String) = assertEquals(expected, addBinary(a, b))

    fun test() {
        testOne("1", "1", "10")
        testOne("0", "0", "0")
        testOne("1", "0", "1")
        testOne("10", "0", "10")
        testOne("11", "1", "100")
        testOne("11", "11", "110")
        testOne("101", "1", "110")
        testOne("111", "1", "1000")
        testOne("1001", "1", "1010")
        testOne("", "", "0")
        testOne("0", "", "0")
        testOne("", "1", "1")
    }
}