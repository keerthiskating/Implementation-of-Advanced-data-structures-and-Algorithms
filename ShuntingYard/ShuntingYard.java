/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/

package cs6301.g22;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ShuntingYard {
	
	static Dictionary<Character, Integer> d = new Hashtable<Character, Integer>();
	
	static void setDictionary() {	//numbered according to precedence
		d.put('(', 1);	//Highest precedent - so best rank 
		d.put(')', 1);  
		d.put('!',10);	//Unary operator - greater than 9
	    d.put('*',-3);	//negative for left associative and positive for right associative
	    d.put('/',-3);	
	    d.put('+',-4);
	    d.put('-', -4);
	    d.put('^', 2);
	}
	
	static Queue<Character> infixToPostfix(String infix) {
		setDictionary();
		
		Stack <Character> s= new Stack<>();
		Queue <Character> q = new LinkedList<>();
		
		for (int i=0; i < infix.length(); i++) {
			
			char c = infix.charAt(i);
			
			if( Character.isDigit(c) || Character.isLetter(c) ) 	
				q.add(c);
			
			else if (c == ' ')	// because we don't consider white spaces present between the operands and operators 
				continue;		
						
			else if( c == '(')
				s.push(c);		
			
			else if (c == ')') {
				while ( !(s.peek().equals('(')) )
					q.add(s.pop());
				s.pop();
			}			
			
			else if (isUnary(c))	// unary operator is assumed to be postfix				
				q.add(c);
						
			else {
				
				if(getAssociativity(c) < 0) {	// operator is left associative

					if (!s.empty() && s.peek() == '(')
						s.push(c);
					
					else {						
						while ( !s.empty() && comparePrecedence(c, s.peek()) <= 0 )							 
							q.add(s.pop());						
					s.push(c);												
					}
					
				}
				
				else if(getAssociativity(c) > 0) {

					if (!s.empty() && s.peek() == '(')
						s.push(c);					
					
					else {
						while ( (!s.empty()) && comparePrecedence(c, s.peek()) < 0 )
							q.add(s.pop());						
					s.push(c);						
					}
						
				}								
			}
		}
		
		while (!(s.empty()))
			q.add(s.pop());
				
		return q;
	}
	
	static int getAssociativity(Character a) {		//left associative? -1 : +1
		return (int)java.lang.Math.signum(d.get(a));	
	}
	
	static int comparePrecedence(Character a, Character b) { // precedence of a > precedence of b? 1 : -1
		
			if (java.lang.Math.abs(d.get(a).intValue()) < java.lang.Math.abs(d.get(b).intValue())) 
				return 1;
			else if (java.lang.Math.abs(d.get(a).intValue()) > java.lang.Math.abs(d.get(b).intValue()))
				return -1;
			else
				return 0;
	}
	
	static boolean isUnary(char a) {	// unary? true : false
		
		if (d.get(a).intValue() > 9)
			return true;
		else
			return false;
	}
	
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			System.out.print("Enter the infix expression: ");
			String infix = in.nextLine();
			System.out.println(ShuntingYard.infixToPostfix(infix));
		}
		
		finally {
			in.close();
		}	
	}
}