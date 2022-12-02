package ui;

import model.PomodoroDefault;
import model.PomodoroInterval;
import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Allows user to add intervals to timer
public class AddIntervalFrame extends JFrame {
    private PomodoroInterval interval;
    private PomodoroTimer pomoTimer;
    private PomodoroTimerApp app;


    private JTextField textName;
    private JComboBox statusSelection;

    // EFFECTS: constructs add interval frame with given app and timer
    public AddIntervalFrame(PomodoroTimerApp app, PomodoroTimer timer) {
        this.app = app;
        pomoTimer = timer;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);

        JLabel labelName = new JLabel("Name");
        textName = new JTextField("", 20);


        String[] intervalStatus = {"short study", "long study", "short break", "long break"};

        statusSelection = new JComboBox(intervalStatus);
        statusSelection.setBounds(50, 50, 90, 20);

        ButtonKeyHandler addButton = new ButtonKeyHandler("Add");

        mainPanel.add(labelName);
        mainPanel.add(textName);
        mainPanel.add(statusSelection);
        mainPanel.add(addButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    // Handles keys related to adding intervals
    public class ButtonKeyHandler extends JButton implements ActionListener {
        private int duration;
        boolean intervalStatus = true;

        // EFFECTS: handles the buttons on the main panel
        public ButtonKeyHandler(String text) {
            super(text);
            addActionListener(this);
        }

        // EFFECTS: button listener for panel, adds timer interval to Pomodoro timer
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Add")) {
                String statusType = statusSelection.getSelectedItem().toString();

                if ((statusType == "short break" || statusType == "long break")) {
                    intervalStatus = false;
                } else {
                    intervalStatus = true;
                }

                if (statusType == "short study") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_STUDY);
                } else if (statusType == "long study") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_STUDY);
                } else if (statusType == "short break") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_BREAK);
                } else if (statusType == "long break") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_BREAK);
                }
            }

            pomoTimer = app.loadPomodoroTimer();
            interval = new PomodoroInterval(intervalStatus, textName.getText(), duration);
            pomoTimer.addInterval(interval);
            app.savePomodoroTimer();
        }
    }
}

