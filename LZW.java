import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.*;

//Brogan Jowers-Wilding 1538252
//George Elstob 1534323


public class LZW{

    public static void main(String args[])
    {
        
        byte[] output = args[0].getBytes();
        
        System.out.println(args[0]);
        System.out.println(Arrays.toString(output));
        List<Integer> encoded =  new ArrayList<>();
        String expression = "";
        String decoded = "";
        for (byte b : output) {
            String st = String.format("%02X", b);
            System.out.print(st);
            expression = expression + st;
        } 
        LZWencode encode = new LZWencode();
        encoded = encode.encodeMessage(expression);
       
        LZWdecode decode = new LZWdecode();
        decoded = LZWdecode.decode(encoded);
        System.out.println("Original expression: " + expression);
        System.out.println("Decoded expression: " + decoded);

    }
}

class LZWdecode{
    public static String decode(List<Integer> encoded) {{

        char value = 'A';
        int size = 16;
        //creates a hash map dictionary with keys as integers and characters as strings
        Map<Integer,String> dictionary = new HashMap<>();
        

        //fills dictionary
        for (int i = 0; i < 16; i++){
            //the 6 letters added to the trie
            if(i>9){
                dictionary.put(i, Character.toString(value));
                value += 1;
            }
            else{
                //the nine numbers added to the trie
                dictionary.put(i, "" + i);
            }    
        }
        //System.out.println(Arrays.asList(dictionary));
        //retrieve and remove 1st integer
        //encoded.add(0, 0);
        String characters = String.valueOf((char) encoded.remove(0).intValue());
        
        StringBuilder message = new StringBuilder(characters);

        //for each digit in encoded
        for (int digit : encoded) {
            // 
            String entry = dictionary.containsKey(digit) ? dictionary.get(digit) : characters + characters.charAt(0);

            message.append(entry);
            //adds new item to dictionary 
            dictionary.put(size++, characters + entry.charAt(0));
            characters = entry;
            //System.out.println(message.toString());
        }
        //prints decoded message
        //System.out.println(message.toString());
        return message.toString();
    }
}
}