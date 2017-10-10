/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/

package cs6301.g22;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class Graph implements Iterable<Graph.Vertex>
{
	int n;
	Vertex[] v;
	boolean directed;
	
	public static class Vertex implements Iterable<Graph.Edge>
	{
	int name;
	List<Edge> adj, revAdj;
	int inDegree = 0;
	int top = -1;
	public Vertex(int n)
	{
		name = n;
		adj = new LinkedList<Edge>();
		revAdj = new LinkedList<Edge>();
	}
	
	public int getName() {
	    return name;
	}
	
	public static<T> T getVertex(T[] node, Vertex u) {
	    return node[u.name];
	}
	
	public String toString() {
	    return Integer.toString(name+1);
	}
	
	public Iterator<Edge> iterator() {
		// TODO Auto-generated method stub
		return adj.iterator();
	}
	}
	
	
	
	public static class Edge
	{
		Vertex from;
		Vertex to;
		int weight;
		
		public Edge(Vertex u, Vertex v, int w)
	{
		from = u;
		to = v;
		weight = w;
		
	}
	
	public Vertex otherEnd(Vertex u)
	{
		assert from == u || to == u;
		if(from == u){
			return to;}
		else {return from;}
	}
	public String toString() {
	    return "(" + from + "," + to + ")";
	}

	public String stringWithSpaces() {
	    return from + " " + to + " " + weight;
	}
	}
	
	public Graph(int n)
	{
		this.n = n;
		this.v = new Vertex[n];
		this.directed = false;
		
		for(int i = 0; i<n; i++)
		{
			v[i] = new Vertex(i);
		}
	}
	
	public int size()
	{
		return n;
	}

	
	public Iterator<Graph.Vertex> iterator() {
		return new ArrayIterator<Graph.Vertex>(v);
		
	}
	
	public Vertex getVertex(int n)
	{
		return v[n-1];
	}	
	
	public void addEdge(Graph.Vertex u, Graph.Vertex v, int w)
	{
		Edge e = new Edge(u,v,w);
		if(this.directed)
		{
			u.adj.add(e);
			v.revAdj.add(e);
		}
		else 
		{
			u.adj.add(e);
			v.adj.add(e);
		}
	}
	
	
	
	public static Graph readGraph(Scanner in)
	{
		return readGraph(in, false);
	}
	
	public static Graph readGraph(Scanner in, boolean directed)
	{
		int n = in.nextInt();
		int m = in.nextInt();
		
		Graph g = new Graph(n);
		g.directed = directed;
		for(int i = 0; i<m; i++)
		{
			int u = in.nextInt();
		    int v = in.nextInt();
		    int w = in.nextInt();
		    g.addEdge(g.getVertex(u), g.getVertex(v), w);
		}
		return g;
		}

	public static Graph readDirectedGraph(Scanner in) {
		return readGraph(in, true);
		
	}
	}


