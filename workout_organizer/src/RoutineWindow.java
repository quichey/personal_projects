/**
 * Created by Quichey on 5/16/17.
 */

import javax.swing.*;
import java.awt.*;

public class RoutineWindow extends JFrame
{
    //Make a scrollpane for the routine panel, and add the panel to the scrollpane
    private RoutinePanel panel = new RoutinePanel();
    private JScrollPane scrollPane = new JScrollPane(panel);

    /**
     * Constructor - adds scrollpane to the window and sets up rest of window
     */
    public RoutineWindow()
    {
        add(scrollPane);

        setTitle("Workout Routine");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Make the window full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        setVisible(true);
    }

    public static void main(String[] args)
    {
        new RoutineWindow();
    }
}
