package question;

import java.util.LinkedList;
import java.util.List;

import anwser.Answer; /** import relation -> Answer.java */
import anwser.MapAnswer; /** import relation -> MapAnswer.java */

public class MapQuestion extends Question { /** extend relation -> Question.java */
	
	MapAnswer answer; /** contain relation -> MapAnswer.java */
	List<String> side1 = new LinkedList<String>();
	List<String> side2 = new LinkedList<String>();
	int side;
	
	public MapQuestion(){
		super(5); /** call relation -> Question.java */
	}
	
	public void setItem(String item) {
		// TODO Auto-generated method stub
		if(side == 1){
			side1.add(item);
		}else{
			side2.add(item);
		}
	}

	public void setAnswer(String answer) {
		// TODO Auto-generated method stub
		this.answer = new MapAnswer(); /** create relation -> MapAnswer.java */
		this.answer.setQuestion(side1, side2); /** call relation -> MapAnswer.java */
		this.answer.setAnswer(answer); /** call relation -> MapAnswer.java */
	}

	public Answer getAnswer() { /** return relation -> Answer.java */
		// TODO Auto-generated method stub
		return answer; /** use relation -> MapAnswer.java */
	}

	public boolean match(String answer) {
		// TODO Auto-generated method stub
		return this.answer.getAnswer().equals(answer); /** call relation -> MapAnswer.java */
	}
	
	public void setSide(int side){
		this.side = side;
	}
	
	public String getQuestion(){
		String ret = "Map: "+prompt+"\n"; /** use relation -> Question.java */
		for(int i=0; i<side1.size(); i++){
			ret += side1.get(i)+"\t"+side2.get(i)+"\n";
		}
		return ret;
	}

	public List<String> getItem() {
		// TODO Auto-generated method stub
		if(side == 1){
			return side1;
		}
		return side2;
	}

	public boolean remove(int index) {
		if(side == 1){
			if(side1.size() > index){
				side1.remove(index);
				return true;
			}
		}else{
			if(side2.size() > index){
				side2.remove(index);
				return true;
			}
		}
		return false;
	}

	public boolean changeItem(int index, String item) {
		if(side == 1){
			if(side1.size() > index){
				side1.remove(index);
				side1.add(index, item);
				return true;
			}
		}else{
			if(side2.size() > index){
				side2.remove(index);
				side2.add(index, item);
				return true;
			}
		}
		return false;
	}

	public boolean changeItemNumber(int num) {
		if(side == 1){
			if(side1.size() > num){
				for(int i = num; i<side1.size(); i++){
					side1.remove(i);
				}
				return true;
			}
		}else{
			if(side2.size() > num){
				for(int i = num; i<side2.size(); i++){
					side2.remove(i);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean match(Answer answer) { /** parameter relation -> Answer.java */
		return this.answer.match(answer); /** call relation -> MapAnswer.java */
	}

	public int getSide() {
		return side;
	}
}
