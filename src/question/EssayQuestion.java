package question;

import anwser.Answer; /** import relation -> Answer.java */

public class EssayQuestion extends Question { /** extend relation -> Question.java */
	public EssayQuestion(){
		super(3); /** call relation -> Question.java */
	}
	
	public String getQuestion(){
		return "Essay: "+prompt; /** use relation -> Question.java */
	}

	@Override
	public void setAnswer(String answer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Answer getAnswer() { /** return relation -> Answer.java */
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean match(Answer answer) { /** parameter relation -> Answer.java */
		// TODO Auto-generated method stub
		return false;
	}
}
