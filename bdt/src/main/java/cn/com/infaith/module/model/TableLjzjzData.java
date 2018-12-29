package cn.com.infaith.module.model;

import java.util.Date;

public class TableLjzjzData {
    private Integer id;

    private String adminId;

    private String ljzjz;

    private Date createTime;

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
}