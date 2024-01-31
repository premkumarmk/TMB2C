package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import groovyjarjarantlr4.v4.codegen.model.Loop;
import utilities.CompareQuestions;
import utilities.JS;
import utilities.ToReadResponse;
import utilities.UpdateDB;
import utilities.mongoDBSeleniumIntegration;

public class BrainGymPage extends BaseTest{
	SoftAssert softAssert = new SoftAssert();
	@FindBy(xpath="//ul/li/a[text()='Brain Gym']")
	public WebElement leftLinkBrainGym;
	
	@FindBy(xpath="//div[@class='dtoggle-bar']/div")
	public List<WebElement> subjectsTabs;
	
	@FindBy(xpath="//div[@class='row braingym-sublisttab']/descendant::button[text()='Science']")
	public WebElement subjectScience;
	
	//@FindBy(xpath="//div[@class='braingym-starttoday']/descendant::button[descendant::span[text()='Start workout']]")
	//button/span[text()='Start workout']
	@FindBy(xpath="//div[@class='braingym-starttoday']/descendant::button[descendant::span[contains(text(),'workout')]]")
	public WebElement workoutBtn;
	
	@FindBy(xpath="//p[@class='adjusted-para1']")
	public List<WebElement> shells; 
	
	@FindBy(xpath="//p[text()='Start now']/../p")
	public WebElement shellNowNum; 
	
	
	@FindBy(xpath="//div[@class='braingym-starttoday']/descendant::button[descendant::span[contains(text(),'Workout completed')]]")
	public WebElement workoutCompletedBtn;
	
	//shell, Start now <p>
	@FindBy(xpath="//div[@class='Oyster-and-title']/p[text()='Start now']")
	public WebElement startNowShell;
	
	@FindBy(xpath="//div[@class='flex-items-details']/descendant::button[text()='Start Now']")
	public WebElement startNowOnPopupBtn;
	
	@FindBy(xpath="//input[@type='text']")
	public WebElement fillType;
	
	@FindBy(xpath="//span[@id='docs-internal-guid-25fb84ed-7fff-5c64-44a2-825f5a642211']")
	public static WebElement questionText;
	
	@FindBy(xpath="//div[@class='question-box']/descendant::p/span") //working for english and science
	public WebElement getQuestion;
	//div[@class='question-box']/descendant::span/span => English
	//div[@class='question-box']/descendant::p/span   =>Science and Maths
	
	@FindBy(xpath="//div[@class='question-box']/descendant::h4")
	public WebElement getQuestionIfNoStatement;
	
	@FindBy(xpath="//div[@class='question-box']")
	public WebElement questionTag;
	
	
	@FindBy(xpath="//div[@class='question-box']/descendant::p") // for Maths
	public WebElement getQuestionForMaths;
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[1]/button")
	public WebElement answerOptionA;
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[2]/button")
	public WebElement answerOptionB;
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[3]/button")
	public WebElement answerOptionC;
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[4]/button")
	public WebElement answerOptionD;
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[5]/button")
	public WebElement answerOptionE;
	
		
	@FindBy(xpath="//button[@class='submit-answer-button active']")
	//@FindBy(xpath="//button[@class=contains(text(),'submit-answer-button')]")	
	public WebElement submitAnswerBtn;
	
	@FindBy(xpath="//table[@class='drag-detail-table vertical']")
	public WebElement veritcalTable;
		
	@FindBy(xpath="//div[@class='drag-item-sub']")
	public List<WebElement> listOfSourceElementsForVeritcalTable;
	
	@FindBy(xpath="//span[@class='right-part-val']") //right-part-val-common
	public List<WebElement> listOfTargetElementsForVeritcalTable;
	
	@FindBy(xpath="//div[@class='drag-item']")
	public List<WebElement> listOfsourceElementsToFillBlanks;
	
	@FindBy(xpath="//input[@class='dropbox']") //right-part-val-common
	public List<WebElement> listOftargetElementsToFillBlanks;
	
	//@FindBy(xpath="//span[text()='Select your answer']")
	//@FindBy(xpath="//div[@class='flex-items']/descendant::p[contains(text(),'Right answers')]")
	//@FindBy(xpath="//div[@class='timer-sub-right']/descendant::p[contains(text(),'Time elapsed')]")
	@FindBy(xpath="//div[@class='question-box']")
	public WebElement verificationTag;
	
	@FindBy(xpath="//button[text()='Next Question']")
	public WebElement nextQuestionBtn;
	
	@FindBy(xpath="//button[text()='Next']")
	public WebElement nextBtn;
	
	@FindBy(xpath="//button[@class='beltpopup-button']")
	public WebElement nextBtnOrGetYoutBeltBtn;
	
	@FindBy(xpath="//button[@class='beltpopup-button']")
	public List<WebElement> listOfBtnForNext; //button[@class='beltpopup-button']
	
	@FindBy(xpath="//button[@class='beltpopup-button' and text()='Next']")
	public WebElement nextBtnForNextShell;
	
	@FindBy(xpath="//button[@class='beltpopup-button' and text()='Get Your Belt']")
	public WebElement getYourBeltBtn;	//On completion of Belt
	//button[@class='beltpopup-button'].getText()== Get Your Belt
	
	@FindBy(xpath="//button[@class='beltpopup-button']/../div/following-sibling::div")
	public WebElement beltAchievedText;
	
	@FindBy(xpath="//button[@class='beltpopup-button' and text()='Next']")
	public WebElement nextBtnAfterBeltAchieved; //On completion of Belt
	
	@FindBy(xpath="//button[@class='beltpopup-button' and contains(text(),'Get Your Belt') or contains(text(),'Next') or contains(text(),'Finish')]")
	public WebElement commonBtnsInBeltpopup; //On completion of Belt
	 
	
	
