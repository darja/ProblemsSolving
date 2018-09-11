import junit.framework.TestCase

class NetworkDelayTime : TestCase() {
    fun networkDelayTime(times: Array<IntArray>, N: Int, K: Int): Int {
        var routes = Array(N) { arrayOfNulls<Int>(N) }

        for (time in times) {
            routes[time[0]-1][time[1]-1] = time[2];
        }

        for (i in 0..N-1) {
            routes[i][i] = 0
        }


        var isFinal = true
        do {
            isFinal = true
            for (i in 0..N-1) {
                for (j in 0..N-1) {
                    if (i == j) {
                        continue
                    }

                    for (k in 0..N-1) {
                        if (i == k || j == k) {
                            continue
                        }

                        if (routes[i][k] != null && routes[k][j] != null) {
                            val sum = routes[i][k]!! + routes[k][j]!!
                            if (routes[i][j] == null || routes[i][j]!! > sum) {
                                routes[i][j] = sum
                                isFinal = false
                            }
                        }
                    }
                }
            }
        } while (!isFinal)

        var result = 0;
        for (i in 0..N-1) {
            val time = routes[K-1][i] ?: return -1
            if (time > result) {
                result = time
            }
        }

        return result;
    }

    public fun testOk() {
        val times = arrayOf(
                intArrayOf(1, 2, 1),
                intArrayOf(1, 3, 5),
                intArrayOf(1, 4, 2),

                intArrayOf(2, 1, 2),
                intArrayOf(2, 3, 2),
                intArrayOf(2, 4, 5),

                intArrayOf(3, 1, 6),
                intArrayOf(3, 2, 8),
                intArrayOf(3, 4, 1),

                intArrayOf(4, 1, 1),
                intArrayOf(4, 2, 1),
                intArrayOf(4, 3, 1)
        )
        assertEquals(3, networkDelayTime(times, 4, 1))
        assertEquals(3, networkDelayTime(times, 4, 2))
        assertEquals(2, networkDelayTime(times, 4, 3))
        assertEquals(1, networkDelayTime(times, 4, 4))

    }

    public fun testNoRoute() {
        val times = arrayOf(
                intArrayOf(1, 2, 1),
                intArrayOf(1, 3, 5),
                intArrayOf(1, 4, 2),

                intArrayOf(2, 1, 2),
                intArrayOf(2, 3, 2),
                intArrayOf(2, 4, 5),

                intArrayOf(3, 1, 6),
                intArrayOf(3, 2, 8),
                intArrayOf(3, 4, 1)
        )
//        assertEquals(6, networkDelayTime(times, 4, 1))
//        assertEquals(7, networkDelayTime(times, 4, 2))
//        assertEquals(5, networkDelayTime(times, 4, 3))
        assertEquals(-1, networkDelayTime(times, 4, 4))
    }

    public fun testSample() {
        var times = arrayOf(
                intArrayOf(2,1,1),
                intArrayOf(2,3,1),
                intArrayOf(3,4,1)
        )
        assertEquals(2, networkDelayTime(times, 4, 2))
        assertEquals(-1, networkDelayTime(times, 4, 1))
    }
}
