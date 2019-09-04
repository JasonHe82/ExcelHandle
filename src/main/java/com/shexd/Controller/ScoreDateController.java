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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
           List<ScoreDataTwo> scoreDataList =	getNewScoreList();                      
           new ExportExcel(DateUtils.getDate("yyyy") + "Football Score", ScoreDataTwo.class,2).setDataList(scoreDataList).write(response, fileName).dispose();
       } catch (Exception e) {
       }
   }
   
   /**
   *
   * 导出Excel文件
   * @param response
   */
  @RequestMapping("exportData")
  public void exportData(HttpServletResponse response){
      try {
          String fileName ="Football Score"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
          List<ScoreDataTwo> scoreDataList =	getNewScoreDataList();                      
          new ExportExcel(DateUtils.getDate("yyyy") + "Football Score", ScoreDataTwo.class,2).setDataList(scoreDataList).write(response, fileName).dispose();
      } catch (Exception e) {
      }
  }
   
   
   /**
   *
   * 获取图表数据
   * @param response
   */
  @RequestMapping("printPicData")
  public void printPicData(HttpServletResponse response){
      try {
          String fileName ="Football Score"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
          List<ScoreDataTwo> scoreDataList =	getNewScoreList();                      
          int oneCount = 0;
          int twoCount = 0;
          int thirdCount = 0;
          int fourCount = 0;
          int fiveCount = 0;
          int pingCount = 0;
          int zeroCount = 0;
          System.out.println("分类,1球,2球,3球,4球,5球,进局,零局");
          Map<String,String> dataMap = new HashMap<String,String>();
          for (int i = 0; i < scoreDataList.size(); i++) {
        	  ScoreDataTwo tempData = scoreDataList.get(i);
        	  String raceRound = tempData.getRaceRound();
        	  String gdCount =  getGdCount(tempData);
        	  if("1".equals(gdCount)){
  				oneCount++;
  			}else if("2".equals(gdCount)){
  				twoCount++;
  			}else if("3".equals(gdCount)){
  				thirdCount++;
  			}else if("4".equals(gdCount)){
  				fourCount++;
  			}else if("5".equals(gdCount)){
  				fiveCount++;
  			}else if("进局".equals(gdCount)){
  				pingCount++;
  			}else if("零局".equals(gdCount)){
  				zeroCount++;
  			}
        	 
        	String nextRaceRound = scoreDataList.get(i+1).getRaceRound();
        	if(!raceRound.equals(nextRaceRound)){
                dataMap.put(raceRound, oneCount + "," + twoCount + "," + thirdCount + "," + fourCount + "," + fiveCount + "," + pingCount + "," + zeroCount);		
                System.out.println(dataMap);         
        		oneCount = 0;
                 twoCount = 0;
                 thirdCount = 0;
                 fourCount = 0;
                 fiveCount = 0;
                 pingCount = 0;
                 zeroCount = 0;	
    		}        	  
          }
/*          for(int i = 0; i < dataMap.size(); i++){
        	  System.out.println(dataMap.get(i));
          }*/
          
         // System.out.println("============");
         //System.out.println(dataMap);                           
      } catch (Exception e) {
    	  //System.out.println(e.getMessage());
      }
      System.out.println("=========");
  }
   
  /**
  *
  * 获取图表数据1
  * @param response
  */
 @RequestMapping("printPicDataOne")
 public void printPicDataOne(HttpServletResponse response){
     try {
         String fileName ="Football Score"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
         List<ScoreDataTwo> scoreDataList =	getNewScoreDataList();                      
     
         System.out.println("分类,双进,零封");
         Map<String,String> dataMap = new HashMap<String,String>();                
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < scoreDataList.size(); i++) {
       	  ScoreDataTwo tempData = scoreDataList.get(i);
       	  String leagueName  = tempData.getLeagueName();
       	  sb.append(leagueName).append(",");     	        	       	  
         }
         String[] splitTempData = sb.toString().split(",");
         String str = StringUtils.EMPTY;
         
         for(String ln : splitTempData){
        	 int index = str.indexOf(ln);
        	 if(!(index >= 0)){
        		 str+=ln+",";
        	 }
         }    
         String[] lastSplitLn = str.split(",");
         for(String lastStr: lastSplitLn){
        	  int zeroCount = 0;
		      int doubleCount = 0;
        	 for (int i = 0; i < scoreDataList.size(); i++) {
        		 ScoreDataTwo lastTempData = scoreDataList.get(i);
        		 String lastLeagueName  = lastTempData.getLeagueName();
        		 if(lastStr.equals(lastLeagueName)){
        			if("零封".equals(lastTempData.getZeroSealDoubleEntry())){
        				zeroCount++;
        			}else if("双进".equals(lastTempData.getZeroSealDoubleEntry())){
        				doubleCount++;
        			}        		      
        		 }
        	 }
        	 dataMap.put(lastStr, doubleCount + "," + zeroCount);
         }       
         Iterator iter = dataMap.entrySet().iterator();
         while (iter.hasNext()) {
         Map.Entry entry = (Map.Entry) iter.next();
         Object key = entry.getKey();
         Object val = entry.getValue();
         System.out.println(key + "," + val);   
         }            
     } catch (Exception e) {
     }
 } 
  
 /**
 *
 * 获取图表数据2
 * @param response
 */
