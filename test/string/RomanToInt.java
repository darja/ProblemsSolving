package string;

import junit.framework.TestCase;

import java.util.HashMap;

/**
 * @see <a href="https://leetcode.com/problems/roman-to-integer/">Problem Description</a>
 */
@SuppressWarnings("ALL")
public class RomanToInt extends TestCase {
    public int romanToInt(String s) {
        HashMap<Character, Integer> romanSymbols = new HashMap<>();
        romanSymbols.put('I', 1);
        romanSymbols.put('V', 5);
        romanSymbols.put('X', 10);
        romanSymbols.put('L', 50);
        romanSymbols.put('C', 100);
        romanSymbols.put('D', 500);
        romanSymbols.put('M', 1000);

        int result = 0;
        int prevValue = 0;
        for (int i = 0; i < s.length(); ++i) {
            int value = romanSymbols.get(s.charAt(i));
            if (prevValue < value) {
                result -= 2 * prevValue;
            }
            result += value;
            prevValue = value;
        }

        return result;
    }

    public void testTrivial() {
        testCase("", 0);
        testCase("I", 1);
        testCase("V", 5);
        testCase("X", 10);
        testCase("L", 50);
        testCase("C", 100);
        testCase("D", 500);
        testCase("M", 1000);
    }

    public void testNumbers() {
        testCase("XX", 20);
        testCase("IX", 9);
        testCase("XI", 11);
        testCase("II", 2);
        testCase("VII", 7);
        testCase("XC", 90);
        testCase("XCVII", 97);
        testCase("LVIII", 58);
        testCase("MCMXCIV", 1994);
    }

    private void testCase(String input, int expected) {
        assertEquals(expected, romanToInt(input));
    }
}
