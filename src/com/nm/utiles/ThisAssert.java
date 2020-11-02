package com.nm.utiles;

/**
 * 断言
 */
public class ThisAssert {

    /**
     *
     * @param message  提示异常的信息
     * @throws ThisExcetion
     */
    public static  void  getThisExcetion(String message)throws ThisExcetion{
           throw  new ThisExcetion(message);
    }


    /***
     * 进行断言 字符串不能为空
     * String arges   进行断言的数据
     * String messge   提示用户的数据信息
     * //断言  一个字符串为空  一个对象为null 或者不为null  写法如下
     */
    public  static  String  isNotNull(String arges ,String messge)throws ThisExcetion{
      if(arges==null  || (arges=arges.trim()).length()==0){
          //则抛出异常
          getThisExcetion(messge);
      }
        return  arges;
    }



}
