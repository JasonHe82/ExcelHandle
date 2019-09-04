package com.shexd.Controller;

import com.shexd.Entity.DailyAttendance;
import com.shexd.Entity.ScoreDataTwo;
import com.shexd.util.DateUtils;
import com.shexd.util.ExportExcel;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("dailyAttendance")
public class DailyAttendanceController {

    private final Logger logger= LoggerFactory.getLogger(DailyAttendanceController.class);
    
    /**
    *
    * 导出Excel文件
    * @param response
    */
   @RequestMapping("exportDailyAttendance")
   public void exportDailyAttendance(HttpServletResponse response){
       try {
           String fileName ="Daily Attendance"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           List<DailyAttendance> dailyAttendanceList =	getDailyAttendance();                      
           new ExportExcel(DateUtils.getDate("yyyy") + "Daily Attendance", DailyAttendance.class,2).setDataList(dailyAttendanceList).write(response, fileName).dispose();
       } catch (Exception e) {
       }
   }

	private List<DailyAttendance> getDailyAttendance() {
		String pathname = "F:\\coco\\ScoreData.txt";
	       List<DailyAttendance> dailyAttendanceList=new ArrayList<>();
	       int count = 0;	       
	       try (FileReader reader = new FileReader(pathname);
	            BufferedReader br = new BufferedReader(reader)
	       ) {
	           String line;            
	           while ((line = br.readLine()) != null) {
	        	   count++;
	        	   System.out.println(count);
	        	   DailyAttendance dailyAttendance = new DailyAttendance();          	          		            		
	           	   String[] splitStr = line.trim().split(",");
	           	   
	           	   dailyAttendance.setCardNumber(splitStr[0]);
	           	   dailyAttendance.setAttendanceDate(splitStr[1]);
	           	   dailyAttendance.setAttendanceTime(splitStr[2]);
	           	   dailyAttendance.setComeout(splitStr[3]);
	           	   dailyAttendance.setJobNumber(splitStr[4]);
	           	   dailyAttendance.setUmNUmber(splitStr[5]);
	           	   dailyAttendance.setWay(splitStr[6]);
	           	   dailyAttendance.setResult(splitStr[7]);
	           	   dailyAttendance.setLatenessStatistics(getLatenessStatistics(dailyAttendance));
	           	   dailyAttendance.setOvertimeStatisticsHour(getOvertimeStatistics(dailyAttendance, 1));
	           	   dailyAttendance.setOvertimeStatisticsSecond(getOvertimeStatistics(dailyAttendance, 2));
	           	   dailyAttendanceList.add(dailyAttendance);
	           	//53D14247,2019/7/1,18:51, NULL ,601039,hezhipeng,刷卡开门,成功
	           	   
	           }  
	           System.out.println("count==="+count);
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       StringBuffer sb = new StringBuffer();
	       for(DailyAttendance da : dailyAttendanceList){
	    	   String attendanceDate = da.getAttendanceDate();
	    	   sb.append(attendanceDate).append(",");	    	   
	       }
	       String[] splitTempData = sb.toString().split(",");
	       String dateStr = StringUtils.EMPTY;
	         
	         for(String ln : splitTempData){
	        	 int index = dateStr.indexOf(ln);
	        	 if(!(index >= 0)){
	        		 dateStr+=ln+",";
	        	 }
	        }
	       
	         String[] splitDate = dateStr.split(",");
	         
	         List<DailyAttendance> newDailyAttendanceList = new ArrayList<>();
	         for(String spDate : splitDate){
	        	 List<DailyAttendance> newTempDailyAttendance = new ArrayList<>();
	        	 for(DailyAttendance daList : dailyAttendanceList){
	        		 if(spDate.equals(daList.getAttendanceDate())){
	        			 newTempDailyAttendance.add(daList);
	        		 }
	        	 }
	        	 newDailyAttendanceList.add(newTempDailyAttendance.get(0));
	        	 newDailyAttendanceList.add(newTempDailyAttendance.get(newTempDailyAttendance.size()-1));	        	 
	         }	         	       
	       return newDailyAttendanceList; 
	}
	
	/**
	 * 获取加班情况
	 * @param dailyAttendance
	 * @return
	 */
	private static String getOvertimeStatistics(DailyAttendance dailyAttendance,int flag) {
		String overTimeStatistics = StringUtils.EMPTY;
		String attendanceTime = dailyAttendance.getAttendanceTime();
		
		SimpleDateFormat format = new SimpleDateFormat("HH:ss");
		Date date1 = new Date();
		Date date2 = new Date();
		try {
			date1 = format.parse(attendanceTime);			
			date2 = format.parse("20:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long beginMillisecond = date1.getTime();
		long endMillisecond = date2.getTime();
		if(beginMillisecond > endMillisecond){
			if(flag == 1){
				overTimeStatistics = String.valueOf(((beginMillisecond - endMillisecond) / (60 * 60 * 1000)) % 24);
			}else{
				overTimeStatistics = String.valueOf(((beginMillisecond - endMillisecond) / 1000) % 60);
			}			
		}
		return overTimeStatistics;	
	}
	
	/**
	 * 获取迟到早退情况
	 * @param dailyAttendance
	 * @return
	 */
	private String getLatenessStatistics(DailyAttendance dailyAttendance) {
		String latenessStatistics = StringUtils.EMPTY;
		String attendanceTime = dailyAttendance.getAttendanceTime();
		
		SimpleDateFormat format = new SimpleDateFormat("HH:ss");
		Date attendanceTimeDate = new Date();
		Date morningDate = new Date();
		//Date afternoonDate = new Date();
		try {
			attendanceTimeDate = format.parse(attendanceTime);			
			morningDate = format.parse("09:00");
			//afternoonDate= format.parse("18:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long attendanceTimeSecond = attendanceTimeDate.getTime();
		long morningDateSecond = morningDate.getTime();
		//long afternoonDateSecond = afternoonDate.getTime();
		
		if(attendanceTimeSecond < morningDateSecond){
			latenessStatistics = "正常";			
		}
		return latenessStatistics;
	}  
	
	
	public static void main(String[] args) {
		DailyAttendance dailyAttendance = new DailyAttendance();
		dailyAttendance.setAttendanceTime("21:45");
		String latenessStatisticsHour = getOvertimeStatistics(dailyAttendance,1);
		String latenessStatisticsSec = getOvertimeStatistics(dailyAttendance,2);
		System.out.println(latenessStatisticsHour + "===" + latenessStatisticsSec);
	}
    
}
