package paper;

import question.Question; /** import relation -> Question.java */

public class Test extends Page { /** extend relation -> Page.java */
	int totalScore;
	
	public void setTotalScore(int score){
		totalScore = score;
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public void computeScore(){
		totalScore = 0;
		for(int i=0; i<questionList.size(); i++){ /** use relation -> Page.java */
			Question question = questionList.get(i); /** contain relation -> Question.java */ /** use relation -> Page.java */
			totalScore += question.getScore(); /** call relation -> Question.java */
		}
	}
}
