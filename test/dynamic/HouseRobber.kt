package dynamic

import junit.framework.TestCase

/**
 * @see <a href="https://leetcode.com/problems/house-robber/">Problem Description</a>
 */
@Suppress("MemberVisibilityCanBePrivate")
class HouseRobber : TestCase() {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        if (nums.size == 1) {
            return nums[0]
        }

        var prev = Math.max(nums[1], nums[0])
        var beforePrev = nums[0]
        var curr: Int = prev

        for (i in 2 until nums.size) {
            curr = Math.max(prev, beforePrev + nums[i])
            beforePrev = prev
            prev = curr
        }

        return curr
    }

    private fun testOne(nums: IntArray, expectedResult: Int) = assertEquals(expectedResult, rob(nums))

    fun test() {
        testOne(intArrayOf(), 0)
        testOne(intArrayOf(10), 10)
        testOne(intArrayOf(10, 6), 10)
        testOne(intArrayOf(10, 6, 5), 15)
        testOne(intArrayOf(10, 6, 5, 6), 16)
        testOne(intArrayOf(10, 6, 5, 6, 4), 19)

        testOne(intArrayOf(1, 2, 3, 1), 4)
        testOne(intArrayOf(2, 7, 9, 3, 1), 12)
    }
}