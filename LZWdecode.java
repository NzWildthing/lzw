import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LZWdecode{
    public String decode(List<Integer> encoded) {{

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
        //retrieve and remove 1st integer
        String characters = Integer.toString(encoded.remove(0));
        StringBuilder message = new StringBuilder(characters);

        //for each digit in encoded
        for (int digit : encoded) {
            // retirve from dictionary if possible 
            String entry = "";
            if ( dictionary.containsKey(digit)){
                entry = dictionary.get(digit);
            }
            //else add the first letter of characters to characters and use that instead
            else{
                entry = characters + characters.charAt(0);
            }
               
            //adds to end of decoded string
            message.append(entry);
            //adds new item to dictionary 
            dictionary.put(size++, characters + entry.charAt(0));
            characters = entry;
        }
        //prints decoded message
        return message.toString();
    }
}
}
