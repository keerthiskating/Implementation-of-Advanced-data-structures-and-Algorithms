/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/
package cs6301.g22;

import java.util.List;
import java.util.ListIterator;
import cs6301.g22.Graph.Edge;
import java.util.LinkedList;

public class Euler {
    int VERBOSE;
    List<Graph.Edge> tour;
    
    EulerVertex[] ev;
    Graph g;
    Graph.Vertex startVertex; 
    
    // Constructor
    Euler(Graph g, Graph.Vertex start) {
	VERBOSE = 1;
	tour = new LinkedList<>();
	
	this.g = g;
	startVertex=start;
	ev = new EulerVertex[g.size()];
	for(Graph.Vertex u: g) { ev[u.name] = new EulerVertex(u); }
	
    }
	
     class EulerVertex {
    	List<Graph.Edge> edgetour;
    	Graph.Vertex element;
    	boolean tourVisited;
    	ListIterator<Graph.Edge> it;
    	EulerVertex(Graph.Vertex u)
    	{
    		element=u;
    	 	edgetour=new LinkedList<>();
    		tourVisited = false;
    		it = u.adj.listIterator();
    	}
     }
    
    // To do: function to find an Euler tour
    public List<Graph.Edge> findEulerTour() {
	findTours();
	if(VERBOSE > 9) { printTours(); }
	stitchTours();
	return tour;
    }  

    /* To do: test if the graph is Eulerian.
     * If the graph is not Eulerian, it prints the message:
     * "Graph is not Eulerian" and one reason why, such as
     * "inDegree = 5, outDegree = 3 at Vertex 37" or
     * "Graph is not strongly connected"
     */
    boolean isEulerian() {
    	if(StronglyConnected.isSCC(g))
    	{
    		for (Graph.Vertex u :g )
    		{
    			if(u.adj.size()==u.revAdj.size())
    			{
    				continue;
    			}
    			else{
    				System.out.println("Graph is not Eulerian because ");
    				System.out.println("Reason: indegree="+ u.revAdj.size()+ " "+"Outdegree="+u.adj.size()+ "at Vertex"+" "+u.toString());
    				return false;
    			}
    		}
    		return true;
    	}
    	
	System.out.println("Graph is not Eulerian");
	System.out.println("Reason: Graph is not strongly connected");
	return false;
    }
    
 // Find tours starting at vertices with unexplored edges
    void findTours() {
    findTour(startVertex,ev[startVertex.name].edgetour);
    
     for(Graph.Vertex v : g)
     {
    	 if(ev[v.name].it.hasNext())
    	 {
    		 findTour(v, ev[v.name].edgetour);
    	 }
     }
    }

//Helper method for find tours    
    void findTour(Graph.Vertex u, List<Edge> edgetour) {
    	
    	Graph.Vertex temp_v = u;

    	while(ev[temp_v.name].it.hasNext()) {
    		
    	  Graph.Edge temp_edge =ev[temp_v.name].it.next();
    		      	 
           edgetour.add(temp_edge);		
    	   temp_v = temp_edge.otherEnd(temp_v);
    	 
    	}
    }

    
    /* Print tours found by findTours() using following format:
     * Start vertex of tour: list of edges with no separators
     * Example: lp2-in1.txt, with start vertex 3, following tours may be found.
     * 3: (3,1)(1,2)(2,3)(3,4)(4,5)(5,6)(6,3)
     * 4: (4,7)(7,8)(8,4)
     * 5: (5,7)(7,9)(9,5)
     *
     * Just use System.out.print(u) and System.out.print(e)
     */
    void printTours() {
    	for(EulerVertex v : ev){
    		System.out.println(v.element.name+": "+v.edgetour);    	
    	}

    }

    // Stitch tours into a single tour using the algorithm discussed in class
    void stitchTours() {
    	explore(startVertex);
    }
//Helper method for stitch tours
    private void explore(Graph.Vertex v) {
		Graph.Vertex temp;
		temp = v;
		for (Edge e : ev[v.name].edgetour){
			tour.add(e);
			temp = e.otherEnd(temp);
		if(!ev[temp.name].tourVisited){
			explore(temp);
			ev[v.name].tourVisited = true;
		}
		}
		
	}

	void setVerbose(int v) {
	VERBOSE = v;
    }
}
