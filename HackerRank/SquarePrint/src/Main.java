import java.util.ArrayList;

public class Main {

    public ArrayList<ArrayList<Integer>> prettyPrint(int a) {
        int iteration = 1;
        ArrayList<ArrayList<Integer>> lists = new ArrayList();
        while(iteration <= a) {
            ArrayList<Integer> beginList = new ArrayList();
            ArrayList<Integer> endList = new ArrayList();
            for(int i =1; i <= (iteration*2) - 1; i++) {
                beginList.add(iteration);
                endList.add(iteration);
            }
            for(ArrayList<Integer> list: lists ) {
                list.add(iteration);
                list.add(0, iteration);
            }
            if(iteration == 1) {
                lists.add(beginList);
            }
            else {
                lists.add(0, beginList);
                lists.add(endList);
            }
            iteration++;
        }
        return lists;
    }

    public static void main(String[] args) {
        Main main = new Main();

        ArrayList<ArrayList<Integer>>result = main.prettyPrint(4);
        for(ArrayList<Integer> list: result) {
            for(Integer integer: list) {
                System.out.print(integer);
            }
            System.out.print("\n");
        }
    }
}
