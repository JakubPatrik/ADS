public class QuickSortInPlace {

    /**
     * @param elements
     *                 Array of integers to be sorted.
     */
    public static void sort(int[] elements) {
        qs(elements, 0, elements.length - 1);
    }

    private static void qs(int[] a, int l, int r) {
        if (l >= r)
            return;

        int pivot = partition(a, l, r);
        qs(a, l, pivot - 1);
        qs(a, pivot + 1, r);
    }

    private static int partition(int[] a, int l, int r) {
        int i = l - 1;
        // take the pivot as the middle value and swap with the last
        int pivot = (l + r) / 2;
        swap(a, pivot, r);

        // swap elements to set the order [...L, pivot, ...G]
        for (int j = l; j < r; j++) {
            if (a[j] <= a[r])
                swap(a, ++i, j);
        }
        // finally swap the pivot element into its correct position
        swap(a, r, i + 1);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
