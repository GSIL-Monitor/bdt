package cn.com.infaith.module.enums;

/**
 * 图形类型枚举
 */
public enum MergeXZEnum {

    ALL(1),
    A(2),
    B(3),
    C(4),
    D(5),
    E(6),
    F(7),
    G(8);

    private int index;

    MergeXZEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
