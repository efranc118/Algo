public class Main {

    static class Node {
        int data;
        Node left;
        Node right;
    }



    static Node Insert(Node root,int value)
    {
        if(root == null) {
            Node newNode = new Node();
            newNode.data = value;
            return newNode;
        }
        System.out.println(root.data);

        if(root.data > value) {
            Insert(root.left, value);
        }
        else {
            Insert(root.right, value);
        }


        return root;


    }

    public static void main(String[] args) {
        Main main = new Main();
        Node node = new Node();

        node.data = 4;
        node.right = new Node();
        node.left = new Node();
        Node temp = node.left;
        temp.data = 2;
        temp.left = new Node();
        temp.right = new Node();
        temp.left.data = 1;
        temp.right.data = 3;
        temp = node;
        temp.right.data = 7;

        Node result = Insert(node, 6);
        System.out.print(result.right.left.data);
    }
}
