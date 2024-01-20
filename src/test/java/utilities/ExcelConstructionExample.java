package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import page.BrainGymPage;




public class ExcelConstructionExample {

	public static Workbook workbook;
	public static Sheet sheet;
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	Workbook workbook = createWorkbook();
    	String sheetName = "one";
    	String[] headers = {"Header1", "Header2", "Header3","Header4"};
 		Sheet sheet = createSheet(workbook, sheetName, headers);
 		String filepath="./data/result1.xlsx";
 		
    	String username = "Student2";
        String grade = "II";
        String subject = "Maths";
        boolean status = true;
        String result = String.format("%s, %s, %s, %s", username, grade, subject, status);
        System.out.println(result);
        int r=3;
//		writeToExcel(filepath,workbook,sheet,result,r);
		
		username = "Student3";
        grade = "III";
        subject = "English";
        status = true;
        r=4;
        result = String.format("%s, %s, %s, %s", username, grade, subject, status);
        System.out.println(result);
       // writeToExcel(filepath,workbook,sheet,result,r);
        writeToExcel();
    }
    


    public static Workbook createWorkbook() {
        try {
            return WorkbookFactory.create(false);
        	} 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return null; // Return null in case of an exception
    }
   
    public static Sheet createSheet(Workbook workbook, String sheetName, String[] headers) 
    {
    	 Sheet sheet = workbook.createSheet("Output");
    	 Row headerRow = sheet.createRow(0);
         for (int i = 0; i < headers.length; i++) 
         {
             Cell cell = headerRow.createCell(i);
             cell.setCellValue(headers[i]);
         }

         return sheet;
      //  return workbook.createSheet(sheetName);
    }
    
	public static void writeToExcel() throws FileNotFoundException, IOException 
	{
		System.out.println("Inside writeToExcel()");
		String[] headers = {"Username", "grade", "Subject","Result", "Comments"};	
		        		  
        System.out.println("filepath is:"+BrainGymPage.outputFilepath);
       
		try (FileInputStream fileInputStream = new FileInputStream(BrainGymPage.outputFilepath)) 
		  {
	
			  Workbook workbook=WorkbookFactory.create(fileInputStream);
			  Sheet sheet = workbook.createSheet(BrainGymPage.outputSheetName);
			  
			 
			  Row headerRow = sheet.createRow(0);
		      for (int i = 0; i < headers.length; i++) 
		         {
		             Cell cell = headerRow.createCell(i);
		             cell.setCellValue(headers[i]);
		         }
		    //  int n= BrainGymPage.ResultListToExcel.size();
	           String[] values; 
	           int r=1;
	           
	           for (String item : BrainGymPage.ResultListToExcel)
	           {
	        	    System.out.println(item);
	        	    CellStyle redFontStyle = createFontStyle(workbook, IndexedColors.RED);
	                CellStyle greenFontStyle = createFontStyle(workbook, IndexedColors.GREEN);
	        	    // Create a row (e.g., row 0)
		            Row row = sheet.createRow(r);
		            //split the items by ,
		            values = item.split(",");
		            // Create cells and set values
		            for (int i = 0; i < values.length; i++)
		            {
		            	System.out.println("Inside cell level for loop");
		            	System.out.println("i value is:"+i);
		                Cell cell = row.createCell(i);
		                cell.setCellValue(values[i]);
		                
		                if ("Fail".equalsIgnoreCase(values[i])) {
		                    cell.setCellStyle(redFontStyle);
		                } else if ("Pass".equalsIgnoreCase(values[i])) {
		                    cell.setCellStyle(greenFontStyle);
		                }
		                
		                
		            }
		            r++;
	           }
	       

            // Write the workbook to a file ./data/result.xlsx
            try (FileOutputStream fileOut = new FileOutputStream(BrainGymPage.outputFilepath)) 
            {
                workbook.write(fileOut);
                System.out.println("Result written to Excel file successfully."+BrainGymPage.outputFilepath);
            }
           
         catch (IOException e) 
            {
            e.printStackTrace();
            }

		  }
	}
	
	
	
	
	private static CellStyle createFontStyle(Workbook workbook, IndexedColors color) 
	{
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(color.getIndex());
        style.setFont(font);
        return style;
    }
    
	
}
	
	
	