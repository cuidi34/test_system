package test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import paper.Test;
import question.ChoiceQuestion;
import question.DecideQuestion;
import question.EssayQuestion;
import question.MapQuestion;
import question.RankQuestion;
import question.ShortEssayQuestion;
import control.IO;

public class TestWrite {

	@org.junit.Test
	public void test() {
		
		IO io = new IO();
		
		Test test = new Test();
		test.setType("test");
		test.setPageName("test");
		test.setTotalScore(100);
		
		ChoiceQuestion cq = new ChoiceQuestion();
		cq.setAnswer("0");
		cq.setItem("item");
		cq.setPrompt("cq");
		cq.setScore(1);
		test.getQuestionList().add(cq);
		
		DecideQuestion dq = new DecideQuestion();
		dq.setAnswer("1");
		dq.setPrompt("ff");
		dq.setScore(12);
		test.getQuestionList().add(dq);
		
		EssayQuestion eq = new EssayQuestion();
		eq.setAnswer("2");
		eq.setPrompt("fff");
		eq.setScore(4);
		test.getQuestionList().add(eq);
		
		MapQuestion mq = new MapQuestion();
		mq.setAnswer("3");
		mq.setItem("tea");
		mq.setPrompt("fffffaa");
		mq.setScore(3);
		mq.setSide(2);
		test.getQuestionList().add(mq);
		
		RankQuestion rq = new RankQuestion();
		rq.setAnswer("4");
		rq.setItem("ee");
		rq.setPrompt("vvv");
		rq.setScore(7);
		test.getQuestionList().add(rq);
		
		ShortEssayQuestion seq = new ShortEssayQuestion();
		seq.setAnswer("5");
		seq.setPrompt("rrrr");
		seq.setScore(6);
		test.getQuestionList().add(seq);
		
		io.writePage(test);
		
		 try {
			 //read test.xml and test0.xml and compare
			File testFile = new File("xml/test.xml");
			File test0File = new File("xml/test0.xml");
			BufferedReader reader = new BufferedReader(new FileReader(testFile));
			BufferedReader reader0 = new BufferedReader(new FileReader(test0File));
			String line = reader.readLine();
			String line0 = reader0.readLine();
			while(line != null){  
				assertTrue(line.equals(line0));
		        line = reader.readLine();  
		        line0 = reader0.readLine();  
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

}
