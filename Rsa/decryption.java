package Rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class decryption {
    public List<String> encrypt(String plainTxt) {
        Func f = new Func();
        int p =  f.PrimeNumberCreate();
        int q =  f.PrimeNumberCreate();

        int N = p*q;
        int Lclear=0;
        int NTemp = N;

        while(NTemp > 0)
        {
            NTemp /= 10;
            Lclear++;
        }

        // Key Generator
        int Fi = (p-1) * (q-1); // Φ
        int e_num = f.EulerNumberCreate(Fi);
        int d_num = f.DNumberCreate(Fi,e_num);

        System.out.println("e num : "+ e_num + "    d num: "+d_num + "    n num: "+N);
        //public key : (N,e_num)
        //private key : d_num


        String metin = plainTxt;
        ArrayList<Character> textChar = new ArrayList<>();
        ArrayList<String> textAsci = new ArrayList<>();
        for(int i=0; i<metin.length();i++){
            textChar.add(metin.charAt(i));
            int tempInt = textChar.get(i);
            String tempStr  = f.leftPad(String.valueOf(tempInt),Lclear,"0");
            textAsci.add(i,tempStr);
        }
        System.out.println("Text Char : " +textChar);
        System.out.println("Text Asci : " +textAsci);

        String lClearText="";
        for(int i=0;i<textAsci.size();i++)
            lClearText += textAsci.get(i);
        while(lClearText.length() % (Lclear-1) != 0 )
        {
            lClearText += "0";
        }
        System.out.println("L Clear Text : "+lClearText);

        ArrayList<BigInteger> LConstraintText = new ArrayList<>();
        int count=0;
        for(int i=0;i<lClearText.length();i++)
        {
            if(i%(Lclear-1)==0)
            {
                LConstraintText.add(count,new BigInteger(lClearText.substring(i,i+Lclear-1)));
                count++;
            }
        }
        System.out.println("LConst : " + LConstraintText);
        ArrayList<BigInteger> CriptText = new ArrayList<>();
        for(int i=0; i<LConstraintText.size();i++)
        {
            BigInteger b1 = new BigInteger(LConstraintText.get(i).toString());
            BigInteger b2 = BigInteger.valueOf(N);
            CriptText.add(i,(b1.pow(e_num)).mod(b2));
        }
        System.out.println("CripText:" +CriptText);

        ArrayList<String> Criptstr = new ArrayList<>() ;

        for(int i=0; i<CriptText.size();i++)
        {
            Criptstr.add(CriptText.get(i).toString());
            while(Criptstr.get(i).length() != Lclear)
            {
                String temp =Criptstr.get(i);
                Criptstr.set(i,f.leftPad(temp,Lclear,"0"));
            }
        }
        System.out.println("Cripstr:" +Criptstr);
        String EncText ="";
        for(int i=0;i<Criptstr.size();i++)
            EncText += Criptstr.get(i);

        System.out.println("Enctext = " +EncText + " L : "+EncText.length());

        /*  Decryption begins */
        ArrayList<BigInteger> DecBlock = new ArrayList<>();
        int inc=0;
        for(int i=0; i< EncText.length()+1;i++)
        {
            if(i%Lclear ==0 && i!=0)
            {
                DecBlock.add(inc,new BigInteger(EncText.substring(i-Lclear,i)));
                inc++;
            }
        }
        System.out.println("DecBlock: " + DecBlock);

        ArrayList<BigInteger> Decmod= new ArrayList<>();
        BigInteger b1 = BigInteger.valueOf(N);

        for(int i=0; i< DecBlock.size();i++)
        {
            BigInteger b2 = DecBlock.get(i).pow(d_num).mod(b1);
            Decmod.add(i,b2);
        }
        System.out.println("DecMod: "+ Decmod);

        ArrayList<String> DecModStr = new ArrayList<>();
        String temp ="",lastDance = "";
        for(int i=0; i< Decmod.size();i++)
        {
            DecModStr.add(i,String.valueOf(Decmod.get(i)));
            while(DecModStr.get(i).length() != (Lclear-1))
            {
                temp = f.leftPad(DecModStr.get(i),Lclear-1,"0");
                DecModStr.set(i,temp);
            }
            lastDance += DecModStr.get(i);
        }
        System.out.println("DecModStr: "+DecModStr);
        System.out.println("Last : " + lastDance);

        /* ASCII */
        String plainText="";
        int asci=0;
        for(int i=0; i< lastDance.length()+1;i++)
        {
            if((i % Lclear==0 && i !=0))
            {
                asci = Integer.valueOf(lastDance.substring(i-Lclear,i));
                plainText += (char)asci;
            }
        }
        System.out.println("Plain Text: " + plainText);
        List<String> result = new ArrayList<>();
        result.add(0,EncText);
        result.add(1,plainText);
        String pubKey = "Genel Anahtar: ("+N+" ,"+" "+e_num+" )";
        String privKey = "Ozel Anahtar: ("+N+" ,"+" "+d_num+" )";
        result.add(2,pubKey);
        result.add(3,privKey);
        String bobPub = ""+e_num;
        String bobPriv = ""+d_num;
        result.add(4,bobPub);
        result.add(5,bobPriv);
        System.out.println("Res : "+ result);
        return result;
    }



}
