import javax.swing.*;
import java.awt.*;

public class HelpFunctions {	

	public Image Imagemaker(String s)// ���� �� ����� ������� ������ ������ �� ������
	{
		ImageIcon icon;
		Image i;
		icon=new ImageIcon(s);
		i=icon.getImage();
	    return i;	
	}	
	public int [] randomBool()
	{
		 int n1,n2,n3,n4;
		 int arr [] = new int [4];
		int randomnumber = (int)(Math.random()*9000) + 1000;
		n1=randomnumber%10; //n1 = �����
		n2=randomnumber/10%10; //n2 = �����
		n3=randomnumber/100%10; //n3 = ����
		n4=randomnumber/1000; //n4 = �����
		while(!(n1!=n2&&n1!=n3&&n1!=n4&&n2!=n3&&n2!=n4&&n3!=n4)) // ����� ����� ����� ����� ��������
		{
	
			randomnumber = (int)(Math.random()*9000) + 1000; 
	 
	    n1=randomnumber%10; //n1 = �����
		n2=randomnumber/10%10; //n2 = �����
		n3=randomnumber/100%10; //n3 = ����
		n4=randomnumber/1000; //n4 = �����
		}
		arr[0]=n1;
		arr[1]=n2;
		arr[2]=n3;
		arr[3]=n4;
		
		return arr;
	}
	public int checkBools(Ball arr [],Ball arr2 [])
	{
		int n1=0,n2=0,n3=0,n4=0,number1=0,number2=0,number3=0,number4=0;
		if(arr[0]!=null)n1=arr[0].getNumber();
		if(arr[1]!=null)n2=arr[1].getNumber();
		if(arr[2]!=null)n3=arr[2].getNumber();
		if(arr[3]!=null)n4=arr[3].getNumber();
		if(arr2[0]!=null)number1=arr2[0].getNumber();
		if(arr2[1]!=null)number2=arr2[1].getNumber();
		if(arr2[2]!=null)number3=arr2[2].getNumber();
		if(arr2[3]!=null)number4=arr2[3].getNumber();
	
		int countbool=0;
			
		if(number1==n1)
			countbool++;
		
		if(number2==n2)
			countbool++;

		
		if(number3==n3)
			countbool++;
	
		
		if(number4==n4)
			countbool++;
	
		return countbool;
		
	}
	public boolean checkNumbers(Ball arr [])
	{
		int n1=0,n2=0,n3=0,n4=0;
		if(arr[0]!=null)n1=arr[0].getNumber();
		if(arr[1]!=null)n2=arr[1].getNumber();
		if(arr[2]!=null)n3=arr[2].getNumber();
		if(arr[3]!=null)n4=arr[3].getNumber();

		if(n1==10)
			n1=-1;
		if(n2==10)
			n2=-2;
		if(n3==10)
			n3=-3;
		if(n4==10)
			n4=-4;
		if((n1!=n2&&n1!=n3&&n1!=n4&&n2!=n3&&n2!=n4&&n3!=n4))
			return true;
		else
			return false;
		
	}
	public void Back(Ball arr [])
	{
		int flag=0;
		for(int i=3;i>=0&&flag==0;i--)
		{
			if(arr[i]!=null)
			{
				if(arr[i].getNumber()!=10)
				{
					arr[i].setNumber(10);
					flag=1;
				}
			}
		}
	}
	public int checkHits(Ball arr [],Ball arr2 [])
	{
		int n1=0,n2=0,n3=0,n4=0,number1=0,number2=0,number3=0,number4=0;
		if(arr[0]!=null)n1=arr[0].getNumber();
		if(arr[1]!=null)n2=arr[1].getNumber();
		if(arr[2]!=null)n3=arr[2].getNumber();
		if(arr[3]!=null)n4=arr[3].getNumber();
		if(arr2[0]!=null)number1=arr2[0].getNumber();
		if(arr2[1]!=null)number2=arr2[1].getNumber();
		if(arr2[2]!=null)number3=arr2[2].getNumber();
		if(arr2[3]!=null)number4=arr2[3].getNumber();
		int counthit=0;
		
		if((number1==n1||number1==n2||number1==n3||number1==n4)||(number2==n1||number2==n2||number2==n3||number2==n4)||(number3==n1||number3==n2||number3==n3||number3==n4)||(number4==n1||number4==n2||number4==n3||number4==n4)) // ����� �� ����� ��� �����
		{
			

		 if(number1==n2||number1==n3||number1==n4)
			counthit++;
		
		 if(number2==n1||number2==n3||number2==n4)
			counthit++;
		
		 if(number3==n1||number3==n2||number3==n4)
			counthit++;
		
		 if(number4==n1||number4==n2||number4==n3)
			counthit++;
		
		}
		return counthit;
		
	}

	
	
}
