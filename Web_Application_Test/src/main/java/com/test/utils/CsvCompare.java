package com.test.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


/**Compares 2 csv file with data and puts the data difference in a third csv file
 * @author 1555976
 *
 */
public class CsvCompare{
	

	//public static String path="C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"BasicDataCapture.csv";
	//public static HashMap<String,String> listMap = new HashMap<String , String>();
	static List<HashMap<String,String>> listMap = new ArrayList<HashMap<String,String>>();//this contains the values in a csv as hashmap
	static List<HashMap<String,String>> listMap2 = new ArrayList<HashMap<String,String>>();
	//static List<HashMap<String,String>> differenceValues = new ArrayList<HashMap<String,String>>();
	//static Map<String,Object> CheckerData= new HashMap<String,Object>();//result
	static Map<String, Object> checkerValues=new HashMap<String, Object>();
	static Map<String,Object> testData= new HashMap<String,Object>();
	////write/////



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"BasicDataCapture.csv
		//List<HashMap<String,String>> listMap = new ArrayList<HashMap<String,String>>();

		System.out.println(listMap.size());
		convertCsvToHashMap("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"BasicDataCapture.csv",listMap,",");
		convertCsvToHashMap("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"BlindDataCapture.csv",listMap2,",");
		System.out.println(listMap.size());
		System.out.println(listMap2.size());
		getValuesFromList(listMap,listMap2);//compares data between list
		
		
//		for(String k:checkerValues.keySet()){
//			System.out.println(checkerValues.get(k));
//		}
	}

	
	
	public void doDataCompare(String csvSheet1,String csvSheet2,String newCsv) throws IOException{
		convertCsvToHashMap("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"BasicDataCapture.csv",listMap,",");
		convertCsvToHashMap("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"BlindDataCapture.csv",listMap2,",");
		getValuesFromList(listMap,listMap2);
	}
	

	/** This method converts the given 2 data sheets in to hashmap for comparison
	 * @param dataFile1= path of the dataSheet1
	 * @param dataFile2= path of the datasheet2
	 * @throws FileNotFoundException 
	 */
	public static void convertCsvToHashMap(String dataFile,List<HashMap<String,String>> listOfMapName,String delimiter) throws FileNotFoundException{
		listOfMapName.clear();
		Scanner scanIn = null;
		String InputLine = "";
		List <String >Headers=new ArrayList<String>();
		String returnvalue=null;
		String[] firstRow=null;
		scanIn = new Scanner(new BufferedReader(new FileReader(dataFile)));
		scanIn.useDelimiter(delimiter);

		List <String> headers= new ArrayList <String>();//headers of file1

		headers=getHeadersFromCsv(dataFile,delimiter);//headers are got from the csv a saved as list

		scanIn.nextLine();//to skip the header
		while(scanIn.hasNext()){
			HashMap<String, String> Form1= new HashMap<String , String>();

			int h=0;
			InputLine = scanIn.nextLine();
			firstRow = InputLine.split(delimiter);
			for(String values:firstRow){
				if(h<headers.size()){
					Form1.put(headers.get(h), values);
					h++;
				}


			}
			System.out.println("End=====");
			listOfMapName.add(Form1);
		}

	}	


	
	/**
	 * @param dataFile= file path of the source file
	 * @param MapOfMapName=Name of the map of map to be created
	 * @param delimiter= line delimiter
	 * @throws FileNotFoundException
	 */
	public static void convertCsvMapOfMap(String dataFile,Map<String,Object> CheckerData,String delimiter) throws FileNotFoundException{
		CheckerData.clear();
		//listOfMapName.clear();
		Scanner scanIn = null;
		String InputLine = "";
		List <String >Headers=new ArrayList<String>();
		String scenarioId=null;
		String returnvalue=null;
		String[] firstRow=null;
		scanIn = new Scanner(new BufferedReader(new FileReader(dataFile)));
		scanIn.useDelimiter(delimiter);

		List <String> headers= new ArrayList <String>();//headers of file1
		headers=getHeadersFromCsv(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"ExcelData"+File.separator+"CheckerTestData.csv",",");
		//headers=getHeadersFromCsv(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"ExcelData"+File.separator+"BasicDataCapture.csv",",");//headers are got from the csv a saved as list,field name delimiter is comma

		scanIn.nextLine();//to skip the header
		while(scanIn.hasNext()){
			HashMap<String, String> Form1= new HashMap<String , String>();

			int h=0;
			InputLine = scanIn.nextLine();
			firstRow = InputLine.split(",");
			for(String values:firstRow){
				if(h<headers.size()){
				//System.out.println(headers.get(h));
					Form1.put(headers.get(h), values);
					h=h+1;
				}
				

			}
			
			CheckerData.put(Form1.get("ID"), Form1);
			//listOfMapName.add(Form1);
		}
	
	}	


	/**
	 * @param filePath
	 * @param sheetName
	 * @return= the headers,that is the first row in an csv as a list
	 */

	public static List<String> getHeadersFromCsv(String filePath,String delimiter){
		Scanner scanIn = null;
		String InputLine = "";
		List <String >Headers=new ArrayList<String>();
		String returnvalue=null;
		String[] firstRow=null;
		try{

			scanIn = new Scanner(new BufferedReader(new FileReader(filePath)));
			scanIn.useDelimiter(delimiter);
			InputLine = scanIn.nextLine();
			firstRow = InputLine.split(delimiter);
			for(String header:firstRow){
				Headers.add(header);
			}
			scanIn.close();
			return Headers;

		}

		catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}


	/**
	 * @param filePath
	 * @param sheetName
	 * @return= the headers,that is the first row in an excelSheet as a list
	 */
	public List<String> readCsv(String filePath,String delimiter){
		Scanner scanIn = null;
		String InputLine = "";
		List <String >Headers=new ArrayList<String>();
		String returnvalue=null;
		String[] firstRow=null;
		try{

			scanIn = new Scanner(new BufferedReader(new FileReader(filePath)));
			scanIn.useDelimiter(delimiter);
			InputLine = scanIn.nextLine();
			firstRow = InputLine.split(delimiter);
			for(String header:firstRow){
				Headers.add(header);
			}
			scanIn.close();
			return Headers;

		}

		catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}


	/**iterates the maps in list and compares the values
	 * @param listOfMap1
	 * @param listOfMap2
	 * @throws IOException 
	 */
	public static void getValuesFromList(List<HashMap<String,String>> listOfMap1,List<HashMap<String,String>> listOfMap2) throws IOException{

		//Map<String,String> diffValuesMap = new HashMap<String,String>();
		List<String> tempList= new <String>ArrayList();
		String difVal;
		HashSet<String> diffHeaderhset = new HashSet<String>();
		List<String> diffHeaderList;
		List<String> diffValue= new <String>ArrayList();
		for(int i=0;i<listOfMap1.size();i++){
			Map<String,String> diffValuesMap = new HashMap<String,String>();//save the differences as map between 2 baskets and saves

			HashMap<String,String> sheet1= listOfMap1.get(i);//returns the map in list1
			HashMap<String,String> sheet2=listOfMap2.get(i);//returns the map in list2
			String id=sheet1.get("ID");
			//System.out.println(id);
			//difVal;
			for(Entry<String, String> record:sheet1.entrySet()){
				String key=record.getKey();//sheet1 key
				String value=record.getValue();//sheet2 value
				diffHeaderhset.add(key);//headeras are added in an hashset for the checker sheet column headers
/////allowed 
				if(!sheet1.get(key).equalsIgnoreCase(sheet2.get(key))| key.equalsIgnoreCase("ID")| key.equalsIgnoreCase("Application_Id")){
					if(key.equalsIgnoreCase("ID")){
						difVal=sheet1.get(key);
						diffValuesMap.put(key, difVal);
					}
					else{
					difVal=sheet1.get(key)+"|"+sheet2.get(key);
					diffValuesMap.put(key, difVal);//the difference is stored a map each row diff is a map
					}
				}
				else{
					difVal=key+"Nil,";
					diffValuesMap.put(key, "Nil");
				}

			}

			addInMapOfMaps(id,diffValuesMap);

		}

		putCheckerDataInCsv(checkerValues);//this contains the entire difference as hashmap
	}



	public static void addInMapOfMaps(String scenarioId,Map<String, String> diffValuesMap){
		checkerValues.put(scenarioId, diffValuesMap);
	}	


	public static void putCheckerDataInCsv(Map<String, Object> checkerValues2) throws IOException{
		//String scenarioID;
		
		String headersOutput = "";
		List <String> test1= new ArrayList<String>();
		List<String>headers= new ArrayList<String>();
		headers=getHeadersFromCsv("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"BasicDataCapture.csv", ",");
		for(String header:headers){//convert the array to string with commas
			headersOutput=headersOutput+header+",";
		}
		writeOutPutToCSV("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"difft.csv", headersOutput);
		//writeOutPutToCSV("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"difft.csv", headers);
		//List<String>checkerValues= new ArrayList<String>();

		String eol = System.getProperty("line.separator");
		
		for(String scenario:checkerValues2.keySet()){//for each row with difference in mapofmap
			String test = "";
			Map<String, String> temp=(Map<String, String>) checkerValues2.get(scenario);//one row of data as map
			test.trim();
			//test=scenario;
			for (String key : headers) {
				test=test+temp.get(key)+",";
				System.out.print(temp.get(key)+",");
			

			}

			System.out.println(test);
			writeOutPutToCSV("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"difft.csv", test);
			test =null;
			//writeOutPutToCSV("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"difft.csv", test);
		}
	}	

	/**Compare 2 hashmaps with its values
	 * @param map1
	 * @param map2
	 * @return
	 */
	public boolean compareMap(HashMap<String , String> map1,HashMap<String , String> map2){

		if (map1.size() != map2.size())
			return false;
		for (String key: map1.keySet())
			if (!map1.get(key).equals(map2.get(key)))
				return false;
		return true;

	}



	////////csv part=============================================================	
	public static void createNewCsv(String path,List<String> outputs) throws IOException{
		String newFileName = path;
		File newFile = new File(newFileName);
		BufferedWriter writer = new BufferedWriter(new FileWriter(newFile,true));
		for(String rows:outputs){
			String[] eachRow = rows.split("/n");//row is split based on null value
			for (String row:eachRow){
				writer.write(row);
				writer.write("\n"); 
			}

		}
		writer.flush();
		writer.close();
	}



	public static void writeOutPutToCSV(String filePath,String outputs) throws IOException{
		//FileWriter pw = new FileWriter("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"difft.txt");
		File file = new File("C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"difft.csv");
		//   Iterator s = customerIterator();
		PrintWriter csvWriter = new PrintWriter(new FileWriter(file, true));
		csvWriter.println(outputs);
		//csvWriter.print("world");

		csvWriter.flush();
		csvWriter.close();
	}	



	public static void writeOutPutToCSV(String filePath,List<String> outputs) throws IOException{
		//String csv = "C:"+File.separator+"Users"+File.separator+"1555976"+File.separator+"Desktop"+File.separator+"output.csv";
		FileWriter pw = new FileWriter(filePath,true);
		/// Iterator rows = outputs.iterator();
		for(String rows:outputs){
			String[] eachRow = rows.split("\n");//row is split based on null value
			for (String row:eachRow){
				String[] eachCell= row.split(",");
				for(String cell:eachCell){
					pw.write(cell);
					//next column
				}
				//				 // newline
				pw.write(row);
				pw.write("\n"); 
			}

		}
		pw.flush();
		pw.close();
	}





	/** This method converts the given 2 data sheets in to hashmap for comparison
	 * @param dataFile1= path of the dataSheet1
	 * @param dataFile2= path of the datasheet2
	 * @throws FileNotFoundException 
	 */
	public static void convertCsvToTestdata(String dataFile,List<HashMap<String,String>> listOfMapName,String delimiter) throws FileNotFoundException{
		listOfMapName.clear();
		Scanner scanIn = null;
		String InputLine = "";
		List <String >Headers=new ArrayList<String>();
		String returnvalue=null;
		String[] firstRow=null;
		scanIn = new Scanner(new BufferedReader(new FileReader(dataFile)));
		scanIn.useDelimiter(delimiter);

		List <String> headers= new ArrayList <String>();//headers of file1

		headers=getHeadersFromCsv(dataFile,delimiter);//headers are got from the csv a saved as list

		scanIn.nextLine();//to skip the header
		while(scanIn.hasNext()){
			HashMap<String, String> Form1= new HashMap<String , String>();

			int h=0;
			InputLine = scanIn.nextLine();
			firstRow = InputLine.split(delimiter);
			for(String values:firstRow){
				if(h<headers.size()){
					Form1.put(headers.get(h), values);
					h++;
				}


			}
			System.out.println("End=====");
			listOfMapName.add(Form1);
			testData.put(Form1.get("ID"), Form1);
		}

	}	






















}
