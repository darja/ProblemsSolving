package util;

import junit.framework.TestCase;

public class TestCaseOneOf extends TestCase {
    protected void assertOneOf(int value, int... possibleValues) {
        boolean found = false;
        for (int v : possibleValues) {
            if (value == v) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
