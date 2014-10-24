package test;

import static org.junit.Assert.assertTrue;
import paper.Test;
import question.ChoiceQuestion;
import question.DecideQuestion;
import question.EssayQuestion;
import question.MapQuestion;
import question.Question;
import question.RankQuestion;
import question.ShortEssayQuestion;
import control.IO;

public class TestRead {

	@org.junit.Test
	public void test() {
		IO io = new IO();
		Test test = (Test) io.readPage("test");
		assertTrue(test.getType().equals("test"));
		assertTrue(test.getPageName().equals("test"));
		assertTrue(test.getTotalScore() == 100);
		
		for(Question q : test.getQuestionList()){
			if(q instanceof ChoiceQuestion){
				ChoiceQuestion cq = (ChoiceQuestion) q;
				assertTrue(cq.getAnswer().writeAnswer().trim().equals("0"));
				assertTrue(cq.getItem().get(0).equals("item"));
				assertTrue(cq.getPrompt().equals("cq"));
				assertTrue(cq.getScore() == 1);
			}
			else if(q instanceof DecideQuestion){
				DecideQuestion dq = (DecideQuestion) q;
				assertTrue(dq.getAnswer().getAnswer().equals("1"));
				assertTrue(dq.getPrompt().equals("ff"));
				assertTrue(dq.getScore() == 12);
			}			else if(q instanceof EssayQuestion){
				EssayQuestion eq = (EssayQuestion) q;
				assertTrue(eq.getPrompt().equals("fff"));
			}
			else if(q instanceof MapQuestion){
				MapQuestion mq = (MapQuestion) q;
				assertTrue(mq.getAnswer().writeAnswer().trim().equals("3"));
				assertTrue(mq.getItem().get(0).equals("tea"));
				assertTrue(mq.getPrompt().equals("fffffaa"));
				assertTrue(mq.getScore() == 3);
				assertTrue(mq.getSide() == 2);
			}
			else if(q instanceof RankQuestion){
				RankQuestion rq = (RankQuestion) q;
				assertTrue(rq.getAnswer().writeAnswer().trim().equals("4"));
				assertTrue(rq.getItem().get(0).equals("ee"));
				assertTrue(rq.getPrompt().equals("vvv"));
				assertTrue(rq.getScore() == 7);
			}
			else if(q instanceof ShortEssayQuestion){
				ShortEssayQuestion seq = (ShortEssayQuestion) q;
				assertTrue(seq.getAnswer().writeAnswer().trim().equals("5"));
				assertTrue(seq.getPrompt().equals("rrrr"));
				assertTrue(seq.getScore() == 6);
			}
		}
	}

}
