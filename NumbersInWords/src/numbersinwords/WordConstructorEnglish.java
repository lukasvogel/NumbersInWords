package numbersinwords;

public class WordConstructorEnglish {
	
	private static String digitName(int digit) {
		
		switch (digit) {
		case 1: return "one";
		case 2: return "two";
		case 3: return "three";
		case 4: return "four";
		case 5: return "five";
		case 6: return "six";
		case 7: return "seven";
		case 8: return "eight";
		case 9: return "nine";
		case 10: return "ten";
		case 11: return "eleven";
		case 12: return "twelve";
		case 13: return "thirteen";
		case 14: return "fourteen";
		case 15: return "fifteen";
		case 16: return "sixteen";
		case 17: return "seventeen";
		case 18: return "eighteen";
		case 19: return "nineteen";
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
		case 2: prefix = "twenty"; break;
		case 3: prefix = "thirty"; break;
		case 4: prefix = "fourty"; break;
		case 5: prefix = "fifty"; break;
		case 6: prefix = "sixty"; break;
		case 7: prefix = "seventy"; break;
		case 8: prefix = "eighty"; break;
		case 9: prefix = "ninety"; break;
		}
		
		if (digit1 == 1 || digit1 == 0)
			return digitName(number);
		else if (digit2 == 0)
			return prefix;
		else
			return prefix + "-" + digitName(digit2);

			
	}
	
	private static String tripleName(String number) {
		
		if (number.length() >= 4)
			return "NaN";
		
		String part1 = number.substring(0,1);
		String part2 = number.substring(1, 3);
		
		if (part1.startsWith("0"))
			return tupleName(part2);
		else
			return digitName(part1) + " hundred " + tupleName(part2);
			
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
		case 2: return "thousand ";
		case 3: return "million "; 
		case 4: return "billion "; 
		case 5: return "trillion ";
		case 6: return "quadrillion "; 
		case 7: return "quintillion "; 
		case 8: return "sextillion ";
		case 9: return "septillion ";
		}
		
		return "";
	}
	
	public  static String constructWord(String number) {
		
		String temp = "";
		String word = "";
		int count = 1;
		
		number = stripZeroes(number);

		if (number == "0")
			return "zero";
		
		while (number != "") {
			if (number.length() >= 3) {
				temp = number.substring(number.length() - 3, number.length());
				
				if (number.length() >= 4)
					number = number.substring(0, number.length() - 3);
				else 
					number = "";
				
				if (temp.contains("000"))
					word = tripleName(temp) + word;
				else 
					word = tripleName(temp) + " " + getSuffix(count) + word;

					
			}
			else if (number.length() == 2) {
				temp = number;
				number = "";
				
				word = tupleName(temp) + " " + getSuffix(count) + word;
			}
			else if (number.length() == 1) {
				temp = number;
				number = "";
				word = digitName(temp) + " " + getSuffix(count) + word;
			}
			
			count += 1;
		}
		return word;
	}
}
