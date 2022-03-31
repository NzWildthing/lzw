//Brogan Jowers-Wilding 1538252
//George Elstob 1534323

public class LZWpack{

    //Minimum number of bits required  
    private int numBits;
    private int buffer = 0; 

    public LZWpack(int currentPhraseTally){
        calcNumbits(currentPhraseTally);
    }

    public void pack(int value, int phraseNum){
        calcNumbits(phraseNum);
        //If the buffer is not empty shift to the left if it is then the first value will need to be added at the start 
        if(phraseNum != 17){   
            buffer = buffer << numBits;
        }
        //Adds the value to the buffer 
        buffer = buffer | value;

        //System.out.println(String.format("%16s", Integer.toBinaryString(buffer)).replace(' ', '0'));
    } 

    private void calcNumbits(int phraseTally){
        int k = phraseTally + 1;
        double x = Math.log(k) / Math.log(2);
        x = Math.ceil(x);
        this.numBits = (int) x;
    }
}