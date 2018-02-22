public class Main {

    public static int isPalindrome(String a) {
        a = a.replaceAll("[^a-zA-Z0-9]+", "");
        a = a.trim();
        a = a.toLowerCase();
        int start = 0;
        int end = a.length() - 1;
        while(start < end) {
            if(a.charAt(start) != a.charAt(end)) return 0;
            start++;
            end--;
        }
        return 1;
    }

    public static void main(String[] args) {
        isPalindrome("1a2");
    }
}
