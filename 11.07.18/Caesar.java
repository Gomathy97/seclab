import java.io.*;

public class Caesar{
	private static BufferedReader br = null;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the text to encrypt...... enter q to quit");
		String text = br.readLine();
		System.out.println("Enter the shift");
		int shift = Integer.parseInt(br.readLine());
		while(!text.equals("q")){
			System.out.println("******ENCRYPTION******");
			String encrypt = "";
			shift %= 26;
			for(int i=0; i<text.length(); i++){
				int s = text.charAt(i) + shift;
				if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z'){
					if(s > 'Z'){
						s -= 26;
					}
				}
				else if(text.charAt(i) >= 'a' && text.charAt(i) <= 'z'){
					if(s > 'z'){
						s -= 26;
					}
				}
				encrypt += (char)s;
			}
			System.out.println("Encrypted text is : " + encrypt);
			System.out.println("******DECRYPTION******");
			String decrypt = "";
			for(int i=0; i<encrypt.length(); i++){
				int s = encrypt.charAt(i) - shift;
				if(encrypt.charAt(i) >= 'A' && encrypt.charAt(i) <= 'Z'){
					if(s < 'A'){
						s += 26;
					}
				}
				else if(encrypt.charAt(i) >= 'a' && encrypt.charAt(i) <= 'z'){
					if(s < 'a'){
						s += 26;
					}
				}
				decrypt += (char)s;
			}
			System.out.println("Decrypted text is : " + decrypt + "\n");
			System.out.println("Enter the text to encrypt...... enter q to quit");
			text = br.readLine();
			if(text.equals("q")){
				break;
			}
			System.out.println("Enter the shift");
			shift = Integer.parseInt(br.readLine());
		}
	}
}