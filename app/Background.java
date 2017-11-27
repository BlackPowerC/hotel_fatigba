package app;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel
{

    private String url;

    public Background(String str)
    {
        super(null);
        url = str;
    }

    public Background()
    {
        super(null);
        url = "/home/jordy/workspace/hotel_fatigba/src/ressource/backgrounds/Aqua Blue.jpg";
    }

    public void paintComponent(Graphics g)
    {
        try
        {
            Image background = ImageIO.read(new File(url));
            g.drawImage(background, 0, 0,
                    this.getWidth(),
                    this.getHeight(),
                    this);
        } catch (IOException er)
        {
            er.printStackTrace();
        }
    }
}
