import junit.framework.TestCase;

import java.util.LinkedList;

public class BaseballGame extends TestCase {
    public int calPoints(String[] ops) {
        LinkedList<Integer> history = new LinkedList<>();

        for (String op : ops) {
            switch (op) {
                case "+":
                    int last = history.pop();
                    int prev = history.getFirst();
                    history.push(last);
                    history.push(last + prev);
                    break;

                case "D":
                    history.push(history.getFirst() * 2);
                    break;

                case "C":
                    history.pop();
                    break;

                default:
                    history.push(Integer.parseInt(op));
                    break;
            }
        }

        int result = 0;
        for (int num : history) {
            result += num;
        }

        return result;
    }

    public void test() {
        assertEquals(0, calPoints(new String[0]));
        assertEquals(5, calPoints(new String[] {"5"}));
        assertEquals(15, calPoints(new String[] {"5", "D"}));
        assertEquals(30, calPoints(new String[] {"5","2","C","D","+"}));
        assertEquals(27, calPoints(new String[] {"5","-2","4","C","D","9","+","+"}));
    }

}
