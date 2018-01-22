/**
 * Created by Quichey on 4/12/17.
 */

import javax.swing.*;       //To make the panel
import java.awt.*;
import java.awt.event.*;          //To make the buttons function

public class AttributeRecord extends JPanel
{
    private Attribute attribute;        //The attribute to be recorded
    private double amount;              //The amount of the attribute; ex. "5" lbs

    private JTextField amountTextField = new JTextField(5);   //Where user enters the amount
    private JButton increase = new JButton("+");           //Button to increase amount
    private JButton decrease = new JButton("-");           //Button to decrease amount

    //Constructors
    public AttributeRecord(Attribute attribute)
    {
        this.attribute = attribute;
        amount = 0;

        buildPanel();
    }

    //Set methods
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAmountTextFieldString()
    {
        this.amountTextField.setText(Double.toString(amount));
    }

    //Get methods

    //Adjust methods
    /**
     * This method increases the amount by however much adjust is
     */
    public void increase()
    {
        amount = Double.parseDouble(amountTextField.getText())
                + Double.parseDouble(attribute.getAdjustTextField().getText());
    }

    /**
     * This method decreases the amount by however much adjust is
     */
    public void decrease()
    {
        amount = Double.parseDouble(amountTextField.getText())
                - Double.parseDouble(attribute.getAdjustTextField().getText());
    }

    //Build the panel
    /**
     * The addListeners method adds all the event listeners
     */
    public void addListeners()
    {
       increase.addActionListener(new IncreaseListener());
       decrease.addActionListener(new DecreaseListener());
       amountTextField.addActionListener(new AmountTextFieldListener());
    }

    public void buildPanel()
    {
        addListeners();

        setBorder(BorderFactory.createLineBorder(Color.black));

        add(decrease);
        add(amountTextField);
        add(increase);
    }

    //Listeners

    /**
     * Listener for increase button
     */
    private class IncreaseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            increase();
            setAmountTextFieldString();
        }
    }

    /**
     * Listener for decrease button
     */
    private class DecreaseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            decrease();
            setAmountTextFieldString();
        }
    }

    private class AmountTextFieldListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setAmount(Double.parseDouble(amountTextField.getText()));
            setAmountTextFieldString();
        }
    }

}