	@FindBy(xpath="//div[@class='flex-items']/descendant::p[text()='Right answers']")
	public WebElement oneSetShellCompletion;
	
	@FindBy(xpath="//button[text()='Finish']")
	public WebElement finishBtn;
	
	@FindBy(xpath="//td[@class='right-part']")
	public List<WebElement> rows;
	
	@FindBy(xpath="//div[@class='swal-modal' and @role='dialog']")
	public WebElement noQuestionAvailbaleDialog;
	
	@FindBy(xpath="//div[@class='swal-modal' and @role='dialog']/div[@class='swal-text']")
	public WebElement noQuestionAvailbaleDialogText;
	
	@FindBy(xpath="//div[@class='swal-modal' and @role='dialog']/descendant::button[text()='OK']")
	public WebElement noQuestionAvailbaleDialogOKBtn;
	
	
	
	@FindBy(xpath="//div[@class='dashboard-overview dash-list-common ']/descendant::li[text()='Logout']")
	public WebElement logout;

	public String accessToken=null;
	static Map<String, Integer> QuestionsMap = new HashMap<String, Integer>();
	public static List<String> ResultListToExcel =new ArrayList<String>();
	public static String outputFilepath="./data/result3.xlsx";
	public static String outputSheetName;//="Output2";
	public static int numberOfDaysToRun=2;  //how may days of shells to complete
	public static Map<String, Integer> questionTypeHashMap = new HashMap<String, Integer>();
	public static String questionDescriptionFromDB = null;
	public static List<Integer> answersList=null;
	public static WebElement answerOption;
	
	public void clickLogout()
	{
		logout.click();
		System.out.println("Clicked on Logout button");
		
	}
	
	public void selectQuestionTypeAndAnswers(WebDriver driver,String questionId, String questionDescriptionFromDB) throws Exception
	{
		System.out.println("Inside selectQuestionTypeAndAnswers()");
		String solutionTypeNumber = mongoDBSeleniumIntegration.getSolutionType(questionId);
		System.out.println("solutionTypeNumber :"+solutionTypeNumber);
		System.out.println("Inside selectQuestionType():");
		int solutionType = Integer.valueOf(solutionTypeNumber);
		System.out.println("solutionType :"+solutionType);
		
		
		questionTypeHashMap.put("choose-the-correct-answer", 1); //single select
		questionTypeHashMap.put("statement-and-support", 2);  //single select
		questionTypeHashMap.put("drag-and-drop", 3); //drag and drop
		questionTypeHashMap.put("two-columns-option", 4);  //single select
		questionTypeHashMap.put("degrees-of-change-from-a-study", 5);
		questionTypeHashMap.put("open-ended-questions", 6);  //single select
		questionTypeHashMap.put("passage", 7);
		questionTypeHashMap.put("error-findings", 8);  //single select
		questionTypeHashMap.put("multi select", 9); //drag and drop
		questionTypeHashMap.put("match-the-following", 10);
		questionTypeHashMap.put("selecting-the-word-sentence", 11); //select word from Ques
		questionTypeHashMap.put("scrambled-and-unscrambled", 12);
		questionTypeHashMap.put("fill-in-the-blanks", 13); //Typing word
		questionTypeHashMap.put("drag-and-match-the-correct-answer", 14);
		questionTypeHashMap.put("select-the-suitable-words", 15);
		questionTypeHashMap.put("preposition-sentence", 16);
		questionTypeHashMap.put("best-suitable-position-word", 17);
		questionTypeHashMap.put("select-the-word-to-match-the-correct-option", 18);
		questionTypeHashMap.put("scrambled unscrambled", 23);
		questionTypeHashMap.put("drag on image", 24);
		questionTypeHashMap.put("multi drag and drop", 25);
		questionTypeHashMap.put("multi solution drag and drop", 26);
	   										//  isTextPresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.includes(arguments[0]);", key);
											//isTextPresent = (Boolean) ((JavascriptExecutor) driver).executeScript("var searchText = arguments[0].toLowerCase(); " +"var pageText = document.documentElement.innerText.toLowerCase(); " +"return pageText.includes(searchText);", key);
	        if(solutionType!=0)
	        {
	        	System.out.println("Inside If , Question is: "+questionTypeHashMap.containsKey(solutionTypeNumber)+" with Question SolutionType is:"+solutionType);
	        
	        	 switch(solutionType)
	     	    {
		     	    case 1:
		     	    case 2:
		     	    case 4:
		     	    case 6:
		     	    case 8:
		     	    	System.out.println(" Inside switch case 1,2,4,6,8 Found:"+ solutionType);
		     	    	multiSelect(questionId);
		     	    	return;
		     	    	
		     	    case 9:
		     	    	System.out.println(" Inside switch case 9 Found:"+ solutionType);
		     	    	multiSelect(questionId);
		     	    	return;
		     	    
		     	    case 3:
		     	    case 10:
		     	    	System.out.println(" Inside switch case 3 and 10 Found:"+ solutionType);
		     	    	//matchTheFollowing(driver, questionId);
		    	    	verticalMatchTheFollowing(driver, questionId);
		    	    	return;
		    	    	
		     	    case 14:
		     	    	System.out.println(" Inside switch case 14 Found:"+ solutionType);
		     	    	dragAndFillTheQuestion();	
		     	    	return;
		    	    	
		     	   	case 13:
		     	   		System.out.println(" Inside switch case 13 Found:"+ solutionType);
		     	   		fillInTheBlanks("text");
		     	   		return;
		     	  
		     	   	default:
		     	   		System.out.println("No Solution Type Defined: for solution Type "+solutionType);
		    	    }
	        	
	        }
	        
	      //  System.out.println("Indside loop: within");
	  //  }///For loop END
	    System.out.println("Exiting selectQuestionTypeAndAnswers(): after switch");
	    System.out.println("Exiting selectQuestionTypeAndAnswers()");
	    
	   return;
	}
	
