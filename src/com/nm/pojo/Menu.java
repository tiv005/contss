package com.nm.pojo;

public class Menu {

    /**
     *编号
     */
    private  Integer m_Id;

    /**
     *菜单名称
     */
    private  String m_Name;

    /**
     *父级菜单
     */
    private  Integer p_MenuId;
    
    /**
     *请求地址
     */
    private  String m_Url;

    @Override
    public String toString() {
        return "Menu{" +
                "m_Id=" + m_Id +
                ", m_Name='" + m_Name + '\'' +
                ", p_MenuId=" + p_MenuId +
                ", m_Url='" + m_Url + '\'' +
                '}';
    }

    public Integer getM_Id() {
        return m_Id;
    }

    public void setM_Id(Integer m_Id) {
        this.m_Id = m_Id;
    }

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public Integer getP_MenuId() {
        return p_MenuId;
    }

    public void setP_MenuId(Integer p_MenuId) {
        this.p_MenuId = p_MenuId;
    }

    public String getM_Url() {
        return m_Url;
    }

    public void setM_Url(String m_Url) {
        this.m_Url = m_Url;
    }
}
