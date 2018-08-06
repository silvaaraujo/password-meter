package br.com.gumga;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gumga.model.AvaliationResult;
import br.com.gumga.model.enums.ComplexityEnum;
import br.com.gumga.utils.Addition;
import br.com.gumga.utils.Deduction;

@RunWith(SpringRunner.class)
public class FinalScoreTest {

	private final String password = "5Xye3#G3";
	private AvaliationResult result;
	private Addition addition;
	private Deduction deduction;
	
	@Before
	public void init() {
		this.result = new AvaliationResult(85, ComplexityEnum.VERY_STRONG.getLabel());
		this.addition = new Addition(this.password);
		this.deduction = new Deduction(this.password);
	}
	
	@Test
	public void calculatedFinalScore() throws Exception {
		int score = this.addition.getAdditionCalculatedValue() - this.deduction.getDeductionCalculatedValue();
		assertEquals(result, new AvaliationResult(score, ComplexityEnum.getFromScore(score).getLabel()));
	}

}
