package recursion

import junit.framework.TestCase
import java.util.*

class HanoiTowers : TestCase() {
    private fun hanoi(n: Int): List<String> {
        val steps = LinkedList<String>()
        return move('A', 'B', 'C', steps, n);
    }

    private fun move(a: Char, b: Char, c: Char, steps: MutableList<String>, n: Int): List<String> {
        if (n == 1) {
            steps.add("$a -> $b")
        } else {
            move(a, c, b, steps, n - 1);
            steps.add("$a -> $b");
            move(c, b, a, steps, n - 1);
        }

        return steps;
    }

    private fun test(n: Int) {
        System.out.println("*** START $n ***")
        val steps = hanoi(n)
        steps.forEach { System.out.println(it) }
        System.out.println("*** END $n ***\n")

    }

    public fun test() {
        test(1)
        test(2)
        test(3)
        test(4)
    }
}
