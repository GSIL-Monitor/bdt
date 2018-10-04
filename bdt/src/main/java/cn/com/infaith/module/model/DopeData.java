package cn.com.infaith.module.model;

import java.util.Date;

public class DopeData {
    private Integer id;

    private Date createTime;

    private String tzsjSection;

    private Integer tableNo;

    private Integer battleNo;

    private Integer fitNo;

    private Integer tzfx;

    private Integer tzxt;

    private String tzzh;

    private Integer tzje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    public Integer getBattleNo() {
        return battleNo;
    }

    public void setBattleNo(Integer battleNo) {
        this.battleNo = battleNo;
    }

    public Integer getFitNo() {
        return fitNo;
    }

    public void setFitNo(Integer fitNo) {
        this.fitNo = fitNo;
    }

    public Integer getTzfx() {
        return tzfx;
    }

    public void setTzfx(Integer tzfx) {
        this.tzfx = tzfx;
    }

    public Integer getTzxt() {
        return tzxt;
    }

    public void setTzxt(Integer tzxt) {
        this.tzxt = tzxt;
    }

    public String getTzzh() {
        return tzzh;
    }

    public void setTzzh(String tzzh) {
        this.tzzh = tzzh == null ? null : tzzh.trim();
    }

    public Integer getTzje() {
        return tzje;
    }

    public void setTzje(Integer tzje) {
        this.tzje = tzje;
    }

    public String getTzsjSection() {
        return tzsjSection;
    }

    public void setTzsjSection(String tzsjSection) {
        this.tzsjSection = tzsjSection;
    }
}