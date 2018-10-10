import java.io.*;
import java.util.*;

public class Hill{
	private static BufferedReader br = null;

	public static String mult(double[][] mat, double[] a, int n){
		String encrypt = "";
		for(int i=0; i<n; i++){
			int sum = 0;
			for(int j=0; j<n; j++){
				sum += a[j] * mat[i][j];
			}
			sum = (sum % 26) + 'a';
			encrypt += (char)sum;
		}
		return encrypt;
	}

	public static double[][] matCreate(String key, int n){
		double[][] mat = new double[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				mat[i][j] = (key.charAt((i*n)+j) - 'a') % 26 ;
			}
		}
		return mat;
	}

	public static double[][] getCofactor(double[][] mat, int p, int q, int n){
		double temp[][] = new double[n][n];
		int i = 0, j = 0;
 	    for (int row = 0; row < n; row++){
	        for (int col = 0; col < n; col++){
	            if (row != p && col != q){
	                temp[i][j++] = mat[row][col];
	                if (j == n - 1){
	                    j = 0;
	                    i++;
	                }
	            }
	        }
	    }
	    return temp;
	}

	public static double determinant(double[][] mat, int n){
		int d = 0;
		if(n == 1)	return mat[0][0];
	    double temp[][] = new double[n][n];
	    int sign = 1;  
	    for (int f = 0; f < n; f++){
	        temp = getCofactor(mat, 0, f, n);
	        d += sign * mat[0][f] * determinant(temp, n - 1);
	        sign = -sign;
	    }
	    return d;
	}

	public static double[][] adjoint(double[][] mat, int n){
		double adj[][] = new double[n][n];
		if (n == 1){
	        adj[0][0] = 1;
	        return adj;
	    }
	    int sign = 1;
	    double temp[][] = new double[n][n];
	    for (int i=0; i<n; i++){
	        for (int j=0; j<n; j++){
	            temp = getCofactor(mat, i, j, n);
	            sign = ((i+j)%2==0)? 1: -1;
	            adj[j][i] = (sign)*(determinant(temp, n-1));
	        }
	    }
	    return adj;
	}

	public static double[][] inverse(double[][] mat, int n){
		double imat[][] = new double[n][n];
		double det = determinant(mat, n); 
		double[][] adj = adjoint(mat, n);
	    for (int i=0; i<n; i++)
	        for (int j=0; j<n; j++){
	            imat[i][j] = (adj[i][j]) % 26;
	            if(imat[i][j] < 0){
	            	imat[i][j] += 26;
	            }
	        }
	       return imat;
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the text to encrypt...... enter q to quit");
		String text = br.readLine();
		System.out.println("Enter the key");
		String key = br.readLine();
		while(!text.equals("q")){
			System.out.println("******ENCRYPTION******");
			double[][] mat = matCreate(key, text.length());
			double[] a = new double[text.length()];
			for(int i=0; i<text.length(); i++){
				a[i] = (text.charAt(i) - 'a') % 26;
			}
			for(int i=0; i<text.length(); i++){
				for(int j=0; j<text.length(); j++){
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}
			String encrypt = mult(mat, a, text.length());
			System.out.println("Encrypted text = " + encrypt);
			System.out.println("*****DECRYPTION*****");
			mat = inverse(mat, encrypt.length());
			for(int i=0; i<text.length(); i++){
				for(int j=0; j<text.length(); j++){
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}
			String decrypt = mult(mat, a, text.length());
			System.out.println("Decrypted text = " + decrypt);
			System.out.println("Enter the text to encrypt...... enter q to quit");
			text = br.readLine();
			if(text.equals("q")){
				break;
			}
			System.out.println("Enter the shift");
			key = br.readLine();
		}
	}
}