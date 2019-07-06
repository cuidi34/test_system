package question;

import anwser.Answer; /** import relation -> Answer.java */
import anwser.DecideAnswer; /** import relation -> DecideAnswer.java */

public class DecideQuestion extends PromptQuestion { /** extend relation -> PromptQuestion.java */
	DecideAnswer answer; /** contain relation -> DecideAnswer.java */
	
	public DecideQuestion(){
		super(0); /** call relation -> PromptQuestion.java */
	}

	
	public String getQuestion(){
		String ret = "T/F: "+prompt+"\n"+"1. right\n2. false"; /** use relation -> Question.java */
		return ret;
	}
	
	public void setAnswer(String anwser){
		answer = new DecideAnswer(); /** create relation -> DecideAnswer.java */
		answer.setAnswer(anwser); /** call relation -> DecideAnswer.java */
	}
	
	public Answer getAnswer(){ /** return relation -> Answer.java */
		return answer; /** use relation -> DecideAnswer.java */
	}

	public boolean match(String answer) {
		// TODO Auto-generated method stub
		return this.answer.getAnswer().equals(answer); /**call relation -> DecideAnswer.java */
	}


	@Override
	public boolean match(Answer answer) { /** parameter relation -> Answer.java */
		return this.answer.match(answer); /** call relation -> DecideAnswer.java */
	}
}
