import java.io.*;

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
	
	int getTotalUniqueWords() {
		return 0;
	}
	String get20MostFrequentWords(){
		return "";
	}
	String get20MostInterestingFrequentWords(){
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
	//List<String> getAutocompleteSentence(String startOfSentence){
	//	return "";
	//}
	//String findClosestMatchingQuote(){
	//	return "";
	//}
	
	public static void main(String[] args) {
		Project_Gutenberg.getTotalNumberOfWords();

	}

}
