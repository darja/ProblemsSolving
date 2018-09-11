import junit.framework.TestCase;

public class Toptal3 extends TestCase {
    public void test() {
//        assertTrue(isPrime(101));
//        assertFalse(isCircularPrime(4));
        assertEquals(0, findCircularPrimeCount(1));
        assertEquals(4, findCircularPrimeCount(10));
        assertEquals(13, findCircularPrimeCount(100));
        assertEquals(25, findCircularPrimeCount(1000));
        assertEquals(33, findCircularPrimeCount(10000));
        assertEquals(43, findCircularPrimeCount(100000));
        assertEquals(55, findCircularPrimeCount(1000000));


//        assertEquals(13, findCircularPrimeCount(101));
//        assertEquals(9, findCircularPrimeCount(50));
    }

    private int findCircularPrimeCount(int n) {
        int count = 0;
        for (int i = 2; i <= n; ++i) {
            if (isCircularPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isCircularPrime(int n) {
        int c = 1;
        int k = n;
        while (k / 10 > 0) {
            c *= 10;
            k /= 10;
        }

        int rotation = n;

        do {
            if (!isPrime(rotation)) {
                return false;
            }
            int firstDigit = rotation / c;
            rotation = rotation % c * 10 + firstDigit;

        } while (rotation != n);

        return true;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
