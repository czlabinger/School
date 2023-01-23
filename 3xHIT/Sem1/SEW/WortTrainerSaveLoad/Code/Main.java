import java.io.IOException;
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
		
		String[] words = {"Hund", "Affe", "Ball", "Apfel"};
		String[] urls = {"https://hund.com", "https://affe.com", "https://ball.com", "https://apfel.com"};
		
 		WortTrainer wt = new WortTrainer(words, urls);
 		boolean running = true;
 		Scanner sc = new Scanner(System.in);
 		
 		while(running) {
 			wt.pickRandomWord();	
 			String input = sc.nextLine();
 		
 			if(wt.checkSelectedWord(input)) {
 				System.out.println("true");
 			}
 			
 			if(input.equals("exit")) {
 				running = false;
 			}
 		}
 		sc.close();
 		
 		try {
			SaveLoad.Save("/home/stoffi05/Documents/SEW/WortTrainerSaveLoad/test.txt", wt);
		} catch (IOException e) {
			System.err.println("Error");
		}
 		
 		try {
 			SaveLoad.Load("/home/stoffi05/Documents/SEW/WortTrainerSaveLoad/test.txt", wt);
 		}catch(IOException e) {
 			System.err.println("LoadError");
		} catch(ClassNotFoundException e) {
			System.err.println("LoadError2");
		}
 		
 		System.out.println(wt.getStatistic());
	}
}
