package main.java.manageReservation;

import javax.swing.*;

import main.java.app.Button;

public class searchData
{
    private JLabel clientTxt;
    private JTextField clientFiled;
    private JLabel chambreTxt;
    private JTextField chambrefield;
    private Button clienButton;
    private Button chambreButton;

    private JPanel panel;

    private void Build_Content()
    {
        clientFiled = new JTextField(10);
        clientTxt = new JLabel("Rechercher Client");

        chambrefield = new JTextField(10);
        chambreTxt = new JLabel("Rechercher Chambre");
        chambreButton = new Button("/home/jordy/workspace/Hotel/src/icons/PNG-48/Search.png");
        clienButton = new Button("/home/jordy/workspace/Hotel/src/icons/PNG-48/Search.png");
    }

    public void setContent()
    {

    }
}
