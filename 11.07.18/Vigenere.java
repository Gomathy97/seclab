import java.io.*;
import java.util.*;

public class Vigenere{
	private static BufferedReader br = null;

	public static String generateKey(String text, String key){
		int n = text.length();
		for(int i=0; ; i++){
			int k = key.length();
			if(n == i)	i = 0;
			if(k == n) break;
			key += key.charAt(i);
		}
		return key;
	}

	public static String encryptText(String text, String key){
		String encrypt = "";
		for(int i=0; i<text.length(); i++){
			char type = 'A';
			if(text.charAt(i)>='A' && text.charAt(i)<='Z'){
				type = 'A';
			}
			else if(text.charAt(i)>='a' && text.charAt(i)<='z'){
				type = 'a';
			}
			int t = (text.charAt(i)-type + key.charAt(i)-type) % 26;
			t += type;
			encrypt += (char)t;
		}		
		return encrypt;
	}

	public static String decryptText(String text, String key){
		String decrypt = "";
		for(int i=0; i<text.length(); i++){
			char type = 'A';
			if(text.charAt(i)>='A' && text.charAt(i)<='Z'){
				type = 'A';
			}
			else if(text.charAt(i)>='a' && text.charAt(i)<='z'){
				type = 'a';
			}
			int t = (text.charAt(i) - key.charAt(i) + 26) % 26;
			t += type;
			decrypt += (char)t;
		}		
		return decrypt;
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the text to encrypt...... enter  q to quit");
		String text = br.readLine();
		System.out.println("Enter the key");
		String key = br.readLine();
		while(!text.equals("q")){
			key = generateKey(text, key);
			System.out.println("******ENCRYPTION******");
			System.out.println("New Key = " + key);
			String encrypt = encryptText(text, key);
			System.out.println("Encrypted text = " + encrypt);
			System.out.println("******DECRYPTION******");
			String decrypt = decryptText(encrypt, key);
			System.out.println("Decrypted text = " + decrypt);
			System.out.println("Enter the text to encrypt...... enter  q to quit");
			text = br.readLine();
			if(text.equals("q"))	break;
			System.out.println("Enter the key");
			key = br.readLine();
		}
	}
}