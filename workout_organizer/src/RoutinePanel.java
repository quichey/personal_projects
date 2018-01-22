/**
 * Created by Quichey on 3/23/17.
 * This class is a routine, which has exercises and sets
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RoutinePanel extends JPanel
{
    private int numbOfExercises;
    private int numbOfSets;

    //So the initialize button can be used only once
    private boolean initButtonUsed = false;

    //Panel for attribute title
    private AttributeTitle attributeTitle = new AttributeTitle();

    //All the set titles
    private ArrayList<SetTitle> setTitlePanels = new ArrayList<>();

    //Delete Button to be added to Exercise name panel
    private ArrayList<JButton> deleteExerciseButtons = new ArrayList<>();

    //Upper left panel, starting panel
    private JPanel panel;

    //Components of panel
    private final JLabel numbOfExercisesLabel = new JLabel("# of Exercises: ");
    private JTextField numbOfExercisesTextField = new JTextField(2);
    private JButton numbOfExercisesIncrement = new JButton("+1");

    private final JLabel numbOfSetsLabel = new JLabel("# of of Sets: ");
    private JTextField numbOfSetsTextField = new JTextField(2);
    private JButton numbOfSetsIncrement = new JButton("+1");
    private JButton numbOfSetsDecrement = new JButton("-1");

    private JButton initializeExercSets = new JButton("Initialize # of Exercises/Sets");

    //All of the exercises of the routine
    private ArrayList<Exercise> exercises = new ArrayList<>();

    //Constraint used to add cells
    private GridBagConstraints c = new GridBagConstraints();

    /**
     * Constructor - RoutinePanel starts with just one initial panel.
     */
    public RoutinePanel()
    {
        setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.HORIZONTAL;

        buildPanel();

        c.gridx = 0;
        c.gridy = 0;
        add(panel, c);

        c.gridx = 1;
        add(attributeTitle, c);

        setVisible(true);
    }

    /**
     * builds the initial stand-alone panel.
     */
    public void buildPanel()
    {
        //add all of the action listeners to the buttons
        numbOfExercisesIncrement.addActionListener(new ExercisesIncrementListener());

        numbOfSetsIncrement.addActionListener(new SetsIncrementListener());

        numbOfSetsDecrement.addActionListener(new DeleteSetListener());

        initializeExercSets.addActionListener(new InitializeListener());

        //create instance of the panel and initialize border, layout
        panel = new JPanel();

        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        panel.setLayout(new GridBagLayout());

        //new gridbagconstraint for just the starting panel
        GridBagConstraints d = new GridBagConstraints();

        //add all of the components to the starting panel
        d.gridx = 0;
        d.gridy = 0;
        panel.add(numbOfExercisesLabel, d);

        d.gridx = 1;
        panel.add(numbOfExercisesTextField, d);

        d.gridx = 2;
        panel.add(numbOfExercisesIncrement, d);

        d.gridx = 0;
        d.gridy = 1;
        panel.add(numbOfSetsLabel, d);

        d.gridx = 1;
        panel.add(numbOfSetsDecrement, d);

        d.gridx = 2;
        panel.add(numbOfSetsTextField, d);

        d.gridx = 3;
        panel.add(numbOfSetsIncrement, d);

        d.gridx = 0;
        d.gridy = 2;
        d.gridwidth = 3;
        panel.add(initializeExercSets, d);
    }

    //Listeners for all of the buttons

    /**
     * Adds an exercise to the last row.
     */
    private class ExercisesIncrementListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            numbOfExercises++;

            numbOfExercisesTextField.setText(Integer.toString(numbOfExercises));

            exercises.add(new Exercise(numbOfSets));

            addExercise(numbOfExercises);

            reopenWindow();
        }
    }

    /**
     * Add sets to the last column.
     */
    private class SetsIncrementListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            numbOfSets++;

            numbOfSetsTextField.setText(Integer.toString(numbOfSets));

            for (int i = 0; i < numbOfExercises; i++)
            {
                exercises.get(i).incrementSets();
            }

            addSet(numbOfSets);

            reopenWindow();
        }
    }

    /**
     * Removes a single exercise from the screen
     */
    private class DeleteExerciseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //index of Exercise to be deleted
            int index = deleteExerciseButtons.indexOf(e.getSource());

            //Remove exercise of index and all exercises after it (so the coordinates don't mess up)
            for (int i = 0; i < (numbOfExercises - index); i++)
            {
                removeExercise(index + i + 1);

                //remove delete buttons from exercise panel
                exercises.get(index + i).remove(deleteExerciseButtons.get(index + i));
            }

            exercises.remove(index);
            deleteExerciseButtons.remove(index);

            numbOfExercises--;

            for (int i = 0; i < (numbOfExercises - index); i++)
                addExercise(index + i + 1);

            numbOfExercisesTextField.setText(Integer.toString(numbOfExercises));

            reopenWindow();
        }
    }

    /**
     * Deletes the last set from every exercise
     */
    private class DeleteSetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(numbOfSets >= 1)
            {
                removeSet(numbOfSets);

                numbOfSets--;

                numbOfSetsTextField.setText(Integer.toString(numbOfSets));

                reopenWindow();
            }
        }
    }

    /**
     * Adds specified number of exercises and specified number of sets
     */
    private class InitializeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (Integer.parseInt(numbOfExercisesTextField.getText()) > 0
                    && Integer.parseInt(numbOfSetsTextField.getText()) > 0
                    && !initButtonUsed
                    && exercises.size() == 0
                    && setTitlePanels.size() == 0)
            {
                numbOfExercises = Integer.parseInt(numbOfExercisesTextField.getText());
                numbOfSets = Integer.parseInt(numbOfSetsTextField.getText());

                for (int i = 0; i < numbOfExercises; i++)
                {
                    exercises.add(new Exercise(numbOfSets));
                    addExercise(i + 1);
                }

                for (int i = 0; i < numbOfSets; i++)
                    addSet(i + 1);

                initButtonUsed = true;

                reopenWindow();
            }
        }
    }

    //methods used inside the listeners
    /**
     * adds an exercise and all of its attributes and sets to the screen
     * @param exerciseNumber dictates which exercise is being added
     */
    public void addExercise(int exerciseNumber)
    {
        //Create delete button for exerices panel
        deleteExerciseButtons.add(new JButton("X"));

        deleteExerciseButtons.get(exerciseNumber-1).addActionListener(new DeleteExerciseListener());

        exercises.get(exerciseNumber-1).add(deleteExerciseButtons.get(exerciseNumber-1));

        //Add panel with exercise name and checkboxes
        c.gridx = 0;
        c.gridy = (exerciseNumber -1)*4 + 1;
        c.gridheight = 4;
        add(exercises.get(exerciseNumber - 1), c);

        //Normalize gridheight to 1
        c.gridheight = 1;

        //Add attribute panels
        c.gridx = 1;
        for (int i = 0; i < 4; i++)
        {
            add(exercises.get(exerciseNumber - 1).getAttribute(i), c);

            c.gridy++;
        }

        //Normalize gridy for sets
        c.gridy -= 4;

        //Add set panels
        for (int i = 0; i < numbOfSets; i++)
        {
            c.gridx++;

            //Add AttributeRecord panels
            for (int j = 0; j < 4; j++)
            {
                add(exercises.get(exerciseNumber - 1).getSet(i).getAttributeRecord(j), c);

                c.gridy++;
            }

            //Normalize gridy for next set
            c.gridy -= 4;
        }
    }

    /**
     * adds a new set to every exercise.
     * @param setNumber the total number of sets after adding the new set.
     */
    public void addSet(int setNumber)
    {
        //The column that all new sets will be added to
        c.gridx = (setNumber - 1) + 2;

        //Add SetTitle panel
        c.gridy = 0;
        setTitlePanels.add(new SetTitle(setNumber));
        add(setTitlePanels.get(setNumber - 1));

        for (int i = 0; i < numbOfExercises; i++)
        {
            //row of ith exercise
            c.gridy = (i * 4) + 1;

            //add AttributeRecord for the exercise
            for (int j = 0; j < 4; j++)
            {
                add(exercises.get(i).getSet(setNumber - 1).getAttributeRecord(j), c);

                c.gridy++;
            }
        }
    }

    /**
     * Removes an exercise and all of its attributes and sets from the screen.
     * @param exerciseNumber dictates which exercise is removed.
     */
    public void removeExercise(int exerciseNumber)
    {
        remove(exercises.get(exerciseNumber - 1));

        for (int i = 0; i < 4; i++)
            remove(exercises.get(exerciseNumber - 1).getAttribute(i));

        for (int i = 0; i < numbOfSets; i++)
            for (int j = 0; j < 4; j++)
                remove(exercises.get(exerciseNumber - 1).getSet(i).getAttributeRecord(j));
    }

    /**
     * Removes a set from all of the exercises.
     * @param setNumber dictates which set is to be removed.
     */
    public void removeSet(int setNumber)
    {
        remove(setTitlePanels.get(setNumber - 1));
        setTitlePanels.remove(setNumber - 1);

        for (int i = 0; i < numbOfExercises; i++)
        {
            for (int j = 0; j < 4; j++)
                remove(exercises.get(i).getSet(setNumber - 1).getAttributeRecord(j));

            exercises.get(i).getSets().remove(setNumber - 1);


        }
    }

    /**
     * Makes it so a button automatically displays the result
     */
    public void reopenWindow()
    {
        setVisible(false);
        setVisible(true);
    }
}