	public void selectQuestionType(WebDriver driver) throws Exception
	{
		System.out.println("Inside selectQuestionType():");
		
		questionTypeHashMap.put("Complete the sentence with the correct form of the adverb:", 1);
		questionTypeHashMap.put("Read the text and drag the correct adverb/ adjective in the blanks:", 2);
		questionTypeHashMap.put("choose-the-correct-answer", 3);
		questionTypeHashMap.put("Multi select question", 4);
		questionTypeHashMap.put("match-the-following", 5);
		questionTypeHashMap.put("drag and drop", 6);
		questionTypeHashMap.put("Select your answer", 7);
	    	    
	    Boolean isTextPresent;
	    for (Map.Entry<String, Integer> entry : questionTypeHashMap.entrySet()) 
	    {	
	    	System.out.println("Inside selectQuestionType() For Loop:");
	    	String key = entry.getKey();
	        Integer value = entry.getValue();
	       
	      //  isTextPresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.includes(arguments[0]);", key);

	        isTextPresent = (boolean) ((JavascriptExecutor) driver).executeScript("var searchText = arguments[0].toLowerCase(); " +"var pageText = document.documentElement.innerText.toLowerCase(); " +"return pageText.includes(searchText);", key);
	        if(isTextPresent)
	        {
	        	System.out.println("Inside If , Question is:"+key+" with key value"+value);
	        
	        	 switch(value)
	     	    {
		     	    case 1:
		     	    	System.out.println(" Inside switch case 1 Found:"+ key);
		     	    	fillInTheBlanks("More Rapidly");
		     	    	return;
		     	    	
		     	    case 2:
		     	    	System.out.println(" Inside switch case 2 Found:"+ key);
		     	    	dragAndFillTheQuestion();		     	    	
		     	    	return;
		     	    
		     	    case 3:
		     	    	System.out.println(" Inside switch case 3 Found:"+ key);
		     	    	//singleChoice();	
		     	    	return;
		    	    	
		     	   	case 4:
		     	   		System.out.println(" Inside switch case 4 Found:"+ key);
		     	   		//multiSelect();
		     	   		return;
		     	   		
		     	   	case 5:
		    	    	System.out.println(" Inside switch case 5 Found:"+ key);
		    	    //	matchTheFollowing(driver);
		    	    	//verticalMatchTheFollowing(driver);
		    	    	return;
		    	    
		    	    case 6:
		    	    	System.out.println(" Inside switch case 6 Found:"+ key);
		    	    	//matchTheFollowing(driver);
		    	    //	verticalMatchTheFollowing(driver);
		    	    	return;
		   	    	
		    	   	case 7:
		    	   		System.out.println(" Inside switch case 7 Found:"+ key);
		    	   		//singleChoice();	
		    	   		return;
		    	   		
		    	   	default:
		    	   		System.out.println("Default case:");
		    	   		//verticalMatchTheFollowing(driver);
		    	   		return;
	     	    }
	        	
	        }
	        
	        System.out.println("Indside loop: within");
	    }///For loop END
	    System.out.println("Exiting selectQuestionType(): after loop");
	   return;
	}
	
//	public boolean verifyTableIsVertical()
//	{
//		if(veritcalTable.isDisplayed())
//		{
//			System.out.println("Veritical table is present");
//			return true;
//		}
//		else
//		{
//			System.out.println("Vertical table is not present");
//			return false;
//		}
//		
//		
//	}
	
	public void fillInTheBlanks(String text) throws Exception 
	{	System.out.println("Inside fillInTheBlanks()");
	
		Thread.sleep(2000);
		fillType.sendKeys(text);
	//	CompareQuestions.scrollDown(submitAnswerBtn);
		submitAnswerBtn.click();
		System.out.println("Exiting fillInTheBlanks()");
	}
	
	
	
	public void dragAndFillTheQuestion() throws Exception 
	{
		System.out.println("Inside dragAndFillTheQuestion()");
		Actions actions = new Actions(driver);
		
		for(int i=0;i<listOfsourceElementsToFillBlanks.size();i++) 
		{
			Thread.sleep(2000);
			actions.dragAndDrop(listOfsourceElementsToFillBlanks.get(i), listOftargetElementsToFillBlanks.get(i)).build().perform();
		//	Thread.sleep(2000);
		}
		//actions.build().perform();
		//Thread.sleep(3000);
		submitAnswerBtn.click();
		System.out.println("Exiting dragAndFillTheQuestion()");

	}
	
	public void matchTheFollowing(WebDriver driver, String questionId) throws Exception 
	{
		CompareQuestions.scrollDown(submitAnswerBtn);
//		if(verifyTableIsVertical())
//		{
			System.out.println("Inside if matchTheFollowing->verital table is true");
			verticalMatchTheFollowing(driver, questionId);
//		}
//		else
//		{
//			System.out.println("Inside else , matchthefollowing -> NOt vertical table");
//		
//		}
//		System.out.println("Inside matchTheFollowing()");
//		Thread.sleep(2000);
//		Actions act = new Actions(driver);
//		System.out.println("Actions initialized");
//				
//		List<WebElement> sourceElements = driver.findElements(By.xpath("//div[@class='drag-item-sub']"));
//		System.out.println("Source elements initialized, size is "+sourceElements.size());
//		List<WebElement> targetElements = driver.findElements(By.xpath("//span[@class='horizontal-right-part-val']"));
////		//span[@class='right-part-val']													class="right-part-val-common"						
//		System.out.println("target elements initialized size is:"+targetElements.size());
//		
//		for (int i=0;i<sourceElements.size();i++) 
//		{
//			System.out.println("Inside Loop:");
//			System.out.println(targetElements.get(i).getText());
//			act.dragAndDrop(sourceElements.get(i), targetElements.get(i)).build().perform();
//
//		}
//		Thread.sleep(2000);
//		submitAnswerBtn.click();
		System.out.println("Exiting matchTheFollowing()");
		return;
	}
	
