package com.hy.sys.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hy.sys.utils.date.StatisticDate;

/** 
 * 统计相关工具类
 * @author He.Xu.Dong 
 * @Date 2016年9月21日 下午6:07:13 
 * @version 1.0 
 */
public  class StatisticUtils {
	
	final static String STATE_TIME=" 00:00:00";
	final static String END_TIME=" 23:59:59";	
	
	/**
	 * 获取最近7天的开始时间和结束时间
	 * @return
	 */
	public static String[] getLately7Day(){
		String[] days=new String[2];
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		days[1]=df.format(calendar.getTime()) + END_TIME;
		calendar.add(Calendar.DAY_OF_MONTH, -6);
		days[0]=df.format(calendar.getTime()) + STATE_TIME;
		return days;
	}
	
	/**
	 * 获取最近28天的开始时间和结束时间
	 * @return
	 */
	public static String[] getLately28Day(){
		String[] days=new String[2];
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		days[1]=df.format(calendar.getTime()) + END_TIME;
		calendar.add(Calendar.DAY_OF_MONTH, -27);
		days[0]=df.format(calendar.getTime()) + STATE_TIME;		
		return days;
	}
	
	/**
	 * 获取最近半年的开始时间和结束时间
	 * @return
	 */
	public static String[] getLatelyHalfYear() {
		String[] months=new String[2];
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		months[1]=df.format(date) + END_TIME;
        try {
        	calendar.add(Calendar.MONTH,-6);
            df = new SimpleDateFormat("yyyy-MM");
            months[0] = df.format(calendar.getTime()) + "-01"+STATE_TIME;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return months;
	}
	
	/**
	 * 获取最近一年的开始时间和结束时间
	 */
	public static String[] getLatelyOneYearNew(){
		String[] years=new String[2];
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		years[1]=df.format(date) + END_TIME;
		df = new SimpleDateFormat("yyyy-MM");
		calendar.add(Calendar.MONTH, -11);
		years[0]=df.format(calendar.getTime()) + "-01"+STATE_TIME;
		return years;
	}
	
	/**
	 * 获取最近一年的开始时间和结束时间
	 * @return
	 */
	public static String[] getLatelyOneYear(){
		String[] years=new String[2];
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		years[1]=df.format(date) + END_TIME;
		df = new SimpleDateFormat("yyyy-MM");
		calendar.add(Calendar.MONTH, -11); 
		years[0]=df.format(calendar.getTime()) + "-01"+STATE_TIME;
		return years;
	}
	
	/**
	 * 获取统计类型，1、按天数统计 2、按周统计 3、按月统计 4、按年统计
	 * @return
	 */
	public static int getStatisticType(String[] date){
		int defday=daysBetween(date[0],date[1]);
		if(defday<14){
			return 1;
		}else if(defday>=14&&defday<=62){
			return 2;
		}else if (defday>62&&defday<=365){
			return 3;
		}else{
			return 4;
		}
	}
	/**
	 * 根据时间段及类型获取统计的时间段
	 * @param date
	 * @param type
	 * @return
	 */
	public static List<StatisticDate> getStatisticDate(String[] date, int type) {
		List<StatisticDate> dateList = new ArrayList<StatisticDate>();
		try {
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(date[0].length()==10){
				date[0]=date[0]+STATE_TIME;
			}
			if(date[1].length()==10){
				date[1]=date[1]+END_TIME;
			}
			Date startDate = sd.parse(date[0]);
			Date endDate = sd.parse(date[1]);
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(startDate);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(endDate);
			switch (type) {
			case 1:
				//按日分类
				 while(startCal.getTime().before(endCal.getTime())){
					 StatisticDate tmpDate = new StatisticDate();	
					 sd=new SimpleDateFormat("yyyy-MM-dd");
					 tmpDate.setStartDate(sd.format(startCal.getTime())+STATE_TIME);
					 tmpDate.setEndDate(sd.format(startCal.getTime())+END_TIME);
					 tmpDate.setTitle(sd.format(startCal.getTime()));
					 tmpDate.setIdentify(sd.format(startCal.getTime()));
					 dateList.add(tmpDate);
					 startCal.add(Calendar.DATE, 1);					 
				 }
				break;
			case 2:
				 //按7天为一组分类
				 int addDay=7;
				 while(startCal.getTime().before(endCal.getTime())){
					 StatisticDate tmpDate = new StatisticDate();	
					 sd=new SimpleDateFormat("yyyy-MM-dd");
					 tmpDate.setStartDate(sd.format(startCal.getTime())+STATE_TIME);
					 String title=startCal.get(Calendar.DAY_OF_MONTH)+"日~";
					 String Identify=sd.format(startCal.getTime())+"|";
					 //如果时间差小于7天
					 int defday=daysBetween(sd.format(startCal.getTime()),sd.format(endCal.getTime()));
					 startCal.add(Calendar.DATE, addDay);
					 
					 if(defday>addDay){
						 Calendar tmpCal = Calendar.getInstance();
						 tmpCal.setTime(startCal.getTime());
						 tmpCal.add(Calendar.DATE, -1);
						 tmpDate.setEndDate(sd.format(tmpCal.getTime())+END_TIME);	
						 title+=tmpCal.get(Calendar.DAY_OF_MONTH)+"日";
						 Identify+=sd.format(tmpCal.getTime());
					 }else{
						 tmpDate.setEndDate(sd.format(endCal.getTime())+END_TIME);
						 title+=endCal.get(Calendar.DAY_OF_MONTH)+"日";
						 Identify+=sd.format(endCal.getTime());
					 }	
					 tmpDate.setTitle(title);
					 tmpDate.setIdentify(Identify);
					 dateList.add(tmpDate);
				 }				
				break;
			case 3:
				 //给一个时间段，按月拆分，前后多余，单独放在自己的月份中
				 boolean isfirst=true;
				 while(startCal.getTime().before(endCal.getTime())){
					 StatisticDate tmpDate = new StatisticDate();
					 SimpleDateFormat sdMonth=new SimpleDateFormat("yyyy-MM");
					 sd=new SimpleDateFormat("yyyy-MM-dd");
					 tmpDate.setTitle(startCal.get(Calendar.MONTH)+1+"月");
					 tmpDate.setIdentify(sdMonth.format(startCal.getTime()));
					 //开始时间
					 if(isfirst){
						 tmpDate.setStartDate(sd.format(startCal.getTime())+STATE_TIME);						 
					 }else{
						 Calendar tmpCal = Calendar.getInstance(); 
						 tmpCal.setTime(startCal.getTime());
						 tmpCal.set(Calendar.DATE, 1);   
						 tmpDate.setStartDate(sd.format(tmpCal.getTime())+STATE_TIME);
					 }
					 //结束时间判断
					 //判断是否为倒数第二个月份
					 Calendar isEndCal = Calendar.getInstance();
					 isEndCal.setTime(endCal.getTime());
					 isEndCal.add(Calendar.MONTH, -1);
					 sd=new SimpleDateFormat("yyyy-MM");
					 //当前循环起始年月
					 String date1=sd.format(startCal.getTime());
					 //判断是否是参数中倒数第2个年月
					 String date2=sd.format(isEndCal.getTime());
					 //取参数中最后一个时间中的倒数年月
					 String date3=sd.format(endCal.getTime());
					 sd=new SimpleDateFormat("yyyy-MM-dd");
					 //加一月
					 Calendar addModeCal = Calendar.getInstance();
					 addModeCal.setTime(startCal.getTime());
					 addModeCal.add(Calendar.MONTH, 1);	
					 if(date1.equals(date3)){
						 //最当月最后一天
						 tmpDate.setEndDate(sd.format(endCal.getTime())+END_TIME);
					 }else{						 
						 Calendar tmpCal = Calendar.getInstance();
						 tmpCal.setTime(startCal.getTime());
						 tmpCal.set(Calendar.DATE, 1);
						 tmpCal.add(Calendar.MONTH, 1); 
						 tmpCal.add(Calendar.DATE, -1); 
						 tmpDate.setEndDate(sd.format(tmpCal.getTime())+END_TIME);
					 }	
					 if(date1.equals(date2)&&endCal.getTime().before(addModeCal.getTime())){
						 //保存上一次记录
						 dateList.add(tmpDate);		
						 tmpDate  = new StatisticDate();
						 //因为加一个月后，下次不会再进入此方法，因为提前处理最后一个月
						 tmpDate.setTitle(endCal.get(Calendar.MONTH)+1+"月");
						 tmpDate.setIdentify(sdMonth.format(endCal.getTime()));
						 Calendar tmpCal = Calendar.getInstance();
						 tmpCal.setTime(endCal.getTime());
						 tmpCal.set(Calendar.DATE, 1);   
						 tmpDate.setStartDate(sd.format(tmpCal.getTime())+STATE_TIME);
						 tmpDate.setEndDate(sd.format(endCal.getTime())+END_TIME);
					 }
					 dateList.add(tmpDate);					 
					 startCal.add(Calendar.MONTH, 1);	
					 isfirst=false;
				 }
				break;
			case 4:
				 //按一年
				 boolean isfirstYear=true;
				 while(startCal.getTime().before(endCal.getTime())){
					 StatisticDate tmpDate = new StatisticDate();
					 SimpleDateFormat yearMonth=new SimpleDateFormat("yyyy");
					 sd=new SimpleDateFormat("yyyy-MM-dd");
					 tmpDate.setTitle(startCal.get(Calendar.YEAR)+"年");
					 tmpDate.setIdentify(yearMonth.format(startCal.getTime()));
					 //开始时间
					 if(isfirstYear){
						 tmpDate.setStartDate(sd.format(startCal.getTime())+STATE_TIME);
					 }else{
						 Calendar tmpCal = Calendar.getInstance(); 
						 tmpCal.setTime(startCal.getTime());
						 tmpCal.set(Calendar.MONTH, 0);  
						 tmpCal.set(Calendar.DATE, 1);   
						 tmpDate.setStartDate(sd.format(tmpCal.getTime())+STATE_TIME);
					 }
					 //结束时间判断
					 //判断是否为倒数第二个月份
					 Calendar isEndCal = Calendar.getInstance();
					 isEndCal.setTime(endCal.getTime());
					 isEndCal.add(Calendar.YEAR, -1);
					 sd=new SimpleDateFormat("yyyy");
					 String date1=sd.format(startCal.getTime());
					 String date2=sd.format(isEndCal.getTime());
					 String date3=sd.format(endCal.getTime());
					 sd=new SimpleDateFormat("yyyy-MM-dd");
					 //加一月
					 Calendar addModeCal = Calendar.getInstance();
					 addModeCal.setTime(startCal.getTime());
					 addModeCal.add(Calendar.YEAR, 1);	
					 if(date1.equals(date3)){
						 //最当月最后一天
						 tmpDate.setEndDate(sd.format(endCal.getTime())+END_TIME);
					 }else{						 
						 Calendar tmpCal = Calendar.getInstance();
						 tmpCal.setTime(startCal.getTime());
						 tmpCal.set(Calendar.DATE, 1);
						 tmpCal.set(Calendar.MONTH, 0);
						 tmpCal.add(Calendar.YEAR, 1); 
						 tmpCal.add(Calendar.DATE, -1); 
						 tmpDate.setEndDate(sd.format(tmpCal.getTime())+END_TIME);
					 }	
					 if(date1.equals(date2)&&endCal.getTime().before(addModeCal.getTime())){
						 //保存上一次记录
						 dateList.add(tmpDate);		
						 tmpDate  = new StatisticDate();
						 //因为加一年后，下次不会再进入此方法，因为提前处理最后一年
						 tmpDate.setTitle(endCal.get(Calendar.YEAR)+"年");
						 tmpDate.setIdentify(yearMonth.format(endCal.getTime()));
						 Calendar tmpCal = Calendar.getInstance();
						 tmpCal.setTime(endCal.getTime());
						 tmpCal.set(Calendar.DATE, 1);   
						 tmpCal.set(Calendar.MONTH, 0);
						 tmpDate.setStartDate(sd.format(tmpCal.getTime())+STATE_TIME);
						 tmpDate.setEndDate(sd.format(endCal.getTime())+END_TIME);
					 }									 
					 dateList.add(tmpDate);					 
					 startCal.add(Calendar.YEAR, 1);	
					 isfirstYear=false;
				 }
				break;
			default:
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList;
	}
	
	/**
	 * 获取两个日期相差的天数
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	/**
	 * 根据条件获取各个条件类的时间数组
	 * @param paramMap
	 * @return
	 */
	public static String[] getTimeArrs(Map<String, Object> paramMap){
		
		int datetype=2;
		String[] dates=null;
		
		String startInDate = "";
		String endInDate = "";
		try {
			datetype = Integer.parseInt(paramMap.get("datetype").toString().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//处理时间		
		switch (datetype) {
		case 2:
			//最近28天	
			dates=StatisticUtils.getLately28Day();
			break;
		case 3:
			//最近1年
			dates=StatisticUtils.getLatelyOneYearNew();
			break;
		case 9:
			//最近半年	
			dates=StatisticUtils.getLatelyHalfYear();
			break;
		case 4:
			//自定义时间
			startInDate = paramMap.get("startTime").toString();
			endInDate = paramMap.get("endTime").toString();
			String[] tmpDate=new String[]{startInDate,endInDate};
			int type=StatisticUtils.getStatisticType(tmpDate);
			datetype=type;
			dates=tmpDate;
			break;
		default:
		}
		return dates;
	}
	
	/**
	 * 根据条件获取各个条件类的时间数组
	 * @param paramMap
	 * @return
	 * @date 2016年10月26日 21:10
	 */
	public static String[] getTimeArrsNew(Map<String, Object> paramMap){
		
		int datetype=2;
		String[] dates=null;
		
		String startInDate = "";
		String endInDate = "";
		try {
			datetype = Integer.parseInt(paramMap.get("datetype").toString().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//处理时间		
		switch (datetype) {
		case 2:
			//最近28天	
			dates=StatisticUtils.getLately28Day();
			break;
		case 3:
			//最近1年
			dates=StatisticUtils.getLatelyOneYearNew();
			break;
		case 9:
			//最近半年	
			dates=StatisticUtils.getLatelyHalfYear();
			break;
		case 4:
			//自定义时间
			startInDate = paramMap.get("startTime").toString();
			endInDate = paramMap.get("endTime").toString();
			String[] tmpDate=new String[]{startInDate,endInDate};
			int type=StatisticUtils.getStatisticType(tmpDate);
			datetype=type;
			dates=tmpDate;
			break;
		default:
		}
		return dates;
	}
 
	public static void main(String[] args) {
		String[] a =new String[]{"2014-06-17 00:00:00","2016-08-22 23:59:59"};
		List<StatisticDate> kk=getStatisticDate(a,3);
		for(StatisticDate tmp:kk){
			System.out.print(tmp.getTitle()+"----"+tmp.getStartDate()+"----"+tmp.getEndDate()+"\r\n");
		}
	}

}
