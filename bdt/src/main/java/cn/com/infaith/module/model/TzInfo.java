package cn.com.infaith.module.model;

import java.util.List;

public class TzInfo {

    private String adminId;

    private Integer tzxt;

    private Boolean started;

    private Integer fha;
    private Integer fhb;
    private Integer fhc;
    private Integer fhd;

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

    public Integer getFha() {
        return fha;
    }

    public void setFha(Integer fha) {
        this.fha = fha;
    }

    public Integer getFhb() {
        return fhb;
    }

    public void setFhb(Integer fhb) {
        this.fhb = fhb;
    }

    public Integer getFhc() {
        return fhc;
    }

    public void setFhc(Integer fhc) {
        this.fhc = fhc;
    }

    public Integer getFhd() {
        return fhd;
    }

    public void setFhd(Integer fhd) {
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
