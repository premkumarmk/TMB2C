package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;




public class ExcelConstructionExample {

    public static void main(String[] args) {
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
		writeToExcel(filepath,workbook,sheet,result,r);
		
		username = "Student3";
        grade = "III";
        subject = "English";
        status = true;
        r=4;
        result = String.format("%s, %s, %s, %s", username, grade, subject, status);
        System.out.println(result);
        writeToExcel(filepath,workbook,sheet,result,r);
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
    	 Sheet sheet = workbook.createSheet(sheetName);
    	 Row headerRow = sheet.createRow(0);
         for (int i = 0; i < headers.length; i++) 
         {
             Cell cell = headerRow.createCell(i);
             cell.setCellValue(headers[i]);
         }

         return sheet;
      //  return workbook.createSheet(sheetName);
    }
    
	private static void writeToExcel(String filepath, Workbook workbook, Sheet sheet, String result,int r) 
	{
        
			
            // Create a row (e.g., row 0)
            Row row = sheet.createRow(r);

            // Split the result string by commas
            String[] values = result.split(", ");

            // Create cells and set values
            for (int i = 0; i < values.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(values[i]);
            }

            // Write the workbook to a file ./data/result.xlsx
            try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
                workbook.write(fileOut);
                System.out.println("Result written to Excel file successfully.");
            }

         catch (IOException e) {
            e.printStackTrace();
        }

    
	}
    
}
	
	
