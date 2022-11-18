package ui;

import model.PomodoroInterval;
import model.PomodoroTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PomodoroTimerGUI extends JFrame {

    JTextField textName;
    JTextField textInterval;

    public PomodoroTimerGUI() {
        super("My Pomodoro Timer");
        initControls();
    }

    private void initControls() {
        //setLayout(new BorderLayout());                     // set layout

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        // adds menu bar to the frame
//        JMenuBar menuBar = new JMenuBar();
//        JMenu menuFile = new JMenu("File");
//        JMenuItem menuItemExit = new JMenuItem("Exit");
//        menuFile.add(menuItemExit);
//
//        menuBar.add(menuFile);
        //MenuExample menuBar = new MenuExample();
        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);
        // adds menu bar to the frame

        //Set icon image for the frame:The icon image is in the file system:
        Image icon = new javax.swing.ImageIcon("images/clock.png").getImage();
        setIconImage(icon);

        setSize(400, 300);
        setLocationRelativeTo(null);            // center frame on screen
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        //addWindowListener(this);

        JLabel lblStatusMessage = new JLabel("Timer Countdown");
        JButton startTimerButton = new JButton("Start");
        ButtonExample stopTimerButton = new ButtonExample("Stop");


        //Add components to the panel
        mainPanel.add(lblStatusMessage, BorderLayout.SOUTH);
        mainPanel.add(stopTimerButton);
        mainPanel.add(startTimerButton);
//        mainPanel.add(TestBox);

        //guessField.addActionListener(new AnswerListener());

        //Add the panel to this JFrame
        add(mainPanel, BorderLayout.CENTER);
    }


    /********************************Menu example********************************/
    public JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_G);
        //menuItem = new JMenuItem("New Timer");
        //menu.add(menuItem);
        //menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        //menuItem.addActionListener(new MenuItemListener());

        addMenuItem(menuBar, menu, "Load Timer", KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        menu.addSeparator();
        addMenuItem(menuBar, menu, "New Timer", KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        menu.addSeparator();
        addMenuItem(menuBar, menu, "Save Timer", KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        menu.addSeparator();
        addMenuItem(menuBar, menu, "Exit", KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuBar.add(menu);

        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(menu);

        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);

        addMenuItem(menuBar, menu, "Setting", KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));

        menuBar.add(menu);

        return menuBar;
    }

    private void addMenuItem(JMenuBar menuBar, JMenu menu, String itemText, KeyStroke shortcut) {
        JMenuItem menuItem;

        menuItem = new JMenuItem(itemText);
        menuItem.setAccelerator(shortcut);
        menuItem.addActionListener(new MenuItemListener());

        menu.add(menuItem);
        //menuBar.add(menu);
    }

    private class MenuItemListener implements ActionListener {
        public MenuItemListener() {

        }

        public void actionPerformed(ActionEvent e) {
            //System.out.println(e.getSource());
            //System.out.println(e.getActionCommand());

            if (e.getActionCommand().equals("New Timer")) {
                // do nothing for now
            } else if (e.getActionCommand().equals("Load Timer")) {
                PomodoroTimerApp app = new PomodoroTimerApp();
                app.loadPomodoroTimer();
                app.runPomodoroTimer();

            } else if (e.getActionCommand().equals("Save Timer")) {
                PomodoroTimerApp app = new PomodoroTimerApp();
                app.savePomodoroTimer();

            } else if (e.getActionCommand().equals("Setting")) {
                SettingFrame settingFrame = new SettingFrame();

            } else {
                System.out.println("Coming soon");
            }

        }
    }

    private class MenuExample extends JMenuBar implements ActionListener {
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

    private class ButtonExample extends JButton implements ActionListener {

        public ButtonExample(String text) {
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

            ButtonExample addButton = new ButtonExample("Add");

            mainPanel.add(labelName);
            mainPanel.add(textName);
            mainPanel.add(labelInterval);
            mainPanel.add(textInterval);
            mainPanel.add(addButton);

            add(mainPanel, BorderLayout.CENTER);
        }
    }
}


//    public void addActionListener(ActionListener listener) {
//        listeners.add(listener);
//    }
//
//    private void notifyListeners(MouseEvent e) {
//        ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, new String(),
//                e.getWhen(), e.getModifiers());
//        synchronized (listeners) {
//            for (int i = 0; i < listeners.size(); i++) {
//                ActionListener tmp = listeners.get(i);
//                tmp.actionPerformed(evt);
//            }
//        }
//    }