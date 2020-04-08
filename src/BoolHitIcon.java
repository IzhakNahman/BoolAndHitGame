import java.awt.*;

public class BoolHitIcon extends Simple{

	private Image ballImage;
	private String check;
	HelpFunctions h = new HelpFunctions();
	
	public BoolHitIcon(int x, int y, int Height, int Width,String check)
	{
		super(x,y,Height,Width);
		this.check =check;
		this.ballImage=h.Imagemaker("images/"+check+".png");
	}
	public void setCheck(String check)
	{
		this.check=check;
		this.ballImage=h.Imagemaker("images/"+check+".png");
	}
	public String getCheck()
	{
		return this.check;
	}
	public int xEnd ()
	{ return super.getX() + super.getWidth(); }
	
	public int yEnd ()
	{ return super.getY() + super.getHeight(); }

	public void setBallImage(Image i)
	{
		this.ballImage=i;
	}
	public Image getBallImage()
	{
		return this.ballImage;
	}

	public void paint (Graphics g)
	{
		g.drawImage(this.ballImage,(int) super.getX(),(int) super.getY(),(int) super.getWidth(),(int) super.getHeight(), null);

	}
}
