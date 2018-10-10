import java.io.*;
import java.util.*;

public class RailFence{
	private static BufferedReader br = null;

	public static char[][] matCreate(String text, int key){
		int n = text.length();
		char[][] mat = new char[key][n];
		int factor = 1;
		for(int i=0, j=0, k=0; k<n; k++){
			mat[i][j] = text.charAt(k);
			i += factor;
			j++;
			if(i == key || i < 0){
				factor *= -1;
				i += factor;
				i += factor;
			}
		}
		return mat;
	}

	public static char[][] deMatCreate(String text, int key){
		int n = text.length();
		char[][] mat = new char[key][n];
		int factor = 1;
		for(int i=0, j=0, k=0; k<n; k++){
			mat[i][j] = '*';
			i += factor;
			j++;
			if(i == key || i < 0){
				factor *= -1;
				i += factor;
				i += factor;
			}
		}
		int k =0;
		for(int i=0; i<key; i++){
			for(int j=0; j<text.length(); j++){
				if(mat[i][j] == '*'){
					mat[i][j] = text.charAt(k++);
				}
			}
		}
		return mat;
	}

	public static String decryptText(String text, int key){
		char[][] mat = new char[key][text.length()];
		int z = 0;
		mat = deMatCreate(text, key);
		for(int i=0; i<key; i++){
			for(int j=0; j<text.length(); j++){
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		String decrypt = "";
		for(int j=0; j<text.length(); j++){
			for (int i=0; i<key; i++)
				if(mat[i][j] != 0)
					decrypt += mat[i][j];
		}
		return decrypt;
	}
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the text to encrypt...... enter q to quit");
		String text = br.readLine();
		System.out.println("Enter the key");
		int key = Integer.parseInt(br.readLine());
		while(!text.equals("q")){
			System.out.println("******ENCRYPTION******");
			char[][] mat = matCreate(text, key);
			for(int i=0; i<key; i++){
				for(int j=0; j<text.length(); j++){
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}
			String encrypt = "";
			for(int i=0; i<key; i++){
				for (int j=0; j<text.length() ; j++)
					if(mat[i][j] != 0)
						encrypt += mat[i][j];
			}
			System.out.println("Encrypted text = " + encrypt);
			System.out.println("******DECRYPTION******");
			System.out.println("Decypted Text = " + decryptText(encrypt, key));
			System.out.println("Enter the text to encrypt...... enter q to quit");
			text = br.readLine();
			if(text.equals("q")){
				break;
			}
			System.out.println("Enter the shift");
			key = Integer.parseInt(br.readLine());
		}
	}
}