package question;

import java.util.LinkedList;
import java.util.List;

import anwser.Answer;
import anwser.ChoiceAnswer;


public class ChoiceQuestion extends ItemQuestion {
	List<String> items = new LinkedList<String>();
	ChoiceAnswer answer;
	
	public ChoiceQuestion(){
		super(1);
	}
	
	public void setItem(String item) {
		this.items.add(item);
	}

	public void setAnswer(String answer) {
		this.answer = new ChoiceAnswer();
		this.answer.setItem(items);
		this.answer.setAnswer(answer);
	}

	public Answer getAnswer() {
		return answer;
	}

	public boolean match(String answer) {
		// TODO Auto-generated method stub
		return this.answer.equals(answer);
	}
	
	public String getQuestion(){
		String ret = "ChoiceQuestion: "+prompt+"\n";
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
	public boolean match(Answer answer) {
		return this.answer.match(answer);
	}

}
