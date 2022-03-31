import java.util.ArrayList;
import java.util.List;

class LZWencode{

    private Trie trie;
    private List<Integer> phraseNumList;

    public LZWencode(){
        //Output phrase numbers list 
        phraseNumList = new ArrayList<Integer>();
        //Initialises a trie with the 16 initial values 
        trie = new Trie();
        char value = 'A';
        for(int i = 0; i < 16; i++){
            //the 6 letters added to the trie
            if(i>9){
                trie.add(Character.toString(value));
                value += 1;
            }
            else{
                //the nine numbers added to the trie
                trie.add(Integer.toString((i)));
            }
        }
    }


    //Check if the value to encode is in the trie using a search function 
    //if it is move onto next character if not then output current phrase number and input phrase into trie for use later
    //then move pointer along to next character and repeat
    public List<Integer> encodeMessage(String message){ 
        LZWpack packer = new LZWpack(trie.globalPhraseCount);
        //Adds a character to the end of the message to represent the end of line character so that the last character is still encoded 
        message = message + '/';
        //Buffer to keep track of characters that have been seen before if necessary
        String buffer = "";
        //Loops through each character in the ascii message 
        for(char c : message.toCharArray()){
            String srchPhrase = buffer + c;
            if(trie.find(srchPhrase)){
                buffer = srchPhrase;
            }
            else{
                //Adds the entry to the trie and adds the phrase number returned to our encoded output list 
                int phraseNum = trie.add(srchPhrase);
                //Moves to next character to encode
                buffer = Character.toString(c);
                //Packs the phrase number up
                packer.pack(phraseNum, trie.globalPhraseCount);
                //Adds the phrase number to the list
                phraseNumList.add(phraseNum);
            }
        }
        return phraseNumList;
    }

    class Trie{
        //Initialise the trie 

        private Node root;
        private int globalPhraseCount = 0;

        public Trie(){
            //The node at the root of the trie using ` to symbolise the top of the trie
            root = new Node('`');
        }

        class Node{
            //Global variables for a node keeping track of character if it is a leaf node and its children 
            public char c;
            public boolean isLeaf;
            public Node[] children;
            public int phraseNumber;

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
        public int add(String expression){
            Node curr = root;
            //Loops through the given expression
            for(int i = 0; i < expression.length(); i++){
                //Gets the current character
                char c = expression.charAt(i);
                //Used to check how much to offset children array by based on ASCII char value
                boolean isLetter = Character.isLetter(c);
                if(isLetter){
                    //Creates a new node if one is not currently there 
                    if(curr.children[c-'A'] == null){
                        curr.children[c-'A'] = new Node(c);
                        //Assigns the node its phrase number and increments the global count
                        curr.children[c-'A'].phraseNumber = globalPhraseCount;
                        globalPhraseCount++;
                        //If something is added it must be a leaf node 
                        curr.isLeaf = true;
                        //Returns the previous phrase number to add to the encode array 
                        return curr.phraseNumber;
                    }
                    //**********If added new node current won't increase therefore can't add multiple nodes at a time *****
                    else{
                        //Otherwise moves onto the next character in the expression
                        curr = curr.children[c-'A'];
                    }
                }
                else{
                    if(curr.children[c-42] == null){
                        curr.children[c-42] = new Node(c);
                        //Assigns the node its phrase number and increments the global count
                        curr.children[c-42].phraseNumber = globalPhraseCount;
                        globalPhraseCount++;
                        //Sets the last node as a leaf node
                        curr.isLeaf = true;
                        //Returns the previous phrase number to add to the encode array 
                        return curr.phraseNumber;
                    }
                    else{
                        //Otherwise moves onto the next character in the expression
                        curr = curr.children[c-42];
                    }
                }
            }
            //If the item is already in the trie we return a phrase number of -1 as nothing has been added 
            return -1;
        }

        //Finds if an expression is currently in the trie or not 
        public boolean find(String expression){
            Node curr = root;
            for(int i = 0; i< expression.length(); i++){
                char c = expression.charAt(i);
                //Used to check how much to offset children array by based on ASCII char value
                boolean isLetter = Character.isLetter(c);//if the node is not there then the expression is not in the trie
                if(isLetter){ 
                    if(curr.children[c-'A'] == null)return false;
                    curr = curr.children[c-'A'];
                }
                else{
                    if(curr.children[c-42] == null) return false;
                    //If the character does exist in the trie continue onto the next node 
                    curr = curr.children[c-42];
                }
            }
            return true;
        }
    }
}

