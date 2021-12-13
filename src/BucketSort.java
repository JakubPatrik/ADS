import java.util.*;

class BucketSort {

    public static void sort(int[] a) {
        readBuckets(a, fillBuckets(a));
    }

    @SuppressWarnings("unchecked")
    public static Queue<Integer>[] fillBuckets(int[] array) {
        if (array == null) return null;
        if (array.length == 0) return new LinkedList[0];

        int vmax = Integer.MIN_VALUE;
        for (int i : array) if (i > vmax) vmax = i;

        int vmin = vmax;
        for (int i : array) if (i < vmin) vmin = i;

        Queue<Integer>[] buckets = new Queue[vmax - vmin + 1];
        for (var i = 0; i < buckets.length; i++) buckets[i] = new LinkedList<>();

        for (int x : array) buckets[x - vmin].add(x);

        return buckets;
    }

    public static void readBuckets(int[] a, Queue<Integer>[] buckets) {
        if (buckets == null) return;

        for (int b = 0, i = 0; b < buckets.length; b++) {
            Queue<Integer> bucket = buckets[b];
            while (!bucket.isEmpty()) a[i++] = bucket.poll();
        }
    }
}