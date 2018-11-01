import junit.framework.TestCase;

@SuppressWarnings("WeakerAccess")
public class RepeatedStringMatch extends TestCase {
    public int repeatedStringMatch(String A, String B) {

        StringBuilder repeated = new StringBuilder(A);
        int num = 1;
        while (repeated.length() < B.length()) {
            num++;
            repeated.append(A);
        }

        if (repeated.indexOf(B) >= 0) return num;
        repeated.append(A);
        if (repeated.indexOf(B) >= 0) return num + 1;

        return -1;
    }

    private void testOne(String A, String B, int expected) {
        assertEquals(expected, repeatedStringMatch(A, B));
    }

    private void testOneFail(String A, String B) {
        assertEquals(-1, repeatedStringMatch(A, B));
    }

    public void testFail() {
        testOneFail("B", "A");
        testOneFail("B", "AAAA");
        testOneFail("B", "AAAA");
        testOneFail("abcdefgjfkl", "ba");
        testOneFail("AB", "ba");
    }

    public void testOk() {
        testOne("A", "A", 1);
        testOne("A", "AAAA", 4);
        testOne("A", "AAAA", 4);
        testOne("AB", "BA", 2);
        testOne("ABjfklaB", "BA", 2);
        testOne("abcd", "cdabcdab", 3);
        testOne("abc", "cabcabca", 4);
    }
}
