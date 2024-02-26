package utilities;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import java.io.File;
public class EmailIntegration
{
	
public static void main(String[] args)
{
	String fileToSend="./BrainGym_output_20240221_175203.xlsx";
	sendEmail(fileToSend);
	
}
	
	public static void sendEmail(String fileToSend)
	{
//		String EmailFrom="no-reply@tautmore.com";
//		String EmailTo="premkumar@tautmore.com";
//		String subject="Test Report for MindMap URLs";
//		String message="PFA for the report";
		System.out.println("Inside sendEmail()");
		String host="http://localhost:8080/api/v1/email/send";
		String apiKey="OQG3RRpnrbo86pYR2hRf38I1Juvs47DA800A13btF303EN1SsU87vhaI7rVI346871jAN4l32o71O14Vm13secGL10hDvWG0C2fbUkyKUb5jTG5615n24ttRlH611m6qTfnK62v8Du8ygFF2I61HTVxfK5AA8ambh6vnsvo62Qep1rrB5j1QouyB06cS1dsY3wm88Ho3AUB7u8sh1M476bUFtJCnNQK21lV3M5xLJXNlVi3q47wrgVB4naXoDP5ll2ONX4nCiNDE4q3e1J4U6otC71MEFeWID2P2NWtktaQ0";
		//Unirest.setTsimeouts(0, 0);
		HttpResponse<String> response = Unirest.post(host)
		  .header("ApiKey", apiKey)
		  .field("json", 
				  "{\"fromEmail\":\"no-reply@tautmore.com\","
				//  + "\"toEmail\":\"sitehealthcheck@tautmore.com\","
				 // + "\"toEmail\":\"premkumar@tautmore.com\","
				  + "\"toEmail\":[\"premkumar@tautmore.com\",\"lakshmiprasad@tautmore.com\",\"sitehealthcheck@tautmore.com\"],"
				//  + "\"toEmail\":\"no-reply@tautmore.com\","
				//  + "\"toEmail\":\"apoorva@tautmore.com\","
				  + "\"subject\":\"Test Report for\","
				  + "\"body\":\"PFA for the report\"}")
		  .field("file", new File(fileToSend))
		  .asString();
		
		System.out.println("EMail sent response body: "+response.getBody());
		System.out.println("EMail sent response body: "+response.getStatus());
		if(response.getStatus()!=200)
		{
			System.err.println("Issue with sending Email !!!!!!!!!!!!!!!!");
		}
	}

}
