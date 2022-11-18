package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimerGUI extends JFrame {
    JTextField textName;
    JTextField textInterval;

    public PomodoroTimerGUI() {
        initControls();
    }

    private void save() {
        PomodoroTimerApp app = new PomodoroTimerApp();
        app.savePomodoroTimer();
    }

    private void initControls() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        // TODO
        //Set icon image for the frame:The icon image is in the file system:
        Image icon = new javax.swing.ImageIcon("images/clock.png").getImage();
        setIconImage(icon);

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

    /********************************Menu example********************************/
    public JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;

        menu = new JMenu("File");
//      menu.setMnemonic(java.awt.event.KeyEvent.VK_F);
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
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuBar.add(menu);

        menu = new JMenu("View");
//      menu.setMnemonic(java.awt.event.KeyEvent.VK_V);
        addMenuItem(menuBar, menu, "View Intervals",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuBar.add(menu);

        menu = new JMenu("Edit");
//      menu.setMnemonic(java.awt.event.KeyEvent.VK_E);
        addMenuItem(menuBar, menu, "Add Interval",
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuBar.add(menu);
        return menuBar;
    }

    private void addMenuItem(JMenuBar menuBar, JMenu menu, String itemText, KeyStroke shortcut) {
        JMenuItem menuItem;

        menuItem = new JMenuItem(itemText);
        menuItem.setAccelerator(shortcut);
        menuItem.addActionListener(new MenuItemListener());

        menu.add(menuItem);
    }

    private class MenuItemListener implements ActionListener {
        public MenuItemListener() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Load Timer")) {
                PomodoroTimerApp app = new PomodoroTimerApp();
                app.loadPomodoroTimer();
//              app.runPomodoroTimer();

            } else if (e.getActionCommand().equals("Save Timer")) {
                save();

            } else if (e.getActionCommand().equals("Add Interval")) {
                SettingFrame settingFrame = new SettingFrame();

            } else if (e.getActionCommand().equals("View Intervals")) {
                ViewIntervalDisplay frame = new ViewIntervalDisplay();
            } else if (e.getActionCommand().equals("Quit")) {
                new QuitFrame();
            } else if (e.getActionCommand().equals("Yes")) {
                save();
                System.exit(0);
            } else if (e.getActionCommand().equals("No")) {
                System.exit(0);
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
            super("Timer Setting");

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
            app.printIntervals();
//            app.printIntervals();
//            JTextArea textArea = new JTextArea();
//            panel.add(textArea);
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



