package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.visitor.DrawVisitor;
import cz.fit.dpo.mvcshooter.model.interfaces.visitor.Visitable;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public abstract class GraphicsObject implements Visitable {

    private int x;
    private int y;

    public GraphicsObject() {
    }

    public GraphicsObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public abstract void accept(DrawVisitor visitor);
}
