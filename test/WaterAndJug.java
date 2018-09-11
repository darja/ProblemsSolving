import junit.framework.TestCase;

import java.util.*;

public class WaterAndJug extends TestCase {
    public boolean canMeasureWater(int jug1, int jug2, int target) {
        if (target == jug1 || target == jug2) {
            return true;
        }

        if (jug1 + jug2 < target) {
            return false;
        }

        return target % GCD(jug1, jug2) == 0;

    }

    public int GCD(int a, int b){
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public boolean canMeasureWater2(int jug1, int jug2, int target) {
        Set<Integer> amountsToAdd = new HashSet<>();
        amountsToAdd.add(jug1);
        amountsToAdd.add(jug2);

        int max = jug1;
        int min = jug2;
        if (max < min) {
            min = jug1;
            max = jug2;
        }

        if (min > 0) {
            while (max > min) {
                amountsToAdd.add(max - min);
                max -= min;
            }
        }

        return canMeasure2(amountsToAdd, target);
    }

    private boolean canMeasure2(Set<Integer> amountsToAdd, int target) {
        if (target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }

        for (int a : amountsToAdd) {
            if (canMeasure2(amountsToAdd, target - a)) {
                return true;
            }
        }

        return false;
    }

    public boolean canMeasureWater1(int jug1, int jug2, int target) {
        Set<Integer> amountsToAdd = new HashSet<>();

        amountsToAdd.add(jug1);
//        amountsToAdd.add(-jug1);

        if (jug1 != jug2) {
            amountsToAdd.add(jug2);
//            amountsToAdd.add(-jug2);

            int max = jug1;
            int min = jug2;
            if (max < min) {
                min = jug1;
                max = jug2;
            }

            if (min > 0) {
                while (max > min) {
                    amountsToAdd.add(max - min);
                    max -= min;
                }
            }
        }
        amountsToAdd.remove(0);

        Set<Integer> possibleAmounts = new HashSet<>();
        possibleAmounts.add(0);

        return measureAll1(amountsToAdd, possibleAmounts, new HashSet<Integer>(), target);
    }

    private boolean measureAll1(Set<Integer> amountsToAdd, Set<Integer> possibleAmounts, Set<Integer> seenAmounts, int target) {
        if (possibleAmounts.isEmpty()) {
            return false;
        }

        seenAmounts.addAll(possibleAmounts);

        Set<Integer> newAmounts = new HashSet<>();
        for (int amount : possibleAmounts) {
            for (int inc : amountsToAdd) {
                int newAmount = amount + inc;
                if (newAmount == target) {
                    return true;
                } else if (newAmount < target && newAmount > 0 && !seenAmounts.contains(newAmount)) {
                    newAmounts.add(newAmount);
                }
            }
        }
        System.out.println(newAmounts);
        return measureAll1(amountsToAdd, newAmounts, seenAmounts, target);
    }

    public void test() {
        assertTrue(canMeasureWater(3, 5, 4));
        assertTrue(canMeasureWater(1, 5, 4));
        assertTrue(canMeasureWater(2, 2, 4));
        assertTrue(canMeasureWater(4, 2, 4));
        assertTrue(canMeasureWater(2, 5, 1));
        assertTrue(canMeasureWater(1, 0, 1));
        assertTrue(canMeasureWater(1, 0, 0));
        assertTrue(canMeasureWater(0, 1, 0));
        assertTrue(canMeasureWater(14, 2, 0));
        assertTrue(canMeasureWater(0, 0, 0));
        assertTrue(canMeasureWater(104659, 104677, 142424));

        assertFalse(canMeasureWater(1, 1, 4));
        assertFalse(canMeasureWater(2, 6, 5));
        assertFalse(canMeasureWater(2, 10, 5));
        assertFalse(canMeasureWater(3, 3, 5));
        assertFalse(canMeasureWater(3, 3, 20));
        assertFalse(canMeasureWater(12, 3, 55));
        assertFalse(canMeasureWater(0, 2, 1));
        assertFalse(canMeasureWater(0, 0, 1));
    }
}
