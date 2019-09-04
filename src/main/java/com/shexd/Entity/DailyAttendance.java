package com.shexd.Entity;

import com.shexd.util.ExcelField;
import lombok.Data;

@Data
public class DailyAttendance {	
    /**
	 * 卡号
	 */
	@ExcelField(title="卡号", align=2, sort=1)
    private String cardNumber;
	
    /**
	 * 刷卡日期
	 * 
	 */
	@ExcelField(title="刷卡日期", align=2, sort=2)
    private String attendanceDate;
	
    /**
	 * 刷卡时间
	 * 
	 */
	@ExcelField(title="刷卡时间", align=2, sort=3)
    private String attendanceTime;
	
    /**
	 * 进出
	 * 
	 */
	@ExcelField(title="进出", align=2, sort=4)
    private String comeout;
	
    /**
	 * 工号
	 * 
	 */
	@ExcelField(title="工号", align=2, sort=5)
    private String jobNumber;
	
    /**
	 * UM号
	 * 
	 */
	@ExcelField(title="UM号", align=2, sort=6)
    private String umNUmber;
	
    /**
	 * 方式
	 * 
	 */
	@ExcelField(title="方式", align=2, sort=7)
    private String way;
	
    /**
	 * 结果
	 * 
	 */
	@ExcelField(title="结果", align=2, sort=8)
    private String result;
	
    /**
	 * 迟到统计
	 * 
	 */
	@ExcelField(title="迟到统计", align=2, sort=9)
    private String latenessStatistics;
	
    /**
	 * 加班小时统计
	 * 
	 */
	@ExcelField(title="加班小时统计", align=2, sort=10)
    private String overtimeStatisticsHour;
	
	/**
	 * 加班分钟统计
	 */
	@ExcelField(title="加班分钟统计", align=2, sort=11)
	private String overtimeStatisticsSecond;	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getComeout() {
		return comeout;
	}

	public void setComeout(String comeout) {
		this.comeout = comeout;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getUmNUmber() {
		return umNUmber;
	}

	public void setUmNUmber(String umNUmber) {
		this.umNUmber = umNUmber;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLatenessStatistics() {
		return latenessStatistics;
	}

	public void setLatenessStatistics(String latenessStatistics) {
		this.latenessStatistics = latenessStatistics;
	}

	

	public String getOvertimeStatisticsHour() {
		return overtimeStatisticsHour;
	}

	public void setOvertimeStatisticsHour(String overtimeStatisticsHour) {
		this.overtimeStatisticsHour = overtimeStatisticsHour;
	}

	public String getOvertimeStatisticsSecond() {
		return overtimeStatisticsSecond;
	}

	public void setOvertimeStatisticsSecond(String overtimeStatisticsSecond) {
		this.overtimeStatisticsSecond = overtimeStatisticsSecond;
	}

	public String getAttendanceTime() {
		return attendanceTime;
	}

	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	
	
	
	
}
