public class Main {

    public static int[] quickSort(int[] arr, int pivot, int low, int high) {
        if(low >= high) return arr;
        int lessPtr = low;
        int greaterPtr = low;
        while(greaterPtr < high) {
            if(arr[greaterPtr] > pivot) {
                greaterPtr++;
            }
            else {
                int temp = arr[lessPtr];
                arr[lessPtr] = arr[greaterPtr];
                arr[greaterPtr] = temp;
                lessPtr++;
                greaterPtr++;
            }
        }
        int temp = arr[lessPtr];
        arr[lessPtr] = pivot;
        arr[high] = temp;
        printArr(arr);
        if(lessPtr - 1 > low) arr = quickSort(arr, arr[lessPtr - 1], low, lessPtr - 1);
        if(lessPtr + 1 < high) arr = quickSort(arr, arr[high], lessPtr + 1, high);
        return arr;
    }

    public static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[7];
        arr[0] = 1;
        arr[1] = 3;
        arr[2] = 9;
        arr[3] = 8;
        arr[4] = 2;
        arr[5] = 7;
        arr[6] = 5;

        quickSort(arr, 5, 0, 6);
    }
}
