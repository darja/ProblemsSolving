package util

data class Interval(var start: Int = 0, var end: Int = 0) {
    override fun toString() = "[$start, $end]"
}