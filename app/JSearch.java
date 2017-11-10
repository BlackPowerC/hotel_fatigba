package app;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class JSearch
{

    private JTextField search_field;
    private JLabel txtSearch;
    private String labelText;

    private void Build_All()
    {
        String tmp1 = "<html><font color=#FEFDF0>";
        String tmp2 = "<font/></html>";
        Font font = new Font("Purisa", Font.BOLD, 16);
        txtSearch = new JLabel(tmp1 + labelText + tmp2);
        txtSearch.setFont(font);
        search_field = new JTextField();
    }

    public Component getComponent(int i)
    {
        switch (i)
        {
            case 0:
                return search_field;
            case 1:
                return txtSearch;
        }
        return null;
    }

    public JTextField getSearch_field()
    {
        return search_field;
    }

    public JLabel getTxtSearch()
    {
        return txtSearch;
    }

    public JSearch(String txt)
    {
        labelText = txt;
        Build_All();
    }

    public JSearch()
    {
        labelText = "Rechercher: ";
        Build_All();
    }
}
