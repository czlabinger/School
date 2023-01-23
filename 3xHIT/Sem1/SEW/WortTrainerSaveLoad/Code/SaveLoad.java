import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
	
	public static void Save(String path, WortTrainer obj) throws IOException {
		File f = new File(path);
		BufferedWriter bw = null;
		ObjectOutputStream os = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(f));
			os = new ObjectOutputStream(new FileOutputStream(f));
			
			for(int i = 0; i < obj.getWordList().getLength(); i++) {
				os.writeObject(obj);
				
				bw.write(obj.getSelectedIndex());
				bw.write(obj.getEingaben());
				bw.write(obj.getRichtig());
				
				
			}
		} finally{
			bw.close();
			os.close();
		}
	}
	
	public static void Load(String path, WortTrainer obj) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		WortTrainer obj2 = (WortTrainer) in.readObject();
		System.out.println(obj2.getStatistic());
		in.close();
	}
}