@RequestMapping("printPicDataTwo")
public void printPicDataTwo(HttpServletResponse response){
    try {
        String fileName ="Football Score"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        List<ScoreDataTwo> scoreDataList =	getBallDataList();                     
    
        System.out.println("分类,比分");
        Map<String,String> dataMap = new HashMap<String,String>();                
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < scoreDataList.size(); i++) {
      	  ScoreDataTwo tempData = scoreDataList.get(i);
      	  String fullCourtScore  = tempData.getFullCourtScore();
      	  sb.append(fullCourtScore).append(",");     	        	       	  
        }
        String[] splitTempData = sb.toString().split(",");
        String str = StringUtils.EMPTY;
        
        for(String ln : splitTempData){
       	 int index = str.indexOf(ln);
       	 if(!(index >= 0)){
       		 str+=ln+",";
       	 }
        }    
        String[] lastSplitLn = str.split(",");
        for(String lastStr: lastSplitLn){
       	  int zeroCount = 0;
		  int doubleCount = 0;
       	 for (int i = 0; i < scoreDataList.size(); i++) {
       		 ScoreDataTwo lastTempData = scoreDataList.get(i);
       		 String lastLeagueName  = lastTempData.getLeagueName();
       		 if(lastStr.equals(lastLeagueName)){
       			if("零封".equals(lastTempData.getZeroSealDoubleEntry())){
       				zeroCount++;
       			}else if("双进".equals(lastTempData.getZeroSealDoubleEntry())){
       				doubleCount++;
       			}        		      
       		 }
       	 }
       	 dataMap.put(lastStr, doubleCount + "," + zeroCount);
        }       
        Iterator iter = dataMap.entrySet().iterator();
        while (iter.hasNext()) {
        Map.Entry entry = (Map.Entry) iter.next();
        Object key = entry.getKey();
        Object val = entry.getValue();
        System.out.println(key + "," + val);   
        }            
    } catch (Exception e) {
    }
} 
 
  
  
   public static List<ScoreDataTwo> getScoreList() {
	   String pathname = "F:\\coco\\ScoreData.txt";
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
           	   score.setGdCount(getGdCount(score));
           	   score.setWinningLosingTheGame(getWinningLosingTheGame(score));
           	   score.setWinningLosingTheGameOdd(getWinningLosingTheGameOdd(score));
           	   score.setGoalsResult(getGoalsResult(score));
           	   score.setHalfFullResult(getHalfFullResult(score));
           	   score.setHomeGuestWin(getHomeGuestWin(score));
           	   score.setZeroSealDoubleEntry(getZeroSealDoubleEntry(score));
           	   score.setSingleWinDoubleEntry(getSingleWinDoubleEntry(score));  
           	   score.setSizesBallsFirst(splitStr[11]);
           	   score.setSizesBalls(splitStr[12]);
           	   scoreList.add(score);
           }  
           System.out.println("count==="+count);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return scoreList;      
   }

   public static List<ScoreDataTwo> getNewScoreList() {
	   String pathname = "F:\\coco\\ScoreData.txt";
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
           	   score.setHalfCourtScore(splitStr[4]);
           	   score.setFullCourtScore(splitStr[5]);
           	   score.setHomeOdd(splitStr[8]);
           	   score.setPingOdd(splitStr[9]);
           	   score.setGuestOdd(splitStr[10]);           	   
           	   score.setGdCount(getGdCount(score));
           	   score.setWinningLosingTheGame(getWinningLosingTheGame(score));
           	   score.setWinningLosingTheGameOdd(getWinningLosingTheGameOdd(score));
           	   score.setGoalsResult(getGoalsResult(score));
           	   score.setHalfFullResult(getHalfFullResult(score));
           	   score.setHomeGuestWin(getHomeGuestWin(score));
           	   score.setZeroSealDoubleEntry(getZeroSealDoubleEntry(score));
           	   score.setSingleWinDoubleEntry(getSingleWinDoubleEntry(score));   
         	   score.setSizesBallsFirst(splitStr[11]);
           	   score.setSizesBalls(splitStr[12]);
           	   score.setLeagueName(splitStr[13]);
           	   scoreList.add(score);
           }  
           System.out.println("count==="+count);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return scoreList;      
   }
     
   
   public static List<ScoreDataTwo> getNewScoreDataList() {
	   String pathname = "F:\\coco\\ScoreData.txt";
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
           	   if(!splitStr[0].startsWith("@")){
           		score.setRaceDate(splitStr[0]);
           		score.setRaceTime(splitStr[1]);
           		score.setLeagueName(splitStr[2]);
           		score.setHomeTeam(splitStr[3]);
           		score.setGueatTeam(splitStr[6]);
           		score.setHalfCourtScore(splitStr[4]);
           		score.setFullCourtScore(splitStr[5]);
           	   score.setGdCount(getGdCount(score));
           	   score.setWinningLosingTheGame(getWinningLosingTheGame(score));
           	   score.setWinningLosingTheGameOdd(getWinningLosingTheGameOdd(score));
           	   score.setGoalsResult(getGoalsResult(score));
           	   score.setHalfFullResult(getHalfFullResult(score));
           	   score.setHomeGuestWin(getHomeGuestWin(score));
           	   score.setZeroSealDoubleEntry(getZeroSealDoubleEntry(score));
           	   score.setSingleWinDoubleEntry(getSingleWinDoubleEntry(score)); 
           	   scoreList.add(score);
           	   }          	   
           }  
           System.out.println("count==="+count);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return scoreList;      
   }
   
   
   public static List<ScoreDataTwo> getBallDataList() {
	   String pathname = "F:\\coco\\ScoreData.txt";
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
           		score.setRaceRound(splitStr[0]);
           		score.setHomeTeam(splitStr[1]);
           		score.setGueatTeam(splitStr[4]);
           		score.setHalfCourtScore(splitStr[2]);
           		score.setFullCourtScore(splitStr[3]);
           	   score.setGdCount(getGdCount(score));
           	   score.setWinningLosingTheGame(getWinningLosingTheGame(score));
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

