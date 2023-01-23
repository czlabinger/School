import java.util.Scanner;


/**
 * Main class um den WortTrainer zu testen
 * @author Christof Zlabinger
 * @version 2022-09-14
 */
public class Main {
	
	/**
	 * Testet den WortTrainer
	 * @param args nicht relevant
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] words = {"Hund", "Affe", "Ball", "Apfel"};
		String[] urls = {"https://www.br.de/radio/bayern1/bild-gesund-hund-104~_v-img__16__9__xl_-d31c35f8186ebeb80b0cd843a7c267a0e0c81647.jpg?version=fa8e8", "https://www.sueddeutsche.de/image/sz.1.3610891/704x396?v=1501575716", "https://assets.manufactum.de/p/065/065612/65612_01.jpg/inflatable-red-rubber-ball.jpg", "https://cdn.shopify.com/s/files/1/0073/6775/3801/products/buah_apfel_large.png?v=1635422135"};
 		WortTrainer wt = new WortTrainer(words, urls);
		boolean running = true;
 		
 		wt.getWordList().removeWord("Hund");
 		wt.getWordList().addWord("Hund");
 		
		while(running) {
			
			wt.pickRandomWord();
			String input = sc.next();
			
			if(input.equals("exit")) {
				running = false;
			}
			
			System.out.println(wt.checkSelectedWord(input));
			System.out.println(wt.checkSelectedWordIgnoreCase(input));
		}
		sc.close();
	}
}
