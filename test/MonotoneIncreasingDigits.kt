import junit.framework.TestCase
import java.util.*

class MonotoneIncreasingDigits: TestCase() {
    fun monotoneIncreasingDigits(N: Int): Int {
        val stack = LinkedList<Int>()

        var n = N
        var dec = 0
        while (n > 0) {
            var digit = n % 10

            if (stack.isEmpty()) {
                stack.push(digit)
            } else {
                var ninesToAdd = 0
                if (!stack.isEmpty() && digit > stack.peek()) {
                    while (!stack.isEmpty()) {
                        stack.pop()
                        ninesToAdd++
                    }
                    dec = 1
                }

                for (i in 1..ninesToAdd) {
                    stack.push(9)
                }

                digit -= dec
                if (digit >= 0) {
                    stack.push(digit)
                    dec = 0
                }
            }

            n /= 10
        }

        var result = 0;
        while (!stack.isEmpty()) {
            result = result * 10 + stack.pop()
        }

        return result;
    }

    fun testCase(input: Int, expected: Int) {
        assertEquals(expected, monotoneIncreasingDigits(input))
    }

    fun test() {
        testCase(322, 299)
        testCase(3219, 2999)
        testCase(123, 123)
        testCase(4525, 4499)
        testCase(0, 0)
        testCase(10, 9)
        testCase(100, 99)
        testCase(1009, 999)
        testCase(21, 19)
    }

}