package question;

import anwser.Answer; /** import relation -> Answer.java */
import anwser.TextAnswer; /** import relation -> TextAnswer.java */

public class ShortEssayQuestion extends PromptQuestion { /** extend relation -> PromptQuestion.java */
	
	TextAnswer answer; /** contain relation -> TextAnswer.java */
	
	public ShortEssayQuestion() {
		super(2); /** call relation -> PromptQuestion.java */
		// TODO Auto-generated constructor stub
	}	
	
	public String getQuestion(){
		return "Text: "+prompt; /** use relation -> Question.java */
	}
	
	public void setAnswer(String answer) {
		// TODO Auto-generated method stub
		
		this.answer = new TextAnswer(); /** Create relation -> TextAnswer.java */
		this.answer.setAnswer(answer); /** Call relation -> TextAnswer.java */
	}

	public Answer getAnswer() { /** return relation -> Answer.java */
		// TODO Auto-generated method stub
		return answer; /** Use relation -> TextAnswer.java */
	}


	@Override
	public boolean match(Answer answer) { /** parameter relation -> Answer.java */
		return this.answer.match(answer); /** Call relation -> TextAnswer.java */
	}
}
