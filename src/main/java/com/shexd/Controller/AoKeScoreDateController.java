package com.shexd.Controller;

import com.shexd.Entity.AoKeScoreData;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("aokescoreDate")
public class AoKeScoreDateController {

    private final Logger logger= LoggerFactory.getLogger(AoKeScoreDateController.class);
    
    /**
    *
    * 导出Excel文件
    * @param response
    */
   @RequestMapping("export")
   public void export(HttpServletResponse response){
       try {
           String fileName ="Football Data"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           List<AoKeScoreData> scoreDataList =	readWriteFile();                      
           new ExportExcel(DateUtils.getDate("yyyy") + "Football Data", AoKeScoreData.class,2).setDataList(scoreDataList).write(response, fileName).dispose();
       } catch (Exception e) {
       	 System.out.println("error==" + e.getMessage());
       }
   }
   
    public static List<AoKeScoreData> readWriteFile() {
    	String pathname = "E:\\coco\\ScoreData.txt";
        List<AoKeScoreData> scoreDataList=new ArrayList<>();
        int count = 0;
        int erroCount = 0;
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;            
            while ((line = br.readLine()) != null) {
            	AoKeScoreData score = new AoKeScoreData();
            	boolean flag = line.contains("延期");
            	boolean flag1 = line.contains("隐藏");
            	boolean flag2 = line.contains("比赛筛选");
            	boolean flag3 = line.contains("全场");
            	boolean flag4 = line.contains("胜平负");
            	boolean flag5 = line.contains("赔率");
            	boolean flag6 = false;
            	erroCount++;
            	if(flag || flag1 || flag2 || flag3 || flag4 || flag5 || StringUtils.isEmpty(line)) {
            		flag6 = true;           		
            	}
            	
            	if(!flag6) {            		            		
            		String[] splitStr = line.trim().split(",");
            		System.out.println("erroCount==="+erroCount);
            		score.setRaceNumber(splitStr[0]);
            		score.setLeagueName(splitStr[1]);
            		score.setRaceDate(splitStr[2]);
            		score.setRaceTime(splitStr[3]);
            		score.setHomeTeam(splitStr[4]);
            		score.setGueatTeam(splitStr[5]);
            		score.setHalfCourtScore(splitStr[6].replaceAll("-", ":"));
            		score.setFullCourtScore(splitStr[7].replaceAll("-", ":"));
            		if(splitStr[7].length() == 1) {
            			score.setWinningLosingTheGame(parseString(splitStr[6]));
            			score.setConcedePointsResult(parseConcedeResult(splitStr[6],splitStr[9]));
            			score.setHomeGuestWin(parseHomeGuestStr(parseString(splitStr[6])));
            			score.setSingleWinDoubleEntry(parseHomeGuestStr(parseString(splitStr[6]))+"&"+parseScoreStr(splitStr[6]));
            		}else {
            			score.setWinningLosingTheGame(parseString(splitStr[7]));
            			score.setConcedePointsResult(parseConcedeResult(splitStr[7],splitStr[10]));
            			score.setHomeGuestWin(parseHomeGuestStr(parseString(splitStr[7])));
            			score.setSingleWinDoubleEntry(parseHomeGuestStr(parseString(splitStr[7]))+"&"+parseScoreStr(splitStr[7]));
            		}           		
            		score.setWinningLosingTheGameOdd(splitStr[9]);
            		score.setConcedePoints(splitStr[10]);
            		
            		score.setConcedePointsOdd(splitStr[12]);  
            		score.setScoreResult(splitStr[13]);
            		score.setScoreOdd(splitStr[14]);
            		score.setGoalsResult(splitStr[15]);
            		score.setGoalsOdd(splitStr[16]);
            		if(splitStr.length > 17) {           		
            	 		score.setHalfFullResult(parseHalfFullScore(splitStr[17]));               		
                		score.setZeroSealDoubleEntry(parseScoreStr(splitStr[7]));                		            		            	              		              		 
            		}  
            		if(splitStr.length > 18) {           		
                		score.setHalfFullOdd(splitStr[18]);     
                		scoreDataList.add(score);
            		}          		
            	}else if(flag){
            		count++;
            	}           	
            }  
            
            for (AoKeScoreData scoreData : scoreDataList){
              	String fullCourtScore = scoreData.getFullCourtScore();
            	String[] splitFullScore = fullCourtScore.split(":");
            	int homeCourtScore = Integer.parseInt(splitFullScore[0]);
            	int guestCourtScore = Integer.parseInt(splitFullScore[1]);
            	String gdCount = StringUtils.EMPTY;
            	if(homeCourtScore > guestCourtScore) {
            		gdCount = String.valueOf(homeCourtScore - guestCourtScore);
            	}else if(homeCourtScore < guestCourtScore) {
            		gdCount = String.valueOf(guestCourtScore - homeCourtScore);
            	}else if(homeCourtScore == guestCourtScore && homeCourtScore > 0) {
            		gdCount = "进和";
            	}else {
            		gdCount = "零和";
            	}
            	scoreData.setGdCount(gdCount);   
            } 
            
            System.out.println("count==="+count);
        } catch (IOException e) {
            e.printStackTrace();
        }                 
        return scoreDataList;
    }
  
    private static String parseConcedeResult (String fullCourtScore,String concedePoint) {
    	String parseStr = "";
    	int number = Integer.parseInt(concedePoint.substring(1));
    	boolean flagAdd = concedePoint.contains("+");
    	boolean flagOdd = concedePoint.contains("-");
    	String[] splitScore = fullCourtScore.split("-");
    	int homeScore = Integer.parseInt(splitScore[0]);
    	int guestScore = Integer.parseInt(splitScore[1]);
    	if(flagAdd) {
    		homeScore += number;
    	}
    	if(flagOdd) {
    		homeScore -= number;
    	}
    	
    	if(homeScore > guestScore) {
			parseStr = "胜";
		}else if(homeScore == guestScore) {
			parseStr = "平";
		}else if(homeScore < guestScore) {
			parseStr = "负";
		}
		return parseStr;   	    	
    }
    
    private static String parseString (String fullCourtScore) {
    	String parseStr = "";
		String[] splitScore = fullCourtScore.split("-");
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
    
    private static String parseHomeGuestStr (String str) {
    	String parseStr = "";
		if("胜".equals(str)) {
			parseStr = "主队胜";
		}else if("平".equals(str)) {
			parseStr = "平局";
		}else if("负".equals(str)) {
			parseStr = "客队胜";
		}
		return parseStr;
    }
    
    private static String parseScoreStr (String str) {
    	String parseStr = "";
		String[] splitStr = str.split("-");
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
    
    private static String parseHalfFullScore (String str) {
    	String parseStr = "";
		String[] splitStr = str.split("-");
		for(String s : splitStr) {
			String temp = "";
			if("0".equals(s)) {
				temp = "负";
			}else if("1".equals(s)) {
				temp =  "平";
			}else if("3".equals(s)) {
				temp = "胜";
			}
			parseStr += temp;
		}			
		return parseStr;
    }
  
  
   /**
    * 获得净胜球数
    * @param gdCount
    * @return
    */
	private static String getGdCount(ScoreDataTwo score) {
		String[] splitFullScore = score.getFullCourtScore().split(":");
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
   
    private static String getWinningLosingTheGame (ScoreDataTwo score) {
    	String parseStr =  StringUtils.EMPTY;
		String[] splitScore = score.getFullCourtScore().split(":");
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

