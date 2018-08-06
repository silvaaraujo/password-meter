package br.com.gumga;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.gumga.utils.Addition;

public class AdditionTest {

	private Addition addition;
	
	@Before
	public void init() {
		this.addition = new Addition("5Xye3#G3");
	}
	
	@Test
	public void scoreByNumberOfCharacters() {
		assertEquals(32, this.addition.byNumberOfCharacters());
	}
	
	@Test
	public void scoreByUppercaseLetters() {
		assertEquals(12, this.addition.byUppercaseLetters());
	}
	
	@Test
	public void scoreByLowercaseLetters() {
		assertEquals(12, this.addition.byLowercaseLetters());
	}
	
	@Test
	public void scoreByNumbers() {
		assertEquals(12, this.addition.byNumbers());
	}
	
	@Test
	public void scoreBySymbols() {
		assertEquals(6, this.addition.bySymbols());
	}
	
	@Test
	public void scoreByMiddleNumbersOrSymbols() {
		assertEquals(4, this.addition.byMiddleNumbersOrSymbols());
	}
	
	@Test
	public void scoreByRequirements() {
		assertEquals(10, this.addition.byRequirements());
	}
	
	@Test
	public void totalAddition() {
		assertEquals(88, this.addition.getAdditionCalculatedValue());
	}
	
	
}
