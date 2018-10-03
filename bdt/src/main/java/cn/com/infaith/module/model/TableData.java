package cn.com.infaith.module.model;

import java.util.Date;

public class TableData {
    private Integer id;

    private Date createTime;

    private Long createDate;

    private Integer tableNo;

    private Integer battleNo;

    private Integer fitNo;

    private String card;

    private Integer result;

    private String xgl;

    private String zgl;

    private String xtsl;

    private String ztsl;

    private String xjz;

    private String zjz;

    private String ljxjz;

    private String ljzjz;

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

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getXgl() {
        return xgl;
    }

    public void setXgl(String xgl) {
        this.xgl = xgl == null ? null : xgl.trim();
    }

    public String getZgl() {
        return zgl;
    }

    public void setZgl(String zgl) {
        this.zgl = zgl == null ? null : zgl.trim();
    }

    public String getXtsl() {
        return xtsl;
    }

    public void setXtsl(String xtsl) {
        this.xtsl = xtsl == null ? null : xtsl.trim();
    }

    public String getZtsl() {
        return ztsl;
    }

    public void setZtsl(String ztsl) {
        this.ztsl = ztsl == null ? null : ztsl.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getXjz() {
        return xjz;
    }

    public void setXjz(String xjz) {
        this.xjz = xjz;
    }

    public String getZjz() {
        return zjz;
    }

    public void setZjz(String zjz) {
        this.zjz = zjz;
    }

    public String getLjxjz() {
        return ljxjz;
    }

    public void setLjxjz(String ljxjz) {
        this.ljxjz = ljxjz;
    }

    public String getLjzjz() {
        return ljzjz;
    }

    public void setLjzjz(String ljzjz) {
        this.ljzjz = ljzjz;
    }
}