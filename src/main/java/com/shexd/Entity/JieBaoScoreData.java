package com.shexd.Entity;

import com.shexd.util.ExcelField;
import lombok.Data;


@Data
public class JieBaoScoreData {
    /**
	 * 主队
	 */
	@ExcelField(title="主队", align=3, sort=1)
    private String homeTeam;
	
    /**
	 * 全场比分
	 */
	@ExcelField(title="全场比分", align=2, sort=2)
    private String fullCourtScore;
	
    /**
	 * 客队
	 */
	@ExcelField(title="客队", align=1, sort=3)
    private String gueatTeam;
	
    /**
	 * 半场比分
	 */
	@ExcelField(title="半场比分", align=2, sort=4)
    private String halfCourtScore;
			
	/**
     * 净胜球
     */
	@ExcelField(title="净胜球", align=2, sort=5)
	private String gdCount;
	

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

	public String getGdCount() {
		return gdCount;
	}

	public void setGdCount(String gdCount) {
		this.gdCount = gdCount;
	}

	@Override
    public String toString() {
        return "Football{" +       
                 ", homeTeam=" + homeTeam +
                  ", gueatTeam=" + gueatTeam +
                   ", halfCourtScore=" + halfCourtScore +
                    ", fullCourtScore=" + fullCourtScore +                  
                '}';
    }
}
