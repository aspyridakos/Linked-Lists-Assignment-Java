//--------------------------------------------------------
//Part: 1
//Written by:Alexandra Spyridakos, 40175280
//--------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverPart1 {

	/**
	 * Driver to test file data processing 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Enter the name of the file you wish to parse then hit the enter key: ");
		Scanner userInput = new Scanner(System.in);
		String fileName = userInput.nextLine();
		userInput.close();
		
		Scanner sc = null;
		PrintWriter pw = null;

		try {
			sc = new Scanner(new FileInputStream(fileName));		
			
			int wordCountOs = 0;
			int wordCountVowels = 0;
			int wordCountDistinct = 0;
			int vowelCount = 0;
			ArrayList<String> token_arr = new ArrayList<String>();
			ArrayList<String> vowelWords = new ArrayList<String>();
			ArrayList<String> O_Words = new ArrayList<String>();
			ArrayList<String> distinctWords = new ArrayList<String>();

			while(sc.hasNext()) {
				String tokens = sc.next().replaceAll("[^a-zA-Z0-9]", "");
				token_arr.add(tokens);
			}
			for (String token:token_arr) {
				for (int j=0; j<token.length();j++) {
					String character = Character.toString(token.charAt(j));
					//checking for greater than 3 vowels in a word
					if (character.equalsIgnoreCase("a")||character.equalsIgnoreCase("e")||character.equalsIgnoreCase("i")||character.equalsIgnoreCase("o")||character.equalsIgnoreCase("u")) {
						vowelCount++;
					}
					//checking for words starting with "o" or "O"
					if (character.equalsIgnoreCase("o") && j == 0) {
						O_Words.add(token);
						wordCountOs++;
					}
				}
				//checking for distinct words
				if (!distinctWords.contains(token)) {
					distinctWords.add(token);
					wordCountDistinct++;
				}
				
				if (vowelCount>3) {
					vowelWords.add(token);
					wordCountVowels++;
				}
				vowelCount = 0;
			}
			
			//outputting contents for "vowel_verbiage.txt"
			pw = new PrintWriter(new FileOutputStream("vowel_verbiage.txt"));
			pw.println("Word Count: " + wordCountVowels);
			for (String word:vowelWords) {
				pw.println(word);
			}
			vowelWords = null;
			pw.close();
			
			//outputting contents for "obessive_o.txt"
			pw = new PrintWriter(new FileOutputStream("obsessive_o.txt"));
			pw.println("Word Count: " + wordCountOs);
			for (String word:O_Words) {
				pw.println(word);
			}
			O_Words = null;
			pw.close();
			
			//outputting contents for "distinct_data.txt"
			pw = new PrintWriter(new FileOutputStream("distinct_data.txt"));
			pw.println("Word Count: " + wordCountDistinct);
			for (String word:distinctWords) {
				pw.println(word);
			}
			distinctWords = null;
			pw.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
			System.out.println("Invalid file inputed. Exiting program after closing any remaining opened files.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Error occured due to a failure during reading, writing or searching file or directory operations. Exiting program after closing any remaining opened files.");
			System.exit(0);
		}
		System.out.println("Sub-dictionaries have been successfully created. Exiting program.");
		sc.close();
		pw.close();
	}
//history_of_java.txt
}
