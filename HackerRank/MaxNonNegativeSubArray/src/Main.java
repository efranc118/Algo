import java.util.ArrayList;

public class Main {

    public ArrayList<Integer> maxset(ArrayList<Integer> a) {
        ArrayList<Integer> maxSubSet = new ArrayList<Integer>();
        ArrayList<Integer> currentSubSet = new ArrayList<Integer>();
        Double currentSum = 0d;
        Double maxSum = 0d;
        for(int i = 0; i < a.size(); i++) {
            if(a.get(i) >= 0) {
                currentSubSet.add(a.get(i));
                currentSum += a.get(i);
                if(currentSum > maxSum || (currentSum.equals(maxSum) && currentSubSet.size() > maxSubSet.size())) {
                    maxSubSet.clear();
                    maxSubSet.addAll(currentSubSet);
                    maxSum = currentSum;
                }
            }
            else {
                currentSubSet.clear();
                currentSum = 0d;
            }
        }
        return maxSubSet;
    }

    public static void main(String[] args) {
        Main main = new Main();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(-1);
        list.add(0);
        ArrayList<Integer> result = main.maxset(list);
        for(int i = 0; i < result.size();i++) {
            System.out.print(result.get(i));
        }
    }
}
