
import junit.framework.TestCase
import util.Interval
import java.util.*

class MergeIntervals: TestCase() {
    fun merge(intervals: List<Interval>): List<Interval> {
        if (intervals.size <= 1) {
            return intervals
        }

        var sorted = intervals.sortedBy { it.start }

        var result = LinkedList<Interval>()
        var last = sorted[0]
        result.add(last)

        for (i in 1..sorted.size-1) {
            if (last.end >= sorted[i].start) {
                last.end = Math.max(sorted[i].end, last.end)
            } else {
                last = sorted[i]
                result.add(last)
            }
        }

        System.out.printf("Input: %s\n", intervals)
        System.out.printf("Output: %s\n", result)

        return result
    }

    fun testTrivial() {
        testOne(emptyList(), emptyList())
        testOne(listOf(Interval(1, 2)), listOf(Interval(1, 2)))
    }

    fun testNoOverlaps() {
        testOne(
            listOf(Interval(1, 2), Interval(10, 20)),
            listOf(Interval(1, 2), Interval(10, 20)))

        testOne(
            listOf(Interval(1, 2), Interval(10, 20), Interval(5, 8)),
            listOf(Interval(1, 2), Interval(5, 8), Interval(10, 20)))
    }

    fun testOverlaps() {
        testOne(
            listOf(Interval(1, 2), Interval(2, 10), Interval(10, 20)),
            listOf(Interval(1, 20)))

        testOne(
            listOf(Interval(1, 2), Interval(2, 6), Interval(10, 20)),
            listOf(Interval(1, 6), Interval(10, 20)))

        testOne(
            listOf(Interval(1, 2), Interval(2, 6), Interval(12, 90), Interval(10, 20)),
            listOf(Interval(1, 6), Interval(10, 90)))

        testOne(
            listOf(Interval(1, 2), Interval(2, 6), Interval(8, 90), Interval(10, 20)),
            listOf(Interval(1, 6), Interval(8, 90)))

    }

    fun testOne(intervals: List<Interval>, expected: List<Interval>) {
        val result = merge(intervals)

        assertEquals(result.size, expected.size)

        for (i in 1..expected.size - 1) {
            val expectedItem = expected[i]
            var found = false
            for (j in 1..intervals.size - 1) {
                if (result[j] == expectedItem) {
                    found = true
                    break
                }
            }
            assertTrue("Item: " + expectedItem.toString(), found)
        }
    }
}