package cn.com.infaith.module.enums;

public enum TableResultEnum {

    Z("庄", 1),
    X("闲", 2),
    H("和", 3),
    ;

    private String name;
    private int index;

    TableResultEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (TableResultEnum c : TableResultEnum.values()) {
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
