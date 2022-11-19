package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents GUI for the Pomodoro timer
public class PomodoroTimerGUI extends JFrame {
    private JTextField textName;
    private JTextField textInterval;
    private String savedFilePath = "./data/pomodorotimer.json";

    // EFFECTS: constructs the main panel
    public PomodoroTimerGUI() {
        initControls();
    }

    // EFFECTS: saves the current Pomodoro timer to file
    private void save() {
        PomodoroTimerApp app = new PomodoroTimerApp();
        app.savePomodoroTimer();
    }

    // EFFECTS: loads saved Pomodoro timer file on computer
    private void load() {
        PomodoroTimerApp app = new PomodoroTimerApp();
        app.loadPomodoroTimer();
//        app.runPomodoroTimer();
    }

    // EFFECTS: quits the open panel
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

                PomodoroTimerApp app = new PomodoroTimerApp(savedFilePath);
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
    private void initControls() {
        ImageIcon backgroundImage = new ImageIcon("./data/background.jpg");
        JLabel background = new JLabel(backgroundImage);
        add(background);
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());

        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        JLabel runningTime = new JLabel("Run Time");
        JButton startTimerButton = new JButton("Start");
        KeyHandler stopTimerButton = new KeyHandler("Stop");

//        panel.add(runningTime, BorderLayout.SOUTH);
//        panel.add(stopTimerButton);
//        panel.add(startTimerButton);
//        add(panel, BorderLayout.CENTER);

        background.add(runningTime, BorderLayout.SOUTH);
        background.add(startTimerButton);
        background.add(stopTimerButton);
        background.setLayout(new FlowLayout());
    }

    // EFFECTS: creates and adds tabs to menu bars on the main panel, and adds shortcuts
    public JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;

        menu = new JMenu("File");
//      addMenuItem(menuBar, menu, "New Timer",
//              KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//      menu.addSeparator();
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

    // EFFECTS: sets shortcut for each sub-tab in the menu bar
    private void addMenuItem(JMenuBar menuBar, JMenu menu, String itemText, KeyStroke shortcut) {
        JMenuItem menuItem;

        menuItem = new JMenuItem(itemText);
        menuItem.setAccelerator(shortcut);
        menuItem.addActionListener(new MenuItemListener());

        menu.add(menuItem);
    }


    // Handles buttons on panel
    private class MenuItemListener implements ActionListener {
        public MenuItemListener() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Load Timer")) {
                load();
            } else if (e.getActionCommand().equals("Save Timer")) {
                save();
            } else if (e.getActionCommand().equals("Add Interval")) {
                new EditFrame();
            } else if (e.getActionCommand().equals("View Intervals")) {
                new ViewIntervalDisplay();
            } else if (e.getActionCommand().equals("Quit")) {
                quit();
            } else if (e.getActionCommand().equals("Start")) {
                // TODO
            } else if (e.getActionCommand().equals("Stop")) {
                // TODO
            } else {
                System.out.println("Coming soon");
            }
        }
    }

    // Handles menu
    private class MenuEvent extends JMenuBar implements ActionListener {
        private JFrame frame;
        private JMenuBar menuBar;
        private JMenu file;
        private JMenu edit;
        private JMenu help;
        private JMenuItem cut;
        private JMenuItem copy;
        private JMenuItem paste;
        private JMenuItem selectAll;
        private JTextArea textArea;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cut) {
                textArea.cut();
            }
            if (e.getSource() == paste) {
                textArea.paste();
            }
            if (e.getSource() == copy) {
                textArea.copy();
            }
            if (e.getSource() == selectAll) {
                textArea.selectAll();
            }
        }
    }

    // Handles keys related to adding intervals
    private class KeyHandler extends JButton implements ActionListener {
        public KeyHandler(String text) {
            super(text);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getSource());

            if (e.getActionCommand().equals("Add")) {
                PomodoroInterval interval = new PomodoroInterval(true, textName.getText(),
                        Integer.parseInt(textInterval.getText()));

                PomodoroTimerApp app = new PomodoroTimerApp();
                PomodoroTimer pomoTimer = app.loadPomodoroTimer();
                pomoTimer.addInterval(interval);
                app.savePomodoroTimer();
            }
        }
    }

    // Allows user to add intervals to timer
    private class EditFrame extends JFrame {
        public EditFrame() {
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

            KeyHandler addButton = new KeyHandler("Add");

            mainPanel.add(labelName);
            mainPanel.add(textName);
            mainPanel.add(labelInterval);
            mainPanel.add(textInterval);
            mainPanel.add(addButton);

            add(mainPanel, BorderLayout.CENTER);
        }
    }


    // Displays all the intervals in the timer
    // TODO
    private class ViewIntervalDisplay extends JFrame {
        public ViewIntervalDisplay() {
            super("Current Intervals");

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            setSize(400, 300);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setVisible(true);

            PomodoroTimerApp app = new PomodoroTimerApp();
            String output = app.toString();

            JTextArea textArea = new JTextArea(output, 50, 50);
            panel.add(textArea);
            add(panel, BorderLayout.CENTER);
        }
    }
}


