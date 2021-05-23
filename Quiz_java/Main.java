package Quiz;
import java.util.Scanner;

public class Main {
	public static void main(String srgs[]) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("----------Quiz----------");
		System.out.println("* At the end of quiz you can either repeat the same topic or go for the next topic");
		System.out.println("* You will get the total score at the end of the quiz \n");
		System.out.println("Select Topic :");
		System.out.println("1) Java ");
		System.out.println("2) Dart ");
		
		int topicNo=sc.nextInt();
		
		if(topicNo==1) 
			new JavaQuiz();
		
		else if(topicNo==2) 
			new DartQuiz();
			
		sc.close();
	}
}
