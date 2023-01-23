import java.io.Serializable;
import java.util.Random;

/**
 * WortTrainer der eine WortListe hat und diese überprüfen kann
 * @author Christof Zlabinger
 * @version 2022-09-14
 */
public class WortTrainer implements Serializable{
	
	private static final long serialVersionUID = -5144116817679608437L;
	private WortListe wordList;
	private int selectedIndex = 0;
	private int richtig = 0;
	private int eingaben = 0;
	
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
		this.selectedIndex = new Random().nextInt(wordList.getLength());
		
		System.out.println(wordList.getWord(selectedIndex));
		
		return wordList.getWord(selectedIndex);
	}
	
	/**
	 * checkt ein ob der Input das selected wort ist
	 * @param input Das Wort das überprüft werden soll
	 * @return ob der input gleich den selected wort ist
	 */
	public boolean checkSelectedWord(String input) {
		eingaben++;
		
		if(input.equals(wordList.getWord(selectedIndex))) {
			richtig++;
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
		eingaben++;
		if(input.equalsIgnoreCase(wordList.getWord(selectedIndex))) {
			richtig++;
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
	
	public String getStatistic() {
		return "Eingaben: " + eingaben + "; Richtig: " + richtig;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	public int getEingaben() {
		return eingaben;
	}
	
	public int getRichtig() {
		return richtig;
	}
}
