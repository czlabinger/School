
/**
 * Eine Liste aus WortEinträgen
 * @author Christof Zlabinger
 * @version 2022-09-14
 */
public class WortListe {
	
	private WortEintrag[] words;
	
	/**
	 * Constructor für WortListe
	 * @param words die Wörter die in der WortListe gespeichert werden sollen
	 * @param urls die urls die in der WortListe gespeichert werden sollen
	 */
	public WortListe(String[] words, String[] urls) {
		
		this.words = new WortEintrag[words.length];
		
		for(int i = 0; i < words.length; i++) {
			this.words[i] = new WortEintrag(words[i], urls[i]);
		}
		
		for(int i = 0; i < urls.length; i++) {
			this.words[i].setURL(urls[i]);
		}
	}
	
	/**
	 * Fügt ein wort in in die Wortliste hinen. Wenn ein platz frei ist wird es dort hinzugefügt und wenn nicht wird der array um eine stelle verlängert
	 * @param word das wort das hinzugeügt werden soll
	 */
	public void addWord(String word) {
		
		if(word.length() < 2) {
			throw new IllegalArgumentException("Word has to be at least two characters!");
		}
		
		String[] newWords = new String[this.words.length + 1];
		
		for(int i = 0; i < this.words.length; i++) {
			newWords[i] = this.words[i].getWord();
			
			if(words[i].getWord().equals("  ")) {
				newWords[i] = word;
				return;
			}
			
			words[i].setWord(newWords[i]);
		}
		newWords[this.words.length + 1] = word;
	}
	
	/**
	 * returnt das word an einem spezifischen index
	 * @param index der index an dem das wort returned werden soll
	 * @return das wort an der stelle des index
	 */
	public String getWord(int index) {
		if(index > words.length) {
			throw new IndexOutOfBoundsException("The index specified is out of bounds!");
		}
		
		return this.words[index].getWord();
	}
	
	/**
	 * returnt die url an einem spezifischen index
	 * @param index der index an dem die url returnt werden soll
	 * @return die url an der stelle des indexes
	 */
	public String getURL(int index) {
		return this.words[index].getURL();
	}
	
	/**
	 * removt ein das angegebene wort
	 * @param word das wort das removt werden soll
	 * @return ob das wort succselfull removed wurde
	 */
	public boolean removeWord(String word) {
		
		if(word.length() < 2) {
			throw new IllegalArgumentException("Word has to be at least two characters!");
		}
		
		for(int i = 0; i < this.words.length; i++) {
			if(this.words[i].getWord().equals(word)) {
				this.words[i].setWord("  ");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * gibt jeden Worteintrag in einer einzelnen zeile zurück
	 */
	@Override
	public String toString() {
		String print = "";
		for(int i = 0; i < this.words.length; i++) {
			print += words[i].toString() + "\n";
		}
		return print;
	}
	
	/**
	 * returnt die länge der Liste
	 * @return
	 */
	public int getLength() {
		return words.length;
	}
	

}
