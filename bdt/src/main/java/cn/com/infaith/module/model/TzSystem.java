package cn.com.infaith.module.model;

public class TzSystem {
    private Integer id;

    private Integer tzxt;

    private Boolean started;

    private Integer fh;

    private String xh;

    private Integer tableNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTzxt() {
        return tzxt;
    }

    public void setTzxt(Integer tzxt) {
        this.tzxt = tzxt;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Integer getFh() {
        return fh;
    }

    public void setFh(Integer fh) {
        this.fh = fh;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }
}