	public void verticalMatchTheFollowing1(WebDriver driver) throws Exception 
	{
		System.out.println("Inside verticalMatchTheFollowing()");
		Thread.sleep(2000);
	//	CompareQuestions.scrollDown(lastBox);
		Actions actions = new Actions(driver);
		System.out.println("Actions object ID"+actions);

		for (int i=0;i<listOfSourceElementsForVeritcalTable.size();i++) 
		{	System.out.println("Inside Loop : listOfSourceElementsForVeritcalTable ID:"+listOfSourceElementsForVeritcalTable.get(i));
			
			actions.dragAndDrop(listOfSourceElementsForVeritcalTable.get(i), listOfTargetElementsForVeritcalTable.get(i)).build().perform();
			//Thread.sleep(4000);
		}
		//actions.build().perform();
		Thread.sleep(3000);
//		CompareQuestions.scrollDown(submitAnswerBtn);
		submitAnswerBtn.click();
		System.out.println("Exititng verticalMatchTheFollowing()");
//		return;
	}
	
	public void verticalMatchTheFollowing(WebDriver driver, String questionId) throws Exception 
	{
		answersList=mongoDBSeleniumIntegration.getAnswersByQuestionId(questionId);
		System.out.println("Inside verticalMatchTheFollowing()");
		CompareQuestions.scrollDown(rows.getLast());
		System.out.println("After scroll to last row");
		Actions actions = new Actions(driver);
		System.out.println("Actions object ID"+actions);
		
		for (int i=0;i<listOfSourceElementsForVeritcalTable.size();i++) 
		{	
			if(isItDisplayed(listOfSourceElementsForVeritcalTable.get(i)))
			{
			actions.dragAndDrop(listOfSourceElementsForVeritcalTable.get(i), listOfTargetElementsForVeritcalTable.get(answersList.get(i))).build().perform();
			  //WebElement sourceElement = listOfSourceElementsForVeritcalTable.get(i);
	         // WebElement targetCell = listOfTargetElementsForVeritcalTable.get(answersList.get(i));
				// actions.dragAndDrop(sourceElement, targetCell).perform();

//	          actions.clickAndHold(sourceElement)
//              .moveToElement(targetCell)
//              .release()
//              .build()
//              .perform();
			}
//	          
			System.out.println("----");
			Thread.sleep(500);
		}

		submitAnswerBtn.click();
		System.out.println("Exititng verticalMatchTheFollowing()");
//		return;
	}
	
	public void multiSelect(String questionId) throws Exception {
	
		System.out.println("Inside multiChoice()");
		answersList=mongoDBSeleniumIntegration.getAnswersByQuestionId(questionId);
	
		System.out.println("Answer List is: "+answersList);
		int answer;
		for(int i=0; i<answersList.size();i++)
		{
			answer=answersList.get(i);
			System.out.println("Answer value is: "+ answer);
			switch(answer)
			{
			case 0:
				if(isItDisplayed(answerOptionA))
				{
					answerOptionA.click();
					break;
				}				
			
			case 1:
				if(isItDisplayed(answerOptionB))
				{
					answerOptionB.click();
					break;
				}
							
			case 2:
				if(isItDisplayed(answerOptionC))
				{
					answerOptionC.click();
					break;
				}
			
			case 3:
				if(isItDisplayed(answerOptionD))
				{
					answerOptionD.click();
					break;
				}
				
			case 4:
				if(isItDisplayed(answerOptionE))
				{
					answerOptionE.click();
					break;
				}
				
				
			default:
				if(isItDisplayed(answerOptionB))
				{
					answerOptionB.click();
					System.out.println("Clicked on Random answer");
					break;
				}
				
			
				
			}
			Thread.sleep(200);
		}
		
		if(submitAnswerBtn.isEnabled()) 
		{
			CompareQuestions.scrollDown(submitAnswerBtn);
			submitAnswerBtn.click();			
		
		}

		System.out.println("Exiting multiChoice()");
	}
	
	
	public void singleChoice(String questionId) throws Exception 
	{
		System.out.println("Inside singleChoice()");
		answersList=mongoDBSeleniumIntegration.getAnswersByQuestionId(questionId);
		Thread.sleep(2000);
		System.out.println("Answer List is: "+answersList);
		String path="//div[@class='question-options-wrapper selectTyleType']/div["+(answersList.get(0)+1)+"]/button";
		System.out.println("xpath is: "+path);
		answerOption= driver.findElement(By.xpath(path));
			
		
	//	CompareQuestions.scrollDown(submitAnswerBtn);
		//answerOptionA.click();
		answerOption.click();
		submitAnswerBtn.click();
		System.out.println("exiting singleChoice()");
		Thread.sleep(2000);
		//getPageSource();
	}
	
	public void getPageSource()
	{
		System.out.println("before geting page source");
		String pageSource = driver.getPageSource();
		System.out.println(pageSource);
	}
	
