import java.io.*;
import java.util.*;

class Solution {

    public static void main(String[] args) {
        FileInputStream inStream;
        try {
            inStream = new FileInputStream(new File("garden3.in")); 
            Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(inStream)));
            new Solution().solve(sc);
            sc.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
    }

    public void solveCorrect(Scanner sc, PrintStream out) {
        sc.nextInt();
        
        List<Point> pts = new ArrayList<>();
        while (sc.hasNext()) 
            pts.add(new Point(sc.nextInt(), sc.nextInt()));
        
        // Use the shoelace formula
        int psum = 0;
        int nsum = 0;
        
        // ∑i=0 to n−1 x_i*y_(i+1)+x_n*y_1
        for (int i = 0; i < pts.size(); i++) {
            int sindex = (i + 1) % pts.size();
            int prod = pts.get(i).x * pts.get(sindex).y;
            psum += prod;
        }
        // ∑i=0 to n−1 x_(i+1)*y_i−x_1*y_n
        for (int i = 0; i < pts.size(); i++) {
            int sindex = (i + 1) % pts.size();
            int prod = pts.get(sindex).x * pts.get(i).y;
            nsum += prod;
        }
        // A=1/2 * | psum - nsum |
        out.print(Math.abs((psum - nsum) / 2));
    }

    public void solve(Scanner sc) {
        sc.nextInt();
        List<Point> pts = new ArrayList<>();
        while(sc.hasNext()) 
            pts.add(new Point(sc.nextInt(), sc.nextInt()));
        pts = vertical(pts);

        // for (Point p : pts) System.out.println(p);
        
        int size = pts.size() / 2;
        int count = 0;
        for(int i = 0; i < size; i++) {
            Point leftFence = pts.get(i);
            Point rightFence = pts.get(i + size);
            while(leftFence.x < rightFence.x) {
                count++;
                leftFence = leftFence.right();
            }
        }
        System.out.println(count);
    }


    private List<Point> vertical(List<Point> points) {
        List<Point> newPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point f = points.get(i % points.size());
            Point s = points.get((i + 1) % points.size());
            if (f.x == s.x) { // we populate from bottom to top
                int min = Math.min(f.y, s.y);
                int diff = Math.abs(f.y - s.y);
                int a = 0;
                while (a < diff) {
                    newPoints.add(new Point(f.x, min + a++));
                }
            }
        }
        return newPoints;
    }

    class Point{
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point right() {return new Point(x + 1, y);}

        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public boolean equals(Object other) {
            if (other == null) return false;
            if (other instanceof Point) {
                Point that = (Point) other;
                return x == that.x && y == that.y;
            }
            return false;
        }
    }
}

