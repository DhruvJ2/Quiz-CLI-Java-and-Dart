package Quiz;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JavaQuiz extends Topic implements Result {
	
	Scanner sc=new Scanner(System.in);

	JavaQuiz(){
		
		setTopicName();
		getMarks();
	}
	
	@Override
	public void getMarks() {
		
		int marks=displayQuestions();
		int totalQuestions=getQuestions().size();
		
		System.out.println("----You got "+marks+"/"+totalQuestions+"-----");
		System.out.println("Do you want to :");
		System.out.println("1) Repeat the same Topic");
		System.out.println("2) Jump to the next Topic");
		System.out.println("3) Exit the Quiz");
		
		/// Take input
		int optionSelect=sc.nextInt();
		if(optionSelect==1) {
			new JavaQuiz();
		}
		else if(optionSelect==2) {
			new DartQuiz();
		}
		sc.close();
		
	}

	@Override
	void setTopicName() {
		System.out.println("==========Java Quiz=========");
	    System.out.println("--------All the Best!!------");
	}
	
	String promptQuestion(String question , String[] option) {
		
		String answerString = null;
		List<String> shuffler=Arrays.asList(option);
		
		System.out.println(question);
		Collections.shuffle(shuffler);
		shuffler.toArray(option);
		
		for(int i=0 ; i< shuffler.size() ; i++) {
			
			System.out.println((i+1)+")"+option[i]);
		}
		
		String input=sc.next();
		int check=Integer.parseInt(input);
		
		for(int i=0 ; i<option.length ; i++) {
			
			if(check==(i+1)) {
				answerString=option[i];
			}
		}
		
		return answerString;
	}

	@Override
	int displayQuestions() {
		
		List<QuestionModel> getQuestion=getQuestions();
		int score=0;
		
		for(QuestionModel i : getQuestion) {
			
			String inputAnswer=promptQuestion(i.question , i.option);
			
			if(inputAnswer.equals(i.answer)) {
				score++;
			}
		}
		
		return score;
	}
	

	List<QuestionModel> getQuestions() {
		
		JSONArray javaArray=JSONConverted();
		
		Object obj=javaArray.get(0);
				
			JSONObject data=(JSONObject)obj;
			
			///Question Array
			JSONArray javaQuestion=(JSONArray) data.get("questions");
			String questionArray[] =new String[javaQuestion.size()];
			
			for(int i=0 ; i<javaQuestion.size() ; i++) {
				String str=(String) javaQuestion.get(i);
				questionArray[i]=str;
			}
			
			///Option Array
			JSONArray javaOption=(JSONArray) data.get("options");
			JSONArray innerArray;
			String[][] optionArray=new String[questionArray.length][4];
			
			for(int i=0 ; i<javaOption.size() ; i++) {
				
				innerArray=(JSONArray) javaOption.get(i);
				
				for(int j=0 ; j<innerArray.size() ; j++) {
					
					String str=(String)innerArray.get(j);
					optionArray[i][j]=str;
				}
			}
			
			///Answer  Array
			JSONArray javaAnswer=(JSONArray) data.get("answers");
			String answerArray[] =new String[javaQuestion.size()];
			
			for(int i=0 ; i<javaAnswer.size() ; i++) {
				
				String str=(String) javaAnswer.get(i);
				answerArray[i]=str;
			}
			
	///Might have Problem HERE!!!!		
			List<QuestionModel> questionSet=new ArrayList<QuestionModel>();
			
			for(int i=0 ; i<questionArray.length ; i++) {
				questionSet.add(new QuestionModel(questionArray[i] ,optionArray[i] ,answerArray[i] ));
				
		}
		Collections.shuffle(questionSet);
		return questionSet;	
	}

	public static void main(String []args) {
		
		new JavaQuiz();
	}
}