	public String login(String un, String pw, String grade, String subject) throws InterruptedException
	{
		
			
			System.out.println("Username:"+un);
			System.out.println("Password:"+pw);
			System.out.println("grade:"+grade);
			System.out.println("subject:"+subject);
			LoginPage login=new LoginPage();
			login.clickOnloginBtnOnDashboard();
			login.clickOnOptionLoginAs();
			login.clickOnNext();
			login.setUserName(un);
			login.setPassword(pw);
			login.clickLoginButton();
			System.out.println("accessToken value before calling userRegistrationSuccessful(): "+ accessToken);
			accessToken=ToReadResponse.userRegistrationSuccessful();
			//System.out.println("Access Token in BG page is:  "+accessToken);
			System.out.println("accessToken value After calling userRegistrationSuccessful(): "+ accessToken);
			
			return accessToken;
	}
	
	public int getShellCount()
	{
	
		return shells.size();
		
	}
	
//	public void prepareDB(int numberOfDays) throws Exception
//	{
//		UpdateDB.changeDate(numberOfDays);
//		Thread.sleep(4000);
//	}
//	
	public void numberOfDaystoRun(WebDriver driver, WebDriverWait wait, String accessToken,String un, String pw, String grade, String subject,String studentId, String subjectId, int numberOfDays) throws Exception
	{
		//int count=0;
		
		//do 
		for(int i=0;i<numberOfDays;i++)
		{		//prepareDB(numberOfDays);
				System.out.println("RUnning "+ i +"th day");
				driver.navigate().refresh();
				//Thread.sleep(1000);
				clickleftLinkBrainGym(wait);
				selectSubject(subject);
				clickWorkoutBtn();
				
				
				testPerDayShells(driver, wait, un, pw, grade, subject, studentId, subjectId,i);
				//studentStrings.add(StringUtils.join(un,pw,grade,subject,"pass","comments"));
				//count++;
				if(i==0)
				{
					System.out.println("Inside if stmt for updateBrainGymMappings first time ");
					mongoDBSeleniumIntegration.updateBrainGymMappings(mongoDBSeleniumIntegration.getStudentIdByEmail());
					Thread.sleep(3000);
					driver.navigate().refresh();
				//	Thread.sleep(3000);
					
				}
				if(i>0)
				{
					System.out.println("Inside if stmt for updateBrainGymMappings "+ i+" time");
					mongoDBSeleniumIntegration.updateBrainGymMappings1(mongoDBSeleniumIntegration.getStudentIdByEmail(),i);
					Thread.sleep(3000);
					driver.navigate().refresh();
					//Thread.sleep(3000);
				}
		}
		//}while(count < numberOfDays);
	}
	
	public void numberOfDaystoRun1(WebDriver driver, WebDriverWait wait, String un, String pw, String grade, String subject,int numberOfDays) throws Exception
	{
		int count=0;
		
		do 
		{		//prepareDB(numberOfDays);
				driver.navigate().refresh();
				Thread.sleep(3000);
				clickleftLinkBrainGym(wait);
				selectSubject(subject);
				clickWorkoutBtn();
				//testPerDayShells(driver, wait, un, pw, grade, subject);
				//studentStrings.add(StringUtils.join(un,pw,grade,subject,"pass","comments"));
				count++;
		}while(count < numberOfDays);
	}
	
