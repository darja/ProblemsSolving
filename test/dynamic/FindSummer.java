package dynamic;

import junit.framework.TestCase;

/**Partition an array, where highest value of A is greater than lowest in B
 *
 *   John was sitting near to a fireplace in his house, trying to get some
 * warmth from the fire. Fighting his cold at the end of a freezing,
 * short, dark winter day, he started wondering why it always had to be
 * so cold during this season. That was when he came up with an idea.
 *   John stated that winter is the initial part of the year in which it is
 * always colder than in the remaining part. This latter part is called
 * 'summer'. Then he assumed that summer is always warmer than winter;
 * that is, any temperature measured in winter is colder than every
 * temperature measured in summer.
 *   Then he searched the Internet
 * and found the previous year's meteorological data, which contained the
 * year's temperature measurements. He began to wonder if it might be
 * possible to divide the year into winter and summer so that winter
 * comes before summer and each winter's temperature measurement is
 * smaller than any temperature measured in summer. In case there are many
 * such possibilities, find the one in which the winter period is as short as possible
 * */
// NOTE: this solution didn't achieve 100% on Codility
public class FindSummer extends TestCase {
    public int solution(int[] T) {
        int[] code = new int[T.length];
        code[0] = 0;
        code[1] = T[1] > T[0] ? 1 : 0;

        for (int i = 2; i < T.length; ++i) {
            if (T[i] > T[code[i - 1]]) {
                code[i] = i;
            } else {
                code[i] = code[i - 1];
            }
        }

        for (int i = code[code.length - 1] - 1; i >= 0; --i) {
            if (code[i] < i) {
                return i + 1;
            }
        }

        return 1;
    }

    public void testOne(int[] input, int expected) {
        assertEquals(expected, solution(input));
    }

    public void test() {
        testOne(new int[] {5, -2, 3, 8, 6}, 3);
        testOne(new int[] {-5, -5, -5, -42, 6, 12}, 4);
        testOne(new int[] {1, 2}, 1);
        testOne(new int[] {1, 2, 8}, 1);
        testOne(new int[] {1, 2, -1, 8}, 3);
        testOne(new int[] {-2, -2, 2, 3, -10, 4, 8, 5}, 5);
    }
}
