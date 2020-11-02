package com.nm.pojo;

import java.util.Date;

/**
 * 底薪
 * @Author 容学斌
 * @Version 1.0
 **/
public class SalaryRec {
    
    /**
    *薪资表编号
    */
    private Integer rId;

    /**
    *用户编号
    */
    private Integer uId;

    /**
     * 薪资方法月份
     */
    private Date rMonth;

    /**
     * 薪资方法时间
     */
    private Date rDate;

    /**
     * 发放底薪
     */
    private Float rSalary;

    /**
     * 发放绩效
     */
    private Float rConn;
    
    /**
    *领取人
    */
    private String uName;

    /**
     * 奖金
     */
    private float rSalaryTotal;

    /**
     * 总提成
     */
    private float rConnTotal;

    /**
    *   账号
    */
    private String uAccout;

    public String getuAccout() {
        return uAccout;
    }

    public void setuAccout(String uAccout) {
        this.uAccout = uAccout;
    }

    @Override
    public String toString() {
        return "SalaryRec{" +
                "rId=" + rId +
                ", uId=" + uId +
                ", rMonth=" + rMonth +
                ", rDate=" + rDate +
                ", rSalary=" + rSalary +
                ", rConn=" + rConn +
                ", uName='" + uName + '\'' +
                ", uAccout='" + uAccout + '\'' +
                '}';
    }

    public float getrConnTotal() {
        return rConnTotal;
    }

    public void setrConnTotal(float rConnTotal) {
        this.rConnTotal = rConnTotal;
    }

    public float getrSalaryTotal() {
        return rSalaryTotal;
    }

    public void setrSalaryTotal(float rSalaryTotal) {
        this.rSalaryTotal = rSalaryTotal;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Date getrMonth() {
        return rMonth;
    }

    public void setrMonth(Date rMonth) {
        this.rMonth = rMonth;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public Float getrSalary() {
        return rSalary;
    }

    public void setrSalary(Float rSalary) {
        this.rSalary = rSalary;
    }

    public Float getrConn() {
        return rConn;
    }

    public void setrConn(Float rConn) {
        this.rConn = rConn;
    }

}
