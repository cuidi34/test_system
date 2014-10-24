package question;

import java.util.LinkedList;
import java.util.List;

import anwser.Answer;
import anwser.RankAnswer;

public class RankQuestion extends ItemQuestion {
	
	RankAnswer answer;
	List<String> items = new LinkedList<String>();
	
	public RankQuestion(){
		super(4);
	}
	
	public void setItem(String item) {
		items.add(item);		
	}

	public void setAnswer(String answer) {
		this.answer = new RankAnswer();
		this.answer.setQuestion(items);
		this.answer.setAnswer(answer);
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public boolean match(String answer) {
		// TODO Auto-generated method stub
		return this.answer.getAnswer().equals(answer);
	}
	
	public String getQuestion(){
		String ret = "Rank: "+prompt + "\n";
		for(int i=0; i<items.size(); i++){
			ret += items.get(i)+"\n";
		}
		return ret;
	}

	public List<String> getItem() {
		// TODO Auto-generated method stub
		return items;
	}

	public boolean remove(int index) {
		// TODO Auto-generated method stub
		if(items.size() > index){
			items.remove(index);
			return true;
		}
		return false;
	}

	public boolean changeItem(int index, String item) {
		// TODO Auto-generated method stub
		if(items.size() > index){
			items.remove(index);
			items.add(index, item);
			return true;
		}
		return false;
	}

	public boolean changeItemNumber(int num) {
		if(items.size() > num){
			for(int i=num; i < items.size(); i++){
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
