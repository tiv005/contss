package com.nm.utiles;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化时间
 * @Author 容学斌
 * @Version 1.0
 **/
public class DateForMat implements Converter {

    /**
    *
    */
    private String data = "yyyy-MM-dd";

    public DateForMat() {
    }

    public DateForMat(String date) {
        this.data = date;
    }


    @Override
    public Object convert(Class aClass, Object o) {
        if(o==null || o.toString().equals("")){
            return null;
        }
        //进行判断类型
        //Date 是util包下面的
        if(aClass== Date.class){
            //进行格式化
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data);
            try {
                return simpleDateFormat.parse(String.valueOf(o));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
