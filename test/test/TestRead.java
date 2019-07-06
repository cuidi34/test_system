package test;

import static org.junit.Assert.assertTrue;
import paper.Test;  /** import relation -> Test.java */
import question.ChoiceQuestion;  /** import relation -> ChoiceQuestion.java */ 
import question.DecideQuestion;  /** import relation -> DecideQuestion.java */
import question.EssayQuestion;  /** import relation -> EssayQuestion.java */
import question.MapQuestion;  /** import relation -> MapQuestion.java */
import question.Question;  /** import relation -> Question.java */
import question.RankQuestion;  /** import relation -> RankQuestion.java */
import question.ShortEssayQuestion;  /** import relation -> ShortEssayQuestion.java */
import control.IO;  /** import relation -> IO.java */

public class TestRead {

	@org.junit.Test
	public void test() {
		IO io = new IO();  /** contain relation -> IO.java */ /** create relation -> IO.java */
		Test test = (Test) io.readPage("test"); /** contain relation -> Test.java */ /** cast relation -> Test.java */ /** call relation -> Test.java */
		assertTrue(test.getType().equals("test")); /** call relation -> Test.java */
		assertTrue(test.getPageName().equals("test")); /** call relation -> Test.java */
		assertTrue(test.getTotalScore() == 100); /** call relation -> Test.java */
		
		for(Question q : test.getQuestionList()){ /** contain relation -> Question.java */ /** call relation -> Test.java */
			if(q instanceof ChoiceQuestion){
				ChoiceQuestion cq = (ChoiceQuestion) q; /** contain relation -> ChoiceQuestion.java */ /** cast relation -> ChoiceQuestion.java */
				assertTrue(cq.getAnswer().writeAnswer().trim().equals("0")); /** call relation -> ChoiceQuestion.java */ /** call relation -> Answer.java */
				assertTrue(cq.getItem().get(0).equals("item")); /** call relation -> ChoiceQuestion.java */
				assertTrue(cq.getPrompt().equals("cq")); /** call relation -> Question.java */
				assertTrue(cq.getScore() == 1); /** call relation -> Question.java */
			}
			else if(q instanceof DecideQuestion){
				DecideQuestion dq = (DecideQuestion) q; /** contain relation -> DecideQuestion.java */ /** cast relation -> DecideQuestion.java */
				assertTrue(dq.getAnswer().getAnswer().equals("1")); /** call relation -> DecideQuestion.java */ /** call relation -> Answer.java */
				assertTrue(dq.getPrompt().equals("ff")); /** call relation -> Question.java */
				assertTrue(dq.getScore() == 12); /** call relation -> Question.java */
			}			else if(q instanceof EssayQuestion){
				EssayQuestion eq = (EssayQuestion) q; /** contain relation -> EssayQuestion.java */ /** cast relation -> EssayQuestion.java */
				assertTrue(eq.getPrompt().equals("fff")); /** call relation -> Question.java */
			}
			else if(q instanceof MapQuestion){
				MapQuestion mq = (MapQuestion) q; /** contain relation -> MapQuestion.java */ /** cast relation -> MapQuestion.java */
				assertTrue(mq.getAnswer().writeAnswer().trim().equals("3")); /** call relation -> MapQuestion.java */ /** call relation -> Answer.java */
				assertTrue(mq.getItem().get(0).equals("tea")); /** call relation -> MapQuestion.java */
				assertTrue(mq.getPrompt().equals("fffffaa")); /** call relation -> Question.java */
				assertTrue(mq.getScore() == 3); /** call relation -> Question.java */
				assertTrue(mq.getSide() == 2); /** call relation -> MapQuestion.java */
			}
			else if(q instanceof RankQuestion){
				RankQuestion rq = (RankQuestion) q; /** contain relation -> RankQuestion.java */ /** cast relation -> RankQuestion.java */
				assertTrue(rq.getAnswer().writeAnswer().trim().equals("4")); /** call relation -> RankQuestion.java */ /** call relation -> Answer.java */
				assertTrue(rq.getItem().get(0).equals("ee")); /** call relation -> RankQuestion.java */
				assertTrue(rq.getPrompt().equals("vvv")); /** call relation -> Question.java */
				assertTrue(rq.getScore() == 7); /** call relation -> Question.java */
			}
			else if(q instanceof ShortEssayQuestion){
				ShortEssayQuestion seq = (ShortEssayQuestion) q; /** contain relation -> ShortEssayQuestion.java */ /** cast relation -> ShortEssayQuestion.java */
				assertTrue(seq.getAnswer().writeAnswer().trim().equals("5")); /** call relation -> ShortEssayQuestion.java */ /** call relation -> Answer.java */
				assertTrue(seq.getPrompt().equals("rrrr")); /** call relation -> Question.java */
				assertTrue(seq.getScore() == 6); /** call relation -> Question.java */
			}
		}
	}

}
