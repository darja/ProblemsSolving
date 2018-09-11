package design;

import junit.framework.TestCase;

public class WordFilterTest extends TestCase {

    public void test() {
        WordFilter f = new WordFilter(new String[] {"WordFilter","f"});
        assertEquals(-1, f.f(null, null));
    }
}
