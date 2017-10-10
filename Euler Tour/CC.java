/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/

package cs6301.g22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CC
{
	
	class CCVertex 
	{
		Graph.Vertex element;
		boolean seen;
		int cno;
		int dis;
		CCVertex parent = null; // needed for tracing the dfs path
		int fin = 0; // to store the finished time of the vertex
		int top = 0;
		boolean rec = false; //to find out if the present vertex is part of the present dfs tree
		
		CCVertex(Graph.Vertex u)
		{
			element = u;
			seen = false;
			cno = -1;
		}
		
		
	}
	
	CCVertex[] ccVertex;
	Graph g;
	
	public CC(Graph g)
	{
		this.g = g;
		ccVertex = new CCVertex[g.size()];
		for(Graph.Vertex u:g)
		{
			ccVertex[u.name] = new CCVertex(u);
		}
	}
	
	
	
	void dfsVisit(Graph.Vertex u, int cno)
	{
		
		visit(u,cno);
		for(Graph.Edge e: u)
		{
			Graph.Vertex v = e.otherEnd(u);
			if(!seen(v))
				dfsVisit(v,cno);
		}
	}
	
	boolean seen(Graph.Vertex u)
	{
		CCVertex ccv = getCCVertex(u);
		return ccv.seen;
	}
	void visit(Graph.Vertex u, int cno)
	{
		CCVertex ccu = getCCVertex(u);
		ccu.seen = true;
		ccu.cno = cno;
	}
	
	CCVertex getCCVertex(Graph.Vertex u)
	{
		return ccVertex[u.name];
	}
	
   static Graph.Vertex getVertex(CCVertex c) 
   {
		return c.element;
   }
   
   
	
	int findCC()
	{
		int cno = 0;
		for(Graph.Vertex u:g)
		{
			if(!seen(u))
			{
				cno++;
				dfsVisit(u,cno);
			}
		}
		return cno;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner in;
		if(args.length > 0)
		{
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		}
		else {
			in = new Scanner(System.in);
		}
		Graph g = Graph.readGraph(in);
		CC cc = new CC(g);
		int nc = cc.findCC();
		
		System.out.println("Input Graph has " + nc + " components:");
		for(Graph.Vertex u: g) {
		    System.out.print(u + " [ " + cc.getCCVertex(u).cno + " ] :");
		    for(Graph.Edge e: u.adj) {
			Graph.Vertex v = e.otherEnd(u);
			System.out.print(e + " ");
		    }
		    System.out.println();
		}
	}
}