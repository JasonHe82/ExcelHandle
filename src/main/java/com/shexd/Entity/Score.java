package com.shexd.Entity;

import com.shexd.util.ExcelField;
import lombok.Data;

@Data
public class Score {
	/**
	 * 联赛名称
	 */
	@ExcelField(title="联赛名称", align=2, sort=1)
    private String leagueName;
	
	/**
	 * 比赛日期
	 */
	@ExcelField(title="比赛日期", align=2, sort=2,type=2)
    private String raceDate;

    /**
	 * 比赛时间
	 */
	@ExcelField(title="比赛时间", align=2, sort=3)
    private String raceTime;

    /**
	 * 比赛輪次
	 */
	@ExcelField(title="比赛輪次", align=2, sort=4)
    private String raceNumber;
	
    /**
	 * 比賽隊伍
	 */
	@ExcelField(title="比賽隊伍", align=3, sort=5)
    private String homeGuestTeam;
	
    /**
	 * 胜赔率
	 */
	@ExcelField(title="胜赔率", align=1, sort=6)
    private String homeOdd;
	
    /**
	 * 平局赔率
	 */
	@ExcelField(title="平局赔率", align=2, sort=7)
    private String pingOdd;
	
    /**
	 * 负赔率
	 */
	@ExcelField(title="负赔率", align=2, sort=8)
    private String guestOdd;
	
	
	
   public String getLeagueName() {
		return leagueName;
	}



	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}



	public String getRaceDate() {
		return raceDate;
	}



	public void setRaceDate(String raceDate) {
		this.raceDate = raceDate;
	}



	public String getRaceTime() {
		return raceTime;
	}



	public void setRaceTime(String raceTime) {
		this.raceTime = raceTime;
	}



	public String getRaceNumber() {
		return raceNumber;
	}



	public void setRaceNumber(String raceNumber) {
		this.raceNumber = raceNumber;
	}



	public String getHomeGuestTeam() {
		return homeGuestTeam;
	}



	public void setHomeGuestTeam(String homeGuestTeam) {
		this.homeGuestTeam = homeGuestTeam;
	}



	public String getHomeOdd() {
		return homeOdd;
	}



	public void setHomeOdd(String homeOdd) {
		this.homeOdd = homeOdd;
	}



	public String getPingOdd() {
		return pingOdd;
	}



	public void setPingOdd(String pingOdd) {
		this.pingOdd = pingOdd;
	}



	public String getGuestOdd() {
		return guestOdd;
	}



	public void setGuestOdd(String guestOdd) {
		this.guestOdd = guestOdd;
	}



@Override
    public String toString() {
        return "Football{" +
                "raceNumber='" + raceNumber + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", raceDate=" + raceDate +
                ", raceTime=" + raceTime +                   
                '}';
    }
}
