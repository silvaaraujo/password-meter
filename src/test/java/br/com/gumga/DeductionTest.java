package br.com.gumga;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.gumga.utils.Deduction;

public class DeductionTest {

	private Deduction deduction;
	
	@Before
	public void init() {
		this.deduction = new Deduction("5Xye3#G3");
	}
	
	@Test
	public void scoreByLettersOnly() {
		assertEquals(0, this.deduction.byLettersOnly());
	}
	
	@Test
	public void scoreByNumbersOnly() {
		assertEquals(0, this.deduction.byNumbersOnly());
	}
	
	@Test
	public void scoreByRepeatCharacters() {
		assertEquals(1, this.deduction.byRepeatCharacters());
	}
	
	@Test
	public void scoreByConsecutiveUppercaseLetters() {
		assertEquals(0, this.deduction.byConsecutiveUppercaseLetters());
	}
	
	@Test
	public void scoreByConsecutiveLowercaseLetters() {
		assertEquals(2, this.deduction.byConsecutiveLowercaseLetters());
	}
	
	@Test
	public void scoreByConsecutiveNumbers() {
		assertEquals(0, this.deduction.byConsecutiveNumbers());
	}
	
	@Test
	public void scoreBySequentialLetters() {
		assertEquals(0, this.deduction.bySequentialLetters());
	}
	
	@Test
	public void scoreBySequentialNumbers() {
		assertEquals(0, this.deduction.bySequentialNumbers());
	}
	
	@Test
	public void scoreBySequentialSymbols() {
		assertEquals(0, this.deduction.bySequentialSymbols());
	}
	
	@Test
	public void totalDeduction() {
		assertEquals(3, this.deduction.getDeductionCalculatedValue());
	}
	
}
