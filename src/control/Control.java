package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import paper.Iterator;
import paper.Page;
import paper.Record;
import paper.Survey;
import paper.Test;
import question.ChoiceQuestion; /** import relation -> ChoiceQuestion.java */
import question.DecideQuestion; /** import relation -> DecideQuestion.java */
import question.EssayQuestion;  /** import relation -> EssayQuestion.java */
import question.ItemQuestion;   /** import relation -> ItemQuestion.java */
import question.MapQuestion;    /** import relation -> MapQuestion.java */
import question.Question;       /** import relation -> Question.java */
import question.RankQuestion;   /** import relation -> RankQuestion.java */
import question.ShortEssayQuestion; /** import relation -> ShortEssayQuestion.java */
import anwser.Answer;               /** import relation -> Answer.java */   
import anwser.ChoiceAnswer;         /** import relation -> ChoiceAnswer.java */
import anwser.DecideAnswer;         /** import relation -> DecideAnswer.java */
import anwser.MapAnswer;            /** import relation -> MapAnswer.java */
import anwser.RankAnswer;           /** import relation -> RankAnswer.java */
import anwser.TextAnswer;           /** import relation -> TextAnswer.java */

public class Control {
	
	
	List<String>[] pageNameList;
	Page page;  /** contain relation -> Page.java */
	Question question; /** contain relation -> Question.java */
	int index;
	Record record; /** contain relation -> Record.java */
	IO io = new IO(); /** contain relation -> IO.java */ /** create relation -> IO.java */
	List<String> recordName;
	Iterator<Question> iterator; /** use relation -> Question.java */
	
	public Control(){
		pageNameList = io.readAllPages(); /** call relation -> IO.java */
	}
	
	public void createPage(int type){
		if(type == 0){
			page = new Survey(); /** create relation -> Survey.java */
			page.setType("survey"); /** call relation -> Page.java */
		}else{
			page = new Test(); /** create relation -> Test.java */
			page.setType("test"); /** call relation -> Page.java */
		}
	}
		
	public void setPageName(String name){
		page.setPageName(name); /** call relation -> Page.java */
		if(page.getType().equals("test")){ /** call relation -> Page.java */
			pageNameList[1].add(name);
		}else{
			pageNameList[0].add(name);
		}
	}
	
	public void setItem(String item){
		((ItemQuestion)question).setItem(item); /** create relation -> ItemQuestion.java */ /** call relation -> ItemQuestion.java */
	}
	
	public void setItem(int side, String item){
		MapQuestion map = (MapQuestion)question; /** contain relation -> MapQuestion.java */ /** cast relation -> MapQuestion.java */
		map.setSide(side); /** call relation -> MapQuestion.java */
		map.setItem(item); /** call relation -> MapQuestion.java */
	}
	
	public void setSide(int side){
		MapQuestion map = (MapQuestion)question; /** contain relation -> MapQuestion.java */ /** cast relation -> MapQuestion.java */
		map.setSide(side); /** call relation -> MapQuestion.java */
	}
	
	public void setAnswer(String answer){
		question.setAnswer(answer); /** call relation -> Question.java */
	}
	
	public void setPrompt(String prompt){
		question.setPrompt(prompt); /** call relation -> Question.java */

	}
	
	public void setScore(int score){
		question.setScore(score); /** call relation -> Question.java */
	}
	
	public List<String> getPageName(int type){
		return pageNameList[type];
	}
	
	public List<String> displayPage(int pageNumber, int type){
		List<String> ret = new LinkedList<String>();
		if(pageNameList[type].size() <= pageNumber){
			return ret;
		}else{
			page = io.readPage(pageNameList[type].get(pageNumber)); /** call relation -> IO.java */
			Iterator<Question> questions = page.iterator(); /** use relation -> Question.java */
			while(questions.hasNext()){
				Question q = questions.next(); /** contain relation -> Question.java */
				int ty = q.getType(); /** call relation -> Question.java */
				String answer = "";
				if(type == 1 && ty != 3){
					answer = "\nThe correct answer is " + q.getAnswer().writeAnswer(); /** call relation -> Question.java */
				}
				ret.add(q.getQuestion()+answer+"\n"); /** call relation -> Question.java */
			}
			return ret;
		}
	}
	
