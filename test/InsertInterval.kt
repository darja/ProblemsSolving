
import junit.framework.TestCase
import util.Interval
import java.util.*

class InsertInterval: TestCase() {
    fun insert(intervals: List<Interval>, newInterval: Interval): List<Interval> {
        if (intervals.isEmpty()) {
            return listOf(newInterval)
        }

        var n = intervals.size
        val updatedIntervals = ArrayList<Interval>(n + 1)

        var i = 0
        while (i < n && intervals[i].end < newInterval.start) {
            updatedIntervals.add(intervals[i])
            i++
        }

        if (i < n && intervals[i].start <= newInterval.end) {
            var mergedInterval = Interval(
                Math.min(newInterval.start, intervals[i].start),
                Math.max(newInterval.end, intervals[i].end)
            )
            updatedIntervals.add(mergedInterval)
            i++
            while (i < n &&
                intervals[i].start <= mergedInterval.end &&
                intervals[i].end < mergedInterval.end) {
                i++
            }


            if (i < n) {
                if (intervals[i].start <= mergedInterval.end) {
                    mergedInterval.end = intervals[i].end
                    i++
                }
            }
        } else {
            updatedIntervals.add(newInterval)
        }

        while (i < n) {
            updatedIntervals.add(intervals[i])
            i++
        }

        return updatedIntervals
    }

    fun testTrivial() {
        testOne(emptyList(), Interval(1, 2), listOf(Interval(1, 2)))
    }

    fun testEqual() {
        testOne(listOf(Interval(1, 2)), Interval(1, 2), listOf(Interval(1, 2)))
        testOne(
            listOf(Interval(1, 2), Interval(4, 8)),
            Interval(1, 2),
            listOf(Interval(1, 2), Interval(4, 8))
        )
        testOne(
            listOf(Interval(1, 2), Interval(4, 8)),
            Interval(5, 6),
            listOf(Interval(1, 2), Interval(4, 8))
        )
    }

    fun testSmall() {
        testOne(listOf(Interval(3, 5)), Interval(1, 2), listOf(Interval(1, 2), Interval(3, 5)))
        testOne(listOf(Interval(1, 2)), Interval(3, 5), listOf(Interval(1, 2), Interval(3, 5)))
        testOne(listOf(Interval(1, 5)), Interval(2, 3), listOf(Interval(1, 5)))
        testOne(listOf(Interval(2, 3)), Interval(1, 5), listOf(Interval(1, 5)))
        testOne(listOf(Interval(2, 3)), Interval(3, 5), listOf(Interval(2, 5)))
        testOne(listOf(Interval(2, 3)), Interval(1, 2), listOf(Interval(1, 3)))
        testOne(listOf(Interval(1, 3), Interval(6, 9)), Interval(2, 5), listOf(Interval(1, 5), Interval(6, 9)))
    }

    fun testMerge() {
        testOne(listOf(
                Interval(1, 2),
                Interval(5, 7),
                Interval(10, 11)
            ),
            Interval(2, 8),
            listOf(
                Interval(1, 8),
                Interval(10, 11)
            )
        )
        testOne(listOf(
                Interval(1, 2),
                Interval(3, 4),
                Interval(5, 7),
                Interval(10, 11)
            ),
            Interval(2, 8),
            listOf(
                Interval(1, 8),
                Interval(10, 11)
            )
        )

        testOne(listOf(
                Interval(1,2),
                Interval(3,5),
                Interval(6,7),
                Interval(8,10),
                Interval(12,16)),
            Interval(4,8),
            listOf(
                Interval(1,2),
                Interval(3,10),
                Interval(12,16)
            )
        )
    }

    fun testInsert() {
        testOne(listOf(
            Interval(1, 2),
            Interval(10, 11)
        ),
            Interval(3, 5),
            listOf(
                Interval(1, 2),
                Interval(3, 5),
                Interval(10, 11)
            )
        )

        testOne(listOf(
            Interval(1, 2)
        ),
            Interval(3, 5),
            listOf(
                Interval(1, 2),
                Interval(3, 5)
            )
        )

        testOne(listOf(
            Interval(3, 5)
        ),
            Interval(1, 2),
            listOf(
                Interval(1, 2),
                Interval(3, 5)
            )
        )
    }

    fun testOne(intervals: List<Interval>, newInterval: Interval, expected: List<Interval>) {
        val result = insert(intervals, newInterval)

        System.out.printf("Input: %s\n", intervals)
        System.out.printf("Inserted: %s\n", newInterval)
        System.out.printf("Output: %s\n\n", result)

        TestCase.assertEquals(result.size, expected.size)

        for (i in 1..expected.size - 1) {
            assertEquals(expected[i], result[i])
        }
    }

}