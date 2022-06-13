package com.baec.vocabularyquiz.model;

public class QuizWord {
   private String word;
   private String answer;

   public QuizWord(){

   }

   public QuizWord(String word, String answer) {
      this.word = word;
      this.answer = answer;
   }

   public String getWord() {
      return word;
   }

   public String getAnswer() {
      return answer;
   }

   public void setWord(String word) {
      this.word = word;
   }

   public void setAnswer(String answer) {
      this.answer = answer;
   }
}
