import java.util.ArrayList;

public class Main {

    public ArrayList<ArrayList<Integer>> generateMatrix(int a) {
        ArrayList<ArrayList<Integer>> result = new ArrayList(a);
        for(int i = 0; i < a; i++) {
            ArrayList<Integer> base = new ArrayList<>(a);
            for(int j = 0; j < a; j++) {
                base.add(0);
            }
            result.add(base);
        }
        Integer top = 0;
        Integer bottom = a-1;
        Integer left = 0;
        Integer right = a-1;
        Integer dir = 1;
        Integer iteration = 1;
        while(top <= bottom && left <= right) {
            if(dir == 1) {
                for(Integer i = left; i <= right;i++) {
                    result.get(top).set(i,iteration);
                    iteration++;
                }
                top++;
                dir++;
            }
            else if(dir == 2) {
                for(Integer i = top; i <= bottom; i++) {
                    result.get(i).set(right,iteration);
                    iteration++;
                }
                right--;
                dir++;
            }
            else if(dir ==3) {
                for(Integer i= right; i >= left; i--) {
                    result.get(bottom).set(i, iteration);
                    iteration++;
                }
                bottom--;
                dir++;
            }
            else if(dir == 4) {
                for(Integer i = bottom; i >= top; i--) {
                    result.get(i).set(left, iteration);
                    iteration++;
                }
                left++;
                dir = 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Main main = new Main();

        ArrayList<ArrayList<Integer>> result = main.generateMatrix(3);
        for(int i = 0; i < result.size(); i++) {
            for(int j = 0; j < result.get(i).size(); j++) {
                System.out.print(j);
            }
            System.out.print("\n");
        }
    }
}
