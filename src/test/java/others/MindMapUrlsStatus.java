package others;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.UnsupportedEncodingException;
import org.apache.http.impl.client.HttpClients;
import java.net.URLEncoder;
import java.net.URI;

public class MindMapUrlsStatus 
{
	public static String outputfilePath;
	
	public static void main(String[] args) throws Exception 
	{
	System.out.println("Hi");
	String filePath="./data/Total_videos_uploaded_list_in_Website.xlsx";
	//Object[][] obj = to2DArray("./data/userIds.xlsx", "user_ids");
	
	String sheetName="GRADE 7";
	
	Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));

	System.out.println("Start of main");
	List<Object> listOfLinks = listOfLinks(wb,sheetName);
	List<String> stringList = new ArrayList<>();
	List<String> linksList=new ArrayList<>();
	String content;
    for (Object obj : listOfLinks) 
	    {
    	if(obj!=null || obj!="Mind Maps")
    	{
	    	System.out.println("inside for obj");
	    	
	        stringList.add(obj.toString());
	       // System.out.println(obj.toString());
    	}
	        
	    }
		
    for(int i=0; i<stringList.size();i++)
    	{
    	//if((!stringList.get(i).isEmpty()) || (!stringList.get(i).contains("Mind Maps")))
    	if(stringList.get(i).length()!=0) 
    		{
    		if(!stringList.get(i).contentEquals("Mind Maps"))
	    		{
	    			content=stringList.get(i);
	    			//System.out.println(stringList.get(i)+"Length id: "+stringList.get(i).length());
	    			//Pattern pattern = Pattern.compile("https?://\\S+\\.(html|jpeg)");
	    			String pattern = "(https://\\S+?\\.(?:html|jpeg))";
	    			  Pattern p = Pattern.compile(pattern);
	    		      Matcher m = p.matcher(content);
	
	    	      //  Matcher matcher = pattern.matcher(content);
	    	        while (m.find()) {
	    	            String link = m.group(1);
	    	            linksList.add(link);
	    		}
    	
	    	}
    	
    	}
    }
    for(String str:linksList)
    {
    	System.out.println(str);
    }
   // System.out.println("Printing String List: "+linksList);
    
    Map<String, String> urlScriptMap = extractUrlScriptMap(linksList);
    
    System.out.println("URL and corresponding script value:");
    for (Map.Entry<String, String> entry : urlScriptMap.entrySet()) {
        System.out.println("URL: " + entry.getKey() + ", Script value: " + entry.getValue());
    }
    
    
    
    writeUrlAndSrcValueToExcel(urlScriptMap);
    updateUrlAndWritetoExcel();
    getUrlStatusCodeAndWritetoExcel();
	System.out.println("End of MAin");

	}
	

	@SuppressWarnings("deprecation")
	public static void getUrlStatusCodeAndWritetoExcel() throws URISyntaxException {
		 System.out.println("In getUrlStatusCodeAndWritetoExcel()");
        try (FileInputStream fis = new FileInputStream(outputfilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows
            for (Row row : sheet) {
                //Cell urlCell = row.getCell(2);
             //  Cell pathCell = row.getCell(1);
              //  Cell resultCell = row.createCell(3); // Create a new cell for the result
                Cell urlCell = row.getCell(2);
                String urlString = urlCell.getStringCellValue();
                int resultColumnIndex = urlCell.getColumnIndex() + 1;
                if (urlCell != null ) {
                	
              	
                	 try {
                		 System.out.println("Step-1");
                		 //URL url = new URL(urlString);
                         // Create HttpClient                		 
//                		 URI uri = new URI(urlString);
//                		 System.out.println("uri value: "+uri);
                	    // String encodedUrl = uri.toASCIIString();
                		 //   HttpClient client = HttpClientBuilder.create().build();
//                       String encodedUrl = URLEncoder.encode(urlString, "UTF-8");
                         //  HttpGet request = new HttpGet(urlString);
                         // HttpGet request = new HttpGet(url.toURI());        		 
                		 String encodedUrl = encodeUrl(urlString);
                         System.out.println("encodedUrl: "+encodedUrl);
                         System.out.println("Step-2");
                         HttpClient client = HttpClients.createDefault();
                        // HttpClient client = HttpClientBuilder.create().build();
                         System.out.println("Step-3");
                        System.out.println("=== "+encodedUrl);
                         // Create HttpGet request

                         HttpGet request = new HttpGet(encodedUrl);
                         // Execute the request
                         System.out.println("Step-4");
                         HttpResponse response = client.execute(request);
                         System.out.println("Step-5");
                         // Get the response code
                         int statusCode = response.getStatusLine().getStatusCode();
                         
                         // Print the status code
                         System.out.println("Status Code: " + statusCode);
                         System.out.println("Step-6");
                         Cell statusCell = row.createCell(resultColumnIndex);
                         statusCell.setCellValue(statusCode);
                     }
                	   
                	 catch (IOException e) {
                           e.printStackTrace();
                           // If an error occurs, write -1 to the output cell
                           Cell statusCell = row.createCell(1);
                           statusCell.setCellValue(-1);
                       }
                   
                }
                
            }

            // Write the updated workbook back to the input file
            try (FileOutputStream fileOut = new FileOutputStream(outputfilePath)) {
                workbook.write(fileOut);
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ENd of getUrlStatusCodeAndWritetoExcel()");
    }
	
	 private static String encodeUrl(String urlString) throws URISyntaxException {
		  String encodedPath=null; 
		 try {
	            // Split the URL into parts (scheme, authority, path, query, fragment)
	            URI uri = new URI(urlString);

	            // Encode the path component of the URL
	          
				try {
					encodedPath = URLEncoder.encode(uri.getPath(), "UTF-8")
					        .replaceAll("\\+", "%20") // Replace '+' with '%20' explicitly
					        .replaceAll("%2F", "/");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Replace encoded '/' back to '/'

	            // Reconstruct the URL with the encoded path
	            URI encodedUri = new URI(uri.getScheme(), uri.getAuthority(), encodedPath, uri.getQuery(), uri.getFragment());

	            return encodedUri.toString();
	        } catch (URISyntaxException e) {
	            // Handle the URISyntaxException
	            System.err.println("Error: Invalid URL - " + urlString);
	            throw e; // Re-throw the exception
	        }
	    }
	
	public static void updateUrlAndWritetoExcel() {
		System.out.println("In updateUrlAndWritetoExcel()");
        try (FileInputStream fis = new FileInputStream(outputfilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows
            for (Row row : sheet) {
                Cell urlCell = row.getCell(0);
                Cell pathCell = row.getCell(1);
                Cell resultCell = row.createCell(2); // Create a new cell for the result

                if (urlCell != null && pathCell != null) {
                    String url = urlCell.getStringCellValue();
                    String path = pathCell.getStringCellValue();

                    // Check if URL ends with "index.html"
                    if (url.endsWith("index.html")) {
                        // Replace "index.html" with the corresponding path
                        String updatedUrl = url.replace("index.html", path);
                        resultCell.setCellValue(updatedUrl); // Write the updated URL to the result cell
                    }
                }
               
            }

            // Write the updated workbook back to the input file
            try (FileOutputStream fileOut = new FileOutputStream(outputfilePath)) {
                workbook.write(fileOut);
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ENd of updateUrlAndWritetoExcel()");
    }
	
	
	public static Map<String, String> extractUrlScriptMap(List<String> urlList) 
	{
		System.out.println("In extractUrlScriptMap()");
        Map<String, String> urlScriptMap = new HashMap<>();
        for (String urlString : urlList) 
        {
            try {
                // Parse the webpage using Jsoup
                Document doc = Jsoup.connect(urlString).get();

                // Find all script elements with src attribute
                Elements scriptElements = doc.select("script[src]");

                // Iterate over script elements and extract src attribute value
                for (Element script : scriptElements) 
                {
                    String src = script.attr("src");
                    if (!src.isEmpty()) 
                    {
                        urlScriptMap.put(urlString, src);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("End of extractUrlScriptMap()");
        return urlScriptMap;
    }

	 public static void writeUrlAndSrcValueToExcel(Map<String, String> urlScriptMap) {
		 System.out.println("In writeUrlAndSrcValueToExcel()");
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        String dateTimeString = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

	        outputfilePath = "./output_" + dateTimeString + ".xlsx";

	        try (Workbook workbook = new XSSFWorkbook()) {
	            Sheet sheet = workbook.createSheet("URLs and Script Values");

	            int rowNum = 0;
	            for (Map.Entry<String, String> entry : urlScriptMap.entrySet()) {
	                Row row = sheet.createRow(rowNum++);
	                row.createCell(0).setCellValue(entry.getKey());
	                row.createCell(1).setCellValue(entry.getValue());
	            }

	            // Write the workbook to a file
	            try (FileOutputStream outputStream = new FileOutputStream(outputfilePath)) {
	                workbook.write(outputStream);
	                workbook.close();
	                System.out.println("Excel file written successfully: " + outputfilePath);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("End of writeUrlAndSrcValueToExcel()");
	    }

	
//	public static String extractSrcAttributeValue(String urlString) 
//	{
//        try {
//            // Parse the webpage using Jsoup
//            Document doc = Jsoup.connect(urlString).get();
//
//            // Find all script elements with src attribute
//            Elements scriptElements = doc.select("script[src]");
//
//            // Iterate over script elements and extract src attribute value
//            for (Element script : scriptElements) 
//            {
//                String src = script.attr("src");
//                if (!src.isEmpty()) 
//                {
//                    return src;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null; // Return null if src attribute not found
//    }
	
	public static String getData(Workbook wb,String sheet,int r,int c) throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		System.out.println("In getData():");
		String v="";
		try 
		{
			//Workbook wb = WorkbookFactory.create(new FileInputStream(wb));
				
			
				if(wb.getSheet(sheet).getRow(r).getCell(c)!=null)
				{
				v=wb.getSheet(sheet).getRow(r).getCell(c).toString();
				System.out.println("data is: "+v);
				}
			
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		
		return v;
	}
	
//	public static int getRowCount(String path,String sheet)
	public static int getRowCount(Workbook wb,String sheet)
	{
		int rowCount=0;
		
		try
		{
			//Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			rowCount=wb.getSheet(sheet).getLastRowNum();
			System.out.println("rowCount is: "+rowCount);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	
	//add a method to count columns
	public static int getColCount(Workbook wb,String sheet)
	{
		
		int colCount=0;
		
		try
		{
			//Workbook wb = WorkbookFactory.create(new FileInputStream(wb));
			
			colCount=wb.getSheet(sheet).getRow(0).getLastCellNum();
			System.out.println("Column Count is: "+colCount);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return colCount;
	}
	
	public static int getSheetCount(String path)
	{
		int sheetCount=0;
		
		try
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			sheetCount=wb.getNumberOfSheets();
			System.out.println("sheet Count is: "+sheetCount);
			
			//String sheetNames=wb.getSheetName(0);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return sheetCount;
	}
	
	

	public static List<Object> listOfLinks(Workbook wb,String sheet) throws Exception 
    {
		System.out.println("In listOfLinks(), reading from excel and to list");
		List<Object> listOfLinks = new ArrayList<>();
		//List<String> listOfLinks = null;
        int noOfRows = getRowCount(wb, sheet);
        int noOfCells = getColCount(wb, sheet);
        String obj[][] = new String[noOfRows][noOfCells];
      

        for(int i=0; i<noOfRows; i++)
        { //i = 0 1 2
            //row = sh.getRow(i);
        	
            for(int j=0; j<noOfCells; j++)
            {
            	System.out.println("row: "+i+"column: "+j);
            	
                	//System.out.println("In j loop");
            		obj[i][j] = getData( wb, sheet, i+1, j); //i = 1 2 3
            		//System.out.println("value is: "+obj[i][j]);
                
                //System.out.println("Row num:"+i+"  ,  Coulmn Num:"+j+"  ,  "+obj[i][j]);
                if(j==6)
                {
                   // System.out.println("In j condition");
	                if(obj[i][j]!=null)
	                {
	                	//System.out.println("Adding value is: "+obj[i][j]);
	                	listOfLinks.add(obj[i][j]);
	                }
	                
                }
              }
        }
        System.out.println("In listOfLinks()");
        return listOfLinks;
    }
	
    
}