	public void save(){
		if(page.getType().equals("test")){ /** call relation -> Page.java */
			Test test = (Test)page; /** contain relation -> Test.java */ /** cast relation -> Test.java */
			test.computeScore(); /** call relation -> Test.java */
		}
		io.writeAllPages(pageNameList); /** call relation -> IO.java */
		io.writePage(page); /** call relation -> IO.java */
	}
	
	public int modify(int index){
		if(index >= page.getQuestionList().size()){ /** call relation -> Page.java */
			return -1;
		}else{
			question = page.getQuestion(index); /** call relation -> Page.java */
			return question.getType(); /** call relation -> Question.java */
		}
	}
	
	public void createDecideQuestion(String prompt, int score, String answer){
		DecideQuestion decide = new DecideQuestion(); /** contain relation -> DecideQuestion.java */ /** create relation -> DecideQuestion.java */
		decide.setPrompt(prompt); /** call relation -> DecideQuestion.java */
		decide.setScore(score); /** call relation -> DecideQuestion.java */
		decide.setAnswer(answer); /** call relation -> DecideQuestion.java */
		page.addQuestion(decide); /** call relation -> Page.java */
	}
	
	public void createDecideQuestion(String prompt){
		DecideQuestion decide = new DecideQuestion(); /** contain relation -> DecideQuestion.java */ /** create relation -> DecideQuestion.java */
		decide.setPrompt(prompt); /** call relation -> DecideQuestion.java */
		page.addQuestion(decide); /** call relation -> Page.java */
	}
	
	public void createChoiceQuestion(String prompt, String[] items, int score, String answer){
		ChoiceQuestion choice = new ChoiceQuestion(); /** contain relation -> ChoiceQuestion.java */ /** create relation -> ChoiceQuestion.java */
		choice.setPrompt(prompt); /** call relation -> ChoiceQuestion.java */
		for(int i=0; i<items.length; i++){
			choice.setItem(items[i]); /** call relation -> ChoiceQuestion.java */
		}
		choice.setScore(score); /** call relation -> ChoiceQuestion.java */
		choice.setAnswer(answer); /** call relation -> ChoiceQuestion.java */
		page.addQuestion(choice); /** call relation -> Page.java */
	}
	
	public void createChoiceQuestion(String prompt, String[] items){
		ChoiceQuestion choice = new ChoiceQuestion(); /** contain relation -> ChoiceQuestion.java */ /** create relation -> ChoiceQuestion.java */
		choice.setPrompt(prompt); /** call relation -> ChoiceQuestion.java */
		for(int i=0; i<items.length; i++){
			choice.setItem(items[i]); /** call relation -> ChoiceQuestion.java */
		}
		page.addQuestion(choice); /** call relation -> Page.java */
	}
	
	public void createTextQuestion(String prompt){
		ShortEssayQuestion text = new ShortEssayQuestion(); /** contain relation -> ShortEssayQuestion.java */ /** create relation -> ShortEssayQuestion.java */
		text.setPrompt(prompt); /** call relation -> ShortEssayQuestion.java */
		page.addQuestion(text); /** call relation -> Page.java */
	}
	
	public void createTextQuestion(String prompt, int score, String answer){
		ShortEssayQuestion text = new ShortEssayQuestion(); /** contain relation -> ShortEssayQuestion.java */ /** create relation -> ShortEssayQuestion.java */
		text.setPrompt(prompt); /** call relation -> Question.java */
		text.setScore(score); /** call relation -> Question.java */
		text.setAnswer(answer); /** call relation -> ShortEssayQuestion.java */
		page.addQuestion(text); /** call relation -> Page.java */
	}
	
	public void createEssayQuestion(String prompt){
		EssayQuestion question = new EssayQuestion(); /** contain relation -> EssayQuestion.java */ /** create relation -> EssayQuestion.java */
		question.setPrompt(prompt); /** call relation -> Question.java */
		page.addQuestion(question); /** call relation -> Page.java */
	}
	
	public void createRankQuestion(String prompt, String[] items){
		RankQuestion question = new RankQuestion(); /** contain relation -> RankQuestion.java */ /** create relation -> RankQuestion.java */
		question.setPrompt(prompt); /** call relation -> RankQuestion.java */
		for(int i=0; i<items.length; i++){
			question.setItem(items[i]); /** call relation -> RankQuestion.java */
		}
		page.addQuestion(question); /** call relation -> Page.java */
	}
	
