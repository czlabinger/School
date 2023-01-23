import java.util.Random;

/**
 * WortTrainer der eine WortListe hat und diese überprüfen kann
 * @author Christof Zlabinger
 *@version 2022-09-14
 */
public class WortTrainer {
	
	private WortListe wordList;
	private int selectedIndex = 0;
	
	/**
	 * Constructor für den WortTrainer
	 * @param words die Wörter für die WortListe
	 * @param urls die URLs für die WortListe
	 */
	public WortTrainer(String[] words, String[] urls) {
		this.wordList = new WortListe(words, urls);
	}
	
	/**
	 * Wählt ein zufälliges Wort aus der Liste ein
	 * @return das zufällig gewählte Wort
	 */
	public String pickRandomWord() {
		Random ran = new Random();
		int ranInt = ran.nextInt(wordList.getLength());
		selectedIndex = ranInt;
		
		System.out.println(wordList.getURL(ranInt));
		return wordList.getWord(ranInt);
	}
	
	/**
	 * checkt ein ob der Input das selected wort ist
	 * @param input Das Wort das überprüft werden soll
	 * @return ob der input gleich den selected wort ist
	 */
	public boolean checkSelectedWord(String input) {
		System.out.println(wordList.getWord(selectedIndex));
		if(input.equals(wordList.getWord(selectedIndex))) {
			return true;
		}
		return false;
	}
	
	/**
	 * checkt ein ob der Input das selected wort ist und Ignoriert groß und klein schreibung
	 * @param input Das Wort das überprüft werden soll
	 * @return ob der input gleich den selected wort ist
	 */
	public boolean checkSelectedWordIgnoreCase(String input) {
		System.out.println(wordList.getWord(selectedIndex));
		if(input.equalsIgnoreCase(wordList.getWord(selectedIndex))) {
			return true;
		}
		return false;
	}
	
	/**
	 * returnt die WortListe
	 * @return die WortListe
	 */
	public WortListe getWordList() {
		return this.wordList;
	}
	
}
