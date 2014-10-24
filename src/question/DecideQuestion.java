package question;

import anwser.Answer;
import anwser.DecideAnswer;

public class DecideQuestion extends PromptQuestion {
	DecideAnswer answer;
	
	public DecideQuestion(){
		super(0);
	}

	
	public String getQuestion(){
		String ret = "T/F: "+prompt+"\n"+"1. right\n2. false";
		return ret;
	}
	
	public void setAnswer(String anwser){
		answer = new DecideAnswer();
		answer.setAnswer(anwser);
	}
	
	public Answer getAnswer(){
		return answer;
	}

	public boolean match(String answer) {
		// TODO Auto-generated method stub
		return this.answer.getAnswer().equals(answer);
	}


	@Override
	public boolean match(Answer answer) {
		return this.answer.match(answer);
	}
}
