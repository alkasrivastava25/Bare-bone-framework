package com.test.framework;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class XMLParser {
	public static String updatedNodeValue;
    public static String envName;

	public static String xmlPath = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"xmlData";
	public static void main(String args[])
	{
		System.out.println("This is XML data reader file!");
		String pCode = readXMLData("Application","productCode");
		System.out.println("Product Code ==== "+pCode);
		readXMLData("Application","productCategory");
		readXMLData("Application","barCode");
		readXMLData("Application","scanType");
		readXMLData("Application","scanMaker");
		readXMLData("Application","scanChecker");
		readXMLData("Application","sourceReferenceNo");
		readXMLData("Application","accountOpeningFormDate");
		readXMLData("Application","segment");
		readXMLData("Application","isBundle");
		readXMLData("Application","salesHandoverDate");
		readXMLData("Application","customerFullName");
		readXMLData("Application","contactTypeClassification");
		readXMLData("Application","contactNumber");
		readXMLData("Application","isPhotoMatch");
		readXMLData("Application","isSignAcrossPhoto");		
	}
	public static String readXMLData(String tagName, String nodeName){
	
		String node="";
	      try {	
	         File inputFile = new File(xmlPath+File.separator+"EOPS_CC_Req_nw.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         
	         NodeList nList = doc.getElementsByTagName(tagName);
	         Node nNode = nList.item(0);
	         Element eElement = (Element) nNode;
	         node = eElement.getElementsByTagName(nodeName).item(0).getTextContent().toString();
	         System.out.println("The node value ===== "+node);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return node;
	   }
	public static String readXML(String xmlPath, String fileName,String tagName, String nodeName){
        
        String node="";
       try {   
          File inputFile = new File(xmlPath+File.separator+fileName);
          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          Document doc = dBuilder.parse(inputFile);
          doc.getDocumentElement().normalize();
          
          NodeList nList = doc.getElementsByTagName(tagName);
          Node nNode = nList.item(0);
          Element eElement = (Element) nNode;
          node = eElement.getElementsByTagName(nodeName).item(0).getTextContent().toString();
          System.out.println("The node value ===== "+node);
       } catch (Exception e) {
          e.printStackTrace();
       }
       return node;
    }

public static String updateXML(String xmlPath, String fileName,String tagName, String nodeName,String value)
 {
        String node="";
        //String updatedNodeValue = null;
        try {  
          File inputFile = new File(xmlPath+File.separator+fileName);
          System.out.println("File Name" + inputFile);
          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          Document doc = dBuilder.parse(inputFile);
          
          Node project = doc.getFirstChild();
          Node properties = doc.getElementsByTagName("properties").item(0);
          NamedNodeMap attr = properties.getAttributes();
              Node nodeAttr = attr.getNamedItem("AUT.env");
              //nodeAttr.setTextContent(value);
              NodeList list = properties.getChildNodes();

               for (int i = 0; i < list.getLength(); i++) {

                    Node AUTnode = list.item(i);

                  // get the salary element, and update the value
                  if ("AUT.env".equals(AUTnode.getNodeName())) {
                        AUTnode.setTextContent(value);
                  }

                  System.out.println(AUTnode.getTextContent());
                   updatedNodeValue = AUTnode.getTextContent();
               
               System.out.println("XML value updated");
                  envName = updatedNodeValue;
       } 
        }
        catch (Exception e) {
          e.printStackTrace();
       }
        return updatedNodeValue;
        
 }


}
