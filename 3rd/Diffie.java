import java.io.*;
import java.math.BigInteger;
public class Diffie
{
    public static void main(String[]args)throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter prime number P:");
        BigInteger p=new BigInteger(br.readLine());
        System.out.print("Enter prime number G:");
        BigInteger g=new BigInteger(br.readLine());
        System.out.println("Enter value x:");
        BigInteger x=new BigInteger(br.readLine());
        BigInteger R1=g.modPow(x,p);
        System.out.println("R1="+R1);
        System.out.print("Enter value y:");
        BigInteger y=new BigInteger(br.readLine());
        BigInteger R2=g.modPow(y,p);
        System.out.println("R2="+R2);
        BigInteger k1=R2.modPow(x,p);
        System.out.println("Key calculated at Sender side:"+k1);
        BigInteger k2=R1.modPow(y,p);
        System.out.println("Key calculated at Receiver side:"+k2);
    }
}