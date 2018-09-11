import java.util.Scanner;

public class Stairs {
    public static void main(String args[]) throws Exception {
//        int n = Integer.parseInt(args[0]);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < n - i; ++j) {
                System.out.print(" ");
            }
            for (int j = n - i; j < n; ++j) {
                System.out.print("#");
            }

            if (i < n) {
                System.out.println();
            }
        }
    }
}
