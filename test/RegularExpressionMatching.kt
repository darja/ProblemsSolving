import junit.framework.TestCase

class RegularExpressionMatching: TestCase() {
    fun isMatch(input: String, pattern: String): Boolean {
        if (pattern.isEmpty()) return input.isEmpty()

        return isMatchBack(input, pattern, 0, 0, null)
    }

    private fun isMatchBack(input: String, pattern: String, inputIndex: Int, patternIndex: Int, initPossibleChars: PatternNode?): Boolean {
        var pi = patternIndex
        var possibleChars = initPossibleChars

        if (possibleChars == null) {
            val gpc = getPossibleChars(pattern, patternIndex)
            pi = gpc.first
            possibleChars = gpc.second
        }

        if (inputIndex == input.length) return pi == pattern.length && (possibleChars == null || possibleChars.allZeroOrMore())

        if (possibleChars == null) {
            return false
        }

        while (possibleChars != null) {
            if (possibleChars.matches(input[inputIndex])) {
                if (!possibleChars.zeroOrMore) {
                    possibleChars = possibleChars.next
                }

                if (isMatchBack(input, pattern, inputIndex + 1, pi, possibleChars)) {
                    return true
                }
            }

            possibleChars = possibleChars?.next
        }
        return false
    }

    private fun getPossibleChars(pattern: String, fromIndex: Int): Pair<Int, PatternNode?> {
        var i = fromIndex
        var possibleCharsHead: PatternNode? = null
        var possibleCharsTail: PatternNode? = null
        while (i < pattern.length) {
            val symbol = pattern[i]
            val zeroOrMore = i < pattern.length - 1 && pattern[i + 1] == '*'

            if (possibleCharsTail == null) {
                possibleCharsHead = PatternNode(symbol, zeroOrMore)
                possibleCharsTail = possibleCharsHead
            } else {
                possibleCharsTail.next = PatternNode(symbol, zeroOrMore)
                possibleCharsTail = possibleCharsTail.next
            }

            i++
            if (zeroOrMore) {
                i++
            } else {
                break
            }
        }

        return Pair(i, possibleCharsHead)
    }

    class PatternNode(val symbol: Char, val zeroOrMore: Boolean) {
        var next: PatternNode? = null

        fun matches(c: Char) = symbol == c || symbol == '.'

        fun allZeroOrMore(): Boolean = zeroOrMore && (next?.allZeroOrMore() ?: true)
    }

    fun testMatches(input: String, pattern: String) {
        assertTrue(isMatch(input, pattern))
    }

    fun testNotMatches(input: String, pattern: String) {
        assertFalse(isMatch(input, pattern))
    }

    fun testOk() {
        testMatches("", "")
        testMatches("", "a*b*c*")
        testMatches("", ".*")

        testMatches("a", "a*")
        testMatches("a", "a")
        testMatches("a", ".")
        testMatches("a", ".*")
        testMatches("aa", ".*")
        testMatches("aaa", "a*")
        testMatches("aaab", "a*b")
        testMatches("aaab", "a*.*")
        testMatches("aaab", "a*.*b")

        testMatches("bc", "a*b*c")
        testMatches("aaabc", "a*b*c")
        testMatches("aaac", "a*b*c")
        testMatches("bbbc", "a*b*c")
        testMatches("abbb", "a*b*.")
        testMatches("abbb", "a*b*.")
        testMatches("abbb", "a*b*.*")
        testMatches("gfsffff", ".*")
    }

    fun testFail() {
        testNotMatches("gfjk", "")
        testNotMatches("", "a")
        testNotMatches("", "a*b*c")

        testNotMatches("aa", "a")
        testNotMatches("ab", "a*b*c")
        testNotMatches("abbb", "a*b*c")
        testNotMatches("abbb", "a*b*.c")
        testNotMatches("za", "a*b*.c")
        testNotMatches("mississippi", "mis*is*p*.")
        testNotMatches("ssippi", "s*p*.")
        testNotMatches("a", "ab*a")
    }
}