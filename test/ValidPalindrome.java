import junit.framework.TestCase;

public class ValidPalindrome extends TestCase {
    public boolean isPalindrome(String s) {
        int len = s.length();
        int hi = len - 1;
        int lo = 0;

        while (hi > lo) {
            while (lo < len && !Character.isLetterOrDigit(s.charAt(lo))) {
                lo++;
            }

            while (hi > 0 && !Character.isLetterOrDigit(s.charAt(hi))) {
                hi--;
            }

            if (hi < lo) {
                break;
            }

            if (Character.toLowerCase(s.charAt(lo)) == Character.toLowerCase(s.charAt(hi))) {
                lo++;
                hi--;
            } else {
                return false;
            }
        }
        return true;
    }

    public void testOk() {
        assertTrue(isPalindrome(".*&*(&"));
        assertTrue(isPalindrome(""));
        assertTrue(isPalindrome("1"));
        assertTrue(isPalindrome("..d"));
        assertTrue(isPalindrome(".d.d"));
        assertTrue(isPalindrome("maoaM"));
        assertTrue(isPalindrome("ma1 o...1aM"));
        assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(isPalindrome("Аргентина манит негра"));
        assertTrue(isPalindrome("1a232a1"));
    }

    public void testFail() {
        assertFalse(isPalindrome("bububu"));
        assertFalse(isPalindrome("bu   bu   bu"));
        assertFalse(isPalindrome("b2u1b"));
        assertFalse(isPalindrome("bu   bu   biooo"));
    }
}
