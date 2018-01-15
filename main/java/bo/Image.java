package main.java.bo;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import main.java.app.Background;

public class Image
{

    private CardLayout card;
    private JPanel contentPanel;
    private Background single; // OK
    private Background _double; // OK
    private Background triple; // OK
    private Background standard; // OK
    private Background junior; // OK
    private Background buisiness; // OK
    private Background presidentielle; // OK
    private Background _default;

    public Image()
    {
        buildPanel();
    }

    private String[] label =
    {
        "Single", "Double",
        "Triple", "Standard",
        "Junior", "Buisiness Class",
        "Présidentielle"
    };

    public void show(String label)
    {
        boolean flag = true;
        for (int i = 0; i < this.label.length; i++)
        {
            if (label == this.label[i])
            {
                flag = false;
            }
        }
        if (flag == false)
        {
            card.show(contentPanel, "Default");
            System.out.println("Default");
            return;
        }
        card.show(contentPanel, label);
    }

    private void buildBackground()
    {
        _default = new Background("/home/jordy/workspace/Hotel/src/manageChambre/default.png");
        _double = new Background("/home/jordy/workspace/Hotel/src/manageChambre/double2.jpg");
        single = new Background("/home/jordy/workspace/Hotel/src/manageChambre/single.jpg");
        standard = new Background("/home/jordy/workspace/Hotel/src/manageChambre/standard.jpg");
        junior = new Background("/home/jordy/workspace/Hotel/src/manageChambre/junior.jpg");
        buisiness = new Background("/home/jordy/workspace/Hotel/src/manageChambre/buisiness.jpg");
        presidentielle = new Background("/home/jordy/workspace/Hotel/src/manageChambre/Presidentielle.jpg");
        triple = new Background("/home/jordy/workspace/Hotel/src/manageChambre/triple.jpg");
    }

    public void buildPanel()
    {
        buildBackground();
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(320, 180));
        card = new CardLayout();
        contentPanel.setLayout(card);
        addContent();
    }

    public void addContent()
    {
        contentPanel.add(_default, "Default");
        contentPanel.add(single, label[0]);
        contentPanel.add(_double, label[1]);
        contentPanel.add(triple, label[2]);
        contentPanel.add(standard, label[3]);
        contentPanel.add(junior, label[4]);
        contentPanel.add(buisiness, label[5]);
        contentPanel.add(presidentielle, label[6]);
    }

    public JPanel getPanel()
    {
        return contentPanel;
    }
}
