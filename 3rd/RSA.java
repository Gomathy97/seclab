import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class RSA{
	private static BufferedReader br = null;

	public static double gcd(double a, double h){
	    double temp;
	    while (true){
	        temp = a%h;
	        if (temp == 0)
	          return h;
	        a = h;
	        h = temp;
	    }
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		double p, q;
		System.out.println("Enten the prime number P");
		p = Double.parseDouble(br.readLine());
		System.out.println("Enten the prime number Q");
		q = Double.parseDouble(br.readLine());
		double n = p*q;
		int k = 2;
		double e = 2;
		double phi = (p-1)*(q-1);
		while(e < phi){
			if(gcd(e, phi) == 1)
				break;
			else e++;
		}
		double d = (k*phi+1)/e;
		System.out.println("Enter the message");
		double msg = Double.parseDouble(br.readLine());
		double encrypt = Math.pow(msg, e) % n;
		System.out.println("d = " + d + " Encrypted message = " + encrypt);
		double decrypt = 1;
		while(d > 0){
			decrypt *= encrypt % n;
			decrypt %= n;
			d--;
		}
		System.out.println("Decrypted message = " + decrypt);
	}
}