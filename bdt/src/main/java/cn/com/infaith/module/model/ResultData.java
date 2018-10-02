package cn.com.infaith.module.model;

import java.util.Date;

public class ResultData {
    private Integer id;

    private Date createTime;

    private Integer tableNo;

    private Integer battleNo;

    private Integer fitNo;

    private Integer tzfx;

    private Integer tzxt;

    private String tzzh;

    private String tzje;

    private Boolean tzzt;

    private Integer tzjg;

    private Integer yxje;

    private Integer yssy;

    private Integer sjsy;

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

    public String getTzje() {
        return tzje;
    }

    public void setTzje(String tzje) {
        this.tzje = tzje == null ? null : tzje.trim();
    }

    public Boolean getTzzt() {
        return tzzt;
    }

    public void setTzzt(Boolean tzzt) {
        this.tzzt = tzzt;
    }

    public Integer getTzjg() {
        return tzjg;
    }

    public void setTzjg(Integer tzjg) {
        this.tzjg = tzjg;
    }

    public Integer getYxje() {
        return yxje;
    }

    public void setYxje(Integer yxje) {
        this.yxje = yxje;
    }

    public Integer getYssy() {
        return yssy;
    }

    public void setYssy(Integer yssy) {
        this.yssy = yssy;
    }

    public Integer getSjsy() {
        return sjsy;
    }

    public void setSjsy(Integer sjsy) {
        this.sjsy = sjsy;
    }
}