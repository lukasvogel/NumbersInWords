package numbersinwords;

public class WordConstructorGerman {
	
	private static String digitName(int digit) {
		
		switch (digit) {
		case 1: return "ein";
		case 2: return "zwei";
		case 3: return "drei";
		case 4: return "vier";
		case 5: return "fünf";
		case 6: return "sechs";
		case 7: return "sieben";
		case 8: return "acht";
		case 9: return "neun";
		case 10: return "zehn";
		case 11: return "elf";
		case 12: return "zwölf";
		case 13: return "dreizehn";
		case 14: return "vierzehn";
		case 15: return "fünfzehn";
		case 16: return "sechzehn";
		case 17: return "siebzehn";
		case 18: return "achtzehn";
		case 19: return "neunzehn";
		}
		
		return "";
	}
	
	private static String digitName(String digit) {
		
		int desiredDigit = 0;
		
		try {
			desiredDigit = Integer.valueOf(digit); 
			
		} catch(Exception e) {
			return "";
		}
		
		return digitName(desiredDigit);
	}
		
	private static String tupleName(String number) {
		
		if (number.length() >= 3)
			return "NaN";
		
		int digit1 = Integer.valueOf(number.substring(0, 1));
		int digit2 = Integer.valueOf(number.substring(1, 2));
		
		String prefix = "";
		
		switch (digit1) {
		case 2: prefix = "zwanzig"; break;
		case 3: prefix = "dreißig"; break;
		case 4: prefix = "vierzig"; break;
		case 5: prefix = "fünfzig"; break;
		case 6: prefix = "sechzig"; break;
		case 7: prefix = "siebzig"; break;
		case 8: prefix = "achtzig"; break;
		case 9: prefix = "neunzig"; break;
		}
		
		if (digit1 == 1 || digit1 == 0)
			return digitName(number);
		else if (digit2 == 0)
			return prefix;
		else
			return digitName(digit2) + "und" + prefix ;

			
	}
	
	private static String tripleName(String number) {
		
		if (number.length() >= 4)
			return "NaN";
		
		String part1 = number.substring(0,1);
		String part2 = number.substring(1, 3);
		
		if (part1.startsWith("0"))
			return tupleName(part2);
		else
			return digitName(part1) + "hundert" + tupleName(part2);
			
	}

	private static String stripZeroes(String input) {
		
		if (input.matches("0+"))
			return "0";
		else {
			while (input.startsWith("0")) {
				input = input.replaceFirst("0","");
			}
		}
		
		return input;
	}
	
	private static String getSuffix(int i) {
		
		switch (i) {
		case 1: return "";
		case 2: return "tausend";
		case 3: return "Million"; 
		case 4: return "Milliarde"; 
		case 5: return "Billion";
		case 6: return "Billiarde"; 
		case 7: return "Trillion"; 
		case 8: return "Trilliarde";
		case 9: return "Quadrillion";
		}
		
		return "";
	}
	
	public static String constructWord(String number) {
		
		String temp = "";
		String word = "";
		String adjustement = "";
		int count = 1;
		
		number = stripZeroes(number);

		if (number == "0")
			return "null";
		
		while (number != "") {
			if (number.length() >= 3) {
				temp = number.substring(number.length() - 3, number.length());
				
				if (number.length() >= 4)
					number = number.substring(0, number.length() - 3);
				else 
					number = "";
				
				if (temp.endsWith("01"))
					adjustement = "s";
				
				if (temp.contains("000")) {
					word = tripleName(temp) + word;
				}
				else {
					
					if ((count == 3) || (count == 5) || (count == 7) || (count == 9))
						word = tripleName(temp) + adjustement + " " + getSuffix(count) + "en " + word;
					else if ((count == 4 || count == 6 || count == 8))
						word = tripleName(temp) + adjustement + " " + getSuffix(count) + "n " + word;
					else if (count >= 3)
						word = tripleName(temp) + adjustement + " " + getSuffix(count) + word;
					else
						word = tripleName(temp) + adjustement + getSuffix(count) + word;
				}
				
				adjustement = "";
				
			}
			else if (number.length() == 2) {
				temp = number;
				number = "";

				if ((count == 3) || (count == 5) || (count == 7) || (count == 9))
					word = tupleName(temp) + " " + getSuffix(count) + "en " + word;
				else if (count == 4 || count == 6 || count == 8)
					word = tupleName(temp) + " " + getSuffix(count) + "n " + word;
				else if (count >= 3)
					word = tupleName(temp) + " " + getSuffix(count) + word;
				else
					word = tupleName(temp) +  getSuffix(count) + word;

			}
			else if (number.length() == 1) {
				temp = number;
				number = "";
				
				if (temp.contains("1") && (count == 3) || (count == 5) || (count == 7) || (count ==9))
					word = "eine " + getSuffix(count) + " " + word;
				else if (temp.contains("1") && count >= 3)
					word = "eine " + getSuffix(count)+ " " + word;
				else if (temp.contains("1") && count < 3)
					word = "eine " + digitName(temp) + getSuffix(count) + word;
				else if (count < 3)
					word = digitName(temp) + getSuffix(count) + word;
				else 
					word = digitName(temp) + " " + getSuffix(count) + "en" + word;

					
			}
			
			count += 1;
		}
		return word;
	}
}
