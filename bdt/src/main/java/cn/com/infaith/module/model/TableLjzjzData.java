package cn.com.infaith.module.model;

import java.util.Date;

public class TableLjzjzData {
    private Integer id;

    private Integer type;

    private String adminId;

    private String ljzjz;

    private String ljxjz;

    private Date createTime;

    public String getLjxjz() {
        return ljxjz;
    }

    public void setLjxjz(String ljxjz) {
        this.ljxjz = ljxjz;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public String getLjzjz() {
        return ljzjz;
    }

    public void setLjzjz(String ljzjz) {
        this.ljzjz = ljzjz == null ? null : ljzjz.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}