package ui;

import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;

// Displays all the intervals in the timer
public class ViewIntervalFrame extends JFrame {
    private PomodoroTimer pomoTimer;
    private PomodoroTimerApp app;

    // EFFECTS: constructs the view interval frame
    public ViewIntervalFrame(PomodoroTimerApp timerApp) {
        super("Current Intervals");

        app = timerApp;
        JPanel panel = new JPanel();
        setupFrame(panel);
        viewIntervals(panel);
    }

    // EFFECTS: initializes view interval frame
    private void setupFrame(JPanel panel) {
        panel.setLayout(new FlowLayout());

        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: displays all the intervals that have been added
    private void viewIntervals(JPanel panel) {
        pomoTimer = app.loadPomodoroTimer();

        String output = "<html>";
        for (int i = 0; i < pomoTimer.length(); i++) {
            output += pomoTimer.getNextInterval().toString() + "<br>";
        }
        output += "</html>";

        JLabel currentIntervalsText = new JLabel(output);
        panel.add(currentIntervalsText);
        add(panel, BorderLayout.CENTER);
    }
}
