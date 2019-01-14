package cn.com.infaith.module.model;

import java.util.List;

public class TzInfo {

    private String adminId;

    private Integer tzxt;

    private Boolean started;

    private String fha;
    private String fhb;
    private String fhc;
    private String fhd;

    private String xh;

    private List<DopeManage> list;

    public Integer getTzxt() {
        return tzxt;
    }

    public void setTzxt(Integer tzxt) {
        this.tzxt = tzxt;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public String getFha() {
        return fha;
    }

    public void setFha(String fha) {
        this.fha = fha;
    }

    public String getFhb() {
        return fhb;
    }

    public void setFhb(String fhb) {
        this.fhb = fhb;
    }

    public String getFhc() {
        return fhc;
    }

    public void setFhc(String fhc) {
        this.fhc = fhc;
    }

    public String getFhd() {
        return fhd;
    }

    public void setFhd(String fhd) {
        this.fhd = fhd;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public List<DopeManage> getList() {
        return list;
    }

    public void setList(List<DopeManage> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TzInfo{" +
                "adminId='" + adminId + '\'' +
                ", tzxt=" + tzxt +
                ", started=" + started +
                ", fha=" + fha +
                ", fhb=" + fhb +
                ", fhc=" + fhc +
                ", fhd=" + fhd +
                ", xh='" + xh + '\'' +
                ", list=" + list +
                '}';
    }
}
