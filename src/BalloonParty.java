import java.io.*;
import java.util.*;

class BalloonParty {

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "balloon1";
        InputStream inStream = new FileInputStream(new File(fileName + ".in"));
        BalloonParty.run(inStream);
    }

    public static void run(InputStream in) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
        new BalloonParty().solve(sc);
        sc.close();
    }

    public void solve(Scanner sc) {
        int cols = sc.nextInt();
        int rows = sc.nextInt();
        // return 0 if there are less than 2 rows -> no balloons
        if (rows < 3) {
            System.out.println(0);
            return;
        }

        int[][] a = new int[rows][cols];
        boolean[][] filled = new boolean[rows][cols];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int total = iterate(a, filled);
        total = iterate(a, filled);
        System.out.println(total);

    }

    private static int iterate(int[][] a, boolean[][] filled) {
        int total = 0;
        for (int i = 1; i < a.length - 1; i++) {
            for (int j = 1; j < a[0].length - 1; j++) {
                int center = a[i][j];

                int left = a[i - 1][j];
                boolean lf = filled[i - 1][j];

                int top = a[i][j - 1];
                boolean tf = filled[i][j - 1];

                int right = a[i + 1][j];
                boolean rf = filled[i + 1][j];

                int bottom =  a[i][j + 1];
                boolean bf = filled[i][j + 1];

                int max = 0;
                if (left > max && !lf) max = left;
                if (top > max && !tf) max = top;
                if (right > max && !rf) max = right;
                if (bottom > max && !bf) max = bottom;

                if (max != 0) {
                    int diff = center - max;
                    if (diff > 0) {
                        filled[i][j] = true;
                        total += diff;
                        System.out.println("examine:" + a[i][j] + ", added: " + diff + ", total: " + total);
                    }
                }
            }    
        }
        System.out.println();
        return total;
    }
}
