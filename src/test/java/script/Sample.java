package script;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import generic.Excel;
import page.LoginPage;
import generic.BaseTest;


public class Sample extends BaseTest {
	
	 @DataProvider(name = "data-set")
	    public static Object[][] DataSet() throws Exception 
	 	{
	       
	        Excel excel = new Excel();

	        Object[][] obj = excel.to2DArray("./data/userIds.xlsx", "user_ids");
	        return obj;
	    }
	 
	 
	 
	 @Test(priority = 3, dataProvider="data-set")
	 public void tryMultiLogin(String un, String pw, String grade, String subject) throws InterruptedException {
			System.out.println(un);
			System.out.println(pw);
			System.out.println(grade);
			System.out.println(subject);
			LoginPage login=new LoginPage(driver);
			//	mongoDB.getStudentIdByEmail("abdul@codewave.com");
				login.clickOnloginBtnOnDashboard();
				Thread.sleep(1000);
				login.clickOnOptionLoginAs();
				Thread.sleep(1000);
				login.clickOnNext();
				Thread.sleep(1000);
				login.setUserName(un);
				
				login.setPassword(pw);
				
				
				//login.clickLoginButton();
			
		 
	 }
}
	
	