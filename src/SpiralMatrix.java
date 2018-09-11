public class SpiralMatrix {
    public void printMatrix(int n) {
        int[][] m = generateMatrix(n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(m[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        int number = 0;

        for (int i = 0; i < n/2; ++i) {
            number = buildLevel(m, i, number);
        }

        if (n % 2 != 0) {
            m[n/2][n/2] = number + 1;
        }

        return m;
    }

    public int buildLevel(int[][] matrix, int level, int number) {
        int dim = matrix.length;
        int delta = dim - 2 * level - 1;
        int high = dim - level - 1;
        for (int i = 0; i < delta; ++i) {
            number++;
            matrix[level][level + i] = number;
            matrix[level + i][high] = number + delta;
            matrix[high][high - i] = number + 2 * delta;
            matrix[high - i][level] = number + 3 * delta;
        }
        return number + 3 * delta;
    }
}
