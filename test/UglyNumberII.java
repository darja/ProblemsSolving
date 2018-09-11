import junit.framework.TestCase;

public class UglyNumberII extends TestCase {
    public int nthUglyNumber(int n) {
        int[] list = new int[n];
        list[0] = 1;

        int i2 = 0,
            i3 = 0,
            i5 = 0,
            n2, n3, n5;
        int next;
        int i = 1;

        while (i < n) {
            n2 = list[i2] * 2;
            n3 = list[i3] * 3;
            n5 = list[i5] * 5;
            next = Math.min(n2, Math.min(n3, n5));
            if (next == n2) i2++;
            if (next == n3) i3++;
            if (next == n5) i5++;

            list[i] = next;
            i++;
        }

        return list[n - 1];
    }

    public void test() {
        assertEquals(1, nthUglyNumber(1));
        assertEquals(2, nthUglyNumber(2));
        assertEquals(3, nthUglyNumber(3));
        assertEquals(4, nthUglyNumber(4));
        assertEquals(5, nthUglyNumber(5));
        assertEquals(6, nthUglyNumber(6));
        assertEquals(8, nthUglyNumber(7));
        assertEquals(9, nthUglyNumber(8));
        assertEquals(10, nthUglyNumber(9));
        assertEquals(12, nthUglyNumber(10));
        assertEquals(15, nthUglyNumber(11));
        assertEquals(16, nthUglyNumber(12));
        assertEquals(18, nthUglyNumber(13));
    }
}
