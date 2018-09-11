import design.MinStack;
import junit.framework.TestCase;

public class MinStackTest extends TestCase {
    public void test() {
        MinStack stack = new MinStack();

        stack.push(1);
        assertEquals(1, stack.getMin());
        assertEquals(1, stack.top());

        stack.push(2);
        assertEquals(1, stack.getMin());
        assertEquals(2, stack.top());

        stack.push(0);
        assertEquals(0, stack.getMin());
        assertEquals(0, stack.top());

        stack.pop();
        stack.pop();
        assertEquals(1, stack.getMin());
        assertEquals(1, stack.top());

        stack.pop();
        stack.push(-1);
        stack.push(2);
        assertEquals(-1, stack.getMin());
        assertEquals(2, stack.top());
    }
}
