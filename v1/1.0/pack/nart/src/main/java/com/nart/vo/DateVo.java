package com.nart.vo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DateVo {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;

    //时间戳转换为字符串
    public DateVo getDateToString(long time) {
        DateVo dateVo = new DateVo();
        Date d = new Date(time);
        //sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = sf.format(d);
//        System.out.println(format);

        return createDateVo(format, dateVo);
    }

    public DateVo createDateVo(String dateToString, DateVo dateVo){
        String nian = dateToString.substring(0, 4);
        int Nian = Integer.parseInt(nian);
//        System.out.println(Nian);
        dateVo.setYear(Nian);


        String yue = dateToString.substring(5, 7);
        int YUE = Integer.parseInt(yue);
//        System.out.println(YUE);
        dateVo.setMonth(YUE);

        String ri = dateToString.substring(8,10);
        int RI = Integer.parseInt(ri);
//        System.out.println(RI);
        dateVo.setDay(RI);

        String xiaoshi = dateToString.substring(11,13);
        int XIAOSHI = Integer.parseInt(xiaoshi);
//        System.out.println(XIAOSHI);
        dateVo.setHour(XIAOSHI);

        String fenzhong = dateToString.substring(14);
        int Fen = Integer.parseInt(fenzhong);
//        System.out.println(Fen);
        dateVo.setMin(Fen);

        return dateVo;
    }
}
