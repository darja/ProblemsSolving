@file:Suppress("MemberVisibilityCanBePrivate")

package dynamic

import junit.framework.TestCase

/**
 * @see <a href="https://leetcode.com/problems/min-cost-climbing-stairs/">Problem Description</a>
 */
class MinCostClimbingStairs: TestCase() {
    fun minCostClimbingStairs(cost: IntArray): Int {
        if (cost.size < 2) {
            return 0
        }

        var beforePrev = 0
        var prev = 0
        var curr = prev

        for (i in 2 .. cost.size) {
            curr = Math.min(prev + cost[i - 1], beforePrev + cost[i - 2])
            beforePrev = prev
            prev = curr
        }

        return curr
    }

    fun testOne(cost: IntArray, expectedResult: Int) = assertEquals(expectedResult, minCostClimbingStairs(cost))

    fun test() {
        testOne(intArrayOf(1), 0)
        testOne(intArrayOf(1, 2), 1)
        testOne(intArrayOf(2, 1), 1)
        testOne(intArrayOf(2, 1, 3), 1)
        testOne(intArrayOf(1, 2, 3), 2)
        testOne(intArrayOf(1, 5, 4), 5)
        testOne(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1), 6)
    }
}