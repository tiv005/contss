package com.nm.pojo;

import java.security.spec.PSSParameterSpec;
import java.util.Arrays;

/**
 * 费用的实体类
 * @Author 容学斌
 * @Version 1.0
 **/
public class Cots {
    /**
    *费用编号
    */
    private Integer cId;

    /**
    *费用名称
    */
    private String cName;

    /**
    *费用描述
    */
    private String cDesc;

    /**
    *费用标记
    */
    private String cMark;


    /**
     * 多选按钮框
     */
    protected  String ck;

    /**
     *选中框的id，被选中用户选项，数组类型
     */
    private Integer[]  ids;

    public String getCk() {
        ck="<input type='checkbox'  id='ids' name='ids' value='"+this.getcId()+"' />";
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public String getcMark() {
        return cMark;
    }

    public void setcMark(String cMark) {
        this.cMark = cMark;
    }

    @Override
    public String toString() {
        return "Cots{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cDesc='" + cDesc + '\'' +
                ", cMark='" + cMark + '\'' +
                '}';
    }
}
