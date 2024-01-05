package script;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SimpleArray {

	public static void main(String[] args) {
	//	autostudentone.9606178621,stuPwd906040,III,Science,pass,


		List<String> student=new ArrayList<String>();
		List<String> studentList=new ArrayList<String>();
		studentList.add( String.join(",", "username", "password", "studentGrade", "studentSubject","Pass","Comments"));
		
		 
		for (int i=0; i<student.size();i++) {
			studentList.add( String.join(",", "studentUserName", "studentPassword"+i, "studentGrade"+i, "studentSubject"+i,"Pass","Comments"));
		}
		
		String[] studentString =  studentList.toArray(new String[studentList.size()]);
		try {
		createExcelSheet(studentString);
		} catch(IOException e) {
			
		}
	}
	public static  void createExcelSheet(String[] lines) throws IOException
    {
        FileOutputStream out = new FileOutputStream("test.xls");
        //FileOutputStream out = new FileOutputStream("/home/yos/lakshmi.xls");

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet s = wb.createSheet();

        Row r = null;

        Cell c = null;

        for(int i=0;i<lines.length;i++)
        {
            r = s.createRow(i); 
           String[] rowStrings = StringUtils.split(lines[i], ',');
			 
            //System.out.println(""+lines[i]);
            //System.out.println("Column string count "+rowStrings.length);
            for(int j=0;j<rowStrings.length;j++)
            {
                c = r.createCell(j);
               // System.out.println("Cell text should be "+rowStrings[j]);
                c.setCellValue( rowStrings[j] );
            }
        }


        wb.write(out);
        out.close();
    }

}
