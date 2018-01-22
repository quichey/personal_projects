/**
 * Created by Quichey on 5/15/17.
 */

import javax.swing.*;
import java.awt.*;

public class AttributeTitle extends JPanel
{
    private JLabel label = new JLabel("Attributes");        //Word to be displayed in cell

    /**
     * Constructor adds label and creates line border
     */
    public AttributeTitle()
    {
        add(label);

        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
