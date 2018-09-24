package fit.maurever.implementation.regression;

/**
 * Enum of regression models.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public enum RegressionTypes {

    SIMPLE("simple"),
    SINUS("sinus"),
    POLYNOM("polynom"),
    POLYNOM2("polynom2"),
    KRR("krr"),
    EXP("exponencial");

    private final String type;

    private RegressionTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
