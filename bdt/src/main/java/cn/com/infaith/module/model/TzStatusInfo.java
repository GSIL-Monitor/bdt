package cn.com.infaith.module.model;

public class TzStatusInfo {
    private Integer id;

    private Integer tzxt;

    private Integer tableNo;

    private Integer tzStatus;

    private String adminId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTzxt() {
        return tzxt;
    }

    public void setTzxt(Integer tzxt) {
        this.tzxt = tzxt;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    public Integer getTzStatus() {
        return tzStatus;
    }

    public void setTzStatus(Integer tzStatus) {
        this.tzStatus = tzStatus;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }
}