//    private void save() {
//        PomodoroTimerApp app = new PomodoroTimerApp();
//        app.savePomodoroTimer();
//    }
//    private void save() {
//        try {
//            savedFileFullPath = showOpenFileDialog("save");
//            PomodoroTimerApp app = new PomodoroTimerApp(savedFileFullPath);
//            app.savePomodoroTimer();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private void load() {
//        try {
//            String selectedFileFullPath = showOpenFileDialog("open");
//            PomodoroTimerApp app = new PomodoroTimerApp(selectedFileFullPath);
//            app.loadPomodoroTimer();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void quit() {
//        int result = JOptionPane.showConfirmDialog(null,
//                "Would you like to save the timer?",
//                "Confirmation",
//                JOptionPane.OK_CANCEL_OPTION,
//                JOptionPane.INFORMATION_MESSAGE);
//
//        switch (result) {
//            case JOptionPane.YES_OPTION:
//                if (savedFileFullPath == "") {
//                    savedFileFullPath = "./data/pomodorotimer.json";
//                }
//
//                PomodoroTimerApp app = new PomodoroTimerApp(savedFileFullPath);
//                app.savePomodoroTimer();
//
//                JOptionPane.showMessageDialog(null, "The timer has been successfully saved");
//                break;
//            case JOptionPane.NO_OPTION:
//                break;
//            case JOptionPane.CANCEL_OPTION:
//                break;
//            case JOptionPane.CLOSED_OPTION:
//                break;
//        }
//    }
//    public void actionPerformed(ActionEvent e) {
//            if (e.getActionCommand().equals("New Timer")) {
//                // do nothing for now
//            } else


//        MenuExample() {
//            frame = new JFrame();
//            cut = new JMenuItem("cut");
//            copy = new JMenuItem("copy");
//            paste = new JMenuItem("paste");
//            selectAll = new JMenuItem("selectAll");
//            cut.addActionListener(this);
//            copy.addActionListener(this);
//            paste.addActionListener(this);
//            selectAll.addActionListener(this);
//            menuBar = new JMenuBar();
//            file = new JMenu("File");
//            edit = new JMenu("Edit");
//            help = new JMenu("Help");
//            edit.add(cut);
//            edit.add(copy);
//            edit.add(paste);
//            edit.add(selectAll);
//            menuBar.add(file);
//            menuBar.add(edit);
//            menuBar.add(help);
//            textArea = new JTextArea();
//            textArea.setBounds(5, 5, 360, 320);
//            frame.add(menuBar);
//            frame.add(textArea);
//            frame.setJMenuBar(menuBar);
//            frame.setLayout(null);
//            frame.setSize(400, 400);
//            frame.setVisible(true);
//        }



