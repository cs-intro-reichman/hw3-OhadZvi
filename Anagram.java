/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String str1PrePro = spacesRemover(preProcess(str1));
		String str2PrePro = spacesRemover(preProcess(str2));
		if (str1PrePro.length() != str2PrePro.length()) {
			return false;
		}
		for (int i = 0; i < str1PrePro.length(); i++) {
			char c = str1PrePro.charAt(i);
			if (str2PrePro.indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ('a' <= c && c <= 'z') {
				res += c;
			} else if ('A' <= c && c <= 'Z') {
				res += (char)(c + 32);
			} else if (c == ' ') {
				res += c;
			}
		}
		return res;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String res = "";
		String strLeftOver = str;
		for (int i = 0; i < str.length(); i++) {
			int randIndex = (int) (Math.random() * strLeftOver.length());
			res += strLeftOver.charAt(randIndex);
			strLeftOver = charRemover(strLeftOver, randIndex);
		}
		return res;
	}

	// Returns a string that is the same as the given string, but with all the spaces removed.
	public static String spacesRemover(String str) {
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ( c != ' ') {
				res += c;
			}
		}
		return res;
	}

	// Returns the same string but without a character at a selected index.
	public static String charRemover(String str, int index) {
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			if (i != index) {
				res += str.charAt(i);
			}
		}
		return res;
	}
}
