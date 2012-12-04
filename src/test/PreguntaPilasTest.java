package test;



import java.util.ArrayList;
import preguntas.PreguntaPilas;

import org.junit.Assert;
import org.junit.Test;

public class PreguntaPilasTest {
	
	@Test
	public void comprobarTest(){
		
		PreguntaPilas pregunta=new PreguntaPilas();
		ArrayList<Integer> comprobar=new ArrayList<Integer>();
		comprobar.add(4);
		comprobar.add(3);
		comprobar.add(2);
		comprobar.add(1);
		comprobar.add(0);
		comprobar.add(9);
		comprobar.add(8);
		comprobar.add(7);
		comprobar.add(6);
		comprobar.add(5);
		 
		Assert.assertTrue(pregunta.comprobarCorrectas(comprobar));
	comprobar.clear();
		comprobar.add(2);
		comprobar.add(5);
		comprobar.add(6);
		comprobar.add(7);
		comprobar.add(4);
		comprobar.add(8);
		comprobar.add(9);
		comprobar.add(3);
		comprobar.add(1);
		comprobar.add(0);
		Assert.assertTrue(pregunta.comprobarCorrectas(comprobar));
		comprobar.clear();
		
		comprobar.add(4);
		comprobar.add(6);
		comprobar.add(8);
		comprobar.add(7);
		comprobar.add(5);
		comprobar.add(3);
		comprobar.add(2);
		comprobar.add(9);
		comprobar.add(0);
		comprobar.add(1);
		Assert.assertFalse(pregunta.comprobarCorrectas(comprobar));
		comprobar.clear();
		comprobar.add(2);
		comprobar.add(1);
		comprobar.add(4);
		comprobar.add(3);
		comprobar.add(6);
		comprobar.add(5);
		comprobar.add(8);
		comprobar.add(7);
		comprobar.add(9);
		comprobar.add(0);
		Assert.assertTrue(pregunta.comprobarCorrectas(comprobar));
		ArrayList<Character> comprobar1=new ArrayList<Character>();
		comprobar1.add('J');
		comprobar1.add('D');
		comprobar1.add('C');
		comprobar1.add('E');
		comprobar1.add('H');
		comprobar1.add('B');
		comprobar1.add('F');
		comprobar1.add('A');
		comprobar1.add('I');
		comprobar1.add('G');
		Assert.assertFalse(PreguntaPilas.comprobarCorrectasChar(comprobar1));
		
		
		
	
		
		
		
	}
	
	

}
