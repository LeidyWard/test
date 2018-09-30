import java.io.*;
import java.util.*;

public class Project_Gutenberg {

	//function to count and return number of words from book txt file
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
	
	static void getTotalUniqueWords() throws FileNotFoundException {
		File file = new File("src/Book.txt");
		ArrayList list = new ArrayList();
		HashMap<String, Integer> words = new HashMap<String, Integer>();
		Scanner scan = new Scanner(file);
		int i = 0;
		
		while(scan.hasNext()) {
			String str = scan.next();
			//System.out.println(str);
			list.add(str);
		}
		Iterator j = list.iterator();
		while(j.hasNext()) {
			i++;
			words.put((String)j.next(), i);
			//System.out.println(words);
		}
		Set<Object> uniqueWords = new HashSet<Object>(words.values());
		
		System.out.println("Number of unique words: " + uniqueWords.size());
	}
	
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
					Integer occur = countWord.get(w);
					if(occur == null)
						occur = 1;
					else
						occur++;
					countWord.put(w, occur);
				}
			}
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
	/*String get20MostInterestingFrequentWords(){
		return "";
	}
	String get20LeastFrequentWords(){
		return "";
	}
	String getFrequencyOfWord(){
		return "";
	}
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

	}

}
