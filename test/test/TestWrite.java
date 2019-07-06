package test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import paper.Test; /** import relation -> Test.java */
import question.ChoiceQuestion; /** import relation -> ChoiceQuestion.java */
import question.DecideQuestion; /** import relation -> DecideQuestion.java */
import question.EssayQuestion; /** import relation -> EssayQuestion.java */
import question.MapQuestion; /** import relation -> MapQuestion.java */
import question.RankQuestion; /** import relation -> RankQuestion.java */
import question.ShortEssayQuestion; /** import relation -> ShortEssayQuestion.java */
import control.IO; /** import relation -> Test.java */

public class TestWrite {

	@org.junit.Test
	public void test() {
		
		IO io = new IO(); /** contain relation -> IO.java */ /** create relation -> IO.java */
		
		Test test = new Test(); /** contain relation -> Test.java */ /** create relation -> Test.java */
		test.setType("test"); /** call relation -> Test.java */
		test.setPageName("test"); /** call relation -> Test.java */
		test.setTotalScore(100); /** call relation -> Test.java */
		
		ChoiceQuestion cq = new ChoiceQuestion(); /** contain relation -> ChoiceQuestion.java */ /** create relation -> ChoiceQuestion.java */
		cq.setAnswer("0"); /** call relation -> ChoiceQuestion.java */
		cq.setItem("item"); /** call relation -> ChoiceQuestion.java */
		cq.setPrompt("cq"); /** call relation -> Question.java */
		cq.setScore(1); /** call relation -> Question.java */
		test.getQuestionList().add(cq); /** call relation -> Test.java */
		
		DecideQuestion dq = new DecideQuestion(); /** contain relation -> DecideQuestion.java */ /** create relation -> DecideQuestion.java */
		dq.setAnswer("1"); /** call relation -> DecideQuestion.java */
		dq.setPrompt("ff"); /** call relation -> Question.java */
		dq.setScore(12); /** call relation -> Question.java */
		test.getQuestionList().add(dq); /** call relation -> Test.java */
		
		EssayQuestion eq = new EssayQuestion(); /** contain relation -> EssayQuestion.java */ /** create relation -> EssayQuestion.java */
		eq.setAnswer("2"); /** call relation -> EssayQuestion.java */
		eq.setPrompt("fff"); /** call relation -> Question.java */
		eq.setScore(4); /** call relation -> Question.java */
		test.getQuestionList().add(eq); /** call relation -> Test.java */
		
		MapQuestion mq = new MapQuestion(); /** contain relation -> MapQuestion.java */ /** create relation -> MapQuestion.java */
		mq.setAnswer("3"); /** call relation -> MapQuestion.java */
		mq.setItem("tea"); /** call relation -> MapQuestion.java */
		mq.setPrompt("fffffaa"); /** call relation -> Question.java */
		mq.setScore(3); /** call relation -> Question.java */
		mq.setSide(2); /** call relation -> MapQuestion.java */
		test.getQuestionList().add(mq); /** call relation -> Test.java */
		
		RankQuestion rq = new RankQuestion(); /** contain relation -> RankQuestion.java */ /** create relation -> RankQuestion.java */
		rq.setAnswer("4"); /** call relation -> RankQuestion.java */
		rq.setItem("ee"); /** call relation -> RankQuestion.java */
		rq.setPrompt("vvv"); /** call relation -> Question.java */
		rq.setScore(7); /** call relation -> Question.java */
		test.getQuestionList().add(rq); /** call relation -> RankQuestion.java */
		
		ShortEssayQuestion seq = new ShortEssayQuestion(); /** contain relation -> ShortEssayQuestion.java */ /** create relation -> ShortEssayQuestion.java */
		seq.setAnswer("5"); /** call relation -> ShortEssayQuestion.java */
		seq.setPrompt("rrrr"); /** call relation -> ShortEssayQuestion.java */
		seq.setScore(6); /** call relation -> Question.java */
		test.getQuestionList().add(seq); /** call relation -> Question.java */
		
		io.writePage(test); /** call relation -> IO.java */
		
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
