package paper;

import java.util.LinkedList;
import java.util.List;

import question.Question;

public class Page {
	
	String pageName;
	String type;
	
	List<Question> questionList = new LinkedList<Question>();
	
	public void setPageName(String pageName){
		this.pageName = pageName;
	}
	
	public String getPageName(){
		return this.pageName;
	}
	
	public void addQuestion(Question question){
		questionList.add(question);
	}
	
	public Question getQuestion(int index){
		if(index >= questionList.size()){
			return null;
		}else{
			return questionList.get(index);
		}
	}
	
	public List<Question> getQuestionList(){
		return questionList;
	}
	
	public int getQuestionSize(){
		return questionList.size();
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public Iterator<Question> iterator(){
		return new IteratorQuestion();
	}
	
	class IteratorQuestion implements Iterator<Question>{
		int questionIndex;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(questionList.size() > questionIndex)
				return true;
			return false;
		}

		@Override
		public Question next() {
			// TODO Auto-generated method stub
			return questionList.get(questionIndex++);
		}
		
		
	}
	
}
