package question;

import java.util.List;

import anwser.Answer; /** import relation -> Answer.java */

public abstract class ItemQuestion extends Question{ /** extend relation -> Question.java */

	public ItemQuestion(int type) {
		super(type); /** call relation -> Question.java */
		// TODO Auto-generated constructor stub
	}
	
	public abstract void setItem(String item);
	public abstract List<String> getItem();
	public abstract boolean remove(int index);
	public abstract boolean changeItem(int index, String item);
	public abstract boolean changeItemNumber(int num);
}
