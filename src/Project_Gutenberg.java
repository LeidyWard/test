import java.io.*;
import java.util.*;

public class Project_Gutenberg {

	//function to count and return number of words from book text file
	static void getTotalNumberOfWords() {
		File file = new File("src/Book.txt");
		
		int numOfWords = 0;
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null){
				String[] words = line.split(" ");	//split line at spaces
				numOfWords += words.length;
			}
			System.out.println("Number of words : " + numOfWords);
		}
		catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		finally {
			try {
				if(null != reader)
					reader.close();
			}
			catch(IOException io) {
				io.printStackTrace();
			}
		}
	}
	
	//function returns the number of different words
	static void getTotalUniqueWords() throws FileNotFoundException {
		File file = new File("src/Book.txt");
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, Integer> words = new HashMap<String, Integer>();
		Scanner scan = new Scanner(file);
		int i = 0;
		
		while(scan.hasNext()) {
			String str = scan.next();
			list.add(str);
		}
		Iterator<String> j = list.iterator();
		while(j.hasNext()) {
			i++;
			words.put(j.next(), i);
		}
		Set<Object> uniqueWords = new HashSet<Object>(words.values());
		
		System.out.println("Number of unique words: " + uniqueWords.size());
		scan.close();
	}
	
	//returns the 20 most frequent words in the text file
	static String get20MostFrequentWords(){
		
		System.out.println("\nThe 20 most frequent words:\n");
		
		LinkedHashMap<String, Integer> countWord = new LinkedHashMap<String, Integer>();
		try {
			BufferedReader read = new BufferedReader(new FileReader("src/Book.txt"));
			String s;
			
			while((s = read.readLine()) != null) {
				s = s.toLowerCase();
				String[] words = s.split("\\s+");	//split line at whitespace
				
				for(String w : words) {
					if(w.length() == 0)
						continue;
					Integer counter = countWord.get(w);
					if(counter == null)
						counter = 1;
					else
						counter++;					//keep track of/update frequency of word
					countWord.put(w, counter);
				}
			}
			read.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		ArrayList<Integer> val = new ArrayList<Integer>();
		val.addAll(countWord.values());
		
		Collections.sort(val, Collections.reverseOrder());
		
		int last = -1;
		for(Integer i : val.subList(0, 19)) {
			if(last == i)	//avoids duplicates
				continue;
			last = i;
	
			for(String str : countWord.keySet()) {
				if(countWord.get(str) == i)		//i = number of occurrences
					System.out.println(str + " " + i); 
			}
		}
		return null;
	}
	
	//returns the 20 most interesting words from the text, filters out the 100 most common English words
	//DEFINITELY NEEDS TO BE IMPROVED
	static String get20MostInterestingFrequentWords() throws FileNotFoundException{
		File file = new File("src/CommonWords");
		ArrayList<String> list = new ArrayList<String>();
		HashMap<Integer, String> words = new HashMap<Integer, String>();
		Scanner scan = new Scanner(file);
		int i =0;
		
		while(scan.hasNext()) {
			String str = scan.next();
			list.add(str);
		}
		Iterator<String> j = list.iterator();
		while(j.hasNext()) {
			i++;
			words.put(i, j.next());
		}
		
		LinkedHashMap<String, Integer> countWord = new LinkedHashMap<String, Integer>();
		try {
			BufferedReader read = new BufferedReader(new FileReader("src/Book.txt"));
			String s;
			
			while((s = read.readLine()) != null) {
				s = s.toLowerCase();
				String[] word = s.split("\\s+");		//split line at whitespace
				
				for(String w : word) {
					if(w.length() == 0)
						continue;
					Integer counter = countWord.get(w);
					if(counter == null)
						counter = 1;
					else
						counter++;					//keep track of/update frequency of word
					if(words.containsValue(w))
						continue;
					else
						countWord.put(w, counter);
				}
			}
			read.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		scan.close();
		
		ArrayList<Integer> val = new ArrayList<Integer>();
		val.addAll(countWord.values());
		
		Collections.sort(val, Collections.reverseOrder());
		
		System.out.println("\n\nThe 20 most interesting frequent words:\n");
		
		int last = -1;
		for(Integer it : val.subList(0, 19)) {
			if(last == it)		//avoids duplicates
				continue;
			last = it;
	
			for(String str : countWord.keySet()) {
				if(countWord.get(str) == it)		//it = number of occurrences
					System.out.println(str + " " + it); 
			}
		}
		return null;
	}
	
	//returns the 20 least common words of the text
	//CAN BE IMPROVED
	static String get20LeastFrequentWords(){

		System.out.println("\n\nThe 20 least frequent words:\n");
		
		LinkedHashMap<String, Integer> countWord = new LinkedHashMap<String, Integer>();
		try {
			BufferedReader read = new BufferedReader(new FileReader("src/Book.txt"));
			String s;
			
			while((s = read.readLine()) != null) {
				s = s.toLowerCase();
				String[] words = s.split("[\\s\\p{Punct}]+");	//split line at whitespace and punctuation marks
				
				for(String w : words) {
					if(w.length() == 0)
						continue;
					Integer counter = countWord.get(w);
					if(counter == null)
						counter = 1;
					else
						counter++;					//keep track of/update frequency of word
					countWord.put(w, counter);
				}
			}
			read.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		ArrayList<Integer> val = new ArrayList<Integer>();
		val.addAll(countWord.values());
		
		Collections.sort(val, Collections.reverseOrder());
		Collections.reverse(val);
		
		int last = -1, limit = 0;
		for(Integer i : val.subList(0, 19)) {
			if(last == i)		//avoids duplicates
				continue;
			last = i;
	
			for(String str : countWord.keySet()) {
				if(countWord.get(str) == i && limit < 21) {		//i = number of occurrences
					if(str.matches(".*\\d.*"))
						continue;
					System.out.println(str + " " + i);
					limit++;
				}
			}
		}
		return null;
	}
	
	//returns the frequency of the word sent into the function
	static List<Integer> getFrequencyOfWord(String str) throws FileNotFoundException{
		File file = new File("src/Book.txt");
		//ArrayList<String> list = new ArrayList<String>();			//10 chapters in the book
		ArrayList<Integer> result = new ArrayList<Integer>(11);
		Scanner scan = new Scanner(file);
		int count = 0;

		System.out.println("\n\nThe frequency of the word  - " + str + " -  per chapter:\n");
		
		int chap = 0;
		while(scan.hasNext() && chap < 11) {
			String s = scan.next();
			if(s.toLowerCase().equals("chapter")) {
				result.add(count);
				chap++;
				count = 0;
			}
			else {
				if(s.toLowerCase().equals(str)) 
					count++;
			}
		}
		scan.close();
		return result.subList(1, 11);
	}
	
	/*
	String getChapterQuoteAppears(){
		return "";
	}
	String generateSentence(){
		return "";
	}
	List<String> getAutocompleteSentence(String startOfSentence){
		return "";
	}
	String findClosestMatchingQuote(){
		return "";
	}
	*/
	public static void main(String[] args) throws FileNotFoundException {
		Project_Gutenberg.getTotalNumberOfWords();
		Project_Gutenberg.getTotalUniqueWords();
		Project_Gutenberg.get20MostFrequentWords();
		Project_Gutenberg.get20MostInterestingFrequentWords();
		Project_Gutenberg.get20LeastFrequentWords();
		System.out.println(Project_Gutenberg.getFrequencyOfWord("the"));
	}

}
