package cn.com.infaith.module.enums;

public enum TableNoEnum {

    NO_1("百家乐01", 1),
    NO_2("百家乐02", 2),
    NO_3("百家乐03", 3),
    NO_4("百家乐04", 4),
    NO_5("百家乐05", 5),
    NO_6("百家乐06", 6),
    NO_7("百家乐07", 7),
    NO_8("百家乐08", 8),
    NO_9("百家乐09", 9),
    NO_10("百家乐10", 10),
    NO_11("百家乐11", 11),
    NO_12("百家乐12", 12),
    ;

    private String name;
    private int index;

    TableNoEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (TableNoEnum c : TableNoEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
