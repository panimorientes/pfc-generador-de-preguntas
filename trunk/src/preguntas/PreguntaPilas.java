package preguntas;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Stack;
import java.util.Random;
import java.io.*;
import java.util.logging.*;

/**
 * Clase PreguntaPilas.
 */
public class PreguntaPilas {

	/**
	 * El metodo principal.
	 *
	 * @param args Los argumentos
	 * @throws IOException Señales de que una excepción de E / S se ha producido.
	 */
	public static void main(String[] args) throws IOException {
		
		MiLogger logger = MiLogger.getInstancia("logger");
		Logger log = Logger.getLogger("Logger de Ejemplo");
		
		Random rnd = new Random();
	
		
		File file;
		int seed;
		int stackSize;
		int numberOptions;
		int correctNumber;
		EstrategiaPermutaciones estrategiaCaracteres=new EstrategiaPermutacionCaracteres();
		EstrategiaPermutaciones estrategiaOperaciones=new EstrategiaPermutacionOperaciones();
		double correctParameter;
		
		String giftFormat="";
		String property;
		String filename="PreguntasGift/PreguntaPila.txt";
		String pregunta="";
		
		boolean continuar=true;
	
		ArrayList<Character> result=new ArrayList<Character>();
		ArrayList<Character> array=new ArrayList<Character>();
		ArrayList<Character>options=new ArrayList<Character>();
		ArrayList<Character> corrects=new ArrayList<Character>();
		ArrayList<Integer>correctPositions=new ArrayList<Integer>();
		
		
		Properties properties = new PropertiesFile().getProperties();
		property=properties.getProperty("stackSize");
		stackSize=Integer.parseInt(property);
		property=properties.getProperty("numberOption");
		numberOptions=Integer.parseInt(property);
		property=properties.getProperty("correctParameter");
		correctParameter=Double.parseDouble(property);
		
		
		char lastLetter='A';
		for(int i=1;i<stackSize;i++)
		{
			
			lastLetter++;
		}
		
		file=FileUtilities.createFile(filename);
		
		while(continuar)
		{
			 correctNumber=0;
			 lastLetter='A';
				
			 for(int i=0;i<stackSize;i++)
			 {
				array.add(  lastLetter++);
			 }
			 
			 seed= (int) java.lang.Math.round(Math.random()*999999999+0);

			 rnd.setSeed(seed);
			 
			 giftFormat=giftFormat+"(seed= "+seed+")\r\n";
			 
			pregunta=pregunta+"(seed= "+seed+")\n";
			 lastLetter--;
		
			
			 pregunta=pregunta+ "Sobre una pila se realizan entremezcladas operaciones de apilar y desapilar. Los valores que se apilan son las letras de la A a la "+ lastLetter+", en ese orden. Al desapilar se imprime el valor.\n";	
			giftFormat=giftFormat+"Sobre una pila se realizan entremezcladas operaciones de apilar y desapilar. Los valores que se apilan son las letras de la A a la "+  lastLetter+", en ese orden. Al desapilar se imprime el valor.\n";
			
			giftFormat=giftFormat+"¿Cuál de las siguientes secuencias podra darse mediante este proceso?{\r\n";
			pregunta=pregunta+"¿Cuál de las siguientes secuencias podra darse mediante este proceso?\n";
			
			
			
			
			 lastLetter='A';
			for(int i=1;i<=numberOptions;i++)
			{
				if(correctParameter<=rnd.nextDouble())
				{
					correctPositions.add( i);
					correctNumber++;
				}
				else
					correctPositions.add(null);
				
			}
		
			if(correctNumber!=0)
				correctNumber=100/correctNumber;
			for(int i=1,a=0;i<=numberOptions;i++,a++)
			{
				options=array;
				
				pregunta=pregunta+""+ lastLetter+") ";
				
				
				if(correctPositions.get(a)!=null)
				{
					giftFormat=giftFormat+"~%"+correctNumber+"% ";
					result=estrategiaOperaciones.permute(array, rnd);
					
					for (int j=0;j<result.size();j++)
					{
						giftFormat=giftFormat+result.get(j)+" ";
						pregunta=pregunta+result.get(j)+" ";
					}
				
					giftFormat=giftFormat+"\r\n";
					pregunta=pregunta+"\n";
					if(comprobarCorrectasChar(result))
						
						corrects.add( lastLetter);
					
				}else
				{
					
					if(i==numberOptions && correctNumber==0 )
					{
						giftFormat=giftFormat+"~%100%"+"Ninguna de las anteriores";
						pregunta=pregunta+"Ninguna de las anteriores";
						corrects.add( lastLetter);
					}
					else
					{
						
						if(i==numberOptions && rnd.nextDouble()<=0.5)
						{
							giftFormat=giftFormat+"~%-100% Ninguna de las anteriores";
							pregunta=pregunta+"Ninguna de las anteriores";
						}
						else
						{
							giftFormat=giftFormat+"~%-100%";
							
							options=estrategiaCaracteres.permute(options, rnd);
							while(comprobarCorrectasChar(options))
							{
								options=estrategiaCaracteres.permute(options, rnd);
								
							}
							for (int j=0;j<options.size();j++)
							{
								giftFormat=giftFormat+options.get(j)+" ";
								pregunta=pregunta+options.get(j)+" ";
							}
						}
						
						
					}
					giftFormat=giftFormat+"\r\n";
					pregunta=pregunta+"\n";
				}
					
				pregunta=pregunta+"";
				
				 lastLetter++;
			}
			giftFormat=giftFormat+"}\r\n\n";
			FileUtilities.writeFile(file,giftFormat);
			pregunta=pregunta+"LAS RESPUESTAS CORRECTAS SON:\n";
			pregunta=pregunta+corrects.toString()+"\n";
			
			corrects.clear();
			array.clear();
			correctPositions.clear();
			
			
			
			char c = ' ';
			logger.getLogger("logger").log(Level.INFO,pregunta+"Pulsa s para salir cualquier otra para continuar");
			pregunta="";
				java.io.BufferedInputStream b = new BufferedInputStream(System.in);
				 c = (char) b.read();
				 if(c=='s')
					 continuar=false;
		}
	}
	
	
	
