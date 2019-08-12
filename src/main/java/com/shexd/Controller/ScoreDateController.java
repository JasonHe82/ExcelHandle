package com.shexd.Controller;

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
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("scoreDate")
public class ScoreDateController {

    private final Logger logger= LoggerFactory.getLogger(ScoreDateController.class);
    
    /**
    *
    * 导出Excel文件
    * @param response
    */
   @RequestMapping("exportScore")
   public void exportScore(HttpServletResponse response){
       try {
           String fileName ="Football Score"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           List<ScoreDataTwo> scoreDataList =	getScoreList();                      
           new ExportExcel(DateUtils.getDate("yyyy") + "Football Score", ScoreDataTwo.class,2).setDataList(scoreDataList).write(response, fileName).dispose();
       } catch (Exception e) {
       }
   }
   
   public static List<ScoreDataTwo> getScoreList() {
	   String pathname = "I:\\football\\ScoreData.txt";
       List<ScoreDataTwo> scoreList=new ArrayList<>();
       int count = 0;
       
       try (FileReader reader = new FileReader(pathname);
            BufferedReader br = new BufferedReader(reader)
       ) {
           String line;            
           while ((line = br.readLine()) != null) {
        	   count++;
        	   System.out.println(count);
        	   ScoreDataTwo score = new ScoreDataTwo();          	          		            		
           	   String[] splitStr = line.trim().split(",");
           	   score.setRaceDate(splitStr[0]);
           	   score.setRaceTime(splitStr[1]);
           	   score.setRaceRound(splitStr[2]);
           	   score.setHomeTeam(splitStr[3]);
           	   score.setGueatTeam(splitStr[6]);
           	   score.setHalfCourtScore(splitStr[5]);
           	   score.setFullCourtScore(splitStr[4]);
           	   score.setHomeOdd(splitStr[7]);
           	   score.setPingOdd(splitStr[8]);
           	   score.setGuestOdd(splitStr[9]);           	   
           	   score.setGdCount(getGdCount(splitStr[4]));
           	   score.setWinningLosingTheGame(getWinningLosingTheGame(splitStr[4]));
           	   score.setWinningLosingTheGameOdd(getWinningLosingTheGameOdd(score));
           	   score.setGoalsResult(getGoalsResult(score));
           	   score.setHalfFullResult(getHalfFullResult(score));
           	   score.setHomeGuestWin(getHomeGuestWin(score));
           	   score.setZeroSealDoubleEntry(getZeroSealDoubleEntry(score));
           	   score.setSingleWinDoubleEntry(getSingleWinDoubleEntry(score));           
           	   scoreList.add(score);
           }  
           System.out.println("count==="+count);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return scoreList;      
   }

   /**
    * 获得净胜球数
    * @param gdCount
    * @return
    */
	private static String getGdCount(String fullScore) {
		String[] splitFullScore = fullScore.split(":");
    	int homeCourtScore = Integer.parseInt(splitFullScore[0]);
    	int guestCourtScore = Integer.parseInt(splitFullScore[1]);
    	String gdCount = StringUtils.EMPTY;
    	if(homeCourtScore > guestCourtScore) {
    		gdCount = String.valueOf(homeCourtScore - guestCourtScore);
    	}else if(homeCourtScore < guestCourtScore) {
    		gdCount = String.valueOf(guestCourtScore - homeCourtScore);
    	}else if(homeCourtScore == guestCourtScore && homeCourtScore > 0) {
    		gdCount = "进局";
    	}else {
    		gdCount = "零局";
    	}
		return gdCount;
	}
   
    private static String getWinningLosingTheGame (String fullScore) {
    	String parseStr =  StringUtils.EMPTY;
		String[] splitScore = fullScore.split(":");
    	int homeScore = Integer.parseInt(splitScore[0]);
    	int guestScore = Integer.parseInt(splitScore[1]);
    	
    	if(homeScore > guestScore) {
			parseStr = "胜";
		}else if(homeScore == guestScore) {
			parseStr = "平";
		}else if(homeScore < guestScore) {
			parseStr = "负";
		}
		return parseStr;
    }
    
    private static String getWinningLosingTheGameOdd (ScoreDataTwo score) {
    	String winningLosingTheGameOdd =  StringUtils.EMPTY;
		String[] splitScore = score.getFullCourtScore().split(":");
    	int homeScore = Integer.parseInt(splitScore[0]);
    	int guestScore = Integer.parseInt(splitScore[1]);
    	
    	if(homeScore > guestScore) {
			winningLosingTheGameOdd = score.getHomeOdd();
		}else if(homeScore == guestScore) {
			winningLosingTheGameOdd = score.getPingOdd();
		}else if(homeScore < guestScore) {
			winningLosingTheGameOdd = score.getGuestOdd();
		}   	
		return winningLosingTheGameOdd;
    }
   
    private static String getGoalsResult (ScoreDataTwo score) {
    	String goalsResult =  StringUtils.EMPTY;
		String[] splitScore = score.getFullCourtScore().split(":");
    	int homeScore = Integer.parseInt(splitScore[0]);
    	int guestScore = Integer.parseInt(splitScore[1]);    	
    	goalsResult = String.valueOf(homeScore+guestScore);
		return goalsResult;
    }
    
    private static String getHalfFullResult (ScoreDataTwo score) {
    	String halfFullResult =  StringUtils.EMPTY;
		
    	String[] splitHalfScore = score.getHalfCourtScore().split(":");
      	int homeHalfScore = Integer.parseInt(splitHalfScore[0]);
    	int guesHalftScore = Integer.parseInt(splitHalfScore[1]);
    	
    	String halfResult = StringUtils.EMPTY;
    	if(homeHalfScore > guesHalftScore) {
    		halfResult = "胜";
		}else if(homeHalfScore == guesHalftScore) {
			halfResult = "平";
		}else if(homeHalfScore < guesHalftScore) {
			halfResult = "负";
		}
    	
    	String[] splitScore = score.getFullCourtScore().split(":");
    	int homeScore = Integer.parseInt(splitScore[0]);
    	int guestScore = Integer.parseInt(splitScore[1]);    	
    	String fullResult = StringUtils.EMPTY;
    	
    	if(homeScore > guestScore) {
    		fullResult = "胜";
		}else if(homeScore == guestScore) {
			fullResult = "平";
		}else if(homeScore < guestScore) {
			fullResult = "负";
		}    	
    	halfFullResult = halfResult+fullResult;
		return halfFullResult;
    }
    
    private static String getHomeGuestWin (ScoreDataTwo score) {
    	String parseStr =  StringUtils.EMPTY;
		String[] splitScore = score.getFullCourtScore().split(":");
    	int homeScore = Integer.parseInt(splitScore[0]);
    	int guestScore = Integer.parseInt(splitScore[1]);
    	
    	if(homeScore > guestScore) {
			parseStr = "主队胜";
		}else if(homeScore == guestScore) {
			parseStr = "平局";
		}else if(homeScore < guestScore) {
			parseStr = "客队胜";
		}
		return parseStr;
    }
    
    private static String getZeroSealDoubleEntry (ScoreDataTwo score) {
    	String parseStr = StringUtils.EMPTY;
		String[] splitStr = score.getFullCourtScore().split(":");
		int zeroCount = 0;
		for(String s : splitStr) {
			if("0".equals(s)) {
				zeroCount++;
			}
		}		
    	if(zeroCount > 0) {
    		parseStr= "零封";
    	}else {
    		parseStr= "双进";
    	}
		return parseStr;
    }
    
    private static String getSingleWinDoubleEntry (ScoreDataTwo score) {
    	String parseStr = StringUtils.EMPTY;
		String[] splitStr = score.getFullCourtScore().split(":");
    	
    	int homeScore = Integer.parseInt(splitStr[0]);
    	int guestScore = Integer.parseInt(splitStr[1]);
    	
    	String oneStr = StringUtils.EMPTY;
    	if(homeScore > guestScore) {
    		oneStr = "主队胜";
		}else if(homeScore == guestScore) {
			oneStr = "平局";
		}else if(homeScore < guestScore) {
			oneStr = "客队胜";
		}
    	
    	String twoStr = StringUtils.EMPTY;
		int zeroCount = 0;
		for(String s : splitStr) {
			if("0".equals(s)) {
				zeroCount++;
			}
		}		
    	if(zeroCount > 0) {
    		twoStr= "零封";
    	}else {
    		twoStr= "双进";
    	}
    	
    	parseStr = oneStr+"&"+twoStr;
		return parseStr;
    }
    
}

