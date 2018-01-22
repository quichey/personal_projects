/**
 * Created by Quichey on 4/26/17.
 */

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;           //To make a panel
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.*;        //To make checkboxes
import java.util.ArrayList;

public class Exercise extends JPanel
{
    private Attribute[] attributes =                //Will have Weight, Reps, Time, and Intensity
            {new Weight(),
            new Reps(),
            new Time(),
            new Intensity()};

    private ArrayList<Set> sets;            //Arraylist of sets because it can be modified

    private JTextField exerciseName = new JTextField(10);  //Where user can type name of exercise

    private JCheckBox[] toggles =                   //Checkboxes for each Attribute
            {new JCheckBox("Weight"),
            new JCheckBox("Reps"),
            new JCheckBox("Time"),
            new JCheckBox("Intensity")};

    /**
     * Creates an exercise with a given number of sets
     * @param numbOfSets is the number of sets the exercise will have
     */
    public Exercise(int numbOfSets)
    {
        sets = new ArrayList<>();

        for (int i = 0; i < numbOfSets; i++)
            sets.add(new Set(attributes[0], attributes[1], attributes[2], attributes[3]));

        setLayout(new GridLayout(6,1));

        setBorder(BorderFactory.createLineBorder(Color.black));

        add(exerciseName);

        for (int i = 0; i < 4; i++) {
            add(toggles[i]);
            toggles[i].doClick();
        }

        toggles[0].addActionListener(new Toggles0Listener());
        toggles[1].addActionListener(new Toggles1Listener());
        toggles[2].addActionListener(new Toggles2Listener());
        toggles[3].addActionListener(new Toggles3Listener());


    }

    //Listener for the four toggle buttons. They make the attribute and
    // related attribute records: black if checkbox is not check,
    // or visible if checkbox is checked
    private class Toggles0Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            toggle(0);
        }
    }

    private class Toggles1Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            toggle(1);
        }
    }

    private class Toggles2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            toggle(2);
        }
    }

    private class Toggles3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            toggle(3);
        }
    }

    /**
     * The action done by a toggle button
     * @param index indicates which toggle button is used
     */
    public void toggle(int index)
    {
        if (!toggles[index].isSelected())
        {
            attributes[index].setBackground(Color.black);
            for (int i = 0; i < sets.size(); i++)
                sets.get(i).getAttributeRecord(index).setBackground(Color.black);
        }
        else
        {
            attributes[index].setBackground(null);
            for (int i = 0; i < sets.size(); i++)
                sets.get(i).getAttributeRecord(index).setBackground(null);
        }
    }

    //Get methods
    public Attribute getAttribute(int index)
    {
        return attributes[index];
    }

    public Set getSet(int index)
    {
        return sets.get(index);
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    //Adds a set to the exercise
    public void incrementSets()
    {
        sets.add(new Set(attributes[0], attributes[1], attributes[2], attributes[3]));
    }
}
