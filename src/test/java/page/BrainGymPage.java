package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
import utilities.CompareQuestions;
import utilities.JS;
import utilities.ToReadResponse;
import utilities.UpdateDB;

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
	
	@FindBy(xpath="//div[@class='question-box']/descendant::p") // for Maths
	public WebElement getQuestionForMaths;
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[1]/button")
	public WebElement answerOptionA;
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[2]/button")
	public WebElement answerOptionB;
		
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
	
	@FindBy(xpath="//button[@class='beltpopup-button' and text()='Next']")
	public WebElement nextBtnForNextShell;
	
	@FindBy(xpath="//div[@class='flex-items']/descendant::p[text()='Right answers']")
	public WebElement oneSetShellCompletion;
	
	@FindBy(xpath="//button[text()='Finish']")
	public WebElement finishBtn;
	
	@FindBy(xpath="(//td[@class='right-part'])[4]")
	public WebElement lastBox;

	public static String accessToken=null;
	static Map<String, Integer> QuestionsMap = new HashMap<String, Integer>();
	public static List<String> ResultListToExcel =new ArrayList<String>();
	public static String outputFilepath="./data/result2.xlsx";
	public static String outputSheetName="Output7";
	public static int numberOfDaysToRun=2;  //how may days of shells to complete
	public static Map<String, Integer> stringIntHashMap = new HashMap<String, Integer>();
	
	public void selectQuestionType(WebDriver driver) throws Exception
	{
		System.out.println("Inside selectQuestionType():");
		
		stringIntHashMap.put("Complete the sentence with the correct form of the adverb:", 1);
	    stringIntHashMap.put("Read the text and drag the correct adverb/ adjective in the blanks:", 2);
	    stringIntHashMap.put("choose-the-correct-answer", 3);
	    stringIntHashMap.put("Multi select question", 4);
	    stringIntHashMap.put("match-the-following", 5);
	    stringIntHashMap.put("drag and drop", 6);
	    stringIntHashMap.put("Select your answer", 7);
	    	    
	    Boolean isTextPresent;
	    for (Map.Entry<String, Integer> entry : stringIntHashMap.entrySet()) 
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
		     	    	singleChoice();	
		     	    	return;
		    	    	
		     	   	case 4:
		     	   		System.out.println(" Inside switch case 4 Found:"+ key);
		     	   		multipleChoice();
		     	   		return;
		     	   		
		     	   	case 5:
		    	    	System.out.println(" Inside switch case 5 Found:"+ key);
		    	    	matchTheFollowing(driver);
		    	    	//verticalMatchTheFollowing(driver);
		    	    	return;
		    	    
		    	    case 6:
		    	    	System.out.println(" Inside switch case 6 Found:"+ key);
		    	    	//matchTheFollowing(driver);
		    	    	verticalMatchTheFollowing(driver);
		    	    	return;
		   	    	
		    	   	case 7:
		    	   		System.out.println(" Inside switch case 7 Found:"+ key);
		    	   		singleChoice();	
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
			actions.dragAndDrop(listOfsourceElementsToFillBlanks.get(i), listOftargetElementsToFillBlanks.get(i)).build().perform();
		//	Thread.sleep(2000);
		}
		//actions.build().perform();
		Thread.sleep(3000);
		submitAnswerBtn.click();
		System.out.println("Exiting dragAndFillTheQuestion()");

	}
	
	public void matchTheFollowing(WebDriver driver) throws Exception 
	{
		CompareQuestions.scrollDown(submitAnswerBtn);
//		if(verifyTableIsVertical())
//		{
			System.out.println("Inside if matchTheFollowing->verital table is true");
			verticalMatchTheFollowing(driver);
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
	
	public void verticalMatchTheFollowing(WebDriver driver) throws Exception 
	{
		System.out.println("Inside verticalMatchTheFollowing()");
		Thread.sleep(2000);
		CompareQuestions.scrollDown(lastBox);
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
	
	public void multipleChoice() throws Exception {
		//Thread.sleep(2000);
		System.out.println("Inside multiChoice()");
	
		answerOptionA.click();
		if(submitAnswerBtn.isEnabled()) 
		{
			CompareQuestions.scrollDown(submitAnswerBtn);
			submitAnswerBtn.click();
			Thread.sleep(2000);
			getPageSource();
		}
		else 
		{
			answerOptionB.click();
			CompareQuestions.scrollDown(submitAnswerBtn);
			submitAnswerBtn.click();
			Thread.sleep(2000);
			getPageSource();
		}
		System.out.println("Exiting multiChoice()");
	}
	
	
	public void singleChoice() throws Exception 
	{
		System.out.println("Inside singleChoice()");
		
		//Thread.sleep(1000);
		answerOptionA.click();
	//	CompareQuestions.scrollDown(submitAnswerBtn);
		submitAnswerBtn.click();
		System.out.println("exiting singleChoice()");
		Thread.sleep(2000);
		getPageSource();
	}
	
	public void getPageSource()
	{
		System.out.println("before geting page source");
		String pageSource = driver.getPageSource();
		System.out.println(pageSource);
	}
	
	public void login(String un, String pw, String grade, String subject) throws InterruptedException{
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
		}
	}
	
	public int getShellCount()
	{
		return shells.size();
	}
	
	public void numberOfDaystoRun(WebDriver driver, WebDriverWait wait, String un, String pw, String grade, String subject, int numberOfDays) throws Exception
	{
		int count=0;
		
		do 
		{		
				UpdateDB.changeDate(numberOfDays);
				Thread.sleep(4000);
				driver.navigate().refresh();
				Thread.sleep(3000);
				clickleftLinkBrainGym(wait);
				selectSubject(subject);
				clickWorkoutBtn();
				testPerDayShells(driver, wait, un, pw, grade, subject);
				//studentStrings.add(StringUtils.join(un,pw,grade,subject,"pass","comments"));
				count++;
		}while(count < numberOfDays);
	}
	
	public void testPerDayShells(WebDriver driver, WebDriverWait wait, String un, String pw, String grade, String subject) throws Exception
	{	
		//String asPerDayShellsAreFinsihed ;
		String shellStatus="no";
		System.out.println("Inside testPerDayShells method");
		//String shellAvailable="no";
		accessToken=ToReadResponse.userRegistrationSuccessful();
		System.out.println("Access Token in BG page is:  "+accessToken);
		List<String> chestIds=ToReadResponse.getChestIds();
		System.out.println("Count Of chestIds is: "+chestIds.size());
		int numberOfShells=getShellCount();
		
		
		for(int i=1; i<=numberOfShells; i++)
		 {
			System.out.println("Inside dowhile loop in testPerDayShells method");
			//clickStartNowShell();
			//clickStartNowOnPopupBtn();
			
			testOneShell(driver, un,pw,grade,subject,shellStatus);
			
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
				
				
				
//			shellAvailable=verifyNextShellStartNowIsAvailable(); //verify StartNowShell is enabled to click
//			System.out.println("verify Next Shell Start Now Is Available:  "+shellAvailable);
//			if(shellAvailable.equals("no"))
//			{
//				asPerDayShellsAreFinsihed = verifyperDayShellsAreCompleted(); //Verify Finish Button is displayed?
//				System.out.println("has Per Day Shells Are Finsihed:  "+asPerDayShellsAreFinsihed);
//				
//				if(asPerDayShellsAreFinsihed.equals("yes"))
//				{
//					System.out.println("before clicking clickFinishperDayShells  in IF");
//					
//					clickFinishButton();
//				}
//			}
//			else if(shellAvailable.equals("yes"))
//			{
//				System.out.println("Still Next Shell is availabele and enabled to operate");
//			}
			
			
		}
		
	}

	
	public void testOneShell(WebDriver driver, String un, String pw, String grade, String subject, String shellStatus) throws Exception
	{

		clickStartNowShell();
		clickStartNowOnPopupBtn();
		String questionText;
//--		getQuestionText();
			
		
		do
		{
			
			questionText=getQuestionText(subject);
			//questionText=getQuestionText();
			System.out.println("New Question: "+questionText);
//			System.out.println("---------------");
//			System.out.println(utilities.mongoDBSeleniumIntegration.getAnswer());
//			System.out.println(">>>>>>>>>>>>>.");
			
			System.out.println("Before calling selectQuestionType()");
			selectQuestionType(driver);
			System.out.println("After calling selectQuestionType()");
			if(CompareQuestions.SearchAndInsertToHashMap(questionText))
			{
				softAssert.assertTrue(true, questionText);
				System.out.println("IF");
				Reporter.log("Pass: No Duplicate Found");
				
				//Write code to write into Excel
			    String joinedString = StringUtils.join(un + "," + grade + "," + subject + ",Pass," + questionText);
			    System.out.println("joinedString is :"+joinedString);
			    ResultListToExcel.addLast(joinedString);
							
			}
			else
			{
				softAssert.assertTrue(false, questionText);
				System.out.println("ELSE");
				Reporter.log("Fail: Duplicate Found");
				//Write code to write into Excel
				String joinedString = StringUtils.join(un + "," + grade + "," + subject + ",Fail," + questionText);
				System.out.println("joinedString is :"+joinedString);
				ResultListToExcel.addLast(joinedString);
				
			}		
			
			
			//clickAnswerOption();
			//clickSubmitAnswerBtn();
			//clickNextQuestionBtn();
			shellStatus=verifyShellCompleted();
		
			System.out.println("Shell Status is:"+shellStatus);
			
		}while(shellStatus.equals("no"));
		System.out.println("After End of While Loop");
		
	}
	
	
	
	
	public void selectSubject(String sub) 
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
	
//	public static String getQuestionText() {
////		String text = questionText.getText().trim();
//		 WebElement element = driver.findElement(By.xpath("//span[contains(@id,'docs-internal-guid')]/../."));
//
//		 String text = element.getAttribute("outerHTML");
//	        // Print the element's HTML
//	        System.out.println("Inside getQuestionText() , text is "+text);
//		return text;
//	}
	
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
	
//	workoutCompletedBtn
//	public void verifyWorkoutCompleted() throws InterruptedException
//	{
//		wait(0);
//
//		//Thread.sleep(10000);
//	}

	public Boolean isItDisplayed(WebElement element) throws NoSuchElementException
	{
		Boolean res=false;
		try
		{
			res= element.isDisplayed();
			System.out.println("Workout Button is Enabled");
		}
		catch(NoSuchElementException e) 
		{
			System.err.println(element+" : Not Found in isItDisplayed()");
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
//	public void clickWorkoutBtn() throws InterruptedException
//	{
//		try
//		{
//			if(workoutBtn.isDisplayed())
//			{
//				workoutBtn.click();
//			}
//			else
//			{
//				System.out.println("Workout Button is not displayed");
//			}
//		}
//		catch(NoSuchElementException e)
//		{
//			e.printStackTrace();
//		}
	
		//Thread.sleep(10000);
//	}
	
	
//	public void clickStartNowShell() throws InterruptedException
//	{
//		System.out.println("Trying to click Start Now Button");
//		if(verifyElementIsDisplayedAndEnabled(startNowShell))
//		{
//			startNowShell.click();
//			System.out.println("Clicked on New Shell to Start");
//		}
//		else
//		{
//			System.out.println("Shell Not enabled to start");
//		}
//		
//		//Thread.sleep(20000);
//	}

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
		String question;
		
		if(subject.equals("Mathematics"))
			{
			System.out.println("Inside Maths");
			question=getQuestionForMaths.getText();
			
			}
		else
		{
			System.out.println("Inside other other subject");
			question=getQuestion.getText();
		}
		System.out.println("Question is: "+question);
		addQuestionToMap(question);
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

	
	public void addQuestionToMap(String question)
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
	

//	public void clickNextQuestionBtn()
//	{
//		if(nextQuestionBtn.isDisplayed())
//		{
//			System.out.println("Displayed Next Question  Button");
//			nextQuestionBtn.click();
//			if(verificationTag.isDisplayed())
//			{
//				System.out.println("Next Question Displayed.....");
//			}
//			else if(nextBtn.isDisplayed())
//			{
//				System.out.println("Next question not displayed");
//			}
//			
//			
//		}
//		else
//		{
//			System.out.println("No Next Question  Button");
//			
//		}
//		return;
//	}
//	
//	public String verifyShellCompletedSingle() throws NoSuchElementException
//	{
//		System.out.println("Inside verifyShellCompleted()");
//		String status="yes";
//		try 
//		{
//			System.out.println("inside verifyShellCompleted try block");
//			if(verificationTag.isDisplayed())
//			{
//				System.out.println("inside verifyShellCompleted try block IF block");
//				System.out.println("Verifying  Question Tag is displayed?");
//				status="no";
//				return status;
//			}
//			else if(nextBtn.isDisplayed())
//			{
//				System.out.println("inside verifyShellCompleted try block ELSE block");
//				System.out.println("Verifying  NEXT button is displayed?");
//				status="yes";
//				return status;
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return status;
//		
//	}
	public String verifyShellCompleted() throws NoSuchElementException, InterruptedException
	{
		System.out.println("Inside verifyShellCompleted()");
		String status="no";
		try 
		{
			System.out.println("inside verifyShellCompleted try block");
			
			try
			{
				Thread.sleep(3000);
				if(nextQuestionBtn.isDisplayed())
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
				if(verificationTag.isDisplayed())
				{
				
					System.out.println("inside nextQuestionBtn.isDisplayed() -return no- Shell NOT Completed");
					status="no"; //shell completed
					return status;
				}
				
			}
			catch(NoSuchElementException e)
			{e.printStackTrace();}
			
			try 
			{
				if(nextBtn.isDisplayed())
				{
					System.out.println("Inside nextBtn.isDisplayed() try block - return yes");
					nextBtn.click();
					status="yes";
					return status;
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
					System.out.println("Element Not enabled");
				}
				return false;
			}
			else
			{
				System.out.println("Element Not Displayed");
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
