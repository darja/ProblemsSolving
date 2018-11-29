package array;

import junit.framework.TestCase;

public class EquiIndex extends TestCase {
    public void test() {
//        testSingle(1, new int[] { -1, 3, -4, 5, 1, -6, 2, 1 });
//        testSingle(2, new int[] { 2, 0, 1, 2 });
//        testSingle(0, new int[] { 0, 0, 0, 0 });
//        testSingle(-1, new int[] { });
        testSingle(0, new int[] { 1 });
        testSingle(-1, new int[] { 1, 1 });
        testSingle(0, new int[] { 1, 0 });
        testSingle(3, new int[] { 1, 1, 1, 1, 1, 1, 1 });
        testSingle(-1, new int[] { 1, 2, 3, 4, 5, 6, 7 });
    }

    private void testSingle(int result, int[] input) {
        assertEquals(result, solution(input));
    }

    public int solution(int[] A) {
        if (A.length == 0) {
            return -1;
        } else if (A.length == 1) {
            return 0;
        }


        int leftSum = 0;
        int rightSum = 0;
        for (int item : A) {
            rightSum += item;
        }

        for (int i = 0; i < A.length - 1; ++i) {
            if (leftSum == rightSum - A[i]) {
                return i;
            }
            leftSum += A[i];
            rightSum -= A[i];
        }

        return -1;
    }
}
