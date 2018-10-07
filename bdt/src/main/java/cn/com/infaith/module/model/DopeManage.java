package cn.com.infaith.module.model;

import io.swagger.annotations.ApiParam;

import java.util.Date;

public class DopeManage {
    private Integer id;

    private Integer tzxt;

    private String tzzh;
    @ApiParam(hidden = true)
    private String name;
    @ApiParam(hidden = true)
    private String account;

    private Integer tzje;

    private Integer tzfx;

    private String tzsjSection1;

    private String tzsjSection2;

    private String tableNo;

    private Boolean isDelete;

    private Date createTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTzsjSection1() {
        return tzsjSection1;
    }

    public void setTzsjSection1(String tzsjSection1) {
        this.tzsjSection1 = tzsjSection1 == null ? null : tzsjSection1.trim();
    }

    public String getTzsjSection2() {
        return tzsjSection2;
    }

    public void setTzsjSection2(String tzsjSection2) {
        this.tzsjSection2 = tzsjSection2 == null ? null : tzsjSection2.trim();
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}