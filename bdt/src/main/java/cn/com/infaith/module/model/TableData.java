package cn.com.infaith.module.model;

import io.swagger.annotations.ApiParam;

import java.util.Date;

public class TableData {

    @ApiParam(hidden = true)
    private Integer id;
    @ApiParam(hidden = true)
    private Date createTime;
    @ApiParam(value = "创建时间")
    private Long createDate;
    @ApiParam(value = "创建时间，不带时分秒", hidden = true)
    private Date created;
    @ApiParam(value = "桌号")
    private Integer tableNo;
    @ApiParam(value = "局号")
    private Integer battleNo;
    @ApiParam(value = "副号")
    private Integer fitNo;
    @ApiParam(value = "牌面数据-庄")
    private String card;
    @ApiParam(value = "牌面数据-闲")
    private String xianCard;
    @ApiParam(value = "牌面结果")
    private Integer result;
    @ApiParam(hidden = true)
    private String xgl;
    @ApiParam(hidden = true)
    private String zgl;
    @ApiParam(hidden = true)
    private String xtsl;
    @ApiParam(hidden = true)
    private String ztsl;
    @ApiParam(value = "牌面状态")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getXianCard() {
        return xianCard;
    }

    public void setXianCard(String xianCard) {
        this.xianCard = xianCard;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}