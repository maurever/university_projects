package cz.fit.dpo.mvcshooter.model.interfaces.visitor;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public interface Visitable {

    void accept(DrawVisitor visitor);
}
