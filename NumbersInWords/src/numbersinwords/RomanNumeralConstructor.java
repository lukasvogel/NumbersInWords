package numbersinwords;

public class RomanNumeralConstructor {
	
	private int number;
	private String romanNumeral;
	
	RomanNumeralConstructor(int Number) {
		this.number = Number;
	}
	
	private String getHighestPossibleGlyph() {
		
		if (number >= 1000) {
			number -= 1000;
			return "M";
		}
		else if (number >= 500) {
			number -= 500;
			return "D";
		}
		else if (number >= 100) {
			number -= 100;
			return "C";
		}
		else if (number >= 50) {
			number -= 50;
			return "L";
		}
		else if (number >= 10) {
			number -= 10;
			return "X";
		}
		else if (number >= 5) {
			number -= 5;
			return "V";
		}
		else if (number >= 1) {
			number -= 1;
			return "I";
		}
		else
			return "?";
	}
	
	private void condenseGlyphs() {
		
		romanNumeral = romanNumeral.replaceAll("IIII", "IV");
		romanNumeral = romanNumeral.replaceAll("XXXX", "XL");
		romanNumeral = romanNumeral.replaceAll("CCCC", "CD");
			
		romanNumeral = romanNumeral.replaceAll("VIV", "IX");
		romanNumeral = romanNumeral.replaceAll("LXL", "XC");
		//romanNumeral = romanNumeral.replaceAll();
				
	}
	
	public String assembleNumeral() {
		romanNumeral = "";
		while (number > 0) {
			romanNumeral += getHighestPossibleGlyph();
		}
		condenseGlyphs();
		return romanNumeral;
	}

}
