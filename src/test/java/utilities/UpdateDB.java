package utilities;

public class UpdateDB {
	
	public static void changeDate(int count) throws Exception 
	{
		
		System.out.println("UpdateDB class ==> changeDate()  method");

		if(count==0)
		{	
			System.out.println("Count to reduce date for first loop:"+count);
		mongoDBSeleniumIntegration.updateBrainGymMappings(mongoDBSeleniumIntegration.getStudentIdByEmail());
		Thread.sleep(6000);
		
		}
		else
		{
			System.out.println("Count to reduce date:"+count);
		mongoDBSeleniumIntegration.updateBrainGymMappings1(mongoDBSeleniumIntegration.getStudentIdByEmail(),count);
		Thread.sleep(6000);
		
		}
	}
}
