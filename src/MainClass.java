import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainClass extends JFrame implements KeyListener,MouseListener,Runnable{ 

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	Ball Ballarray[][] = new Ball[15][4];
	Ball BallCircle[][] = new Ball[15][4];
	public int win=0;
	BoolHitIcon boolhit[][] = new BoolHitIcon[14][4];
	int Line=1;
	int lose=0;
	int number=10;
	private Image img;//����� �� ����
	Graphics offgc;
	Image offscreen = null;
	int keypress=0;// 0=stand, 1=right, 2=left, 3=shoot.
	public int windowWidth=500;
	public int windowHeight=1000;
	private boolean flag=true;
	public int ArrRandom [];
	Ball b;
	 HelpFunctions h = new HelpFunctions();
	HelpFunctions help= new HelpFunctions();
	
	public static void main(String[] args) {
		
		MainClass world = new MainClass ();
		world.setVisible(true);
	}

	private MainClass () { 
	
		setSize (windowWidth, windowHeight);
		this.setLocation(700, 15);
		this.setVisible(true);
		offscreen=this.createImage(this.getWidth(),this.getHeight());
		offgc = offscreen.getGraphics();
		img=help.Imagemaker("images/background.png");
		
		int dx=70,dy=50,x=26,y=817;
		ArrRandom = h.randomBool();
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(i==0)
				{
				Ballarray[i][j] =new Ball(x,y,35,45,ArrRandom[j]);
				}
				else
					Ballarray[i][j] =new Ball(x,y,35,45,10);
				x+=dx;
			}
			x=26;
			y-=dy;
		}
		int dx2=24,dy2=50,x2=359,y2=785;
		
		for(int i=0;i<14;i++)
		{
			for(int j=0;j<4;j++)
			{
				boolhit[i][j] =new BoolHitIcon(x2,y2,15,17,"black");
				x2+=dx2;
			}
			x2=359;
			y2-=dy2;
		}
	    
		Thread t=new Thread(this);
		t.start();
		addKeyListener(this);
		addMouseListener(this);
		addWindowListener( 
				new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}
	

	
	class Balllisten extends Thread
	{
		public void run()
		{

		}
		public void start(){
			Thread t = new Thread(this);
			t.start();
		}
	}

	public void editBoolHit()
	{
		int count=0,count2=0;
	
		
				count=h.checkBools(Ballarray[Line],Ballarray[0]);
				count2=h.checkHits(Ballarray[Line],Ballarray[0]);
				if(count==4)
				win=1;
					for(int j=0;j<4;j++)
					{
					if(boolhit[Line-1][j]!=null)
						if(count>0)
						{
						boolhit[Line-1][j].setCheck("red");
						count--;
						}
					}
	
					for(int j=0;j<4;j++)
					{
					if(boolhit[Line-1][j]!=null)
						if(!boolhit[Line-1][j].getCheck().equals("red"))
							if(count2>0)
							{
						boolhit[Line-1][j].setCheck("white");
						count2--;
							}
					}
			
		
		
		
	}
	public void editBalls ()
	{
		if(number !=10&&Line<15)
		{
		int i =Line;
			for(int j=0;j<4;j++)
			{
				if(Ballarray[i][j]!=null)
				if(Ballarray[i][j].getNumber()==10)
				{
					
				Ballarray[i][j].setNumber(number);
				if(h.checkNumbers(Ballarray[i])==false)
				{
					Ballarray[i][j].setNumber(10);
				}
				number=10;
				
				}
					
			}
			
			
		}
		if(Line<15)
		{
		if(Ballarray[Line][3]!=null)
			if(Ballarray[Line][3].getNumber()!=10&&keypress==1)
			{
				editBoolHit();
				Line+=1;
			}
		}
		else
			lose=1;
			
		
	}

	public void paint(Graphics g) {
		if(offgc!=null)
		{
		offgc.drawImage(img, 0, 5, windowWidth,windowHeight, this);
		
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<4;j++)
			{
			if(Ballarray[i][j]!=null)
			{
				if(i!=0||win==1)
				Ballarray[i][j].paint(offgc);
				else
					Ballarray[i][j].paint(offgc,h.Imagemaker("images/11.png"));
				
			}
			
			
			if(i==Line)
			{
				if(Ballarray[i][j]!=null)
			BallCircle[Line][j] = new Ball(Ballarray[i][j].getX(),Ballarray[i][j].getY(),Ballarray[i][j].getHeight(),Ballarray[i][j].getWidth(),12);
			if(BallCircle[Line][j]!=null)
			BallCircle[Line][j].paint(offgc);
			}
			
			}
		}
		editBalls();
		
		for(int i=0;i<14;i++)
		{
			for(int j=0;j<4;j++)
			{
			if(boolhit[i][j]!=null)
				boolhit[i][j].paint(offgc);
			}
		}
		
		if(win==1)
		{
			int randomnumber = (int)(Math.random()*4) + 1;
			
			offgc.drawImage(h.Imagemaker("images/Won"+randomnumber+".png"), 100, 300, 300,300, this);
		}
		if(lose==1)
		{
			int randomnumber = (int)(Math.random()*4) + 1;
			
			offgc.drawImage(h.Imagemaker("images/lose"+randomnumber+".png"), 20, 150, 500,500, this);
		}
			
	
		}
		g.drawImage(offscreen , 0, 0, this.getWidth(),this.getHeight(), null);	
	}
	
	public void run()
	{
		while(flag){
			
			//���� �������� paint
			try  {
				repaint();
				Thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(flag!=true)
			System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(win==0)
		{
		if(arg0.getKeyCode()==KeyEvent.VK_1)
		{
			number=1;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_2)
		{
			number=2;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_3)
		{
			number=3;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_4)
		{
			number=4;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_5)
		{
		
			number=5;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_6)
		{
			number=6;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_7)
		{

			number=7;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_8)
		{
			number=8;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_9)
		{

			number=9;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_0)
		{

			number=0;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE&&Line>15)
		{

			h.Back(Ballarray[Line]);
		}
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
		{

			keypress=1;
		}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
		{

			keypress=0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	//	saySomething("clicked"+ arg0.getClickCount(), arg0);
		
		System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		if(win==0)
		{
		if(arg0.getXOnScreen()>=730&&arg0.getXOnScreen()<=773&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=1;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=800&&arg0.getXOnScreen()<=843&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=2;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=871&&arg0.getXOnScreen()<=914&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=3;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=941&&arg0.getXOnScreen()<=984&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=4;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=1011&&arg0.getXOnScreen()<=1054&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
		
			number=5;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		
		if(arg0.getXOnScreen()>=730&&arg0.getXOnScreen()<=773&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{
			number=6;
		}
		if(arg0.getXOnScreen()>=800&&arg0.getXOnScreen()<=843&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

			number=7;
		}
		if(arg0.getXOnScreen()>=871&&arg0.getXOnScreen()<=914&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{
			number=8;
		}
		if(arg0.getXOnScreen()>=941&&arg0.getXOnScreen()<=984&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

			number=9;
		}
		if(arg0.getXOnScreen()>=1011&&arg0.getXOnScreen()<=1054&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

			number=0;
		}
		
		if(arg0.getXOnScreen()>=1081&&arg0.getXOnScreen()<=1124&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935&&Line>15)
		{

			h.Back(Ballarray[Line]);
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=1081&&arg0.getXOnScreen()<=1124&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

		keypress=1;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
	
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	//	saySomething("enterd"+ arg0.getClickCount(), arg0);
	//	System.out.println("enterd"+ arg0.getXOnScreen()+" "+arg0.getYOnScreen());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	//	saySomething("exited"+ arg0.getClickCount(), arg0);
	//	System.out.println("exited: "+ arg0.getXOnScreen()+" "+arg0.getYOnScreen());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//saySomething("pressed"+ arg0.getClickCount(), arg0);
	//	System.out.println("pressed"+ arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		
	}
	void saySomething(String eventDescription, MouseEvent e) {
		System.out.println(eventDescription + " detected on "
                        + e.getComponent().getClass().getName()
                        + "." );
    }
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		keypress=0;
		if(win==0)
		{
			
		if(arg0.getXOnScreen()>=730&&arg0.getXOnScreen()<=773&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=1;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=800&&arg0.getXOnScreen()<=843&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=2;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=871&&arg0.getXOnScreen()<=914&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=3;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=941&&arg0.getXOnScreen()<=984&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
			number=4;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=1011&&arg0.getXOnScreen()<=1054&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935)
		{
		
			number=5;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		
		if(arg0.getXOnScreen()>=730&&arg0.getXOnScreen()<=773&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{
			number=6;
		}
		if(arg0.getXOnScreen()>=800&&arg0.getXOnScreen()<=843&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

			number=7;
		}
		if(arg0.getXOnScreen()>=871&&arg0.getXOnScreen()<=914&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{
			number=8;
		}
		if(arg0.getXOnScreen()>=941&&arg0.getXOnScreen()<=984&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

			number=9;
		}
		if(arg0.getXOnScreen()>=1011&&arg0.getXOnScreen()<=1054&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

			number=0;
		}
		
		if(arg0.getXOnScreen()>=1081&&arg0.getXOnScreen()<=1124&&arg0.getYOnScreen()>=898&&arg0.getYOnScreen()<=935&&Line<15)
		{

			h.Back(Ballarray[Line]);
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
		if(arg0.getXOnScreen()>=1081&&arg0.getXOnScreen()<=1124&&arg0.getYOnScreen()>=969&&arg0.getYOnScreen()<=1004)
		{

		keypress=1;
			System.out.println("clicked"+arg0.getXOnScreen()+" "+arg0.getYOnScreen());
		}
	
		}
		
	}

	
	

	

	
}
