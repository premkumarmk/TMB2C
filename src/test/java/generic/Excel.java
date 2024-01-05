package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {


	

	    public void WriteToExcel(String fileName, String sheetName) throws IOException {
	        // Step 1: Create a Workbook using WorkbookFactory
	        Workbook workbook = WorkbookFactory.create(true);

	        // Step 2: Create a Sheet
	        Sheet sheet = workbook.createSheet(sheetName);

	        // Step 3: Create a Row
	        Row row = sheet.createRow(0);

	        // Step 4: Create a Cell
	        Cell cell = row.createCell(0);

	        // Step 5: Set the cell value
	        cell.setCellValue("Hello, Excel!");

	        // Step 6: Save the workbook
	        try (FileOutputStream fileOut = new FileOutputStream("fileName")) //workbook.xlsx 
	        {
	            workbook.write(fileOut);
	            System.out.println("Excel file has been written successfully!");
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	            try 
	            {
	                workbook.close();
	            } 
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	            }
	        }
	    }

	
	
	
	
	public static String getData(String path,String sheet,int r,int c)
	{
		String v="";
		try 
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			v=wb.getSheet(sheet).getRow(r).getCell(c).toString();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return v;
	}
	
	public static int getRowCount(String path,String sheet)
	{
		int rowCount=0;
		
		try
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			rowCount=wb.getSheet(sheet).getLastRowNum();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	//add a method to count columns
	public static int getColCount(String path,String sheet)
	{
		
		int colCount=0;
		
		try
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			
			colCount=wb.getSheet(sheet).getRow(0).getLastCellNum();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return colCount;
	}
	
    public Object[][] to2DArray(String path,String sheet) throws Exception {
        int noOfRows = getRowCount(path, sheet);
        int noOfCells = getColCount(path, sheet);
        Object obj[][] = new Object[noOfRows][noOfCells];

        for(int i=0; i<noOfRows; i++){ //i = 0 1 2
            //row = sh.getRow(i);
            for(int j=0; j<noOfCells; j++){
            	
                obj[i][j] = getData( path, sheet, i+1, j); //i = 1 2 3
                System.out.println("Row num:"+i+"  ,  Coulmn Num:"+j+"  ,  "+obj[i][j]);
            }
        }
        return obj;
    }
	
	//method to write the data
	
}
