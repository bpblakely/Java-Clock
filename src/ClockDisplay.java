import java.util.*;

/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Brian Blakely for CPS 132 at University of Dayton
 * 
 * 
 */
public class ClockDisplay implements Runnable
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private int alarmHour, alarmMinute;  
    private int alarmCountdown;      
    private String displayString;    // simulates the actual display
    public boolean show12;          // true is 12-hour output has been requested.
                                     
    public DisplayWindow display;   // Used to display the time.
    private boolean running;
    private Thread clockThread;
    
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        display = new DisplayWindow();
        // Alarm hour and minute = -1 indicates alarm is off.
        alarmHour = -1;
        alarmMinute = -1;
        alarmCountdown = 0;      // When > 0, alarm is sounding.
        show12 = false;          // Defaults to 24-hour display
        setToCurrentTime();
        updateDisplay();
        running = false;
    }

    public void timeTick()
    {
        
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        // Check to see if alarm time has been reached.
        if (hours.getValue()==alarmHour && minutes.getValue()==alarmMinute)
        {
            // Set the alarm countdown.
            alarmCountdown = 5; 
        }
        // Once alarm time is reached, display must alternate.
        if (alarmCountdown > 0)
        {
            // display alarm for a simulated minute.
            display.setText("ALARM!");
            wait_a_little(1000);
            // increment the time to keep it correct.
            minutes.increment();
            if(minutes.getValue() == 0) {  // it just rolled over!
                hours.increment();
            }
            // Decrement the alarm countdown.
            alarmCountdown--;
            
        }
        // Display the time.
        updateDisplay();
        display.setText(displayString);
    }

    public void timeBack()
    {
        
        minutes.decrement();
        if(minutes.getValue() == 59) {  // it just rolled over!
            hours.decrement();
        }
        // Display the time.
        updateDisplay();
        display.setText(displayString);
    }
    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
        display.setText(displayString);
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    public void updateDisplay()
    {
        String hourString, tag;
        
        if (show12)
        {
            // Determine is time is AM or PM.
            tag = "am";
            if (hours.getValue() >= 12)
            {
                tag = "pm";
            }
            // Determine the correct display for the hour.
            hourString = "" + hours.getValue();
            if (hours.getValue() > 12)
            {
                hourString = ""+(hours.getValue()-12);
            }
            if (hours.getValue() == 0)
            {
                hourString = "12";
            }
            // Build the 12-hour string.
            displayString = hourString + ":" + minutes.getDisplayValue() + tag;
        }
        else
        {
            // Build the 24-hour string.
            displayString = "" + hours.getValue() + ":" + minutes.getDisplayValue();
        }
    }
    
    
    public void setAlarmTime(int hour, int minute)
    {
        alarmHour = hour;
        alarmMinute = minute;
    }

    public void setToCurrentTime()
    {
        Calendar now;
        
        // This creates an instance of the current day and time.
        now = Calendar.getInstance();
        // Now we can access this object for the current hour and minute, and send those
        // values to setTime().
        setTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));
    }

    /**
     * Wait for approximately the number of milliseconds provided 
     * before doing anything else.
     */
    public static void wait_a_little(int milliseconds) 
    {
        try {Thread.sleep(milliseconds);}
        catch (Exception e) {}
    }
    
    /**
     * Run this clock in a thread.
     */
    
    public void runClock() 
    {
        if (!running)
        {
            clockThread = new Thread(this);
            clockThread.start();
        }
    }
    
    /**
     * Run the clock using simulated "minutes" (Minutes will actually
     * advance each second.)
     */
   public void run()
   {
        running = true;
        // Loop forever. Program will end whwn clock window is closed.
        while (running)
        {
            wait_a_little(1000);
            timeTick();
        }
   }
   
   /**
    * Pause a running clock.
    */
   public void stopClock()
   {
       running = false;
   }
   
   /**
    * Is the clock running? (return true or false)
    * 
    */
   public boolean isRunning()
   {
       return running;
   }
   
   /**
    * Set the display format for this clock.
    * If the parameter "twelveHour" is true, display 12-hour format.
    * If the parameter "twelveHour" is false, display 24-hour format.
    */
   public void setDisplayFormat(boolean twelveHour)
   {
       show12 = twelveHour;
       updateDisplay();
       display.setText(displayString);
   }
}