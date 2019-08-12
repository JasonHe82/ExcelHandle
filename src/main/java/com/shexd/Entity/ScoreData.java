package com.shexd.Entity;

import com.shexd.util.ExcelField;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ScoreData {
	/**
	 * 比赛编号
	 */
	@NotNull(message = "Race Number 不能为空")
    @ExcelField(title="比赛编号", align=2, sort=1)
    private String raceNumber;

	/**
	 * 联赛名称
	 */
	@ExcelField(title="联赛名称", align=2, sort=2)
    private String leagueName;
	
	/**
	 * 比赛日期
	 */
	@ExcelField(title="比赛日期", align=2, sort=3)
    private String raceDate;

    /**
	 * 比赛时间
	 */
	@ExcelField(title="比赛时间", align=2, sort=4)
    private String raceTime;

    /**
	 * 主队
	 */
	@ExcelField(title="主队", align=3, sort=5)
    private String homeTeam;
	
    /**
	 * 客队
	 */
	@ExcelField(title="客队", align=1, sort=6)
    private String gueatTeam;
	
    /**
	 * 半场比分
	 */
	@ExcelField(title="半场比分", align=2, sort=7)
    private String halfCourtScore;
	
    /**
	 * 全场比分
	 */
	@ExcelField(title="全场比分", align=2, sort=8)
    private String fullCourtScore;
	
    /**
	 * 全场胜负平彩果
	 */
	@ExcelField(title="全场胜负平彩果", align=2, sort=9)
    private String winningLosingTheGame;
	
    /**
	 * 全场胜负平赔率
	 */
	@ExcelField(title="全场胜负平赔率", align=2, sort=10)
    private String winningLosingTheGameOdd;
	
    /**
	 * 让球胜平负让球
	 */
	@ExcelField(title="让球", align=2, sort=11)
    private String concedePoints;

	
    /**
	 * 让球胜平负彩果
	 */
	@ExcelField(title="让球胜平负彩果", align=2, sort=12)
    private String concedePointsResult;
	
    /**
	 * 让球胜平负赔率
	 */
	@ExcelField(title="让球胜平负赔率", align=2, sort=13)
    private String concedePointsOdd;
	
    /**
	 * 比分彩果
	 */
	@ExcelField(title="比分彩果", align=2, sort=14)
    private String scoreResult;
	
    /**
	 * 比分赔率
	 */
	@ExcelField(title="比分赔率", align=2, sort=15)
    private String scoreOdd;
	
    /**
	 * 总进球数彩果
	 */
	@ExcelField(title="总进球数彩果", align=2, sort=16)
    private String goalsResult;
	
    /**
	 * 总进球数赔率
	 */
	@ExcelField(title="总进球数赔率", align=2, sort=17)
    private String goalsOdd;
	
    /**
	 * 半全场彩果
	 */
	@ExcelField(title="半全场彩果", align=2, sort=18)
    private String halfFullResult;

	/**
	 * 半全场赔率
	 */
	@ExcelField(title="半全场赔率", align=2, sort=19)
    private String halfFullOdd;
	
	/**
	 * 主客队胜平负
	 */
	@ExcelField(title="主客队胜平负", align=2, sort=20)
    private String homeGuestWin;
	
	/**
	 * 零封双进
	 */
	@ExcelField(title="零封双进", align=2, sort=21)
    private String zeroSealDoubleEntry; 
	
	/**
	 * 独赢&双进
	 */
	@ExcelField(title="独赢&双进", align=2, sort=22)
    private String singleWinDoubleEntry;
	
	
	
	
    public String getRaceNumber() {
		return raceNumber;
	}




	public void setRaceNumber(String raceNumber) {
		this.raceNumber = raceNumber;
	}




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




	public String getHomeTeam() {
		return homeTeam;
	}




	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}




	public String getGueatTeam() {
		return gueatTeam;
	}




	public void setGueatTeam(String gueatTeam) {
		this.gueatTeam = gueatTeam;
	}




	public String getHalfCourtScore() {
		return halfCourtScore;
	}




	public void setHalfCourtScore(String halfCourtScore) {
		this.halfCourtScore = halfCourtScore;
	}




	public String getFullCourtScore() {
		return fullCourtScore;
	}




	public void setFullCourtScore(String fullCourtScore) {
		this.fullCourtScore = fullCourtScore;
	}




	public String getWinningLosingTheGame() {
		return winningLosingTheGame;
	}




	public void setWinningLosingTheGame(String winningLosingTheGame) {
		this.winningLosingTheGame = winningLosingTheGame;
	}




	public String getWinningLosingTheGameOdd() {
		return winningLosingTheGameOdd;
	}




	public void setWinningLosingTheGameOdd(String winningLosingTheGameOdd) {
		this.winningLosingTheGameOdd = winningLosingTheGameOdd;
	}




	public String getConcedePoints() {
		return concedePoints;
	}




	public void setConcedePoints(String concedePoints) {
		this.concedePoints = concedePoints;
	}




	public String getConcedePointsResult() {
		return concedePointsResult;
	}




	public void setConcedePointsResult(String concedePointsResult) {
		this.concedePointsResult = concedePointsResult;
	}




	public String getConcedePointsOdd() {
		return concedePointsOdd;
	}




	public void setConcedePointsOdd(String concedePointsOdd) {
		this.concedePointsOdd = concedePointsOdd;
	}




	public String getScoreResult() {
		return scoreResult;
	}




	public void setScoreResult(String scoreResult) {
		this.scoreResult = scoreResult;
	}




	public String getScoreOdd() {
		return scoreOdd;
	}




	public void setScoreOdd(String scoreOdd) {
		this.scoreOdd = scoreOdd;
	}




	public String getGoalsResult() {
		return goalsResult;
	}




	public void setGoalsResult(String goalsResult) {
		this.goalsResult = goalsResult;
	}




	public String getGoalsOdd() {
		return goalsOdd;
	}




	public void setGoalsOdd(String goalsOdd) {
		this.goalsOdd = goalsOdd;
	}




	public String getHalfFullResult() {
		return halfFullResult;
	}




	public void setHalfFullResult(String halfFullResult) {
		this.halfFullResult = halfFullResult;
	}




	public String getHalfFullOdd() {
		return halfFullOdd;
	}




	public void setHalfFullOdd(String halfFullOdd) {
		this.halfFullOdd = halfFullOdd;
	}




	public String getHomeGuestWin() {
		return homeGuestWin;
	}




	public void setHomeGuestWin(String homeGuestWin) {
		this.homeGuestWin = homeGuestWin;
	}




	public String getZeroSealDoubleEntry() {
		return zeroSealDoubleEntry;
	}




	public void setZeroSealDoubleEntry(String zeroSealDoubleEntry) {
		this.zeroSealDoubleEntry = zeroSealDoubleEntry;
	}




	public String getSingleWinDoubleEntry() {
		return singleWinDoubleEntry;
	}




	public void setSingleWinDoubleEntry(String singleWinDoubleEntry) {
		this.singleWinDoubleEntry = singleWinDoubleEntry;
	}




	@Override
    public String toString() {
        return "Football{" +
                "raceNumber='" + raceNumber + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", raceDate=" + raceDate +
                ", raceTime=" + raceTime + 
                 ", homeTeam=" + homeTeam +
                  ", gueatTeam=" + gueatTeam +
                   ", halfCourtScore=" + halfCourtScore +
                    ", fullCourtScore=" + fullCourtScore +
                     ", winningLosingTheGame=" + winningLosingTheGame +
                      ", winningLosingTheGameOdd=" + winningLosingTheGameOdd +
                       ", concedePoints=" + concedePoints +
                        ", concedePointsResult=" + concedePointsResult +
                         ", concedePointsOdd=" + concedePointsOdd +
                          ", scoreResult=" + scoreResult +
                           ", scoreOdd=" + scoreOdd +
                            ", goalsResult=" + goalsResult +
                             ", goalsOdd=" + goalsOdd +
                              ", halfFullResult=" + halfFullResult +
                               ", halfFullOdd=" + halfFullOdd +   
                                 ", homeGuestWin=" + homeGuestWin +    
                                   ", zeroSealDoubleEntry=" + zeroSealDoubleEntry +    
                                     ", singleWinDoubleEntry=" + singleWinDoubleEntry +    
                '}';
    }
}
