import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ForestRun {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("forest1.in"));
        new ForestRun().solve(sc, System.out);
        sc.close();
    }

    public void solve(Scanner sc, PrintStream out) {
        // Decode the first two lines to setup the data structures
        Scanner first = new Scanner(sc.nextLine());
        int nodes = first.nextInt();
        int e_count = first.nextInt();
        List<Integer> entrances = new ArrayList<>(e_count);
        first.close();

        Scanner second = new Scanner(sc.nextLine());
        while (second.hasNextInt()) {
            int e = second.nextInt();
            entrances.add(e);
        }
        second.close();

        // Create trees where each tree is an entrance to a forest
        List<BinaryTree<Integer>> trees = new ArrayList<>();
        for (int entry : entrances) {
            BinaryTree<Integer> t = new BinaryTree<>();
            t.add(entry, entry);
            trees.add(t);
        }

        // Build-up the trees from the list of entrances
        int tree_id = 0;
        for (int ptr = 1; ptr <= nodes; ptr++) {
            String line = sc.nextLine(); // 2 2 3
            Scanner lnScanner = new Scanner(line);
            int children = lnScanner.nextInt();

            if ((tree_id + 1) < entrances.size() && ptr == entrances.get(tree_id + 1)) {
                tree_id++;
            }

            BinaryTree<Integer> t = trees.get(tree_id);
            while (children > 0 && lnScanner.hasNextInt()) {
                int left = lnScanner.nextInt();
                t.addLeftChildFor(ptr, left, left);
                if (lnScanner.hasNextInt()) {
                    int right = lnScanner.nextInt();
                    t.addRightChildFor(ptr, right, right);
                }
            }
            lnScanner.close();
        }

        // Display the preorder traversals of the trees
        for (var t : trees.subList(0, 1))
            t.preorder();

    }

    public static void print(int[] a) {
        for (int x : a)
            System.out.print(x + ", ");
        System.out.println();
    }
}
