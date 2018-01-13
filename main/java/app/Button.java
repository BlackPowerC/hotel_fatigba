package app;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Button extends JButton
{
	private String url;
	private static final long serialVersionUID = -7196363953902143443L;

	public Button(String url)
	{
		super(url);
		this.url = url;
	}

	public void paintComponent(Graphics g)
	{
		try
		{
			Image img = ImageIO.read(new File(url));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}