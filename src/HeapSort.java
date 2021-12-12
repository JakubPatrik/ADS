public class HeapSort {

    // with Max Heap we can sort in-place with increasing order
    public static void sort(int[] a) {
        if (a == null) return;

        Heap heap = new Heap(a);

        while (!heap.isEmpty())
            heap.removeMax();
    }

    /**
     * Computes how fast the given tasks can be finished by the given number of TAs.
     * 
     * @param durations Array containing the duration for each tasks.
     * @param n         Number of TAs to complete the tasks.
     * @return The shortest time in which all tasks can be completed.
     */
    public static int completeTasks(int[] durations, int k) {
        Heap heap = new Heap(durations);
        int[] minK = new int[k];
        
        while (!heap.isEmpty()) {
            int max = heap.removeMax();
            int minIndex = 0;
            for (int j = 1; j < k; j++) {
                if (minK[minIndex] > minK[j]) minIndex = j;
            }
            minK[minIndex] += max;
            
        }

        int max = minK[0];
        for (int i = 0; i < k; i++) {
            if (minK[i] > max) max = minK[i];
        }
        return max;
    }
}

class Heap {
    public int size;
    public int[] a;

    public Heap(int[] a) {
        this.a = a;
        this.size = a.length;
        heapify();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void heapify() {
        // Start at the parent of the last node.
        for (int i = (a.length - 2) / 2; i >= 0; i--)
            downheap(i);
    }  

    public int removeMax() {
        if (size == 0)
            return 0;
        int max = a[0];
        swap(0, --size);
        downheap(0);
        return max;
    }

    private void downheap(int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && a[i] < a[left])
            max = left;
        if (right < size && a[max] < a[right])
            max = right;
        if (i != max) {
            swap(i, max);
            downheap(max);
        }
    }

    private void swap(int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

}
