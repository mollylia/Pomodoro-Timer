package ui;

import model.EventLog;
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
//    private JTextField textInterval;
    private JComboBox statusSelection;

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

        //JLabel labelInterval = new JLabel("Interval");
        //textInterval = new JTextField("", 20);

        String intervalStatus[] = {"short study", "long study", "short break", "long break"};

        statusSelection = new JComboBox(intervalStatus);
        statusSelection.setBounds(50, 50, 90, 20);

        ButtonKeyHandler addButton = new ButtonKeyHandler("Add");

        mainPanel.add(labelName);
        mainPanel.add(textName);
//        mainPanel.add(labelInterval);
        //mainPanel.add(textInterval);
        mainPanel.add(statusSelection);
        mainPanel.add(addButton);

        add(mainPanel, BorderLayout.CENTER);

//        String intervalStatus[] = {"short study", "long study", "custom study",
//                "short break", "long break", "custom break"};
    }


    // Handles keys related to adding intervals
    public class ButtonKeyHandler extends JButton implements ActionListener {
//        PomodoroTimer pomoTimer;
//        PomodoroTimerApp app;

//        private java.util.Timer timer;
//        private TimerTask timerInterval;
//        private JLabel runningTime;

        private int duration;
        boolean intervalStatus = true;

        public ButtonKeyHandler(String text) {
            super(text);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            //System.out.println(e.getSource());


            if (e.getActionCommand().equals("Add")) {
                String statusType = statusSelection.getSelectedItem().toString();


                if ((statusType == "short break" || statusType == "long break")) {
                    intervalStatus = false;
                } else {
                    intervalStatus = true;
                }


                if (statusType == "short study") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_STUDY);
//                    textInterval = new JTextField(String.valueOf(duration), 20);

                } else if (statusType == "long study") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_STUDY);
//                    textInterval = new JTextField(String.valueOf(duration), 20);

                } else if (statusType == "short break") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.SHORT_BREAK);
//                    textInterval = new JTextField(String.valueOf(duration), 20);

                } else if (statusType == "long break") {
                    duration = PomodoroDefault.getPomodoroDefault(PomodoroDefault.LONG_BREAK);
//                    textInterval = new JTextField(String.valueOf(duration), 20);

                }
//                else {
//                    PomodoroInterval interval = new PomodoroInterval(intervalStatus, textName.getText(),
//                            Integer.parseInt(textInterval.getText()));
//
//                    pomoTimer.addInterval(interval);
            }


            pomoTimer = app.loadPomodoroTimer();
            interval = new PomodoroInterval(intervalStatus, textName.getText(), duration);
//            interval = new PomodoroInterval(intervalStatus, textName.getText(),
//                    Integer.parseInt(textInterval.getText()));
//                PomodoroInterval interval = new PomodoroInterval(intervalStatus, textName.getText(),
//                        Integer.parseInt(textInterval.getText()));

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


//}
