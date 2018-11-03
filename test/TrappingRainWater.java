import junit.framework.TestCase;

@SuppressWarnings("WeakerAccess")
public class TrappingRainWater extends TestCase {
    public int trap(int[] height) {
        int N = height.length;
        if (N < 3) return 0;

        int maxIndex = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; ++i) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }

        int result = 0;

        int localMax = height[0];
        for (int i = 1; i < maxIndex; ++i) {
            if (height[i] > localMax) {
                localMax = height[i];
            } else {
                result += localMax - height[i];
            }
        }


        localMax = height[N - 1];
        for (int i = N - 2; i > maxIndex; --i) {
            if (height[i] > localMax) {
                localMax = height[i];
            } else {
                result += localMax - height[i];
            }
        }


        return result;
    }

    private void testOneZero(int[] height) {
        assertEquals(0, trap(height));
    }

    public void testZero() {
        testOneZero(new int[0]);
        testOneZero(new int[]{1});
        testOneZero(new int[]{1, 2});
        testOneZero(new int[]{1, 1, 1});
        testOneZero(new int[]{1, 10, 100});
        testOneZero(new int[]{1, 10, 100, 20});
        testOneZero(new int[]{1, 20, 1});
        testOneZero(new int[]{0, 20, 0});
        testOneZero(new int[]{10, 10, 12, 11, 10});
    }

    private void testOne(int[] height, int expected) {
        assertEquals(expected, trap(height));
    }

    public void test() {
        testOne(new int[]{2, 1, 2}, 1);
        testOne(new int[]{2, 1, 2, 1, 2}, 2);
        testOne(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
        testOne(new int[]{0, 2, 1, 4, 0, 7, 4, 6, 0}, 7);
    }
}
