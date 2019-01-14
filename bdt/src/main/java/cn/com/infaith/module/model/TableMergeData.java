package cn.com.infaith.module.model;

import java.util.Date;

public class TableMergeData {
    private Integer id;

    private String adminId;

    private Integer type;

    private Date createTime;

    private Integer tableNo;

    private Integer battleNo;

    private Integer fitNo;

    private String xjz;

    private String zjz;

    private String ljxjz;

    private String ljzjz;

    private Boolean isDelete;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public String getXjz() {
        return xjz;
    }

    public void setXjz(String xjz) {
        this.xjz = xjz == null ? null : xjz.trim();
    }

    public String getZjz() {
        return zjz;
    }

    public void setZjz(String zjz) {
        this.zjz = zjz == null ? null : zjz.trim();
    }

    public String getLjxjz() {
        return ljxjz;
    }

    public void setLjxjz(String ljxjz) {
        this.ljxjz = ljxjz == null ? null : ljxjz.trim();
    }

    public String getLjzjz() {
        return ljzjz;
    }

    public void setLjzjz(String ljzjz) {
        this.ljzjz = ljzjz == null ? null : ljzjz.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}