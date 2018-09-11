
import util.ArrayTestCase

class PlusOne: ArrayTestCase() {
    fun plusOne(digits: IntArray): IntArray {
        val size = digits.size

        var i = size - 1
        var overflow = true
        while (i >= 0 && overflow) {
            if (digits[i] == 9) {
                digits[i] = 0
                overflow = true
            } else {
                digits[i]++
                overflow = false
            }
            i--
        }

        if (overflow) {
            val newDigits = IntArray(size + 1)
            newDigits[0] = 1
            for (j in 0..(size-1)) {
                newDigits[j + 1] = digits[j]
            }
            return newDigits
        } else {
            return digits
        }
    }

    fun test() {
        testOne(intArrayOf(1, 1), intArrayOf(1, 0))
        testOne(intArrayOf(1), intArrayOf(0))
        testOne(intArrayOf(2), intArrayOf(1))
        testOne(intArrayOf(1, 0), intArrayOf(9))
        testOne(intArrayOf(2, 0), intArrayOf(1, 9))
        testOne(intArrayOf(1, 0, 0), intArrayOf(9, 9))
    }

    private fun testOne(expected: IntArray, input: IntArray) {
        assertArraysEqual(expected, plusOne(input))
    }
}