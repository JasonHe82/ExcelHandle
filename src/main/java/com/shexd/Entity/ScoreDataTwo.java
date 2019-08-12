package com.shexd.Entity;

import com.shexd.util.ExcelField;

import lombok.Data;

@Data
public class ScoreDataTwo {
	
	/**
	 * 比赛日期
	 */
	@ExcelField(title="比赛日期", align=2, sort=1)
    private String raceDate;

    /**
	 * 比赛时间
	 */
	@ExcelField(title="比赛时间", align=2, sort=2)
    private String raceTime;

    /**
	 * 比赛轮次
	 */
	@ExcelField(title="比赛轮次", align=2, sort=3)
    private String raceRound;
	
    /**
	 * 主队
	 */
	@ExcelField(title="主队", align=3, sort=4)
    private String homeTeam;
	
    /**
	 * 客队
	 */
	@ExcelField(title="客队", align=1, sort=5)
    private String gueatTeam;
	
    /**
	 * 半场比分
	 */
	@ExcelField(title="半场比分", align=2, sort=6)
    private String halfCourtScore;
	
    /**
	 * 全场比分
	 */
	@ExcelField(title="全场比分", align=2, sort=7)
    private String fullCourtScore;
	
	/**
     * 净胜球
     */
	@ExcelField(title="净胜球", align=2, sort=8)
	private String gdCount;
	
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
	 * 总进球数彩果
	 */
	@ExcelField(title="总进球数彩果", align=2, sort=11)
    private String goalsResult;
	    	
    /**
	 * 半全场彩果
	 */
	@ExcelField(title="半全场彩果", align=2, sort=12)
    private String halfFullResult;
	
	/**
	 * 主客队胜平负
	 */
	@ExcelField(title="主客队胜平负", align=2, sort=13)
    private String homeGuestWin;
	
	/**
	 * 零封双进
	 */
	@ExcelField(title="零封双进", align=2, sort=14)
    private String zeroSealDoubleEntry; 
	
	/**
	 * 独赢&双进
	 */
	@ExcelField(title="独赢&双进", align=2, sort=15)
    private String singleWinDoubleEntry;
	
	/**
	 * 胜赔率
	 */
	@ExcelField(title="胜赔率", align=2, sort=16)
    private String homeOdd;
	
    /**
	 * 平局赔率
	 */
	@ExcelField(title="平局赔率", align=2, sort=17)
    private String pingOdd;
	
    /**
	 * 负赔率
	 */
	@ExcelField(title="负赔率", align=2, sort=18)
    private String guestOdd;
	
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

	public String getGoalsResult() {
		return goalsResult;
	}

	public void setGoalsResult(String goalsResult) {
		this.goalsResult = goalsResult;
	}


	public String getHalfFullResult() {
		return halfFullResult;
	}




	public void setHalfFullResult(String halfFullResult) {
		this.halfFullResult = halfFullResult;
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




	public String getRaceRound() {
		return raceRound;
	}


	public void setRaceRound(String raceRound) {
		this.raceRound = raceRound;
	}


	public String getGdCount() {
		return gdCount;
	}


	public void setGdCount(String gdCount) {
		this.gdCount = gdCount;
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
                ", raceDate=" + raceDate +
                ", raceTime=" + raceTime + 
                 ", homeTeam=" + homeTeam +
                  ", gueatTeam=" + gueatTeam +
                   ", halfCourtScore=" + halfCourtScore +
                    ", fullCourtScore=" + fullCourtScore +
                     ", winningLosingTheGame=" + winningLosingTheGame +
                      ", winningLosingTheGameOdd=" + winningLosingTheGameOdd +
                            ", goalsResult=" + goalsResult +
                              ", halfFullResult=" + halfFullResult +
                                 ", homeGuestWin=" + homeGuestWin +    
                                   ", zeroSealDoubleEntry=" + zeroSealDoubleEntry +    
                                     ", singleWinDoubleEntry=" + singleWinDoubleEntry +    
                '}';
    }
}
