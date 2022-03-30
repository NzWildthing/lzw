import java.util.ArrayList;
import java.util.List;

public class LZWpack{

    //Minimum number of bits required  
    private int numBits;
    private int buffer = 1; 

    public LZWpack(int currentPhraseTally){
        int k = currentPhraseTally + 1;
        double x = Math.log(k) / Math.log(2);
        this.numBits = (int) x;
        System.out.println("");
        System.out.println(Math.ceil(x));
    }

    public void pack(int value, int phraseNum){
        value = value << numBits + 1;
        buffer = buffer | value;
        System.out.println(Integer.toBinaryString(buffer));
        System.out.println(Integer.toBinaryString(value));
    } 
}