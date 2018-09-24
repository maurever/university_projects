package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.Model.ModelMemento;
import cz.fit.dpo.mvcshooter.view.Canvas;
import java.util.TimerTask;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Controller extends TimerTask {

    public static ModelMemento memento;
    Model model;
    Canvas view;

    public Controller(Model model, Canvas view) {
        this.model = model;
        this.view = view;
    }

    public void processInput(int keyChar, int keyCode) {
        switch (keyCode) {
            case 37: {
                if (memento != null) {
                    model.restore(memento);
                }
                break;
            }
            case 39: {
                model.shoot();
                break;
            }
            case 38: {
                model.changeCannonYPossition(-1);
                break;
            }
            case 40: {
                model.changeCannonYPossition(1);
                break;
            }
            case 83: {
                
            }

        }
        switch (keyChar) {
            case 7: {
                model.changeAngle(1);
                break;
            }
            case 6: {
                model.changeAngle(-1);
                break;
            }
            case 5: {
                model.changeSpeed(1);
                break;
            }
            case 4: {
                model.changeSpeed(-1);
                break;
            }
            case 3: {
                model.changeGravity(1);
                break;
            }
            case 2: {
                model.changeGravity(-1);
                break;
            }
            case 8: {
                model.changeCannonState();
                break;
            }
            case 9: {
                Controller.memento = model.save();
                break;
            }
        }
    }

    @Override
    public void run() {
        model.moveObjects();
        view.repaintView();
    }
}
