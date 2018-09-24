package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.view.MainWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Shooter {

    public static final int MAX_X = 480;
    public static final int MAX_Y = 480;
    public static final int MIN_CANNON_Y = 30;
    public static final int MAX_CANNON_Y = 450;
    public static final int NUMBER_OF_ENEMIES = 10;
    public static final int TOLERATION = 20;
    public static final int MIN_SPEED = 10;
    public static final int MAX_SPEED = 100;
    public static final int MIN_ANGLE = -90;
    public static final int MAX_ANGLE = 90;
    public static final int MIN_GRAVITY = 0;
    public static final int MAX_GRAVITY = 100;

    public static boolean simpleMode = false;

    public static void main(String[] args) {
        if (args.length != 0) {
            simpleMode = args[0].equals("simple");
            System.out.println("set simple mode:" + args[0].equals("simple"));
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

}
