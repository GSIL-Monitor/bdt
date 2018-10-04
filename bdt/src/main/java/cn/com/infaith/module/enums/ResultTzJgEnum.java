package cn.com.infaith.module.enums;

public enum ResultTzJgEnum {

    A("0.95", 1),
    B("0", 2),
    C("-1", 3),
    D("1", 4),
    ;

    private String name;
    private int index;

    ResultTzJgEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (ResultTzJgEnum c : ResultTzJgEnum.values()) {
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
