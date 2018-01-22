/**
 * Created by Quichey on 4/12/17.
 */

import javax.swing.*;

public class Weight extends Attribute
{
    public Weight()
    {
        buildPanel();
    }

    //Main
    public static void main(String[] args)
    {
        Weight test = new Weight();

        JFrame window = new JFrame();

        window.add(test);

        window.setTitle("Test");

        window.setSize(200,100);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);

    }
}
