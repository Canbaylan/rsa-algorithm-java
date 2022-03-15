package Rsa;

import java.math.BigInteger;
import java.util.Random;

public class Func {
    Random rand = new Random();
    public int PrimeNumberCreate() {
        int i = 1;
        int num = 0, count;
        while (i > 0) {
            count = 0;
            num = 30 + rand.nextInt(200);
            for (int j = 2; j < num; j++) {
                if (num % j == 0)
                    count++;
            }
            if (count == 0)
                i--;
        }
        return num;
    }

    public int EulerNumberCreate(int fi)
    {
        BigInteger e_num = BigInteger.valueOf(rand.nextInt(fi)+2);
        BigInteger bigFi = BigInteger.valueOf(fi);
        BigInteger gcd = e_num.gcd(bigFi);

        while(gcd.intValue() != 1)
        {
            e_num = BigInteger.valueOf(rand.nextInt(fi)+2);
            gcd = e_num.gcd(bigFi);
        }
        return e_num.intValue();
    }
    public int DNumberCreate(int fi,int e)
    {
        int d = 2;
        while( (e*d) % fi != 1)
        {
            d = rand.nextInt(fi)+2;
        }
        return d;
    }
    public String leftPad(String input, int length, String fill){
        String pad = String.format("%"+length+"s", "").replace(" ", fill) + input.trim();
        return pad.substring(pad.length() - length, pad.length());
    }
}