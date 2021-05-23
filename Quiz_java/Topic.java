package Quiz;
import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public abstract class Topic {
	
	abstract void setTopicName();
	
	abstract int displayQuestions();
		
	public JSONArray JSONConverted() {
		
		 JSONParser parser=new JSONParser();
		 JSONArray array=new JSONArray();
			
			try {
				array=(JSONArray)parser.parse(new FileReader("D:\\Dart_Programs\\bin\\Quiz\\Questions.json"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return array;
		}
	
}
