package question;

import anwser.Answer;
import anwser.TextAnswer;

public class ShortEssayQuestion extends PromptQuestion {
	
	TextAnswer answer;
	
	public ShortEssayQuestion() {
		super(2);
		// TODO Auto-generated constructor stub
	}	
	
	public String getQuestion(){
		return "Text: "+prompt;
	}
	
	public void setAnswer(String answer) {
		// TODO Auto-generated method stub
		
		this.answer = new TextAnswer();
		this.answer.setAnswer(answer);
	}

	public Answer getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}


	@Override
	public boolean match(Answer answer) {
		return this.answer.match(answer);
	}
}
