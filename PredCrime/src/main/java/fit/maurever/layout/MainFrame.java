package fit.maurever.layout;

import fit.maurever.implementation.regression.RegressionTypes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main Frame, set all panels and run application.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class MainFrame extends JFrame {

    private int NUMBER_PREDICTED_X = 5;
    private double PERIOD = 12.0;

    private WelcomePanel welcomeMainPanel;
    private JPanel filePanel;
    private DataPanel dataMainPanel;
    private RegressionPanel regressionMainPanel;
    private ManualPanel manualMainPanel;

    private JMenuBar menubar;
    public static StatusbarLabel statusbar;

    private Double[][] insertedValues;

    public RegressionTypes regressionType = RegressionTypes.SIMPLE;

    final private String LOOKANDFEEL = "System";

    public MainFrame() {
        initUI();
    }

    public JMenu getFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem item = new JMenuItem("Upload data to database");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanel(filePanel);
                statusbar.setText("Upload data clicked.");
            }
        });
        file.add(item);
        return file;
    }

    public JMenu getDataMenu() {
        JMenu data = new JMenu("Data");
        JMenuItem item1 = new JMenuItem("Generate data for regression");
        JMenuItem item2 = new JMenuItem("Select data for regression");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                statusbar.setText("Select data clicked. Loading information...");
                JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int option = chooser.showOpenDialog(MainFrame.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    insertedValues = null;
                    File selectedFile = chooser.getSelectedFile();
                    processSelectedFile(selectedFile);
                    regressionMainPanel.resetInsertedValues(insertedValues);
                    changePanel(regressionMainPanel);
                } else {
                    statusbar.setText("You canceled.");
                }
            }
        });
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusbar.setText("Generate data clicked.");
                changePanel(dataMainPanel);
            }
        });
        data.add(item1);
        data.add(item2);
        return data;
    }

    public JMenu getResultsMenu() {
        JMenu regression = new JMenu("Regression");
        JMenuItem item1 = new JMenuItem("View results");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusbar.setText("View results clicked.");
                regressionMainPanel.updateDataPanels();
                changePanel(regressionMainPanel);
            }
        });
        regression.add(item1);
        return regression;
    }

    public JMenu getHelpMenu() {
        JMenu help = new JMenu("Help");
        JMenuItem manualItem = new JMenuItem("Manual");
        manualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusbar.setText("Manual clicked.");
                changePanel(manualMainPanel);
            }
        });
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusbar.setText("About clicked.");
                changePanel(welcomeMainPanel);
            }
        });
        help.add(manualItem);
        help.add(aboutItem);
        return help;
    }

    public void processSelectedFile(File file) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            String[] lineArray;
            int counter = 0;
            while (input.readLine() != null) {
                counter++;
            }
            insertedValues = new Double[counter - 1][2];
            input = new BufferedReader(new FileReader(file));
            counter = -1;
            while ((line = input.readLine()) != null) {
                lineArray = line.split("~");
                if (lineArray.length == 2) {
                    if (counter == -1) {
                        regressionMainPanel.setAxisX(lineArray[0]);
                        regressionMainPanel.setAxisY(lineArray[1]);
                    } else {
                        insertedValues[counter][0] = Double.valueOf(lineArray[0]);
                        insertedValues[counter][1] = Double.valueOf(lineArray[1]);
                    }
                    counter++;
                    statusbar.setText("File is well-formed. See result.");
                } else {
                    insertedValues = null;
                    statusbar.setText("Wrong file format (in the file it must be row with 2 columns divide by '~', with header on the first line!).");
                    break;
                }
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error input file: " + file);
        }
    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(statusbar, BorderLayout.SOUTH);
        getContentPane().doLayout();
        update(getGraphics());
    }

    public final void initUI() {
        filePanel = new FilePanel();
        dataMainPanel = new DataPanel();
        regressionMainPanel = new RegressionPanel();
        manualMainPanel = new ManualPanel();

        welcomeMainPanel = new WelcomePanel();
        welcomeMainPanel.add(new JLabel("Welcome!"));
        welcomeMainPanel.setOpaque(true);
        add(welcomeMainPanel, BorderLayout.CENTER);

        menubar = new JMenuBar();
        menubar.add(getFileMenu());
        menubar.add(getDataMenu());
        menubar.add(getResultsMenu());
        menubar.add(getHelpMenu());
        setJMenuBar(menubar);

        statusbar = new StatusbarLabel();
        add(statusbar, BorderLayout.SOUTH);

        pack();
        setTitle("PredCrime");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize, ySize);
        repaint();
    }

    private void initLookAndFeel() {
        String lookAndFeel;
        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } else if (LOOKANDFEEL.equals("GTK")) {
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            } else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: "
                        + LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            try {
                UIManager.setLookAndFeel(lookAndFeel);
                UIManager.getDefaults().put("Button.disabledText", Color.GRAY);
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:"
                        + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            } catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel ("
                        + lookAndFeel
                        + ") on this platform.");
                System.err.println("Using the default look and feel.");
            } catch (Exception e) {
                System.err.println("Couldn't get specified look and feel ("
                        + lookAndFeel
                        + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MainFrame ex = new MainFrame();
        ex.setVisible(true);
        ex.initLookAndFeel();
        JFrame.setDefaultLookAndFeelDecorated(true);
    }
}
