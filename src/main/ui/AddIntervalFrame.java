package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Allows user to add intervals to timer
public class AddIntervalFrame extends JFrame {
    private PomodoroTimer pomoTimer;
    private PomodoroTimerApp app;

    private JTextField textName;
    private JTextField textInterval;

//  public AddIntervalFrame() {
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

        JLabel labelInterval = new JLabel("Interval");
        textInterval = new JTextField("", 20);

        ButtonKeyHandler addButton = new ButtonKeyHandler("Add");

        mainPanel.add(labelName);
        mainPanel.add(textName);
        mainPanel.add(labelInterval);
        mainPanel.add(textInterval);
        mainPanel.add(addButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    // Handles keys related to adding intervals
    public class ButtonKeyHandler extends JButton implements ActionListener {
//        PomodoroTimer pomoTimer;
//        PomodoroTimerApp app;

//        private java.util.Timer timer;
//        private TimerTask timerInterval;
//        private JLabel runningTime;

        public ButtonKeyHandler(String text) {
            super(text);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getSource());

            if (e.getActionCommand().equals("Add")) {
                pomoTimer = app.loadPomodoroTimer();
                PomodoroInterval interval = new PomodoroInterval(true, textName.getText(),
                        Integer.parseInt(textInterval.getText()));

                pomoTimer.addInterval(interval);
                app.savePomodoroTimer();
            }
//            else if (e.getActionCommand().equals("Start")) {
//                runningTime.setText("Timer running ");
//                pomoTimer = app.loadPomodoroTimer();
//                timer = new Timer();
//                timerInterval = new ui.PomodoroTimerDisplay(pomoTimer, runningTime);
//                timer.schedule(timerInterval, 0, 1000);
//            } else if (e.getActionCommand().equals("Stop")) {
//                runningTime.setText("Timer stopped");
//                timer.cancel();
//            }
        }
    }

}
