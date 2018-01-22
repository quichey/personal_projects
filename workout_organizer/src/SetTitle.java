/**
 * Created by Quichey on 5/15/17.
 */

import javax.swing.*;
import java.awt.*;

public class SetTitle extends JPanel
{
    private String title = "Set";       //To be part of the text for the label
    private JLabel label;               //Label to display "Set #"

    /**
     * Constructor that initializes the set #.
     * @param setNumber is to go into title.
     */
    public SetTitle(int setNumber)
    {
        title += " " + setNumber;

        label = new JLabel(title);

        setBorder(BorderFactory.createLineBorder(Color.black));

        add(label);
    }
}
