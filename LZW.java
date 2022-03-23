import java.util.Arrays;

//Brogan Jowers-Wilding 1538252
//George Elstob 

public class LZW{
    public static void main(String args[])
    {
        byte[] output = args[0].getBytes();
        System.out.println(args[0]);
        System.out.println(Arrays.toString(output));
        for (byte b : output) {
            String st = String.format("%02X", b);
            System.out.print(st);
        }
    }
}

class LZWencode{
    class Trie{
        //Initialise the trie 

        private Node root;

        public Trie(){
            //The node at the root of the trie using ` to symbolise the top of the trie
            root = new Node('`');
        }

        class Node{
            //Global variables for a node keeping track of character if it is a leaf node and its children 
            public char c;
            public boolean isLeaf;
            public Node[] children;

            //Constructor for a new node 
            public Node(char c){
                //Character of the node
                this.c = c;
                isLeaf = false;
                //number of possible children the node can have 
                children = new Node[16];
            }
        }

        //Method to add a character of string of characters into the trie
        public void add(String expression){
            Node curr = root;
        }

        //add node 
        //initial node with 16 children 
        //need to know if it is a leaf node 
        //need to have head
        //need current character 
    }
}