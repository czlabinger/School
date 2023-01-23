import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * Speichert einen WortEintrag und eine URL ab
 * @author Christof Zlabinger
 * @version 2022-09-14
 */
public class WortEintrag {
	
	private String word = "";
	private String url = "";
	
	/**
	 * Constructor für den WortEintrag
	 * @param word das Wort das abgespeichert werden soll
	 * @param url die URL zu einem Bild des Wortes
	 */
	public WortEintrag(String word, String url) {
		
		if(word.length() >= 2) {
			this.word = word;
		}else {
			this.word = "notAValideWord";
			throw new IllegalArgumentException("Word has to be at least two characters!");
		}
		
		if(checkURL(url)) {
			this.url = url;
		}else {
			this.url = "invalideURL";
			throw new IllegalArgumentException("Not a valid URL!");
		}
	}
	
	/**
	 * Checkt ob die URL valid ist
	 * @param url die URL die getestet werden soll
	 * @return ob die URL valid ist
	 */
	public static boolean checkURL(String url) {
		try {
			new URL(url).toURI();
			return true;
			
		}catch (URISyntaxException | MalformedURLException e) {
			throw new IllegalArgumentException("Not a valid URL!");
		}
	}
	
	/**
	 * Setzt das Wort des WortEintrags
	 * @param word das wort das gesetzt werden soll
	 */
	public void setWord(String word) {
		if(word.length() >= 2) {
			this.word = word;
		}else {
			throw new IllegalArgumentException("Word has to be at least two characters!");
		}
	}
	
	/**
	 * Setzt die URL des WortEintrages
	 * @param url die URL die gesetzt werden soll
	 */
	public void setURL(String url) {
		if(checkURL(url)) {
			this.url = url;
		}else {
			throw new IllegalArgumentException("Not a valid URL!");
		}
	}
	
	/**
	 * returnt das Wort des WortEintrages
	 * @return das Wort des WortEintrages
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * returnt die URL des WortEintrages
	 * @return die URL des WortEintrages
	 */
	public String getURL() {
		return this.url;
	}
	
	@Override
	public String toString() {
		return this.word + "; " + this.url;
	}
}