	public void createRankQuestion(String prompt, String[] items, int score, String answer){
		RankQuestion question = new RankQuestion(); /** contain relation -> RankQuestion.java */ /** create relation -> RankQuestion.java */
		question.setPrompt(prompt); /** call relation -> RankQuestion.java */
		for(int i=0; i<items.length; i++){
			question.setItem(items[i]); /** call relation -> RankQuestion.java */
		}
		question.setScore(score); /** call relation -> Question.java */
		question.setAnswer(answer); /** call relation -> Question.java */
		page.addQuestion(question); /** call relation -> Page.java */
	}
	
	public void createMapQuestion(String prompt, String[] side1, String[] side2){
		MapQuestion map = new MapQuestion(); /** contain relation -> MapQuestion.java */ /** create relation -> MapQuestion.java */
		map.setPrompt(prompt); /** call relation -> Question.java */
		map.setSide(1); /** contain relation -> MapQuestion.java */
		for(int i=0; i<side1.length; i++){
			map.setItem(side1[i]); /** call relation -> MapQuestion.java */
		}
		map.setSide(2); /** call relation -> MapQuestion.java */
		for(int i=0; i<side2.length; i++){
			map.setItem(side2[i]); /** call relation -> MapQuestion.java */
		}
		page.addQuestion(map); /** call relation -> Page.java */
	}
	
	public void createMapQuestion(String prompt, String[] side1, String[] side2, int score, String answer){
		MapQuestion map = new MapQuestion(); /** contain relation -> MapQuestion.java */ /** create relation -> MapQuestion.java */
		map.setPrompt(prompt); /** call relation -> Question.java */
		map.setSide(1); /** call relation -> MapQuestion.java */
		for(int i=0; i<side1.length; i++){
			map.setItem(side1[i]); /** call relation -> MapQuestion.java */
		}
		map.setSide(2);
		for(int i=0; i<side2.length; i++){
			map.setItem(side2[i]); /** call relation -> MapQuestion.java */
		}
		map.setScore(score); /** call relation -> Question.java */
		map.setAnswer(answer); /** call relation -> MapQuestion.java */
		page.addQuestion(map); /** call relation -> Page.java */
	}		
	
	public boolean remove(int index){
		if(question.getType() == 5){ /** call relation -> Question.java */
			return ((MapQuestion)question).remove(index); /** cast relation -> MapQuestion.java */ /** call relation -> MapQuestion.java */
		}
		return ((ItemQuestion)question).remove(index); /** cast relation -> ItemQuestion.java */ /** call relation -> ItemQuestion.java */
	}
	
	public boolean changeItem(int index, String item){
		if(question.getType() == 5){ /** call relation -> Question.java */
			return ((MapQuestion)question).changeItem(index, item);	/** cast relation -> MapQuestion.java */ /** call relation -> MapQuestion.java */ 	 
		}
		return ((ItemQuestion)question).changeItem(index, item); /** cast relation -> ItemQuestion.java */ /** call relation -> ItemQuestion.java */
	}
	
	public boolean changeItemNumber(int num){
		if(question.getType() == 5){ /** call relation -> Question.java */
			return ((MapQuestion)question).changeItemNumber(num); /** cast relation -> MapQuestion.java */ /** call relation -> MapQuestion.java */		 
		}
		return ((ItemQuestion)question).changeItemNumber(num); /** cast relation -> ItemQuestion.java */ /** call relation -> ItemQuestion.java */
	}
	
	public void loadPage(int index, int type){
		page = io.readPage(pageNameList[type].get(index)); /** call relation -> IO.java */
		record = new Record(); /** new relation -> Record.java */
	}
	
	public void setRecordName(String name){
		record.setPersonName(name); /** call relation -> Record.java */
		iterator = page.iterator(); /** call relation -> Page.java */
	}
	
	public String nextQuestion(){
		question = iterator.next();
		return question.getQuestion(); /** call relation -> Question.java */
	}
	
	public boolean hasNextQuestion(){
		return iterator.hasNext();
	}
	
