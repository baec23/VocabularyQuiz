package com.baec.vocabularyquiz.model;

public class QuizWord {
   private String word;
   private String answer;

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
}
