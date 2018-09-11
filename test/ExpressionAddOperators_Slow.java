import util.ListTestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpressionAddOperators_Slow extends ListTestCase{
    static abstract class Expression {
        abstract long eval();
        abstract Expression copy();
        abstract Expression append(Character digit);
        abstract Expression append(Character operation, Character digit);
    }

    static class Operation extends Expression {
        Expression right;
        Expression left;
        Character operator;

        private static HashMap<Character, Integer> opPriorities = new HashMap<>();

        static {
            opPriorities.put('*', 2);
            opPriorities.put('+', 1);
            opPriorities.put('-', 1);
        }

        Operation(Character operator) {
            this.operator = operator;
        }

        @Override
        long eval() {
            switch (operator) {
                case '+': return left.eval() + right.eval();
                case '-': return left.eval() - right.eval();
                case '*': return left.eval() * right.eval();
            }
            throw new UnsupportedOperationException();
        }

        @Override
        Expression copy() {
            Operation ex = new Operation(operator);
            ex.left = left.copy();
            ex.right = right.copy();
            return ex;
        }

        @Override
        Expression append(Character digit) {
            Operation operation = new Operation(operator);
            operation.left = left.copy();
            operation.right = right.append(digit);

            return operation;
        }

        @Override
        Expression append(Character op, Character digit) {
            if (opPriorities.get(op) > opPriorities.get(operator)) {
                Operation operation = new Operation(operator);
                operation.left = left.copy();
                operation.right = right.append(op, digit);

                return operation;
            } else {
                Operation operation = new Operation(op);
                operation.left = copy();
                operation.right = new Number(digit - '0');

                return operation;
            }
        }
    }

    static class Number extends Expression {
        long value;

        Number(long value) {
            this.value = value;
        }

        @Override
        long eval() {
            return value;
        }

        @Override
        Expression copy() {
            return new Number(value);
        }

        @Override
        Expression append(Character digit) {
            if (value > 0) {
                return new Number(value * 10 + (digit - '0'));
            } else {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        Expression append(Character operation, Character digit) {
            Number right = new Number(digit - '0');
            Operation oper = new Operation(operation);
            oper.left = this;
            oper.right = right;

            return oper;
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();

        if (num.length() == 0) {
            return result;
        }
        char firstChar = num.charAt(0);
        String exprString = "" + firstChar;
        String rest = num.substring(1);
        Expression expr = new Number(firstChar - '0');

        back(result, exprString, expr, rest, target);

        return result;
    }

    private void back(List<String> list, String exprString, Expression expr, String rest, int target) {
        if (rest.length() == 0) {
            if (expr.eval() == target) {
                list.add(exprString);
            }
            return;
        }

        char nextChar = rest.charAt(0);
        String newRest = rest.substring(1);

        try {
            back(list, exprString + nextChar, expr.append(nextChar), newRest, target);
        } catch (UnsupportedOperationException ignored) {}

        back(list, exprString + '+' + nextChar, expr.append('+', nextChar), newRest, target);
        back(list, exprString + '-' + nextChar, expr.append('-', nextChar), newRest, target);
        back(list, exprString + '*' + nextChar, expr.append('*', nextChar), newRest, target);
    }

    public void test() {
//        assertListsEqual(new String[] {"2-1-2+1", "21-21", "2*1-2*1", "2-1*2*1", "2+1-2-1"}, addOperators("2121", 0));
//        assertListsEqual(new String[] {"1*2*3", "1+2+3"}, addOperators("123", 6));
//        assertListsEqual(new String[] {"1*2*3", "1+2+3"}, addOperators("123", 6));
//        assertListsEqual(new String[] {"10-5", "1*0+5"}, addOperators("105", 5));
        assertListsEqual(new String[0], addOperators("2147483648", -2147483648));
        assertListsEqual(new String[0], addOperators("12", 6));
        assertListsEqual(new String[0], addOperators("", 6));
        assertListsEqual(new String[0], addOperators("", 0));
        assertListsEqual(new String[0], addOperators("", 1));
    }
}
