package com.nm.pojo;

import java.util.Arrays;
import java.util.Date;

/**
 * 报销单实体类
 * @Author 容学斌
 * @Version 1.0
 **/
public class Expemse {
    
    /**
    *报销单编号
    */
    private Integer eId;

    /**
    *用户编号
    */
    private Integer uId;

    /**
    *报销名称
    */
    private String eName;

    /**
     *报销总金额
     */
    private Float eToltel;

    /**
    *报销描述
    */
    private String eDesc;

    /**
     * 报销时间
     */
    private Date eDate;

    /**
    *报销状态
    */
    private String eState;


    private String eStateHtml;
    
    /**
    *报销标识
    */
    private String eMark;

    /**
    *费用编号
    */
    private Integer[] costIds;

    /**
     * 费用的金额
     */
    private Float[] detailMoneys;
    
    /**
    *费用的描述
    */
    private String[] detailDescs;
    
    /**
    *报销人名称
    */
    private String uName;

    /**
     * 报销开始时间
     */
    private Date startDate;

    /**
     * 报销结束时间
     */
    private Date endDate;

    /**
    *需要审核的状态码
    */
    private String[] auditeState;

    /**
    *经理进行审核路径地址
    */
    private String manageAduit;
    /**
    *财务审核
     * 1.经理已经通过了
     * 2.财务审核失败第二次审核
    */
    private String financAaudit;

    public String getFinancAaudit() {
        return financAaudit;
    }

    public void setFinancAaudit(String financAaudit) {
        this.financAaudit = financAaudit;
    }

    /**
    *报销单的操作
     * 进行查询和修改当前用户报销单
     * 所有报销都可以查询详情
     * 注意：在修改报销单的时候，只能修改保存未提交的和经理和财务审核失败的
    */
    private String updateUrl;

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getManageAduit() {
        return manageAduit;
    }

    public void setManageAduit(String manageAduit) {
        this.manageAduit = manageAduit;
    }

    public String geteStateHtml() {
        return eStateHtml;
    }

    public void seteStateHtml(String eStateHtml) {
        this.eStateHtml = eStateHtml;
    }

    public String[] getAuditeState() {
        return auditeState;
    }

    public void setAuditeState(String[] auditeState) {
        this.auditeState = auditeState;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public Float geteToltel() {
        return eToltel;
    }

    public void seteToltel(Float eToltel) {
        this.eToltel = eToltel;
    }

    public String geteDesc() {
        return eDesc;
    }

    public void seteDesc(String eDesc) {
        this.eDesc = eDesc;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public String geteState() {
        return eState;
    }

    public void seteState(String eState) {
        this.eState = eState;
    }

    public String geteMark() {
        return eMark;
    }

    public void seteMark(String eMark) {
        this.eMark = eMark;
    }

    public Integer[] getCostIds() {
        return costIds;
    }

    public void setCostIds(Integer[] costIds) {
        this.costIds = costIds;
    }

    public Float[] getDetailMoneys() {
        return detailMoneys;
    }

    public void setDetailMoneys(Float[] detailMoneys) {
        this.detailMoneys = detailMoneys;
    }

    public String[] getDetailDescs() {
        return detailDescs;
    }

    public void setDetailDescs(String[] detailDescs) {
        this.detailDescs = detailDescs;
    }

    @Override
    public String toString() {
        return "Expemse{" +
                "eId=" + eId +
                ", uId=" + uId +
                ", eName='" + eName + '\'' +
                ", eToltel=" + eToltel +
                ", eDesc='" + eDesc + '\'' +
                ", eDate=" + eDate +
                ", eState='" + eState + '\'' +
                ", eMark='" + eMark + '\'' +
                ", costIds=" + Arrays.toString(costIds) +
                ", detailMoneys=" + Arrays.toString(detailMoneys) +
                ", detailDescs=" + Arrays.toString(detailDescs) +
                ", uName='" + uName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", auditeState=" + Arrays.toString(auditeState) +
                '}';
    }

}
