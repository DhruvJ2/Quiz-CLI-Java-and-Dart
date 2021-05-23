// Question Model
class Question{

  late String question ;
  late List<dynamic> option;
  late String answer;
  late List<dynamic> ans;

  Question(String question , List<dynamic> option , String answer){

    this.question =question;
    this.option=option;
    this.answer=answer;
  }
  Question.overloaded(String question ,List<dynamic> option , List<dynamic> ans){

    this.question =question;
    this.option=option;
    this.ans=ans;
  }
}
