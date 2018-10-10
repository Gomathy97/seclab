import java.io.*;
import java.util.*;
public class BellBiba
{
	public int number;
	public String[] user;
	public int[] prior,stored;
	public String[][] written;
	public BellBiba(int number)
	{
		this.number=number;
		user=new String[number];
		prior=new int[number];
		written=new String[number][10];
		stored=new int[number];
	}
	public void BellLaPadula()
	{
		System.out.println("Bell LaPadula\nFiles which a user can read");
		for(int i=0;i<number;i++)
		{
			System.out.println(user[i]);
			for(int j=prior[i]+1;j<number;j++)
			{
				int l=0;
				for(int ii=0;ii<number;ii++)
				{
					if(prior[ii]==j)
					{
						l=ii;
						break;
					}
				}
				for(int k=0;k<stored[l];k++){
					System.out.print(written[l][k] + ", ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("Users and their accepted receipients");
		for(int i=0;i<number;i++){
			System.out.print(user[i] + "-->");
			for(int j=prior[i]-1;j>=0;j--){
				int l=0;
				for(int ii=0;ii<number;ii++)				{
					if(prior[ii]==j)					{
						l=ii;
						break;
					}
				}
				System.out.print(user[l] + ", ");
			}
			System.out.println();
		}
	}
	public void Biba(){
		System.out.println("Biba Model\nFiles which a user can read");
		for(int i=0;i<number;i++){
			System.out.print(user[i] + "-->");
			for(int j=prior[i]-1;j>=0;j--){
				int l=0;
				for(int ii=0;ii<number;ii++){
					if(prior[ii]==j){
						l=ii;
						break;
					}
				}
				for(int k=0;k<stored[l];k++){
					System.out.print(written[l][k] + ", ");
				}
			}
			System.out.println();
		}
		System.out.println("Users and their accepted receipients");
		for(int i=0;i<number;i++){
			System.out.print(user[i] + "-->");
			for(int j=prior[i]+1;j<number;j++){
				int l=0;
				for(int ii=0;ii<number;ii++){
					if(prior[ii]==j){
						l=ii;
						break;
					}
				}
				System.out.print(user[l] + ", ");
			}
			System.out.println();
		}
	}
	public static void main(String[] argv) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of users");
		int n = Integer.parseInt(br.readLine());
		BellBiba bb=new BellBiba(n);
		System.out.println("Enter the user names");
		for(int i=0; i<n; i++){
			bb.user[i]=br.readLine();	
		}
		System.out.println("Enter the user prior");
		for(int i=0; i<n; i++){
			bb.prior[i]=Integer.parseInt(br.readLine());	
		}
		System.out.println("Enter the sub-user");
		for(int i=0; i<n; i++){
			bb.stored[i]=Integer.parseInt(br.readLine());	
		}
		bb.written[0][0]="Article1.pdf";
		bb.written[0][1]="Article2.pdf";
		bb.written[1][0]="Article3.docx";
		bb.written[1][1]="Article4.pdf";
		bb.written[1][2]="Article5.pdf";
		bb.written[2][0]="Article6.pdf";
		bb.written[3][0]="Article7.docx";
		bb.written[3][1]="Article8.rtf";
		bb.written[3][2]="Article9.pdf";
		bb.written[4][0]="Article10.pdf";
		bb.written[4][1]="Article11.pdf";
		bb.BellLaPadula();
		bb.Biba();
	}
}