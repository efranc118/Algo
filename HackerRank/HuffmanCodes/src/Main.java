public class Main {

    static class Node {
        int frequency;
        char data;
        Node left, right;
    }

    void decode(String S ,Node root)
    {
        String result = "";
        Node tempNode = root;
        for(int i = 0; i < S.length(); i++) {
            char temp = S.charAt(i);
            if(temp == '1') {
                tempNode = tempNode.right;
            }
            else if(temp == '0') {
                tempNode = tempNode.left;
            }
            if(tempNode.left == null && tempNode.right == null) {
                result = result + tempNode.data;
                tempNode = root;
            }
        }

        System.out.println(result);


    }


    public static void main(String[] args) {

        Node root = new Node();
        root.frequency = 5;
        root.left = new Node();
        root.right = new Node();
        root.right.data = 'A';
        root.left.right = new Node();
        root.left.left = new Node();
        root.left.left.data = 'B';
        root.left.right.data = 'C';
        String test = "1001011";
        Main main = new Main();
        main.decode(test, root);
    }
}
