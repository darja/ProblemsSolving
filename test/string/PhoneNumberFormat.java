package string;

import junit.framework.TestCase;

/*
 *We are given a string S representing a phone number, which we would like to reformat.
 *String S consists of N characters: digits, spaces and/or dashes. It contains at least two digits.
 *
 *Spaces and dashes in string S can be ignored. We want to reformat the given phone number in such a way that the digits are
 *grouped in blocks of length three, separated by single dashes. If necessary, the final block or the last two blocks can be of length two.
 *
 *For example, given string S = "00-44 48 5555 8361", we would like to format it as 004-448-555-583-61.
 *Write a function function solution(S);
 *that, given a string S representing a phone number, returns this phone number reformatted as described above.
 *For example, given S = "00-44 48 5555 8361", the function should return 004-448-555-583-61. Given S = "0 - 22 1985--324",
 *the function should return 022-198-53-24. Given S = "555372654", the function should return 555-372-654.
 *
 *Assume that: N is an integer within the range [2..100]; string S consists only of digits (0−9),
 *spaces and/or dashes (-); string S contains at least two digits. In your solution, focus on correctness.
 *The performance of your solution will not be the focus of the assessment.
 */
// NOTE: 100% on Codility
public class PhoneNumberFormat extends TestCase {
    public String solution(String S) {
        StringBuilder sb = new StringBuilder();

        if (S == null || S.isEmpty()) {
            return sb.toString();
        }

        int groupSize = 0;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);

            if (Character.isDigit(ch)) {
                sb.append(ch);
                groupSize++;

                if (groupSize == 3) {
                    sb.append('-');
                    groupSize = 0;
                }
            }
        }

        if (sb.charAt(sb.length() - 1) == '-') {
            sb.delete(sb.length() - 1, sb.length());
        }

        if (sb.charAt(sb.length() - 2) == '-') {
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.insert(sb.length() - 2, '-');
        }

        return sb.toString();
    }

    private void testOne(String input, String output) {
        assertEquals(output, solution(input));
    }

    public void test() {
        testOne("", "");
        testOne(null, "");
        testOne("11", "11");
        testOne("12", "12");
        testOne("1-2", "12");
        testOne("1-2-", "12");
        testOne("--1-2-", "12");
        testOne("--121-2-", "12-12");
        testOne("00-44  48 5555 8361", "004-448-555-583-61");
        testOne("0 - 22 1985--324", "022-198-53-24");
        testOne("555372654", "555-372-654");
    }
}
