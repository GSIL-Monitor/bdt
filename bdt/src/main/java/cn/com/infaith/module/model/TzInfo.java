package cn.com.infaith.module.model;

import java.util.List;

public class TzInfo {

    private Integer tzxt;

    private Boolean started;

    private Integer fh;

    private String xh;

    private List<DopeManage> list;

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

    public List<DopeManage> getList() {
        return list;
    }

    public void setList(List<DopeManage> list) {
        this.list = list;
    }
}
