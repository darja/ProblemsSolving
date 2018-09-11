import junit.framework.TestCase;

public class SmallestLetterGreaterThanTarget extends TestCase{
    public char nextGreatestLetter(char[] letters, char target) {
        char min = 'z';
        Character result = null;
        for (char l: letters) {
            if (l > target) {
                if (result == null || l < result) {
                    result = l;
                }
            }
            if (l < min) {
                min = l;
            }
        }

        if (result != null) {
            return result;
        }

        return min;
    }

    private void test(char[] letters, char target, char result) {
        assertEquals(result, nextGreatestLetter(letters, target));
    }

    public void test() {
        test(new char[] {'c', 'f', 'j'}, 'a', 'c');
        test(new char[] {'c', 'f', 'j'}, 'c', 'f');
        test(new char[] {'c', 'f', 'j'}, 'd', 'f');
        test(new char[] {'c', 'f', 'j'}, 'g', 'j');
        test(new char[] {'c', 'f', 'j'}, 'j', 'c');
        test(new char[] {'c', 'f', 'j'}, 'z', 'c');
        test(new char[] {'a', 'a', 'b'}, 'a', 'b');
        test(new char[] {'a', 'a', 'b'}, 'b', 'a');
        test(new char[] {'a', 'a', 'b'}, 'c', 'a');
    }
}
