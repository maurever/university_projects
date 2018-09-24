package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.controller.Controller;
import cz.fit.dpo.mvcshooter.model.Model;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class MainWindow extends JFrame {

    private Controller controller;

    public MainWindow() {
        try {
            Model model = new Model();
            Canvas view = new Canvas(model, 0, 0, 500, 500);
            model.addObserver(view);
            this.controller = new Controller(model, view);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Shooter");
            this.setResizable(false);

            Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(
                    (int) (window.getWidth() / 2 - 250),
                    (int) (window.getHeight() / 2 - 250));

            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    // delegate to controller
                    controller.processInput(Character.getNumericValue(evt.getKeyChar()), evt.getKeyCode());
                    System.out.println("key pressed: " + evt.getKeyCode() + " " + Character.getNumericValue(evt.getKeyChar()));
                }
            });
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(controller, 0, 10);
            this.add(view);
            this.pack();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void showHelp() {
        JOptionPane.showMessageDialog(this,
                "Controls: \n"
                + "here goes some description...");
    }
}