	public void testPerDayShells(WebDriver driver, WebDriverWait wait, String un, String pw, String grade, String subject, String studentId, String subjectId, int n) throws Exception
	{	
		
		//String asPerDayShellsAreFinsihed ;
		String shellStatus="no";
		System.out.println("Inside testPerDayShells method");
		//String shellAvailable="no";
		List<String> chestIds=ToReadResponse.getChestIds(accessToken,studentId,subjectId);
		System.out.println("Count Of chestIds is: "+chestIds.size());
		int numberOfShellsToLoop=getShellCount();
		System.out.println("numberOfShellsToLoop value is: "+numberOfShellsToLoop);
		String chestId=null;
		
		for(int i=0; i<numberOfShellsToLoop; i++)  //start from chestIds 0 index
		//for(int i=0; i<chestIds.size(); i++)
		 {
			System.out.println("Inside dowhile loop in testPerDayShells method");
			System.out.println("Running "+n+"th day and "+i+" th shell");
			//clickStartNowShell();
			//clickStartNowOnPopupBtn();

			
			chestId=chestIds.get(i);
			String shellReturnStatus=testOneShell(driver, un,pw,grade,subject,shellStatus,chestId);
			
			if(shellReturnStatus.equals("Shell Completed"))
			{
				System.out.println("completed One shell, waiting to click Next button");			
				//clickNextBtn(); // Click on after one loop of 5 shells
				
				clickNextBtnForNextShell(); //click on Belt Next button
				
				if(isItDisplayed(finishBtn))
					{
						if(isItEnabled(finishBtn))
						{
							System.out.println("Shells Completed for the day");
							finishBtn.click();
							
						}
					}
				else
				{
					System.out.println("Shells  not over!!!");
				}

			}
			else if(shellReturnStatus.equals("No questions available"))
			{
				
				System.out.println("inside else shellReturnStatus.equals(\"No questions available\" " );
				System.out.println("Clicking on noQuestionAvailbaleDialogOKBtn" );
				if(isItDisplayed(noQuestionAvailbaleDialogOKBtn))
				{
					noQuestionAvailbaleDialogOKBtn.click();
				}
				
				if(isItDisplayed(nextBtn))
				{
					nextBtn.click();
				}
				
				System.out.println("Clicking omn commonBtnsInBeltpopup");
				if(isItDisplayed(commonBtnsInBeltpopup))
				{
					commonBtnsInBeltpopup.click();
				}
				
			}
						
			System.out.println("Shell "+i+ "Completed !");
		}
		System.out.println("All Shells Completed!!!");
		
	}

	
	public String testOneShell(WebDriver driver, String un, String pw, String grade, String subject, String questionsCompletedStatus,String chestId) throws Exception
	{
		
		clickStartNowShell();
		clickStartNowOnPopupBtn();
		String questionTextFromUI;
		String questionId=null;
//--		getQuestionText();
			
		if(isItDisplayed(noQuestionAvailbaleDialog))
		{
			System.out.println("No Question displayed in this shell: Questions may be over!!!!!!!!! returning from testOneShell() method");
			String text=noQuestionAvailbaleDialogText.getText();
			System.out.println("Dialog text is: "+text);
			return text;
		}
		
		do
		{
			Thread.sleep(2000);
		
			//questionTextFromUI=isQuestionDisplayedAndGetText();
			
			questionId=ToReadResponse.getQuestionId(accessToken,chestId);
			
			
			if(questionId==null)
			{
				System.out.println("Question ID is empty, Exiting from execution");
				Thread.sleep(100000);
			//	driver.quit();
			}
			String questionDescriptionFromDB = mongoDBSeleniumIntegration.getQuestionStatement(questionId);
			//String solutionTypeNumber = mongoDBSeleniumIntegration.getSolutionType(questionId);
			System.out.println("Solution Type is: "+mongoDBSeleniumIntegration.getSolutionType(questionId));
			questionTextFromUI=getQuestionText(subject);
			//questionText=getQuestionText();
			//System.out.println("New Question: "+questionText);
			System.out.println("Question Id From DB is: "+questionId);			
			System.out.println("question Description From DB : "+questionDescriptionFromDB);
			System.out.println("Question Description From UI: "+questionTextFromUI);
			
			System.out.println("Before calling selectQuestionType()");

			System.out.println("After calling selectQuestionType()");
			selectQuestionTypeAndAnswers(driver,questionId, questionDescriptionFromDB);
			//If condition for Question from DB
			if(CompareQuestions.SearchQuestionIdAndInsertToHashMap(questionDescriptionFromDB, questionId))
			{
				softAssert.assertTrue(true, questionDescriptionFromDB);
				System.out.println("IF");
				Reporter.log("Pass: No Duplicate Found");
				String joinedString = StringUtils.join(un + "," + grade + "," + subject + "," + "Pass" + "," + questionDescriptionFromDB + "," +  questionTextFromUI + "," + questionId);
				System.out.println("joinedString is :"+joinedString);
				ResultListToExcel.addLast(joinedString);
			}
			else
			{
				softAssert.assertTrue(false, questionDescriptionFromDB);
				System.out.println("ELSE");
				Reporter.log("Fail: Duplicate Found");
				//Write code to write into Excel
				String joinedString = StringUtils.join(un + "," + grade + "," + subject +  "," + "Fail" + ","  + questionDescriptionFromDB + "," +  questionTextFromUI + "," + questionId);
				System.out.println("joinedString is :"+joinedString);
				ResultListToExcel.addLast(joinedString);
			}
						
			
			//clickAnswerOption();
			//clickSubmitAnswerBtn();
			//clickNextQuestionBtn();
			
			
			questionsCompletedStatus=verifyQuestionsCompletedOrNot();
		
			System.out.println("Questions Status is:"+questionsCompletedStatus);
			
			//controlFlowForNextQuestionOrNextShell();
			
		}while(questionsCompletedStatus.equals("no"));
		System.out.println("After End of While Loop");
		
		return "Shell Completed";
		
	}
	
	
	
	
	public void selectSubject(String sub) 
	{
		try 
		{
			for (WebElement subject : subjectsTabs) 
			{
				System.out.println(subject);
				if(subject.getText().equals(sub)) 
				{
					subject.click();
				}
			}
		}
		catch(NoSuchElementException e)
		{
			System.err.println(e);
		}
		
	}
	

	
	public BrainGymPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	
	public void clickleftLinkBrainGym(WebDriverWait wait)
	{
		wait.until(ExpectedConditions.visibilityOf(leftLinkBrainGym));
		leftLinkBrainGym.click();
	}
	
	public void clickSubject()
	{
		
		subjectScience.click();
	}
	
//	public String isQuestionDisplayedAndGetText() throws NoSuchElementException
//	{
//		System.out.println("Inside isQuestionDisplayedAndGetText() ");
//		String res="false";
//		 String text;
//		 String script;
//		 WebElement childElement ;
//		try
//		{
//			if(isItDisplayed(questionTag))
//			{
//				 childElement = questionTag.findElement(By.xpath(".//p | .//span"));
//		        // Get the text from the child element
//		         text = childElement.getText();
//		         System.out.println("Text from the element: " + text);
//			}
//	       
////			res= commonParent.isDisplayed();
////			System.out.println("Element state is Enabled in : "+element+"-"+res);
//		}
//		catch(NoSuchElementException e) 
//		{
//			System.err.println("Question Tag Not Found: in isItDisplayed(): "+questionTag);
//			e.printStackTrace();
//		}
//		return res;
//		
//	}
	
	
	public Boolean isItDisplayed(WebElement element) throws NoSuchElementException
	{
		Boolean res=false;
		try
		{
			res= element.isDisplayed();
			System.out.println("Element state is Enabled in isItDisplayed(): "+element+"-"+res);
		}
		catch(NoSuchElementException e) 
		{
			System.err.println("Not Found: in isItDisplayed(): "+element+"-"+res);
			e.printStackTrace();
		}
		return res;
		
	}
	
