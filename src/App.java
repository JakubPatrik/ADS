public class App {
  public static void main(String[] args) {
    int[] data = { 11, 90, 33, 71, 24, 50, 35, 30, 15, 21 };
    // MergeSort.sort(data);
    // QuickSort.sort(data);
    // QuickSortInPlace.sort(data);
    // HeapSort.sort(data);
    data = BucketSort.sort(data);
    for (int i : data)
      System.out.print(i + ", ");

    System.out.println("\n" + fCaller(new int[]{1,2,3}));
  }
  public static int fCaller(int[] arr) {
    return f(arr, 0, arr.length - 1);
  }
  
  private static int f(int[] arr, int i, int k) {
    if (i > k) {
      return 0;
    }
    return f(arr, i+1, k) + f(arr, i, k-1) + arr[i] + arr[k];
  }

  public static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int x = arr[i];
      int j = i;
      while (j > 0 && arr[j - 1] > x)
        arr[j] = arr[--j];
      arr[j] = x;
    }
  }

  public static void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[minIndex] > arr[j])
          minIndex = j;
      }
      swap(arr, i, minIndex);
    }
  }

  public static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1])
          swap(arr, j, j + 1);
      }
    }
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  // private static int[] copy(int[] arr) {
  //   int[] res = new int[arr.length];
  //   int i = 0;
  //   while (i < arr.length)
  //     res[i] = arr[i++];
  //   return res;
  // }

}
