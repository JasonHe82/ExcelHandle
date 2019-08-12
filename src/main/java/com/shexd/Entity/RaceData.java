package com.shexd.Entity;

import com.shexd.util.ExcelField;
import lombok.Data;

@Data
public class RaceData {	
    /**
     * 排名
     */
	@ExcelField(title="排名", align=2, sort=1)
	private String ranking;
	
    /**
     * 球队
     */
	@ExcelField(title="球队", align=2, sort=2)
	private String footballTeam;
	
    /**
     * 总赛次数
     */
	@ExcelField(title="总赛次数", align=2, sort=3)
	private String totalRound;
	
	/**
	 * 总比赛胜次数
	 */
	@ExcelField(title="总胜次数", align=2, sort=4)
	private String totalWinningTimes;

	/**
	 * 总比赛平次数
	 */
	@ExcelField(title="总平次数", align=2, sort=5)
	private String totalMatchTimes;

	/**
	 * 总比赛负次数
	 */
	@ExcelField(title="总负次数", align=2, sort=6)
	private String totalNumberOfTimes;
		
	/**
	 * 总比赛进球数
	 */
	@ExcelField(title="总进球数", align=2, sort=6)
	private String totalNumberOfGoals;
	
	/**
	 * 总比赛失球数
	 */
	@ExcelField(title="总失球数", align=2, sort=7)
	private String totalTurnovers;
	
	/**
	 * 总比赛净胜球数
	 */
	@ExcelField(title="总净胜球数", align=2, sort=8)
	private String totalWinningGoal;
	
    /**
     * 主场总赛次数
     */
	@ExcelField(title="主场总赛次数", align=2, sort=9)
	private String homeCourtTotalRound;

	/**
	 * 主场胜次数
	 */
	@ExcelField(title="主场胜次数", align=2, sort=10)
	private String homeCourtTotalWinningTimes;

	/**
	 * 主场平次数
	 */
	@ExcelField(title="主场平次数", align=2, sort=11)
	private String homeCourtTotalMatchTimes;

	/**
	 * 主场负次数
	 */
	@ExcelField(title="主场负次数", align=2, sort=12)
	private String homeCourtTotalNumberOfTimes;
		
	/**
	 * 主场进球数
	 */
	@ExcelField(title="主场进球数", align=2, sort=13)
	private String homeCourtTotalNumberOfGoals;
	
	/**
	 * 主场失球数
	 */
	@ExcelField(title="主场失球数", align=2, sort=14)
	private String homeCourtTotalTurnovers;

	  /**
     * 客场总赛次数
     */
	@ExcelField(title="客场总赛次数", align=2, sort=15)
	private String guestCourtTotalRound;

	/**
	 * 客场胜次数
	 */
	@ExcelField(title="客场胜次数", align=2, sort=16)
	private String guestCourtTotalWinningTimes;

	/**
	 * 客场平次数
	 */
	@ExcelField(title="客场平次数", align=2, sort=17)
	private String guestCourtTotalMatchTimes;

	/**
	 * 客场负次数
	 */
	@ExcelField(title="客场负次数", align=2, sort=18)
	private String guestCourtTotalNumberOfTimes;
		
	/**
	 * 客场进球数
	 */
	@ExcelField(title="客场进球数", align=2, sort=19)
	private String guestCourtTotalNumberOfGoals;
	
	/**
	 * 客场失球数
	 */
	@ExcelField(title="客场失球数", align=2, sort=20)
	private String guestCourtTotalTurnovers;

	/**
	 * 积分
	 */
	@ExcelField(title="客场失球数", align=2, sort=21)
	private String accumulatePoints;
	
/*   @Override
    public String toString() {
        return "Football{" +
                "homeGuestTeam='" + homeGuestTeam + '\'' +
                ", raceRemark='" + raceRemark +                   
                '}';
    }*/
}
