package br.com.gumga.utils;

/**
 * Class created to keep together all of additions rules. <br />
 * <br />
 * ------------------------------------------------------------- <br />
 * ---------------------RULES TO ADD SCORE --------------------- <br />
 * ------------------------------------------------------------- <br />
 * Number of Characters  	 | Flat 	 | +(n*4)                <br /> OK
 * ------------------------------------------------------------- <br /> 
 * Uppercase Letters	 	 | Cond/Incr | +((len-n)*2)          <br /> OK
 * ------------------------------------------------------------- <br />
 * Lowercase Letters	 	 | Cond/Incr | +((len-n)*2)          <br /> OK
 * ------------------------------------------------------------- <br />
 * Numbers				 	 | Cond 	 | +(n*4)                <br /> OK
 * ------------------------------------------------------------- <br />
 * Symbols				 	 | Flat		 | +(n*6)                <br /> OK
 * ------------------------------------------------------------- <br />
 * Middle Numbers or Symbols | Flat		 | +(n*2)                <br /> OK
 * ------------------------------------------------------------- <br />
 * Requirements				 | Flat		 | +(n*2)                <br /> OK  
 * ------------------------------------------------------------- <br />
 * 
 * @author Thiago Silva
 */
public class Addition {

	private String password;

	public Addition(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		if (this.password == null) password = "";
		return password;
	}
	
	public int getAdditionCalculatedValue() {
		int addiction = 0;
		addiction += this.byNumberOfCharacters();
		addiction += this.byUppercaseLetters();
		addiction += this.byLowercaseLetters();
		addiction += this.byNumbers();
		addiction += this.bySymbols();
		addiction += this.byMiddleNumbersOrSymbols();
		addiction += this.byRequirements();
		
		return addiction;
	}
	
	//+(n*4)
	public int byNumberOfCharacters() {
		return this.getPassword().length() * 4;
	}
	
	//+((len-n)*2
	public int byUppercaseLetters() {
		int numOfUpperLetters = (this.getPassword().replaceAll("[^A-Z]", "")).length();
		return numOfUpperLetters == 0 ? 0 : ((this.getPassword().length() - numOfUpperLetters) * 2);
	}
	
	//+((len-n)*2
	public int byLowercaseLetters() {
		int numOfLowerLetters = (this.getPassword().replaceAll("[^a-z]", "")).length();
		return numOfLowerLetters == 0 ? 0 : ((this.getPassword().length() - numOfLowerLetters) * 2);
	}
	
	//+(n*4)
	public int byNumbers() {
		int numOfNumbers = (this.getPassword().replaceAll("\\D+","")).length();
		if (this.getPassword().length() == numOfNumbers) return 0;
		return numOfNumbers * 4;
	}
	
	//+(n*6)
	public int bySymbols() {
		int numOfSymbols = (this.getPassword().replaceAll("[a-zA-Z0-9_]","")).length();
		return numOfSymbols * 6;
	}
	
	//+(n*2)
	public int byMiddleNumbersOrSymbols() {
		if (this.getPassword().length() < 2) return 0;
		int count = 0; 
		
		String[] values = this.getPassword().substring(1, this.getPassword().length()-1).split("");
		for (String val : values) {
			if (String.valueOf(val).matches("[^0-9^A-Z^a-z]")) count++;
			if (String.valueOf(val).matches("[0-9]")) count++;
		}
		
		return count * 2;
	}

	//+(n*2)
	public int byRequirements() {
		int requerimentsCounter = 0;
		int minRequeriment = 4;
		boolean passwordOk = false;
		
		//Minimum 8 characters in length
		if (this.getPassword().length() >= 8) {
			minRequeriment = 3;
			passwordOk = true;
		}
		
		//Uppercase Letters
		if ((this.getPassword().replaceAll("[^A-Z]", "")).length() > 0) {
			requerimentsCounter++;
		}
		
		//Lowercase Letters
		if ((this.getPassword().replaceAll("[^a-z]", "")).length() > 0) {
			requerimentsCounter++;
		}
		
		//Numbers
		if ((this.getPassword().replaceAll("\\D+","")).length() > 0) {
			requerimentsCounter++;
		}
		
		//Symbols
		if ((this.getPassword().replaceAll("[a-zA-Z0-9]","")).length() > 0) {
			requerimentsCounter++;
		}
		
		if (passwordOk && requerimentsCounter >= minRequeriment) {
			requerimentsCounter++;
			return requerimentsCounter * 2;
		}
		
		return 0;
	}
	
}