	public Boolean isItEnabled(WebElement element) throws NoSuchElementException//, InterruptedException
	{	//Thread.sleep(1000);
		Boolean res=false;
		try
		{
			res= element.isEnabled();
			
		}
		catch(NoSuchElementException e) 
		{
			System.err.println(element+" : Not Found in isItEnabled()");
			e.printStackTrace();
		}
		return res;
		
	}
	
	public void clickWorkoutBtn()
	{
		if(isItDisplayed(workoutBtn))
		{	
			if(isItEnabled(workoutBtn))
			{
				
				workoutBtn.click();
			}
		}
	}

	public void clickStartNowShell() throws InterruptedException
	{
		if(isItDisplayed(startNowShell))
		{
			if(isItEnabled(startNowShell))
			{
				startNowShell.click();
			}
		}
	}
	
	public void clickStartNowOnPopupBtn() throws InterruptedException
	{
		if(isItDisplayed(startNowOnPopupBtn))
		{
			if(isItEnabled(startNowOnPopupBtn))
			{
				startNowOnPopupBtn.click();
			}
		}
		//Thread.sleep(20000);
	}
	public String getQuestionText(String subject) throws InterruptedException
	{
		System.out.println("INside getQuestionText() method");
		String question = null;
		
		if(subject.equals("Mathematics"))
			{
			System.out.println("Inside Maths");
			question=getQuestionForMaths.getText();
			}
			else
			{
				System.out.println("Inside other other subject");
				if(isItDisplayed(getQuestion))
				{
					System.out.println("Question element is displayed:");
					question=getQuestion.getText();
				}
				else
				{
					System.out.println("Question element is Not displayed: So get heading \"Questions\" only");
					if(isItDisplayed(getQuestionIfNoStatement))   
					{
						question=getQuestionIfNoStatement.getText();
						//childElement = questionTag.findElement(By.xpath(".//p | .//span"));
				        // Get the text from the child element
				        // text = childElement.getText();
						
						
						if(question==null)
						{
							System.out.println("Question description not found in UI: ");
							System.out.println("Quit script");
							driver.close();
							driver.quit();
							
						}
						else
						{
							System.out.println("Question statement is empty:");
						}
					}
				
				}
			
			}
		System.out.println("Question is: "+question);
		addQuestionToMap(question); //not using now, instead using questionTypeHashMap with solution type
		Thread.sleep(2000);
		return question;
		
	}
	
	public String verifyOneShellSetCompletion(WebDriverWait wait)
	{
		System.out.println("Inside verifyOneShellSetCompletion()");
		String status="no";
		try 
		{
			System.out.println("inside verifyOneShellSetCompletion try block");
			if(oneSetShellCompletion.isDisplayed())
			{
				System.out.println("inside verifyOneShellSetCompletion try block IF block");
				status="yes";
				return status;
			}
			else
			{
				System.out.println("inside verifyOneShellSetCompletion try block ELSE block");
				System.out.println("Text Right Answer is not displayed to verify completion of One set of Shell");
			}
		}
		catch(Exception e)
		{
			
		}
		return status;
		
	}
	 public static boolean isNullOrEmptyMap(Map < ? , ? > map) 
	 {
		 System.out.println("INside isNullOrEmptyMap() method");
	        return (map == null || map.isEmpty());
	 }

	
	public void addQuestionToMap(String question)   //stores questions and its repetition count
	{
		 System.out.println("INside addQuestionToMap() method");
		int count=0;
		if(isNullOrEmptyMap(QuestionsMap))
		{
			
			System.out.println("HashTable is empty");
			System.out.println("\nINserting FIRST Question: "+ question+"\n");
			QuestionsMap.put(question, 1);
			//QuestionsMap.
		}
		else
		{
			System.out.println("HashTable is NOT empty");
			System.out.println("\nINserting Question: "+ question+"\n");
			if(QuestionsMap.containsKey(question))
			{
				count = QuestionsMap.containsKey(question) ? QuestionsMap.get(question) : 0;
				QuestionsMap.put(question, count+1);
			}
			
			
		}
		
		
	}
	
	public void displayHashTable()
	{
		System.out.println("INside displayHashTable() method");
		for(Map.Entry<String, Integer> entry : QuestionsMap.entrySet()) 
		{
//			if(entry.getValue()>=0) 
//			{
				System.out.println(entry.getKey() +"==>"+ entry.getValue());
//			}
			
		}
	}
	
	public void clickAnswerOption()
	{
		System.out.println("Inside click AnswerOption()");
		
		if(isItDisplayed(answerOptionA))
		{
			if(isItEnabled(answerOptionA))
			{
				answerOptionA.click();
			}
		}
	}
	
