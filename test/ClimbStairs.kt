import junit.framework.TestCase

class ClimbStairs: TestCase() {
    private fun climbStairs(n: Int): Int {
        if (n <= 0) {
            return 0
        }

        var steps = hashMapOf(Pair(1, 1))
        for (i in 2..n) {
            val ones = steps[1] ?: 0
            val twos = steps[2] ?: 0
            steps.put(1, twos + ones)
            steps.put(2, ones)
        }

        return (steps[1] ?: 0) + (steps[2] ?: 0)
    }

    /**
     * 1 1 1
     * 1 2
     * 2 1
     *
     * 1 1 1 1
     * 1 1 2
     * 1 2 1
     * 2 1 1
     * 2 2
     *
     * 1 1 1 1 1
     * 1 1 1 2
     * 1 1 2 1
     * 1 2 2
     * 1 2 1 1
     * 2 1 1 1
     * 2 2 1
     * 2 1 2
     */

    fun test() {
        assertEquals(1, climbStairs(1))
        assertEquals(2, climbStairs(2))
        assertEquals(3, climbStairs(3))
        assertEquals(5, climbStairs(4))
        assertEquals(8, climbStairs(5))
    }
}

