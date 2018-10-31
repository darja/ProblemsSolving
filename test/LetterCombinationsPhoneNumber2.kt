
import util.ListTestCase
import java.util.*

class LetterCombinationsPhoneNumber2: ListTestCase() {
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) {
            return emptyList()
        }

        val codes = arrayOf("0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

        val result = LinkedList<String>()
        result.add("")

        return findCombinations(result, digits, 0, codes)
    }

    private fun findCombinations(result: List<String>,
                                 digits: String, digitIndex: Int, codes: Array<String>): List<String> {
        if (digitIndex == digits.length) return result

        val code = codes[digits[digitIndex] - '0']
        val next = result.flatMap { str -> code.map { str + it } }.toList()

        return findCombinations(next, digits, digitIndex + 1, codes)
    }

    fun test() {
        testSingle("23", arrayOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))
        testSingle("12", arrayOf("1a", "1b", "1c"))
        testSingle("11", arrayOf("11"))
        testSingle("01", arrayOf("01"))
        testSingle("", emptyArray())
    }

    private fun testSingle(input: String, expected: Array<String>) {
        assertListsEqual(expected, letterCombinations(input))
    }
}