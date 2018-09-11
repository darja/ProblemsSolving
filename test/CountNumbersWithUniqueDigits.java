import junit.framework.TestCase;

public class CountNumbersWithUniqueDigits extends TestCase {
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 0 || n >= 10) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        int count = 0;
        int prevCount = 1;
        for (int i = 1; i <= n; ++i) {
            count = 9;
            for (int j = 9; j > (10 - i); --j) {
                count *= j;
            }
            count += prevCount;
            prevCount = count;
        }

        return count;
    }

    public int countNaive(int n) {
        int max = 1;
        for (int i = 0; i < n; ++i) {
            max *= 10;
        }

        int count = 0;
        for (int i = 0; i < max; ++i) {
            if (hasUniqueDigits(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean hasUniqueDigits(int num) {
        int[] digits = new int[10];
        while (num > 0) {
            int digit = num % 10;
            if (digits[digit] != 0) {
                return false;
            }
            digits[digit] = 1;
            num /= 10;
        }
        return true;
    }

    public void test() {
//        0: 1
//        1: 10
//        2: 91
//        3: 739
//        4: 5275
//        5: 32491
//        6: 168571
//        7: 712891
//        8: 2345851

//        for (int i = 8; i <= 9; ++i) {
//            System.out.printf("%s: %d", i, countNaive(i));
//            System.out.println();
//        }

        assertEquals(0, countNumbersWithUniqueDigits(-1));
        assertEquals(1, countNumbersWithUniqueDigits(0));
        assertEquals(0, countNumbersWithUniqueDigits(11));
        assertEquals(10, countNumbersWithUniqueDigits(1));
        assertEquals(91, countNumbersWithUniqueDigits(2));
        assertEquals(739, countNumbersWithUniqueDigits(3));
        assertEquals(5275, countNumbersWithUniqueDigits(4));
        assertEquals(32491, countNumbersWithUniqueDigits(5));
        assertEquals(168571, countNumbersWithUniqueDigits(6));
        assertEquals(712891, countNumbersWithUniqueDigits(7));
        assertEquals(2345851, countNumbersWithUniqueDigits(8));
        assertEquals(5611771, countNumbersWithUniqueDigits(9));
    }
}
