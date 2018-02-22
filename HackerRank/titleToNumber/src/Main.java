import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public int titleToNumber(String a) {
        HashMap<Character,Integer> decimals = new HashMap<Character,Integer>();
        Integer number = 1;
        for(Character ch = 'A'; ch <= 'Z'; ch++) {
            decimals.put(ch, number);
            number++;
        }
        int result = 0;
        int count = 0;
        String reversedOrder = new StringBuilder(a).reverse().toString();
        while(count < reversedOrder.length()) {
            Character character = reversedOrder.charAt(count);
            result += decimals.get(character) * (Math.pow(26,count));
            count++;
        }
        return result;
    }


    public static void main(String[] args) {
        Main main = new Main();
        int result = main.titleToNumber("AA");
        System.out.println(result);
    }
}
