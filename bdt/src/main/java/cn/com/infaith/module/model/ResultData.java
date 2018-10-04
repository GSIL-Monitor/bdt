package cn.com.infaith.module.model;

import java.math.BigDecimal;
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

    private BigDecimal yxje;

    private BigDecimal yssy;

    private BigDecimal sjsy;

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

    public BigDecimal getYxje() {
        return yxje;
    }

    public void setYxje(BigDecimal yxje) {
        this.yxje = yxje;
    }

    public BigDecimal getYssy() {
        return yssy;
    }

    public void setYssy(BigDecimal yssy) {
        this.yssy = yssy;
    }

    public BigDecimal getSjsy() {
        return sjsy;
    }

    public void setSjsy(BigDecimal sjsy) {
        this.sjsy = sjsy;
    }
}