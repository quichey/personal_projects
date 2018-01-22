/**
 * Created by Quichey on 4/12/17.
 */

import javax.swing.*;       //To make the panel
import java.awt.*;
import java.awt.event.*;          //To make the buttons function

public abstract class Attribute extends JPanel
{
    private String unit;            //The unit of measure for the Attribute
    private double adjust;          //How much the the attribute will change by

    //Variables for the panel
    private JTextField unitTextField = new JTextField(5);        //Where the user enters the unit
    private JLabel unitLabel = new JLabel("Unit:");                 //Label for the unitTextField

    protected JTextField adjustTextField = new JTextField(5);      //Where the user enters the value for the adjust variable
    protected JLabel adjustLabel = new JLabel("+/-: ");               //Label for the adjustTextField

    //Constructors
    public Attribute()
    {
        unit = " ";
        adjust = 0;
    }

    //Set Methods

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setAdjust(double adjust) {
        this.adjust = adjust;
    }

    //Get Methods
    /**
     * The getAdjust method returns the value of adjust
     * @return the value of the adjust variable
     */
    public JTextField getAdjustTextField() {
        return adjustTextField;
    }

    //Action Listeners
    private class UnitTextFieldListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setUnit(unitTextField.getText());
        }
    }

    private class AdjustTextFieldListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setAdjust(Double.parseDouble(adjustTextField.getText()));
        }
    }

    //To build panel
    /**
     * The addListeners method adds the event listeners to the buttons
     */
    public void addListeners()
    {
        unitTextField.addActionListener(new UnitTextFieldListener());
        adjustTextField.addActionListener(new AdjustTextFieldListener());
    }

    /**
     * The buildPanel method adds all the components to the panel
     */
    public void buildPanel()
    {
        addListeners();

        setBorder(BorderFactory.createLineBorder(Color.black));

        add(unitLabel);
        add(unitTextField);
        add(adjustLabel);
        add(adjustTextField);
    }
}
