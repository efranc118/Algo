import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int queries = scan.nextInt();
        LinkedList<Integer> stack = new LinkedList<>();
        Integer max = Integer.MIN_VALUE;
        for(int i = 0; i < queries; i++) {
            int operation = scan.nextInt();
            switch(operation) {
                case 1: {
                    int temp = scan.nextInt();
                    stack.push(temp);
                    if(temp > max) {
                        max = temp;
                    }
                    break;
                }
                case 2: {
                    int temp = stack.pop();
                    if(max == temp){
                        max = stack.get(0);
                        for(int j = 1; j < stack.size();j++) {
                            if(max < stack.get(j)) {
                                max = stack.get(j);
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println(max);
                    break;
                }
            }
        }
    }
}
