
import 'dart:convert';
import 'dart:io';
import 'QuestionModel.dart';
// import 'package:console/console.dart';

///json array to string .....Done 
 var jsonString=File("bin/Quiz/Questions.json").readAsStringSync();
 var DecodedArray=jsonDecode(jsonString);

  // int count=10;
  // Timer? timer;

//implicit interface Result
abstract class Result{
  void getMarks();
}

///Abstract class
abstract class Topic{
  
  void setTopicName();
  int displayQuestions();
  List<dynamic> getQuestions();

}

class Java extends Topic implements Result{

///Where the quiz will start
  Java(){
    setTopicName();
    getMarks();
  }

///calculate the score and display marks out of total questions
   @override
  void getMarks() {
    ///calls the displayQuestion method and stores the returntype
    int marks=displayQuestions();
    int totalQuestions=getQuestions().length;
    print('-----You got ${marks}/${totalQuestions}-----');
     
    print('''Do you want to :
1)Repeat Same Topic
2)Jump to the Next Topic
3)Exit the Quiz''');

/// repeat topic or go to next one
    String topicSelect=stdin.readLineSync()!;
    if(topicSelect=='1')
      Java();
    else if(topicSelect=='2')
      Dart();
  }

///Quiz intro
   @override
  void setTopicName() {
    print('==========Java Quiz=========');
    print('--------All the Best!!------');
    print('');
  }

///will prompt questions and options and take input
  String promptQuestion(String question , List<dynamic> option){
    
    ///Will contain answer
    String? answerString;
    // count=10;

    print(question);
    option.shuffle();
        
        for(int i=0 ; i<option.length ; i++){
          print('${i+1}) ${option[i]}');
        }

///Timer for each question 
    // timer=Timer.periodic(Duration(seconds:1), (timer) {
    //   if(count>0){
    //     print(count--);
    //   }
    //   else{
    //     timer.cancel();
    //     answerString='Null';
    //   }
    // });

    // if(count<0)
    //   return answerString!;
    
    String input=stdin.readLineSync()!;

    for(int j=0; j<=option.length ;j++){
      if(input=='${j+1}')
        answerString=option[j];
    }
    return answerString!;
  }

  @override
  int displayQuestions() {
    List getquestion=getQuestions();
    int score=0;

    for(Question i in getquestion){
      ///stores the answer and adds a point if the answer is correct
      String inputAnswer=promptQuestion(i.question ,i.option);
      if(inputAnswer==i.answer)
          score++;
    }
    return score;
  }

  List<dynamic> getQuestions() {
  
    Map<String , dynamic>data=Map<String , dynamic>();

    ///storing data from json file in a map 
    data['question']=DecodedArray[0]['questions'];
    data['option']=DecodedArray[0]['options'];
    data['answer']=DecodedArray[0]['answers'];

    ///Stores data from json file to a List
    List<Question> questionList=[
                                    for(int i=0 ; i<data['question'].length ;i++)
                                    Question(data['question'][i] , data['option'][i] , data['answer'][i])
                                ]; 
      questionList.shuffle();

      return questionList;
  }

}

class Dart extends Topic implements Result {

  ///Where the quiz will start
  Dart(){
    setTopicName();
    getMarks();
  }

  ///calculate the score and display marks out of total questions
  @override
  void getMarks()  {
    int marks=displayQuestions();
    int totalQuestions=getQuestions().length;
    print('-----You got ${marks}/${totalQuestions}-----');

    print('''Do you want to :
1)Repeat Same Topic
2)Jump to the Next Topic
3)Exit the Quiz''');

    /// repeat topic or go to next one
    /// Quiz end-point
    String topicSelect=stdin.readLineSync()!;
    if(topicSelect=='1')
      Dart();
    else if(topicSelect=='2')
      Java();
  }

  ///Quiz intro
   @override
  void setTopicName() {
    print('''==========Dart Quiz==========
---------All the Best--------
**Multiple answers are possible \n''');
  }

  ///will prompt questions and options and take input
  List<dynamic> promptQuestion(String question , List<dynamic> option){
    
    ///Will contain answer
    List answerString=[];
    ///contains List of inputoptions
    List charArray=[];

    print(question);
    option.shuffle();
        
        for(int i=0 ; i<option.length ; i++){
          print('${i+1}) ${option[i]}');
        }
    
    String input=stdin.readLineSync()!;
    //splitting the String into character array
    input.split('').forEach((ch)=>charArray.add(ch));

    for(int j=0; j<option.length ;j++){

      for(int k=0 ; k<charArray.length; k++)
        if(charArray[k]=='${j+1}')
          answerString.add(option[j]);
        continue;
    }
    return answerString;
  }

  @override
  int displayQuestions() {

    List getquestion=getQuestions();
    int score=0;

    for(Question i in getquestion){
      
      ///stores the answer and adds a point if the answer is correct
      ///counter for number of input answers if not equal then answer is wrong
      int counter=0;

      List<dynamic> inputAnswer=promptQuestion(i.question ,i.option);
      
        for(int j=0 ; j<i.ans.length ; j++){
            if(inputAnswer.length==0)
              break;
            for(int k=0 ; k<inputAnswer.length ; k++){
                if(inputAnswer[k]==i.ans[j]){
                  counter++;
                }
            }
          }
      if(counter==i.ans.length)
          score++;
    }
    return score;
  }

  List<dynamic> getQuestions() {
  
    Map<String , dynamic> data=Map<String , dynamic>();

    ///storing data from json file in a map 
    data['question']=DecodedArray[1]['questions'];
    data['option']=DecodedArray[1]['options'];
    data['answer']=DecodedArray[1]['answers'];

    ///Stores data from json file to a List
    List<Question> questionList=[
                                    for(int i=0 ; i<data['question'].length ;i++)
                                    Question.overloaded( data['question'][i] ,data['option'][i] , data['answer'][i])
                                ];
      questionList.shuffle();

      return questionList;
  }

}
