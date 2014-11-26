package cn.joymates.erp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 格式化当天日期yyyy-MM-dd HH:mm:ss
	 * @return 日期字符串
	 */
	public static String dateFormat(){
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(now);
	}
	/**
	 * 格式化日期：yyyy-MM-dd HH:mm:ss
	 * @param 传进一个日期
	 * @return
	 */
	public static String dateFormat(Date d){
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(d);
	}
	/**
	 * 根据传入的格式，格式化当前日期
	 * @param 日期格式
	 * @return
	 */
	public static String dateFormat(String str){
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat(str);
		return f.format(now);
	}
	/**
	 * 根据传入格式，格式化当前日期
	 * @param d 要格式化的日期
	 * @param str 日期格式
	 * @return
	 */
	public static String dateFormat(Date d,String str){
		SimpleDateFormat f=new SimpleDateFormat(str);
		return f.format(d);
	}
	/**
	 * 将日期字符转换日期
	 * @param arg0 日期字符
	 * @param arg1 传入日期字符格式
	 * @return
	 */
	public static Date dateFormat(String arg0,String arg1){
		Date date = new Date();
		SimpleDateFormat f=new SimpleDateFormat(arg1);
		try {
			date= f.parse(arg0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 更改日期字符格式
	 * @param 日期字符
	 * @param 传入日期字符格式
	 * @param 输出字符格式
	 * @return
	 */
	public static String dateFormat(String arg0,String arg1,String arg2){
		String fdate="";
		try {
			fdate = new SimpleDateFormat(arg2).format(new SimpleDateFormat(arg1).parse(arg0)) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fdate;
	}
	public static String dateYear(){
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy");
		return f.format(now);
	}
	 public static int compare_date(String DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                //System.out.println("dt1 在dt2前");
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	               // System.out.println("dt1在dt2后");
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	 /**
	  * 获取本周第一天的日期
	  * @return
	  */
	 public static String  weekFirstDate(){
		 Calendar call =Calendar.getInstance();
		 SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
		 call.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		 return dff.format(call.getTime());
	 }
	 
	 
	 /**
	  * 获取本周最后一天的日期
	  * @return
	  */
	 public static String  weekLasttDate(){
		 Calendar call =Calendar.getInstance();
		 SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
		//这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		 call.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		//增加一个星期，才是我们中国人理解的本周日的日期
		call.add(Calendar.WEEK_OF_YEAR, 1);
		 return dff.format(call.getTime());
	 }
	 
	 
	public static void main(String[] args) {
//		System.out.println(dateYear());
//		System.out.println(dateFormat("20130506","yyyyMMdd"));
//		
//		String a=dateFormat();
//		System.out.println(a);
//		System.out.println(dateFormat(a,"yyyy-MM-dd HH:mm:ss","yyyy-MM"));
		Calendar call =Calendar.getInstance();
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        
		System.out.println(dff.format(call.getTime())+"eeeeeeee");
		//这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		call.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		//增加一个星期，才是我们中国人理解的本周日的日期
		call.add(Calendar.WEEK_OF_YEAR, 1);
		   System.out.println(dff.format(call.getTime())+"3333333");
	}
	
	
	 
}
