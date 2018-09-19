import junit.framework.TestCase;

public class PermutationInString extends TestCase{
    public boolean checkInclusion(String what, String where) {
        int whatLen = what.length();
        int whereLen = where.length();

        if (whatLen > whereLen) {
            return false;
        }

        int[] whatCode = new int[26];
        int[] whereCode = new int[26];

        for (int i = 0; i < whatLen; ++i) {
            whatCode[charIndex(what, i)]++;
            whereCode[charIndex(where, i)]++;
        }

        int i = 0;
        while (true) {
            if (checkEqual(whatCode, whereCode)) {
                return true;
            }

            if (i >= whereLen - whatLen) {
                break;
            }

            whereCode[charIndex(where, i)]--;
            whereCode[charIndex(where, i + whatLen)]++;
            i++;
        }

        return false;
    }

    private boolean checkEqual(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; ++i) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    private int charIndex(String str, int index) {
        return str.charAt(index) - 'a';
    }

    public void test() {
        assertTrue(checkInclusion("a", "a"));
        assertTrue(checkInclusion("ab", "fjdbarew"));
        assertTrue(checkInclusion("ab", "ba"));
        assertTrue(checkInclusion("acb", "bac"));
        assertTrue(checkInclusion("acb", "dbacddd"));
        assertTrue(checkInclusion("adc", "dcda"));

        assertFalse(checkInclusion("bd", "a"));
        assertFalse(checkInclusion("b", "a"));
        assertFalse(checkInclusion("ab", "afdsbss"));
        assertFalse(checkInclusion("abd", "afdsbss"));
        assertFalse(checkInclusion("x", "afdsbss"));
    }
}
