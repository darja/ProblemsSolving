import junit.framework.TestCase;

public class Toptal extends TestCase {
    public void test3() {
        assertEquals(7, solution3(new int[]{1, 3, 2, 5, 4, 4, 6, 3, 2}));
    }

    public int solution3(int[] A) {
        Rect rect = new Rect();

        int x = 0;
        int y = 0;
        int dx = 1;
        int dy = 0;

        for (int i = 0; i < A.length; ++i) {
            dy = ((dy + 1) % 2) * (i % 4 == 0 ? 1 : -1);
            dx = ((dx + 1) % 2) * (i % 4 == 1 ? 1 : -1);

            int prevX = x;
            int prevY = y;

            y += A[i] * dy;
            x += A[i] * dx;

            if (rect.intersect(prevX, prevY, x, y)) {
                return i + 1;
            }

            if (i == 0) {
                rect.init(x, prevY, y);
            } else {
                rect.addPoint(x, y);
            }
        }
        return 0;
    }

    public class Rect {
        int left, right, top, bottom;

        boolean intersect(int x1, int y1, int x2, int y2) {
            return
                (x1 == x2 && left < x1 && x1 < right && !(y1 > top && y2 > top || y1 < bottom && y2 < bottom)) ||
                    (y1 == y2 && top < y1 && y1 < bottom && !(x1 > left && x2 > bottom || x2 < bottom && y2 < bottom));
        }

        void init(int x, int y1, int y2) {
            left = x;
            right = x;
            top = Math.max(y1, y2);
            bottom = Math.min(y1, y2);
        }

        void addPoint(int x, int y) {
            if (x < left) {
                left = x;
            }
            if (x > right) {
                right = x;
            }
            if (y > top) {
                top = y;
            }
            if (y < bottom) {
                bottom = y;
            }
        }
    }

    public void test2() {
//        testSingle2(new int[] {1,1,0,1}, new int[] {1, 0, 0, 1, 1});
//        testSingle2(new int[] {1, 0, 0, 1, 1}, new int[] {1,1,0,1});
//        testSingle2(new int[] {1,0,0,1,1,1}, new int[] {1,1,0,1,0,1,1});
//        testSingle2(new int[] {1,1,0,1,0,1,1}, new int[] {1,0,0,1,1,1});
//        testSingle2(new int[] {1}, new int[] {1,1});
//        testSingle2(new int[] {1,1}, new int[] {1});
        testSingle2(new int[] {0}, new int[] {0});
    }

    private void testSingle2(int[] result, int[] input) {
        int[] actual = solution2(input);
        assertEquals("length", result.length, actual.length);
        for (int i = 0; i < result.length; ++i) {
            assertEquals("item " + i, result[i], actual[i]);
        }
    }

    public int[] solution2(int[] A) {
        long pow = 1;
        long x = 0;

        for (int a : A) {
            x += a * pow;
            pow *= -2;
        }
        if (x == 0) {
            return A;
        }

        long y = -x;
        int n = 0;
        int rem;
        while (y != 0) {
            rem = (int) y % -2;
            y /= -2;
            if (rem == -1) {
                y++;
            }
            n++;
        }

        y = -x;

        int[] result = new int[n];

        for (int i = 0; i < n; ++i) {
            result[i] = (int)(y % -2);
            y /= -2;
            if (result[i] == -1) {
                result[i] = 1;
                y += 1;
            }
        }

        return result;
    }

    public void test1() {
        testSingle1(4, 5, new int[]{5, 5, 1, 7, 2, 3, 5});
        testSingle1(-1, 1, new int[]{1, 1, 1});
        testSingle1(-1, 1, new int[]{1, 1, 1});
        testSingle1(-1, 1, new int[]{2, 2, 2});
        testSingle1(-1, 1, new int[]{2, 2});
        testSingle1(-1, 1, new int[]{1, 1});
        testSingle1(-1, 1, new int[]{1});
        testSingle1(-1, 1, new int[]{2});
        testSingle1(-1, 1, new int[]{});

        testSingle1(2, 200000, new int[]{200000, 4789877, 473829148, 200000});
    }

    private void testSingle1(int result, int x, int[] input) {
        assertEquals(result, solution1(x, input));
    }

    public int solution1(int X, int[] A) {
        int rightCount = 0;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == X) {
                A[i] = 1;
            } else {
                rightCount++;
                A[i] = 0;
            }
        }

        if (rightCount == 0) {
            return -1;
        }

        int leftCount = 0;
        for (int i = 0; i < A.length; ++i) {
            if (leftCount == rightCount) {
                return i;
            }
            rightCount -= (1 - A[i]);
            leftCount += A[i];
        }

        return -1;
    }
}
