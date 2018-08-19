package com.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.support.ui.Select;

import com.test.framework.Commons;


import com.test.framework.glue.*;

import org.openqa.selenium.*;


public class CommonUtils {

	

	public String screenShotPath = "C:/CaptureScreenshot";
	public static WebApp base = new WebApp();
	 public static Commons com=new Commons();

	public static List<HashMap<String, String>> mydata = new ArrayList<HashMap<String, String>>();

	public List<HashMap<String, String>> inputData = new ArrayList<HashMap<String, String>>();

	public List<List<HashMap<String, String>>> mydata1 = new ArrayList<List<HashMap<String, String>>>();

	public static int rowCount = 10;

	/*public void convertAllExcelToMap() throws IOException{

		mydata1.clear();
		convertExcelToMap(base.excelPath, "RTOB_Input.xls", "DocumentIndexing");
		inputData = mydata;
		mydata1.add(inputData);
		System.out.println(inputData);
		System.out.println(mydata1);
		convertExcelToMap(base.excelPath, "RTOB_Input.xls", "DocInd_order");
		inputData = mydata;
		mydata1.add(mydata);
		System.out.println(inputData);
		System.out.println(mydata1);

	}*/

	/*@SuppressWarnings("deprecation")
	public static void convertExcelToMap(String FilePath,String FileName,String sheetName) throws IOException{
		mydata.clear();
		String MyFile= FilePath+File.separator+FileName;
		System.out.println("MyFile[before] :"+MyFile);

		//String envName = System.getProperty("env");
		URL resource = DBUtils.class.getClassLoader().getResource("ExcelData" + File.separator + FileName);
		File file = null;
		FileInputStream fin = null;
		try {
			file = new File(resource.toURI());
			fin = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println("MyFile[after] :"+file.toString());

		//FileInputStream fin = new FileInputStream(MyFile);
		HSSFWorkbook book = new HSSFWorkbook(fin);
		HSSFSheet sheet = book.getSheet(sheetName);
		for(int i=0;i<sheet.getPhysicalNumberOfRows();i++)
		{
		    Row currentRow = sheet.getRow(i);
		    for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
		    {
		        Cell currentCell = currentRow.getCell(j);
		        switch (currentCell.getCellType())
		        {
		            case Cell.CELL_TYPE_STRING:
		                System.out.print(currentCell.getStringCellValue() + "|");
		                break;
		            case Cell.CELL_TYPE_NUMERIC:
		                System.out.print(currentCell.getNumericCellValue() + "|");
		                break;

		            case Cell.CELL_TYPE_BLANK:
		                System.out.print("<blank>|");
		                break;
		        }
		    }
		    System.out.println("\n");
		}
		rowCount = sheet.getPhysicalNumberOfRows();
		Row HeaderRow = sheet.getRow(0);
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row currentRow = sheet.getRow(i);
			HashMap<String, String> currentHash = new HashMap<String, String>();
			for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
				Cell currentCell = currentRow.getCell(j);
				switch (currentCell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), String.valueOf(currentCell.getNumericCellValue()));
					break;
				}
			}
			mydata.add(currentHash);
			//System.out.println("\n\n"+currentHash);
		}
		//System.out.println(mydata);
		//System.out.println(mydata.get(0));
	}
*/


	public static String readColumn(String column,int row){
		HashMap<String, String> map = mydata.get(row);
		String result=null;
		for (Entry<String, String> entry : map.entrySet()) {
			if(entry.getKey().equalsIgnoreCase(column)){
				System.out.println(entry.getValue());
				result = entry.getValue();
			}

		}
		return result;


	}

	public static String readColumn1(String column){
		for(int i=0;i<10;i++){
			HashMap<String, String> map = mydata.get(i);
			String result=null;
			for (Entry<String, String> entry : map.entrySet()) {
				if(entry.getKey().equalsIgnoreCase(column)){
					System.out.println(entry.getValue());
					//result = entry.getValue();
					//System.out.println(result);
				}

			}

		}
		return column;



	}

	public static String readColumnWithRowID(String column,String rowID){
		System.out.println("row :"+column+":"+rowID);
		String result=null;
		try{
			for(int i=0;i<rowCount;i++){
				if(readColumn("Scenarioid", i).equalsIgnoreCase(rowID)){
					System.out.println("matched with "+readColumn("Scenarioid", i));
					result = readColumn(column, i);
					break;
				}
			}
		}
		catch(IndexOutOfBoundsException I){
			System.out.println("Row ID didn't match");
			I.printStackTrace();
		}
		catch(Exception E){
			System.out.println("Reading from Excel has failed");
		}
		System.out.println("result : "+result);
		return result;
	}

