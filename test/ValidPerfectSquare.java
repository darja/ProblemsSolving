import junit.framework.TestCase;

public class ValidPerfectSquare extends TestCase {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }

        if (num <= 1) {
            return true;
        }

        for (int i = 0; i <= num / 2; ++i) {
            if (i * i == num) {
                return true;
            }
        }
        return false;
    }

    public void test() {
        assertTrue(isPerfectSquare(0));
        assertTrue(isPerfectSquare(1));
        assertTrue(isPerfectSquare(4));
        assertTrue(isPerfectSquare(9));
        assertTrue(isPerfectSquare(16));
        assertTrue(isPerfectSquare(25));

        assertFalse(isPerfectSquare(-4));
        assertFalse(isPerfectSquare(-10));
        assertFalse(isPerfectSquare(2));
    }
}
