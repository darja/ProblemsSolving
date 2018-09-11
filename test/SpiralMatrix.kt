import util.ListTestCase
import java.util.*

class SpiralMatrix: ListTestCase() {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var i = 0
        var j = 0

        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return emptyList()
        }

        val cols = matrix[0].size
        val rows = matrix.size

        var left = 0
        var right = cols - 1
        var top = 0
        var bottom = rows - 1

        var colStep = 1
        var rowStep = 0

        val result = ArrayList<Int>()

        while (left <= right && top <= bottom) {
            result.add(matrix[i][j])

            i += rowStep
            j += colStep

            if (colStep != 0) {
                if (j > right) {
                    i = Math.min(i+1, bottom)
                    j = right

                    top++

                    colStep = 0
                    rowStep = 1
                } else if (j < left) {
                    i = Math.max(i-1, top)
                    j = left

                    bottom--

                    colStep = 0
                    rowStep = -1
                }

            } else if (rowStep != 0) {
                if (i < top) {
                    i = top
                    j = Math.min(j+1, right)

                    left++

                    colStep = 1
                    rowStep = 0
                } else if (i > bottom) {
                    i = bottom
                    j = Math.max(j-1, left)

                    right--

                    colStep = -1
                    rowStep = 0
                }
            }
        }

        return result
    }

    fun testOne(matrix: Array<IntArray>, expected: IntArray) {
        val result = spiralOrder(matrix)
        System.out.printf("Result: %s\n", result)

        assertListsEqual(expected, spiralOrder(matrix))
    }

    fun testEmpty() {
        testOne(emptyArray(), intArrayOf())
        testOne(arrayOf(intArrayOf()), intArrayOf())
    }

    fun testSingleElement() {
        testOne(arrayOf(intArrayOf(1)), intArrayOf(1))
    }

    fun testRow() {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3)
        )

        testOne(matrix, intArrayOf(1, 2, 3))
    }

    fun testCol() {
        val matrix = arrayOf(
            intArrayOf(1),
            intArrayOf(2),
            intArrayOf(3)
        )

        testOne(matrix, intArrayOf(1, 2, 3))
    }

    fun testSquare3() {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(8, 9, 4),
            intArrayOf(7, 6, 5)
        )

        testOne(matrix, (1..9).toList().toIntArray())
    }

    fun testSquare4() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3,  4),
            intArrayOf(12, 13, 14, 5),
            intArrayOf(11, 16, 15, 6),
            intArrayOf(10, 9,  8,  7)
        )

        testOne(matrix, (1..16).toList().toIntArray())
    }

    fun testSquare5() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3,  4,  5),
            intArrayOf(16, 17, 18, 19, 6),
            intArrayOf(15, 24, 25, 20, 7),
            intArrayOf(14, 23, 22, 21, 8),
            intArrayOf(13, 12, 11, 10, 9)
        )

        testOne(matrix, (1..25).toList().toIntArray())
    }

    fun testSquare6() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3,  4,  5, 6),
            intArrayOf(20, 21, 22, 23, 24, 7),
            intArrayOf(19, 32, 33, 34, 25, 8),
            intArrayOf(18, 31, 36, 35, 26, 9),
            intArrayOf(17, 30, 29, 28, 27, 10),
            intArrayOf(16, 15, 14, 13, 12, 11)
        )

        testOne(matrix, (1..36).toList().toIntArray())
    }

    fun testRect2_4() {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(8, 7, 6, 5)
        )

        testOne(matrix, (1..8).toList().toIntArray())
    }

    fun testRect3_4() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3,  4),
            intArrayOf(10, 11, 12, 5),
            intArrayOf(9,  8,  7,  6)
        )

        testOne(matrix, (1..12).toList().toIntArray())
    }

    fun testRect3_5() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3,  4, 5),
            intArrayOf(12, 13, 14, 15, 6),
            intArrayOf(11, 10,  9,  8, 7)
        )

        testOne(matrix, (1..15).toList().toIntArray())
    }

    fun testRect4_5() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3,  4, 5),
            intArrayOf(14, 15, 16, 17, 6),
            intArrayOf(13, 20, 19, 18, 7),
            intArrayOf(12, 11, 10,  9, 8)
        )

        testOne(matrix, (1..20).toList().toIntArray())
    }

    fun testRect5_2() {
        val matrix = arrayOf(
            intArrayOf(1,  2),
            intArrayOf(10, 3),
            intArrayOf(9,  4),
            intArrayOf(8,  5),
            intArrayOf(7,  6)
        )

        testOne(matrix, (1..10).toList().toIntArray())
    }

    fun testRect5_3() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3),
            intArrayOf(12, 13, 4),
            intArrayOf(11, 14, 5),
            intArrayOf(10, 15, 6),
            intArrayOf(9,  8,  7)
        )

        testOne(matrix, (1..15).toList().toIntArray())
    }

    fun testRect5_4() {
        val matrix = arrayOf(
            intArrayOf(1,  2,  3,  4),
            intArrayOf(14, 15, 16, 5),
            intArrayOf(13, 20, 17, 6),
            intArrayOf(12, 19, 18, 7),
            intArrayOf(11, 10, 9,  8)
        )

        testOne(matrix, (1..20).toList().toIntArray())
    }
}