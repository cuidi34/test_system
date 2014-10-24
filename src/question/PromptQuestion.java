package question;

import anwser.Answer;

public abstract class PromptQuestion extends Question{
	
	public PromptQuestion(int type) {
		super(type);
	}

	public abstract Answer getAnswer();
	public abstract void setAnswer(String answer);
}
