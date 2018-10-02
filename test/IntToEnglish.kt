import junit.framework.TestCase

class IntToEnglish : TestCase() {
    fun numberToWords(num: Int): String {
        if (num == 0) {
            return "Zero"
        }

        val sb = StringBuilder()

        val groups = arrayOf("", " Thousand", " Million", " Billion")

        var rest = num
        var groupIndex = 0
        while (rest > 0) {
            val part = rest % 1000
            if (part > 0) {
                if (sb.isNotEmpty()) {
                    sb.insert(0, " ")
                }

                sb.insert(0, groups[groupIndex])
                sb.insert(0, threeDigitNumberToWords(part))
            }

            rest /= 1000
            groupIndex++
        }

        return sb.toString()
    }

    fun threeDigitNumberToWords(num: Int): StringBuilder {
        val digits = arrayOf(
            num / 100,
            num % 100 / 10,
            num % 10
        )

        val sb = StringBuilder()

        // hundreds
        if (digits[0] > 0) {
            sb.append(digitToWord(digits[0]))
            sb.append(" Hundred")
        }

        // tens
        if (digits[1] == 1) {
            appendWord(sb, nteenToWord(digits[2]))
        } else if (digits[1] > 0) {
            appendWord(sb, ntyToWord(digits[1]))
        }

        // ones
        if (digits[1] != 1) {
            appendWord(sb, digitToWord(digits[2]))
        }

        return sb
    }

    fun appendWord(sb: StringBuilder, s: String) {
        if (s.isEmpty()) {
            return
        }

        if (sb.isNotEmpty()) {
            sb.append(" ")
        }
        sb.append(s)
    }

    fun digitToWord(d: Int): String {
        return when (d) {
            1 -> "One"
            2 -> "Two"
            3 -> "Three"
            4 -> "Four"
            5 -> "Five"
            6 -> "Six"
            7 -> "Seven"
            8 -> "Eight"
            9 -> "Nine"
            else -> ""
        }
    }

    fun nteenToWord(n: Int): String {
        return when (n) {
            0 -> "Ten"
            1 -> "Eleven"
            2 -> "Twelve"
            3 -> "Thirteen"
            5 -> "Fifteen"
            8 -> "Eighteen"
            else -> digitToWord(n) + "teen"
        }
    }

    fun ntyToWord(n: Int): String {
        return when (n) {
            2 -> "Twenty"
            3 -> "Thirty"
            4 -> "Forty"
            5 -> "Fifty"
            8 -> "Eighty"
            else -> digitToWord(n) + "ty"
        }
    }

    fun testLess10() {
        testCase(0, "Zero")
        testCase(1, "One")
        testCase(2, "Two")
        testCase(3, "Three")
        testCase(4, "Four")
        testCase(5, "Five")
        testCase(6, "Six")
        testCase(7, "Seven")
        testCase(8, "Eight")
        testCase(9, "Nine")
    }

    fun test10_100() {
        testCase(11, "Eleven")
        testCase(12, "Twelve")
        testCase(13, "Thirteen")
        testCase(14, "Fourteen")
        testCase(18, "Eighteen")
        testCase(28, "Twenty Eight")
        testCase(55, "Fifty Five")
        testCase(81, "Eighty One")
        testCase(90, "Ninety")
    }

    fun test100_1000() {
        testCase(123, "One Hundred Twenty Three")
        testCase(111, "One Hundred Eleven")
        testCase(419, "Four Hundred Nineteen")
        testCase(910, "Nine Hundred Ten")
    }

    fun testBigNumbers() {
        testCase(12321, "Twelve Thousand Three Hundred Twenty One")
        testCase(5912615, "Five Million Nine Hundred Twelve Thousand Six Hundred Fifteen")
        testCase(12345, "Twelve Thousand Three Hundred Forty Five")
        testCase(1234567, "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven")
        testCase(1234567891, "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One")
    }

    fun testCase(input: Int, expected: String) {
        assertEquals(expected, numberToWords(input))
    }
}