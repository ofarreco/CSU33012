package Assignment1;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class DirectedAcyclicGraphTest {

	@Test
	public void testDirectedAcyclicGraph()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(2, 5);
		graph.addEdge(4, 6);
		graph.addEdge(4, 7);
		
		assertEquals(1, graph.indeg(4));
		assertEquals(2, graph.outdeg(4));
		assertEquals(5, graph.edge());
		assertEquals(10, graph.X());
		String adjacent = "[6, 7]";
		assertEquals(adjacent, graph.adjacent(4).toString());
	}
	@Test(expected=Exception.class)
	public void exceptionTest(){
		
		//Impossible to create when there's less than 0 vertices
		
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(-5);
	}
	
	@Test
	public void addEdge()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(5);
		
		graph.addEdge(1,2);

		//Negative so will print a system error and will not addEdge
		graph.addEdge(-1, -6);
		
		//This will not addEdge as 12 > 5
		graph.addEdge(3, 12);
		
		assertEquals(1, graph.edge());
	}
	@Test
	public void testIndeg()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		
		assertEquals(1, graph.indeg(3));
	
		assertEquals(-1, graph.indeg(5));
	}
	@Test
	public void testLCAForEmptyDirectedAcyclicGraph()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(5);
		assertEquals(-1, graph.findLCA(0, 2));
		assertEquals(-1, graph.findLCA(0, 4));
		assertEquals(-1, graph.findLCA(0, 0));
	}
	@Test
	public void testLCAForSameVertex()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(1, 3);
		
		assertEquals(3, graph.findLCA(3, 3));
	}
	@Test
	public void testLCAForNonDirectedAcyclicGraph()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(3, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 4);
		
		assertEquals(-1, graph.findLCA(3, 2));
		assertEquals(-1, graph.findLCA(2, 4));
		assertEquals(-1, graph.findLCA(1, 3));
		assertEquals(-1, graph.findLCA(0, 3));
		assertEquals(-1, graph.findLCA(1, 2));
		
	}
	@Test
	public void testLCA()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		//--------3---4----8--
		//---0--1-------6----7
		//--------2---5-------
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(2, 5);
		graph.addEdge(5, 6);
		graph.addEdge(4, 6);
		graph.addEdge(6, 7);
		graph.addEdge(4, 8);
		graph.addEdge(8, 7);
		
		assertEquals(1, graph.findLCA(4, 5));
		assertEquals(8, graph.findLCA(7, 8));
		assertEquals(6, graph.findLCA(6, 7));
	}
	
	@Test
	public void testLCAforNoCommonAncestors()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(1, 3);
		graph.addEdge(3, 2);
		graph.addEdge(2, 4);
		graph.addEdge(1, 5);
		graph.addEdge(2, 5);
		
		//Check LCA function works
		assertEquals(0, graph.findLCA(2, 1));
		assertEquals(3, graph.findLCA(2, 3));
		assertEquals(2, graph.findLCA(4, 5));
		
		//Check for non-present vertex
		assertEquals(-1, graph.findLCA(8, 2));
		
		//Check for negative vertex
		assertEquals(-1, graph.findLCA(-2, 3));
		assertEquals(-1, graph.findLCA(3, -2));
		assertEquals(-1, graph.findLCA(-2, -3));
	}
	@Test
	public void testOutdegree()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		
		assertEquals(1, graph.outdeg(3));
	
		assertEquals(-1, graph.outdeg(5));
	}
	
	@Test 
	public void testV()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(6);
		assertEquals(6, graph.X());
	}
	
	@Test
	public void testE(){
		
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		
		assertEquals(3, graph.edge());
	}
	
	@Test
	public void testAdj()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(5);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		graph.addEdge(4, 3);
		
		String adjacent = "[4]";
		assertEquals(adjacent, graph.adjacent(2).toString());
	}
	
	@Test
	public void testforCycle()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		
		graph.findCycle(0);
		
		assertTrue(graph.hasCycle());
	}
	
	@Test
	public void testAcyclicGraph()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 3);
		
		graph.findCycle(1);
		assertFalse(graph.hasCycle());
	}
	
	@Test
	public void testLCA()
	{
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(10);
		
		//--------3---4----8--
		//---0--1-------6----7
		//--------2---5-------
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(2, 5);
		graph.addEdge(5, 6);
		graph.addEdge(4, 6);
		graph.addEdge(6, 7);
		graph.addEdge(4, 8);
		graph.addEdge(8, 7);
		
		assertEquals(1, graph.findLCA(4, 5));
		assertEquals(8, graph.findLCA(7, 8));
		assertEquals(6, graph.findLCA(6, 7));
	}
}
