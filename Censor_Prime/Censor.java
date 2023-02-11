import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Censor {
	
	public static void censor(File source, File destination, File badlist) throws IOException {
		
		badlist = new File("C:\sBad.txt");
		source = new File("C:\source.txt");
		destination = new File("C:\sDestination");
		
		Scanner sc = new Scanner(System.in);
		String badInput;
		String textInput;
		List<String> badWords = new ArrayList<String>();
		System.out.println("Hello! Please write all bad/sensitive words in a single line, each word separated by a space character");
		
		try (FileWriter fw1 = new FileWriter(badlist)) {
			badInput = sc.nextLine();
			fw1.write(badInput);
			fw1.close();
			System.out.println("write to badlist file successful");
			String[] words = badInput.split(" ");
			for(String word : words) {
				badWords.add(word);
			}
			System.out.println("saved to array list");
		} catch (IOException e) {
			System.out.println("badlist writer exception");
		}
		
		System.out.println("Please enter your text");
		textInput = sc.nextLine();
		List<String> textWords = new ArrayList<String>();
		
		try (FileWriter fw2 = new FileWriter(source)) {
			fw2.write(textInput);
			String[] words = textInput.split(" ");
			for (String word : words) {
				textWords.add(word + " ");
			}
			System.out.println("write to source file successful");
		} catch (IOException e) {
			System.out.println("source writer exception");
		}
		
		BufferedReader br1 = new BufferedReader(new FileReader(source));
		String line1 = "";
		while((line1 = br1.readLine()) != null) {
			for(String badWord : badWords) {
				if(line1.contains(badWord)) {
					String stars = "";
					for(int i = 0; i < badWord.length(); i++) {
						stars += "*";
					}
					line1 = line1.replaceAll(badWord, stars);
				}
			}
			textWords.clear();
			textWords.add(line1);
		}
		br1.close();
		sc.close();
		
		try (FileWriter fw3 = new FileWriter(destination)) {
			
			for (int i = 0; i < textWords.size(); i++) {
				fw3.write(textWords.get(i));
			}
			System.out.println("write to destination file successful");
			
		} catch (IOException e) {
			System.out.println("destination writer exception");
		}
		
		System.out.println("Here is your text with the bad words masked:");
		BufferedReader br2 = new BufferedReader(new FileReader(destination));
		String line2 = "";
		while((line2 = br2.readLine()) != null) {
			int textLength = textWords.size();
			for(int i = 0; i < textWords.size(); i++) {
				System.out.print(textWords.get(i));
			}
		}
		br2.close();
	}
}