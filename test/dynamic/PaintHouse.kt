package dynamic

import junit.framework.TestCase

/**
 * @see <a href="https://leetcode.com/problems/paint-house/">Problem Description</a>
 */
@Suppress("MemberVisibilityCanBePrivate")
class PaintHouse : TestCase() {
    fun minCost(costs: Array<IntArray>): Int {
        if (costs.isEmpty()) {
            return 0
        }

        for (i in 1 until costs.size) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2])
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2])
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1])
        }

        return costs.last().min()!!
    }

    fun testOne(costs: Array<IntArray>, expectedResult: Int) = assertEquals(expectedResult, minCost(costs))

    fun test() {
        testOne(arrayOf(
            intArrayOf(17, 2, 17),
            intArrayOf(16, 16, 5),
            intArrayOf(14, 3, 19)
        ), 10)

        testOne(arrayOf(
            intArrayOf(17, 2, 1),
            intArrayOf(16, 16, 5),
            intArrayOf(14, 3, 19)
        ), 10)

        testOne(arrayOf(
            intArrayOf(17, 2, 17),
            intArrayOf(16, 16, 5)
        ), 7)

        testOne(arrayOf(intArrayOf(17, 2, 17)), 2)
    }
}