package numbersinwords;

import numbersinwords.WordConstructorGerman;
import numbersinwords.WordConstructorEnglish;

public class WordConstructor {
	
	public static String constructWord(String language, String number) {
		
		if (language.contains("German")) 
			return WordConstructorGerman.constructWord(number);
		else if (language.contains("English"))
			return WordConstructorEnglish.constructWord(number);
		else if (language.contains("Roman Numerals")) {
			RomanNumeralConstructor rnc = new RomanNumeralConstructor(Integer.valueOf(number));
			return rnc.assembleNumeral();
		}
		else
			return "Invalid language";
			
	}
}