package cn.com.infaith.module.enums;

/**
 * 图形类型枚举
 */
public enum MergeXZEnum {

    ALL(1),
    Other(2),
    A(3),
    B(4),
    C(5),
    D(6),
    E(7),
    F(8),
    G(9);

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
