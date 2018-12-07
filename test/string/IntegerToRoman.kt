package string

import junit.framework.TestCase

/**
 * @see <a href="https://leetcode.com/problems/integer-to-roman/">Problem Description</a>
 */
@Suppress("MemberVisibilityCanBePrivate")
class IntegerToRoman: TestCase() {
    fun intToRoman(num: Int): String {
        val scheme = arrayOf(
            emptyArray(),       // 0
            arrayOf(0),         // 1
            arrayOf(0, 0),      // 2
            arrayOf(0, 0, 0),   // 3
            arrayOf(0, 1),      // 4
            arrayOf(1),         // 5
            arrayOf(1, 0),      // 6
            arrayOf(1, 0, 0),   // 7
            arrayOf(1, 0, 0, 0),// 8
            arrayOf(0, 2)       // 9
        )
        when (num) {
            1 -> "I"
        }


        val sb = StringBuilder()
        digitToRoman(sb, scheme[num / 1000], arrayOf('M'))
        digitToRoman(sb, scheme[num % 1000 / 100], arrayOf('C', 'D', 'M'))
        digitToRoman(sb, scheme[num % 100 / 10], arrayOf('X', 'L', 'C'))
        digitToRoman(sb, scheme[num % 10], arrayOf('I', 'V', 'X'))

        return sb.toString()
    }

    fun digitToRoman(sb: StringBuilder, scheme: Array<Int>, symbols: Array<Char>) {
        for (i in 0 until scheme.size) {
            sb.append(symbols[scheme[i]])
        }
    }

    private fun testOne(input: Int, expected: String) {
        assertEquals(expected, intToRoman(input))
    }

    fun test() {
        testOne(1, "I")
        testOne(2, "II")
        testOne(3, "III")
        testOne(4, "IV")
        testOne(5, "V")
        testOne(6, "VI")
        testOne(7, "VII")
        testOne(8, "VIII")
        testOne(9, "IX")
        testOne(10, "X")
        testOne(11, "XI")
        testOne(12, "XII")
        testOne(15, "XV")
        testOne(20, "XX")
        testOne(28, "XXVIII")
        testOne(43, "XLIII")
        testOne(54, "LIV")
        testOne(1994, "MCMXCIV")
    }
}