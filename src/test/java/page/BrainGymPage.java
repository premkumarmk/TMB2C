package page;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrainGymPage {

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
	
	@FindBy(xpath="//div[@class='braingym-starttoday']/descendant::button[descendant::span[contains(text(),'Workout completed')]]")
	public WebElement workoutCompletedBtn;
	
	//shell, Start now <p>
	@FindBy(xpath="//div[@class='Oyster-and-title']/p[text()='Start now']")
	public WebElement startNowShell;
	
	@FindBy(xpath="//div[@class='flex-items-details']/descendant::button[text()='Start Now']")
	public WebElement startNowOnPopupBtn;
	
	@FindBy(xpath="//div[@class='question-box']/descendant::p/span")
	public WebElement getQuestion;
	//div[@class='question-box']/descendant::span/span => English
	//div[@class='question-box']/descendant::p/span   =>Science and Maths
	
	@FindBy(xpath="//div[@class='question-options-wrapper selectTyleType']/div[2]/button")
	public WebElement answerOption;
	
	@FindBy(xpath="//button[@class='submit-answer-button active']")
	public WebElement submitAnswerBtn;
	
	@FindBy(xpath="//span[text()='Select your answer']")
	//@FindBy(xpath="//div[@class='flex-items']/descendant::p[text()='Right answers']")
	public WebElement verificationText;
	
	@FindBy(xpath="//button[text()='Next Question']")
	public WebElement nextQuestionBtn;
	
	@FindBy(xpath="//button[text()='Next']")
	public WebElement nextBtn;
	
	@FindBy(xpath="//button[@class=\"beltpopup-button\" and text()='Next']")
	public WebElement nextBtnForNextShell;
	
	@FindBy(xpath="//div[@class='flex-items']/descendant::p[text()='Right answers']")
	public WebElement oneSetShellCompletion;
	
	@FindBy(xpath="//button[text()='Finish']")
	public WebElement finishOfperDayShellBtn;
	
//	@FindBy(xpath="//div[@class='flex-items']/button[text()='Next']")
//	public WebElement NextBtnAfterEachShell;
//	
	//static Map<String, Integer> QuestionsMap;
	
	static Map<String, Integer> QuestionsMap = new HashMap<String, Integer>();
	 
	public void selectSubject(String sub) {
		for (WebElement subject : subjectsTabs) {
			System.out.println(subject);
			if(subject.getText().equals(sub)) {
				subject.click();
			}
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
	
//	workoutCompletedBtn
//	public void verifyWorkoutCompleted() throws InterruptedException
//	{
//		wait(0);
//
//		//Thread.sleep(10000);
//	}

	
	public void clickWorkoutBtn() throws InterruptedException
	{
		workoutBtn.click();
		//Thread.sleep(10000);
	}
	
	public void clickStartNowShell() throws InterruptedException
	{
		startNowShell.click();
		//Thread.sleep(20000);
	}
	public void clickStartNowOnPopupBtn() throws InterruptedException
	{
		startNowOnPopupBtn.click();
		//Thread.sleep(20000);
	}
	public String getQuestionText() throws InterruptedException
	{
		System.out.println("INside getQuestionText() method");
		String question=getQuestion.getText();
	//	System.out.println("Question is: "+question);
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
		answerOption.click();
	}
	
	public void clickSubmitAnswerBtn()
	{
		System.out.println("Inside click SubmitAnswerBtn()");
		submitAnswerBtn.click();
	}
	

	public void clickNextQuestionBtn()
	{
		nextQuestionBtn.click();
	}
	
	public String verifyShellCompletedSingle() throws NoSuchElementException
	{
		System.out.println("Inside verifyShellCompleted()");
		String status="yes";
		try 
		{
			System.out.println("inside verifyShellCompleted try block");
			if(verificationText.isDisplayed())
			{
				System.out.println("inside verifyShellCompleted try block IF Stmt");
				status="no";
				return status;
			}
		}
		catch(Exception e)
		{
			
		}
		return status;
		
	}
	public String verifyShellCompleted() throws NoSuchElementException
	{
		System.out.println("Inside verifyShellCompleted()");
		String status="yes";
		try 
		{
			System.out.println("inside verifyShellCompleted try block");
			if(verificationText.isDisplayed())
			{
				System.out.println("inside verifyShellCompleted try block IF Stmt");
				status="no"; //shell not completed
				return status;
			}
			else if(nextBtn.isDisplayed())
			{
				System.out.println("inside verifyShellCompleted try block ELSE IF Stmt");
				status="yes"; //shell completed
				return status;
			}
			else
			{
				System.out.println("inside verifyShellCompleted try block ELSE Stmt");
			}
		}
		catch(Exception e)
		{
			
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
				System.out.println("inside try block IF Stmt");
				status="yes";
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
			if(finishOfperDayShellBtn.isDisplayed())
			{
				System.out.println("inside try block IF Stmt");
				status="yes";
				return status;
			}
		}
		catch(Exception e)
		{
			
		}
		return status;
	}

	public void clickFinishPerDayShells()
	{
		finishOfperDayShellBtn.click();
	}

	public void clickNextBtn() {
		nextBtn.click();
		
	}
	
	public void nextBtnForNextShell()
	{
		nextBtnForNextShell.click();
	}
	
//	public String verifyShellCompleted()
//	{
//		try
//		{
//			if(textWhoopie.isDisplayed())
//			{
//				if(nextBtn.isDisplayed())
//				{
//					System.out.println("All questions over!!!");
//					nextBtn.click();
//					if(NextBtnForNextShell.isDisplayed())
//					{
//						NextBtnForNextShell.click();
//						
//					}
//				}
//				
//				
//			}
//			else
//			{
//				System.out.println("Shell not completed!!");
//				System.out.println("Going to next question");
//				
//			}
//			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return textWhoopie.getText();
//	}	
	
//	public String clickNextBtn()
//	{
//		System.out.println("Inside click NextQuestionBtn()");
//		String text="";
//		if(nextQuestionBtn.isDisplayed())
//		{
//			System.out.println("Inside IF Block click NextQuestionBtn()");
//			nextQuestionBtn.click();
//			text= "Clicked Succesfully";
//		}
//		else
//		{
//			System.out.println("Inside Else Block click NextQuestionBtn()");
//			System.out.println("Next Question button not shown");
//			text="Questions Over, Not able to click";
//		}
//		return text;
//		
//	}
	
}
