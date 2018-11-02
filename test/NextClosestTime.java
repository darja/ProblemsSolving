import junit.framework.TestCase;

import java.util.HashSet;

@SuppressWarnings("WeakerAccess")
public class NextClosestTime extends TestCase {
    public String nextClosestTime(String time) {
        HashSet<Byte> digitsSet = new HashSet<>();
        for (int i = 0; i < time.length(); ++i) {
            char c = time.charAt(i);
            if (c >= '0' && c <= '9') {
                digitsSet.add((byte)(c - '0'));
            }
        }

        int[] digits = new int[digitsSet.size()];
        int i = 0;
        for (Byte digit : digitsSet) {
            digits[i++] = digit;
        }

        int srcHour = Integer.parseInt(time.substring(0, 2));
        int srcMin = Integer.parseInt(time.substring(3, 5));

        TimeAndDiff interim = new TimeAndDiff(srcHour, srcMin, 24 * 60);
        nextTimeBack(srcHour, srcMin, 0, 0, 0, digits, interim);

        return String.format("%02d:%02d", interim.hour, interim.min);
    }

    private void nextTimeBack(int srcHour, int srcMin, int hour, int min, int index, int[] digits, TimeAndDiff interimResult) {
        if (index == 4) {
            int diff = timeToMin(hour, min) - timeToMin(srcHour, srcMin);
            if (diff <= 0) diff += 24 * 60;

            if (diff < interimResult.diff) {
                interimResult.update(hour, min, diff);
            }
            return;
        }

        for (int digit : digits) {
            int nextHour = hour;
            int nextMin = min;
            if (index < 2) {
                nextHour = hour * 10 + digit;
                if (nextHour > 23) continue;
            } else {
                nextMin = min * 10 + digit;
                if (nextMin > 59) continue;
            }

            nextTimeBack(srcHour, srcMin, nextHour, nextMin, index + 1, digits, interimResult);
        }
    }

    private int timeToMin(int h, int min) {
        return h * 60 + min;
    }

    private class TimeAndDiff {
        int hour;
        int min;
        int diff;

        TimeAndDiff(int hour, int min, int diff) {
            update(hour, min, diff);
        }

        void update(int hour, int min, int diff) {
            this.hour = hour;
            this.min = min;
            this.diff = diff;
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d (%d min diff)", hour, min, diff);
        }
    }

    private void testOne(String input, String expected) {
        assertEquals(expected, nextClosestTime(input));
    }

    public void test() {
        testOne("11:11", "11:11");
        testOne("11:01", "11:10");
        testOne("11:08", "11:10");
        testOne("23:58", "22:22");
        testOne("22:58", "22:22");
        testOne("19:34", "19:39");
    }
}
