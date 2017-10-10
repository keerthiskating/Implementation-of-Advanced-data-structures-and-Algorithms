/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/

package cs6301.g22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cs6301.g22.CC.CCVertex;
import cs6301.g22.Graph.Vertex;

public class StronglyConnected {
 
	int time = 0;
	int topNum = 0;
	int cno = 0;
	CC cc = null;
	LinkedList<Graph.Vertex> finList = new LinkedList<Graph.Vertex>(); //list of finish times in descending order
	
	
	int stronglyConnectedComponents(Graph g) 
	{ 
		cc = new CC(g);
		dfs(g); // normal dfs on graph g(method call to dfs(g) described in this class)
		for(Graph.Vertex u: g) //finding the transpose of the graph
		{
			List<Graph.Edge> temp = u.adj;
			u.adj = u.revAdj;
			u.revAdj = temp;
		}
		
		Iterator<Graph.Vertex> it= finList.iterator(); 
		dfs(it); //dfs with iterator(method call to dfs(iterator) function defined in this class)
		return cno; // number of connected components
	}
	
	public void dfs(Iterator<Vertex> i)
	{
		Iterator<Graph.Vertex> it = i;
		CCVertex[] V = cc.ccVertex; //array of all the ccVertices for corresponding graph g
	    for(CCVertex u:V)
		{
			u.seen = false;
		}
	    
	   while(it.hasNext())
		{
			Graph.Vertex l = it.next();
			CCVertex u = cc.getCCVertex(l);
			if(!u.seen)
			{
				cno++;
				Graph.Vertex v = cc.getVertex(u);
				cc.dfsVisit(v, cno); //method call to function defined in dfsVisit(Graph.Vertex u, cno)  
			}                        // defined in CC class
		}
	}
	
	void dfs(Graph g)
	{
		for(Graph.Vertex l :g)
		{
			CCVertex u = cc.getCCVertex(l);
			if(!u.seen)
			{
			    dfsVisit(u);//method call to dfsVisit(CCVertex u) defined in this class
			}
		}
	}
	
	void dfsVisit(CCVertex u) //similar to dfsVisit() defined in topologicalOrder class 
	{                        // but removing all the not needed details like u.dis
		u.seen = true;
		Graph.Vertex temp = cc.getVertex(u);
		for(Graph.Edge e:temp.adj)
		{
			Graph.Vertex x = e.otherEnd(temp);
			CCVertex v = cc.getCCVertex(x);
			if(!v.seen)
			{
				dfsVisit(v);
			}
		}
		u.fin = ++time;
		finList.addFirst(temp); //adding the finished elements in decreasing order
	}
		public static boolean isSCC(Graph g)
		{
			StronglyConnected sc = new StronglyConnected();
			int result = sc.stronglyConnectedComponents(g);
				if(result==1)
				{
					return true;
				}
				return false;
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
		
		Graph g = Graph.readGraph(in,true);
		
		StronglyConnected sc = new StronglyConnected();
		int ans = sc.stronglyConnectedComponents(g);
		System.out.println("Number of strongly connected components"+ " "+ans);
	}
}