	/**
	 * Comprobar si el array pasado es una posible solucion cuando sea numerica.
	 *
	 * @param posibleSolucion Array con la posible solucion de la pila 
	 * @return true, Si es correcto
	 */
	public  boolean comprobarCorrectas(ArrayList<Integer> possibleSolution)
	{
		int number=0;
		int position=0;
		int lastLetter=0+possibleSolution.size()-1;
		boolean bigger=false;
		
		boolean correct=false;
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer>aux=new ArrayList<Integer>();
		
		int i=0;
		int taken=0;
		boolean finish=false;
		while(!finish)
		{
			while(possibleSolution.get(position)>=number)
			{
				stack.push(number);
				number++;
				
			}
		
			if(possibleSolution.get(0)==lastLetter)
			{
				bigger=true;
			}
			for(int j=0;j<possibleSolution.size();j++)
			{
				if(possibleSolution.get(j)>possibleSolution.get(i))
				{
					position=j;
					if(possibleSolution.get(position)==lastLetter)
					{
						bigger=true;
					}
					break;
				}
			}
			while(possibleSolution.get(position)>possibleSolution.get(i))
			{
				taken=stack.pop();
				aux.add(taken);
				i++;
			}
			position=i;
			if(bigger)
			{
				while(number<=lastLetter)
				{
					stack.push(number);
					number++;
				}
				
				while(!stack.empty())
				{
					aux.add(stack.pop());
				}
			}
			if(stack.empty())
			{
				finish=true;
			}
		}
	
		
		
		if(aux.equals(possibleSolution))
		
			correct=true;
		else
			correct=false;
		
		
		return correct;
	}
	
	
	
	
	/**
	 * Comprobar correctas cuando la posible solucion cuando sea char.
	 *
	 * @param posibleSolucion Array con  caracteres
	 * @return true, Si es correcta
	 */
	public static boolean comprobarCorrectasChar(ArrayList<Character> possibleSolution)
	{
		char letter='A';
		
		int position=0;
		
		char ultimaLetra=(char) ('A'+possibleSolution.size()-1);
		
		boolean bigger=false;
	
		boolean correct=false;

		Stack<Character> stack = new Stack<Character>();
		
		ArrayList<Character>aux=new ArrayList<Character>();
		
		int i=0;
		
		char taken=0;
		
		boolean finish=false;
		
		while(!finish)
		{
			
			while(possibleSolution.get(position)>=letter)
			{
				
				stack.push(letter);
				
				letter++;
				
			}
			
			if(possibleSolution.get(0)==ultimaLetra)
			{
				
				bigger=true;
			}
			
			for(int j=0;j<possibleSolution.size();j++)
			{
				if(possibleSolution.get(j)>possibleSolution.get(i))
				{
					
					position=j;
					
					if(possibleSolution.get(position)==ultimaLetra)
					{
					
						bigger=true;
						
					}
					
					break;
				}
			}
			
			while(possibleSolution.get(position)>possibleSolution.get(i))
			{
				
				taken=stack.pop();
				
				aux.add(taken);
				i++;
			}
			position=i;
			
			if(bigger)
			{
				
				while(letter<=ultimaLetra)
				{
					stack.push(letter);
					letter++;
				}
				
				while(!stack.empty())
				{
					
					aux.add(stack.pop());
				}
			}
			
			if(stack.empty()&&bigger)
			{
				
				finish=true;
			}
		}
	
	
		if(aux.equals(possibleSolution))
		
			correct=true;
	
		else
		
			correct=false;
		

		return correct;
	}


}
