package ui;

import model.Event;
import model.EventLog;
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
    private Timer timer;
    private TimerTask timerInterval;
    private JLabel runningTime;
    private JLabel background;
    private int width = 800;
    private int height = 500;

    // EFFECTS: constructs main panel
    public MainGUI() {
        app = new PomodoroTimerApp();
        initializePanel();
    }

    // EFFECTS: initializes main panel
    private void initializePanel() {
        setBackground();
        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        setPomoDisplay();
        MainPanelKeyHandler startTimerButton = setButton("Start");
        MainPanelKeyHandler stopTimerButton = setButton("Stop");

        JPanel timerPanel = new JPanel();
        setPomoPanel(timerPanel);

        JPanel buttonPanel = new JPanel();
        setButtonPanel(buttonPanel, startTimerButton, stopTimerButton);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBounds(width/4,height/5, width/2, height/2);
        contentPanel.add(timerPanel);
        contentPanel.add(buttonPanel);
        contentPanel.setOpaque(false);

        background.add(contentPanel);
        background.setLayout(null);
        this.addWindowListener(new WindowHandler());

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: sets and sizes background image
    private void setBackground() {
        ImageIcon backgroundImage = new ImageIcon("./data/rain.gif");
        background = new JLabel(backgroundImage);
        setResizable(false);
        add(background);
    }

    // EFFECTS: creates and sets the size of a new button
    private MainPanelKeyHandler setButton(String name) {
        MainPanelKeyHandler button = new MainPanelKeyHandler(name);
        button.setPreferredSize(new Dimension(100, 45));
        button.setFont(new Font("Chalkboard", Font.PLAIN, 18));
        return button;
    }

    // EFFECTS: sets the size of the timer display
    private void setPomoDisplay() {
        runningTime = new JLabel();
        runningTime.setText("00:00");
        runningTime.setFont(new Font("Chalkboard", Font.PLAIN, 140));
        runningTime.setForeground(new Color(0xf5f5f5));
    }

    // EFFECTS: adds running time to panel
    private void setPomoPanel(JPanel panel) {
        panel.setOpaque(false);
        panel.add(runningTime);
    }

    // EFFECTS: adds buttons to panel
    private void setButtonPanel(JPanel panel, MainPanelKeyHandler start, MainPanelKeyHandler stop) {
        panel.add(start);
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
        panel.setOpaque(false);
        panel.add(stop);
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

    // EFFECTS: quits the main panel and asks user if they want to save the timer
    public void quit() {

        int result = JOptionPane.showConfirmDialog(null,
                "Would you like to save the timer?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        switch (result) {
            case JOptionPane.YES_OPTION:
                if (savedFilePath == "") {
                    savedFilePath = "./data/pomodorotimer.json";
                }

                app.savePomodoroTimer();
                printLog(EventLog.getInstance());

                JOptionPane.showMessageDialog(null, "The timer has been successfully saved");
                System.exit(0);
                break;

            case JOptionPane.NO_OPTION:
                printLog(EventLog.getInstance());
                System.exit(0);
                break;
        }
    }

    // EFFECTS: handles the close window box
    public class WindowHandler extends WindowAdapter {

        // EFFECTS: constructs window handler
        public WindowHandler() {
        }

        // EFFECTS: handles close window button
        public void windowClosing(WindowEvent e) {
            quit();
        }
    }

    // EFFECTS: prints the log to console
    private void printLog(EventLog eventLog) {
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }


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

    // Handles buttons on panel
    private class MenuKeyHandler implements ActionListener {

        // EFFECTS: constructs menu handler
        public MenuKeyHandler() {
        }

        // EFFECTS: menu button listeners
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("New Timer")) {
                reset();
            } else if (e.getActionCommand().equals("Load Timer")) {
                load();
            } else if (e.getActionCommand().equals("Save Timer")) {
                save();
            } else if (e.getActionCommand().equals("Add Interval")) {
                new AddIntervalFrame(app, pomoTimer);
            } else if (e.getActionCommand().equals("View Intervals")) {
                new ViewIntervalFrame(app);
            } else if (e.getActionCommand().equals("Quit")) {
                quit();
            }
        }
    }


    // EFFECTS: starts the pomodoro timer
    public void start() {
        app = new PomodoroTimerApp(runningTime);
        pomoTimer = app.loadPomodoroTimer();

        timerInterval = new ui.PomodoroTimerDisplay(pomoTimer, runningTime);
        pomoTimer.start(timer, timerInterval);
    }

    // EFFECTS: stops the pomodoro timer
    public void stop() {
        pomoTimer.stop();
    }


    // Handles main panel buttons
    private class MainPanelKeyHandler extends JButton implements ActionListener {

        // EFFECTS: constructs new panel handler
        public MainPanelKeyHandler(String text) {
            super(text);
            addActionListener(this);
        }

        // EFFECTS: main panel button listeners
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Start")) {
                start();
            } else if (e.getActionCommand().equals("Stop")) {
                stop();
            }
        }
    }
}