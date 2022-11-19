package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Represents GUI for the Pomodoro timer
public class PomodoroTimerGUI extends JFrame {
    JTextField textName;
    JTextField textInterval;
    private String savedFileFullPath = "./data/pomodorotimer.json";

    // EFFECTS: constructs the main panel
    public PomodoroTimerGUI() {
        initControls();
    }

//    // EFFECTS: saves the current Pomodoro timer
//    private void save() {
//        PomodoroTimerApp app = new PomodoroTimerApp();
//        app.savePomodoroTimer();
//    }

    private void save() {
        try {
            savedFileFullPath = showOpenFileDialog("save");
            PomodoroTimerApp app = new PomodoroTimerApp(savedFileFullPath);
            app.savePomodoroTimer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            String selectedFileFullPath = showOpenFileDialog("open");
            PomodoroTimerApp app = new PomodoroTimerApp(selectedFileFullPath);
            app.loadPomodoroTimer();
            //app.runPomodoroTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // EFFECTS: initializes the main panel
    private void initControls() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        JLabel runningTime = new JLabel("Run Time");
        JButton startTimerButton = new JButton("Start");
        KeyHandler stopTimerButton = new KeyHandler("Stop");

        panel.add(runningTime, BorderLayout.SOUTH);
        panel.add(stopTimerButton);
        panel.add(startTimerButton);
        add(panel, BorderLayout.CENTER);
    }


    // EFFECTS: creates and adds the menu bars on the main panel, and adds shortcuts
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

    private String showOpenFileDialog(String status) {
        String selectedFileFullPath = "";

        JFileChooser fileChooser = new JFileChooser();
        if(status == "save"){
            fileChooser.setDialogTitle("Specify a file to save");
        }
        else {
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFileFullPath = selectedFile.getAbsolutePath();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        return selectedFileFullPath;
    }

    /***************************************File Dialog***************************************/

    /***************************************Quit**********************************************/
    public void quit() {
        //new QuitFrame();
        //JOptionPane.showMessageDialog(null, "Hello Java");
        //JOptionPane.showMessageDialog(null, "You have less amount, please recharge","Apocalyptic message", JOptionPane.WARNING_MESSAGE);

        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to quit, Save your file...",
                "Confirmation",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        switch (result) {
            case JOptionPane.YES_OPTION:
                //System.out.println("Yes");
                if(savedFileFullPath == "")
                    savedFileFullPath = "./data/pomodorotimer.json";

                PomodoroTimerApp app = new PomodoroTimerApp(savedFileFullPath);
                app.savePomodoroTimer();

                JOptionPane.showMessageDialog(null, "You list saved successfully");
                break;
            case JOptionPane.NO_OPTION:
                //System.out.println("No");
                break;
            case JOptionPane.CANCEL_OPTION:
                //System.out.println("Cancel");
                break;
            case JOptionPane.CLOSED_OPTION:
                //System.out.println("Closed");
                break;
        }
    }




    // Represents
    private class MenuItemListener implements ActionListener {
        public MenuItemListener() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Load Timer")) {
//                PomodoroTimerApp app = new PomodoroTimerApp();
//                app.loadPomodoroTimer();
//              app.runPomodoroTimer();
                load();

            } else if (e.getActionCommand().equals("Save Timer")) {
                save();

            } else if (e.getActionCommand().equals("Add Interval")) {
                new SettingFrame();

            } else if (e.getActionCommand().equals("View Intervals")) {
                ViewIntervalDisplay frame = new ViewIntervalDisplay();
            } else if (e.getActionCommand().equals("Quit")) {
                quit();
            } else if (e.getActionCommand().equals("Yes")) {
                save();
                System.exit(0);
            } else if (e.getActionCommand().equals("No")) {
                System.exit(0);
            } else if (e.getActionCommand().equals("Start")) {
                // TODO
            } else if (e.getActionCommand().equals("Stop")) {
                // TODO
            } else {
                System.out.println("Coming soon");
            }
        }
    }

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

    /********************************Menu example********************************/

    private class KeyHandler extends JButton implements ActionListener {
        public KeyHandler(String text) {
            super(text);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getSource());

            if (e.getActionCommand().equals("Add New Interval")) {
                PomodoroInterval interval = new PomodoroInterval(true, textName.getText(), 1000);

                PomodoroTimerApp app = new PomodoroTimerApp();
                PomodoroTimer pomoTimer = app.loadPomodoroTimer();
                pomoTimer.addInterval(interval);

            }
        }
    }

    private class SettingFrame extends JFrame {
        public SettingFrame() {
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

//            PomodoroTimer pomoTimer = new PomodoroTimer();
//            String textArea = pomoTimer.getPomoIntervals().toString();
//            System.out.println(textArea);

            PomodoroTimerApp app = new PomodoroTimerApp();
            String output = app.toString();
//            app.printIntervals();
//            JTextArea textArea = new JTextArea();
//            panel.add(textArea);

            JTextArea textArea = new JTextArea(output, 50, 50);
            panel.add(textArea);
            add(panel, BorderLayout.CENTER);
        }
    }

    //TODO
    public class QuitFrame extends JFrame {
        public QuitFrame() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            setSize(200, 150);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setVisible(true);

            JLabel saveMessage = new JLabel("Do you want to save your current Pomodoro timer?");

            KeyHandler yesSave = new KeyHandler("Yes");
            KeyHandler noSave = new KeyHandler("No");

            panel.add(saveMessage);
            panel.add(yesSave);
            panel.add(noSave);
            add(panel, BorderLayout.CENTER);
        }
    }
}

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



