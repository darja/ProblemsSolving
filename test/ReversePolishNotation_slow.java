import junit.framework.TestCase;

import java.util.Stack;

public class ReversePolishNotation_slow extends TestCase {
    public void test() {
        testEval(9, new String[] {"2", "1", "+", "3", "*"});
        testEval(6, new String[] {"4", "13", "5", "/", "+"});
        testEval(2, new String[] {"1", "2", "*"});
        testEval(9, new String[] {"1", "2", "5", "-", "12", "+", "*"});
    }

    private void testEval(int expected, String[] tokens) {
        assertEquals(expected, evalRPN(tokens));
    }

    public int evalRPN(String[] tokens) {
        Evaluator evaluator = new Evaluator();
        return evaluator.eval(tokens);
    }

    public class Evaluator {
        Stack<Node> nodes = new Stack<Node>();

        public int eval(String[] tokens) {
            for (int i = 0; i < tokens.length; ++i) {
                String token = tokens[i];

                if (token.equals("+")) {
                    addOperation(new AddOperation());
                } else if (token.equals("-")) {
                    addOperation(new SubtractOperation());
                } else if (token.equals("*")) {
                    addOperation(new MultipleOperation());
                } else if (token.equals("/")) {
                    addOperation(new DivideOperation());
                } else {
                    int value = Integer.parseInt(token);
                    nodes.push(new Number(value));
                }
            }

            return nodes.pop().eval();
        }

        public void addOperation(Operation node) {
            node.right = nodes.pop();
            node.left = nodes.pop();
            nodes.push(node);
        }
    }

    public abstract class Node {
        public abstract int eval();
    }

    public class Number extends Node {
        final int value;

        public Number(int value) {
            this.value = value;
        }

        @Override
        public int eval() {
            return value;
        }
    }

    public abstract class Operation extends Node {
        public Node left;
        public Node right;
    }

    public class AddOperation extends Operation {
        @Override
        public int eval() {
            return left.eval() + right.eval();
        }
    }

    public class SubtractOperation extends Operation {
        @Override
        public int eval() {
            return left.eval() - right.eval();
        }
    }

    public class MultipleOperation extends Operation {
        @Override
        public int eval() {
            return left.eval() * right.eval();
        }
    }

    public class DivideOperation extends Operation {
        @Override
        public int eval() {
            return left.eval() / right.eval();
        }
    }
}
