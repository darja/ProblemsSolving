import util.ListTestCase;

import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddresses extends ListTestCase {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        backtrack(result, "", 0, 0, s);

        return result;
    }

    public void backtrack(List<String> result, String ipPart, int dotsCount, int pos, String s) {
        if (dotsCount > 4) {
            return;
        }

        if (pos >= s.length()) {
            if (dotsCount == 4) {
                result.add(ipPart);
            }
            return;
        }

        char char1 = s.charAt(pos);
        backtrack(result, createNewIpPart(dotsCount, ipPart, char1), dotsCount + 1, pos + 1, s);

        if (char1 != '0' && pos < s.length() - 1) {
            char char2 = s.charAt(pos + 1);
            backtrack(result, createNewIpPart(dotsCount, ipPart, char1, char2), dotsCount + 1, pos + 2, s);

            if (char1 <= '2' && pos < s.length() - 2) {
                char char3 = s.charAt(pos + 2);
                int num = (char1 - '0') * 100 + (char2 - '0') * 10 + (char3 - '0');
                if (num <= 255) {
                    backtrack(result, createNewIpPart(dotsCount, ipPart, char1, char2, char3), dotsCount + 1, pos + 3, s);
                }
            }
        }
    }

    private String createNewIpPart(int dotsCount, String base, char... chars) {
        StringBuilder sb = new StringBuilder(base);
        sb.append(chars);

        if (dotsCount < 3) {
            sb.append('.');
        }
        return sb.toString();
    }

    public void test1() {
        assertListsEqual(new String[] {
            "1.92.168.11",
            "19.2.168.11",
            "19.21.68.11",
            "19.216.8.11",
            "19.216.81.1",
            "192.1.68.11",
            "192.16.81.1",
            "192.16.8.11",
            "192.168.1.1",
        }, restoreIpAddresses("19216811"));
    }

    public void test2() {
        assertListsEqual(new String[] {
            "255.255.11.135",
            "255.255.111.35"
        }, restoreIpAddresses("25525511135"));
    }

    public void test3() {
        assertListsEqual(new String[] {
            "1.0.0.1"
        }, restoreIpAddresses("1001"));
    }

    public void test4() {
        assertListsEqual(new String[0], restoreIpAddresses("100"));
    }

    public void test5() {
        assertListsEqual(new String[] {
            "1.20.0.1",
            "12.0.0.1",
        }, restoreIpAddresses("12001"));
    }

    public void test6() {
        assertListsEqual(new String[0], restoreIpAddresses("111111111111111111111111111111111"));
    }
}
