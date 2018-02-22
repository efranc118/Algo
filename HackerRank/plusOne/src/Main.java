import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
        Collections.reverse(a);

        int carryover = 1;
        for(int i =0; i < a.size(); i++) {
            int value = a.get(i);
            if(carryover == 1) {
                value = value + 1;
                carryover = 0;
            }
            if(value > 10) {
                carryover = 1;
            }
            value = value % 10;
            a.set(i, value);
        }

        Collections.reverse(a);
        return a;
    }

    public static void main(String[] args) {
        Main main = new Main();
        ArrayList<Integer> test = new ArrayList<>();
        test.add(0);
//        test.add(1);
//        test.add(1);
//        test.add(3);
//        test.add(2);
//        test.add(1);
//        test.add(1);
//        test.add(2);
//        test.add(5);
//        test.add(9);
//        test.add(6);
//        test.add(5);

        ArrayList<Integer> result = main.plusOne(test);

    }
}
