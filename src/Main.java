import java.util.List;

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i < 10; ++i) {
            new SpiralMatrix().printMatrix(i);
            System.out.println();
            System.out.println();
        }
    }

    private void runCombinationSum3() {
        CombinationSum3.Solution sol = new CombinationSum3.Solution();

        List<List<Integer>> result = sol.combinationSum3(3, 15);
        System.out.print(result);
    }

    public static int computeArea(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int s1 = (x2-x1) * (y2-y1);
        int s2 = (x4-x3) * (y4-y3);
        int xInt1 = Math.min(x2, x4);
        int xInt2 = Math.max(x1, x3);
        int yInt1 = Math.min(y2, y4);
        int yInt2 = Math.max(y1, y3);
        int sInt = (xInt1 > xInt2 && yInt1 > yInt2) ? (xInt1 - xInt2) * (yInt1 - yInt2) : 0;
        return s1 + s2 - sInt;
    }
}
