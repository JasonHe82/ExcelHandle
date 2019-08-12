package com.shexd.Entity;

import com.shexd.util.ExcelField;
import lombok.Data;

@Data
public class Race {	
    /**
	 * 比賽隊伍
	 */
	@ExcelField(title="比賽隊伍", align=3, sort=1)
    private String homeGuestTeam;
	
    /**
	 * 比赛名次备注
	 * 
	 */
	@ExcelField(title="备注", align=1, sort=2)
    private String raceRemark;
	
   @Override
    public String toString() {
        return "Football{" +
                "homeGuestTeam='" + homeGuestTeam + '\'' +
                ", raceRemark='" + raceRemark +                   
                '}';
    }
}
