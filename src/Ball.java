import java.awt.*;

public class Ball extends Simple{

	private Image ballImage;
	private int number;
	HelpFunctions h = new HelpFunctions();
	
	public Ball(int x, int y, int Height, int Width,int number)
	{
		super(x,y,Height,Width);
		this.number =number;
		this.ballImage=h.Imagemaker("images/"+number+".png");
	}
	public int getNumber() 
	{
		return number;
	}
	public void setNumber(int x) 
	{
		this.number = x;
		this.ballImage =h.Imagemaker("images/"+number+".png");
	}
	public int xEnd ()
	{ return super.getX() + super.getWidth(); }
	
	public int yEnd ()
	{ return super.getY() + super.getHeight(); }

	public Image getBallImage()
	{
		return this.ballImage;
	}

	public void paint (Graphics g)
	{
		g.drawImage(this.ballImage,(int) super.getX(),(int) super.getY(),(int) super.getWidth(),(int) super.getHeight(), null);

	}
	public void paint (Graphics g,Image imageball)
	{
		g.drawImage(imageball,(int) super.getX(),(int) super.getY(),(int) super.getWidth(),(int) super.getHeight(), null);

	}
}
