import java.io.Serializable;

/**
 * Speichert einen WortEintrag und eine URL ab
 * @author Christof Zlabinger
 * @version 2022-09-14
 */
public class WortEintrag implements Serializable {
	
	private static final long serialVersionUID = 6454105900590438995L;
	private String word = "";
	private String url = "";
	
	/**
	 * Constructor für den WortEintrag
	 * @param word das Wort das abgespeichert werden soll
	 * @param url die URL zu einem Bild des Wortes
	 */
	public WortEintrag(String word, String url) {
		
		this.setWord(word);
		
		if(checkURL(url)) {
			this.url = url;
		}
	}
	
	/**
	 * Checkt ob die URL valid ist
	 * @param url die URL die getestet werden soll
	 * @return ob die URL valid ist
	 */
	public static boolean checkURL(String url) {
		String[] part1 = url.split("\\.", -1); //part1[0] = all except toplevel | part1[1] = toplevel
        String[] part2 = part1[0].split("://", -1); //part2[0] = protocol | part2[1] = domain

         try {
        	 
        	 if(part2[1].contains("$") || part1[1].contains("$")) {
             	return false;
             }
        	 
        	 return !(part1.length != 2 || part2.length != 2 || !(part2[0].equals("https") ||
                     part2[0].equals("http")) || part1[1].length() < 1 || part2[1].length() < 1);
         }
         catch(ArrayIndexOutOfBoundsException e) { return false; }
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
