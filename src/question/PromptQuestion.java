package question;

import anwser.Answer; /** import relation -> Answer.java */

public abstract class PromptQuestion extends Question{ /** extend relation -> Question.java */
	
	public PromptQuestion(int type) {
		super(type); /** import relation -> Question.java */
	}

	public abstract Answer getAnswer(); /** return relation -> Answer.java */
	public abstract void setAnswer(String answer); 
}
