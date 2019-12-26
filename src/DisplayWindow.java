import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

/*
 * TimerDisplay objects can display strings of up to 6 characters. Most often, this will be a
 * string representing time in the format mm:ss.
 */
@SuppressWarnings("serial")
public class DisplayWindow extends JFrame {

	private JTextField currentTime;
	private String disp = "--:--";
	private static int xpos=10, ypos=360;
	

	/*
	 * Create and configure the window, including the default display, then make it visible.
	 */
	public DisplayWindow() {
		super("Current Time");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		currentTime = new JTextField(disp,9);
		currentTime.setEditable(false);
		currentTime.setFont(new Font("Courier New", Font.BOLD, 48));
		container.add(currentTime);
		setSize(240, 105);
		setLocation(xpos, ypos);
		ypos = ypos+150;
		if (ypos > 900) {
		    ypos = 10;
		    xpos = xpos+150;
		    if (xpos > 1000) {
		        xpos = 10;
		        ypos = 10;
		      }
		   }
		currentTime.setHorizontalAlignment(JTextField.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/*
	 * Update the string displayed to the one provided.
	 */
	public void setText(String str) {
	    disp = str;
	    currentTime.setText(disp);
	}
}// End of class TextFieldTest