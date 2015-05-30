package com.ktr.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhoubo on 2014/10/15.
 */
public class TimeUtil {

    //日期格式 － yyyy-MM-dd HH:mm:ss
    public static final String DATE_FORMAT_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    private final static DateFormat df = new SimpleDateFormat("MM-dd");
    
    private final static DateFormat dfN = new SimpleDateFormat("yyyy-MM-dd");
    
    private final static DateFormat dfNV = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // 日期格式
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

    // 日期格式 － yyyyMMddHHmmss
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmmss";

    // 日期格式 － hh:mm
    public static final String DATE_FORMAT_HHMM = "HH:mm";

    // 日期格式 － hh:mm:ss
    public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";

    // 日期格式 － hh:mm
    public static final String DATE_FORMAT_MMDD = "MM:dd";

    public static final String DATE_FORMAT_MMDD_STR = "MM月dd日";

    public static final int TIME_ONE_M = 1000;

    // 一分钟的秒数
    public static final int TIME_ONE_MINUTE = 60 * 1000;

    // 一小时的秒数
    public static final int TIME_ONE_HOUR = TIME_ONE_MINUTE * 60;
    // 日期格式 －MM月dd日
    public static final String DATE_FORMAT_MMDD_HHMM = "MM月dd日 HH:mm";
    /**
     * 得到昨天
     * @return
     */
    public static String getYesStr(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -1);
        Date date1 = calendar1.getTime();
        String todayStr = df.format(date1);
        return todayStr;
    }

    /**
     * 得到今天
     * @return
     */
    public static String getTodayStr(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 0);
        Date date1 = calendar1.getTime();
        String todayStr = df.format(date1);
        return todayStr;
    }
    
    /**
     * 得到今天
     * @return
     */
    public static String getTodayStr_send(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 0);
        Date date1 = calendar1.getTime();
        String todayStr = dfN.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getOneday(int whiche){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, whiche);
        Date date1 = calendar1.getTime();
        String todayStr = dfN.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getOnedayVlaue(int whiche){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, whiche);
        Date date1 = calendar1.getTime();
        String todayStr = dfNV.format(date1);
        return todayStr;
    }

    /**
     * 得到明天
     * @return
     */
    public static String getTomStr(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 1);
        Date date1 = calendar1.getTime();
        String todayStr = df.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getTomStr_send(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 1);
        Date date1 = calendar1.getTime();
        String todayStr = dfN.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getTom_1(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 2);
        Date date1 = calendar1.getTime();
        String todayStr = df.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getTom_1_send(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 2);
        Date date1 = calendar1.getTime();
        String todayStr = dfN.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getTom_2(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 3);
        Date date1 = calendar1.getTime();
        String todayStr = df.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getTom_2_send(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 3);
        Date date1 = calendar1.getTime();
        String todayStr = dfN.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getTom_3(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 4);
        Date date1 = calendar1.getTime();
        String todayStr = df.format(date1);
        return todayStr;
    }
    
    /**
     * 得到明天
     * @return
     */
    public static String getTom_3_send(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 4);
        Date date1 = calendar1.getTime();
        String todayStr = dfN.format(date1);
        return todayStr;
    }

    /**
     * 日期时间判断
     */
    public static boolean isCurrentDateTimeBetween( String startDateTimeString, String endDateTimeString ) {

        Date startDateTime = getFormatedDateTime( startDateTimeString, DATE_FORMAT_YYYYMMDD_HHMMSS );
        Date endDateTime = getFormatedDateTime( endDateTimeString, DATE_FORMAT_YYYYMMDD_HHMMSS );

        return isCurrentDateTimeBetween( startDateTime, endDateTime );
    }

    public static boolean isCurrentDateTimeBetweenHHMMSS(String targetTimeString, String startDateTimeString, String endDateTimeString){

        Date targetDateTime = getFormatedDateTime(targetTimeString, DATE_FORMAT_HHMMSS);
        Date startDateTime = getFormatedDateTime( startDateTimeString, DATE_FORMAT_HHMMSS );
        Date endDateTime = getFormatedDateTime( endDateTimeString, DATE_FORMAT_HHMMSS );

        return ( targetDateTime.after( startDateTime ) && targetDateTime.before( endDateTime ) );
    }

    /**
     * 日期时间判断
     */
    public static boolean isCurrentDateTimeBetween( Date startDateTime, Date endDateTime ) {

        Date date = new Date();
        return ( date.after( startDateTime ) && date.before( endDateTime ) );
    }

    /**
     * 当前日期时间判断（目标日期之后）
     */
    public static boolean isCurrentDateTimeAfter( String dateString, String dateFormatString ) {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat( dateFormatString );

            Date date = dateFormat.parse( dateString );

            return date.before( new Date() );
        }
        catch ( Exception e ) {

            return false;
        }
    }

    /**
     * 当前日期时间判断（目标日期之前）
     */
    public static boolean isCurrentDateTimeBefore( String dateString, String dateFormatString ) {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat( dateFormatString );

            Date date = dateFormat.parse( dateString );

            Date dateNow = new Date();

            return date.after( dateNow );
        }
        catch ( Exception e ) {

            return false;
        }
    }

    /**
     * 获取格式化的日期
     */
    public static Date getFormatedDateTime( String dateString, String formatString) {

        Date date = null;

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat( formatString );
            date = dateFormat.parse( dateString );
        }
        catch( Exception e ) {

            date = null;
        }

        return date;
    }
    
    public static String  getTime1(String time){
    	
    	String resultString = "";
    	
    	try {
			long timeL = Long.parseLong(time);
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1=new Date(timeL);
			resultString = format.format(d1);
		} catch (NumberFormatException e) {
			
			return "";
		}
    	
    	return resultString;
    }

    /**
     * 获取格式化的日期
     */
    public static String getFormatedDateTime( Date date, String formatString) {

        String dateString = null;

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat( formatString );
            dateString = dateFormat.format( date );
        }
        catch( Exception e ) {

            dateString = null;
        }

        return dateString;
    }

    public static String getTodayTimeStrMMDD(){

        Date date = getFormatedDateTime(getTodayStr(), TimeUtil.DATE_FORMAT_YYYYMMDD);

        return getFormatedDateTime(date, DATE_FORMAT_MMDD);
    }
    
    public static String getMakerStartTime(String startTime){
    	
    	Date start_date = getFormatedDateTime(startTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);
    	
    	long startTime_return = start_date.getTime() / 1000;
    	
    	return String.valueOf(startTime_return);
    }
    
    public static String getMakerEndTime(String endTime){
    	
//    	Date end_date = getFormatedDateTime(endTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);
//    	Date date_1970 = getFormatedDateTime("1970-01-01 00:00:00", TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);
    	Date end_date = getFormatedDateTime(endTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);
    	
    	long endTime_result = end_date.getTime() / 1000;
    	
    	return String.valueOf(endTime_result);
    }

    public static String getTomTimeStrMMDD(){

        Date date = getFormatedDateTime(getTomStr(), TimeUtil.DATE_FORMAT_YYYYMMDD);

        return getFormatedDateTime(date, DATE_FORMAT_MMDD);
    }

    public static String getProgramTimeStrMMDDStr(String startTime){

        Date date = getFormatedDateTime(startTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);

        return getFormatedDateTime(date, DATE_FORMAT_MMDD_STR);
    }

    public static String getProgramPlayTimeStrYYYYMMDDHHMMSS(String time){

        Date date = getFormatedDateTime(time, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);

        return getFormatedDateTime(date, DATE_FORMAT_YYYYMMDDHHMM);
    }
    /**
     * format 下个节目的开始时间
     * @param startTime
     * @return
     */
    public static String getProgramTimeStrHHMM(String startTime){

        Date date = getFormatedDateTime(startTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);

        return getFormatedDateTime(date, DATE_FORMAT_HHMM);
    }

    public static String getProgramTimeStrHHMMSS(String startTime){

        Date date = getFormatedDateTime(startTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);

        return getFormatedDateTime(date, DATE_FORMAT_HHMMSS);
    }


    /**
     * format 下个节目的开始日期
     * @param startTime
     * @return
     */
    public static String getProgramTimeStrMMDD(String startTime){

        Date date = getFormatedDateTime(startTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS);

        return getFormatedDateTime(date, DATE_FORMAT_MMDD);
    }

    /**
     * 播放进度转化
     * @param second
     * @return
     */
    public static String updateTextViewWithTimeFormat( int second) {

        second = second / 1000;

        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;

        String strTemp = null;

        if (0 != hh) {
            strTemp = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            strTemp = String.format("%02d:%02d", mm, ss);
        }

        return strTemp;
    }

    public static String isWillPrePlayScheduleRange(){

        Date startPreDateTime = new Date( ( new Date() ).getTime() - TIME_ONE_MINUTE );

        return TimeUtil.getFormatedDateTime( startPreDateTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS );
    }

    public static String isWillAfterPlayScheduleRange(){

        Date endAfterDateTime = new Date( ( new Date() ).getTime() + TIME_ONE_MINUTE );

        return TimeUtil.getFormatedDateTime( endAfterDateTime, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS );
    }


    public static String getNowTimeStr(){

        return TimeUtil.getFormatedDateTime(new Date(new Date().getTime()), TimeUtil.DATE_FORMAT_HHMMSS );
    }

    public static int getProgressMaxValue(String startTime, String endTime){

        Date startDateTime = getFormatedDateTime( startTime, DATE_FORMAT_YYYYMMDD_HHMMSS );
        Date endDateTime = getFormatedDateTime( endTime, DATE_FORMAT_YYYYMMDD_HHMMSS );

        int progressMaxValue = (int) (endDateTime.getTime() - startDateTime.getTime());

        return progressMaxValue;
    }

    public static String getProgressValue(String startTime, int currentProgress){

        Date startDateTime = getFormatedDateTime( startTime, DATE_FORMAT_YYYYMMDD_HHMMSS );

        long start = startDateTime.getTime() + currentProgress;

        Date startDate = new Date(start);

        return TimeUtil.getFormatedDateTime( startDate, TimeUtil.DATE_FORMAT_YYYYMMDD_HHMMSS );
    }

    /**
     * 广播获取频道详情时，获取当前的时间
     * @return
     */
    public static String getRadioCurrentDate() {
        Date now = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 可以方便地修改日期格式
        String nowDate = df.format(now);
        return nowDate;
    }


    /**
     * 获取格式化的当前时间
     */
    public static String getFormatedDateTime(String formatString) {

        String dateString = null;

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat( formatString );
            dateString = dateFormat.format(new Date());
        }
        catch( Exception e ) {

            dateString = null;
        }

        return dateString;
    }

    public static String getNowDateStr(){

        return TimeUtil.getFormatedDateTime(new Date(new Date().getTime()), TimeUtil.DATE_FORMAT_MMDD_HHMM );
    }

    /**
     * 获取下一秒时间
     * @param timeString
     * @return
     */
    public static String getNextSecondTime(String timeString){
        Date date = getFormatedDateTime(timeString,DATE_FORMAT_HHMMSS);
        long time = date.getTime() + 1000;
        Date newDate = new Date(time);
        return getFormatedDateTime(newDate, DATE_FORMAT_HHMMSS);
    }

    public static int getNextSecondTime(int s){

        return s + 1000;
    }

    /**
     *
     * @param startTimeStr 开始时间
     * @param endTimeStr 结束时间
     * @param progress 当年progress,总值为100
     * @return
     */
    public static String getDateStrByProgress(String startTimeStr,String endTimeStr,int progress){
        Date dateStart = getFormatedDateTime(startTimeStr,DATE_FORMAT_HHMMSS);
        long timeStart = dateStart.getTime();

        Date dateEnd = getFormatedDateTime(endTimeStr,DATE_FORMAT_HHMMSS);
        long timeEnd = dateEnd.getTime();

        long diff = timeEnd - timeStart;

        long result = (long)(diff * ( progress/100.0)) + timeStart;

        Date newDate = new Date(result);

        return getFormatedDateTime(newDate,DATE_FORMAT_HHMMSS);
    }

    /**
     * YYYYMMDDHHMMSS转换为HHMMSS
     * @param targetDate
     * @return
     */
    public static String dateYYYYMMDDHHMMSS2HHMMSS(String targetDate){
        Date date = getFormatedDateTime(targetDate,DATE_FORMAT_MMDD_HHMM);
        return getFormatedDateTime(date,DATE_FORMAT_YYYYMMDD_HHMMSS);
    }
    
    // TODO
    public static String getArtircleMakerEndTime(String dateStr1){
    	
    	long l_datestr = Long.parseLong(dateStr1) * 1000;
    	
    	Date date = new Date(l_datestr);
    	
//    	Date date = getFormatedDateTime(String.valueOf(l_datestr),DATE_FORMAT_MMDD_HHMM);
        return getFormatedDateTime(date,DATE_FORMAT_YYYYMMDD_HHMMSS);
    }

    /**
     * 计算两个HHMMSS格式日期的差
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    public static long calDiffBetweenHHMMSSData(String dateStr1){

    	Date now = new Date(); // now date.
    	long l_datestr = Long.parseLong(dateStr1) * 1000;
    	long interval = l_datestr - now.getTime();
    	
    	return interval / 1000;
    }

    /**
     * 判断日期是不是今天
     * @param dateStr
     * @return
     */
    public static boolean isDateToday(String dateStr){
        Date date = getFormatedDateTime(dateStr,DATE_FORMAT_YYYYMMDD_HHMMSS);
        String dateStrYYYYMMDD = getFormatedDateTime(date,DATE_FORMAT_YYYYMMDD);
        Date newDate = getFormatedDateTime(dateStrYYYYMMDD,DATE_FORMAT_YYYYMMDD);
        Date dateToaday = getFormatedDateTime(getTodayStr(),DATE_FORMAT_YYYYMMDD);
        if(newDate.compareTo(dateToaday)==0){
            return true;
        }
        return false;
    }
    
	public static String getTimeDiff(String params){
    	
    	try {
    		
			return getTimeStr(calDiffBetweenHHMMSSData(params));
			
		} catch (NumberFormatException e) {
			
			return "";
		}
    }

	public static String getTimeDiff2(String params){
    	
    	try {
    		
    		Date targetDateTime = getFormatedDateTime(params, DATE_FORMAT_YYYYMMDD);
    		Date now = new Date(); // now date.
        	long l_datestr = targetDateTime.getTime();
        	long interval = l_datestr - now.getTime();
        	
        	return getTimeStr(interval / 1000 );
			
		} catch (NumberFormatException e) {
			
			return "";
		}
    }
	
	public static String getTimeStr(long interval){
		
		long days = interval / (24 * 3600);
		long hours = 0;
		long minutes = 0;
		long second = 0;
		
		if(interval / 3600 > 0){
			
			hours = (interval / 3600) / 60;
			
		}else if(interval / 3600 == 0 && interval / 60 > 0){
			
			hours = 0;
			minutes = interval / 60;
			second = ((int)interval - minutes*60) % 60;
		}else{
			second = (int)interval % 60;
		}
		
		if(interval != 0.f){
			
			if(days == 0){
				
				return "小于一天";
//				return String.format("%d小时  %d分 %d秒", hours, minutes, second);
			}else if(days < 0){
				
				return "已经结束";
			} else{
				
				return String.format("%d天 ", days);
				
//				return String.format("%d天  %d小时", days, hours);
			}
		}else{
			
			return "已经结束";
		}
	}
}
