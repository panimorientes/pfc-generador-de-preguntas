package preguntas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * La clase EstrategiaPermutacionCaracteres.
 */
public class EstrategiaPermutacionCaracteres implements EstrategiaPermutaciones {

	
	@Override
	public ArrayList<Character> permute(ArrayList<Character> array, Random rnd) {
		Collections.shuffle(array, rnd);
		return array;
	}

}
