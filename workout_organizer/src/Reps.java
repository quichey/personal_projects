import javax.swing.*;
import java.awt.*;

/**
 * Created by Quichey on 4/12/17.
 */
public class Reps extends Attribute
{
    public Reps()
    {
        buildPanel();
    }

    /**
     * The buildPanel method adds all the components to the panel
     */
    public void buildPanel()
    {
        addListeners();

        setBorder(BorderFactory.createLineBorder(Color.black));

        add(adjustLabel);
        add(adjustTextField);
    }

    //Main
    public static void main(String[] args)
    {
        Reps test = new Reps();

        JFrame window = new JFrame();

        window.add(test);

        window.setTitle("Test");

        window.setSize(200,100);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);

    }
}