	public void clickSubmitAnswerBtn()
	{
		System.out.println("In clickSubmitAnswerBtn()");
		if(isItDisplayed(submitAnswerBtn))
		{
			if(isItEnabled(submitAnswerBtn))
			{
				submitAnswerBtn.click();
			}
		}
	}
	


	
//	public String controlFlowForNextQuestionOrNextShell()
//	{
//		System.out.println("Inside controlFlowForNextQuestionOrNextShell()");
//		String text;
//		String status="no";
//		int numberOfBtns;
//		
//		if(isItDisplayed(nextBtnForNextShell))
//		{
//			System.out.println("Inside If isItDisplayed(nextBtnForNextShell)");
//			
//			numberOfBtns=listOfBtnForNext.size();
//			System.out.println("numberOfBtns: "+numberOfBtns);
//			if(numberOfBtns==1)
//			{
//				System.out.println("Inside if numberOfBtns==1 ");
//				text=listOfBtnForNext.get(0).getText();
//				if(text.equals("Next"))
//				{
//					System.out.println("Inside if NExt: ");
//					handleNextBtnsForShellsExceptLast();
//					System.out.println("Status: "+status);
//					
//					//One shell completed and navigates to "Start Shell" page to go through next shell
//				}
//				else if(text.equals("Get Your Belt"))
//				{
//					System.out.println("Inside else if GetYourBelt intermediate shells");
//					handleNextBtnsIfLastShell();
//					System.out.println("Status: "+status);
//				//	One Day Shells Completed after clicking Finish, navigates to BrainGym Home page
//				}
//			}
//			else if(numberOfBtns==2)
//			{
//				System.out.println("Inside If numberOfBtns==2");
//				text=listOfBtnForNext.get(0).getText();
//				if(text.equals("Next"))
//				{
//					System.out.println("Inside If btns2 Next");
//					handleGetYourBeltBtnShellsExceptLast();
//				}
//				else if(text.equals("Get Your Belt"))
//				{
//					System.out.println("Inside elseIf GetYourBelt Last Shell");
//					handleGetYourBeltNextShellsAndFinishBtn();
//				}
//			}
//		}
//		
//		return status;
//	}
	
	public void handleGetYourBeltNextShellsAndFinishBtn()
	{
		System.out.println("handleNextShellsExceptLastAndFinishBtn()");
		getYourBeltBtn.click();
		nextQuestionBtn.click();;
		finishBtn.click();
		//return "yes";
		
	}
	
	public void handleGetYourBeltBtnShellsExceptLast()
	{
		getYourBeltBtn.click();
		nextBtn.click();
		//return "no";
		//after click navigates to Start ShellNow page
	}
	
	public void handleNextBtnsIfLastShell()
	{
		nextBtnForNextShell.click();
		finishBtn.click();
	//	return "yes";
	}
	
	
	public void handleNextBtnsForShellsExceptLast() 
	{
		nextBtnForNextShell.click();
		//return "yes";
		//		
	}

	


	
	public String verifyQuestionsCompletedOrNot() throws NoSuchElementException, InterruptedException
	{
		System.out.println("Inside verifyShellCompleted()");
		String status="no";
		try 
		{
			System.out.println("inside verifyShellCompleted try block");
			
			try
			{
				Thread.sleep(3000);
				//if(nextQuestionBtn.isDisplayed())
				if(isItDisplayed(nextQuestionBtn))
				{
					System.out.println("Inside nextQuestionBtn.isDisplayed()try block- returns No");
					nextQuestionBtn.click();
					status="no";
				}
			}
			catch(NoSuchElementException e)
			{e.printStackTrace();}
			
			try
			{	
				//if(verificationTag.isDisplayed())
				System.out.println("before verificationTag");
				if(isItDisplayed(verificationTag))
				{
				
					System.out.println("inside nextQuestionBtn.isDisplayed() -return no- Shell NOT Completed");
					status="no"; //shell not completed 
					return status;
				}
				else if(isItDisplayed(commonBtnsInBeltpopup))
				{
					do
					{
						commonBtnsInBeltpopup.click();
						status="yes";
						
					}while(isItDisplayed(commonBtnsInBeltpopup));
					return  status;
				}
				
			}
			catch(NoSuchElementException e)
			{e.printStackTrace();}
			
			try 
			{
			
				//if(nextBtn.isDisplayed())
				if(isItDisplayed(nextBtn))
				{
					System.out.println("Inside nextBtn.isDisplayed() try block - return yes");
					nextBtn.click();
					status="yes";
					return status;
				}
				else if(isItDisplayed(commonBtnsInBeltpopup))
				{
					do
					{
						commonBtnsInBeltpopup.click();
						
					}while(isItDisplayed(commonBtnsInBeltpopup));
				}
			}
			catch(NoSuchElementException e)
			{e.printStackTrace();}			
			
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
		return status;
		
	}

	
	public String verifyNextShellStartNowIsAvailable() throws InterruptedException 
	{
		Thread.sleep(2000);
		System.out.println("Inside verify Next Shell Is Available()");
		String status="no";
		try 
		{
			System.out.println("inside try block");
			if(startNowShell.isDisplayed())
			{
				if(startNowShell.isEnabled())
				{
					System.out.println("inside try block IF Stmt");
					status="yes";
					return status;
				}
				else
				{
					return status;
				}
			}
			else
			{
				return status;
			}
		}
		catch(Exception e)
		{
			
		}
		return status;
		
	}

	public String verifyperDayShellsAreCompleted() {
		
		System.out.println("Inside verify Shell Is Completed()");
		String status="no";
		try 
		{
			System.out.println("inside try block");
			if(isItDisplayed(finishBtn))
			{
				System.out.println("inside try block IF Stmt");
				status="yes";
				return status;
			}
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public void clickFinishButton()
	{
	
		if(isItDisplayed(finishBtn))
		{
			if(isItEnabled(finishBtn))
			{
				finishBtn.click();
			}
		}
		
	}

	public void clickNextBtn() {
		nextBtn.click();
		
	}
	
	public void clickNextBtnForNextShell()
	{
		if(verifyElementIsDisplayedAndEnabled(nextBtnForNextShell))
		{
			nextBtnForNextShell.click();
		}
	}
	
	public Boolean verifyElementIsDisplayedAndEnabled(WebElement element)
	{
		try
		{
			if(element.isDisplayed())
			{
				if(element.isEnabled())
				{
					return true;
				}
				else
				{
					System.out.println("Element Not enabled"+element);
				}
				return false;
			}
			else
			{
				System.out.println("Element Not Displayed"+element);
				return false;
			}
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
		
		return false;
		
	}
	
}
