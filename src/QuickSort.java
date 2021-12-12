public class QuickSort {
    
    /**
     * @param elements
     *     Array of integers to be sorted.
     */
    public static void sort(int[] elements) {
        qs(elements, 0, elements.length - 1);
    }

    private static void qs(int[] a, int l, int r) {
        if (l >= r) return;
        
        int pivot = partition(a, l, r);
        qs(a, l, pivot - 1);
        qs(a, pivot + 1, r);
    }

    private static int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int i = l - 1;
        for (int j = l; j < r; j++)
            if (a[j] < pivot) 
                swap(a, ++i, j); 
            
        swap(a, r, ++i);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
