package Assignment1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DirectedAcyclicGraph
{
	private int X;						//number of vertices
	private int edge;						//number of edges
	private ArrayList<Integer>[] adjacent;   //adjacent[X] = adjacency list for vertex X
	private int [] indeg;			//indeg[X] = indeg of vertex X
	private int [] outdeg;			//outdeg[X] = outdeg of vertex X
	private boolean marked [];			//list of visited vertices
	private boolean hasCycle;			//True if graph has cycle
	private boolean stack [];			//
	
	
	public DirectedAcyclicGraph(int X)
	{
		if(X < 0)
		{
			throw new IllegalArgumentException("Number of vertices must be more than 0");
		}
		
		this.X = X;
		this.edge = 0;
		indeg = new int[X];
		marked = new boolean[X];
		stack = new boolean[X];
		adjacent = (ArrayList<Integer>[]) new ArrayList[X];
		
		for(int x = 0; x < X; x++)
		{
			adjacent[x] = new ArrayList<Integer>();
		}
	}
	
	//Returns current vertex
	public int X()
	{
		return X;
	}
	
	public int edge()
	{
		return edge;
	}
	
	//Adds directed edge from x to z
	public void addEdge(int x, int z)
	{
		if((validVert(x) > 0) && (validVert(z) > 0))
		{
			adjacent[x].add(z);
			indeg[z]++;
			edge++;
		}
		else
		{
			System.out.println("Please enter numbers between 0 and " + (X-1));
		}		
	}
	
	private int validVert(int x)
	{
		if(x < 0 || x >= X)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	
	//Returns number of directed edges to vertex x
	public int indeg(int x)
	{
		if(validVert(x) > 0)
		{
			return indeg[x];
		}
		else
		{
			return -1;
		}
		
	}
	
	//Returns number of directed edges from vertex x
	public int outdeg(int x)
	{
		if(validVert(x) > 0)
		{
			return adjacent[x].size();
		}
		else
		{
			return -1;
		}
	}
	
	//Returns the adjacent vertices to x
	public Iterable<Integer> adjacent(int x)
	{
		return adjacent[x];
	}
	
	public boolean hasCycle()
	{
		return hasCycle;
	}
	
	public void findCycle(int x)
	{
		marked[x] = true;
		stack[x] = true;
		
		for(int z : adjacent(x))
		{
			if(!marked[z])
			{
				findCycle(z);
			}
			else if(stack[z])
			{
				hasCycle = true;
				return;
			}
		}
		stack[x] = false;
	}
	
	//Method to implement lowest common ancestor
	public int findLCA(int x, int z)
	{
		findCycle(0);
		
		if(hasCycle) //Graph is not DirectedAcyclicGraph
		{
			return -1;
		}
		else if(validVert(x) < 0 || validVert(z) < 0)
		{
			//Not valid vertices, ie. non-negative
			return -1;
		}
		else if(edge == 0)
		{
			//Graph has no edges, ie. empty
			return -1;
		}
		
		DirectedAcyclicGraph reverse = reverse();
		
		ArrayList<Integer> arr1 = reverse.BFS(x);
		ArrayList<Integer> arr2 = reverse.BFS(z);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
		
		boolean found = false;
		
		for(int a = 0; a < arr1.size(); a++)
		{
			for(int b = 0; b < arr2.size(); b++)
			{
				if(arr1.get(a) == arr2.get(b))
				{
					commonAncestors.add(arr1.get(a));
					found = true;
				}
			}
		}
		
		if(found)
		{
			//Return the first element in list - Lowest Common Ancestor
			return commonAncestors.get(0);
		}
		else
		{
			return -1; //If none found
		}
	}
	
	//Prints BFS(Breadth-First search) from source s
	public ArrayList<Integer> BFS(int s)
	{
		ArrayList<Integer> order = new ArrayList<Integer>();
		boolean visited[] = new boolean[X]; //Marks vertices as not visited
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		visited[s] = true;
		queue.add(s);
		
		while(queue.size() != 0)
		{
			s = queue.poll(); //Sets s to the head of the list
			order.add(s);
			
			//Find adjacent vertices to s. If not visited,
			//mark as visited (true) and enqueue
			Iterator<Integer> a = adjacent[s].listIterator();
			
			while(a.hasNext())
			{
				int n = a.next();
				if(!visited[n])
				{
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return order;
	}
	
	//Reverse DirectedAcyclicGraph
	public DirectedAcyclicGraph reverse()
	{
		DirectedAcyclicGraph reverse = new DirectedAcyclicGraph(X);
		for(int x = 0; x <X; x++)
		{
			for(int z : adjacent(x))
			{
				reverse.addEdge(z, x);
			}		
		}
		return reverse;
	}
}