	public void answerQuestion(String answer){
		switch(question.getType()){ /** call relation -> Question.java */
		case 0: DecideAnswer decide = new DecideAnswer(); /** contain relation -> DecideAnswer.java */ /** create relation -> DecideAnswer.java */
				decide.setAnswer(answer); /** call relation -> DecideAnswer.java */
				record.addAnwser(decide); /** call relation -> Record.java */
				break;
		case 1: ChoiceAnswer choice = new ChoiceAnswer(); /** contain relation -> ChoiceAnswer.java */ /** create relation -> ChoiceAnswer.java */
				choice.setAnswer(answer); /** call relation -> ChoiceAnswer.java */
				record.addAnwser(choice); /** call relation -> Record.java */
				break;
		case 2:
		case 3:	TextAnswer text = new TextAnswer(); /** contain relation -> TextAnswer.java */ /** create relation -> TextAnswer.java */
				text.setAnswer(answer); /** call relation -> TextAnswer.java */
				record.addAnwser(text); /** call relation -> Record.java */
				break;
		case 4: RankAnswer rank = new RankAnswer(); /** contain relation -> RankAnswer.java */ /** create relation -> RankAnswer.java */
				rank.setAnswer(answer); /** call relation -> RankAnswer.java */
				record.addAnwser(rank); /** call relation -> Record.java */
				break;
		case 5: MapAnswer map = new MapAnswer(); /** contain relation -> MapAnswer.java */ /** create relation -> MapAnswer.java */
				map.setAnswer(answer); /** call relation -> MapAnswer.java */
				record.addAnwser(map); /** call relation -> Record.java */
				break;
		}
	}
	
	public void saveAnswer(){
		recordName = io.readRecordInfo(page.getPageName()); /** call relation -> IO.java */ /** call relation -> Page.java */
		recordName.add(page.getPageName()+"-"+record.getPersonName()); /** call relation -> Page.java */ /** call relation -> Record.java */
		if(page.getType().equals("test")){ /** call relation -> Page.java */
			this.grade();
		}
		io.writeReordInfo(page.getPageName(), recordName); /** call relation -> IO.java */ /** call relation -> Page.java */
		io.writeRecord(page.getPageName()+"-"+record.getPersonName(), record); /** call relation -> IO.java */ /** call relation -> Page.java */ /** call relation -> Record.java */
	}
	
	public void grade(){
		Iterator<Question> questionIterator = page.iterator(); /** use relation -> Question.java */
		Iterator<Answer> answerIterator = record.iterator(); /** use relation -> Answer.java */
		if(questionIterator.hasNext()){
			Question q = questionIterator.next(); /** contain relation -> Question.java */
			if(q.getType() != 3){
				if(q.match((Answer)answerIterator.next())){ /** cast relation -> Answer.java */
					record.addScore(q.getScore()); /** call relation -> Record.java */ /** call relation -> Question.java */
				}
			}else{
				answerIterator.next();
			}
		}
	}
	
	public String getOutcome(int index, int type){
		this.loadPage(index, type);
		recordName = io.readRecordInfo(page.getPageName()); /** call relation -> IO.java */ /** call relation -> Page.java */
		List<Iterator<Answer>> recordList = new LinkedList<Iterator<Answer>>(); /** use relation -> Answer.java */
		for(int i=0; i<recordName.size(); i++){
			recordList.add(io.readRecord(recordName.get(i)).iterator());
		}
		Iterator<Question> questionIterator = page.iterator(); /** use relation -> Question.java */ 
		List<String> outcome = new LinkedList<String>();
		while(questionIterator.hasNext()){
			Question question = questionIterator.next(); /** contain relation -> Question.java */ 
			Map<String, Integer> map = new HashMap<String, Integer>();
			for(int i=0; i<recordList.size(); i++){
				Answer answer = recordList.get(i).next(); /** call relation -> Answer.java */
				if(map.containsKey(answer.writeAnswer())){ /** call relation -> Answer.java */
					int value = map.get(answer.writeAnswer()); /** call relation -> Answer.java */
					map.put(answer.writeAnswer(), value+1); /** call relation -> Answer.java */
				}else{
					map.put(answer.writeAnswer(), 1); /** call relation -> Answer.java */
				}
			}
			String oneOutcome = question.getQuestion(); /** call relation -> Question.java */
			for(String key: map.keySet()){
				oneOutcome +="Answer: " + key+"\t"+map.get(key)+"\n";
			}
			outcome.add(oneOutcome);
		}
		String ret = "";
		for(int i=0; i<outcome.size(); i++){
			ret += outcome.get(i)+"\n";
		}
		return ret;
	}
}
