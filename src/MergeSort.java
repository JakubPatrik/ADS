public class MergeSort {

    public static void sort(int[] elements) {
        if (elements == null)
            return;
        mergeSort(elements, 0, elements.length - 1);
    }

    // Main function that sorts arr[l..r] using
    // merge()
    private static void mergeSort(int arr[], int l, int r) {
        if (l >= r)
            return;

        // Find the middle point
        int m = (l + r) / 2;

        // Sort first and second halves
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        // Merge the sorted halves
        merge(arr, l, m, r);
    }

    private static void merge(int[] a, int l, int m, int r) {
        int left[] = copyRange(a, l, m);
        int right[] = copyRange(a, m + 1, r);

        int i = 0, j = 0, k = l;

        while (i < left.length && j < right.length)
            a[k++] = left[i] < right[j] ? left[i++] : right[j++];

        while (i < left.length)
            a[k++] = left[i++];
        while (j < right.length)
            a[k++] = right[j++];
    }

    private static int[] copyRange(int[] arr, int from, int to) {
        int[] res = new int[to - from + 1];
        int i = 0;
        while (from <= to)
            res[i++] = arr[from++];
        return res;
    }

}
