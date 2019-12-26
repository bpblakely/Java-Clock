//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!


/**
 *
 *Currently configured such that the minutes = seconds (1 second in real time will move the clock 1 minute forward)
 *This is for debugging and seeing/testing the general functionality of the program without having to wait forever
 *It can be easily reverted back into minutes in real time 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class MyPanel extends JPanel implements ActionListener {
    private JButton startbtn;
    private JButton setTimebtn;
    private JTextField hourField;
    private JButton alarmbtn;
    private JLabel ampText;
    private JButton hourTogglebtn;
    private JTextField minField;
    private JLabel label303;
    private JLabel hourLabel;
    private JLabel minLabel;
    private ClockDisplay clock;

    public MyPanel() {
        //construct components
        startbtn = new JButton ("Start/Stop");
        setTimebtn = new JButton ("Set Time");
        hourField = new JTextField (5);
        alarmbtn = new JButton ("Set Alarm");
        ampText = new JLabel ("");
        hourTogglebtn = new JButton ("Set 12 Hour Format");
        minField = new JTextField (5);
        label303 = new JLabel (":");
        hourLabel = new JLabel ("Hour");
        minLabel = new JLabel ("Minute");

        //adjust size and set layout
        setPreferredSize (new Dimension (463, 211));
        setLayout (null);

        //add components
        add (startbtn);
        add (setTimebtn);
        add (hourField);
        add (alarmbtn);
        add (ampText);
        add (hourTogglebtn);
        add (minField);
        add (label303);
        add (hourLabel);
        add (minLabel);

        //set component bounds (only needed by Absolute Positioning)
        startbtn.setBounds (185, 15, 100, 20);
        setTimebtn.setBounds (100, 85, 125, 25);
        hourField.setBounds (180, 55, 55, 25);
        alarmbtn.setBounds (245, 85, 100, 25);
        ampText.setBounds (295, 55, 35, 25);
        hourTogglebtn.setBounds (170, 120, 150, 25);
        minField.setBounds (240, 55, 50, 25);
        label303.setBounds (235, 55, 50, 25);
        hourLabel.setBounds (195, 35, 35, 25);
        minLabel.setBounds (245, 35, 70, 20);
        startbtn.addActionListener(this);
        setTimebtn.addActionListener(this);
        alarmbtn.addActionListener(this);
        hourTogglebtn.addActionListener(this);
        clock = new ClockDisplay();
    }

    
    public void actionPerformed(ActionEvent evt)
    {
        String hour, min;
        String cmd;
        cmd = evt.getActionCommand();
        hour= hourField.getText();
        min= minField.getText();
        if(cmd.equals("Start/Stop") ||cmd.equals("Start"))
        {
           clock.runClock();
           startbtn.setText("Stop"); 
           setTimebtn.setEnabled(false);
        }
        else if(cmd.equals("Stop"))
        {
            clock.stopClock();
            startbtn.setText("Start");
            setTimebtn.setEnabled(true);
        }
        if(cmd.equals("Set Time"))
        {
            clock.setTime(Integer.parseInt(hour),Integer.parseInt(min));
            clock.updateDisplay();
        }
        if(cmd.equals("Set Alarm"))
        {
            System.out.println(""+hour);
            clock.setAlarmTime(Integer.parseInt(hour),Integer.parseInt(min));
            clock.updateDisplay();
        }
        if(cmd.equals("Set 12 Hour Format"))
        {
            clock.setDisplayFormat(true);
            hourTogglebtn.setText("Set 24 Hour Format");
        }
        if(cmd.equals("Set 24 Hour Format"))
        {
            clock.setDisplayFormat(false);
            hourTogglebtn.setText("Set 12 Hour Format");
        }
        
        System.out.println("User has pressed " + cmd + ".");
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);
    }
}
