import util.ListTestCase;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNumbers extends ListTestCase {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; ++i) {
            if (isSelfDividing(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isSelfDividing(int n) {
        int original = n;
        while (n > 0) {
            int digit = n % 10;
            if (digit == 0 || original % digit != 0) {
                return false;
            }
            n /= 10;
        }

        return true;
    }
    public void test() {
        test(1, 10, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        test(1, 21, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15});
        test(1, 2, new int[] {1, 2});
        test(18, 20, new int[0]);
        test(18, 25, new int[] {22, 24});
    }

    public void test(int left, int right, int[] expected) {
        assertListsEqual(expected, selfDividingNumbers(left, right));
    }
}
