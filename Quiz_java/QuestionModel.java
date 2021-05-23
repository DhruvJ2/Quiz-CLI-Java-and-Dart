package Quiz;

public class QuestionModel {

   String question ;
   String[] option;
   String answer;
   String[] ans;

  QuestionModel(String question , String[] option , String answer){

    this.question =question;
    this.option=option;
    this.answer=answer;
  }
  
  QuestionModel(String question ,String[] option , String[] ans){

    this.question =question;
    this.option=option;
    this.ans=ans;
  }

}