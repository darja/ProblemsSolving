import junit.framework.TestCase;

public class TapeEquilibrium extends TestCase {
    public int solution(int[] A) {
        int left = A[0];
        int right = 0;

        for (int i = 1; i < A.length; ++i) {
            right += A[i];
        }

        int diff;
        int minDiff = Math.abs(left - right);

        for (int i = 1; i < A.length - 1; ++i) {
            left += A[i];
            right -= A[i];

            diff = Math.abs(left - right);

            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        return minDiff;
    }

    public void test() {
//        testSingle(0, new int[] {1, 1});
        testSingle(20, new int[] {-10, 10});
        testSingle(80, new int[] {100, 20});
        testSingle(1, new int[] {1, 1, 1});
        testSingle(1, new int[] {1, 2, 2});
        testSingle(1, new int[] {3, 1, 2, 4, 3});
        testSingle(3, new int[] {1, 2, 3, 2, 1});
        testSingle(1, new int[] {1, 1, 1, 1, 1});
        testSingle(30, new int[] {1, 2, 30, 2, 1});
    }

    private void testSingle(int expected, int[] input) {
        assertEquals(expected, solution(input));
    }
}
