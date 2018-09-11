import junit.framework.TestCase
import java.util.*

class SimplifyPath: TestCase() {
    fun simplifyPath(path: String): String {
        var items = path.split("/")
        val stack = LinkedList<String>()
        for (item in items) {
            when (item) {
                "." -> {}
                "" -> {}
                ".." -> {
                    if (stack.size > 0) {
                        stack.pop()
                    }
                }
                else -> stack.push(item)
            }
        }

        val sb = StringBuilder()
        while (true) {
            sb.append("/")
            if (!stack.isEmpty()) {
                sb.append(stack.pollLast())
            }

            if (stack.isEmpty()) {
                break
            }
        }

        return sb.toString()
    }

    fun test() {
        testOne("/../", "/")
        testOne("/darja/", "/darja")
        testOne("/a/b/", "/a/b")
        testOne("/a/b/./c", "/a/b/c")
        testOne("/a/b/./././c", "/a/b/c")
        testOne("/a/b/../.././c", "/c")
        testOne("/a/b//.././c", "/a/c")
        testOne("/a/./b/../../c/", "/c")
    }

    fun testOne(path: String, expectedResult: String) {
        assertEquals(expectedResult, simplifyPath(path))
    }
}