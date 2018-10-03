package cn.com.infaith.module.enums;

public enum TableStatusEnum {

    NEW("新局准备", 1),
    TZ("可投注", 2),
    KP("开牌", 3),
    ;

    private String name;
    private int index;

    TableStatusEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (TableStatusEnum c : TableStatusEnum.values()) {
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
