package com.shexd.Controller;

import com.google.common.collect.Lists;
import com.shexd.Entity.Race;
import com.shexd.Entity.RaceData;
import com.shexd.Entity.Score;
import com.shexd.Entity.ScoreData;
import com.shexd.util.DateUtils;
import com.shexd.util.ExportExcel;
import com.shexd.util.ImportExcel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("excel")
public class ExcelController {

    private final Logger logger= LoggerFactory.getLogger(ExcelController.class);

    @RequestMapping("/fileUploadFiles")
    public String getListaUtentiView(Model model){
    	model.addAttribute("excelHandle", "excelHandle");
        return "/excelHandle.jsp";
    }

    
    /**
     * 下载输入数据的模板
     *
     * @param response
     */
    @RequestMapping("import/template")
    public void importFileTemplate(HttpServletResponse response){
        try {
            //定义文件名称
            String fileName = "User_Data_import_template.xlsx";
            List<ScoreData> list = Lists.newArrayList();
            new ExportExcel("User Data", ScoreData.class, 1).setDataList(list).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 导入已经填好数据的Excel
     * @param multipartFile
     */
    @RequestMapping(value = "import",method = RequestMethod.POST)
    public void importFile(@RequestParam("file") MultipartFile multipartFile){
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(multipartFile, 1, 0);
            List<ScoreData> list = ei.getDataList(ScoreData.class);
            for (ScoreData user : list){
                try{
                    //to do: 保存处理数据
                    //userService.save(user);
                    logger.info(user.toString());
                    successNum++;
                }catch(ConstraintViolationException ex){
                    failureNum++;
                }catch (Exception ex) {
                    failureNum++;
                }
            }

            if (failureNum>0){
                failureMsg.insert(0, ", Failures: "+failureNum);
            }
            logger.info("Had Operation "+successNum+" Data;"+" "+"Failure "+failureNum);
        } catch (Exception e) {
            logger.error("导入失败",e);
        }
    }

    /**
    *
    * 导入已经填好数据的Excel
    * @param multipartFile
    */
   @RequestMapping(value = "importExcel",method = RequestMethod.POST)
   public void importExcelFile(@RequestParam(required=false,value="files") MultipartFile[] multipartFile) throws IOException{
       try {
           int successNum = 0;
           int failureNum = 0;
           StringBuilder failureMsg = new StringBuilder();
           for(MultipartFile mf : multipartFile) {
           	ImportExcel ei = new ImportExcel(mf, 1, 0);               
               List<ScoreData> list = ei.getDataList(ScoreData.class);
               for (ScoreData user : list){
                   try{
                       //to do: 保存处理数据
                       //userService.save(user);
                       logger.info(user.toString());
                       successNum++;
                   }catch(ConstraintViolationException ex){
                       failureNum++;
                   }catch (Exception ex) {
                       failureNum++;
                   }
               }
           }
                      
           if (failureNum>0){
               failureMsg.insert(0, ", Failures: "+failureNum);
           }
           logger.info("Had Operation "+successNum+" Data;"+" "+"Failure "+failureNum);
       } catch (Exception e) {
           logger.error("导入失败",e);
       }
   }
    
   /**
   *
   * 导入已经填好数据的Excel
   * @param multipartFile
   */
  @RequestMapping(value = "importRaceData",method = RequestMethod.POST)
  public void importRaceData(@RequestParam(required=false,value="files") MultipartFile[] multipartFile) throws IOException{
      try {
          int successNum = 0;
          int failureNum = 0;
          StringBuilder failureMsg = new StringBuilder();
          for(MultipartFile mf : multipartFile) {
          	ImportExcel ei = new ImportExcel(mf, 1, 0);               
              List<RaceData> list = ei.getDataList(RaceData.class);
              for (RaceData race : list){
                  try{
                      //to do: 保存处理数据
                      //userService.save(user);
                      logger.info(race.toString());
                      successNum++;
                  }catch(ConstraintViolationException ex){
                      failureNum++;
                  }catch (Exception ex) {
                      failureNum++;
                  }
              }
          }
                     
          if (failureNum>0){
              failureMsg.insert(0, ", Failures: "+failureNum);
          }
          logger.info("Had Operation "+successNum+" Data;"+" "+"Failure "+failureNum);
      } catch (Exception e) {
          logger.error("导入失败",e);
      }
  }
   
   
    /**
     *
     * 导出Excel文件
     * @param response
     */
    @RequestMapping("export")
    public void export(HttpServletResponse response){
        try {
            String fileName ="Football Data"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<ScoreData> scoreDataList =	readWriteFile();                      
            new ExportExcel(DateUtils.getDate("yyyy") + "Football Data", ScoreData.class,2).setDataList(scoreDataList).write(response, fileName).dispose();
        } catch (Exception e) {
        }
    }
       
    
    /**
    *
    * 导出Excel文件
    * @param response
    */
   @RequestMapping("exportScore")
   public void exportScore(HttpServletResponse response){
       try {
           String fileName ="Football Score"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           List<Score> scoreDataList =	getScoreList();                      
           new ExportExcel(DateUtils.getDate("yyyy") + "Football Score", Score.class,2).setDataList(scoreDataList).write(response, fileName).dispose();
       } catch (Exception e) {
       }
   }
   
   /**
   *
   * 导出Excel文件
   * @param response
   */
  @RequestMapping("exportRace")
  public void exportRaceNumber(HttpServletResponse response){
      try {
          String fileName ="Football Race Number"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
          List<Race> raceDataList =	getRaceList();                      
          new ExportExcel(DateUtils.getDate("yyyy") + "Football Race", Race.class,2).setDataList(raceDataList).write(response, fileName).dispose();
      } catch (Exception e) {
      }
  }   
   
   public static List<Race> getRaceList() {
	   String pathname = "E:\\ScoreData.txt";
       List<Race> raceList=new ArrayList<Race>();
       List<String> tempRaceTeam = new ArrayList<String>();
       Map<String,String> raceMap = new HashMap<String,String>();
       try (FileReader reader = new FileReader(pathname);
               BufferedReader br = new BufferedReader(reader)
          ) {
              String line;            
              while ((line = br.readLine()) != null) {           	        	          		            		
              	   String[] splitStr = line.trim().split(",");
              	   String raceTeam = splitStr[0];
              	   tempRaceTeam.add(raceTeam);
              	   raceMap.put(splitStr[2], splitStr[1]);              	   
              } 
              for(String raceTeam : tempRaceTeam) {
            	  String[] splitRace = raceTeam.split("vs");
            	  if(splitRace.length == 2) {
            		  String homeRaceTeam = splitRace[0];
            		  String guestRaceTeam = splitRace[1];
            		  String homeNumber = raceMap.get(homeRaceTeam);
            		  String guestNumber = raceMap.get(guestRaceTeam);
            		  Race race = new Race();  
            		  /*race.setHomeGuestTeam(raceTeam);
            		  race.setRaceRemark("联赛第" + homeNumber + "第" + guestNumber);*/
            		  raceList.add(race);
            	  }
              }                           
          } catch (IOException e) {
              e.printStackTrace();
          }
          //System.out.println(count);
          return raceList;        
   }
   
   
   public static List<Score> getScoreList() {
	   String pathname = "E:\\ScoreData.txt";
       List<Score> scoreList=new ArrayList<>();
       int count = 0;
       try (FileReader reader = new FileReader(pathname);
            BufferedReader br = new BufferedReader(reader)
       ) {
           String line;            
           while ((line = br.readLine()) != null) {
        	   count++;
        	   System.out.println(count);
        	   Score score = new Score();          	          		            		
           	   String[] splitStr = line.trim().split(",");
           	   score.setLeagueName(splitStr[0]);
           	   //score.setRaceDate(splitStr[1]);
           	   score.setRaceTime(splitStr[1]);
           	   score.setRaceNumber(splitStr[2]);
           	   score.setHomeGuestTeam(splitStr[3]);
           	   if(splitStr.length > 4) {
           		   score.setHomeOdd(splitStr[4]);
           		   score.setPingOdd(splitStr[5]);
           		   score.setGuestOdd(splitStr[6]);
           	   }
           	   scoreList.add(score);
           }  
           System.out.println("count==="+count);
       } catch (IOException e) {
           e.printStackTrace();
       }
       //System.out.println(count);
       return scoreList;      
   }
        
    public static List<ScoreData> readWriteFile() {
        String pathname = "E:\\ScoreData.txt";
        List<ScoreData> scoreDataList=new ArrayList<>();
        int count = 0;
        int erroCount = 0;
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;            
            while ((line = br.readLine()) != null) {
            	ScoreData score = new ScoreData();
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
}

