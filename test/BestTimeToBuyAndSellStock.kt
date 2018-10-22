import junit.framework.TestCase

class BestTimeToBuyAndSellStock : TestCase() {
    fun maxProfit(prices: IntArray): Int {
        if (prices.size < 2) {
            return 0
        }

        var min = prices[0]
        var result = 0
        var diff: Int

        for (i in 1 until prices.size) {
            if (prices[i] < min) {
                min = prices[i]
            } else {
                diff = prices[i] - min
                if (diff > result) {
                    result = diff
                }
            }
        }

        return result
    }

    fun testOne(prices: IntArray, expectedResult: Int) = assertEquals(expectedResult, maxProfit(prices))

    fun test() {
        testOne(intArrayOf(1), 0)

        testOne(intArrayOf(1, 2), 1)
        testOne(intArrayOf(2, 1), 0)

        testOne(intArrayOf(2, 4, 6, 1, 2), 4)
        testOne(intArrayOf(7, 1, 5, 3, 6, 4), 5)

        testOne(intArrayOf(1, 2, 3, 4, 5), 4)
        testOne(intArrayOf(5, 4, 3, 2, 1), 0)

    }
}