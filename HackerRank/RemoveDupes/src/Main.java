import java.util.Scanner;

public class Main {

    public static String findDupes(String input) {
        String result = "";
        if(input.length() == 0) return result;
        int count = 1;
        char prev = input.charAt(0);
        for(int i = 1; i < input.length(); i++) {
            char cur = input.charAt(i);
            if(prev == cur) {
                count++;
            }
            else {
                if((count % 2) == 1) {
                    result += prev;
                }
                count = 1;
                prev = cur;
            }
        }
        if((count % 2) == 1) {
            result += prev;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        int map = 0;
//        while(scan.nextLine()) {
            input += scan.nextLine();
//        }
        input = input.toLowerCase();
        input = input.replaceAll("\\s","");
        for(int i = 0; i < input.length(); i++) {
            map = (map | (1 << (input.charAt(i) - 'a'))) ;
        }
        if(map == (1 << 26) - 1) System.out.println("pangram");
        else System.out.println("not pangram");
    }
}
