// entry point 
// contains all the topic for the quiz
import 'dart:io';
import 'Topic.dart';

void main(List<String> args) {
  print("""----------QUIZ----------
*You will get 10 seconds to answer for each question
*At the end of quiz you can either repeat the same topic or go for the next topic
*You will get the total score at the end of the quiz \n
Select Topic : 
1) Java 
2) Dart """);

  var topicNo=stdin.readLineSync();

  if(topicNo == '1')
    Java();
  else if (topicNo == '2')
    Dart();
}