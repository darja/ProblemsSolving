import junit.framework.TestCase;

public class Toptal2 extends TestCase {
    public void testFormat() {
        singleTest("0", 0);
        singleTest("10", 10);
        singleTest("100", 100);
        singleTest("1,000", 1000);
        singleTest("10,000", 10000);
        singleTest("100,000", 100000);
        singleTest("1,000,000", 1000000);
        singleTest("35,235,235", 35235235);
        singleTest("999,999,999", 999999999);

        singleTest("1,234,567,890", 1234567890);
    }

    public void singleTest(String expected, int input) {
        assertEquals(expected, format(input));
    }

    public String format(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            for (int i = 0; i < 3; ++i) {
                int digit = n % 10;
                n /= 10;
                sb.insert(0, digit);

                if (n == 0) {
                    break;
                }
            }

            if (n > 0) {
                sb.insert(0, ",");
            }
        }

        return sb.toString().trim();
    }
}
