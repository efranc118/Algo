import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> testArray = new ArrayList<ArrayList<Integer>>();
        testArray.add(new ArrayList<Integer>(Arrays.asList(9, 1, 3, 29, 36, 63, 67, 72, 74, 78, 85)));
        System.out.println(Solution.searchMatrix(testArray, 41));
    }
}
