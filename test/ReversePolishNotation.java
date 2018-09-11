import junit.framework.TestCase;

import java.util.Stack;

public class ReversePolishNotation extends TestCase {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token: tokens) {
            switch (token) {
                case "*": {
                    int op1 = stack.pop();
                    int op2 = stack.pop();
                    stack.push(op1 * op2);
                    break;
                }

                case "+": {
                    int op1 = stack.pop();
                    int op2 = stack.pop();
                    stack.push(op1 + op2);
                    break;
                }

                case "-": {
                    int op2 = stack.pop();
                    int op1 = stack.pop();
                    stack.push(op1 - op2);
                    break;
                }

                case "/": {
                    int op2 = stack.pop();
                    int op1 = stack.pop();
                    stack.push(op1 / op2);
                    break;
                }

                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    public void test() {
        testEval(9, new String[] {"2", "1", "+", "3", "*"});
        testEval(6, new String[] {"4", "13", "5", "/", "+"});
        testEval(2, new String[] {"1", "2", "*"});
        testEval(9, new String[] {"1", "2", "5", "-", "12", "+", "*"});
    }

    private void testEval(int expected, String[] tokens) {
        assertEquals(expected, evalRPN(tokens));
    }
}
