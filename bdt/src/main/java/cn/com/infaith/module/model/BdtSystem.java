package cn.com.infaith.module.model;

import java.math.BigDecimal;

public class BdtSystem {

    private Integer id;

    private Integer ps;

    private BigDecimal phxs;

    private Integer tableNo;

    private BigDecimal yxje;
    private BigDecimal ysje;
    private BigDecimal sjje;

    private Boolean started;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public BigDecimal getPhxs() {
        return phxs;
    }

    public void setPhxs(BigDecimal phxs) {
        this.phxs = phxs;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public BigDecimal getYxje() {
        return yxje;
    }

    public void setYxje(BigDecimal yxje) {
        this.yxje = yxje;
    }

    public BigDecimal getYsje() {
        return ysje;
    }

    public void setYsje(BigDecimal ysje) {
        this.ysje = ysje;
    }

    public BigDecimal getSjje() {
        return sjje;
    }

    public void setSjje(BigDecimal sjje) {
        this.sjje = sjje;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }
}