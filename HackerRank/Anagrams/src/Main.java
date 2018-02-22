import java.util.ArrayList;
import java.util.List;

public class Main {

    List<String> createPermutations(List<String> words, String letters, String currentWord ) {
        for(int i = 0; i < letters.length();i++ ) {
            if(letters.length() > 1) {
                createPermutations(words, letters.substring(0, i) + letters.substring(i + 1, letters.length()), currentWord + letters.charAt(i));
            }
            else if(letters.length() == 1) {
                words.add(currentWord + letters);
            }

        }
        return words;
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<String> results = main.createPermutations(new ArrayList<>(), "abcd", "");
        for(String result : results) {
            System.out.println(result);
        }

    }
}
