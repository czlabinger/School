package genericUtils;

/**
 * Eine Util klasse mit generic methoden
 * @author Christof Zlabinger
 * @version 2023-02-15
 */
public class GenericUtils {
	
	/**
	 * Dreht den übergebenen Array um
	 * @param <T> der typ
	 * @param obj der array
	 * @return den umgedrehten array
	 */
	public static <T> T[] umdrehen(T[] obj){
		for(int i = 0; i < obj.length / 2; i++) {
			T temp = obj[i];
			obj[i] = obj[obj.length - i - 1];
			obj[obj.length - i - 1] = temp;
		}
		return obj;
	}
}
