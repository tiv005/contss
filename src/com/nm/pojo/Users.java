package com.nm.pojo;

/***
 * 用户的实体类
 */
public class Users {

    /**
     *用户的编号
     */
    private  Integer uId;
    
     /**
      *角色编号
      */
     private  Integer r_Id;
     /**
      *姓名
      */
     private  String uName;
     /**
      *年龄
      */
     private  Integer uAge;
     /**
      *性别
      */
     private  String uSex;
     /**
      *手机号
      */
     private  String uPhone;
     //底薪
     private  float   uSalary;
     /**
      *账号
      */
     private  String uAccout;
     /**
      *密码
      */
     private  String uPwd;
     
     /**
      *标识
      */
     private  String uMrak;
     //角色的名称
     private  String r_Name;

     /**
      * 多选按钮框
      */
     protected  String ck;

     /**
      *选中框的id，被选中用户选项，数组类型
      */
     private Integer[]  ids;

 public Integer[] getIds() {
  return ids;
 }

 public void setIds(Integer[] ids) {
  this.ids = ids;
 }

 public String getCk() {
     ck="<input type='checkbox'  id='ids' name='ids' value='"+this.getuId()+"' />";
    return ck;
   }

   public void setCk(String ck) {

    this.ck = ck;
   }

 @Override
 public String toString() {
  return "Users{" +
          "uId=" + uId +
          ", r_Id=" + r_Id +
          ", uName='" + uName + '\'' +
          ", uAge=" + uAge +
          ", uSex='" + uSex + '\'' +
          ", uPhone='" + uPhone + '\'' +
          ", uSalary=" + uSalary +
          ", uAccout='" + uAccout + '\'' +
          ", uPwd='" + uPwd + '\'' +
          ", uMrak='" + uMrak + '\'' +
          ", r_Name='" + r_Name + '\'' +
          '}';
 }

 public String getR_Name() {
  return r_Name;
 }

 public void setR_Name(String r_Name) {
  this.r_Name = r_Name;
 }

 public Integer getuId() {
  return uId;
 }

 public void setuId(Integer uId) {
  this.uId = uId;
 }

 public Integer getR_Id() {
  return r_Id;
 }

 public void setR_Id(Integer r_Id) {
  this.r_Id = r_Id;
 }

 public String getuName() {
  return uName;
 }

 public void setuName(String uName) {
  this.uName = uName;
 }

 public Integer getuAge() {
  return uAge;
 }

 public void setuAge(Integer uAge) {
  this.uAge = uAge;
 }

 public String getuSex() {
  return uSex;
 }

 public void setuSex(String uSex) {
  this.uSex = uSex;
 }

 public String getuPhone() {
  return uPhone;
 }

 public void setuPhone(String uPhone) {
  this.uPhone = uPhone;
 }

 public float getuSalary() {
  return uSalary;
 }

 public void setuSalary(float uSalary) {
  this.uSalary = uSalary;
 }

 public String getuAccout() {
  return uAccout;
 }

 public void setuAccout(String uAccout) {
  this.uAccout = uAccout;
 }

 public String getuPwd() {
  return uPwd;
 }

 public void setuPwd(String uPwd) {
  this.uPwd = uPwd;
 }

 public String getuMrak() {
  return uMrak;
 }

 public void setuMrak(String uMrak) {
  this.uMrak = uMrak;
 }
}
