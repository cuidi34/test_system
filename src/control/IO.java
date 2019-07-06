package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import paper.Iterator;
import paper.Page;
import paper.Record;
import paper.Survey;
import paper.Test;
import question.ChoiceQuestion;
import question.DecideQuestion;
import question.EssayQuestion;
import question.ItemQuestion;
import question.MapQuestion;
import question.PromptQuestion; /** import relation -> PromptQuestion.java */
import question.Question; /** import relation -> Question.java */
import question.RankQuestion; /** import relation -> RankQuestion.java */
import question.ShortEssayQuestion; /** import relation -> ShortEssayQuestion.java */
import anwser.Answer; /** import relation -> Answer.java */
import anwser.ChoiceAnswer; /** import relation -> ChoiceAnswer.java */
import anwser.DecideAnswer; /** import relation -> DecideAnswer.java */
import anwser.MapAnswer; /** import relation -> MapAnswer.java */
import anwser.RankAnswer; /** import relation -> RankAnswer.java */
import anwser.TextAnswer; /** import relation -> TextAnswer.java */

public class IO {
	SAXBuilder builder = new SAXBuilder();
	
	public List<String>[] readAllPages(){
		InputStream file;
		Element root = null;
		try {
			file = new FileInputStream("xml/pageInfo.xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Element> pageList = root.getChildren("pageName");
		List<String>[] pageName = new List[2];
		pageName[0] = new LinkedList<String>();
		pageName[1] = new LinkedList<String>();
		for(int i=0; i<pageList.size(); i++){
			if(pageList.get(i).getAttributeValue("type").equals("test")){
				pageName[1].add(pageList.get(i).getText());
			}else{
				pageName[0].add(pageList.get(i).getText());
			}
		}
		return pageName;
	}
	
	public void writeAllPages(List<String>[] pageName){
		
		Element root = new Element("totalInfo");
		for(int i=0; i<pageName[0].size(); i++){
			Element page = new Element("pageName");
			page.addContent(pageName[0].get(i));
			page.setAttribute("type", "survey");
			root.addContent(page);
		}
		for(int i=0; i<pageName[1].size(); i++){
			Element page = new Element("pageName");
			page.addContent(pageName[1].get(i));
			page.setAttribute("type", "test");
			root.addContent(page);
		}
		Document doc=new Document(root);  
		 try {
			FileOutputStream out=new FileOutputStream("xml/pageInfo.xml");
			XMLOutputter outputter = new XMLOutputter();  
	        Format f = Format.getPrettyFormat();  
	        outputter.setFormat(f);  
	        outputter.output(doc, out);  
	        out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public Page readPage(String pageName){ /** return relation -> Page.java */
		InputStream file;
		Element root = null;
		try {
			file = new FileInputStream("xml/"+pageName+".xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page page; /** use relation -> Page.java */ /** Contain relation -> Page.java */
		if(root.getAttribute("type").getValue().equals("test")){
			Test test = new Test(); /** create relation -> Test.java */ 
			test.setTotalScore(Integer.parseInt(root.getChildText("score"))); /** call relation -> Test.java */
			page = test;
			page.setType("test"); /** call relation -> page.java */
		}else{
			page = new Survey(); /** create relation -> Survey.java */
			page.setType("survey"); /** call relation -> Page.java */
		}
		page.setPageName(pageName); /** call relation -> Page.java */
		System.out.println(page.getPageName()); /** call relation -> Page.java */
		Element questions =  root.getChild("questions");
		List<Element> questionList = questions.getChildren();
		for(int i=0; i<questionList.size(); i++){
			Element question = questionList.get(i);
			int type = Integer.parseInt(question.getAttributeValue("type"));
			Question q = null; /** use relation -> Question.java */ /** contain relation -> Question.java */
			switch(type){
			case 0: 
				DecideQuestion decide = new DecideQuestion(); /** create relation -> DecideQuestion.java */ /** contain relation -> DecideQuestion.java */
				q = this.readDecideQuestion(question, decide);
				break;
			case 1: 
				ChoiceQuestion choice = new ChoiceQuestion(); /** create relation -> ChoiceQuestion.java */ /** contain relation -> ChoiceQuestion.java */
				q = this.readChoiceQuestion(question, choice);
				break;
			case 2: 
				ShortEssayQuestion text = new ShortEssayQuestion(); /** create relation -> ShortEssayQuestion.java */ /** contain relation -> ShortEssayQuestion.java */
				q = this.readTextQuestion(question, text);
				break;
			case 3: 
				EssayQuestion essay = new EssayQuestion(); /** create relation -> EssayQuestion.java */ /** contain relation -> EssayQuestion.java */
				q = this.readEssayQuestion(question, essay);
				break;
			case 4: 
				RankQuestion rank = new RankQuestion(); /** create relation -> RankQuestion.java */ /** contain relation -> RankQuestion.java */
				q = this.readRankQuestion(question, rank);
				break;
			case 5: 
				MapQuestion map = new MapQuestion(); /** create relation -> MapQuestion.java */ /** contain relation -> MapQuestion.java */
				q = this.readMapQuestion(question, map);
				break;
			}
			page.addQuestion(q); /** call relation -> Page.java */
		}
		return page; /** use relation -> Page.java */
	}
	
	public Question readDecideQuestion(Element question, DecideQuestion decide){ /** return relation -> Question.java */ /** parameter relation -> DecideQuestion.java */
		decide.setPrompt(question.getChildText("prompt")); /** call relation -> DecideQuestion.java */
		decide.setScore(Integer.parseInt(question.getChildText("score"))); /** call relation -> DecideQuestion.java */
		if(question.getAttributeValue("answer").equals("1")){ 
			decide.setAnswer(question.getChildText("answer")); /** call relation -> DecideQuestion.java */
		}
		return decide; /** use relation -> DecideQuestion.java */
	}
	
	public Question readChoiceQuestion(Element question, ChoiceQuestion choice){ /** return relation -> Question.java */ /** parameter relation -> ChoiceQuestion.java */
		choice.setPrompt(question.getChildText("prompt")); /** call relation -> ChoiceQuestion.java */
		choice.setScore(Integer.parseInt(question.getChildText("score"))); /** call relation -> ChoiceQuestion.java */
		List<Element> items = question.getChild("items").getChildren();
		for(int i=0; i<items.size(); i++){
			Element item = items.get(i);
			choice.setItem(item.getText()); /** call relation -> ChoiceQuestion.java */
		}
		if(question.getAttributeValue("answer").equals("1")){
			choice.setAnswer(question.getChildText("answer")); /** call relation -> ChoiceQuestion.java */
		}
		return choice; /** use relation -> ChoiceQuestion.java */
	}
	
	public Question readTextQuestion(Element question, ShortEssayQuestion text){ /** return relation -> Question.java */ /** parameter relation -> ShortEssayQuestion.java */
		text.setPrompt(question.getChildText("prompt")); /** call relation -> ShortEssayQuestion.java */
		text.setScore(Integer.parseInt(question.getChildText("score"))); /** call relation -> ShortEssayQuestion.java */
		if(question.getAttributeValue("answer").equals("1")){
			text.setAnswer(question.getChildText("answer")); /** call relation -> ShortEssayQuestion.java */
		}
		return text; /** use relation -> ShortEssayQuestion.java */
	}
	
	public Question readEssayQuestion(Element question, EssayQuestion essay){ /** return relation -> Question.java */ /** parameter relation -> EssayQuestion.java */
		essay.setPrompt(question.getChildText("prompt")); /** call relation -> EssayQuestion.java */
		essay.setAnswer(""); /** call relation -> EssayQuestion.java */
		return essay; /** use relation -> EssayQuestion.java */
	}
	
	public Question readRankQuestion(Element question, RankQuestion rank){ /** return relation -> Question.java */ /** parameter relation -> RankQuestion.java */
		rank.setPrompt(question.getChildText("prompt")); /** call relation -> RankQuestion.java */
		rank.setScore(Integer.parseInt(question.getChildText("score"))); /** call relation -> RankQuestion.java */
		List<Element> items = question.getChild("items").getChildren();
		for(int i=0; i<items.size(); i++){
			rank.setItem(items.get(i).getText()); /** call relation -> RankQuestion.java */
		}
		System.out.println(question.getChildText("answer"));
		if(question.getAttributeValue("answer").equals("1")){
			rank.setAnswer(question.getChildText("answer")); /** call relation -> RankQuestion.java */
		}
		return rank; /** use relation -> RankQuestion.java */
	}
	
	public Question readMapQuestion(Element question, MapQuestion map){ /** return relation -> Question.java */ /** parameter relation -> MapQuestion.java */
		map.setPrompt(question.getChildText("prompt")); /** call relation -> MapQuestion.java */
		Element side1 = (Element) question.getChild("side1");
		List<Element> sideList1 = side1.getChildren();
		map.setSide(1); /** call relation -> MapQuestion.java */
		for(int j=0; j<sideList1.size(); j++){
			map.setItem(sideList1.get(j).getText()); /** call relation -> MapQuestion.java */
		}
		Element side2 = (Element) question.getChild("side2");
		List<Element> sideList2 = side2.getChildren();
		map.setSide(2); /** call relation -> MapQuestion.java */
		for(int j=0; j<sideList2.size(); j++){
			map.setItem(sideList2.get(j).getText()); /** call relation -> MapQuestion.java */
		}
		if(question.getAttributeValue("isScore").equals("1")){
			map.setScore(Integer.parseInt(question.getChildText("score"))); /** call relation -> MapQuestion.java */
		}
		if(question.getAttributeValue("answer").equals("1")){
			map.setAnswer(question.getChildText("answer")); /** call relation -> MapQuestion.java */						
		}
		return map; /** use relation -> MapQuestion.java */
	}
	
	public void writePage(Page page){
		Element root = new Element("Page");
		root.setAttribute("type", page.getType());
		root.addContent(new Element("pageName").setText(page.getPageName()));
		if(page.getType().equals("test")){
			root.addContent(new Element("score").setText(((Test)page).getTotalScore()+"")); /** cast relation -> Test.java */
		}
		
		List<Question> questionList = page.getQuestionList();  /** use relation -> Question.java */
		Element questions = new Element("questions");
		for(int i=0; i<questionList.size(); i++){
			Question question = questionList.get(i); 
			Element qe = null;
			switch(question.getType()){
			case 0: qe = savePromptQuestion((PromptQuestion)question);break; /** cast relation -> PromptQuestion.java */
			case 1: qe = saveItemQuestion((ItemQuestion)question);break; /** cast relation -> ItemQuestion.java */
			case 2: qe = savePromptQuestion((PromptQuestion)question);break; /** cast relation -> PromptQuestion.java */
			case 3: qe = saveEssayQuestion((EssayQuestion)question);break; /** cast relation -> EssayQuestion.java */
			case 4: qe = saveItemQuestion((ItemQuestion)question);break; /** cast relation -> ItemQuestion.java */
			case 5: qe = saveMapQuestion((MapQuestion)question);break; /** cast relation -> MapQuestion.java */
			}
			questions.addContent(qe);			
		}
		root.addContent(questions);
		Document doc=new Document(root);  
		 try {
			FileOutputStream out=new FileOutputStream("xml/"+page.getPageName()+".xml");
			XMLOutputter outputter = new XMLOutputter();  
	        Format f = Format.getPrettyFormat();  
	        outputter.setFormat(f);  
	        outputter.output(doc, out);  
	        out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public Element savePromptQuestion(PromptQuestion promptQuestion){ /** parameter relation -> PromptQuestion.java */
		Element ret = new Element("question");
		ret.setAttribute("type", promptQuestion.getType()+"");
		ret.setAttribute("isScore", "1");
		int anwser = 1;
		if(promptQuestion.getAnswer() == null) /** call relation -> PromptQuestion.java */
			anwser = 0;
		ret.setAttribute("answer", anwser+"");
		Element prompt = new Element("prompt");
		prompt.setText(promptQuestion.getPrompt());
		ret.addContent(prompt);
		if(promptQuestion.getAnswer() != null){ /** call relation -> PromptQuestion.java */
			Answer an = promptQuestion.getAnswer(); /** call relation -> PromptQuestion.java */
			Element answerElement = new Element("answer");
			answerElement.setText(an.writeAnswer()); /** call relation -> Answer.java */
			ret.addContent(answerElement);
		}
		
		ret.addContent(new Element("score").setText(promptQuestion.getScore()+""));
		return ret;
	}
	
	public Element saveItemQuestion(ItemQuestion item){ /** parameter relation -> ItemQuestion.java */
		Element ret = new Element("question");
		ret.setAttribute("type", item.getType()+"");
		ret.setAttribute("isScore", "1");
		int anwser = 1;
		if(item.getAnswer() == null) /** call relation -> ItemQuestion.java */
			anwser = 0;
		ret.setAttribute("answer", anwser+"");
		Element prompt = new Element("prompt");
		prompt.setText(item.getPrompt());
		ret.addContent(prompt);
		List<String> items = item.getItem(); /** call relation -> ItemQuestion.java */
		Element itemElement = new Element("items");
		for(int j=0; j<items.size(); j++){
			itemElement.addContent(new Element("item").setText(items.get(j)));
		}
		ret.addContent(itemElement);
		if(item.getAnswer() != null){ /** call relation -> ItemQuestion.java */
			Answer an = item.getAnswer(); /** call relation -> ItemQuestion.java */ /** contain relation -> Answer.java */
			Element answerElement = new Element("answer");
			answerElement.setText(an.writeAnswer()); /** call relation -> Answer.java */
			ret.addContent(answerElement);
		}
		
		ret.addContent(new Element("score").setText(item.getScore()+""));
		return ret;
	}
	
	public Element saveMapQuestion(MapQuestion map){ /** parameter relation -> MapQuestion.java */
		Element ret =new Element("question");
		ret.setAttribute("type", map.getType()+"");
		ret.setAttribute("isScore", "1");
		int anwser = 1;
		if(map.getAnswer() == null) /** call relation -> MapQuestion.java */
			anwser = 0;
		ret.setAttribute("answer", anwser+"");
		Element prompt = new Element("prompt");
		prompt.setText(map.getPrompt()); /** call relation -> MapQuestion.java */
		ret.addContent(prompt);
		
		map.setSide(1); /** call relation -> MapQuestion.java */
		List<String> side1 = map.getItem(); /** call relation -> MapQuestion.java */
		Element item1 = new Element("side1");
		for(int j=0; j<side1.size(); j++){
			item1.addContent(new Element("left").setText(side1.get(j)));
		}
		ret.addContent(item1);
		
		map.setSide(2); /** call relation -> MapQuestion.java */
		List<String> side2 = map.getItem();
		Element item2 = new Element("side2");
		for(int j=0; j<side2.size(); j++){
			item2.addContent(new Element("right").setText(side2.get(j)));
		}
		ret.addContent(item2);
		if(map.getAnswer() != null){ /** call relation -> MapQuestion.java */
			Answer an = map.getAnswer(); /** contain relation -> Answer.java */ /** call relation -> MapQuestion.java */
			Element answerElement = new Element("answer");
			answerElement.setText(an.writeAnswer()); /** call relation -> Answer.java */
			ret.addContent(answerElement);
		}
		
		ret.addContent(new Element("score").setText(map.getScore()+"")); /** call relation -> MapQuestion.java */
		return ret;
	}
	
	public Element saveEssayQuestion(EssayQuestion question){ /** parameter relation -> EssayQuestion.java */
		Element ret =new Element("question");
		ret.setAttribute("type", question.getType()+"");
		ret.setAttribute("isScore", "0");
		ret.setAttribute("answer", "0");
		Element prompt = new Element("prompt");
		prompt.setText(question.getPrompt()); /** call relation -> EssayQuestion.java */
		question.setAnswer(null); /** call relation -> EssayQuestion.java */
		ret.addContent(prompt);
		return ret;
	}
	
	public List<String> readRecordInfo(String pageName){
		InputStream file;
		Element root = null;
		try {
			File recordFile = new File("xml/record/"+pageName+"-recordInfo.xml");
			if(!recordFile.exists()){
				return new LinkedList<String>();
			}
			file = new FileInputStream("xml/record/"+pageName+"-recordInfo.xml");
			
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Element> pageList = root.getChildren("record");
		List<String> records = new LinkedList<String>();
		for(int i=0; i<pageList.size(); i++){
			records.add(pageList.get(i).getText()); 
		}
		return records;
	}
	
	public void writeReordInfo(String pageName, List<String> recordName){
		Element root = new Element("Records");
		for(int i=0; i<recordName.size(); i++){
			Element record = new Element("record");
			record.setText(recordName.get(i));
			root.addContent(record);
		}
		
		Document doc=new Document(root);  
		 try {
			FileOutputStream out=new FileOutputStream("xml/record/"+pageName+"-recordInfo.xml");
			XMLOutputter outputter = new XMLOutputter();  
	        Format f = Format.getPrettyFormat();  
	        outputter.setFormat(f);  
	        outputter.output(doc, out);  
	        out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public void writeRecord(String recordName, Record record){ /** parameter relation -> Record.java */
		Element root = new Element("Record");
		Element personName = new Element("PersonName");
		personName.setText(record.getPersonName());
		root.addContent(personName);
		Element score = new Element("score");
		score.setText(record.getScore()+"");
		Element answers = new Element("answers");
		root.addContent(score);
		Iterator<Answer> iterator = record.iterator(); /** use relation -> Answer.java */ /** call relation -> Record.java */
		
		while(iterator.hasNext()){
			Answer answer = iterator.next(); /** contain relation -> Answer.java */ 
			Element answerElement = new Element("answer");
			answerElement.setAttribute("type", answer.getType()+"");  /** call relation -> Answer.java */
			answerElement.setText(answer.writeAnswer()); /** call relation -> Answer.java */
			answers.addContent(answerElement); /** call relation -> Answer.java */
		}
		
		root.addContent(answers);
		
		Document doc=new Document(root);  
		 try {
			FileOutputStream out=new FileOutputStream("xml/record/"+recordName+".xml");
			XMLOutputter outputter = new XMLOutputter();  
	        Format f = Format.getPrettyFormat();  
	        outputter.setFormat(f);  
	        outputter.output(doc, out);  
	        out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public Record readRecord(String recordName){ /** return relation -> Record.java */
		
		InputStream file;
		Element root = null;
		try {
			file = new FileInputStream("xml/record/"+recordName+".xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Record record = new Record(); /** create relation -> Record.java */ /** contain relation -> Record.java */
		record.setPersonName(root.getChildText("PersonName")); /** call relation -> Record.java */
		record.addScore(Integer.parseInt(root.getChildText("score"))); /** call relation -> Record.java */
		Element answers = root.getChild("answers"); 
		List<Element> answerList = answers.getChildren(); /** call relation -> Record.java */
		System.out.println(answerList.size()); 
		for(int i=0; i<answerList.size(); i++){
			Element answer = answerList.get(i);
			int type = Integer.parseInt(answer.getAttributeValue("type")); 
			switch(type){
			case 0: DecideAnswer decide = new DecideAnswer(); /** create relation -> DecideAnswer.java */ /** contain relation -> DecideAnswer.java */
					decide.setAnswer(answer.getText()); /** call relation -> DecideAnswer.java */
					record.addAnwser(decide); /** call relation -> Record.java */
					break;
			case 1: ChoiceAnswer choice = new ChoiceAnswer(); /** create relation -> ChoiceAnswer.java */ /** contain relation -> ChoiceAnswer.java */
					choice.setAnswer(answer.getText()); /** call relation -> ChoiceAnswer.java */
					record.addAnwser(choice); /** call relation -> Record.java */
					break;
			case 2: TextAnswer text = new TextAnswer(); /** create relation -> TextAnswer.java */ /** contain relation -> TextAnswer.java */
					text.setAnswer(answer.getText()); /** call relation -> TextAnswer.java */
					record.addAnwser(text); /** call relation -> Record.java */
					break;
			case 4: RankAnswer rank = new RankAnswer(); /** create relation -> RankAnswer.java */ /** contain relation -> RankAnswer.java */
					rank.setAnswer(answer.getText()); /** call relation -> RankAnswer.java */
					record.addAnwser(rank); /** call relation -> Record.java */
					break;
			case 5: MapAnswer map = new MapAnswer(); /** create relation -> MapAnswer.java */ /** contain relation -> MapAnswer.java */
					map.setAnswer(answer.getText()); /** call relation -> MapAnswer.java */
					record.addAnwser(map); /** call relation -> Record.java */
					break;
			}
		}
		return record;
	}
	
}
