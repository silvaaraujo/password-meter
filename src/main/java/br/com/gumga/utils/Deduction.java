package br.com.gumga.utils;

/**
 * ------------------------------------------------------------- <br />
 * ----------------------REMOVE FROM SCORE---------------------- <br /> 
 * ------------------------------------------------------------- <br />
 * Letters Only			  	 			| Flat 	 | -n            <br /> OK
 * ------------------------------------------------------------- <br />
 * Numbers Only			  	 			| Flat 	 | -n            <br /> OK
 * ------------------------------------------------------------- <br />
 * Repeat Characters (Case Insensitive)	| Comp 	 | -             <br /> OK
 * ------------------------------------------------------------- <br />
 * Consecutive Uppercase Letters		| Flat	 | -(n*2)        <br /> OK
 * ------------------------------------------------------------- <br />
 * Consecutive Lowercase Letters		| Flat	 | -(n*2)        <br /> OK
 * ------------------------------------------------------------- <br />
 * Consecutive Numbers					| Flat	 | -(n*2)        <br /> OK
 * ------------------------------------------------------------- <br />
 * Sequential Letters (3+)				| Flat	 | -(n*3)        <br /> OK
 * ------------------------------------------------------------- <br />
 * Sequential Numbers (3+)				| Flat	 | -(n*3)        <br /> OK
 * ------------------------------------------------------------- <br />
 * Sequential Symbols (3+)				| Flat	 | -(n*3)        <br /> OK
 * ------------------------------------------------------------- <br />
 * 
 * @author Thiago Silva
 */
public class Deduction {

	private String password;

	public Deduction(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		if (this.password == null) password = "";
		return password;
	}
	
	public int getDeductionCalculatedValue() {
		int deduction = 0;
		deduction += this.byLettersOnly();
		deduction += this.byNumbersOnly();
		deduction += this.byRepeatCharacters();
		deduction += this.byConsecutiveUppercaseLetters();
		deduction += this.byConsecutiveLowercaseLetters();
		deduction += this.byConsecutiveNumbers();
		deduction += this.bySequentialLetters();
		deduction += this.bySequentialNumbers();
		deduction += this.bySequentialSymbols();
		
		return deduction;
	}
	
	//-n
	public int byLettersOnly() {
		int stringChars = (this.getPassword().replaceAll("[^a-zA-Z]","")).length();
		return stringChars == this.getPassword().length() ? stringChars : 0;
	}
	
	//-n
	public int byNumbersOnly() {
		int justNumbers = (this.getPassword().replaceAll("[^0-9]","")).length();
		return justNumbers == this.getPassword().length() ? justNumbers : 0;
	}
	
	public int byRepeatCharacters() {
		String[] arrPwd = this.getPassword().replace("/s+","").split("");
		int arrPwdLen = arrPwd.length;
		double nRepInc = 0;
		double nRepChar = 0;
		double nUnqChar = 0;

		for (int a=0; a < arrPwdLen; a++) {
			boolean bCharExists = false;
			
			for (int b=0; b < arrPwdLen; b++) {
				
				if (arrPwd[a].equals(arrPwd[b]) && a != b) { // repeat character exists
					bCharExists = true;
					
					//Calculate icrement deduction based on proximity to identical characters
					//Deduction is incremented each time a new match is discovered
					//Deduction amount is based on total password length divided by the
					//difference of distance between currently selected match
					nRepInc += Math.abs(Double.valueOf(arrPwdLen)/(b-a));
				}
			}
			
			if (bCharExists) { 
				nRepChar++; 
				nUnqChar = arrPwdLen-nRepChar;
				nRepInc = ((nUnqChar > 0) ? Math.ceil(nRepInc/nUnqChar) : Math.ceil(nRepInc)); 
			}	
		}
		return (int)nRepInc;
	}

	//-(n*2)
	public int byConsecutiveUppercaseLetters() {
		int consecutives = 0;
		String uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[] positions = this.getPassword().split("");
		String lastValue = null;
		
		for (String s : positions) {
			if (!uppers.contains(s)) {
				lastValue = null;
				continue;
			}
			
			if (lastValue == null) {
				lastValue = s;
				continue;
			}
			
			consecutives++;
			lastValue = s;
		}
		
		return consecutives * 2;
	}
	
	//-(n*2)
	public int byConsecutiveLowercaseLetters() {
		int consecutives = 0;
		String lowers = "abcdefghijklmnopqrstuvwxyz";
		String[] positions = this.getPassword().split("");
		String lastValue = null;
		
		for (String s : positions) {
			if (!lowers.contains(s)) {
				lastValue = null;
				continue;
			}
			
			if (lastValue == null) {
				lastValue = s;
				continue;
			}
			
			consecutives++;
			lastValue = s;
		}
		
		return consecutives * 2;
	}
	
	//-(n*2)
	public int byConsecutiveNumbers() {
		int consecutives = 0;
		String[] positions = this.getPassword().split("");
		Integer lastValue = null;
		
		for (String s : positions) {
			if (!this.isNumber(s)) {
				lastValue = null;
				continue;
			}
			
			if (lastValue == null) {
				lastValue = Integer.parseInt(s);
				continue;
			}
			
			consecutives++;
			lastValue = Integer.parseInt(s);
		}
		
		return consecutives * 2;
	}
	
	private boolean isNumber(String value) {
		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	//Sequential Letters (3+)
	//-(n*3)
	public int bySequentialLetters() {
		int sequenceCount = 0;
		String alphas = "abcdefghijklmnopqrstuvwxyz";
		
		for (int i = 0; i < 8; i++) {
			String forwardSequence = alphas.substring(i, i+3);
			String reverseSequence = new StringBuilder(forwardSequence).reverse().toString();
			
			if (this.getPassword().toLowerCase().indexOf(forwardSequence) != -1 || 
				this.getPassword().toLowerCase().indexOf(reverseSequence) != -1) {
				sequenceCount++; 
			}
		}
		
		return sequenceCount * 3;
	}
	
	//Sequential Numbers (3+)
	//-(n*3)
	public int bySequentialNumbers() {
		int sequenceCount = 0;
		String numbers = "01234567890";

		for (int i = 0; i < 8; i++) {
			String forwardSequence = numbers.substring(i, i+3);
			String reverseSequence = new StringBuilder(forwardSequence).reverse().toString();
			
			if (this.getPassword().toLowerCase().indexOf(forwardSequence) != -1 || 
				this.getPassword().toLowerCase().indexOf(reverseSequence) != -1) {
				sequenceCount++; 
			}
		}
		
		return sequenceCount * 3;
	}
	
	//Sequential Symbols (3+)
	//-(n*3)
	public int bySequentialSymbols() {
		String symbols = ")!@#$%^&*()";
		int sequenceCount = 0;
		
		for (int i = 0; i < 8; i++) {
			String forwardSequence = symbols.substring(i, i+3);
			String reverseSequence = new StringBuilder(forwardSequence).reverse().toString();
			
			if (this.getPassword().toLowerCase().indexOf(forwardSequence) != -1 || 
				this.getPassword().toLowerCase().indexOf(reverseSequence) != -1) {
				sequenceCount++; 
			}
		}
		
		return sequenceCount * 3;
	}

}
