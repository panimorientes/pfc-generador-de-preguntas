package preguntas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class EstrategiaPermutacionOperaciones implements EstrategiaPermutaciones {

	@Override
	public ArrayList<Character> permute(ArrayList<Character>array, Random rnd) {
	
		boolean flag1=true;
		int stackSize=array.size();
		Stack<Character> stack = new Stack<Character>();
		int times=0;
		int get=0;
		int taken=0;
		ArrayList<Character>posibles=new ArrayList<Character>();
		int route=1;
		
		int introduced=0;
		char x='A';
		
		while(flag1 )
		{
			route=1;
			times=rnd.nextInt(stackSize-1)+1;;
			
			while(route<=times)
			{
				
				  stack.push(x);
				  route++;
				
				  x++;
				  introduced++;
				 
				  if(introduced==stackSize)
				  {
					  
					  flag1=false;
					  break;
				  }
				
			}
			
			get=(int) rnd.nextInt(times)+1;
			
			while ( get>taken && !stack.empty())
			{
			
				  posibles.add(stack.pop());
				 taken++;
			}
				
			taken=0;
			
		}
		while(!stack.empty())
		{
			posibles.add(stack.pop());
		}
		return posibles;
		
	}

}
