package fit.maurever.layout;

import java.awt.Color;

/**
 * Map area
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class MapArea {

    private double x, y;
    private Color color;

    public static final double TOP = 935296.5;
    public static final double LEFT = 904583.9;
    public static final double BOTTOM = 1211312.2;
    public static final double RIGHT = 431729.0;

    private static final double DIV = 1000;
    private final double value;

    public MapArea(double x, double y, Color color, double value) {
        super();
        this.x = x;
        this.y = y;
        this.color = color;
        this.value = value;
    }

    public MapArea(double x, double y, double value) {
        super();
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public double getValue() {
        return value;
    }

    public int getXpr() {
        return (int) Math.round(2 * (x + LEFT) / DIV);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public int getYpr() {
        return (int) Math.round(2 * (-y - TOP) / DIV);
    }

    public void setY(double y) {
        this.y = y;
    }

}
