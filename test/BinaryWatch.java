import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch extends TestCase {
    public List<String> readBinaryWatch(int lightsCount) {
        List<String> results = new ArrayList<>();
        backtrack(results, 0, 0, lightsCount);

        return results;
    }

    private void backtrack(List<String> result, int num, int numLength, int lightsCount) {
        if (numLength == 10) {
            if (lightsCount == 0) {
                int h = num >> 6;
                int m = num % (1 << 6);
                if (h < 12 && m <= 59) {
                    String time = String.format("%d:%02d", h, m);
                    result.add(time);
                }
            }
            return;
        }
        backtrack(result, num << 1, numLength + 1, lightsCount);
        backtrack(result, (num << 1) + 1, numLength + 1, lightsCount - 1);
    }

    public void test() {
        testSingle(1, new String[] {"1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"});
        testSingle(2, new String[] {"0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33",
            "0:34","0:36","0:40","0:48","1:01","1:02","1:04","1:08","1:16","1:32",
            "2:01","2:02","2:04","2:08","2:16","2:32","3:00",
            "4:01","4:02","4:04","4:08","4:16","4:32","5:00","6:00",
            "8:01","8:02","8:04","8:08","8:16","8:32","9:00","10:00"});
//        testSingle(10, new String[] {"11:59"});
        testSingle(0, new String[] {"0:00"});
    }

    private void testSingle(int input, String[] expected) {
        List<String> result = readBinaryWatch(input);
        for (String item : expected) {
            assertTrue("Item missing: " + item, result.contains(item));
        }

        for (String item: result) {
            assertTrue("Extra item: " + item, contains(expected, item));
        }
        assertEquals(expected.length, result.size());
    }

    private boolean contains(String[] arr, String item) {
        for (String i : arr) {
            if (i.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
