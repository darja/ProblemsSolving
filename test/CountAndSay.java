import junit.framework.TestCase;

public class CountAndSay extends TestCase {
    public void test() {
        assertEquals("1", countAndSay(1));
        assertEquals("11", countAndSay(2));
        assertEquals("21", countAndSay(3));
        assertEquals("1211", countAndSay(4));
        assertEquals("111221", countAndSay(5));
        assertEquals("312211", countAndSay(6));
        assertEquals("13112221", countAndSay(7));
        assertEquals("1113213211", countAndSay(8));
        assertEquals("31131211131221", countAndSay(9));
//        assertEquals("31131211131221", countAndSay(25));
    }

    public String countAndSay(int n) {
        String s = "1";
        if (n > 1) {
            for (int i = 1; i < n; ++i) {
                s = createNext(s);
            }
        }
        return s;
    }

    public String createNext(String s) {
        StringBuilder sb = new StringBuilder();

        char prev = s.charAt(0);
        int count = 1;

        for (int i = 1; i < s.length(); ++i) {
            char curr = s.charAt(i);
            if (prev == curr) {
                count++;
            } else {
                sb.append(count);
                sb.append(prev);
                prev = curr;
                count = 1;
            }
        }
        sb.append(count);
        sb.append(prev);

        return sb.toString();
    }
}
