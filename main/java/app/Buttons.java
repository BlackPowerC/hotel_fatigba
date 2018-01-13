package main.java.app;

import main.resources.Rc;

public class Buttons
{
    private Button ok;
    private Button reset;
    private Button update;
    private Button delete;
    private Button search;

    private void Build_Button()
    {
        search = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/Search.png");
        update = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/Modify.png");
        ok = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/Add.png");
        reset = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/repeat.png");
        delete = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/Delete.png");
    }

    public void setPosition(int x2, int w2, int h)
    {
//		int x2 = 300, w2 = 260, h = 20;
        /* positionnement du bouton recherche 2 */
        search.setBounds(x2 + 20 + 15 + w2 + x2 * 2 - 48, 245 + h + 15, 48, 48);
        /* Positionnnement du bouton ok 0 */
        ok.setBounds(x2 + 20 - 5 - 10 - 20, 335 + 20, 48, 48);
        /* Positionnnement du bouton reset 1 */
        reset.setBounds(x2 + 100 - 5 - 10 - 20, 335 + 20, 48, 48);
        /* Positionnnement du bouton update 4 */
        update.setBounds(x2 + 180 - 5 - 10 - 20, 335 + 20, 48, 48);
        /* Positionnnement du bouton delete 3 */
        delete.setBounds(x2 + 260 - 5 - 10 - 20, 335 + 20, 48, 48);
    }

    public Buttons()
    {
        Build_Button();
//		setPosition() ;
    }

    public Button getButtons(int index)
    {
        switch (index)
        {
            case 0:
                return ok;
            case 1:
                return reset;
            case 2:
                return update;
            case 3:
                return delete;
            case 4:
                return search;
        }
        return null;
    }
}