	public static void convertExcelToMapFromLocal(String FilePath,String FileName,String sheetName) throws IOException{
		mydata.clear();
		String MyFile= FilePath+File.separator+FileName;
		HSSFWorkbook book = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(MyFile)));
		FileInputStream fin = new FileInputStream(MyFile);
		//		HSSFWorkbook book = new HSSFWorkbook(fin);
		HSSFSheet sheet = book.getSheet(sheetName);
		//		XSSFWorkbook book = new XSSFWorkbook(fin);
		//		XSSFSheet sheet = book.getSheet(sheetName);
		/*for(int i=0;i<sheet.getPhysicalNumberOfRows();i++)
		{
		    Row currentRow = sheet.getRow(i);
		    for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
		    {
		        Cell currentCell = currentRow.getCell(j);
		        switch (currentCell.getCellType())
		        {
		            case Cell.CELL_TYPE_STRING:
		                System.out.print(currentCell.getStringCellValue() + "|");
		                break;
		            case Cell.CELL_TYPE_NUMERIC:
		                System.out.print(currentCell.getNumericCellValue() + "|");
		                break;

		            case Cell.CELL_TYPE_BLANK:
		                System.out.print("<blank>|");
		                break;
		        }
		    }
		    System.out.println("\n");
		}*/
		rowCount = sheet.getPhysicalNumberOfRows();
		Row HeaderRow = sheet.getRow(0);
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row currentRow = sheet.getRow(i);
			HashMap<String, String> currentHash = new HashMap<String, String>();
			for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
				Cell currentCell = currentRow.getCell(j);
				switch (currentCell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), String.valueOf(currentCell.getNumericCellValue()));
					break;
				}
			}
			mydata.add(currentHash);
			//System.out.println("\n\n"+currentHash);
		}
		//System.out.println(mydata);
		//System.out.println(mydata.get(0));
	}



	
	
	public static void Read_CSV(String Field, String xpath) throws Throwable {

		//  CommonUtils.convertExcelToMap(BaseProject.excelPath,"Datamodel.xls","Frontline");
		//  
		//  String Doc_category = utils.readColumnWithRowID("Document Category","1");
		//  System.out.println("Documnet List: "+Doc_category);
		//  String getval = "CLIENT VERIFICATION DOCUMENTS","CLIENT //SERVICING DOCUMENTS","INTERNAL DOCUMENTS","CLIENT TAX //DOCUMENTS","PRODUCT & SUPPORTING DOCUMENTS","FATCA  DOCUMENTS","CLIENT //CREDIT ASSESSMENT DOCUMENTS","CLIENT ID  DOCUMENTS","COLLATERAL //DOCUMENTS","Please Select...";
		//String csvFilename = "data.csv";
		//CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		//csvFilename .readHeaders();
		//String getval = csvReader.get(Field);
		//List Documnet_Category_List=Arrays.asList(getval.split(","));
		List Documnet_Category_List=Arrays.asList("CLIENT VERIFICATION DOCUMENTS","CLIENT SERVICING DOCUMENTS","INTERNAL DOCUMENTS","CLIENT TAX DOCUMENTS","PRODUCT & SUPPORTING DOCUMENTS","FATCA  DOCUMENTS","CLIENT CREDIT ASSESSMENT DOCUMENTS","CLIENT ID  DOCUMENTS","COLLATERAL DOCUMENTS","Please Select...");       
		Iterator document_category = Documnet_Category_List.iterator();
		while(document_category.hasNext()){
			String doclists=document_category.next().toString().trim().toUpperCase();
			System.out.println("Document from confluence: " + doclists);
		}     

		Collections.sort(Documnet_Category_List);
		System.out.println(Documnet_Category_List);
		switchFrame();

		ArrayList<String> doclistsort = new ArrayList();
		//List<WebElement> doc_category =BaseProject.driver.findElements(By.xpath("//select[@id='DocCategoryToUpload']"));

		WebElement element= base.driver.findElement(By.xpath(xpath));
		//WebElement ele = wrap.getElement(BaseProject.driver, Attribute)
		Select doclist=new Select(element);
		List<WebElement> doclist_count=doclist.getOptions();
		int doclist_size=doclist_count.size();

		for(WebElement ele:doclist_count)
		{
			doclistsort.add(ele.getText()); 
			System.out.println("Document from APP: " +ele.getText());
		}
		Collections.sort(doclistsort);
		System.out.println(doclistsort);

		if(Documnet_Category_List.equals(doclistsort)){
			System.out.println("Success");

		}
		else
		{
			System.out.println("Fail");
		}

		//csvReader .close();


	}

	public static void switchFrame() throws InterruptedException {
		int Last = 0;

		base.driver.switchTo().defaultContent();
		//wrap.wait(300);
		List<WebElement> frames = base.driver.findElements(By.tagName("iframe"));
		for (WebElement frame : frames) {
			System.out.println(frame.getAttribute("Name"));

		}
		//System.out.println("current frame name is "+ frmName.getAttribute("id"));
		Last = frames.size() - 1;
		//string currentFrame =
		System.out.println("User should switch to this frame name PegaGadget" + Last + "Ifr");

		//wdwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Last));
		base.driver.switchTo().frame(Last);
		System.out.println("User switched to this frame name PegaGadget" + Last + "Ifr");
		//wrap.wait(300);
	}

	}
