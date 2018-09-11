import junit.framework.TestCase;

public class BitsNumber extends TestCase {
    public void test() {
        testSingle(1, 1);
        testSingle(2, 0b11);
        testSingle(2, 0b101);
        testSingle(3, 0b111);
        testSingle(4, 0b100111);
        testSingle(4, 0b10010001001000);
        testSingle(4, 0b01010101);
        testSingle(16, 0b01010101010101010101010101010101);
        testSingle(32, 0b11111111111111111111111111111111);
    }

    private void testSingle(int numBits, int input) {
        assertEquals(numBits, hammingWeight(input));

    }

    public int hammingWeight(int n) {
        int bitsNum = 0;

        int mask = 1;

        for (int i = 0; i < 32; ++i) {
            if ((mask & n) != 0) {
                bitsNum++;
            }
            mask <<= 1;
        }

        return bitsNum;
    }
}
