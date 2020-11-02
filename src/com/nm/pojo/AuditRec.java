package com.nm.pojo;

import java.util.Date;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class AuditRec {

    /**
    *审核记录编号
    */
    private Integer a_Id;

    /**
    *报销单编号
    */
    private Integer eId;
    
    /**
    *用户的编号
    */
    private Integer uId;

    /**
    *审核状态
    */
    private String a_State;
    
    /**
    *审核建议
    */
    private String a_Sugg;

    /**
     * 审核时间
     */
    private Date a_Date;
    
    /**
    *审核人名称
    */
    private String uName;

    private String a_StateHtml;

    public String getA_StateHtml() {
        return a_StateHtml;
    }

    public void setA_StateHtml(String a_StateHtml) {
        this.a_StateHtml = a_StateHtml;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return "AuditRec{" +
                "a_Id=" + a_Id +
                ", eId=" + eId +
                ", uId=" + uId +
                ", a_State='" + a_State + '\'' +
                ", a_Sugg='" + a_Sugg + '\'' +
                ", a_Date=" + a_Date +
                '}';
    }

    public Integer getA_Id() {
        return a_Id;
    }

    public void setA_Id(Integer a_Id) {
        this.a_Id = a_Id;
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

    public String getA_State() {
        return a_State;
    }

    public void setA_State(String a_State) {
        this.a_State = a_State;
    }

    public String getA_Sugg() {
        return a_Sugg;
    }

    public void setA_Sugg(String a_Sugg) {
        this.a_Sugg = a_Sugg;
    }

    public Date getA_Date() {
        return a_Date;
    }

    public void setA_Date(Date a_Date) {
        this.a_Date = a_Date;
    }
}
