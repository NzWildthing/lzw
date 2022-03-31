import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Brogan Jowers-Wilding 1538252
//George Elstob 1534323


public class LZW{

    public static void main(String args[])
    {
        
        byte[] output = args[0].getBytes();
        
        System.out.println(args[0]);

        List<Integer> encoded =  new ArrayList<>();
        String expression = "";
        String decoded = "";
        for (byte b : output) {
            String st = String.format("%02X", b);
            expression = expression + st;
        } 

        LZWencode encode = new LZWencode();
        encoded = encode.encodeMessage(expression);
       
        LZWdecode decode = new LZWdecode();
        decoded = decode.decode(encoded);

        System.out.println("Original expression: " + expression);
        //Prints out the encoded message i.e phrase number list
        System.out.println("Encoded " + encoded.toString());
        System.out.println("Decoded expression: " + decoded);

    }
}