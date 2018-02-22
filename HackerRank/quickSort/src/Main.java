import java.util.ArrayList;

public class Main {

    public static ArrayList<Integer> quickSort(ArrayList<Integer> arr, int pivot) {
        ArrayList<Integer> sortedLeft = new ArrayList<>();
        ArrayList<Integer> sortedRight = new ArrayList<>();
        if(arr.size() == 1) {
            return arr;
        }

        ArrayList<Integer> low = new ArrayList<Integer>();
        ArrayList<Integer> high = new ArrayList<Integer>();
        for(int i = 1; i < arr.size(); i++) {
            Integer temp = arr.get(i);
            if(temp > pivot) {
                high.add(temp);
            }
            else {
                low.add(temp);
            }
        }
        if(!low.isEmpty()) sortedLeft = quickSort(low, low.get(0));
        if(!high.isEmpty()) sortedRight = quickSort(high, high.get(0));
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.addAll(sortedRight);
        result.add(0, pivot);
        result.addAll(0, sortedLeft);

        printArr(result);
        System.out.println();
        return result;
    }

    public static void printArr(ArrayList<Integer> arr) {
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5);
        arr.add(8);
        arr.add(1);
        arr.add(3);
        arr.add(7);
        arr.add(9);
        arr.add(2);
        quickSort(arr, arr.get(0));
    }
}
