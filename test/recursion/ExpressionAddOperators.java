package recursion;

import util.ListTestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/expression-add-operators/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
// todo can be better
public class ExpressionAddOperators extends ListTestCase {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();

        back(result, "", num, target, 0, 0, 0);

        return result;
    }

    public void back(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
            if (target == eval)
                rst.add(path);
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                back(rst, path + cur, num, target, i + 1, cur, cur);
            } else {
                back(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);

                back(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);

                back(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }
    public void test() {
        assertListsEqual(new String[] {"2-1-2+1", "21-21", "2*1-2*1", "2-1*2*1", "2+1-2-1"}, addOperators("2121", 0));
        assertListsEqual(new String[] {"1*2*3", "1+2+3"}, addOperators("123", 6));
        assertListsEqual(new String[] {"1*2*3", "1+2+3"}, addOperators("123", 6));
        assertListsEqual(new String[] {"10-5", "1*0+5"}, addOperators("105", 5));
        assertListsEqual(new String[0], addOperators("2147483648", -2147483648));
        assertListsEqual(new String[0], addOperators("12", 6));
        assertListsEqual(new String[0], addOperators("", 6));
        assertListsEqual(new String[0], addOperators("", 0));
        assertListsEqual(new String[0], addOperators("", 1));
    }

}
