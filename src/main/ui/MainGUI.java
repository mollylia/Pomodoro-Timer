package ui;

import model.Event;
import model.EventLog;
import model.EventLogDisplay;
import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

// Represents GUI for the Pomodoro timer
public class MainGUI extends JFrame {
    private String savedFilePath = "./data/pomodorotimer.json";
    private PomodoroTimer pomoTimer;
    private PomodoroTimerApp app;

    private java.util.Timer timer;
    private TimerTask timerInterval;
    private JLabel runningTime;

    private JTextField textName;
    private JTextField textInterval;
    private JLabel displayedTime;


    // EFFECTS: constructs the main panel
    public MainGUI() {
        app = new PomodoroTimerApp();
        initializePanel();
    }

    // EFFECTS: saves the current Pomodoro timer to file
    private void save() {
        app.savePomodoroTimer();
    }

    // EFFECTS: loads saved Pomodoro timer file on computer
    private void load() {
        pomoTimer = app.loadPomodoroTimer();
    }

    // EFFECTS: clears the saved file and creates a new Pomodoro Timer app
    private void reset() {
        app = new PomodoroTimerApp();
        app.savePomodoroTimer();
    }

    // EFFECTS: quits the main panel
    public void quit() {
        printLog(EventLog.getInstance());

        int result = JOptionPane.showConfirmDialog(null,
                "Would you like to save the timer?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

//        printLog(EventLog.getInstance());
//        new EventLogDisplay().printLog();

        switch (result) {
            case JOptionPane.YES_OPTION:
                if (savedFilePath == "") {
                    savedFilePath = "./data/pomodorotimer.json";
                }

                app.savePomodoroTimer();

                JOptionPane.showMessageDialog(null, "The timer has been successfully saved");
                System.exit(0);
                break;

            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
        }
    }

    // EFFECTS: initializes the main panel
    private void initializePanel() {
        ImageIcon backgroundImage = new ImageIcon("./data/background.jpg");
        JLabel background = new JLabel(backgroundImage);
        add(background);

        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        runningTime = new JLabel();

        MainPanelKeyHandler startTimerButton = new MainPanelKeyHandler("Start");
        MainPanelKeyHandler stopTimerButton = new MainPanelKeyHandler("Stop");

        background.add(runningTime, BorderLayout.SOUTH);
        background.add(startTimerButton);
        background.add(stopTimerButton);
        background.setLayout(new FlowLayout());

        this.addWindowListener(new WindowHandler());

//        windowListener();

//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        runningTime = new JLabel("Run Time");
//        JButton startTimerButton = new JButton("Start");
//        startTimerButton.addActionListener(new ButtonKeyHandler("Start"));
//        JButton stopTimerButton = new JButton("Stop");
//        stopTimerButton.addActionListener(new ButtonKeyHandler("Stop"));
//        timer = new Timer();
//        timerInterval = new ui.PomodoroTimerDisplay(pomoTimer, runningTime);
//        timer.schedule(timerInterval, 0, 1000);
    }


    // EFFECTS: handles the close window box
    public class WindowHandler extends WindowAdapter {
        public WindowHandler() {}

        public void windowClosed(WindowEvent e) {}

        public void windowClosing(WindowEvent e) {
//            printLog(EventLog.getInstance());
//            new EventLogDisplay().printLog();
            quit();
        }
    }


//    private void windowListener() {
//        this.addWindowListener(new WindowAdapter() {
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//                new EventLogDisplay().printLog();
//                quit();
////                printLog();
////                new EventLogDisplay().printLog();
//
//            }
//        });
//    }

    // EFFECTS: adds tabs to menu bars on the main panel, and adds shortcuts
    public JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        addMenuItem(menuBar, menu, "New Timer",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menu.addSeparator();
        addMenuItem(menuBar, menu, "Load Timer",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menu.addSeparator();
        addMenuItem(menuBar, menu, "Save Timer",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menu.addSeparator();
        addMenuItem(menuBar, menu, "Quit",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuBar.add(menu);

        menu = new JMenu("View");
        addMenuItem(menuBar, menu, "View Intervals",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menuBar.add(menu);

        menu = new JMenu("Edit");
        addMenuItem(menuBar, menu, "Add Interval",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuBar.add(menu);

        return menuBar;
    }

    // EFFECTS: adds tabs to menu bar
    private void addMenuItem(JMenuBar menuBar, JMenu menu, String itemText, KeyStroke shortcut) {
        JMenuItem menuItem;

        menuItem = new JMenuItem(itemText);
        menuItem.setAccelerator(shortcut);
        menuItem.addActionListener(new MenuKeyHandler());

        menu.add(menuItem);
    }


    // TODO: can I have it in ui?
    private void printLog(EventLog eventLog) {
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }





    // Handles buttons on panel
    private class MenuKeyHandler implements ActionListener {
        public MenuKeyHandler() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("New Timer")) {
                reset();

            } else if (e.getActionCommand().equals("Load Timer")) {
                load();

            } else if (e.getActionCommand().equals("Save Timer")) {
                save();

            } else if (e.getActionCommand().equals("Add Interval")) {
                new AddIntervalFrame(app, pomoTimer);
//                new AddIntervalFrame(textName, textInterval);
//                new AddIntervalFrame();

            } else if (e.getActionCommand().equals("View Intervals")) {
                new ViewIntervalFrame(app);
//                new ViewIntervalDisplay();
//                save();

            } else if (e.getActionCommand().equals("Quit")) {
//                pomoTimer.printLog(EventLog.getInstance());

                quit();


//                new EventLogDisplay().printLog();
//                printLog();

            } else {
                System.out.println("Coming soon");
            }
        }
    }

    // EFFECTS: starts the pomodoro timer
    public void start() {
        app = new PomodoroTimerApp(runningTime);
        pomoTimer = app.loadPomodoroTimer();

        timer = new Timer();
        timerInterval = new ui.PomodoroTimerDisplay(pomoTimer, runningTime);
        timer.schedule(timerInterval, 0, 1000);

//        pomoTimer = app.loadPomodoroTimer();
//        timer = new Timer();
//        timerInterval = new ui.PomodoroTimerDisplay(pomoTimer, runningTime);
//        timer.schedule(timerInterval, 0, 1000);
    }

    // EFFECTS: pauses the pomodoro timer
    public void pause() {
        timer.cancel();
    }

    // Handles main panel buttons
    private class MainPanelKeyHandler extends JButton implements ActionListener {
        public MainPanelKeyHandler(String text) {
            super(text);
            addActionListener(this);
        }

        // EFFECTS: handles the buttons on the main panel
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getSource());
//
//            if (e.getActionCommand().equals("Start")) {
//                start();
//                setText("Pause");
//            } else if (e.getActionCommand().equals("Pause")) {
//                pause();
//                setText("Resume");
//            } else {
//                start();
//                setText("Pause");
//            }

//            if (e.getActionCommand().equals("Add")) {
//                pomoTimer = app.loadPomodoroTimer();
//                PomodoroInterval interval = new PomodoroInterval(true, textName.getText(),
//                        Integer.parseInt(textInterval.getText()));
//
//                pomoTimer.addInterval(interval);
//            }

            if (e.getActionCommand().equals("Start")) {
                start();

//                EventLog.getInstance().logEvent(new Event("Timer started"));
//                new EventLogDisplay().printLog();


//                app = new PomodoroTimerApp(runningTime);
//                pomoTimer = app.loadPomodoroTimer();
//
//                pomoTimer = app.loadPomodoroTimer();
//                timer = new Timer();
//                timerInterval = new ui.PomodoroTimerDisplay(pomoTimer, runningTime);
//                timer.schedule(timerInterval, 0, 1000);


//                if (timer != null) {
//                    timer.cancel();
//                    timer.purge();
//                }
//
//                timer = new java.util.Timer();
//                timerInterval = new ui.PomodoroTimerDisplay(pomoTimer, runningTime);
//                timer.schedule(timerInterval, 0, 1000);


//                runningTime.setText("Timer running ");

            } else if (e.getActionCommand().equals("Stop")) {
                pause();

//                EventLog.getInstance().logEvent(new Event("Timer stopped"));

//                timer.cancel();

//                if (timer != null) {
//                    timer.cancel();
//                    timer.purge();
//                }

//                runningTime.setText("Timer stopped");

            }
        }
    }


//    // Allows user to add intervals to timer
//    private class AddIntervalFrame extends JFrame {
//        public AddIntervalFrame() {
//            JPanel mainPanel = new JPanel();
//            mainPanel.setLayout(new FlowLayout());
//
//            setSize(400, 300);
//            setLocationRelativeTo(null);
//            setResizable(false);
//            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            setVisible(true);
//
//            JLabel labelName = new JLabel("Name");
//            textName = new JTextField("", 20);
//
//            JLabel labelInterval = new JLabel("Interval");
//            textInterval = new JTextField("", 20);
//
//            ButtonKeyHandler addButton = new ButtonKeyHandler("Add");
//
//            mainPanel.add(labelName);
//            mainPanel.add(textName);
//            mainPanel.add(labelInterval);
//            mainPanel.add(textInterval);
//            mainPanel.add(addButton);
//
//            add(mainPanel, BorderLayout.CENTER);
//        }
//}


//    // Displays all the intervals in the timer
//    private class ViewIntervalDisplay extends JFrame {
//        public ViewIntervalDisplay() {
//            super("Current Intervals");
//
//            JFrame frame = new JFrame();
//            JPanel panel = new JPanel();
//            panel.setLayout(new FlowLayout());
//
//            setSize(400, 300);
//            setLocationRelativeTo(null);
//            setResizable(false);
////            setResizable(true);
//            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            setVisible(true);
//
//            pomoTimer = app.loadPomodoroTimer();
//
//            String output = "<html>";
//            for (int i = 0; i < pomoTimer.length(); i++) {
//                output += pomoTimer.getNextInterval().toString() + "<br>";
//            }
//            output += "</html>";
//
//            JLabel currentIntervalsText = new JLabel(output);
//            panel.add(currentIntervalsText);
//            add(panel, BorderLayout.CENTER);
//        }
//    }
}