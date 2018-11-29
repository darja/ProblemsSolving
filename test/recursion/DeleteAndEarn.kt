package recursion
import junit.framework.TestCase
import java.util.*

/**
 * @see <a href="https://leetcode.com/problems/delete-and-earn/">Problem Description</a>
 */
@Suppress("MemberVisibilityCanBePrivate")
// todo time limit exceeded
class DeleteAndEarn : TestCase() {
    fun deleteAndEarn(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        nums.forEach { map.put(it, (map[it] ?: 0).plus(1)) }

        return back(map, 0, hashSetOf())
    }

    fun back(map: HashMap<Int, Int>, sum: Int, usedNumbers: HashSet<Int>) : Int {
        if (map.size == usedNumbers.size) {
            return sum
        }

        var max = Integer.MIN_VALUE;
        for (key in map.keys) {
            if (usedNumbers.contains(key)) {
                continue
            }

            usedNumbers += key

            val nextKey = key + 1
            val nextAdded = map.containsKey(nextKey) && !usedNumbers.contains(nextKey)
            if (nextAdded) usedNumbers += nextKey

            val prevKey = key - 1
            val prevAdded = map.containsKey(prevKey) && !usedNumbers.contains(prevKey)
            if (prevAdded) usedNumbers += prevKey

            val value = back(map, sum + key * map[key]!!, usedNumbers)

            if (value > max) {
                max = value
            }

            usedNumbers -= key
            if (nextAdded) usedNumbers -= nextKey
            if (prevAdded) usedNumbers -= prevKey
        }

        return max
    }

    fun testCase(input: IntArray, expected: Int) {
        assertEquals(expected, deleteAndEarn(input))
    }

    fun test() {
        testCase(intArrayOf(1, 1, 1), 3)
        testCase(intArrayOf(1), 1)
        testCase(intArrayOf(), 0)
        testCase(intArrayOf(1, 2, 2), 4)
        testCase(intArrayOf(3, 2), 3)
        testCase(intArrayOf(1, 2, 3), 4)
        testCase(intArrayOf(2, 3, 4), 6)
        testCase(intArrayOf(2, 2, 3, 3, 3, 4), 9)
    }
}