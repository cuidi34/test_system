package question;

import java.util.LinkedList;
import java.util.List;

import anwser.Answer; /** import relation -> Answer.java */
import anwser.ChoiceAnswer; /** import relation -> ChoiceAnswer.java */


public class ChoiceQuestion extends ItemQuestion { /** extend relation -> Answer.java */
	List<String> items = new LinkedList<String>(); 
	ChoiceAnswer answer; /** contain relation -> ChoiceAnswer.java */
	
	public ChoiceQuestion(){
		super(1); /** call relation -> Question.java */
	}
	
	public void setItem(String item) {
		this.items.add(item);
	}

	public void setAnswer(String answer) {
		this.answer = new ChoiceAnswer(); /** create relation -> ChoiceAnswer.java */
		this.answer.setItem(items); /** call relation -> ChoiceAnswer.java */
		this.answer.setAnswer(answer); /** call relation -> ChoiceAnswer.java */
	}

	public Answer getAnswer() { /** return relation -> Answer.java */
		return answer; /** use relation -> ChoiceAnswer.java */
	}

	public boolean match(String answer) {
		// TODO Auto-generated method stub
		return this.answer.equals(answer); /** call relation -> ChoiceAnswer.java */
	}
	
	public String getQuestion(){
		String ret = "ChoiceQuestion: "+prompt+"\n"; /** use relation -> Question.java */
		for(int i=0; i<items.size(); i++){
			ret += items.get(i)+"\n";
		}
		return ret;
	}

	public List<String> getItem() {
		return items;
	}

	public boolean remove(int index) {
		if(items.size() > index){
			items.remove(index);
			return true;
		}
		return false;
	}

	public boolean changeItem(int index, String item) {
		if(items.size() > index){
			items.remove(index);
			items.add(index, item);
			return true;
		}
		return false;
	}

	public boolean changeItemNumber(int num) {
		if(items.size() > num){
			for(int i=num; i<items.size(); i++){
				items.remove(i);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean match(Answer answer) { /** parameter relation -> Answer.java */
		return this.answer.match(answer); /** call relation -> ChoiceAnswer.java */
	}

}
