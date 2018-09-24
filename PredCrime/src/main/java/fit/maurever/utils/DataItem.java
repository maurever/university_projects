package fit.maurever.utils;

/**
 * Data item, used for inserting data to database.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class DataItem {

    private String name;
    private String type;
    private String format;
    private String value;
    private int columnIndex;

    public DataItem(String name, String type, String format, int columnIndex) {
        this.name = name;
        this.type = type;
        this.format = format;
        this.columnIndex = columnIndex;
    }

    public DataItem(String name, String type, String format, String value) {
        this.name = name;
        this.type = type;
        this.format = format;
        this.value = value;
    }

    public DataItem(DataItem item, String value) {
        this.name = item.getName();
        this.type = item.getType();
        this.format = item.getFormat();
        this.value = value;
    }

    public DataItem(String name, String type, String format) {
        this.name = name;
        this.type = type;
        this.format = format;
    }

    public DataItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public DataItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public boolean hasFormat() {
        return this.format != null && !"".equals(this.format);
    }

    @Override
    public String toString() {
        return "Item name:" + name + " type:" + type + " value:" + value + " format:" + format + " column:" + columnIndex;
    }
}
