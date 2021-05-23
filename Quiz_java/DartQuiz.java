package Quiz;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DartQuiz extends Topic implements Result{
	
	Scanner sc=new Scanner(System.in);
	
	DartQuiz(){
		
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
			new DartQuiz();
		}
		
		else if(optionSelect==2) {
			new JavaQuiz();
		}
		sc.close();
	}

	@Override
	void setTopicName() {
		System.out.println("==========Dart Quiz==========");
		System.out.println("---------All the Best--------");
		System.out.println("**Multiple answers are possible");
	}
	
	String[] promptQuestion(String question , String[] option , String[] answer) {
		
		String answerString[]=new String[answer.length];
		List<String> shuffler=Arrays.asList(option);
		
		System.out.println(question);
		
		Collections.shuffle(shuffler);
		shuffler.toArray(option);
		
		for(int i=0 ; i< shuffler.size() ; i++) {
			
			System.out.println((i+1)+")"+option[i]);
		}
		
		String input=sc.next();
		char charArray[]=new char[input.length()];
		
		for(int i=0 ; i<input.length() ; i++) {
			charArray[i]=input.charAt(i);
			
		}
		
		for(int j=0; j<option.length ;j++){
			
		      for(int k=0 ; k<charArray.length; k++) {
		    	 
		    	int check=Character.getNumericValue(charArray[k]); 
		    	
		        if(check==(j+1)) {
		          answerString[k]=option[j];
		         
		        }
		       
		        continue;
		    }
		}
		
		return answerString;
	}

	///problem 2-----
	@Override
	int displayQuestions(){
		
		List<QuestionModel> getQuestion=getQuestions();
		int score=0;
		
		for(QuestionModel i : getQuestion) {
			
			int counter=0;
			int revCounter=0;
			
			String[] inputAnswer=promptQuestion(i.question , i.option,i.ans);
			
			for(int j=0 ; j<inputAnswer.length ; j++){
				
	            if(inputAnswer.length==0 && inputAnswer.length==4)
	              break;
	           
	            for(int k=0 ; k<inputAnswer.length ; k++){
	            	
	            	try {
	            		if(inputAnswer[k].equals(i.ans[j])) {
	            			counter++;
	            		}
	            	}catch (Exception e) {
	            		revCounter++;
	            	}
	             }
	          }
            
			if(counter==(i.ans.length-(revCounter/4))) {
				score++;
			}
		}
		
		return score;
	}
	
	List<QuestionModel> getQuestions() {
		
		JSONArray javaArray=JSONConverted();
		
		Object obj=javaArray.get(1);
				
			JSONObject data=(JSONObject)obj;
			
			///Question Array
			JSONArray dartQuestion=(JSONArray) data.get("questions");
			String questionArray[] =new String[dartQuestion.size()];
			
			for(int i=0 ; i<dartQuestion.size() ; i++) {
				String str=(String) dartQuestion.get(i);
				questionArray[i]=str;
			}
			
			///Option Array
			JSONArray dartOption=(JSONArray) data.get("options");
			JSONArray innerArray;
			
			String[][] optionArray=new String[questionArray.length][4];
			
			for(int i=0 ; i<dartOption.size() ; i++) {
				
				innerArray=(JSONArray) dartOption.get(i);
				
				for(int j=0 ; j<innerArray.size() ; j++) {
					
					String str=(String)innerArray.get(j);
					optionArray[i][j]=str;
				}
			}
			
			///Answer  Array
			JSONArray dartAnswer=(JSONArray) data.get("answers");
			JSONArray innerAnswerArray;
			
			String answerArray[][]=new String[questionArray.length][4];
			
			for(int i=0 ; i<answerArray.length ; i++) {
				
				innerAnswerArray=(JSONArray)dartAnswer.get(i);
		
				for(int j=0 ; j<innerAnswerArray.size() ; j++) {
					
				String str=(String) innerAnswerArray.get(j);
				answerArray[i][j]=str;
			
			}
		}
			
			List<QuestionModel> questionSet=new ArrayList<QuestionModel>();
			
			for(int i=0 ; i<questionArray.length ;i++) {
				
				questionSet.add(new QuestionModel(questionArray[i] ,optionArray[i] ,answerArray[i] ));
				
			}
			
		Collections.shuffle(questionSet);
		return questionSet;	
	}
	
	public static void main(String args[]) {
		
		new DartQuiz();
	}

}
