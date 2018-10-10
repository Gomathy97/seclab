import java.io.*;
import java.util.*;

public class PlayFair{
	private static BufferedReader br = null;

	public static char[][] matCreate(String key){
		HashMap<Character, Integer> m = new HashMap<Character, Integer>();
		char[][] mat = new char[5][5];
		for(char i='a'; i<='z'; i++){
				m.put(i, 1);
			}
			m.put('j', 0);
			int row = 0, col = 0;
			for(int i=0; i<key.length(); i++){
				if(m.get(key.charAt(i)) == null)	continue;
				if(m.get(key.charAt(i)) == 1){
					m.put(key.charAt(i), 0);
					if(col == 5){
						col = 0;
						row++;
					}	
					mat[row][col] = key.charAt(i);
					col++;
				}
			}
			char alpha = 'a';
			if(col < 5){
				for(int j=col; j<5; j++){
					if(m.get(alpha) == 1){
						mat[row][j] = alpha;
						m.put(alpha, 0);
					}
					alpha++;
				}
				row++;
			}
			for(int i=row; i<5; i++){
				for(int j=0; j<5; j++){
					if(m.get(alpha) == 1){
						mat[i][j] = alpha;
						m.put(alpha, 0);
					}
					else{
						while(m.get(alpha) == 0 && alpha <= 'z'){
							alpha++;
						}
						mat[i][j] = alpha;
						m.put(alpha, 0);
					}
					alpha++;
				}
			}
			return mat;
	}

	public static int[] find(char c, char[][] mat){
		int[] a = new int[2];
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(mat[i][j] == c){
					a[0] = i;
					a[1] = j;
					return a; 
				}
			}
		}
		return a;
	}

	public static String encryptText(String t, char[][] mat){
		int[] a = find(t.charAt(0), mat);
		int[] b = find(t.charAt(1), mat);
		String s = "";
		if(a[0] == b[0]){
			s += mat[a[0]][(a[1]+1)%5];
			s += mat[b[0]][(b[1]+1)%5];
		}
		else if(a[1] == b[1]){
			s += mat[(a[0]+1)%5][a[1]];
			s += mat[(b[0]+1)%5][b[1]];
		}
		else{
			s += mat[a[0]][b[1]];
			s += mat[b[0]][a[1]];
		}
		return s;
	}

	public static String decryptText(String t, char[][] mat){
		int[] a = find(t.charAt(0), mat);
		int[] b = find(t.charAt(1), mat);
		String s = "";
		if(a[0] == b[0]){
			int c = a[1]-1;
			if(c < 0){
				c += 5;
			}
			s += mat[a[0]][c%5];
			c = b[1]-1;
			if(c < 0){
				c += 5;
			}
			s += mat[b[0]][c%5];
		}
		else if(a[1] == b[1]){
			int r = a[0]-1;
			if(r < 0){
				r += 5;
			}
			s += mat[r%5][a[1]];
			r = b[0]-1;
			if(r < 0){
				r += 5;
			}
			s += mat[r%5][b[1]];
		}
		else{
			s += mat[a[0]][b[1]];
			s += mat[b[0]][a[1]];
		}
		return s;
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the text to encrypt...... enter q to quit");
		String text = br.readLine();
		System.out.println("Enter the key");
		String key = br.readLine();
		while(!text.equals("q")){
			System.out.println("******ENCRYPTION******");
			char[][] mat = matCreate(key);
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}
			if(text.length()%2 == 1)	text += "z";
			String[] tokens = new String[text.length()/2];
			for(int i=0, j=0; i<text.length(); i++, j++){
				if(text.charAt(i) == ' ')	i++;
				tokens[j] = String.valueOf(text.charAt(i));
				i++;
				if(text.charAt(i) == ' ')	i++;
				tokens[j] += String.valueOf(text.charAt(i));
			}
			String encrypt = "";
			for(int i=0; i<text.length()/2; i++){
				encrypt += encryptText(tokens[i], mat);
			}
			System.out.println("Encrypted text = " + encrypt);
			System.out.println("******DECRYPTION******");
			tokens = new String[encrypt.length()/2];
			for(int i=0,j=0; i<encrypt.length(); i++, j++){
				tokens[j] = String.valueOf(encrypt.charAt(i));
				i++;
				tokens[j] += String.valueOf(encrypt.charAt(i));
			}
			String decrypt = "";
			for(int i=0; i<encrypt.length()/2; i++){
				decrypt += decryptText(tokens[i], mat);
			}
			System.out.println("Decrypted text = " + decrypt + "\n");
			System.out.println("Enter the text to encrypt...... enter q to quit");
			text = br.readLine();
			if(text.equals("q")){
				break;
			}
			System.out.println("Enter the key");
			key = br.readLine();
		}
	}
}