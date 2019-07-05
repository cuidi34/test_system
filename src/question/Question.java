package question;

import anwser.Answer; /** import relation -> Answer.java */


public abstract class Question {
	String prompt;
	int score;
	int type;
	
	public Question(int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
	
	public String getQuestion(){
		return null;
	}
	
	public String getPrompt(){
		return prompt;
	}
	
	public void setPrompt(String prompt){
		this.prompt = prompt;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public abstract void setAnswer(String answer);
	
	public abstract Answer getAnswer(); /** return relation -> Answer.java */
	public abstract boolean match(Answer answer); /** parameter relation -> Answer.java */
}
