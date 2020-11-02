package com.nm.pojo;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class ExpemseDetali {
    
    /**
    *报销明细编号
    */
    private Integer d_Id;

    /**
    *费用编号
    */
    private Integer cId;
    
    /**
    *报销编号
    */
    private Integer eId;

    /**
    *报销描述
    */
    private String d_Desc;

    /**
    *报销总金额
    */
    private Float d_Monery;

    /**
    *费用名称
    */
    private String cName;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public String toString() {
        return "ExpemseDetali{" +
                "d_Id=" + d_Id +
                ", cId=" + cId +
                ", eId=" + eId +
                ", d_Desc='" + d_Desc + '\'' +
                ", d_Monery=" + d_Monery +
                '}';
    }

    public Integer getD_Id() {
        return d_Id;
    }

    public void setD_Id(Integer d_Id) {
        this.d_Id = d_Id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getD_Desc() {
        return d_Desc;
    }

    public void setD_Desc(String d_Desc) {
        this.d_Desc = d_Desc;
    }

    public Float getD_Monery() {
        return d_Monery;
    }

    public void setD_Monery(Float d_Monery) {
        this.d_Monery = d_Monery;
    }
}
