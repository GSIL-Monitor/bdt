package cn.com.infaith.module.model;

import io.swagger.annotations.ApiParam;

import java.util.Date;

public class TableInfo {

    @ApiParam(hidden = true)
    private Integer id;
    @ApiParam(hidden = true)
    private Date createTime;
    @ApiParam(value = "创建时间")
    private Long createDate;
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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
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
        this.card = card;
    }

    public String getXianCard() {
        return xianCard;
    }

    public void setXianCard(String xianCard) {
        this.xianCard = xianCard;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
